package parts.cdp1800;
import parts.*;
public class CCDP1878 extends Ic {
  int counterA,counterB;
  int jamA,jamB;
  int holdA,holdB;
  int modeA,modeB;
  int intReg;
  int enable;
  public CCDP1878() {
    super();
    name=new String("CDP1878");
    pin=new int[41];
    reset();
    }
  public void reset() {
    holdA=0; holdB=0;
    jamA=0; jamB=0;
    counterA=0; counterB=0;
    modeA=0; modeB=0;
    intReg=0;
    enable=0;
    }
  public int numPins() {
    return 28;
    }
  public int pinOut(int pn) {
    if (pn==1 || pn==2 || pn==3 || pn==18 || pn==19) return 1;
    if (pn>=20 && pn<=27) {
      if (pin[6]==0) return 129; else return 128;
      }
    return 0;
    }
  private void write(int value) {
    if ((value&1)==1) pin[20]=1; else pin[20]=0;
    if ((value&2)==2) pin[21]=1; else pin[21]=0;
    if ((value&4)==4) pin[22]=1; else pin[22]=0;
    if ((value&8)==8) pin[23]=1; else pin[23]=0;
    if ((value&16)==16) pin[24]=1; else pin[24]=0;
    if ((value&32)==32) pin[25]=1; else pin[25]=0;
    if ((value&64)==64) pin[26]=1; else pin[26]=0;
    if ((value&128)==128) pin[27]=1; else pin[27]=0;
    }
  private int read() {
    int ret=0;
    if (pin[20]!=1) ret=1; else ret=0;
    if (pin[21]!=1) ret+=2;
    if (pin[22]!=1) ret+=4;
    if (pin[23]!=1) ret+=8;
    if (pin[24]!=1) ret+=16;
    if (pin[25]!=1) ret+=32;
    if (pin[26]!=1) ret+=64;
    if (pin[27]!=1) ret+=128;
    return ret;
    }
  public int run() {
    int addr=0,input;
    if (pin[9]!=0) enable=pin[10];
    if (enable!=0) {
      if (pin[6]==0) {
        if (pin[11]!=0) addr=1; else addr=0;
        if (pin[12]!=0) addr+=2;
        if (pin[13]!=0) addr+=4;
        switch (addr) {
          case 2:write(holdA&255); break;
          case 3:write(holdB&255); break;
          case 4:
          case 5:write(intReg); break;
          case 6:write(holdA>>8); break;
          case 7:write(holdB>>8); break;
          }
        } /* end if for /rd */
      if ((pin[7]!=0 && pin[30]!=0 && pin[8]==0) ||
          (pin[7]==0 && pin[30]==0 && pin[8]!=0)) {
        if (pin[11]!=0) addr=1; else addr=0;
        if (pin[12]!=0) addr+=2;
        if (pin[13]!=0) addr+=4;
        input=read();
        switch (addr) {
          case 2:jamA=(jamA&(255<<8))+input; break;
          case 3:jamB=(jamB&(255<<8))+input; break;
          case 4:if ((input&7)!=0) modeA=input; 
                   else modeA=(modeA&7)+(input&0xf8);
                 if ((modeA&128)==128) {
                   counterA=jamA;
                   pin[2]=0; pin[3]=1;
                   intReg&=0x7f;
                   }
                 break;
          case 5:if ((input&7)!=0) modeB=input; 
                   else modeB=(modeB&7)+(input&0xf8);
                 if ((modeB&128)==128) {
                   counterB=jamB;
                   pin[19]=0; pin[18]=1;
                   intReg&=0xbf;
                   }
                 break;
          case 6:jamA=(jamA&255)+(input<<8); break;
          case 7:jamB=(jamB&255)+(input<<8); break;
          }
        } /* end of if for write */
      if ((modeA & 288)==288 && (((modeA&8)==8 && pin[4]!=0) ||
                                 ((modeA&8)==0 && pin[4]==0))) {
        if (pin[31]!=0 && pin[5]==0) {
          if (counterA>0) {
            if (--counterA == 0) {
              intReg+=128;
              pin[2]=0; pin[3]=1;
              if ((modeA&16)==16) pin[1]=0;
              }
            } else {
            }
          } /* end of pulse detection */
        }
      if ((modeA & 64)==0) holdA=counterA;
      if ((modeB & 64)==0) holdB=counterB;
      }
    pin[30]=pin[8];
    pin[31]=pin[5];
    pin[32]=pin[16];
    return 0;
    }
  }

