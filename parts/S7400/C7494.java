package parts.S7400;
import parts.*;
public class C7494 extends Ic {
  public C7494() {
    super();
    name=new String("7494");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==9) return 1; else return 0;
    }
  public int run() {
    pin[31]=f_nor(f_and(pin[1],pin[6]),f_and(pin[16],pin[15]));
    pin[32]=f_nor(f_and(pin[2],pin[6]),f_and(pin[14],pin[15]));
    pin[33]=f_nor(f_and(pin[3],pin[6]),f_and(pin[13],pin[15]));
    pin[34]=f_nor(f_and(pin[4],pin[6]),f_and(pin[11],pin[15]));
    if ((pin[10]!=0) && (pin[31]+pin[32]+pin[33]+pin[34]==4))  pin[20]=0;
    if ((pin[8]!=0) && (pin[21]==0) && (pin[10]==0)) 
     if ((pin[10]!=0) && (pin[31]+pin[32]+pin[33]+pin[34]==4))  pin[20]=0;
      {
        pin[20]+=pin[20];
        if (pin[7]!=0)  pin[20]++;
        pin[20]=(pin[20] & 15);
     }
    if ((pin[8]==0) && (pin[10]==0)) 
      {
        if (pin[31]==0)  pin[20]=(pin[20] | 8);
        if (pin[32]==0)  pin[20]=(pin[20] | 4);
        if (pin[33]==0)  pin[20]=(pin[20] | 2);
        if (pin[34]==0)  pin[20]=(pin[20] | 1);
     }
    if ((pin[20] & 8)==8) 
      pin[9]=1;
    else
      pin[9]=0;
    pin[21]=pin[8];
    return 0;
    }
  }

