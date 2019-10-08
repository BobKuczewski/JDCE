package parts.S7400;
import parts.*;
public class C74ls75 extends Ic {
  public C74ls75() {
    super();
    name=new String("74LS75");
    pin=new int[40];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==1 || (pn>=8 && pn<=11) || (pn>=14 && pn<=16))
      return 1; else return 0;
    }
  public int run() {
    if (pin[13]!=0) 
      {
        if (pin[2]==0) 
          {
            pin[16]=0;
            pin[1]=1;
          }
        else
          {
            pin[16]=1;
            pin[1]=0;
         }
        if (pin[3]==0) 
          {
            pin[15]=0;
            pin[14]=1;
          }
        else
          {
            pin[15]=1;
            pin[14]=0;
         }
     }
    if (pin[4]!=0) 
      {
        if (pin[6]==0) 
          {
            pin[10]=0;
            pin[11]=1;
          }
        else
          {
            pin[10]=1;
            pin[11]=0;
         }
        if (pin[7]==0) 
          {
            pin[9]=0;
            pin[8]=1;
          }
        else
          {
            pin[9]=1;
            pin[8]=0;
         }
     }
    return 0;
    }
  }

