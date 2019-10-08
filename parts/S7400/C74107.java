package parts.S7400;
import parts.*;
public class C74107 extends Ic {
  public C74107() {
    super();
    name=new String("74107");
    pin=new int[41];
    }
  public int numPins() {
    return 14;
    }
  public int pinOut(int pn) {
    if (pn==2 || pn==3 || pn==5 || pn==6) return 1; else return 0;
    }
  public int run() {
    int i;
    if ((pin[13]==0)) 
      {
        pin[3]=0;
        pin[2]=1;
     }
    if ((pin[10]==0)) 
      {
        pin[5]=0;
        pin[6]=1;
     }
    if (pin[13]!=0) 
      {
        if ((pin[12]!=0) && (pin[20]==0)) 
          {
            pin[21]=pin[1];
            pin[22]=pin[4];
         }
        if ((pin[12]==0) && (pin[20]!=0)) 
          {
            if ((pin[21]!=0) && (pin[22]==0)) 
              {
                pin[3]=1;
                pin[2]=0;
             }
            if ((pin[21]==0) && (pin[22]!=0)) 
              {
                pin[3]=0;
                pin[2]=1;
             }
            if ((pin[21]!=0) && (pin[22]!=0)) 
              {
                i=pin[3];
                pin[3]=pin[2];
                pin[2]=i;
             }
         }
     }
    if (pin[10]!=0) 
      {
        if ((pin[9]!=0) && (pin[30]==0)) 
          {
            pin[31]=pin[8];
            pin[32]=pin[11];
         }
        if ((pin[9]==0) && (pin[30]!=0)) 
          {
            if ((pin[31]!=0) && (pin[32]==0)) 
              {
                pin[5]=1;
                pin[6]=0;
             }
            if ((pin[31]==0) && (pin[32]!=0)) 
              {
                pin[5]=0;
                pin[6]=1;
             }
            if ((pin[31]!=0) && (pin[32]!=0)) 
              {
                i=pin[5];
                pin[5]=pin[6];
                pin[6]=i;
             }
         }
     }
    pin[20]=pin[12];
    pin[30]=pin[9];
    return 0;
    }
  }

