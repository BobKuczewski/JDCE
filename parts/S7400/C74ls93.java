package parts.S7400;
import parts.*;
public class C74ls93 extends Ic {
  public C74ls93() {
    super();
    name=new String("74LS93");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==8 || pn==9 || pn==11 | pn==12) return 1; else return 0;
    }
  public int run() {
    int rst;
    int max=16;
    rst=0;
    if ((pin[2]!=0) && (pin[3]!=0)) 
      {
        pin[21]=0;
        pin[12]=0;
        rst=1;
     }
    if (max==10) 
      if ((pin[6]!=0) && (pin[7]!=0)) 
        {
          pin[21]=4;
          pin[12]=1;
          rst=1;
       }
    if (rst==0) 
      {
        if ((pin[14]==0) && (pin[20]!=0)) 
          if (pin[12]==0)  pin[12]=1; else pin[12]=0;
        if ((pin[1]==0) && (pin[30]!=0)) 
          {
            pin[21]++;
            if (pin[21]==(max / 2)) 
              pin[21]=0;
         }
     }
    pin[20]=pin[14];
    pin[30]=pin[1];
    if ((pin[21] & 4)==4)  pin[11]=1; else pin[11]=0;
    if ((pin[21] & 2)==2)  pin[8]=1; else pin[8]=0;
    if ((pin[21] & 1)==1)  pin[9]=1; else pin[9]=0;
    return 0;
    }
  }

