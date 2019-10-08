package dialogs;

import dce.*;
import parts.*;
import java.awt.*;
import java.awt.event.*;

public class ChipFunctions implements ActionListener {
  int selection;
  PopupMenu chipFunctions;
  MenuItem item;
  PerfBoard pb;
  public ChipFunctions(java.awt.Component win,int x,int y) {
    pb=(PerfBoard)win;
    selection=0;
    chipFunctions=new PopupMenu();
    win.add(chipFunctions);
    item=new MenuItem("Add Wire");
    item.addActionListener(this);
    chipFunctions.add(item);
    item=new MenuItem("Cut Wire");
    item.addActionListener(this);
    chipFunctions.add(item);
    item=new MenuItem("Move Part");
    item.addActionListener(this);
    chipFunctions.add(item);
    item=new MenuItem("Delete Part");
    item.addActionListener(this);
    chipFunctions.add(item);
    item=new MenuItem("Add Probe");
    item.addActionListener(this);
    chipFunctions.add(item);
    item=new MenuItem("Remove Probe");
    item.addActionListener(this);
    chipFunctions.add(item);
    item=new MenuItem("Show Net");
    item.addActionListener(this);
    chipFunctions.add(item);
    item=new MenuItem("Show All");
    item.addActionListener(this);
    chipFunctions.add(item);
    item=new MenuItem("Data Sheet");
    item.addActionListener(this);
    chipFunctions.add(item);
    chipFunctions.show((java.awt.Component)win,x,y);
    }
  public int getSelection() {
    return selection;
    }
  public void actionPerformed(ActionEvent e) {
    MenuItem mi=(MenuItem)e.getSource();
    if ("Add Wire".equals(mi.getLabel())) {
      pb.doSelection(1);
      }
    if ("Cut Wire".equals(mi.getLabel())) {
      pb.doSelection(2);
      }
    if ("Move Part".equals(mi.getLabel())) {
      pb.doSelection(3);
      }
    if ("Delete Part".equals(mi.getLabel())) {
      pb.doSelection(4);
      }
    if ("Add Probe".equals(mi.getLabel())) {
      pb.doSelection(5);
      }
    if ("Remove Probe".equals(mi.getLabel())) {
      pb.doSelection(6);
      }
    if ("Show Net".equals(mi.getLabel())) {
      pb.doSelection(7);
      }
    if ("Show All".equals(mi.getLabel())) {
      pb.doSelection(8);
      }
    if ("Data Sheet".equals(mi.getLabel())) {
      pb.doSelection(9);
      }
    }
}

