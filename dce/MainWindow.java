package dce;

import dce.*;
import java.awt.*;

public class MainWindow {
  Frame window;
  Scrollbar hScroll;
  Scrollbar vScroll;
  MyCanvas myCanvas;
  class MyCanvas extends Canvas {
    public MyCanvas() {
      setSize(200,200);
      setBackground(Color.blue);
      setVisible(true);
      }
    }
  public MainWindow() {
    myCanvas=new MyCanvas();
    window=new Frame();
    window.setLayout(new BorderLayout());
    window.setSize(640,480);
    hScroll=new Scrollbar(Scrollbar.HORIZONTAL);
    hScroll.setSize(200,20);
    hScroll.setVisible(true);
    window.add(hScroll,"South");
    vScroll=new Scrollbar(Scrollbar.VERTICAL);
    vScroll.setSize(20,200);
    vScroll.setVisible(true);
    window.add(vScroll,"East");
    window.add(myCanvas,"Center");
    window.setVisible(true);
    }
  }
