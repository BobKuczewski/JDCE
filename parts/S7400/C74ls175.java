package parts.S7400;
import parts.*;
public class C74ls175 extends Ic {
  public C74ls175() {
    super();
    name=new String("74LS175");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==3 || pn==6 || pn==7 || pn==10 || pn==11 ||
        pn==14 || pn==15) return 1; else return 0;
    }
  public int run() {
    if (pin[1]==0)
      {
        pin[2]=0; pin[3]=1;
        pin[7]=0; pin[6]=1;
        pin[10]=0; pin[11]=1;
        pin[15]=0; pin[14]=1;
     }
   else
      {
        if ((pin[9]!=0) && (pin[20]==0))
          {
            if (pin[4]!=0)
              {
                pin[2]=1;
                pin[3]=0;
             }
           else
              {
                pin[2]=0;
                pin[3]=1;
             }
            if (pin[5]!=0)
              {
                pin[7]=1;
                pin[6]=0;
             }
           else
              {
                pin[7]=0;
                pin[6]=1;
             }
            if (pin[12]!=0)
              {
                pin[10]=1;
                pin[11]=0;
             }
           else
              {
                pin[10]=0;
                pin[11]=1;
             }
            if (pin[13]!=0)
              {
                pin[15]=1;
                pin[14]=0;
             }
           else
              {
                pin[14]=0;
                pin[14]=1;
             }
         }
     }
    pin[20]=pin[9];
    return 0;
    }
  }

