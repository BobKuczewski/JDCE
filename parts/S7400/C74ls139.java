package parts.S7400;
import parts.*;
public class C74ls139 extends Ic {
  public C74ls139() {
    super();
    name=new String("74LS139");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if ((pn>=4 && pn<=7) || (pn>=9 && pn<=12)) return 1; else return 0;
    }
  public int run() {
    int i;
    for (i=4;i<=7;i++) pin[i]=1;
    for (i=9;i<=12;i++) pin[i]=1;
    if (pin[1]==0)
      {
        if ((pin[3]==0) && (pin[2]==0))  pin[4]=0;
        if ((pin[3]==0) && (pin[2]!=0))  pin[5]=0;
        if ((pin[3]!=0) && (pin[2]==0))  pin[6]=0;
        if ((pin[3]!=0) && (pin[2]!=0))  pin[7]=0;
     }
    if (pin[15]==0)
      {
        if ((pin[13]==0) && (pin[14]==0))  pin[12]=0;
        if ((pin[13]==0) && (pin[14]!=0))  pin[11]=0;
        if ((pin[13]!=0) && (pin[14]==0))  pin[10]=0;
        if ((pin[13]!=0) && (pin[14]!=0))  pin[9]=0;
     }
    return 0;
    }
  }

