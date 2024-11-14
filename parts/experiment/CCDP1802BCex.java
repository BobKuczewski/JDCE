package parts.experiment;
import parts.*;

public class CCDP1802BCex extends Ic {

  // Define pin numbers for this part
  final int pCLK = 1;
  final int pnWAIT = 2;
  final int pnCLEAR = 3;
  final int pQ = 4;
  final int pSC1 = 5;
  final int pSC0 = 6;
  final int pnMRD = 7;
  final int pDB7 = 8;
  final int pDB6 = 9;
  final int pDB5 = 10;
  final int pDB4 = 11;
  final int pDB3 = 12;
  final int pDB2 = 13;
  final int pDB1 = 14;
  final int pDB0 = 15;
  final int pVCC = 16;
  final int pN2 = 17;
  final int pN1 = 18;
  final int pN0 = 19;
  final int pVSS = 20;
  final int pnEF4 = 21;
  final int pnEF3 = 22;
  final int pnEF2 = 23;
  final int pnEF1 = 24;
  final int pMA0 = 25;
  final int pMA1 = 26;
  final int pMA2 = 27;
  final int pMA3 = 28;
  final int pMA4 = 29;
  final int pMA5 = 30;
  final int pMA6 = 31;
  final int pMA7 = 32;
  final int pTPB = 33;
  final int pTPA = 34;
  final int pnMWR = 35;
  final int pnINT = 36;
  final int pnDMAOUT = 37;
  final int pnDMAIN = 38;
  final int pnXTAL = 39;
  final int pVDD = 40;
  
  int lastCLK = 0;
  boolean in_init = true;
  int clock_num = 0;

  public CCDP1802BCex() {
    super();
    name = new String("CDP1802BCex");
    pin  = new int[43]; // code below uses 42 ... [41]; // Arrays start at 0, but pins start at 1, so leave room
    lastCLK = 0;
    pin[pTPA] = 0;
    pin[pTPB] = 0;
    System.out.println ( "Created a " + name );
  }

  public int numPins() {
    return 40;
  }

  public int pinOut(int pn) {
    if (pn>=pDB7 && pn<=pDB0) {
      if (pin[42]==4) {
        return 129;
      } else {
        return 128;
      }
    } else if ((pn>=pQ && pn<=pnMRD) || (pn>=pN2 && pn<=pN0) || (pn>=pMA0 && pn<=pnMWR)) {
      return 1;
    } else {
      return 0;
    }
  }

  public int run() {
    boolean rising_edge = false;
    boolean falling_edge = false;
    if ( (pin[pCLK] != 0) && (lastCLK == 0) ) rising_edge = true;
    if ( (pin[pCLK] == 0) && (lastCLK != 0) ) falling_edge = true;
    lastCLK = pin[pCLK];

    if (rising_edge) {
      // This is a rising clock edge
      System.out.println ( "Rising Edge" );
      if (in_init) {
        // Do nothing
      } else {
        // Check for TPB which changes on a rising edge
        if (clock_num == 6) {
          pin[pTPB] = 1;
        } else if (clock_num == 7) {
          pin[pTPB] = 0;
        }
      }
    } else if (falling_edge) {
      // This is a falling clock edge
      System.out.println ( "Falling Edge" );
      if (in_init) {
        clock_num += 1;
        if (clock_num > 9) {
          in_init = false;
          clock_num = 0;
        }
      } else {
        // Check for TPA which changes on a falling edge
        if (clock_num == 1) {
          pin[pTPA] = 1;
        } else if (clock_num == 2) {
          pin[pTPA] = 0;
        }
        clock_num += 1;
      }
    }
    if (pin[pCLK] == 0) {
      pin[pnXTAL] = 1;
    } else {
      pin[pnXTAL] = 0;
    }

    return 0;
  }
}
