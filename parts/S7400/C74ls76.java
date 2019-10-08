package parts.S7400;
import parts.*;
public class C74ls76 extends Ic {
  public C74ls76() {
    super();
    name=new String("74LS76");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==10 || pn==11 || pn==14 || pn==15) return 1; else return 0;
    }
  public int run() {
    int i;
    if ((pin[2]==0) && (pin[3]!=0)) 
      {
        pin[14]=0;
        pin[15]=1;
     }
    if ((pin[2]!=0) && (pin[3]==0)) 
      {
        pin[14]=1;
        pin[15]=0;
     }
    if ((pin[2]==0) && (pin[3]==0)) 
      {
        pin[14]=1;
        pin[15]=1;
     }
    if ((pin[7]==0) && (pin[8]!=0)) 
      {
        pin[10]=0;
        pin[11]=1;
     }
    if ((pin[7]!=0) && (pin[8]==0)) 
      {
        pin[10]=1;
        pin[11]=0;
     }
    if ((pin[7]==0) && (pin[8]==0)) 
      {
        pin[11]=1;
        pin[10]=1;
     }
    if ((pin[2]!=0) && (pin[3]!=0)) 
      {
        if ((pin[1]==0) && (pin[20]!=0)) 
          {
            pin[21]=pin[4];
            pin[22]=pin[16];
            if ((pin[21]!=0) && (pin[22]==0)) 
              {
                pin[15]=1;
                pin[14]=0;
             }
            if ((pin[21]==0) && (pin[22]!=0)) 
              {
                pin[15]=0;
                pin[14]=1;
             }
            if ((pin[21]!=0) && (pin[22]!=0)) 
              {
                i=pin[15];
                pin[15]=pin[14];
                pin[14]=i;
             }
         }
     }
    if ((pin[7]!=0) && (pin[8]!=0)) 
      {
        if ((pin[6]==0) && (pin[30]!=0)) 
          {
            pin[31]=pin[9];
            pin[32]=pin[12];
            if ((pin[31]!=0) && (pin[32]==0)) 
              {
                pin[11]=1;
                pin[10]=0;
             }
            if ((pin[31]==0) && (pin[32]!=0)) 
              {
                pin[11]=0;
                pin[10]=1;
             }
            if ((pin[31]!=0) && (pin[32]!=0)) 
              {
                i=pin[11];
                pin[11]=pin[10];
                pin[10]=i;
             }
         }
     }
    pin[20]=pin[1];
    pin[30]=pin[6];
    return 0;
    }
  }

