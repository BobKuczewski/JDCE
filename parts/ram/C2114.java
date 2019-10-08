package parts.ram;
import parts.*;
public class C2114 extends Ram {
  public C2114() {
    super();
    name=new String("2114");
    pin=new int[41];
    memory=new byte[1024];
    }
  public int numPins() {
    return 18;
    }
  public int pinOut(int pn) {
    if (pin[10]!=0)
      {
        if (pn>=11 && pn<=14) return 129; else return 128;
      }
   else
    return 128;
    }
  public int run() {
    int i;
    byte j;
    int bl;
    if (pin[8]!=0)
      {
        pin[11]=2; pin[12]=2; pin[13]=2; pin[14]=2;
     }
    if ((pin[8]==0) && (pin[10]==0))
      {
        if (pin[5]!=0)  i=1; else i=0;
        if (pin[6]!=0)  i+=2;
        if (pin[7]!=0)  i+=4;
        if (pin[4]!=0)  i+=8;
        if (pin[3]!=0)  i+=16;
        if (pin[2]!=0)  i+=32;
        if (pin[1]!=0)  i+=64;
        if (pin[17]!=0)  i+=128;
        if (pin[16]!=0)  i+=256;
        if (pin[15]!=0)  i+=512;
        if (pin[14]!=0)  j=1; else j=0;
        if (pin[13]!=0)  j+=2;
        if (pin[12]!=0)  j+=4;
        if (pin[11]!=0)  j+=8;
        memory[i]=j;
     }
    if ((pin[8]==0) && (pin[10]!=0))
      {
        if (pin[5]!=0)  i=1; else i=0;
        if (pin[6]!=0)  i+=2;
        if (pin[7]!=0)  i+=4;
        if (pin[4]!=0)  i+=8;
        if (pin[3]!=0)  i+=16;
        if (pin[2]!=0)  i+=32;
        if (pin[1]!=0)  i+=64;
        if (pin[17]!=0)  i+=128;
        if (pin[16]!=0)  i+=256;
        if (pin[15]!=0)  i+=512;
        j=memory[i];
        if ((j & 1)==1)  pin[14]=1; else pin[14]=0;
        if ((j & 2)==2)  pin[13]=1; else pin[13]=0;
        if ((j & 4)==4)  pin[12]=1; else pin[12]=0;
        if ((j & 8)==8)  pin[11]=1; else pin[11]=0;
     }
    return 0;
    }
  }

