package parts.S7400;
import parts.*;
public class C74111 extends Ic {
  public C74111() {
    super();
    name=new String("74111");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==6 || pn==7 || pn==9 || pn==10) return 1; else return 0;
    }
  public int run() {
    int i;
    if ((pin[2]==0) && (pin[3]!=0)) 
      {
        pin[7]=1;
        pin[6]=0;
     }
    if ((pin[2]!=0) && (pin[3]==0)) 
      {
        pin[7]=0;
        pin[6]=1;
     }
    if ((pin[2]==0) && (pin[3]==0)) 
      {
        pin[7]=1;
        pin[6]=1;
     }
    if ((pin[14]==0) && (pin[13]!=0)) 
      {
        pin[9]=1;
        pin[10]=0;
     }
    if ((pin[14]!=0) && (pin[13]==0)) 
      {
        pin[9]=0;
        pin[10]=1;
     }
    if ((pin[14]==0) && (pin[13]==0)) 
      {
        pin[9]=1;
        pin[10]=1;
     }
    if ((pin[2]!=0) && (pin[3]!=0)) 
      {
        if ((pin[5]!=0) && (pin[20]==0)) 
          {
            pin[21]=pin[4];
            pin[22]=pin[1];
         }
        if ((pin[5]==0) && (pin[20]!=0)) 
          {
            if ((pin[21]!=0) && (pin[22]==0)) 
              {
                pin[7]=1;
                pin[6]=0;
             }
            if ((pin[21]==0) && (pin[22]!=0)) 
              {
                pin[7]=0;
                pin[6]=1;
             }
            if ((pin[21]!=0) && (pin[22]!=0)) 
              {
                i=pin[7];
                pin[7]=pin[6];
                pin[6]=i;
             }
         }
     }
    if ((pin[14]!=0) && (pin[13]!=0)) 
      {
        if ((pin[11]!=0) && (pin[30]==0)) 
          {
            pin[31]=pin[12];
            pin[32]=pin[15];
         }
        if ((pin[11]==0) && (pin[30]!=0)) 
          {
            if ((pin[31]!=0) && (pin[32]==0)) 
              {
                pin[9]=1;
                pin[10]=0;
             }
            if ((pin[31]==0) && (pin[32]!=0)) 
              {
                pin[9]=0;
                pin[10]=1;
             }
            if ((pin[31]!=0) && (pin[32]!=0)) 
              {
                i=pin[9];
                pin[9]=pin[10];
                pin[10]=i;
             }
         }
     }
    pin[20]=pin[5];
    pin[30]=pin[11];
    return 0;
    }
  }

