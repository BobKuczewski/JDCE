package parts.S7400;
import parts.*;
public class C74ls109 extends Ic {
  public C74ls109() {
    super();
    name=new String("74LS109");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==7 || pn==9 || pn==10) return 1; else return 0;
    }
  public int run() {
    int i;
    if ((pin[5]==0) && (pin[1]!=0)) 
      {
        pin[6]=0;
        pin[7]=1;
     }
    if ((pin[5]!=0) && (pin[1]==0)) 
      {
        pin[6]=1;
        pin[7]=0;
     }
    if ((pin[5]==0) && (pin[1]==0)) 
      {
        pin[6]=1;
        pin[7]=1;
     }
    if ((pin[11]==0) && (pin[15]!=0)) 
      {
        pin[10]=0;
        pin[9]=1;
     }
    if ((pin[11]!=0) && (pin[15]==0)) 
      {
        pin[10]=1;
        pin[9]=0;
     }
    if ((pin[11]==0) && (pin[15]==0)) 
      {
        pin[10]=1;
        pin[9]=1;
     }
    if ((pin[5]!=0) && (pin[1]!=0)) 
      {
        if ((pin[4]!=0) && (pin[20]==0)) 
          {
            pin[21]=pin[2];
            pin[22]=pin[3];
            if ((pin[21]==0) && (pin[22]==0)) 
              {
                pin[6]=0;
                pin[7]=1;
             }
            if ((pin[21]!=0) && (pin[22]!=0)) 
              {
                pin[6]=1;
                pin[7]=0;
             }
            if ((pin[21]!=0) && (pin[22]==0)) 
              {
                i=pin[6];
                pin[6]=pin[7];
                pin[7]=i;
             }
         }
     }
    if ((pin[11]!=0) && (pin[15]!=0)) 
      {
        if ((pin[12]!=0) && (pin[30]==0)) 
          {
            pin[31]=pin[14];
            pin[32]=pin[13];
            if ((pin[31]==0) && (pin[32]==0)) 
              {
                pin[10]=0;
                pin[9]=1;
             }
            if ((pin[31]!=0) && (pin[32]!=0)) 
              {
                pin[10]=1;
                pin[9]=0;
             }
            if ((pin[31]!=0) && (pin[32]==0)) 
              {
                i=pin[10];
                pin[10]=pin[9];
                pin[9]=i;
             }
         }
     }
    pin[20]=pin[4];
    pin[30]=pin[12];
    return 0;
    }
  }

