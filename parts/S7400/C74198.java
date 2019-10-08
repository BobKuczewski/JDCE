package parts.S7400;
import parts.*;
public class C74198 extends Ic {
  public C74198() {
    super();
    name=new String("74198");
    pin=new int[41];
    }
  public int numPins() {
    return 24;
    }
  public int pinOut(int pn) {
    if (pn==4 || pn==6 || pn==8 || pn==10 || pn==14 || pn==16 ||
        pn==18 || pn==20) return 1; else return 0;
    }
  public int run() {
    int i;
    if (pin[13]==0)  pin[30]=0;
   else
      {
        if ((pin[11]!=0) && (pin[31]==0))
          {
            if ((pin[1]==0) && (pin[23]!=0))
              {
                pin[30]=pin[30] / 2;
                if (pin[22]!=0)  pin[30]=(pin[30] | 128);
             }
            if ((pin[1]!=0) && (pin[23]==0))
              {
                pin[30]+=pin[30];
                if (pin[2]!=0)  pin[30]=(pin[30] | 1);
             }
            if ((pin[1]!=0) && (pin[23]!=0))
              {
                if (pin[3]!=0)  i=1; else i=0;
                if (pin[5]!=0)  i+=2;
                if (pin[7]!=0)  i+=4;
                if (pin[9]!=0)  i+=8;
                if (pin[15]!=0)  i+=16;
                if (pin[17]!=0)  i+=32;
                if (pin[19]!=0)  i+=64;
                if (pin[21]!=0)  i+=128;
                pin[30]=i;
             }
         }
     }
    pin[31]=pin[11];
    disperse(30,4,6,8,10,14,16,18,20);
    return 0;
    }
  }

