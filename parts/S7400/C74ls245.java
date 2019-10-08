package parts.S7400;
import parts.*;
public class C74ls245 extends Ic {
  public C74ls245() {
    super();
    name=new String("74LS245");
    pin=new int[41];
    }
  public int numPins() {
    return 20;
    }
  public int pinOut(int pn) {
    if (pin[1]==0)
      {
        if (pn>=2 && pn<=9) return 129; else return 128;
      }
    else
      {
        if (pn>=11 && pn<=18) return 129; else return 128;
     }
    }
  public int run() {
    int i;
    if (pin[19]!=0)
      {
        for (i=2;i<=9;i++) pin[i]=2;
        for (i=11;i<=18;i++) pin[i]=2;
     }
   else
      {
        if (pin[1]==0)
          {
            for (i=1;i<=8;i++)
              if (pin[19-i]!=0)  pin[1+i]=1; else pin[1+i]=0;
         }
       else
          {
            for (i=1;i<=8;i++)
              if (pin[1+i]!=0)  pin[19-i]=1; else pin[19-i]=0;
         }
     }
    return 0;
    }
  }

