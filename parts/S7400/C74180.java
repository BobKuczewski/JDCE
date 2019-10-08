package parts.S7400;
import parts.*;
public class C74180 extends Ic {
  public C74180() {
    super();
    name=new String("74180");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==5 || pn==6) return 1; else return 0;
    }
  public int run() {
    int i;
    if (pin[8]!=0)  i=1; else i=0;
    if (pin[9]!=0)  i++;
    if (pin[10]!=0)  i++;
    if (pin[11]!=0)  i++;
    if (pin[12]!=0)  i++;
    if (pin[13]!=0)  i++;
    if (pin[1]!=0)  i++;
    if (pin[2]!=0)  i++;
    i=i & 1;
    if ((i==0) && (pin[3]!=0) && (pin[4]==0))
      {
        pin[5]=1; pin[6]=0;
     }
    if ((i==1) && (pin[3]!=0) && (pin[4]==0))
      {
        pin[5]=0; pin[6]=1;
     }
    if ((i==0) && (pin[3]==0) && (pin[4]!=0))
      {
        pin[5]=0; pin[6]=1;
     }
    if ((i!=0) && (pin[3]==0) && (pin[4]!=0))
      {
        pin[5]=1; pin[6]=0;
     }
    if ((pin[3]!=0) && (pin[4]!=0))
      {
        pin[5]=0; pin[6]=0;
     }
    if ((pin[3]==0) && (pin[4]==0))
      {
        pin[5]=1; pin[6]=1;
     }
    return 0;
    }
  }

