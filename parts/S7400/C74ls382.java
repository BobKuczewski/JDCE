package parts.S7400;
import parts.*;
public class C74ls382 extends Ic {
  public C74ls382() {
    super();
    name=new String("74LS382");
    pin=new int[41];
    }
  public int numPins() {
    return 20;
    }
  public int pinOut(int pn) {
    if ((pn>=11 && pn<=14) || pn==8 || pn==9) return 1; else return 0;
    }
  public int run() {
    int mode=382;
    int s1,s2,s3,s4,s5,s6,s7,cn;
    int a0,a1,a2,a3,ia0,ia1,ia2,ia3;
    int b0,b1,b2,b3,ib0,ib1,ib2,ib3;
    int i1,i2,i3,i4,i5,i6,i7,i8;
    int ii1,ii2;
    s1=f_nor3(f_and3(f_inv(pin[7]),f_inv(pin[6]),pin[5]),
               f_and3(f_inv(pin[7]),pin[6],f_inv(pin[5])),
               f_and3(pin[7],pin[6],pin[5]));
    s2=f_nor3(f_and(f_inv(pin[6]),pin[5]),
               f_and(pin[7],pin[5]),
               f_and(pin[6],f_inv(pin[5])));
    s3=f_nor(f_and(pin[6],pin[5]),
              f_and(pin[7],f_inv(pin[6])));
    s4=f_nand3(f_inv(pin[7]),f_inv(pin[6]),pin[5]);
    s5=f_nand3(f_inv(pin[7]),pin[6],pin[5]);
    s6=f_nand3(f_inv(pin[7]),pin[6],f_inv(pin[5]));
    s7=f_or(f_and(f_inv(pin[7]),pin[5]),
             f_and(f_inv(pin[7]),pin[6]));
  
    ia0=f_inv(pin[3]); a0=f_inv(ia0);
    ia1=f_inv(pin[1]); a1=f_inv(ia1);
    ia2=f_inv(pin[19]); a2=f_inv(ia2);
    ia3=f_inv(pin[17]); a3=f_inv(ia3);
  
    ib0=f_inv(pin[4]); b0=f_inv(ib0);
    ib1=f_inv(pin[2]); b1=f_inv(ib1);
    ib2=f_inv(pin[18]); b2=f_inv(ib2);
    ib3=f_inv(pin[16]); b3=f_inv(ib3);
  
    if (pin[15]!=0)  cn=1; else cn=0;
    i1=f_nor4(f_and3(s3,a0,ib0),f_and3(s2,a0,b0),
               f_and3(s3,ia0,b0),f_and3(s1,ia0,ib0));
    i2=f_nor4(f_and3(s6,a0,ib0),f_and3(s5,a0,b0),
               f_and3(s4,ia0,b0),f_and(ia0,ib0));
  
    i3=f_nor4(f_and3(s3,a1,ib1),f_and3(s2,a1,b1),
               f_and3(s3,ia1,b1),f_and3(s1,ia1,ib1));
    i4=f_nor4(f_and3(s6,a1,ib1),f_and3(s5,a1,b1),
               f_and3(s4,ia1,b1),f_and(ia1,ib1));
  
    i5=f_nor4(f_and3(s3,a2,ib2),f_and3(s2,a2,b2),
               f_and3(s3,ia2,b2),f_and3(s1,ia2,ib2));
    i6=f_nor4(f_and3(s6,a2,ib2),f_and3(s5,a2,b2),
               f_and3(s4,ia2,b2),f_and(ia2,ib2));
  
    i7=f_nor4(f_and3(s3,a3,ib3),f_and3(s2,a3,b3),
               f_and3(s3,ia3,b3),f_and3(s1,ia3,ib3));
    i8=f_nor4(f_and3(s6,a3,ib3),f_and3(s5,a3,b3),
               f_and3(s4,ia3,b3),f_and(ia3,ib3));
  
    pin[8]=f_xnor(i1,f_nand(s7,cn));
    pin[9]=f_xnor(i3,f_nor(f_and3(s7,cn,i1),f_and(s7,i2)));
    pin[11]=f_xnor(i5,f_nor3(f_and4(s7,cn,i1,i3),
                      f_and3(s7,i3,i2),f_and(s7,i4)));
    ii1=f_nor4(f_and5(s7,cn,i1,i3,i5),
                f_and4(s7,i3,i5,i2),
                f_and3(s7,i5,i4),f_and(s7,i6));
    pin[12]=f_xnor(ii1,i7);
    if (mode==382)
      {
        ii2=f_nor5(f_and5(cn,i1,i3,i5,i7),
                    f_and4(i3,i5,i7,i2),
                    f_and3(i5,i7,i4),
                    f_and(i7,i6),
                    i8);
        pin[13]=f_xor(ii1,ii2);
        pin[14]=f_inv(ii2);
     }
    if (mode==381)
      {
        pin[14]=f_nand4(i1,i3,i5,i7);
        pin[13]=f_nor4(f_and4(i3,i5,i7,i2),
                           f_and3(i5,i7,i4),
                           f_and(i7,i6),
                           i8);
     }
    return 0;
    }
  }

