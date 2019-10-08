package parts.S7400;
import parts.*;
public class C74116 extends Ic {
  public C74116() {
    super();
    name=new String("74116");
    pin=new int[41];
    }
  public int numPins() {
    return 24;
    }
  public int pinOut(int pn) {
    if (pn==5 || pn==7 || pn==9 || pn==11 || pn==17 || pn==19 ||
      pn==21 || pn==23) return 1; else return 0;
    }
  public int run() {
    if (pin[1]==0) 
      {
        pin[5]=0;
        pin[7]=0;
        pin[9]=0;
        pin[11]=0;
     }
    if ((pin[1]!=0) && (pin[2]==0) && (pin[3]==0)) 
      {
        if (pin[4]!=0)  pin[5]=1; else pin[5]=0;
        if (pin[6]!=0)  pin[7]=1; else pin[7]=0;
        if (pin[8]!=0)  pin[9]=1; else pin[9]=0;
        if (pin[10]!=0)  pin[11]=1; else pin[11]=0;
     }
    if (pin[13]==0) 
      {
        pin[17]=0;
        pin[19]=0;
        pin[21]=0;
        pin[23]=0;
     }
    if ((pin[13]!=0) && (pin[14]==0) && (pin[15]==0)) 
      {
        if (pin[16]!=0)  pin[17]=1; else pin[17]=0;
        if (pin[18]!=0)  pin[19]=1; else pin[19]=0;
        if (pin[20]!=0)  pin[21]=1; else pin[21]=0;
        if (pin[22]!=0)  pin[23]=1; else pin[23]=0;
     }
    if (pin[5]!=0)  pin[5]=1;
    if (pin[7]!=0)  pin[7]=1;
    if (pin[9]!=0)  pin[9]=1;
    if (pin[11]!=0)  pin[11]=1;
    if (pin[17]!=0)  pin[17]=1;
    if (pin[19]!=0)  pin[19]=1;
    if (pin[21]!=0)  pin[21]=1;
    if (pin[23]!=0)  pin[23]=1;
    return 0;
    }
  }

