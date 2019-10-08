package parts.S7400;
import parts.*;
public class C74177 extends Ic {
  public C74177() {
    super();
    name=new String("74177");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==5 || pn==9 || pn==12) return 1; else return 0;
    }
  public int run() {
    int max=16;
    if (pin[13]==0)
      {
        pin[20]=0;
        pin[5]=0;
     }
   else
      {
        if (pin[1]==0)
          {
            if (pin[4]!=0)  pin[5]=1; else pin[5]=0;
            if (pin[10]!=0)  pin[20]=1; else pin[20]=0;
            if (pin[3]!=0)  pin[20]+=2;
            if (pin[11]!=0)  pin[20]+=4;
         }
       else
          {
            if ((pin[8]==0) && (pin[21]!=0))
              {
                if (pin[5]==0)  pin[5]=1; else pin[5]=0;
             }
            if ((pin[6]==0) && (pin[22]!=0))
              {
                pin[20]++;
                if (pin[20]==(max / 2))  pin[20]=0;
             }
         }
     }
    if ((pin[20] & 1)==1)  pin[9]=1; else pin[9]=0;
    if ((pin[20] & 2)==2)  pin[2]=1; else pin[2]=0;
    if ((pin[20] & 4)==4)  pin[12]=1; else pin[12]=0;
    pin[21]=pin[8];
    pin[22]=pin[6];
    return 0;
    }
  }

