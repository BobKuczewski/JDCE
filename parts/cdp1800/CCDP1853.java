package parts.cdp1800;
import parts.*;
public class CCDP1853 extends Ic {
  public CCDP1853() {
    super();
    name=new String("CDP1853");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if ((pn>=4 && pn<=7) || (pn>=9 && pn<=12)) return 1; else return 0;
    }
  public int run() {
    if (pin[13]==0) {
      pin[5]=0; pin[5]=0; pin[6]=0; pin[7]=0;
      pin[9]=0; pin[10]=0; pin[11]=0; pin[12]=0;
      } else {
      pin[20]=f_inv(pin[1]);
      pin[21]=f_inv(pin[15]);
      srff(20,21,17,18);
      if (pin[17]==0) {
        pin[5]=0; pin[5]=0; pin[6]=0; pin[7]=0;
        pin[9]=0; pin[10]=0; pin[11]=0; pin[12]=0;
        } else {
        pin[18]=f_binin(pin[2],pin[3],pin[4],0,0,0,0,0);
        decode(18,4,5,6,7,12,11,10,9);
        pin[4]=f_inv(pin[4]);
        pin[5]=f_inv(pin[5]);
        pin[6]=f_inv(pin[6]);
        pin[7]=f_inv(pin[7]);
        pin[9]=f_inv(pin[9]);
        pin[10]=f_inv(pin[10]);
        pin[11]=f_inv(pin[11]);
        pin[12]=f_inv(pin[12]);
        }
      }
    return 0;
    }
  }

