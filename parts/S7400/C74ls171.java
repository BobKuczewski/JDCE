package parts.S7400;
import parts.*;
public class C74ls171 extends Ic {
  public C74ls171() {
    super();
    name=new String("74LS171");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if ((pn>=1 && pn<=3) || pn==6 | pn==7 || pn==9 || pn==10 ||
        pn==15) return 1; else return 0;
    }
  public int run() {
    if (pin[13]==0)
      {
        pin[15]=0; pin[1]=1;
        pin[3]=0; pin[2]=1;
        pin[6]=0; pin[7]=1;
        pin[10]=0; pin[9]=1;
     }
    else
      {
        if ((pin[12]!=0) && (pin[20]==0))
          {
            if (pin[14]!=0)
              {
                pin[15]=1;
                pin[1]=0;
             }
            else
              {
                pin[15]=0;
                pin[1]=1;
             }
            if (pin[4]!=0)
              {
                pin[3]=1;
                pin[2]=0;
             }
            else
              {
                pin[3]=0;
                pin[2]=1;
             }
            if (pin[5]!=0)
              {
                pin[6]=1;
                pin[7]=0;
             }
            else
              {
                pin[6]=0;
                pin[7]=1;
             }
            if (pin[11]!=0)
              {
                pin[10]=1;
                pin[9]=0;
             }
            else
              {
                pin[10]=0;
                pin[9]=1;
             }
         }
     }
    pin[20]=pin[12];
    return 0;
    }
  }

