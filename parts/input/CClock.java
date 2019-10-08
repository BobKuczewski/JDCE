package parts.input;
import parts.*;
public class CClock extends Ic {
  int phase9,phase10,phase11,phase12;
  int phase13,phase14,phase15,phase16;
  public CClock() {
    super();
    name=new String("Clock");
    pin=new int[41];
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    return 1;
    }
  public void reset() {
    int i;
    for (i=1;i<17;i++) pin[i]=0;
    phase9=0; phase10=0; phase11=0; phase12=0;
    phase13=0; phase14=0; phase15=0; phase16=0;
    }
  public int run() {
    if (pin[1]!=0) {
      pin[1]=0;
      if (pin[2]!=0) {
        pin[2]=0;
        if (pin[3]!=0) {
          pin[3]=0;
          if (pin[4]!=0) {
            pin[4]=0;
            if (pin[5]!=0) {
              pin[5]=0;
              if (pin[6]!=0) {
                pin[6]=0;
                if (pin[7]!=0) {
                  pin[7]=0;
                  if (pin[8]!=0) {
                    pin[8]=0;
                    } else pin[8]=1;
                  } else pin[7]=1;
                } else pin[6]=1;
              } else pin[5]=1;
            } else pin[4]=1;
          } else pin[3]=1;
        } else pin[2]=1;
      } else pin[1]=1;
    if (pin[9]==0) {
      pin[9]=1; phase9=0;
      } else if (phase9++==1) {
      pin[9]=0;
      if (pin[10]==0) {
        pin[10]=1; phase10=0;
        } else if (phase10++==1) {
        pin[10]=0;
        if (pin[11]==0) {
          pin[11]=1; phase11=0;
          } else if (phase11++==1) {
          pin[11]=0;
          if (pin[12]==0) {
            pin[12]=1; phase12=0;
            } else if (phase12++==1) {
            pin[12]=0;
            }
          }
        }
      }
    if (pin[13]!=0) {
      pin[13]=0; phase13=0;
      } else if (phase13++==1) {
      pin[13]=1;
      if (pin[14]!=0) {
        pin[14]=0; phase14=0;
        } else if (phase14++==1) {
        pin[14]=1;
        if (pin[15]!=0) {
          pin[15]=0; phase15=0;
          } else if (phase15++==1) {
          pin[15]=1;
          if (pin[16]!=0) {
            pin[16]=0; phase16=0;
            } else if (phase16++==1) {
            pin[16]=1;
            }
          }
        }
      }
    return 0;
    }
  }
