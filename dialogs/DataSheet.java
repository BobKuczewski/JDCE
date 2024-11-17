package dialogs;

import shared.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import dce.*;

public class DataSheet implements ActionListener,WindowListener {
  Frame window;
  Pinout pinout;
  Button exitButton;
  Button flipButton;
  Button rotateButton;
  TextArea text;
  public DataSheet(String name) {
    int ret=0;
    window=new Frame(name);
    window.setSize(790,480);
    window.setLayout(null);
    window.addWindowListener(this);
    pinout=new Pinout(name,14);
    pinout.setLocation(1,45);
    pinout.setVisible(true);
    window.add(pinout);
    text=new TextArea();
    text.setFont(Globals.courier);
    text.setSize(570,450);
    text.setLocation(201,25);
    text.setVisible(true);

    exitButton=new Button("Close");
    exitButton.setSize(50,20);
    exitButton.setLocation(1,25);
    exitButton.setVisible(true);
    exitButton.addActionListener(this);

    flipButton=new Button("Flip");
    flipButton.setSize(50,20);
    flipButton.setLocation(50,25);
    flipButton.setVisible(true);
    flipButton.addActionListener(this);

    /*
    rotateButton=new Button("Rotate");
    rotateButton.setSize(50,20);
    rotateButton.setLocation(100,25);
    rotateButton.setVisible(true);
    rotateButton.addActionListener(this);
    */

    ret=readFile(name);
    window.add(text);
    window.add(exitButton);
    window.add(flipButton);
    // window.add(rotateButton);
    window.setVisible(true);
    if (ret==0) {
      window.dispose();
      }
    }

  public int readFile(String name) {
    MyBufferedInputStream file;
    StringBuffer   buffer=new StringBuffer();
    StringBuffer   buffer2=new StringBuffer();
    char flag;
    int ret=0,i,pns;
    flag=' ';
    try {
      file=new MyBufferedInputStream(getClass().getResourceAsStream(name));
      while (flag==' ') {
        buffer.setLength(0);
        buffer.append(file.readLine());
        if (buffer.length()>6) 
          if (".NAME=".equals(buffer.toString().substring(0,6))) {
            pinout.setName(buffer.toString().substring(6));
            text.append(buffer.toString().substring(6));
            text.append("\n");
            window.setTitle(buffer.toString().substring(6));
            flag='*';
          }
        if (file.ready()==false) flag='E';
        }
      if (flag=='E') file.close();
        else {
          buffer.setLength(0);
          buffer.append(file.readLine());
          buffer2.setLength(0);
          buffer2.append(buffer.toString().substring(6,buffer.length()));
          pns=(new Integer(buffer2.toString())).intValue();
          pinout.setPins(pns);
          for (i=0;i<pns;i++) {
            buffer.setLength(0);
            buffer.append(file.readLine());
            pinout.addLabel(buffer.toString());
            }
          while (flag=='*') {
            ret=1;
            buffer.setLength(0);
            buffer.append(file.readLine());
            if (buffer.length()>1) {
              if (buffer.charAt(0)=='.') flag=' ';
                else {
                text.append(buffer.toString());
                text.append("\n");
                }
              } else text.append("\n");
            if (file.ready()==false) flag='E';
            }
          file.close();
          }
      } catch (IOException er) {
      }
    return ret;
    }
  public void actionPerformed(ActionEvent e) {
    if ("Close".equals(e.getActionCommand())) {
      window.dispose();
      }
    if ("Flip".equals(e.getActionCommand())) {
      pinout.flip_diagram();
      }
    //if ("Rotate".equals(e.getActionCommand())) {
    //  pinout.rotate_diagram();
    //  }
    }
  public void windowActivated(WindowEvent e) {
    }
  public void windowClosed(WindowEvent e) {
    }
  public void windowClosing(WindowEvent e) {
    window.removeWindowListener(this);
    window.dispose();
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

