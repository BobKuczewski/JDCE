package parts.rom;
import parts.*;
public class C2764 extends Rom {
  public C2764() {
    super();
    name2=new String("2764");
    pin=new int[41];
    memory=new byte[8192];
    }
  public int numPins() {
    return 28;
    }
  public int pinOut(int pn) {
    if ((pn>=11 && pn<=13) || (pn>=15 && pn<=19)) return 1; else return 0;
    }
  public int run() {
  int i;
  byte j;
  if ((pin[20]!=0) || (pin[22]!=0))
    {
      pin[11]=2; pin[12]=2; pin[13]=2; pin[15]=2;
      pin[16]=2; pin[17]=2; pin[18]=2; pin[19]=2;
   }
  if ((pin[20]==0) && (pin[22]==0))
    {
      if (pin[10]!=0)  i=1; else i=0;
      if (pin[9]!=0)  i+=2;
      if (pin[8]!=0)  i+=4;
      if (pin[7]!=0)  i+=8;
      if (pin[6]!=0)  i+=16;
      if (pin[5]!=0)  i+=32;
      if (pin[4]!=0)  i+=64;
      if (pin[3]!=0)  i+=128;
      if (pin[25]!=0)  i+=256;
      if (pin[24]!=0)  i+=512;
      if (pin[21]!=0)  i+=1024;
      if (pin[23]!=0)  i+=2048;
      if (pin[2]!=0)  i+=4096;
      j=memory[i];
      if ((j & 1)==1)  pin[11]=1; else pin[11]=0;
      if ((j & 2)==2)  pin[12]=1; else pin[12]=0;
      if ((j & 4)==4)  pin[13]=1; else pin[13]=0;
      if ((j & 8)==8)  pin[15]=1; else pin[15]=0;
      if ((j & 16)==16)  pin[16]=1; else pin[16]=0;
      if ((j & 32)==32)  pin[17]=1; else pin[17]=0;
      if ((j & 64)==64)  pin[18]=1; else pin[18]=0;
      if ((j & 128)==128)  pin[19]=1; else pin[19]=0;
       }
    return 0;
    }
  }

