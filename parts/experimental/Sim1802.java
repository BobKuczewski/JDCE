package parts.experimental;
import parts.*;
import shared.*;

/**
* This class/part aspires to be a clock-for-clock simulation
* of a CDP1802BC microprocessor. At this time, it only
* generates /XTAL, TPA, TPB, and SC0.
*
* </pre>
*/

class pin {
  int value;
  public pin() {
    value = 0;
  }
}

public class Sim1802 extends Ic {

  pin p;

  // Define pin numbers for this part
  public final static int pCLK = 1;
  public final static int pnWAIT = 2;
  public final static int pnCLEAR = 3;
  public final static int pQ = 4;
  public final static int pSC1 = 5;
  public final static int pSC0 = 6;
  public final static int pnMRD = 7;
  public final static int pDB7 = 8;
  public final static int pDB6 = 9;
  public final static int pDB5 = 10;
  public final static int pDB4 = 11;
  public final static int pDB3 = 12;
  public final static int pDB2 = 13;
  public final static int pDB1 = 14;
  public final static int pDB0 = 15;
  public final static int pVCC = 16;
  public final static int pN2 = 17;
  public final static int pN1 = 18;
  public final static int pN0 = 19;
  public final static int pVSS = 20;
  public final static int pnEF4 = 21;
  public final static int pnEF3 = 22;
  public final static int pnEF2 = 23;
  public final static int pnEF1 = 24;
  public final static int pMA0 = 25;
  public final static int pMA1 = 26;
  public final static int pMA2 = 27;
  public final static int pMA3 = 28;
  public final static int pMA4 = 29;
  public final static int pMA5 = 30;
  public final static int pMA6 = 31;
  public final static int pMA7 = 32;
  public final static int pTPB = 33;
  public final static int pTPA = 34;
  public final static int pnMWR = 35;
  public final static int pnINT = 36;
  public final static int pnDMAOUT = 37;
  public final static int pnDMAIN = 38;
  public final static int pnXTAL = 39;
  public final static int pVDD = 40;
  public final static int nPins = 40;

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

  /**
  * <pre>
  * This method is called by JDCE to build this part.
  * </pre>
  */
  public Sim1802() {
    super();
    name = new String("CDP1802BCex");
    pin  = new int[43]; // code below uses 42 ... [41]; // Arrays start at 0, but pins start at 1, so leave room
    lastCLK = 0;
    pin[pTPA] = 0;
    pin[pTPB] = 0;
    println ( "Created a " + name );
  }

  /**
  * <pre>
  * This method is called by JDCE to find out how many pins this part has.
  * </pre>
  */
  public int numPins() {
    return 40;
  }

  /**
  * This method is called by JDCE to figure out which pins are outputs.
  * This method should return a 1 for output pins and 0 otherwise.
  */
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

  /**
  * This method is called by JDCE during simulation.
  * <p/>
  * Note that this method may be called many times during
  * a single clock cycle.
  */
  public int run() {
    // Run one "iteration" (half clock) of the 1802
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
