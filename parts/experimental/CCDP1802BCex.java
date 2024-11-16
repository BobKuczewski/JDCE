package parts.experimental;
import parts.*;
import shared.*;

/**
* <pre>
* This class/part provides an interface between JDCE
* and the Sim1802 class.
* </pre>
*/

public class CCDP1802BCex extends Ic {

  Sim1802 sim;

  int lastCLK = 0;
  boolean in_init = true;
  int clk = 14;   // Currently counts half clocks, initialized for proper phasing (not currently counting init)

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
  public CCDP1802BCex() {
    super();
    sim = new Sim1802();
    name = new String("CDP1802BCex");
    pin  = new int[43]; // code below uses 42 ... [41]; // Arrays start at 0, but pins start at 1, so leave room
    lastCLK = 0;
    pin[sim.pTPA] = 0;
    pin[sim.pTPB] = 0;
    println ( "Created a " + name );
  }

  /**
  * <pre>
  * This method is called by JDCE to find out how many pins this part has.
  * </pre>
  */
  public int numPins() {
    return sim.num_pins;
  }

  /**
  * This method is called by JDCE to figure out which pins are outputs.
  * This method should return a 1 for output pins and 0 otherwise.
  */
  public int pinOut(int pn) {
    // What does this section do? What is pin[42]? What are return codes 128 and 129?
    if (pn>=sim.pDB7 && pn<=sim.pDB0) {
      if (pin[42]==4) {
        return 129;
      } else {
        return 128;
      }
    } else {
      // Check if this is an output pin and return 1 if it is.
      for (int i=0; i<sim.outpins.length; i++) {
        if (pn == sim.outpins[i]) {
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

    if (Globals.debug_enabled) {
      sim.enable_debug();
    } else {
      sim.disable_debug();
    }

    // Check for a clock edge ... everything happens on a clock edge
    if ( (pin[sim.pCLK] != 0) && (lastCLK == 0) ) rising_edge = true;
    if ( (pin[sim.pCLK] == 0) && (lastCLK != 0) ) falling_edge = true;
    if (rising_edge || falling_edge) {
      for (int p=0; p<=sim.num_pins; p++) {
        sim.pin[p] = pin[p];
      }
      sim.run_half_clock();
      for (int p=0; p<=sim.num_pins; p++) {
        pin[p] = sim.pin[p];
      }
    }
    lastCLK = pin[sim.pCLK];

    return 0;
  }
}
