package parts.S7400;
import parts.*;
public class C74ls147 extends Ic {
  public C74ls147() {
    super();
    name=new String("74LS147");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==7 || pn==9 || pn==14) return 1; else return 0;
    }
  public int run() {
    if (pin[10]==0)
      {
        pin[14]=0; pin[6]=1; pin[7]=1; pin[9]=0;
     }
   else if (pin[5]==0)
      {
        pin[14]=0; pin[6]=1; pin[7]=1; pin[9]=1;
     }
    else if (pin[4]==0)
      {
        pin[14]=1; pin[6]=0; pin[7]=0; pin[9]=0;
     }
    else if (pin[3]==0)
      {
        pin[14]=1; pin[6]=0; pin[7]=0; pin[9]=1;
     }
    else if (pin[2]==0)
      {
        pin[14]=1; pin[6]=0; pin[7]=1; pin[9]=0;
     }
    else if (pin[1]==0)
      {
        pin[14]=1; pin[6]=0; pin[7]=1; pin[9]=1;
     }
    else if (pin[13]==0)
      {
        pin[14]=1; pin[6]=1; pin[7]=0; pin[9]=0;
     }
    else if (pin[12]==0)
      {
        pin[14]=1; pin[6]=1; pin[7]=0; pin[9]=1;
     }
    else if (pin[11]==0)
      {
        pin[14]=1; pin[6]=1; pin[7]=1; pin[9]=0;
     }
    else
      {
        pin[14]=1; pin[6]=1; pin[7]=1; pin[9]=1;
     }
    return 0;
    }
  }

