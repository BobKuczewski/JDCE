package dialogs;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class About implements ActionListener,WindowListener {
  Frame window;
  Button exitButton;
  java.awt.Label title,version,by,thanks1,thanks2;
  public About() {
    int ret=0;
    window=new Frame("About");
    window.setSize(250,170);
    window.setLayout(new FlowLayout());
    window.addWindowListener(this);
    title=new java.awt.Label("DCE - Digital Circuit Emulator");
    window.add(title);
    version=new java.awt.Label("Version 2.0.0    ");
    window.add(version);
    by=new java.awt.Label("By Michael H. Riley");
    window.add(by);
    thanks1=new java.awt.Label("Special thanks to Stephen Holloway");
    window.add(thanks1);
    thanks2=new java.awt.Label("for all of his Java help.");
    window.add(thanks2);
    exitButton=new Button("Close");
    exitButton.setSize(50,20);
    exitButton.setVisible(true);
    exitButton.addActionListener(this);
    window.add(exitButton);
    window.setVisible(true);
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

