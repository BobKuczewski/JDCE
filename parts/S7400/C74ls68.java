package parts.S7400;
import parts.*;
public class C74ls68 extends Ic {
  public C74ls68() {
    super();
    name=new String("74ls68");
    pin=new int[33];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==14 || pn==2 || pn==3 || pn==13 || pn==7 || pn==5 ||
        pn==10 || pn==12) return 1; else return 0;
    }
  public int run() {
    byte i;
    int max=10;
    if (pin[4]==0) 
      {
        pin[20]=0;
        pin[14]=0;
     }
    if (pin[11]==0) 
      {
        pin[21]=0;
     }
    if ((pin[1]==0) && (pin[4]!=0)) 
      {
        if ((pin[1]==0) && (pin[30]!=0)) 
          {
            if (pin[14]==0)  pin[14]=1;
              else pin[14]=0;
         }
     }
    if ((pin[15]==0) && (pin[4]!=0)) 
      {
        if ((pin[15]==0) && (pin[31]!=0)) 
          {
            pin[20]++;
            if (pin[20]==(max / 2)) 
              pin[20]=0;
         }
     }
    if ((pin[9]==0) && (pin[11]!=0)) 
      {
        if ((pin[9]==0) && (pin[32]!=0)) 
          {
            pin[21]++;
            if (pin[21]==max) 
              pin[21]=0;
         }
     }
    pin[30]=pin[1];
    pin[31]=pin[15];
    pin[32]=pin[9];
    disperse(21,7,10,5,12,0,0,0,0);
    disperse(20,2,13,3,0,0,0,0,0);
    return 0;
    }
  }

