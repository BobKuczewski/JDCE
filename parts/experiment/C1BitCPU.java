package parts.experiment;
import parts.*;

/**
* <pre>
* This is a simple 1 bit processor with 2 instructions:
*   0 = REQ: Reset the Q output
*   1 = SEQ: Set the Q output
*
* This machine has 2 states: 0=Fetch, 1=Execute
*
* There are no branching instructions.
* The 3 address lines just continually count (and wrap).
*
* Typical configuration:
*
*   The clock input is connected to a Clock and an LED.
*   The Q output of the processor is connected to an LED.
*   Memory is a 2708 EPROM with exactly 8 instructions.
*   A typical program might be: REQ SEQ REQ SEQ SEQ REQ SEQ SEQ
*   Since there is only one bit of instruction, but the 2708 has 8,
*   all 8 output bits are typically programmed with the same value:
*
*    :0000 00   ; REQ
*    :0001 FF   ; SEQ
*    :0002 00   ; REQ
*    :0003 FF   ; SEQ
*    :0004 FF   ; SEQ
*    :0005 00   ; REQ
*    :0006 FF   ; SEQ
*    :0007 FF   ; SEQ
*
* Pin Diagram of the processor:
*
*    Clock 1 -==-==- 8 Power
*     Data 2 -=====- 7 A2
*        Q 3 -=====- 6 A1
*   Ground 4 -=====- 5 A0
* </pre>
*/

public class C1BitCPU extends Ic {
  // Define some constants to make the code readable!

  static int CLK = 1;  // External Pin: Clock
  static int DAT = 2;  // External Pin: Data "Bus"
  static int Q   = 3;  // External Pin: Q Output
  static int A0  = 5;  // External Pin: Address 0
  static int A1  = 6;  // External Pin: Address 1
  static int A2  = 7;  // External Pin: Address 2

  static int PC  = 23;  // Internal: Program Counter
  static int SC  = 24;  // Internal: State Code
  static int LCK = 25;  // Internal: Last Clock (for edge trigger)

  /**
  * <pre>
  * This method is called by JDCE to build this part.
  *
  * Create a 1 bit CPU of type "C1BitCPU" with 8 pins:
  *
  *   CLK 1 -====- 8 (pwr)
  *   DAT 2 -====- 7 A2
  *     Q 3 -====- 6 A1
  * (gnd) 4 -====- 5 A0
  *
  * The processor will have access to each pin through
  * through the superclass "pin" variable:
  *
  *   pin[CLK], pin[DAT], pin[Q], ...
  * </pre>
  */
  public C1BitCPU() {
    super();
    //// System.out.println ( "Top of constructor" );
    name=new String("C1BitCPU");
    pin=new int[41]; // These must be the extra pins available for storage?
    pin[SC] = 0;     // State Code: 0=Set addresses on bus, 1=Read instruction and execute
    pin[PC] = 0;     // Program Counter
    pin[A2] = 0;     // Address Line 2
    pin[A1] = 0;     // Address Line 1
    pin[A0] = 0;     // Address Line 0
    pin[Q]  = 0;     // Q Output
    //// System.out.println ( "Done with constructor" );
  }

  /**
  * <pre>
  * This method is called by JDCE to find out how many pins this part has.
  * </pre>
  */
  public int numPins() {
    //// System.out.println ( "numPins called" );
    return 8;        // This is the actual number of pins
  }

  /**
  * This method is called by JDCE to figure out which pins are outputs.
  * This method should return a 1 for output pins and 0 otherwise.
  */
  public int pinOut(int pn) {
    //// System.out.println ( "pinOut called for pin " + pn );
    if (pn==3 || pn==5 || pn==6 || pn==7) {
      return 1;
    } else {
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
    //// System.out.println ( "run with SC = " + pin[SC] + ", PC = " + pin[PC] );

    // Check for edge trigger when clock is low and had been high
    if (pin[CLK]==0 && pin[LCK]!=0) {

      // This must be a falling clock edge so execute
      if (pin[SC] == 0) {
        //// System.out.println ( "Clock triggered in state 0 (fetch) with PC = " + pin[PC] );
        // Put the PC on the address bus to read next instruction
        if      (pin[PC] == 0) { pin[A2]=0; pin[A1]=0; pin[A0]=0; }
        else if (pin[PC] == 1) { pin[A2]=0; pin[A1]=0; pin[A0]=1; }
        else if (pin[PC] == 2) { pin[A2]=0; pin[A1]=1; pin[A0]=0; }
        else if (pin[PC] == 3) { pin[A2]=0; pin[A1]=1; pin[A0]=1; }
        else if (pin[PC] == 4) { pin[A2]=1; pin[A1]=0; pin[A0]=0; }
        else if (pin[PC] == 5) { pin[A2]=1; pin[A1]=0; pin[A0]=1; }
        else if (pin[PC] == 6) { pin[A2]=1; pin[A1]=1; pin[A0]=0; }
        else if (pin[PC] == 7) { pin[A2]=1; pin[A1]=1; pin[A0]=1; }
        else                   { System.out.println ( "Bad Program Counter: " + pin[PC] ); }

        pin[SC] = 1;    // Transition to state 1

      } else if (pin[SC] == 1) {
        //// System.out.println ( "Clock triggered in state 1 (execute) with PC = " + pin[PC] );
        // Get the instruction from the bus and execute it
        int instr = pin[DAT];
        if (instr == 0) {           // REQ Instruction
          //// System.out.println ( "  Execute " + instr + " = REQ" );
          pin[Q] = 0;
        } else {                    // SEQ Instruction
          //// System.out.println ( "  Execute " + instr + " = SEQ" );
          pin[Q] = 1;
        }
        pin[SC] = 0;                // Transition to state 0
        pin[PC] = (pin[PC]+1) % 8;  // Increment the PC and loop
          
      } else {
        //// System.out.println ( "Unexpected State Code: " + pin[SC] );
      }

    }
    pin[LCK]=pin[CLK];  // End the edge trigger condition
    return 0;
  }
}

