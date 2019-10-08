package dialogs;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import shared.*;

public class FileViewer implements ActionListener,WindowListener {
  Frame window;
  Button exitButton;
  TextArea text;
  public FileViewer(String file,String title,int flag) {
    int ret=0;
    window=new Frame(title);
    window.setSize(640,480);
    window.addWindowListener(this);
    text=new TextArea();
    if (flag==1) text.setFont(Globals.courier);
    text.append("\n");
    text.setVisible(true);
    exitButton=new Button("Close");
    exitButton.setSize(50,20);
    exitButton.setVisible(true);
    exitButton.addActionListener(this);
    ret=readFile(file);
    window.add(exitButton,"North");
    window.add(text,"Center");
    window.setVisible(true);
//    if (ret==0) window=null;
    }
  public int readFile(String name) {
    BufferedReader file;
    StringBuffer   buffer=new StringBuffer();
    char flag;
    int ret=0,i,pns;
    flag=' ';
    try {
      MyBufferedInputStream file2=
        new MyBufferedInputStream(getClass().getResourceAsStream(name));
      if (file2!=null) {
        while (file2.available()>0) {
          buffer.setLength(0);
          buffer.append(file2.readLine());
          if (buffer.length()==0) buffer.append(" ");
          text.append(buffer.toString());
          text.append("\n");
          }
        file2.close();
        } else {
        System.out.println("Not Found");
        }
      } catch (IOException er) {
        ret=0;
      }
    return ret;
    }
  public void actionPerformed(ActionEvent e) {
    if ("Close".equals(e.getActionCommand())) {
      window.dispose();
      }
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

