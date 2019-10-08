package parts.pic;
import java.awt.*;
import java.io.*;
import parts.*;

public class C12C509A extends Ic {
  int[] mem;
  int[] rom;
  int   tris;
  int   option;
  int   inPort;
  int   w;
  int   wait;
  int   wdt;
  int   prescaler;
  int   stack1;
  int   stack2;
  int   sleep;
  int   cycle;
  int   pc;
  int   phase;
  int   pin5;
  String name2;
  final int PIC_INDF   =0;
  final int PIC_TMR0   =1;
  final int PIC_PCL    =2;
  final int PIC_STATUS =3;
  final int PIC_FSR    =4;
  final int PIC_OSCCAL =5;
  final int PIC_GPIO   =6;

  public C12C509A() {
    super();
    name=new String("PIC");
    pin=new int[51];
    mem=new int[48];
    rom=new int[2048];
    }

  public int numPins() {
    return 8;
    }

  public int pinOut(int pn) {
    int r=0;
    switch (pn) {
      case   2:if ((tris & 32) == 0) r=1; else r=0;
               break;
      case   3:if ((tris & 16) == 0) r=1; else r=0;
               break;
      case   4:r=0;
               break;
      case   5:if ((tris & 4) == 0) r=1; else r=0;
               break;
      case   6:if ((tris & 2) == 0) r=1; else r=0;
               break;
      case   7:if ((tris & 1) == 0) r=1; else r=0;
               break;
      }
    return r;
    }

  public void reset() {
    mem[PIC_INDF]=0x00;
    mem[PIC_TMR0]=0x00;
    mem[PIC_PCL]=0xff;
    mem[PIC_STATUS]=0x18;
    mem[PIC_FSR]=0xc0;
    mem[PIC_OSCCAL]=0x80;
    mem[PIC_GPIO]=0x00;
    tris=0x3f;
    option=0xff;
    sleep=0;
    cycle=0;
    wait=1;
    phase=1;
    prescaler=1;
    pc=0;
    }

  private int readPort() {
    int r=0;
    if (pin[7] != 0) r=1; else r=0;
    if (pin[6] != 0) r+=2;
    if (pin[5] != 0) r+=4;
    if (pin[4] != 0) r+=8;
    if (pin[3] != 0) r+=16;
    if (pin[2] != 0) r+=32;
    return r;
    }

  private void writePort(int value) {
    if ((tris & 1) == 0) {
      if ((value & 1) == 1) pin[7]=1; else pin[7]=0;
      }
    if ((tris & 2) == 0) {
      if ((value & 2) == 2) pin[6]=1; else pin[6]=0;
      }
    if ((tris & 4) == 0) {
      if ((value & 4) == 4) pin[5]=1; else pin[5]=0;
      }
    if ((tris & 16) == 0) {
      if ((value & 16) == 16) pin[3]=1; else pin[3]=0;
      }
    if ((tris & 32) == 0) {
      if ((value & 32) == 32) pin[2]=1; else pin[2]=0;
      }
    }

  private void picStore(int reg,int value) {
    reg=reg & 0x1f;
    if (reg!=0) {
      mem[reg]=value;
      if (reg==PIC_GPIO) writePort(value);
      if (reg==PIC_PCL) pc=(pc & 0xf00) | mem[PIC_PCL];
      } else {
      }
    }

  private int picRead(int reg) {
    int r=0xff;
    reg=reg & 0x1f;
    if (reg == PIC_GPIO) r=inPort;
    else if (reg != 0) {
      r=mem[reg];
      } else {
      }
    return r;
    }

  private int picGetpc() {
    int r;
    r=rom[pc++];
    pc &= 0x7ff;
    mem[PIC_PCL]=(pc & 0xff);
    return r;
    }

  private void picExec() {
    int command;
    int f;
    int k;
    int d;
    int i;
    int r;
    int b;
    command=picGetpc();
    f=(command & 0x1f);
    k=(command & 0xff);
    b=(command>>5) & 7;
    if ((command & 0x20) == 0x20) d=1; else d=0;
    if ((command & 0xf00) == 0xe00) {              /* ANDLW */
      w=w & k;
      if (w == 0) mem[PIC_STATUS] |= 4;
        else mem[PIC_STATUS] &= 0xfb;
      }
    else if ((command & 0xf00) == 0x400) {         /* BCF */
      i=1<<b;
      picStore(f,picRead(f)&(~i));
      }
    else if ((command & 0xf00) == 0x500) {         /* BSF */
      i=1<<b;
      picStore(f,picRead(f) | i);
      }
    else if ((command & 0xf00) == 0x600) {         /* BTFSC */
      i=1<<b;
      if((picRead(f) & i) == 0) {
        pc=(pc+1)&0x7ff;
        mem[PIC_PCL]=(pc & 0xff);
        wait=1;
        }
      }
    else if ((command & 0xf00) == 0x700) {         /* BTFSS */
      i=1<<b;
      if((picRead(f) & i) == i) {
        pc=(pc+1)&0x7ff;
        mem[PIC_PCL]=(pc & 0xff);
        wait=1;
        }
      }
    else if ((command & 0xf00) == 0x900) {         /* CALL */
      stack2=stack1;
      stack1=pc;
      pc=k | ((mem[PIC_STATUS] & 0x20)<<4);
      mem[PIC_PCL]=(pc & 0xff);
      wait=1;
      }
    else if (command == 0x004) {                   /* CLRWDT */
      wdt=0;
      if ((option & 8) == 8) prescaler=0;
      mem[PIC_STATUS] |= 0x18;
      }
    else if ((command & 0xe00) == 0xa00) {         /* GOTO */
      pc=k | ((mem[PIC_STATUS] & 0x20)<<4) | (command & 0x100);
      mem[PIC_PCL]=(pc & 0xff);
      wait=1;
      }
    else if ((command & 0xf00) == 0xd00) {         /* IORLW */
      w=w | k;
      if (w == 0) mem[PIC_STATUS] |= 4;
        else mem[PIC_STATUS] &= 0xfb;
      }
    else if ((command & 0xf00) == 0xc00) {         /* MOVLW */
      w=k;
      }
    else if (command == 0x002) {                   /* OPTION */
      option=w;
      }
    else if ((command & 0xf00) == 0x800) {         /* RETLW */
      w=k;
      pc=stack1;
      stack1=stack2;
      mem[PIC_PCL]=(pc & 0xff);
      wait=1;
      }
    else if (command == 0x003) {                   /* SLEEP */
      sleep=1;
      wdt=0;
      if ((option & 8) == 8) prescaler=0;
      mem[PIC_STATUS] |= 0x10;
      }
    else if (command == 0x006) {                   /* TRIS */
      tris=w;
      }
    else if ((command & 0xf00) == 0xf00) {         /* XORLW */
      w=w ^ k;
      if (w == 0) mem[PIC_STATUS] |= 4;
        else mem[PIC_STATUS] &= 0xfb;
      }
    else switch ((command>>4) & 0xfc) {
      case 0x1c:i=picRead(f);                      /* ADDWF */
                r=(w+i)&0xff;
                if (((w & 15) + (i & 15)) > 15) mem[PIC_STATUS] |= 2;
                  else mem[PIC_STATUS] &= 0xfd;
                if (r > 255) mem[PIC_STATUS] |= 1;
                  else mem[PIC_STATUS] &= 0xfe;
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x14:i=picRead(f);                      /* ANDWF */
                r=w & i;
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x04:                                   /* CLRF,CLRW */
                mem[PIC_STATUS] |= 4;
                if (d == 0) w=0; else picStore(f,0);
                break;
      case 0x24:i=picRead(f);                      /* COMF */
                r= ~i;
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x0c:i=picRead(f);                      /* DECF */
                r= (i-1)&0xff;
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x2c:i=picRead(f);                      /* DECFSZ */
                r= (i-1)&0xff;
                if (r == 0) {
                  pc=(pc+1)&0x7ff;
                  mem[PIC_PCL]=(pc & 0xff);
                  wait=1;
                  }
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x28:i=picRead(f);                      /* INCF */
                r= (i+1)&0xff;
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x3c:i=picRead(f);                      /* INCFSZ */
                r= (i+1)&0xff;
                if (r == 0) {
                  pc=(pc+1)&0x7ff;
                  mem[PIC_PCL]=(pc & 0xff);
                  wait=1;
                  }
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x10:i=picRead(f);                      /* IORWF */
                r=w | i;
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x20:r=picRead(f);                      /* MOVF */
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x00:                                   /* MOVWF */
                if (d == 1) picStore(f,w);
                break;
      case 0x34:i=picRead(f);                      /* RLF */
                if ((i & 0x80) == 0x80) mem[PIC_STATUS] |= 1;
                  else mem[PIC_STATUS] &= 0xfe;
                r=((i<<1) | (mem[PIC_STATUS] & 1)) & 0xff;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x30:i=picRead(f);                      /* RRF */
                if ((i & 1) == 1) mem[PIC_STATUS] |= 1;
                  else mem[PIC_STATUS] &= 0xfe;
                r=((i>>1) | ((mem[PIC_STATUS] & 1) * 0x80)) & 0xff;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x08:i=picRead(f);                      /* SUBWF */
                r=(i-w)&0xff;
                if (((i & 15) - (w & 15)) < 0) mem[PIC_STATUS] &= 0xfd;
                  else mem[PIC_STATUS] |= 0x02;
                if (r < 0) mem[PIC_STATUS] &= 0xfe;
                  else mem[PIC_STATUS] |= 1;
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x38:i=picRead(f);                      /* SWAPF */
                r=((i>>4) & 0x0f) | ((i & 0x0f)<<4);
                if (d == 0) w=r; else picStore(f,r);
                break;
      case 0x18:i=picRead(f);                      /* XORWF */
                r=w ^ i;
                if (r == 0) mem[PIC_STATUS] |= 4;
                  else mem[PIC_STATUS] &= 0xfb;
                if (d == 0) w=r; else picStore(f,r);
                break;
      } 
    }

  protected void picTimer() {
    if ((option & 8)==8) {
      if (--prescaler<=0) {
        mem[PIC_TMR0]=(mem[PIC_TMR0]+1)&0xff;
        switch (option & 7) {
          case 0:prescaler=2; break;
          case 1:prescaler=4; break;
          case 2:prescaler=8; break;
          case 3:prescaler=16; break;
          case 4:prescaler=32; break;
          case 5:prescaler=64; break;
          case 6:prescaler=128; break;
          case 7:prescaler=256; break;
          }
        }
      } else mem[PIC_TMR0]=(mem[PIC_TMR0]+1)&0xff;
    }

  protected boolean clocked() {
    if ((option & 32)==0) {
      if ((option & 16)==16) {
        if (pin5 != 0 && pin[5]==0) picTimer();
        } else {
        if (pin5 == 0 && pin[5]!=0) picTimer();
        }
      }
    if (phase==1) {
      phase=0;
      } else phase=1;
    if (phase==0) return true; else return false;
    }

  public int run() {
    if (clocked()) {
      cycle++;
      if (cycle==1) inPort=readPort();
      if (cycle==3) {
        if (wait==0) {
          picExec();
          if ((option & 32)==32) picTimer();
          } else wait=0;
        cycle=0;
        }
      }
    pin5=pin[5];
    return 0;
    }

  public void init(Frame window) {
    int j;
    StringBuffer buffer=new StringBuffer();
    FileDialog fileDialog;
    fileDialog=new FileDialog(window,"Load ROM file",FileDialog.LOAD);
    fileDialog.show();
    if (fileDialog.getFile()!=null) {
      buffer.setLength(0);
      buffer.append(fileDialog.getFile());
      j=0;
      while (j<buffer.length() && buffer.charAt(j)!='.') j++;
      buffer.setLength(j);
      name2=new String(buffer.toString());
      loadRom();
      }
    fileDialog=null;
    }
  public void load(String buffer) {
    int j=0;
    StringBuffer buffer2=new StringBuffer();
    buffer2.setLength(0);
    while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
    x=(new Integer(buffer2.toString())).intValue();
    j++;
    buffer2.setLength(0);
    while (buffer.charAt(j)!=' ') buffer2.append(buffer.charAt(j++));
    y=(new Integer(buffer2.toString())).intValue();
    j++;
    buffer2.setLength(0);
    while (j<buffer.length()) buffer2.append(buffer.charAt(j++));
    name2=buffer2.toString();
    loadRom();
    }
  public String save() {
    int j=0;
    StringBuffer buffer=new StringBuffer();
    buffer.setLength(0);
    buffer.append(toString());
    j=0;
    while (j<buffer.length() && buffer.charAt(j)!='@') j++;
    buffer.setLength(j);
    buffer.append(" ");
    buffer.append(x);
    buffer.append(" ");
    buffer.append(y);
    buffer.append(" ");
    buffer.append(name2);
    buffer.append("\n");
    return buffer.toString();
    }
  public void loadRom() {
    int j;
    int addr=0;
    int byt=0;
    int newByte;
    int bits;
    int accum;
    char mode='B';
    BufferedReader file;
    StringBuffer   buffer=new StringBuffer();
    buffer.setLength(0);
    buffer.append(name2);
    buffer.append(".rom");
    try {
      file=new BufferedReader(new FileReader(buffer.toString()));
      while (file.ready()) {
        buffer.setLength(0);
        buffer.append(file.readLine());
        j=0; byt=0; mode='B'; newByte=0; bits=0; accum=0;
        while (j<buffer.length()) {
          switch (buffer.charAt(j)) {
            case '0':if (mode=='B') byt=byt<<4; else addr=addr<<4;
                     newByte=1; break;
            case '1':if (mode=='B') byt=(byt<<4)+1; else addr=(addr<<4)+1;
                     newByte=1; break;
            case '2':if (mode=='B') byt=(byt<<4)+2; else addr=(addr<<4)+2;
                     newByte=1; break;
            case '3':if (mode=='B') byt=(byt<<4)+3; else addr=(addr<<4)+3;
                     newByte=1; break;
            case '4':if (mode=='B') byt=(byt<<4)+4; else addr=(addr<<4)+4;
                     newByte=1; break;
            case '5':if (mode=='B') byt=(byt<<4)+5; else addr=(addr<<4)+5;
                     newByte=1; break;
            case '6':if (mode=='B') byt=(byt<<4)+6; else addr=(addr<<4)+6;
                     newByte=1; break;
            case '7':if (mode=='B') byt=(byt<<4)+7; else addr=(addr<<4)+7;
                     newByte=1; break;
            case '8':if (mode=='B') byt=(byt<<4)+8; else addr=(addr<<4)+8;
                     newByte=1; break;
            case '9':if (mode=='B') byt=(byt<<4)+9; else addr=(addr<<4)+9;
                     newByte=1; break;
            case 'a':
            case 'A':if (mode=='B') byt=(byt<<4)+10; else addr=(addr<<4)+10;
                     newByte=1; break;
            case 'b':
            case 'B':if (mode=='B') byt=(byt<<4)+11; else addr=(addr<<4)+11;
                     newByte=1; break;
            case 'c':
            case 'C':if (mode=='B') byt=(byt<<4)+12; else addr=(addr<<4)+12;
                     newByte=1; break;
            case 'd':
            case 'D':if (mode=='B') byt=(byt<<4)+13; else addr=(addr<<4)+13;
                     newByte=1; break;
            case 'e':
            case 'E':if (mode=='B') byt=(byt<<4)+14; else addr=(addr<<4)+14;
                     newByte=1; break;
            case 'f':
            case 'F':if (mode=='B') byt=(byt<<4)+15; else addr=(addr<<4)+15;
                     newByte=1; break;
            case ':':mode='A'; addr=0; break;
            case ' ':if (mode=='B' && newByte==1) {
                       if (bits==1) {
                         accum |= (byt & 0xf);
                         rom[addr++]=accum;
                         accum=0;
                         bits=0;
                         } else {
                         accum=byt<<4;
                         bits=1;
                         }
                       }
                     byt=0; mode='B'; newByte=0;
                     break;
            }
          j++;
          }
        if (mode=='B' && newByte==1) {
           if (bits==0) rom[addr++]=byt;
             else {
             accum |= (byt & 0xf);
             rom[addr++]=accum;
             }
           }
        byt=0; mode='B'; newByte=0; bits=0; accum=0;
        }
      file.close();
      } catch (IOException e) {
      }
    }
  }
