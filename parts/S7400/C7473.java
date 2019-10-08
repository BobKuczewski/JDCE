package parts.S7400;
import parts.*;
public class C7473 extends Ic {
  public C7473() {
    super();
    name=new String("7473");
    pin=new int[40];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==8 || pn==9 || pn==12 || pn==13) return 1; else return 0;
    }
  public int run() {
    int i;
    if ((pin[2]==0)) 
      {
        pin[12]=0;
        pin[13]=1;
     }
    if ((pin[6]==0)) 
      {
        pin[9]=0;
        pin[8]=1;
     }
    if (pin[2]!=0) 
      {
        if ((pin[1]!=0) && (pin[20]==0)) 
          {
            pin[21]=pin[14];
            pin[22]=pin[3];
         }
        if ((pin[1]==0) && (pin[20]!=0)) 
          {
            if ((pin[21]!=0) && (pin[22]==0)) 
              {
                pin[12]=1;
                pin[13]=0;
             }
            if ((pin[21]==0) && (pin[22]!=0)) 
              {
                pin[12]=0;
                pin[13]=1;
             }
            if ((pin[21]!=0) && (pin[22]!=0)) 
              {
                i=pin[12];
                pin[12]=pin[13];
                pin[13]=i;
             }
         }
     }
    if (pin[6]!=0) 
      {
        if ((pin[5]!=0) && (pin[30]==0)) 
          {
            pin[31]=pin[7];
            pin[32]=pin[10];
         }
        if ((pin[5]==0) && (pin[30]!=0)) 
          {
            if ((pin[31]!=0) && (pin[32]==0)) 
              {
                pin[9]=1;
                pin[8]=0;
             }
            if ((pin[31]==0) && (pin[32]!=0)) 
              {
                pin[9]=0;
                pin[8]=1;
             }
            if ((pin[31]!=0) && (pin[32]!=0)) 
              {
                i=pin[9];
                pin[9]=pin[8];
                pin[8]=i;
             }
         }
     }
    pin[20]=pin[1];
    pin[30]=pin[5];
    return 0;
    }
  }

