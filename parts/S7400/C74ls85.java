package parts.S7400;
import parts.*;
public class C74ls85 extends Ic {
  public C74ls85() {
    super();
    name=new String("74LS85");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==5 || pn==6 || pn==7) return 1; else return 0;
    }
  public int run() {
    int i,j;
    if (pin[10]!=0)  i=1; else i=0;
    if (pin[12]!=0)  i+=2;
    if (pin[13]!=0)  i+=4;
    if (pin[15]!=0)  i+=8;
    if (pin[9]!=0)  j=1; else j=0;
    if (pin[11]!=0)  j+=2;
    if (pin[14]!=0)  j+=4;
    if (pin[1]!=0)  j+=8;
    if (i>j) 
      {
        pin[5]=1;
        pin[6]=0;
        pin[7]=0;
     }
    if (i<j) 
      {
        pin[5]=0;
        pin[6]=0;
        pin[7]=1;
     }
    if (i==j) 
      {
        if (pin[3]!=0) 
          {
            pin[5]=0;
            pin[6]=1;
            pin[7]=0;
          }
        else
          {
            if ((pin[4]!=0) && (pin[2]==0)) 
              {
                pin[5]=1;
                pin[6]=0;
                pin[7]=0;
             }
            if ((pin[4]==0) && (pin[2]!=0)) 
              {
                pin[5]=0;
                pin[6]=0;
                pin[7]=1;
             }
            if ((pin[4]!=0) && (pin[2]!=0)) 
              {
                pin[5]=0;
                pin[6]=0;
                pin[7]=0;
             }
            if ((pin[4]==0) && (pin[2]==0)) 
              {
                pin[5]=1;
                pin[6]=0;
                pin[7]=1;
             }
         }
     }
    return 0;
    }
  }

