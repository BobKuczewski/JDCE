package dce;

import dce.*;
import dialogs.*;
import shared.*;
import parts.*;
import java.util.*;
import java.awt.*;

public class Dce extends Thread {
  Board board;
  Net net;
  int maxNets;
  public Dce(Board brd,Net nt) {
    board=brd;
    net=nt;
    }
  public void run() {
    int k,down;
    parts.Component i;
    down=10;
    maxNets=board.getNets();
      while (Main.mode=='R') {
        Main.cycles++;
        for (k=0;k<=maxNets;k++) net.net[k]=2;
        board.cycle(net);
        try {
          if (down==0) { sleep(1); down=100;  }
          Thread.yield();
          } catch (InterruptedException e) { }
        }
      }
  }
