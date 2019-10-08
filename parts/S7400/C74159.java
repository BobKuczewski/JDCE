package parts.S7400;
import parts.*;
public class C74159 extends Ic {
  public C74159() {
    super();
    name=new String("74159");
    pin=new int[41];
    }
  public int numPins() {
    return 24;
    }
  public int pinOut(int pn) {
    if ((pn>=1 && pn<=11) || (pn>=13 && pn<=17)) return 1; else return 0;
    }
  public int run() {
    int i;
    for (i=1;i<=11;i++) pin[i]=1;
    for (i=13;i<=17;i++) pin[i]=1;
    if ((pin[18]==0) && (pin[19]==0)) 
      {
        if (pin[23]!=0)  i=1; else i=0;
        if (pin[22]!=0)  i+=2;
        if (pin[21]!=0)  i+=4;
        if (pin[20]!=0)  i+=8;
        switch(i) {
          case 0:pin[1]=0; break;
          case 1:pin[2]=0; break;
          case 2:pin[3]=0; break;
          case 3:pin[4]=0; break;
          case 4:pin[5]=0; break;
          case 5:pin[6]=0; break;
          case 6:pin[7]=0; break;
          case 7:pin[8]=0; break;
          case 8:pin[9]=0; break;
          case 9:pin[10]=0; break;
          case 10:pin[11]=0; break;
          case 11:pin[13]=0; break;
          case 12:pin[14]=0; break;
          case 13:pin[15]=0; break;
          case 14:pin[16]=0; break;
          case 15:pin[17]=0; break;
        }
     }
    return 0;
    }
  }

