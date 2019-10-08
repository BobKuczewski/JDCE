package parts.S7400;
import parts.*;
public class C74ls74 extends Ic {
  public C74ls74() {
    super();
    name=new String("74LS74");
    pin=new int[40];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==5 || pn==6 || pn==8 || pn==9) return 1; else return 0;
    }
  public int run() {
    if ((pin[4]==0) && (pin[1]==0)) 
      {
        pin[5]=1; pin[6]=1;
     }
    if ((pin[4]!=0) && (pin[1]==0)) 
      {
        pin[5]=0; pin[6]=1;
     }
    if ((pin[4]==0) && (pin[1]!=0)) 
      {
        pin[5]=1; pin[6]=0;
     }
    if ((pin[4]!=0) && (pin[1]!=0)) 
     if ((pin[3]!=0) && (pin[15]==0)) 
      {
        if (pin[2]==0) 
          {
            pin[5]=0; pin[6]=1;
         }
        if (pin[2]!=0) 
          {
            pin[5]=1; pin[6]=0;
         }
     }
    pin[15]=pin[3];
    if ((pin[10]==0) && (pin[13]==0)) 
      {
        pin[9]=1; pin[8]=1;
     }
    if ((pin[10]!=0) && (pin[13]==0)) 
      {
        pin[9]=0; pin[8]=1;
     }
    if ((pin[10]==0) && (pin[13]!=0)) 
      {
        pin[9]=1; pin[8]=0;
     }
    if ((pin[10]!=0) && (pin[13]!=0)) 
     if ((pin[11]!=0) && (pin[16]==0)) 
      {
        if (pin[12]==0) 
          {
            pin[9]=0; pin[8]=1;
         }
        if (pin[12]!=0) 
          {
            pin[9]=1; pin[8]=0;
         }
     }
    pin[16]=pin[11];
    return 0;
    }
  }

