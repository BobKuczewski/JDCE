package parts.experiment;
import parts.*;
import shared.*;

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
  final int nPins = 40;

  // Define the output pins
  final int outpins[] = {pQ,pSC1,pSC0,pnMRD,pN2,pN1,pN0,pMA0,pMA1,pMA2,pMA3,pMA4,pMA5,pMA6,pMA7,pTPB,pTPA,pnMWR,pnXTAL};

  int lastCLK = 0;
  boolean in_init = true;
  int clk = 14;        // Currently counts half clocks, initialized for proper phasing (not currently counting init)

  // Helper function for debugging
  void println ( String s ) {
    if (Globals.debug_enabled) {
      System.out.println ( s );
    }
  }

  public CCDP1802BCex() {
    super();
    name = new String("CDP1802BCex");
    pin  = new int[43]; // code below uses 42 ... [41]; // Arrays start at 0, but pins start at 1, so leave room
    lastCLK = 0;
    pin[pTPA] = 0;
    pin[pTPB] = 0;
    println ( "Created a " + name );
  }

  // Return the number of pins for this part
  public int numPins() {
    return 40;
  }

  public int pinOut(int pn) {
    // What does this section do? What is pin[42]? What are return codes 128 and 129?
    if (pn>=pDB7 && pn<=pDB0) {
      if (pin[42]==4) {
        return 129;
      } else {
        return 128;
      }
    } else {
      // Check if this is an output pin and return 1 if it is.
      for (int i=0; i<outpins.length; i++) {
        if (pn == outpins[i]) {
          return 1;
        }
      }
      // Otherwise return 0 (not an output pin)
      return 0;
    }
  }

  // Run one "iteration" of this code
  public int run() {
    boolean rising_edge = false;
    boolean falling_edge = false;

    // Check for a clock edge ... everything happens on a clock edge
    if ( (pin[pCLK] != 0) && (lastCLK == 0) ) rising_edge = true;
    if ( (pin[pCLK] == 0) && (lastCLK != 0) ) falling_edge = true;
    lastCLK = pin[pCLK];
    for (int i=0; i<outpins.length; i++) {
      if (pin[outpins[i]] == 2) {
        pin[outpins[i]] = 0;
      }
    }
    if (rising_edge || falling_edge) {

      if (rising_edge) {
        // This is a rising clock edge
        println ( "Rising Edge of clock " + clk );
      } else if (falling_edge) {
        // This is a falling clock edge
        println ( "Falling Edge of clock " + clk );
      }

      // Set TPA and TPB as needed
      if        ((clk%16) == 1) {
        pin[pTPA] = 1;
      } else if ((clk%16) == 3) {
        pin[pTPA] = 0;
      } else if ((clk%16) == 12) {
        pin[pTPB] = 1;
      } else if ((clk%16) == 14) {
        pin[pTPB] = 0;
      }
      // Toggle SC0 as needed
      if ((clk%16) == 15) {
        pin[pSC0] = 1 - pin[pSC0];
      }

      // Print the state if debugging is enabled
      println ( "TPA=" + pin[pTPA] + ", TPB=" + pin[pTPB] + ", SC0=" + pin[pSC0] );

      // Advance the clock by half a cycle
      clk = clk + 1;
    }

    // Output to the nXTAL pin (opposite of CLK)
    if (pin[pCLK] == 0) {
      pin[pnXTAL] = 1;
    } else {
      pin[pnXTAL] = 0;
    }

    return 0;
  }
}
