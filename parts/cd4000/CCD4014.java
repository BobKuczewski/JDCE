package parts.cd4000;
import parts.*;
public class CCD4014 extends Ic {
  public CCD4014() {
    super();
    name=new String("CD4014");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==3 || pn==12) return 1;
      else return 0;
    }
  public int run() {
    if (pin[9]!=0) {
      pin[20]=f_binin(7,6,5,4,13,14,15,1);
      } else if (pin[10]!=0 && pin[30]==0) {
      pin[20]+=pin[20];
      if (pin[11]!=0) pin[20]++;
      }
    disperse(20,31,32,33,34,35,2,12,3);
    pin[30]=pin[10];
    return 0;
    }
  }

