package parts.S7400;
import parts.*;
public class C74ls114 extends Ic {
  public C74ls114() {
    super();
    name=new String("74LS114");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==5 || pn==6 || pn==8 || pn==9) return 1; else return 0;
    }
  public int run() {
    int i;
    if ((pin[4]==0) && (pin[1]!=0))
      {
        pin[5]=1;
        pin[6]=0;
     }
    if ((pin[4]!=0) && (pin[1]==0))
      {
        pin[5]=0;
        pin[6]=1;
     }
    if ((pin[4]==0) && (pin[1]==0))
      {
        pin[5]=1;
        pin[6]=1;
     }
    if ((pin[10]==0) && (pin[1]!=0))
      {
        pin[9]=1;
        pin[8]=0;
     }
    if ((pin[10]!=0) && (pin[1]==0))
      {
        pin[9]=0;
        pin[8]=1;
     }
    if ((pin[10]==0) && (pin[1]==0))
      {
        pin[9]=1;
        pin[8]=1;
     }
    if ((pin[4]!=0) && (pin[1]!=0))
      {
        if ((pin[13]==0) && (pin[20]!=0))
          {
            pin[21]=pin[3];
            pin[22]=pin[2];
            if ((pin[21]!=0) && (pin[22]==0))
              {
                pin[5]=1;
                pin[6]=0;
             }
            if ((pin[21]==0) && (pin[22]!=0))
              {
                pin[5]=0;
                pin[6]=1;
             }
            if ((pin[21]!=0) && (pin[22]!=0))
              {
                i=pin[5];
                pin[5]=pin[6];
                pin[6]=i;
             }
         }
     }
    if ((pin[10]!=0) && (pin[1]!=0))
      {
        if ((pin[13]==0) && (pin[30]!=0))
          {
            pin[31]=pin[11];
            pin[32]=pin[12];
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
    pin[20]=pin[13];
    pin[30]=pin[13];
    return 0;
    }
  }

