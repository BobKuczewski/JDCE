package dce;

import dce.*;
import parts.*;
import parts.input.*;
import dialogs.*;
import shared.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class View implements ActionListener,AdjustmentListener,WindowListener {
  Frame window;
  MenuBar menuBar;
  Menu fileMenu;
  Menu partMenu;
  Board board;
  PerfBoard perfBoard;
  Scrollbar vScroll;
  Scrollbar hScroll;
  MainMenu makeMain;
  DigiScope digiScope=null;
  Vector probes;
  parts.Component keyListener;
  public View(Board brd) {
    board=brd;
    keyListener=null;
    perfBoard=new PerfBoard(board);
    perfBoard.setOffsetX(0); perfBoard.setOffsetY(0);
    window=new Frame("DCE");
    window.addWindowListener(this);
    window.setSize(640,480);
    window.setLayout(new BorderLayout());
    perfBoard.setWindow(window);
    makeMain=new MainMenu(this);
    window.setMenuBar(makeMain.getMenuBar());
    hScroll=new Scrollbar(Scrollbar.HORIZONTAL,0,8,0,1024);
    hScroll.addAdjustmentListener(this);
    hScroll.setSize(400,20);
    hScroll.setVisible(true);
    window.add(hScroll,"South");
    vScroll=new Scrollbar(Scrollbar.VERTICAL,0,8,0,1024);
    vScroll.addAdjustmentListener(this);
    vScroll.setSize(20,400);
    vScroll.setVisible(true);
    window.add(vScroll,"East");
    window.add(perfBoard,new String("Center"));
    window.setVisible(true);
    perfBoard.setWireColor(Color.black);
    } /* end Board() */

  public void adjustmentValueChanged(AdjustmentEvent e) {
    if (e.getAdjustable()==hScroll) {
      perfBoard.setOffsetX(e.getValue());
      perfBoard.update(perfBoard.getGraphics());
      }
    else if (e.getAdjustable()==vScroll) {
      perfBoard.setOffsetY(e.getValue());
      perfBoard.update(perfBoard.getGraphics());
      }
    }
  public void actionPerformed(ActionEvent e) {
    int i;
    parts.Component temp;
    CKeyboard ktemp;
    FileDialog fileDialog;
    double st,et,cy;
    Vector parts;
    if (e.getActionCommand().charAt(0)=='C') {
      try {
        Class c=Class.forName(e.getActionCommand().substring(1));
        temp=(parts.Component)c.newInstance();
        temp.init(window);
        perfBoard.setDevice(temp);
        Main.mode='C';
        perfBoard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } catch (ClassNotFoundException er) {
          System.out.println("Class Not Found");
        } catch (IllegalAccessException er) {
          System.out.println("Illegal Access");
        } catch (InstantiationException er) {
          System.out.println("Instantiation");
        }
      }
    else if (e.getActionCommand().charAt(0)=='D') {
       DataSheet d=new DataSheet(e.getActionCommand().substring(1,
         e.getActionCommand().length()));
       }
    else if ((e.getActionCommand()=="Save")&&(Globals.current_file_name!=null)) {
      board.saveFile(Globals.current_file_name);
      }
    else if ((e.getActionCommand()=="Save As...")||(e.getActionCommand()=="Save")) {
      fileDialog=new FileDialog(window,"Save File",FileDialog.SAVE);
      fileDialog.setVisible(true);
      if (fileDialog.getFile()!=null) {
        Globals.current_file_name = fileDialog.getFile();
        board.saveFile(Globals.current_file_name);
        window.setTitle("DCE: " + Globals.current_file_name);
        }
      }
    else if (e.getActionCommand()=="Load") {
      fileDialog=new FileDialog(window,"Load File",FileDialog.LOAD);
      fileDialog.setVisible(true);
      if (fileDialog.getFile()!=null) {
        Globals.current_file_name = fileDialog.getFile();
        board.loadFile(fileDialog.getFile());
        window.setTitle("DCE: " + Globals.current_file_name);
        }
        perfBoard.update(perfBoard.getGraphics());
        parts=board.getParts();
        for (i=0;i<parts.size();i++) {
          temp=(parts.Component)parts.elementAt(i);
          if (temp instanceof CKeyboard)
            perfBoard.addKeyListener((KeyListener)temp);
          }
      }
    else if (e.getActionCommand()=="New") {
      keyListener=null;
      Main.mode='E';
      board.clear();
      perfBoard.update(perfBoard.getGraphics());
      Globals.current_file_name = null;
      window.setTitle("DCE");
      }
    else if (e.getActionCommand()=="Exit") {
      window.setVisible(false);
      System.exit(0);
      }
    else if (e.getActionCommand()=="Black") {
      perfBoard.setWireColor(Color.black);
      }
    else if (e.getActionCommand()=="Blue") {
      perfBoard.setWireColor(Color.blue);
      }
    else if (e.getActionCommand()=="cyan") {
      perfBoard.setWireColor(Color.cyan);
      }
    else if (e.getActionCommand()=="Gray") {
      perfBoard.setWireColor(Color.gray);
      }
    else if (e.getActionCommand()=="Green") {
      perfBoard.setWireColor(Color.green);
      }
    else if (e.getActionCommand()=="Magenta") {
      perfBoard.setWireColor(Color.magenta);
      }
    else if (e.getActionCommand()=="Orange") {
      perfBoard.setWireColor(Color.orange);
      }
    else if (e.getActionCommand()=="Pink") {
      perfBoard.setWireColor(Color.pink);
      }
    else if (e.getActionCommand()=="Red") {
      perfBoard.setWireColor(Color.red);
      }
    else if (e.getActionCommand()=="White") {
      perfBoard.setWireColor(Color.white);
      }
    else if (e.getActionCommand()=="Yellow") {
      perfBoard.setWireColor(Color.yellow);
      }
    else if (e.getActionCommand()=="dark Red") {
      perfBoard.setWireColor(Globals.darkRed);
      }
    else if (e.getActionCommand()=="dark Green") {
      perfBoard.setWireColor(Globals.darkGreen);
      }
    else if (e.getActionCommand()=="dark Blue") {
      perfBoard.setWireColor(Globals.darkBlue);
      }
    else if (e.getActionCommand()=="dark Cyan") {
      perfBoard.setWireColor(Globals.darkCyan);
      }
    else if (e.getActionCommand()=="dark Yellow") {
      perfBoard.setWireColor(Globals.darkYellow);
      }
    else if (e.getActionCommand()=="Run") {
      if (Main.mode=='E') {
        Main.cycles=0;
        board.reset();
        if (digiScope!=null) {
          probes=board.getProbes();
          digiScope.reset(probes.size());
          for (i=0;i<probes.size();i++)
            digiScope.addLabel(((Probe)probes.elementAt(i)).getName());
          }
        Main.mode='P';
        perfBoard.update(perfBoard.getGraphics());
        Main.setStartTime();
        Main.mode='R';
        Main.run();
        } else {
        Main.setEndTime();
        Main.mode='E';
        perfBoard.update(perfBoard.getGraphics());
        }
      }
    else if (e.getActionCommand()=="General Help") {
      FileViewer fv=new FileViewer("/docs/dce.doc","General Help",0);
      }
    else if (e.getActionCommand()=="Parts by Category") {
      FileViewer fv=new FileViewer("/docs/parts.doc","Parts by Category",1);
      }
    else if (e.getActionCommand()=="Parts Sorted") {
      FileViewer fv=new FileViewer("/docs/partsord.doc","Parts Sorted",1);
      }
    else if (e.getActionCommand()=="Rom Files") {
      FileViewer fv=new FileViewer("/docs/rom.doc","Rom Files",0);
      }
    else if (e.getActionCommand()=="PLD Files") {
      FileViewer fv=new FileViewer("/docs/pld.doc","PLD Files",1);
      }
    else if (e.getActionCommand()=="First Circuit") {
      FileViewer fv=new FileViewer("/docs/tut1.doc","First Circuit",1);
      }
    else if (e.getActionCommand()=="Second Circuit") {
      FileViewer fv=new FileViewer("/docs/tut2.doc","Second Circuit",1);
      }
    else if (e.getActionCommand()=="Third Circuit") {
      FileViewer fv=new FileViewer("/docs/tut3.doc","Third Circuit",1);
      }
    else if (e.getActionCommand()=="Electricity") {
      FileViewer fv=new FileViewer("/docs/electric.doc","Electricity",1);
      }
    else if (e.getActionCommand()=="Resistors") {
      FileViewer fv=new FileViewer("/docs/resistor.doc","Resistors",1);
      }
    else if (e.getActionCommand()=="capacitors") {
      FileViewer fv=new FileViewer("/docs/capacitor.doc","Capacitors",1);
      }
    else if (e.getActionCommand()=="Inductors") {
      FileViewer fv=new FileViewer("/docs/inductor.doc","Inductors",1);
      }
    else if (e.getActionCommand()=="diodes") {
      FileViewer fv=new FileViewer("/docs/diode.doc","Diodes",1);
      }
    else if (e.getActionCommand()=="Transistors") {
      FileViewer fv=new FileViewer("/docs/transistor.doc","Transistors",1);
      }
    else if (e.getActionCommand()=="Basics") {
      FileViewer fv=new FileViewer("/docs/digbasics.doc","Basics",1);
      }
    else if (e.getActionCommand()=="Building Blocks") {
      FileViewer fv=new FileViewer("/docs/blocks.doc","Building Blocks",1);
      }
    else if (e.getActionCommand()=="Flip Flops") {
      FileViewer fv=new FileViewer("/docs/flipflop.doc","Flip Flops",1);
      }
    else if (e.getActionCommand()=="counters") {
      FileViewer fv=new FileViewer("/docs/counters.doc","Counters",1);
      }
    else if (e.getActionCommand()=="Shift Registers") {
      FileViewer fv=new FileViewer("/docs/shiftreg.doc","Shift Registers",1);
      }
    else if (e.getActionCommand()=="decoders") {
      FileViewer fv=new FileViewer("/docs/decoders.doc","Decoders",1);
      }
    else if (e.getActionCommand()=="Multiplexers") {
      FileViewer fv=new FileViewer("/docs/multiplx.doc","Multiplexers",1);
      }
    else if (e.getActionCommand()=="About") {
      About about=new About();
      }
    else if (e.getActionCommand()=="Status") {
      Status status=new Status(board);
      }
    else if (e.getActionCommand()=="check") {
      Check check=new Check(board);
      }
    else if (e.getActionCommand()=="Scope") {
      if (digiScope==null) {
        digiScope=new DigiScope(board);
        } else {
        digiScope.remove();
        digiScope=null;
        }
      }
    else if (e.getActionCommand()=="Label") {
      TextEntryDialog td=
        new TextEntryDialog(window,"New Label","",
          window.getLocationOnScreen().x,window.getLocationOnScreen().y);
      i=td.getValue();
      if (i==1) {
        perfBoard.setLabel(new Label(td.getText()));
        perfBoard.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Main.mode='L';
        }
      }
    }
  public void windowActivated(WindowEvent e) {
    }
  public void windowClosed(WindowEvent e) {
    }
  public void windowClosing(WindowEvent e) {
    window.setVisible(false);
    System.exit(0);
    }
  public void windowDeactivated(WindowEvent e) {
    }
  public void windowDeiconified(WindowEvent e) {
    }
  public void windowIconified(WindowEvent e) {
    }
  public void windowOpened(WindowEvent e) {
    }
  }
