package parts.cd4000;
import parts.*;
public class CCD4010 extends Ic {
  public CCD4010() {
    super();
    name=new String("CD4010");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==4 || pn==6 || pn==10 || pn==12 || pn==15) return 1;
      else return 0;
    }
  public int run() {
    pin[2]=pin[3];
    pin[4]=pin[5];
    pin[6]=pin[7];
    pin[10]=pin[9];
    pin[12]=pin[11];
    pin[15]=pin[14];
    return 0;
    }
  }

