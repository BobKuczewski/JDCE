package parts.S7400;
import parts.*;
public class C74ls194 extends Ic {
  public C74ls194() {
    super();
    name=new String("74LS194");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn>=12 && pn<=15) return 1; else return 0;
    }
  public int run() {
    int i;
    if (pin[1]==0)  pin[20]=0;
   else
      {
        if ((pin[11]!=0) && (pin[21]==0))
          {
            if ((pin[9]==0) && (pin[10]!=0))
              {
                pin[20]=pin[20] / 2;
                if (pin[7]!=0)  pin[20]=(pin[20] | 8);
             }
            if ((pin[9]!=0) && (pin[10]==0))
              {
                pin[20]+=pin[20];
                if (pin[2]!=0)  pin[20]=(pin[20] | 1);
             }
            if ((pin[9]!=0) && (pin[10]!=0))
              {
                if (pin[3]!=0)  i=1; else i=0;
                if (pin[4]!=0)  i+=2;
                if (pin[5]!=0)  i+=4;
                if (pin[6]!=0)  i+=8;
                pin[20]=i;
             }
         }
     }
    pin[21]=pin[11];
    if ((pin[20] & 1)==1)  pin[15]=1; else pin[15]=0;
    if ((pin[20] & 2)==2)  pin[14]=1; else pin[14]=0;
    if ((pin[20] & 4)==4)  pin[13]=1; else pin[13]=0;
    if ((pin[20] & 8)==8)  pin[12]=1; else pin[12]=0;
    return 0;
    }
  }

