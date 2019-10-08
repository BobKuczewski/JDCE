package parts.pic;

import java.awt.*;
import parts.*;

public class C24LC256 extends Rom {
  protected char mode;
  protected int  subMode;
  protected byte status;
  protected int  address;
  protected byte currentByte;
  protected int  clock;
  protected byte frame[];
  protected int  framePos;
  protected int  ack;
  public C24LC256() {
    super();
    name2=new String("256");
    pin=new int[51];
    memory=new byte[32768];
    mode='I';
    subMode=0;
    frame=new byte[64];
    }
  public int numPins() {
    return 8;
    }
  public int pinOut(int pn) {
    if (pn==5) {
      if (mode=='R' && clock != 9) return 129;
      else if (mode!='I' && mode!='R' && clock==9) return 129;
      else return 128;
      } 
    return 0;
    }
  public int run() {
    int i;
    byte j;
    if (pin[41]!=0 && pin[6]==0) clock++;
    if (clock==10) {
      clock=1;
      if (mode == 'S') {
        if (pin[1] != 0) i=1; else i=0;
        if (pin[2] != 0) i+=2;
        if (pin[3] != 0) i+=4;
        if ((currentByte & 2) ==2) j=1; else j=0;
        if ((currentByte & 4) ==4) j+=2;
        if ((currentByte & 8) ==8) j+=4;
        if (i != j) mode='I';
        if ((currentByte & 0xa0) != 0xa0) mode='I';
        if (mode != 'I') {
          if ((currentByte & 1) ==1) mode='R';
            else {
              mode='H';
              currentByte=0;
              subMode=128;
              }
          if (mode=='R') {
            currentByte=memory[address++];
            if (address==32768) address=0;
            subMode=128;
            }
          }
        }
      else if (mode == 'H') {
        mode='L';
        address=(currentByte & 0x7f)<<8;
        currentByte=0;
        subMode=128;
        }
      else if (mode == 'L') {
        mode='W';
        address |= currentByte;
        framePos=0;
        currentByte=0;
        subMode=128;
        }
      else if (mode == 'W') {
        frame[framePos++]=currentByte;
        if (framePos==64) framePos=0;
        currentByte=0;
        subMode=128;
        }
      else if (mode == 'R') {
        if (ack==0) {
          currentByte=memory[address++];
          if (address==32768) address=0;
          subMode=128;
          }
        }
      }
    if (pin[6]!=0 && pin[45]==0 && pin[5]!=0) {
System.out.println("Stop");
      if (mode=='W') {
        for (i=0;i<framePos;i++) memory[address++]=frame[i];
        }
      mode='I';
      clock=0;
      }
    if (pin[6]!=0 && pin[45]!=0 && pin[5]==0) {
System.out.println("Start");
      mode='S';
      subMode=128;
      currentByte=0;
      clock=0;
      }
    if (mode=='S' || mode=='H' || mode=='L' || mode=='W') readByte();
    if (mode=='R') writeByte();
    pin[41]=pin[6];
    pin[45]=pin[5];
    return 0;
    }

  protected void readByte() {
    if (clock == 9) {
      pin[5]=0;
      }
    else if (pin[41]==0 && pin[6]!=0) {
      if (pin[5]!=0) currentByte |= subMode;
      subMode >>= 1;
if (subMode==0) System.out.println("Received: "+currentByte);
      }
    }

  protected void writeByte() {
    if (clock==9) ack=pin[5];
    else if (pin[41]==0 && pin[6]!=0) {
      if ((currentByte & subMode)==subMode) pin[5]=1; else pin[5]=0;
      subMode >>= 1;
      }
    }
  }
