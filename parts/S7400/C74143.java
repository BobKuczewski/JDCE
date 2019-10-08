package parts.S7400;
import parts.*;
public class C74143 extends Ic {
  public C74143() {
    super();
    name=new String("74143");
    pin=new int[41];
    }
  public int numPins() {
    return 24;
    }
  public int pinOut(int pn) {
    if (pn!=6)
      {
        if ((pn>=14 && pn<=20) || (pn>=8 && pn <=11) || pn==13 || pn==22)
          return 1; else return 0;
      }
    else
      {
        if ((pin[4]==0) && (pin[32]==0))  return 129;
          else return 128;
     }
    }
  public int run() {
    int i;
    int p;
    if (pin[3]==0)  pin[30]=0;
   else
      {
        if (pin[23]==0 && pin[1]==0 &&
            pin[2]!=0 && pin[31]==0)
          {
            pin[30]++;
            if (pin[30]==10)  pin[30]=0;
            if (pin[30]==9)  pin[22]=0; else pin[22]=1;
         }
     }
    if (pin[21]==0)  pin[32]=pin[30];
    if ((pin[32] & 1)==1)  pin[17]=1; else pin[17]=0;
    if ((pin[32] & 2)==2)  pin[18]=1; else pin[18]=0;
    if ((pin[32] & 4)==4)  pin[19]=1; else pin[19]=0;
    if ((pin[32] & 8)==8)  pin[20]=1; else pin[20]=0;
    pin[14]=1; pin[15]=1; pin[16]=1; pin[9]=1;
    pin[10]=1; pin[11]=1; pin[13]=1;
    p=pin[32];
    if (p==0 || p==2 || p==3 || p==5 || p==7 || p==8 || p==9 || p==13)
      pin[15]=0;
    if ((p>=0 && p<=4) || (p>=7 && p<=9) || p==12) pin[16]=0;
    if (p==0 || p==1 || (p>=3 && p<=9) || p==11) pin[14]=0;
    if (p==0 || p==2 || p==3 || p==5 || p==6 || p==8 || p==10 || p==11 ||
        p==13 || p==14) pin[9]=0;
    if (p==0 || p==2 || p==6 || p==8 || p==10 || p==14 ) pin[11]=0;
    if (p==0 || (p>=4 && p<=6) || p==8 || p==9 || (p>=12 && p<=14)) pin[10]=0;
    if ((p>=2 && p<=6) || (p>=8 && p<=14)) pin[13]=0;
    if (pin[7]==0)  pin[8]=0; else pin[8]=1;
    if ((pin[4]==0) && (pin[32]==0))  pin[6]=0;
    if ((pin[6]==0) || (pin[5]!=0))
      {
        pin[15]=1; pin[16]=1; pin[14]=1; pin[9]=1;
        pin[11]=1; pin[10]=1; pin[13]=1; pin[8]=1;
     }
    pin[31]=pin[2];
    return 0;
    }
  }

