package dce;

import dce.*;
import parts.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DigiScope implements ProbeListener,AdjustmentListener {
  Board board;
  Frame window;
  Vector probeData;
  Vector labels;
  Scrollbar hScroll;
  int maxRecord;
  TraceOutput traceOutput;
  int scrollOffset;
  public DigiScope(Board brd) {
    board=brd;
    window=new Frame("DigiScope");
    window.setSize(640,480);
    hScroll=new Scrollbar(Scrollbar.HORIZONTAL,0,8,0,1024);
    hScroll.addAdjustmentListener(this);
    hScroll.setSize(400,20);
    hScroll.setVisible(true);
    window.add(hScroll,"South");
    traceOutput=new TraceOutput();
    traceOutput.setVisible(true);
    window.add(traceOutput,"Center");
    window.setVisible(true);
    board.setProbeListener(this);
    maxRecord=1000;
    scrollOffset=0;
    }
  public void remove() {
    board.setProbeListener(null);
    window.dispose();
    }
  public void reset(int numProbes) {
    int i;
    probeData=new Vector();
    for (i=0;i<numProbes;i++) probeData.addElement(new Vector());
    labels=new Vector();
    }
  public void setSignal(int probe,int value) {
    Vector trace;
    trace=(Vector)probeData.elementAt(probe);
    if (trace.size()<maxRecord) {
      trace.addElement(new Integer(value));
      } else {
      trace.removeElementAt(0);
      trace.addElement(new Integer(value));
      }
    }
  public void updateDisplay() {
    if (probeData.size()>0) 
      traceOutput.update(traceOutput.getGraphics());
    }
  public void addLabel(String text) {
    labels.addElement(text);
    }
  class TraceOutput extends Canvas {
    Image offScreen=null;
    public TraceOutput() {
      super();
      setBackground(Color.black);
      }
    public void paint(Graphics g) {
      while (offScreen==null)
        offScreen=createImage(800,600);
      drawTraces(offScreen.getGraphics());
      g.drawImage(offScreen,0,0,null);
      }
    public void update(Graphics g) {
      paint(g);
      }
    public void drawTraces(Graphics g) {
      int i;
      int start;
      g.clearRect(0,0,800,600);
      if (probeData!=null) {
        start=startPos();
        for (i=0;i<probeData.size();i++) drawTrace(g,i,start);
        } /* end if probeData */
      } /* end drawTraces() */
    public void drawTrace(Graphics g,int trace,int start) {
      int x,y;
      int last=-1;
      int value;
      x=50; y=trace*16+20;
      g.setColor(Color.green);
      Vector probe=(Vector)probeData.elementAt(trace);
      if (trace<labels.size()) 
        g.drawString((String)labels.elementAt(trace),0,y);
      while (x<getSize().width && start<probe.size()) {
        value=((Integer)probe.elementAt(start)).intValue();
        if (value==2) g.setColor(Color.blue); else g.setColor(Color.green);
        if (last==0 && value!=0) g.drawLine(x,y,x,y-10);
        if (last!=0 && value==0) g.drawLine(x,y,x,y-10);
        if (value==0) g.drawLine(x,y,x+16,y);
          else g.drawLine(x,y-10,x+16,y-10);
        x+=16;
        start++;
        last=value;
        }
      }
    public int startPos() {
      Vector trace;
      int ret=0;
      ret=scrollOffset;
      int num=getSize().width/16;
      trace=(Vector)probeData.elementAt(0);
      if (scrollOffset>trace.size()) scrollOffset=trace.size();
      if (Main.mode=='R') {
        if (trace.size()<num) scrollOffset=0;
          else scrollOffset=trace.size()-num;
        }
      return scrollOffset;
      }
    } /* ends traceOutput class */

  public void adjustmentValueChanged(AdjustmentEvent e) {
    if (e.getAdjustable()==hScroll) {
      scrollOffset=e.getValue();
        traceOutput.update(traceOutput.getGraphics());
      }
    }

  }
