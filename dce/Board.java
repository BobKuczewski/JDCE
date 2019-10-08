package dce;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import dce.*;
import parts.*;

public class Board implements ActionListener {
  Vector parts;
  Vector wires;
  Vector probes;
  Vector labels;
  int    maxNet;
  ModelListener viewer;
  ProbeListener digiScope=null;
  public Board() {
    parts.Component i;
    parts=new Vector();
    wires=new Vector();
    probes=new Vector();
    labels=new Vector();
    maxNet=0;
    } /* end Board() */
  public void setViewer(ModelListener g) {
    viewer=g;
    }
  public void setProbeListener(ProbeListener l) {
    digiScope=l;
    }
  public void clear() {
    parts=null;
    wires=null;
    probes=null;
    parts=new Vector();
    wires=new Vector();
    probes=new Vector();
    labels=new Vector();
    maxNet=0;
    } /* end Board() */
  public void loadFile(String fileName) {
    int i,j;
    int x,y;
    int pin1,pin2;
    int numParts,numWires,numNets,numProbes,numLabels;
    parts.Component part1,part2;
    Wire wire;
    Label label;
    BufferedReader file;
    StringBuffer buffer=new StringBuffer();
    StringBuffer buffer2=new StringBuffer();
    try {
      clear();
      file=new BufferedReader(new FileReader(fileName));
      buffer.setLength(0);
      buffer.append(file.readLine());
      buffer2.setLength(0);
      j=0;
      while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
      numParts=(new Integer(buffer2.toString())).intValue();
      j++;
      buffer2.setLength(0);
      while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
      numWires=(new Integer(buffer2.toString())).intValue();
      j++;
      buffer2.setLength(0);
      while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
      numNets=(new Integer(buffer2.toString())).intValue();
      j++;
      buffer2.setLength(0);
      while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
      numProbes=(new Integer(buffer2.toString())).intValue();
      j++;
      buffer2.setLength(0);
      while (j<buffer.length()) buffer2.append(buffer.charAt(j++));
      numLabels=(new Integer(buffer2.toString())).intValue();
      for (i=0;i<numParts;i++) {
        buffer.setLength(0);
        buffer.append(file.readLine());
        buffer2.setLength(0);
        j=0;
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        Class c=Class.forName(buffer2.toString());
        part1=(parts.Component)c.newInstance();
        j++;
        buffer2.setLength(0);
        while (j<buffer.length()) buffer2.append(buffer.charAt(j++));
        part1.load(buffer2.toString());
        parts.addElement(part1);
        }
      for (i=0;i<numWires;i++) {
        buffer.setLength(0);
        buffer.append(file.readLine());
        buffer2.setLength(0);
        j=0;
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        x=(new Integer(buffer2.toString())).intValue();
        part1=(parts.Component)parts.elementAt(x);
        j++;
        buffer2.setLength(0);
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        pin1=(new Integer(buffer2.toString())).intValue();
        j++;
        buffer2.setLength(0);
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        x=(new Integer(buffer2.toString())).intValue();
        part2=(parts.Component)parts.elementAt(x);
        j++;
        buffer2.setLength(0);
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        pin2=(new Integer(buffer2.toString())).intValue();
        wire=new Wire(part1,pin1,part2,pin2,Color.white);
        j++;
        buffer2.setLength(0);
        while (j<buffer.length()) buffer2.append(buffer.charAt(j++));
        y=(new Integer(buffer2.toString())).intValue();
        wire.setIntColor(y);
        wires.addElement(wire);
        }
      for (i=0;i<numProbes;i++) {
        buffer.setLength(0);
        buffer.append(file.readLine());
        buffer2.setLength(0);
        j=0;
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        x=(new Integer(buffer2.toString())).intValue();
        part1=(parts.Component)parts.elementAt(x);
        j++;
        buffer2.setLength(0);
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        x=(new Integer(buffer2.toString())).intValue();
        pin1=(new Integer(buffer2.toString())).intValue();
        j++;
        buffer2.setLength(0);
        while (j<buffer.length()) buffer2.append(buffer.charAt(j++));
        addProbe(new Probe(part1,pin1,buffer2.toString()));
        }
      for (i=0;i<numLabels;i++) {
        buffer.setLength(0);
        buffer.append(file.readLine());
        j=0;
        buffer2.setLength(0);
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        x=(new Integer(buffer2.toString())).intValue();
        j++;
        buffer2.setLength(0);
        while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
        y=(new Integer(buffer2.toString())).intValue();
        j++;
        buffer2.setLength(0);
        while (j<buffer.length()) buffer2.append(buffer.charAt(j++));
        label=new Label(buffer2.toString());
        label.setLocation(x,y);
        labels.addElement(label);
        }
      renet();
      file.close();
      } catch (IOException er) {
          System.out.println(er.toString());
      } catch (ClassNotFoundException er) {
          System.out.println(er.toString());
      } catch (IllegalAccessException er) {
          System.out.println(er.toString());
      } catch (InstantiationException er) {
          System.out.println(er.toString());
      }
    }
  public void saveFile(String fileName) {
    int i,j;
    Wire wire;
    Probe probe;
    FileWriter file;
    Label label;
    StringBuffer buffer=new StringBuffer();
    try {
      file=new FileWriter(fileName);
      buffer.setLength(0);
      buffer.append(parts.size());
      buffer.append(" ");
      buffer.append(wires.size());
      buffer.append(" ");
      buffer.append(maxNet);
      buffer.append(" ");
      buffer.append(probes.size());
      buffer.append(" ");
      buffer.append(labels.size());
      buffer.append("\n");
      file.write(buffer.toString());
      for (i=0;i<parts.size();i++) {
        file.write(((parts.Component)parts.elementAt(i)).save());
        }
      for (i=0;i<wires.size();i++) {
        wire=(Wire)wires.elementAt(i);
        buffer.setLength(0);
        buffer.append(chipNumber(wire.startChip));
        buffer.append(" ");
        buffer.append(wire.startPin);
        buffer.append(" ");
        buffer.append(chipNumber(wire.endChip));
        buffer.append(" ");
        buffer.append(wire.endPin);
        buffer.append(" ");
        buffer.append(wire.colorToInt());
        buffer.append("\n");
        file.write(buffer.toString());
        }
      for (i=0;i<probes.size();i++) {
        probe=(Probe)probes.elementAt(i);
        buffer.setLength(0);
        buffer.append(chipNumber(probe.part));
        buffer.append(" ");
        buffer.append(probe.pin);
        buffer.append(" ");
        buffer.append(probe.name);
        buffer.append("\n");
        file.write(buffer.toString());
        }
      for (i=0;i<labels.size();i++) {
        label=(Label)labels.elementAt(i);
        buffer.setLength(0);
        buffer.append(label.getX());
        buffer.append(" ");
        buffer.append(label.getY());
        buffer.append(" ");
        buffer.append(label.getText());
        buffer.append("\n");
        file.write(buffer.toString());
        }
      file.close();
      } catch (IOException er) {
      }
    }
  public int chipNumber(parts.Component part) {
    int i;
    int ret=-1;
    for (i=0;i<parts.size();i++)
      if ((parts.Component)parts.elementAt(i)==part) ret=i;
    return ret;
    }
  public void addComponent(parts.Component c,int x,int y) {
    c.setLocation(x,y);
    parts.addElement(c);
    }
  public void addLabel(Label label,int x,int y) {
    label.setLocation(x,y);
    labels.addElement(label);
    }
  public Vector getLabels() {
    return labels;
    }
  public void removeComponent(parts.Component c) {
    int i;
    char flag;
    Wire wire;
    Probe probe;
    flag='Y';
    while (flag=='Y') {
      flag='N';
      for (i=0;i<probes.size();i++) {
        probe=(Probe)probes.elementAt(i);
        if (probe.part==c) {
          probes.removeElementAt(i);
          flag='Y';
          }
        }
      }
    flag='Y';
    while (flag=='Y') {
      flag='N';
      for (i=0;i<wires.size();i++) {
        wire=(Wire)wires.elementAt(i);
        if (wire.startChip==c || wire.endChip==c) {
          wires.removeElementAt(i);
          flag='Y';
          }
        }
      }
    wire=null;
    probe=null;
    parts.removeElement(c);
    renet();
    }
  public void renet() {
    int i,j;
    int n1,n2;
    Wire w1,w2;
    if (wires.size()>0) {
      maxNet=1;
      w1=(Wire)wires.elementAt(0);
      w1.setNet(1);
      for (j=1;j<wires.size();j++) {
        w1=(Wire)wires.elementAt(j);
        n1=-1; n2=-1;
        for (i=0;i<j;i++) {
          w2=(Wire)wires.elementAt(i);
          if ((w1.startChip==w2.startChip && w1.startPin==w2.startPin) ||
              (w1.startChip==w2.endChip && w1.startPin==w2.endPin))
            n1=w2.getNet();
          if ((w1.endChip==w2.startChip && w1.endPin==w2.startPin) ||
              (w1.endChip==w2.endChip && w1.endPin==w2.endPin))
            n2=w2.getNet();
          }
        if (n1==-1 && n2==-1) {
          w1.setNet(++maxNet);
          }
        else if (n1>=0 && n2==-1) {
          w1.setNet(n1);
          }
        else if (n2>=0 && n1==-1) {
          w1.setNet(n2);
          }
        else if (n2==n1) {
          w1.setNet(n2);
          }
        else {
          if (n2<n1) { i=n1; n1=n2; n2=i; }
          w1.setNet(n1);
          for (i=0;i<j;i++) {
            w2=(Wire)wires.elementAt(i);
            if (w2.getNet()==n2) w2.setNet(n1);
            }
          }
        }
      } else maxNet=0;
    }
  public int getNets() {
    return maxNet;
    }
  public void addWire(Wire w) {
    wires.addElement(w);
    renet();
    }
  public void removeWire(Wire w) {
    Wire w2;
    int i;
    for (i=0;i<wires.size();i++) {
      w2=(Wire)wires.elementAt(i);
      if (w2.startChip==w.startChip && w2.startPin==w.startPin &&
          w2.endChip==w.endChip && w2.endPin==w.endPin)
        wires.removeElementAt(i);
      if (w2.startChip==w.endChip && w2.startPin==w.endPin &&
          w2.endChip==w.startChip && w2.endPin==w.startPin)
        wires.removeElementAt(i);
      }
    renet();
    }
  public void addProbe(Probe probe) {
    probes.addElement(probe);
    }
  public void removeProbe(parts.Component part,int pin) {
    Probe probe;
    int i;
    for (i=0;i<probes.size();i++) {
      probe=(Probe)probes.elementAt(i);
      if (probe.getPart()==part && probe.getPin()==pin)
        probes.removeElementAt(i);
      }
    }
  public int findNet(parts.Component part,int pin) {
    int net=-1;
    int i;
    Wire wire;
    for (i=0;i<wires.size();i++) {
      wire=(Wire)wires.elementAt(i);
      if (wire.startChip==part && wire.startPin==pin) net=wire.net;
      if (wire.endChip==part && wire.endPin==pin) net=wire.net;
      }
 
    return net;
    }
  public Vector getProbes() {
    return probes;
    }
  public Vector getParts() {
    return parts;
    }

  public Vector getWires() {
    return wires;
    }

  public parts.Component getComponent(int x,int y) {
    int i;
    parts.Component ret,c;
    ret=null;
    for (i=0;i<parts.size();i++) {
      c=(parts.Component)parts.elementAt(i);
      if (c.findPin(x,y)!=0) ret=c;
      }
    return ret;
    }
  public void cycle(Net net) {
    resolverOut(net);
    resolverIn(net);
    resolverRun();
    }

  public void resolverOut(Net net) {
    int i;
    Wire wire;
    int ws,we;
    for (i=0;i<wires.size();i++) {
      wire=(Wire)wires.elementAt(i);
      ws=wire.startIO; we=wire.endIO;
      if (ws>127)
        ws=wire.startChip.pinOut(wire.startPin) & 0x7f;
      if (we>127)
        we=wire.endChip.pinOut(wire.endPin) & 0x7f;
      if (ws==1 || ws==2) {
        if (wire.startChip.pin[wire.startPin]==1 && net.net[wire.net]==2)
          net.net[wire.net]=1;
        if (wire.startChip.pin[wire.startPin]==0 && net.net[wire.net]==2)
          net.net[wire.net]=0;
        if (wire.startChip.pin[wire.startPin]==1 && net.net[wire.net]==0)
          net.net[wire.net]=1;
        } /* end if ws==1 */
      if (we==1 || ws==2) {
        if (wire.endChip.pin[wire.endPin]==1 && net.net[wire.net]==2)
          net.net[wire.net]=1;
        if (wire.endChip.pin[wire.endPin]==0 && net.net[wire.net]==2)
          net.net[wire.net]=0;
        if (wire.endChip.pin[wire.endPin]==1 && net.net[wire.net]==0)
          net.net[wire.net]=1;
        } /* end if we==1 */
      } /* end for */
    } /* end resolverOut() */

  public void resolverIn(Net net) {
    int i;
    Wire wire;
    int ws,we;
    for (i=0;i<wires.size();i++) {
      wire=(Wire)wires.elementAt(i);
      ws=wire.startIO; we=wire.endIO;
      if (ws>127)
        ws=wire.startChip.pinOut(wire.startPin) & 0x7f;
      if (we>127)
        we=wire.endChip.pinOut(wire.endPin) & 0x7f;
      if (ws==0)
        wire.startChip.pin[wire.startPin]=net.net[wire.net];
      if (we==0)
        wire.endChip.pin[wire.endPin]=net.net[wire.net];
      } /* end for */
    } /* end resolverIn() */

  public void resolverRun() {
    int i,result;
    parts.Component part;
    Probe probe;
    for (i=0;i<parts.size();i++) {
      result=((parts.Component)parts.elementAt(i)).run();
      if (result !=0 ) viewer.componentUpdated((parts.Component)parts.elementAt(i));
      }
    if (digiScope!=null) {
      for (i=0;i<probes.size();i++) {
        probe=(Probe)probes.elementAt(i);
        digiScope.setSignal(i,probe.part.pin[probe.pin]);
        }
      digiScope.updateDisplay();
      }

    } /* end resolverRun() */

  public void reset() {
    int i;
    for (i=0;i<parts.size();i++) {
      ((parts.Component)parts.elementAt(i)).reset();
      }
    } /* end reset() */


  public void actionPerformed(ActionEvent e) {
    System.out.println(e.getActionCommand());
    }
  }
