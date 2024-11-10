package dce;

import dce.*;
import parts.*;
import parts.input.*;
import dialogs.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class PerfBoard extends Canvas implements MouseListener,ModelListener {
    parts.Component start,end;
    int       startPin,endPin,k;
    parts.Component part,device;
    parts.Component moment;
    Wire wire;
    Probe probe;
    Label label;
    int offsetX,offsetY;
    Board board;
    Color wireColor;
    int visibleNet;
    Image offScreen;
    Frame window;
    public PerfBoard(Board brd) {
      board=brd;
      visibleNet=0;
      board.setViewer(this);
      setBackground(new Color(189,183,107));
      setVisible(true);
      addMouseListener(this);
      }
    public void setWindow(Frame win) {
      window=win;
      }
    public void setBGColor(Color bgColor) {
      setBackground(bgColor);
      }
    public void setDevice(parts.Component dev) {
      device=dev;
      }
    public void setLabel(Label lbl) {
      label=lbl;
      }
    public void setOffsetX(int x) {
      offsetX=x;
      }
    public void setOffsetY(int y) {
      offsetY=y;
      }
    public void setWireColor(Color clr) {
      wireColor=clr;
      }
    public void componentUpdated(parts.Component part) {
      part.drawTop(getGraphics(),offsetX,offsetY,getSize().height);
      }
    public void drawPart(parts.Component part) {
      part.drawTop(getGraphics(),offsetX,offsetY,getSize().height);
      }
    public void doSelection(int i) {
      int x=getLocationOnScreen().x;
      int y=getLocationOnScreen().y;
      if (i==1) {
        start=part; startPin=k;
        Main.mode='W';
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
      if (i==2) {
        start=part; startPin=k;
        Main.mode='w';
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }
      else if (i==3) {
        start=part;
        Main.mode='M';
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
      else if (i==4) {
        if (part instanceof CKeyboard) removeKeyListener((KeyListener)part);
        board.removeComponent(part);
        update(getGraphics());
        }
      else if (i==5) {
        TextEntryDialog td=
          new TextEntryDialog(window,"New Probe","",x,y);
        int j=td.getValue();
        if (j==1) {
          probe=new Probe(part,k,td.getText());
          board.addProbe(probe);
          probe.draw(getGraphics(),0,0);
          }
        Main.mode='E';
        }
      else if (i==6) {
        board.removeProbe(part,k);
        update(getGraphics());
        Main.mode='E';
        }
      else if (i==7) {
        visibleNet=board.findNet(part,k);
        update(getGraphics());
        }
      else if (i==8) {
        visibleNet=0;
        update(getGraphics());
        }
      else if (i==9) {
        int j;
        StringBuffer buffer=new StringBuffer();
        buffer.setLength(0);
        buffer.append(part.toString());
        j=0;
        while (j<buffer.length() && buffer.charAt(j)!='@') j++;
        buffer.setLength(j);
        for (j=0;j<buffer.length();j++)
          buffer.setCharAt(j,Character.toUpperCase(buffer.charAt(j)));
        DataSheet d=new DataSheet(buffer.toString().substring(1,
           buffer.length()));
 
        }
      }
    public void update(Graphics gr) {
      paint(gr);
      }
    public void paint(Graphics g) {
      drawCircuit();
      g.drawImage(offScreen,0,0,null);
      }
    public void drawCircuit() {
      int i,x,y;
      Vector parts;
      parts.Component c;
      Wire  w;
      Probe p;
      Label l;
      while (offScreen==null) offScreen=createImage(800,600);
      Graphics g=offScreen.getGraphics();
      g.clearRect(0,0,800,600);
      if (Main.mode!='R' && Main.mode!='P') {
        for (y=0;y<this.getSize().height;y+=8)
          for (x=0;x<this.getSize().width;x+=8) {
            g.drawLine(x-1,y,x+1,y);
            g.drawLine(x,y-1,x,y+1);
            }
        parts=board.getParts();
        for (i=0;i<parts.size();i++) {
          c=(parts.Component)parts.elementAt(i);
          c.drawBottom(g,offsetX,offsetY);
          }
        parts=board.getWires();
        for (i=0;i<parts.size();i++) {
          w=(Wire)parts.elementAt(i);
          w.draw(g,offsetX,offsetY,visibleNet);
          }
        parts=board.getLabels();
        for (i=0;i<parts.size();i++) {
          l=(Label)parts.elementAt(i);
          l.drawBottom(g,offsetX,offsetY);
          }
        parts=board.getProbes();
        for (i=0;i<parts.size();i++) {
          p=(Probe)parts.elementAt(i);
          p.draw(g,offsetX,offsetY);
          }
        } else {
        for (y=this.getSize().height;y>0;y-=8)
          for (x=0;x<this.getSize().width;x+=8) {
            g.drawLine(x-1,y,x+1,y);
            g.drawLine(x,y-1,x,y+1);
            }
        parts=board.getParts();
        for (i=0;i<parts.size();i++) {
          c=(parts.Component)parts.elementAt(i);
          c.drawTop(g,offsetX,offsetY,getSize().height);
          }
        parts=board.getLabels();
        for (i=0;i<parts.size();i++) {
          l=(Label)parts.elementAt(i);
          l.drawTop(g,offsetX,offsetY,getSize().height);
          }
        } /* end else */
      } /* end paint() */
    public void mouseClicked(MouseEvent m) {
      }
    public void mouseEntered(MouseEvent m) {
      }
    public void mouseExited(MouseEvent m) {
      }
    public void mousePressed(MouseEvent m) {
      int i=0,j=0;
      int hx,hy;
      ChipFunctions chipMenu;
      hx=(m.getX()+4)/8;
      if (Main.mode=='R') hy=(getSize().height-(m.getY()-4))/8;
        else hy=(m.getY()+4)/8;
      if (Main.mode=='R') {
        part=board.getComponent(hx+offsetX,hy+offsetY);
        if (part!=null) {
          i=part.pressed(hx,hy);
          if (i!=0) part.drawTop(getGraphics(),offsetX,offsetY,
                                 getSize().height);
          if (i==2) moment=part;
          }
        }
      else if (Main.mode=='C') {
        board.addComponent(device,hx+offsetX,hy+offsetY);
        if (device instanceof CKeyboard)
          addKeyListener((KeyListener)device);
        paint(getGraphics());
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        Main.mode='E';
        }
      else if (Main.mode=='L') {
        board.addLabel(label,hx+offsetX,hy+offsetY);
        paint(getGraphics());
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        Main.mode='E';
        }
      else if (Main.mode=='E') {
        part=board.getComponent(hx+offsetX,hy+offsetY);
        if (part!=null) {
          k=part.findPin(hx+offsetX,hy+offsetY);
          chipMenu=new ChipFunctions(this,hx*8,hy*8);
          }
        }
      else if (Main.mode=='M') {
        start.setLocation(hx+offsetX,hy+offsetY);
        Main.mode='E';
        update(getGraphics());
        }
      else if (Main.mode=='W') {
        part=board.getComponent(hx+offsetX,hy+offsetY);
        if (part!=null) {
          k=part.findPin(hx+offsetX,hy+offsetY);
          end=part; endPin=k;
          wire=new Wire(start,startPin,end,endPin,wireColor);
          board.addWire(wire);
          wire.draw(getGraphics(),offsetX,offsetY,0);
          }
        Main.mode='E';
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } /* end if mode */
      else if (Main.mode=='w') {
        part=board.getComponent(hx+offsetX,hy+offsetY);
        if (part!=null) {
          k=part.findPin(hx+offsetX,hy+offsetY);
          end=part; endPin=k;
          wire=new Wire(start,startPin,end,endPin,wireColor);
          board.removeWire(wire);
          wire=null;
          update(getGraphics());
          }
        Main.mode='E';
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        } /* end if mode */
      }
    public void mouseReleased(MouseEvent m) {
      if (moment!=null) {
        moment.released(0,0);
        part.drawTop(getGraphics(),offsetX,offsetY, getSize().height);
        }
      moment=null;
      }
    } /* End class perfBoard */
