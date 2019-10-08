package parts.cd4000;
import parts.*;
public class CCD4027 extends Ic {
  public CCD4027() {
    super();
    name=new String("CD4027");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==1 || pn==2 || pn==14 || pn==15) return 1;
      else return 0;
    }
  public int run() {
    pin[24]=f_inv(pin[4]);
    pin[27]=f_inv(pin[7]);
    pin[29]=f_inv(pin[9]);
    pin[32]=f_inv(pin[12]);
    pin[23]=f_inv(pin[3]);
    pin[33]=f_inv(pin[13]);
    jkff(27,24,23,6,5,1,2,21);
    jkff(29,32,33,10,11,15,14,22);
    return 0;
    }
  }

