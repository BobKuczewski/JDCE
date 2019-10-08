package dce;

import parts.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import dce.*;

public class Check implements ActionListener,WindowListener {
  Frame window;
  Button exitButton;
  TextArea text;
  Board board;
  StringBuffer buffer;
  public Check(Board brd) {
    int ret=0;
    board=brd;
    buffer=new StringBuffer();
    window=new Frame("Circuit Checker");
    window.setSize(640,480);
    window.addWindowListener(this);
    text=new TextArea();
    text.append("Checking Circuit:\n");
    text.setVisible(true);
    checkWires();
    checkNets();
    exitButton=new Button("Close");
    exitButton.setSize(50,20);
    exitButton.setVisible(true);
    exitButton.addActionListener(this);
    window.add(exitButton,"North");
    window.add(text,"Center");
    window.setVisible(true);
    }
  public void checkWires() {
    int i;
    Vector wires;
    Wire wire;
    wires=board.getWires();
    for (i=0;i<wires.size();i++) {
      wire=(Wire)wires.elementAt(i);
      if (wire.startIO==1 && wire.endIO==1) {
        buffer.setLength(0);
        buffer.append("ERROR - Wire connects two outputs: ");
        buffer.append(wire.startChip.partName());
        buffer.append(" : ");
        buffer.append(wire.startPin);
        buffer.append(" - ");
        buffer.append(wire.endChip.partName());
        buffer.append(" : ");
        buffer.append(wire.endPin);
        text.append(buffer.toString());
        text.append("\n");
        }
      }
    }
  public void checkNets() {
    int i,j;
    Vector wires;
    Wire wire;
    int nets;
    parts.Component part=null;
    int pin=0;
    int ins,outs,tris,ios;
    wires=board.getWires();
    nets=board.getNets();
    for (i=1;i<=nets;i++) {
      ins=0; outs=0; tris=0; ios=0;
      for (j=0;j<wires.size();j++) {
        wire=(Wire)wires.elementAt(j);
        if (wire.net==i) {
          part=wire.startChip;
          pin=wire.startPin;
          if (wire.startIO==0) ins++;
          else if (wire.startIO==1) outs++;
          else if (wire.startIO==2) tris++;
          else if (wire.startIO>2) ios++;
          if (wire.endIO==0) ins++;
          else if (wire.endIO==1) outs++;
          else if (wire.endIO==2) tris++;
          else if (wire.endIO>2) ios++;
          }
        }
      if (outs>1) {
        buffer.setLength(0);
        buffer.append("ERROR - Net connected to more than 1 normal output: ");
        buffer.append(part.partName());
        buffer.append(" : ");
        buffer.append(pin);
        text.append(buffer.toString());
        text.append("\n");
        }
      if (outs>0 && tris>0) {
        buffer.setLength(0);
        buffer.append("ERROR - Net mixes normal and tri-state outputs: ");
        buffer.append(part.partName());
        buffer.append(" : ");
        buffer.append(pin);
        text.append(buffer.toString());
        text.append("\n");
        }
      if (ins==0 && ios==0) {
        buffer.setLength(0);
        buffer.append("ERROR - Net connects to no inputs: ");
        buffer.append(part.partName()+" : ");
        buffer.append(pin);
        text.append(buffer.toString());
        text.append("\n");
        }
      }
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

