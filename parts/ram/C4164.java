package parts.ram;
import parts.*;
public class C4164 extends Ram {
  long timePosition;
  long[] refresh;
  int ras,cas;
  int output;
  public C4164() {
    super();
    name=new String("4164");
    pin=new int[41];
    memory=new byte[8192];
    refresh=new long[256];
    }
  public void reset() {
    int i;
    timePosition=0;
    for (i=0;i<8192;i++) {
      if (i<256) refresh[i]=0;
      memory[i]=0;
      }
    output=0;
    }
  public int numPins() {
    return 16;
    }
  public int pinOut(int pn) {
    if (pn==14) return 1; else return 0;
    }
  private int getAddress() {
    int ret=0;
    if (pin[5]!=0) ret=1; else ret=0;
    if (pin[7]!=0) ret+=2;
    if (pin[6]!=0) ret+=4;
    if (pin[12]!=0) ret+=8;
    if (pin[11]!=0) ret+=16;
    if (pin[10]!=0) ret+=32;
    if (pin[13]!=0) ret+=64;
    if (pin[9]!=0) ret+=128;
    return ret;
    }
  private void readData() {
    int addr,cell,pos,i=0;
    addr=cas*256+ras;
    cell=addr/8;
    pos=addr%8;
    switch (pos) {
      case 0:i=1; break;
      case 1:i=2; break;
      case 2:i=4; break;
      case 3:i=8; break;
      case 4:i=16; break;
      case 5:i=32; break;
      case 6:i=64; break;
      case 7:i=128; break;
      }
    if ((memory[cell]&i)==i) output=1; else output=0;
    }
  private void writeData() {
    int addr,cell,pos,i=0;
    addr=cas*256+ras;
    cell=addr/8;
    pos=addr%8;
    switch (pos) {
      case 0:i=1; break;
      case 1:i=2; break;
      case 2:i=4; break;
      case 3:i=8; break;
      case 4:i=16; break;
      case 5:i=32; break;
      case 6:i=64; break;
      case 7:i=128; break;
      }
    if (pin[3]!=0) memory[cell]|=i;
      else memory[cell]&=(255-i);
    if (pin[3]!=0) output=1; else output=0;
    }
  public int run() {
    int addr,i;
    timePosition++;
    if (pin[15]==0 && pin[25]!=0) {
      addr=getAddress();
      if (timePosition>refresh[addr]+10000) {
        for (i=0;i<32;i++) memory[addr*32+i]=0;
        }
      refresh[addr]=timePosition;
      cas=addr;
      readData();
      }
    if (pin[4]==0 && pin[24]!=0) {
      ras=getAddress();
      readData();
      }
    if (pin[3]==0 && pin[23]!=0) {
      writeData();
      }
    pin[25]=pin[15];
    pin[24]=pin[4];
    pin[23]=pin[3];
    pin[2]=output;
    return 0;
    }
  }
