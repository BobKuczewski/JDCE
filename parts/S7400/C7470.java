package parts.S7400;
import parts.*;
public class C7470 extends Ic {
  public C7470() {
    super();
    name=new String("7470");
    pin=new int[40];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==8) return 1; else return 0;
    }
  public int run() {
    int i;
    if ((pin[2]==0) && (pin[13]==0)) 
      {
        pin[6]=0;
        pin[8]=0;
     }
    if ((pin[13]!=0) && (pin[2]==0) && (pin[12]==0)) 
      {
        pin[6]=1;
        pin[8]=0;
     }
    if ((pin[13]==0) && (pin[2]!=0) && (pin[12]==0)) 
      {
        pin[6]=0;
        pin[8]=1;
     }
    if ((pin[13]!=0) && (pin[2]!=0)) 
      {
        if ((pin[12]!=0) && (pin[20]==0)) 
          {
            if ((pin[3]==0) || (pin[4]==0) || (pin[5]!=0) &&
               (pin[10]==0) || (pin[11]==0) || (pin[9]!=0)) 
              {
             }
            if (((pin[3]!=0) && (pin[4]!=0) && (pin[5]==0)) &&
               ((pin[10]==0) || (pin[11]==0) || (pin[9]!=0))) 
              {
                pin[8]=1;
                pin[6]=0;
             }
            if (((pin[3]==0) || (pin[4]==0) || (pin[5]!=0)) &&
               ((pin[10]!=0) && (pin[11]!=0) && (pin[9]==0))) 
              {
                pin[8]=0;
                pin[6]=1;
             }
            if (((pin[3]!=0) && (pin[4]!=0) && (pin[5]==0)) &&
               ((pin[10]!=0) && (pin[11]!=0) && (pin[9]==0))) 
              {
                i=pin[8];
                pin[8]=pin[6];
                pin[6]=i;
             }
         }
     }
    pin[20]=pin[12];
    return 0;
    }
  }

