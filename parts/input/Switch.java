package parts.input;
import java.awt.*;
import dce.*;
import parts.*;
public class Switch extends parts.Component {
  int switchOn;
  public Switch() {
    switchOn=0;
    }
  public int pressed(int hx,int hy) {
    if (switchOn!=0) switchOn=0; else switchOn=1;
    return 1;
    }
  public void reset() {
    switchOn=0;
    }
  }

