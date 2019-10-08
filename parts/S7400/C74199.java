package parts.S7400;
import parts.*;
public class C74199 extends Ic {
  public C74199() {
    super();
    name=new String("74199");
    pin=new int[41];
    }
  public int numPins() {
    return 24;
    }
  public int pinOut(int pn) {
    if (pn==4 || pn==6 || pn==8 || pn==10 || pn==15 || pn==17 ||
        pn==19 || pn==21) return 1; else return 0;
    }
  public int run() {
    int i;
    int cl=0;
    if (pin[11]==0)
      if ((pin[31]==0) && (pin[13]!=0))  cl=1; else cl=0;
    if (pin[13]==0)
      if ((pin[32]==0) && (pin[11]!=0))  cl=1; else cl=0;
  
    if ((pin[23]==0) && (cl==1))
      {
        if (pin[3]!=0)  pin[30]=1; else pin[30]=0;
        if (pin[5]!=0)  pin[30]+=2;
        if (pin[7]!=0)  pin[30]+=4;
        if (pin[9]!=0)  pin[30]+=8;
        if (pin[16]!=0)  pin[30]+=16;
        if (pin[18]!=0)  pin[30]+=32;
        if (pin[20]!=0)  pin[30]+=64;
        if (pin[22]!=0)  pin[30]+=128;
     }
    if ((pin[23]!=0) && (cl==1))
      {
        if ((pin[2]==0) && (pin[1]!=0))
          {
            if ((pin[30] & 1)==1)  i=1; else i=0;
            pin[30]=(pin[30]+pin[30]+i);
         }
        if ((pin[2]==0) && (pin[1]==0))
          pin[30]=(pin[30]+pin[30]);
        if ((pin[2]!=0) && (pin[1]!=0))
          pin[30]+=pin[30]+1;
        if ((pin[2]!=0) && (pin[1]==0))
          {
            if ((pin[30] & 1)==1)  i=0; else i=1;
            pin[30]+=pin[30]+i;
         }
     }
    if (pin[14]==0)  pin[30]=0;
    disperse(30,4,6,8,10,15,17,19,21);
    pin[31]=pin[13];
    pin[32]=pin[11];
    return 0;
    }
  }

