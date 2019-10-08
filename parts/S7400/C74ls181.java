package parts.S7400;
import parts.*;
public class C74ls181 extends Ic {
  public C74ls181() {
    super();
    name=new String("74LS181");
    pin=new int[41];
    }
  public int numPins() {
    return 24;
    }
  public int pinOut(int pn) {
    if ((pn>=9 && pn<=11) || (pn>=13 && pn<=17)) return 1; else return 0;
    }
  public int run() {
    pin[30]=f_nor(f_and3(pin[18],pin[3],pin[19]),
                      f_and3(pin[19],pin[4],f_inv(pin[18])));
    pin[31]=f_nor3(f_and(f_inv(pin[18]),pin[5]),
                       f_and(pin[6],pin[18]),
                       pin[19]);
    pin[32]=f_nor(f_and3(pin[20],pin[3],pin[21]),
                      f_and3(pin[21],pin[4],f_inv(pin[20])));
    pin[33]=f_nor3(f_and(f_inv(pin[20]),pin[5]),
                       f_and(pin[6],pin[20]),
                       pin[21]);
    pin[34]=f_nor(f_and3(pin[22],pin[3],pin[23]),
                      f_and3(pin[23],pin[4],f_inv(pin[22])));
    pin[35]=f_nor3(f_and(f_inv(pin[22]),pin[5]),
                       f_and(pin[6],pin[22]),
                       pin[23]);
    pin[36]=f_nor(f_and3(pin[1],pin[3],pin[2]),
                      f_and3(pin[2],pin[4],f_inv(pin[1])));
    pin[37]=f_nor3(f_and(f_inv(pin[1]),pin[5]),
                       f_and(pin[6],pin[1]),
                       pin[2]);
  
    pin[9]=f_xor(f_xor(pin[36],pin[37]),
                     f_nand(pin[7],f_inv(pin[8])));
    pin[10]=f_xor(f_xor(pin[34],pin[35]),
                      f_nor(f_and3(pin[7],pin[36],f_inv(pin[8])),
                            f_and(pin[37],f_inv(pin[8]))));
    pin[11]=f_xor(f_xor(pin[32],pin[33]),
                      f_nor3(f_and4(pin[7],pin[36],pin[34],f_inv(pin[8])),
                             f_and3(pin[34],pin[37],f_inv(pin[8])),
                             f_and(pin[35],f_inv(pin[8]))));
    pin[13]=f_xor(f_xor(pin[30],pin[31]),
                      f_nor4(f_and5(pin[7],pin[36],pin[34],pin[32],f_inv(pin[8])),
                             f_and4(pin[34],pin[32],pin[37],f_inv(pin[8])),
                             f_and3(pin[32],pin[35],f_inv(pin[8])),
                             f_and(pin[33],f_inv(pin[8]))));
    pin[14]=f_and4(pin[13],pin[11],pin[10],pin[9]);
  
    pin[15]=f_nand4(pin[30],pin[32],pin[34],pin[36]);
    pin[17]=f_nor4(f_and4(pin[30],pin[32],pin[34],pin[37]),
                       f_and3(pin[30],pin[32],pin[35]),
                       f_and(pin[30],pin[33]),
                       pin[31]);
    pin[16]=f_nand(f_nand5(pin[30],pin[32],pin[34],pin[36],pin[7]),
                       pin[17]);
    return 0;
    }
  }

