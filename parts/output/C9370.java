package parts.output;

import java.awt.*;
import parts.*;
import shared.*;

public class C9370 extends Ic {
  public C9370() {
    super();
    name=new String("9370");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn!=4)
      {
        if (pn>=9 && pn<=15) return 1; else return 0;
     }
   else
      {
        if ((pin[5]==0) && (pin[7]==0) && (pin[1]==0) &&
           (pin[2]==0) && (pin[6]==0)) return 129;
         else return 128;
     }
    }
  public int run() {
    int valu;
    pin[13]=1; pin[12]=1; pin[11]=1; pin[10]=1;
    pin[9]=1; pin[15]=1; pin[14]=1;
    if (pin[7]!=0)  valu=1; else valu=0;
    if (pin[1]!=0)  valu+=2;
    if (pin[2]!=0)  valu+=4;
    if (pin[6]!=0)  valu+=8;
    if (pin[3]==0)  pin[30]=valu;
    valu=pin[30];
    if (valu==0 || valu==2 || valu==3 || (valu>=5 && valu<=10) ||
        valu==12 || valu==14 || valu==15) pin[13]=0;
    if ((valu>=0 && valu<=4) || (valu>=7 && valu<=10) || valu==13) pin[12]=0;
    if (valu==0 || valu==1 || (valu>=3 && valu<=11) || valu==13) pin[11]=0;
    if (valu==0 || valu==2 || valu==3 || valu==5 || valu==6 || valu==8 ||
        (valu>=11 && valu<=14)) pin[10]=0;
    if (valu==0 || valu==2 || valu==6 || valu==8 || (valu>=10 && valu<=15))
        pin[9]=0;
    if (valu==0 || (valu>=4 && valu<=6) || (valu>=8 && valu<=12) ||
        valu==14 || valu==15) pin[15]=0;
    if ((valu>=2 && valu<=6) || (valu>=8 && valu<=11) || (valu>=13 && valu<=15))
        pin[14]=0;
    if ((pin[5]==0) && (valu==0))  pin[4]=0;
  /*  if (pin[5]==0) && (pin[7]==0) && (pin[1]==0) &&
       (pin[2]==0) && (pin[6]==0) then pin[4]=0;  */
    if (pin[4]==0)
      {
        pin[13]=1; pin[12]=1; pin[11]=1; pin[10]=1;
        pin[9]=1; pin[15]=1; pin[14]=1;
     }
    return 0;
    }
  }

