package dce;

import dce.*;
import java.awt.*;

public class Main {
  public static char mode;
  public static long cycles;
  public static long startTime;
  public static long endTime;
  static int j,k;
  static Net net;
  static Component i;
  static Board board;
  static View perf;

  public static void main(String argv[]) {
    net=new Net(10);
    board=new Board();
    Main.mode='E';
/*    board.addComponent(i,5,10);
    board.addWire(new Wire(i,1,i,3,Color.red));
    board.addWire(new Wire(i,1,i,2,Color.blue));
    board.addWire(new Wire(i,10,i,6,Color.red)); */
    perf=new View(board);
    board.reset();
    }
  public static void run() {
    net=new Net(board.getNets()+1);
    Dce dce=new Dce(board,net);
    board.reset();
    dce.start();
    }
  public static void setStartTime() {
    startTime=System.currentTimeMillis();
    }
  public static void setEndTime() {
     endTime=System.currentTimeMillis();
     }
  public static double getElapsedTime() {
    double st,et;
    st=(new Long(Main.startTime)).doubleValue();
    et=(new Long(Main.endTime)).doubleValue();
    st=et-st; st/=1000;
    return st;
    }
  public static double getCycles() {
    return (new Long(cycles)).doubleValue();
    }
  public static double getCycleAverage() {
    return getCycles()/getElapsedTime();
    }
  }

