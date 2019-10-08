package dialogs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import dce.*;
import shared.*;

public class Status implements ActionListener,WindowListener {
  Frame window;
  Button close;
  StringBuffer buffer=new StringBuffer();
  Vector parts;
  public Status (Board board) {
    window=new Frame("Circuit Status");
    window.setSize(200,200);
    window.setLayout(new GridLayout(9,0));
    window.addWindowListener(this);
    buffer.setLength(0);
    buffer.append("Components :");
    parts=board.getParts();
    buffer.append(parts.size());
    window.add(new java.awt.Label(buffer.toString()));
    buffer.setLength(0);
    buffer.append("Wires :");
    parts=board.getWires();
    buffer.append(parts.size());
    window.add(new java.awt.Label(buffer.toString()));
    buffer.setLength(0);
    buffer.append("Nets :");
    buffer.append(board.getNets());
    window.add(new java.awt.Label(buffer.toString()));
    window.add(new java.awt.Label("Last Run:"));
    buffer.setLength(0);
    buffer.append("Cycles Run :");
    buffer.append(Main.getCycles());
    window.add(new java.awt.Label(buffer.toString()));
    buffer.setLength(0);
    buffer.append("Elapsed Time :");
    buffer.append(Main.getElapsedTime());
    window.add(new java.awt.Label(buffer.toString()));
    buffer.setLength(0);
    buffer.append("Cycles per sec. :");
    buffer.append(Main.getCycleAverage());
    window.add(new java.awt.Label(buffer.toString()));
    buffer.setLength(0);
    buffer.append("Free Memory :");
    buffer.append(Runtime.getRuntime().freeMemory());
    window.add(new java.awt.Label(buffer.toString()));
    
    close=new Button("Close");
    close.setVisible(true);
    close.addActionListener(this);
    window.add(close);
    window.setVisible(true);
    }
  public void actionPerformed(ActionEvent e) {
    if ("Close".equals(e.getActionCommand())) window.setVisible(false);
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

