package parts.S7400;
import parts.*;
public class C74285 extends Ic {
  public C74285() {
    super();
    name=new String("74285");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn>=9 && pn<=12) return 1; else return 0;
    }
  public int run() {
    int digit=0;
    int i,j;
    if ((pin[13]==0) && (pin[14]==0))
      {
        if (pin[5]!=0)  i=1; else i=0;
        if (pin[6]!=0)  i+=2;
        if (pin[7]!=0)  i+=4;
        if (pin[4]!=0)  i+=8;
        if (pin[3]!=0)  j=1; else j=0;
        if (pin[2]!=0)  j+=2;
        if (pin[1]!=0)  j+=4;
        if (pin[15]!=0)  j+=8;
        i=i*j;
        if (digit==0)
          {
            if ((i & 1)==1)  pin[12]=1; else pin[12]=0;
            if ((i & 2)==2)  pin[11]=1; else pin[11]=0;
            if ((i & 4)==4)  pin[10]=1; else pin[10]=0;
            if ((i & 8)==8)  pin[9]=1; else pin[9]=0;
         }
       else
          {
            if ((i & 16)==16)  pin[12]=1; else pin[12]=0;
            if ((i & 32)==32)  pin[11]=1; else pin[11]=0;
            if ((i & 64)==64)  pin[10]=1; else pin[10]=0;
            if ((i & 128)==128)  pin[9]=1; else pin[9]=0;
         }
     }
   else
      {
        pin[9]=1; pin[10]=1; pin[11]=1; pin[12]=1;
     }
    return 0;
    }
  }

