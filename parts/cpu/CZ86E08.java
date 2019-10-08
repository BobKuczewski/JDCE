package parts.cpu;
import java.awt.*;
import java.io.*;
import parts.*;

public class CZ86E08 extends Ic {
  int[] mem;
  int[] rom;
  String name2;
  final int Z8_PORT33  =504;
  final int Z8_PORT32  =505;
  final int Z8_PORT31  =506;
  final int Z8_IRQ     =507;
  final int Z8_PRE0    =508;
  final int Z8_PRE1    =509;
  final int Z8_COUNT0  =510;
  final int Z8_COUNT1  =511;
  final int Z8_PCL     =512;
  final int Z8_PCH     =513;
  final int Z8_HALT    =514;
  final int Z8_STOP    =515;
  final int Z8_WDT     =516;
  final int Z8_WDH     =517;
  final int Z8_CLK_PHS =518;
  final int Z8_CLK_CNT =519;
  final int CARRY_FLAG =128;
  final int ZERO_FLAG  =64;
  final int SIGN_FLAG  =32;
  final int OVER_FLAG  =16;
  final int DA_FLAG    =8;
  final int HALF_FLAG  =4;
  public CZ86E08() {
    super();
    name=new String("Z86E08");
    pin=new int[51];
    mem=new int[1024];
    rom=new int[2048];
    }
  public int numPins() {
    return 18;
    }
  public int pinOut(int pn) {
    int r=0;
    switch (pn) {
      case 1:if ((mem[0xf6] & 16)==0) r=129; else r=128;
             break;
      case 2:if ((mem[0xf6] & 32)==0) r=129; else r=128;
             break;
      case 3:if ((mem[0xf6] & 64)==0) r=129; else r=128;
             break;
      case 4:if ((mem[0xf6] & 128)==0) r=129; else r=128;
             break;
      case 6:r=0; break;
      case 7:r=0; break;
      case 8:r=0; break;
      case 9:r=0; break;
      case 10:r=0; break;
      case 11:if ((mem[0xf8] & 1)==1) r=128; else r=129;
             break;
      case 12:if ((mem[0xf8] & 1)==1) r=128; else r=129;
             break;
      case 13:if ((mem[0xf8] & 1)==1) r=128; else r=129;
             break;
      case 15:if ((mem[0xf6] & 1)==0) r=129; else r=128;
             break;
      case 16:if ((mem[0xf6] & 2)==0) r=129; else r=128;
             break;
      case 17:if ((mem[0xf6] & 4)==0) r=129; else r=128;
             break;
      case 18:if ((mem[0xf6] & 8)==0) r=129; else r=128;
             break;
      }
    return r;
    }

  public void reset() {
    mem[Z8_PCL]=0xc;
    mem[Z8_PCH]=0;
    mem[0xf1]=0;
    mem[0xf3]&=0xfc;
    mem[0xf5]&=0xfe;
    mem[0xf6]=0xff;
    mem[0xf7]=0;
    mem[0xf8]=0x4d;
    mem[0xfa]=0;
    mem[0xfb]&=0x7f;
    mem[0xfd]=0;
    mem[Z8_HALT]=0;
    }
  private void z8_store(int reg,int value) {
    int mode;
    while (value>255) value-=256;
    if (reg>=0xe0 && reg<=0xef) {
      reg=(mem[0xfd] & 0xf0)+(reg &0xf);
      }
    if (reg!=0xf4 && reg!=0xf2 && reg!=0xf5 && reg!=0xf3 &&
        (reg<128 || reg>=0xf0))
      mem[reg]=value;
    if (reg==0xf4) mem[Z8_COUNT0]=value;
    if (reg==0xf2) mem[Z8_COUNT1]=value;
    if (reg==0xf5) {
      mem[reg]=(mem[reg] & 0xfc) | (value & 3);
      mem[Z8_PRE0]=value>>2;
      }
    if (reg==0xf3) {
      mem[reg]=(mem[reg] & 0xfc) | (value & 3);
      mem[Z8_PRE1]=value>>2;
      }
    if (reg==0 && (mem[0xf8]&1)==0) {
      if ((value & 1)==1) pin[11]=1; else pin[11]=0;
      if ((value & 2)==2) pin[12]=1; else pin[12]=0;
      if ((value & 4)==4) pin[13]=1; else pin[13]=0;
      }
    if (reg==2) {
        mode=mem[0xf6];
        if ((mode & 1)==0)
          if ((value & 1)==1) pin[15]=1; else pin[15]=0;
        if ((mode & 2)==0)
          if ((value & 2)==2) pin[16]=1; else pin[16]=0;
        if ((mode & 4)==0)
          if ((value & 4)==4) pin[17]=1; else pin[17]=0;
        if ((mode & 8)==0)
          if ((value & 8)==8) pin[18]=1; else pin[18]=0;
        if ((mode & 16)==0)
          if ((value & 16)==16) pin[1]=1; else pin[1]=0;
        if ((mode & 32)==0)
          if ((value & 32)==32) pin[2]=1; else pin[2]=0;
        if ((mode & 64)==0)
          if ((value & 64)==64) pin[3]=1; else pin[3]=0;
        if ((mode & 128)==0)
          if ((value & 128)==128) pin[4]=1; else pin[4]=0;
      }
    }
  private int z8_read(int reg) {
    int mode;
    int r;
    if (reg>=0xe0 && reg<=0xef) {
      reg=(mem[0xfd] & 0xf0)+(reg &0xf);
      }
    if (reg==0) {
      r=0;
      mode=mem[0xf8];
      if ((mode & 1)==0) r |= (mem[0] & 1);
        else if (pin[11]!=0) r |= 1;
      if ((mode & 1)==0) r |= (mem[0] & 2);
        else if (pin[12]!=0) r |= 2;
      if ((mode & 1)==0) r |= (mem[0] & 4);
        else if (pin[13]!=0) r |= 4;
      return r;
      }
    else if (reg==2) {
      mode=mem[0xf6];
      r=0;
      if ((mode & 1)==0) r |= (mem[2] & 1);
        else if (pin[15]!=0) r |= 1;
      if ((mode & 2)==0) r |= (mem[2] & 2);
        else if (pin[16]!=0) r |= 2;
      if ((mode & 4)==0) r |= (mem[2] & 4);
        else if (pin[17]!=0) r |= 4;
      if ((mode & 8)==0) r |= (mem[2] & 8);
        else if (pin[18]!=0) r |= 8;
      if ((mode & 16)==0) r |= (mem[2] & 16);
        else if (pin[1]!=0) r |= 16;
      if ((mode & 32)==0) r |= (mem[2] & 32);
        else if (pin[2]!=0) r |= 32;
      if ((mode & 64)==0) r |= (mem[2] & 64);
        else if (pin[3]!=0) r |= 64;
      if ((mode & 128)==0) r |= (mem[2] & 128);
        else if (pin[4]!=0) r |= 128;
      return r;
      }
    else if (reg==3) {
      r=0;
      if (pin[8]!=0) r |= 2;
      if (pin[9]!=0) r |= 4;
      if (pin[10]!=0) r |= 8;
      }
    else return mem[reg];
    return 0;
    }
  private void z8_jr(int offs) {
    int pc;
    pc=mem[Z8_PCL]+256*mem[Z8_PCH];
    if (offs<128) pc+=offs-2; else pc-=(255-offs+1)-2;
    mem[Z8_PCL]=(pc & 255);
    mem[Z8_PCH]=(pc>>8);
    }
  private void z8_push(int h,int l) {
    int sp;
    sp=z8_read(0xff)-2;
    z8_store(0xff,sp);
    z8_store(sp,h);
    z8_store(sp+1,l);
    }
  private int z8_iread(int reg) {
    reg=z8_read(reg);
    return z8_read(reg);
    }
  private void z8_istore(int reg,int value) {
    reg=z8_read(reg);
    z8_store(reg,value);
    }
  private int z8_getpc() {
    int r;
    int pc;
    pc=mem[Z8_PCL]+256*mem[Z8_PCH];
    r=rom[pc];
    pc++;
    mem[Z8_PCL]=pc&0xff;
    mem[Z8_PCH]=pc>>8;
    return r;
    }
  private int z8_carry() {
    if ((mem[0xfc] & CARRY_FLAG)==CARRY_FLAG) return 1; else return 0;
    }
  private void z8_add_flags(int nw,int old,int acc) {
    while (nw>255) nw-=256;
    while (old>255) old-=256;
    while (acc>255) acc-=256;
    if (nw<old) mem[0xfc] |= CARRY_FLAG;
      else mem[0xfc] &= (255-CARRY_FLAG);
    if (nw>127) mem[0xfc] |= SIGN_FLAG;
      else mem[0xfc] &= (255-SIGN_FLAG);
    if (nw==0) mem[0xfc] |= ZERO_FLAG;
      else mem[0xfc] &= (255-ZERO_FLAG);
    if ((acc & 128) == (old & 128))
      if ((acc & 128) != (nw & 128)) mem[0xfc] |= OVER_FLAG;
        else mem[0xfc] &= (255-OVER_FLAG);
    mem[0xfc] &= (255-DA_FLAG);
    if ((nw & 0xf) < (old & 0xf)) mem[0xfc] |= HALF_FLAG;
      else mem[0xfc] &= (255-HALF_FLAG);
    }
  private void z8_sub_flags(int nw,int old,int acc) {
    while (nw>255) nw-=256;
    while (old>255) old-=256;
    while (acc>255) acc-=256;
    if (nw>old) mem[0xfc] |= CARRY_FLAG;
      else mem[0xfc] &= (255-CARRY_FLAG);
    if (nw>127) mem[0xfc] |= SIGN_FLAG;
      else mem[0xfc] &= (255-SIGN_FLAG);
    if (nw==0) mem[0xfc] |= ZERO_FLAG;
      else mem[0xfc] &= (255-ZERO_FLAG);
    if ((acc & 128) == (old & 128))
      if ((acc & 128) != (nw & 128)) mem[0xfc] |= OVER_FLAG;
        else mem[0xfc] &= (255-OVER_FLAG);
    mem[0xfc] |= DA_FLAG;
    if ((nw & 0xf) > (old & 0xf)) mem[0xfc] |= HALF_FLAG;
      else mem[0xfc] &= (255-HALF_FLAG);
    }
  private void z8_cp_flags(int nw,int old,int acc) {
    while (nw>255) nw-=256;
    while (old>255) old-=256;
    while (acc>255) acc-=256;
    if (nw>old) mem[0xfc] |= CARRY_FLAG;
      else mem[0xfc] &= (255-CARRY_FLAG);
    if (nw>127) mem[0xfc] |= SIGN_FLAG;
      else mem[0xfc] &= (255-SIGN_FLAG);
    if (nw==0) mem[0xfc] |= ZERO_FLAG;
      else mem[0xfc] &= (255-ZERO_FLAG);
    if ((acc & 128) == (old & 128))
      if ((acc & 128) != (nw & 128)) mem[0xfc] |= OVER_FLAG;
        else mem[0xfc] &= (255-OVER_FLAG);
    }
  private void z8_log_flags(int nw,int old,int acc) {
    while (nw>255) nw-=256;
    while (old>255) old-=256;
    while (acc>255) acc-=256;
    if (nw>127) mem[0xfc] |= SIGN_FLAG;
      else mem[0xfc] &= (255-SIGN_FLAG);
    if (nw==0) mem[0xfc] |= ZERO_FLAG;
      else mem[0xfc] &= (255-ZERO_FLAG);
    mem[0xfc] &= (255-OVER_FLAG);
    }
  private void z8_incdec_flags(int nw,int acc) {
    while (nw>255) nw-=256;
    while (acc>255) acc-=256;
    if (nw>127) mem[0xfc] |= SIGN_FLAG;
      else mem[0xfc] &= (255-SIGN_FLAG);
    if (nw==0) mem[0xfc] |= ZERO_FLAG;
      else mem[0xfc] &= (255-ZERO_FLAG);
    if ((acc & 128) != (nw & 128)) mem[0xfc] |= OVER_FLAG;
      else mem[0xfc] &= (255-OVER_FLAG);
    }

  private void z8_exec() {
    int rom1;
    int rom2;
    int ram;
    int d,s;
    int h,l;
    int src;
    int dst;
    int acc;
    int command;
    int pc;
    int addr;
    ram=pin[43];
    rom1=pin[41];
    rom2=pin[42];
    command=z8_getpc();
    switch (command & 0xf0) {
     case 0x00: switch (command & 0xf) {
        case 0x0:d=z8_getpc();
                 dst=z8_read(d);
                 acc=dst;
                 dst--;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0x1:d=z8_getpc();
                 dst=z8_iread(d);
                 acc=dst;
                 dst--;
                 z8_istore(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src+dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src+dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src+dst;
                 z8_store(d,dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src+dst;
                 z8_store(d,dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src+dst;
                 z8_store(d,dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src+dst;
                 z8_istore(d,dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe0);
                 dst--;
                 z8_store(0xe0,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc(); break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:d=z8_getpc(); d=z8_getpc(); break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        }; break;
      case 0x10: switch (command & 0xf) {
        case 0x0:d=z8_getpc(); dst=z8_read(d);
                 acc=dst;
                 if (dst>127) s=1; else s=0;
                 dst=(dst<<1)+z8_carry();
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_store(d,dst);
                 break;
        case 0x1:d=z8_getpc(); dst=z8_iread(d);
                 acc=dst;
                 if (dst>127) s=1; else s=0;
                 dst=(dst<<1)+z8_carry();
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_istore(d,dst);
                 break;
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src+dst+z8_carry();
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src+dst+z8_carry();
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src+dst+z8_carry();
                 z8_store(d,dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src+dst+z8_carry();
                 z8_store(d,dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src+dst+z8_carry();
                 z8_store(d,dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src+dst+z8_carry();
                 z8_istore(d,dst);
                 z8_add_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe1);
                 dst--;
                 z8_store(0xe1,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if (((mem[0xfc] & SIGN_FLAG) / SIGN_FLAG) !=
                     ((mem[0xfc] & OVER_FLAG) / OVER_FLAG)) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if (((mem[0xfc] & SIGN_FLAG) / SIGN_FLAG) !=
                     ((mem[0xfc] & OVER_FLAG) / OVER_FLAG)) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        }; break; 
      case 0x20: switch (command & 0xf) {
        case 0x0:d=z8_getpc();
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0x1:d=z8_getpc();
                 dst=z8_iread(d);
                 acc=dst;
                 dst++;
                 z8_istore(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src-dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src-dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src-dst;
                 z8_store(d,dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src-dst;
                 z8_store(d,dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src-dst;
                 z8_store(d,dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src-dst;
                 z8_istore(d,dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe2);
                 dst--;
                 z8_store(0xe2,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if (((mem[0xfc] & SIGN_FLAG) / SIGN_FLAG) !=
                     ((mem[0xfc] & OVER_FLAG) / OVER_FLAG) ||
                      (mem[0xfc] & ZERO_FLAG) !=0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if (((mem[0xfc] & SIGN_FLAG) / SIGN_FLAG) !=
                     ((mem[0xfc] & OVER_FLAG) / OVER_FLAG) ||
                      (mem[0xfc] & ZERO_FLAG) !=0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        }; break;
      case 0x30: switch (command & 0xf) {
        case 0x0:d=z8_getpc();
                 d=z8_read(d);
                 s=z8_read(d); d=z8_read(d+1);
                 mem[Z8_PCL]=d; mem[Z8_PCH]=s;
                 break;
        case 0x1:src=z8_getpc();
                 mem[0xfd]=src;
                 break;
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src-dst-z8_carry();
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src-dst-z8_carry();
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src-dst-z8_carry();
                 z8_store(d,dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src-dst-z8_carry();
                 z8_store(d,dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src-dst-z8_carry();
                 z8_store(d,dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src-dst-z8_carry();
                 z8_istore(d,dst);
                 z8_sub_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe3);
                 dst--;
                 z8_store(0xe3,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                   if ((mem[0xfc] & CARRY_FLAG) !=0 ||
                      (mem[0xfc] & ZERO_FLAG) !=0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                   if ((mem[0xfc] & CARRY_FLAG) !=0 ||
                      (mem[0xfc] & ZERO_FLAG) !=0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        }; break;
      case 0x40: switch (command & 0xf) {
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src|dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src|dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src|dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src|dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src|dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src|dst;
                 z8_istore(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe4);
                 dst--;
                 z8_store(0xe4,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if ((mem[0xfc] & OVER_FLAG) !=0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if ((mem[0xfc] & OVER_FLAG) !=0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[Z8_WDH]=1;
                 break;
        }; break;
      case 0x50: switch (command & 0xf) {
        case 0x0:d=z8_getpc();
                 s=mem[0xff];
                 src=z8_read(s);
                 z8_store(d,src);
                 mem[0xff]=s+1;
                 break; 
        case 0x1:d=z8_getpc();
                 s=mem[0xff];
                 src=z8_read(s);
                 z8_istore(d,src);
                 mem[0xff]=s+1;
                 break; 
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src&dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src&dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src&dst;
                 z8_istore(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe5);
                 dst--;
                 z8_store(0xe5,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if ((mem[0xfc] & SIGN_FLAG) !=0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if ((mem[0xfc] & SIGN_FLAG) !=0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[Z8_WDT]=1;
                 break;
        }; break;
      case 0x60: switch (command & 0xf) {
        case 0x0:d=z8_getpc();
                 src=z8_read(d);
                 dst=~src;
                 z8_store(d,dst);
                 acc=0;
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x1:d=z8_getpc();
                 src=z8_iread(d);
                 dst=~src;
                 z8_istore(d,dst);
                 acc=0;
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src&(~dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src&(~dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&(~dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&(~dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&(~dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src&(~dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe6);
                 dst--;
                 z8_store(0xe6,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if ((mem[0xfc] & ZERO_FLAG) !=0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if ((mem[0xfc] & ZERO_FLAG) !=0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[Z8_STOP]=1;
                 break;
        }; break;
     case 0x70: switch (command & 0xf) {
        case 0x0:s=z8_getpc(); src=z8_read(s);
                 d=mem[0xff]-1;
                 z8_store(d,src);
                 mem[0xff]=d;
                 break;
        case 0x1:s=z8_getpc(); src=z8_iread(s);
                 d=mem[0xff]-1;
                 z8_store(d,src);
                 mem[0xff]=d;
                 break;
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src&dst;
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src&dst;
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&dst;
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&dst;
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src&dst;
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src&dst;
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe7);
                 dst--;
                 z8_store(0xe7,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if ((mem[0xfc] & CARRY_FLAG) !=0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if ((mem[0xfc] & CARRY_FLAG) !=0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[Z8_HALT]=1;
                 break;
       }; break;
     case 0x80: switch (command & 0xf) {
        case 0x0:d=z8_getpc();
                 dst=z8_read(d+1)-1;
                 z8_store(d+1,dst);
                 if (dst==0) {
                   dst=z8_read(d)-1;
                   z8_store(d,dst);
                   if (dst==0) mem[0xfc] |= OVER_FLAG;
                     else mem[0xfc] &= (255-OVER_FLAG);
                   }
                 if (z8_read(d)>127) mem[0xfc] |= SIGN_FLAG;
                   else mem[0xfc] &= (255-SIGN_FLAG);
                 if (z8_read(d)==0 && z8_read(d+1)==0)
                   mem[0xfc] |= ZERO_FLAG;
                   else mem[0xfc] &= (255-ZERO_FLAG);
                 break;
        case 0x1:d=z8_getpc();
                 d=z8_iread(d);
                 dst=z8_read(d+1)-1;
                 z8_store(d+1,dst);
                 if (dst==0) {
                   dst=z8_read(d)-1;
                   z8_store(d,dst);
                   if (dst==0) mem[0xfc] |= OVER_FLAG;
                     else mem[0xfc] &= (255-OVER_FLAG);
                   }
                 if (z8_read(d)>127) mem[0xfc] |= SIGN_FLAG;
                   else mem[0xfc] &= (255-SIGN_FLAG);
                 if (z8_read(d)==0 && z8_read(d+1)==0)
                   mem[0xfc] |= ZERO_FLAG;
                   else mem[0xfc] &= (255-ZERO_FLAG);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe8);
                 dst--;
                 z8_store(0xe8,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 z8_jr(d);
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[0xfb]&=127;
                 break;
        }; break;
      case 0x90: switch (command & 0xf) {
        case 0x0:d=z8_getpc(); dst=z8_read(d);
                 acc=dst;
                 if (dst>127) s=1; else s=0;
                 dst=(dst<<1)+s;
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_store(d,dst);
                 break;
        case 0x1:d=z8_getpc(); dst=z8_iread(d);
                 acc=dst;
                 if (dst>127) s=1; else s=0;
                 dst=(dst<<1)+s;
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_istore(d,dst);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xe9);
                 dst--;
                 z8_store(0xe9,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if (((mem[0xfc] & SIGN_FLAG) / SIGN_FLAG) ==
                     ((mem[0xfc] & OVER_FLAG) / OVER_FLAG)) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if (((mem[0xfc] & SIGN_FLAG) / SIGN_FLAG) ==
                     ((mem[0xfc] & OVER_FLAG) / OVER_FLAG)) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[0xfb]|=128;
                 break;
        }; break;
      case 0xa0: switch (command & 0xf) {
        case 0x0:d=z8_getpc();
                 dst=z8_read(d+1)+1;
                 z8_store(d+1,dst);
                 if (dst==0) {
                   dst=z8_read(d)+1;
                   z8_store(d,dst);
                   if (dst==0) mem[0xfc] |= OVER_FLAG;
                     else mem[0xfc] &= (255-OVER_FLAG);
                   }
                 if (z8_read(d)>127) mem[0xfc] |= SIGN_FLAG;
                   else mem[0xfc] &= (255-SIGN_FLAG);
                 if (z8_read(d)==0 && z8_read(d+1)==0)
                   mem[0xfc] |= ZERO_FLAG;
                   else mem[0xfc] &= (255-ZERO_FLAG);
                 break;
        case 0x1:d=z8_getpc();
                 d=z8_iread(d);
                 dst=z8_read(d+1)+1;
                 z8_store(d+1,dst);
                 if (dst==0) {
                   dst=z8_read(d)+1;
                   z8_store(d,dst);
                   if (dst==0) mem[0xfc] |= OVER_FLAG;
                     else mem[0xfc] &= (255-OVER_FLAG);
                   }
                 if (z8_read(d)>127) mem[0xfc] |= SIGN_FLAG;
                   else mem[0xfc] &= (255-SIGN_FLAG);
                 if (z8_read(d)==0 && z8_read(d+1)==0)
                   mem[0xfc] |= ZERO_FLAG;
                   else mem[0xfc] &= (255-ZERO_FLAG);
                 break;
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=dst-src;
                 z8_cp_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=dst-src;
                 z8_cp_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=dst-src;
                 z8_cp_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=dst-src;
                 z8_cp_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=dst-src;
                 z8_cp_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=dst-src;
                 z8_cp_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xea);
                 dst--;
                 z8_store(0xea,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if (((mem[0xfc] & SIGN_FLAG) / SIGN_FLAG) ==
                     ((mem[0xfc] & OVER_FLAG) / OVER_FLAG) ||
                      (mem[0xfc] & ZERO_FLAG) ==0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if (((mem[0xfc] & SIGN_FLAG) / SIGN_FLAG) ==
                     ((mem[0xfc] & OVER_FLAG) / OVER_FLAG) ||
                      (mem[0xfc] & ZERO_FLAG) ==0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:s=mem[0xff];
                 mem[Z8_PCH]=z8_read(s);
                 mem[Z8_PCL]=z8_read(s+1);
                 mem[0xff]=s+2;
                 break;
        }; break;
      case 0xb0: switch (command & 0xf) {
        case 0x0:d=z8_getpc();
                 z8_store(d,0);
                 break;
        case 0x1:d=z8_getpc();
                 z8_istore(d,0);
                 break;
        case 0x2:d=z8_getpc();
                 src=z8_read((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src^dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((d & 0xf | 0xe0));
                 dst=z8_read(((d>>4) & 0xf | 0xe0));
                 acc=dst;
                 dst=src^dst;
                 z8_store(((d>>4) & 0xf | 0xe0),dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src^dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 dst=z8_read(d);
                 acc=dst;
                 dst=src^dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x6:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_read(d);
                 acc=dst;
                 dst=src^dst;
                 z8_store(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=s;
                 dst=z8_iread(d);
                 acc=dst;
                 dst=src^dst;
                 z8_istore(d,dst);
                 z8_log_flags(dst,src,acc);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xeb);
                 dst--;
                 z8_store(0xeb,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                   if ((mem[0xfc] & CARRY_FLAG) ==0 &&
                      (mem[0xfc] & ZERO_FLAG) ==0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                   if ((mem[0xfc] & CARRY_FLAG) ==0 &&
                      (mem[0xfc] & ZERO_FLAG) ==0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:d=mem[0xff];
                 mem[0xfc]=z8_read(d);
                 mem[Z8_PCH]=z8_read(d+1);
                 mem[Z8_PCL]=z8_read(d+2);
                 mem[0xff]=d+3;
                 mem[0xfb]|=128;
                 break;
        }; break;
      case 0xc0: switch (command & 0xf) {
        case 0x0:d=z8_getpc(); dst=z8_read(d);
                 acc=dst;
                 if ((dst & 1)==1) s=1; else s=0;
                 dst=(dst>>1)+128*z8_carry();
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_store(d,dst);
                 break;
        case 0x1:d=z8_getpc(); dst=z8_iread(d);
                 acc=dst;
                 if ((dst & 1)==1) s=1; else s=0;
                 dst=(dst>>1)+128*z8_carry();
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_istore(d,dst);
                 break;
        case 0x2:d=z8_getpc();
                 s=(d & 0xf)+0xe0;
                 addr=z8_read(s)*256+z8_read(s+1);
                 src=rom[addr];
                 z8_store((d>>4)+0xe0,src);
                 break;
        case 0x3:d=z8_getpc();
                 s=(d & 0xf)+0xe0;
                 addr=z8_read(s)*256+z8_read(s+1);
                 src=rom[addr];
                 z8_istore((d>>4)+0xe0,src);
                 addr++;
                 z8_store(s,addr>>4);
                 z8_store(s+1,addr & 0xf);
                 z8_store((d>>4)+0xe0,z8_read((d>>4)+0xe0)+1);
                 break;
        case 0x7:d=z8_getpc(); s=z8_getpc();
                 src=z8_read(s+z8_read((d & 0xf)+0xe0));
                 z8_store(d,src);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xec);
                 dst--;
                 z8_store(0xec,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if ((mem[0xfc] & OVER_FLAG) ==0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if ((mem[0xfc] & OVER_FLAG) ==0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[0xfc]&=0x7f;
                 break;
        }; break;
      case 0xd0: switch (command & 0xf) {
        case 0x0:d=z8_getpc(); dst=z8_read(d);
                 acc=dst;
                 if ((dst & 1)==1) s=1; else s=0;
                 dst=(dst>>1); if (dst>63) dst+=128;
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_store(d,dst);
                 break;
        case 0x1:d=z8_getpc(); dst=z8_iread(d);
                 acc=dst;
                 if ((dst & 1)==1) s=1; else s=0;
                 dst=(dst>>1); if (dst>63) dst+=128;
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_istore(d,dst);
                 break;
        case 0x4:d=z8_getpc();
                 d=z8_read(d);
                 s=z8_read(d); d=z8_read(d+1);
                 l=mem[Z8_PCL]; h=mem[Z8_PCH];
                 z8_push(h,l);
                 mem[Z8_PCL]=d; mem[Z8_PCH]=s;
                 break;
        case 0x6:s=z8_getpc(); d=z8_getpc();
                 l=mem[Z8_PCL]; h=mem[Z8_PCH];
                 z8_push(h,l);
                 mem[Z8_PCL]=d; mem[Z8_PCH]=s;
                 break;
        case 0x7:s=z8_getpc(); d=z8_getpc();
                 src=z8_read((s>>4)+0xe0);
                 z8_store(d+z8_read((s & 0xf)+0xe0),src);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xed);
                 dst--;
                 z8_store(0xed,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if ((mem[0xfc] & SIGN_FLAG) ==0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if ((mem[0xfc] & SIGN_FLAG) ==0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[0xfc]|=CARRY_FLAG;
                 break;
        }; break;
      case 0xe0: switch (command & 0xf) {
        case 0x0:d=z8_getpc(); dst=z8_read(d);
                 acc=dst;
                 if ((dst & 1)==1) s=1; else s=0;
                 dst=(dst>>1)+s*128;
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_store(d,dst);
                 break;
        case 0x1:d=z8_getpc(); dst=z8_iread(d);
                 acc=dst;
                 if ((dst & 1)==1) s=1; else s=0;
                 dst=(dst>>1)+s*128;
                 z8_add_flags(dst,acc,acc);
                 if (s==1) mem[0xfc]|=CARRY_FLAG;
                   else mem[0xfc]&=(255-CARRY_FLAG);
                 mem[0xfc]&=(255-DA_FLAG);
                 z8_istore(d,dst);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_iread((command & 0xf)+0xe0);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x4:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 z8_store(d,src);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_iread(s);
                 z8_store(d,src);
                 break;
        case 0x6:d=z8_getpc(); src=z8_getpc();
                 z8_store(d,src);
                 break;
        case 0x7:d=z8_getpc(); src=z8_getpc();
                 z8_istore(d,src);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xee);
                 dst--;
                 z8_store(0xee,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if ((mem[0xfc] & ZERO_FLAG) ==0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if ((mem[0xfc] & ZERO_FLAG) ==0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        case 0xf:mem[0xfc]^=CARRY_FLAG;
                 break;
        }; break;
      case 0xf0: switch (command & 0xf) {
        case 0x0:d=z8_getpc(); dst=z8_read(d);
                 src=dst>>4;
                 dst=(dst<<4)+src;
                 z8_store(d,dst);
                 if (dst==0) mem[0xfc]|=ZERO_FLAG;
                   else mem[0xfc]&=(255-ZERO_FLAG);
                 if (dst>127) mem[0xfc]|=SIGN_FLAG;
                   else mem[0xfc]&=(255-SIGN_FLAG);
                 break;
        case 0x1:d=z8_getpc(); dst=z8_iread(d);
                 src=dst>>4;
                 dst=(dst<<4)+src;
                 z8_istore(d,dst);
                 if (dst==0) mem[0xfc]|=ZERO_FLAG;
                   else mem[0xfc]&=(255-ZERO_FLAG);
                 if (dst>127) mem[0xfc]|=SIGN_FLAG;
                   else mem[0xfc]&=(255-SIGN_FLAG);
                 break;
        case 0x3:d=z8_getpc();
                 src=z8_read((command & 0xf)+0xe0);
                 z8_istore((command>>4)+0xe0,src);
                 break;
        case 0x5:s=z8_getpc(); d=z8_getpc();
                 src=z8_read(s);
                 z8_istore(d,src);
                 break;
        case 0x8:s=z8_getpc();
                 src=z8_read(s);
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0x9:d=z8_getpc();
                 src=z8_read((command>>4)+0xe0);
                 z8_store(d,src);
                 break;
        case 0xa:d=z8_getpc();
                 dst=z8_read(0xef);
                 dst--;
                 z8_store(0xef,dst);
                 if (dst!=0) z8_jr(d);
                 break;
        case 0xb:d=z8_getpc();
                 if ((mem[0xfc] & CARRY_FLAG) ==0) {
                   z8_jr(d);
                   }
                 break;
        case 0xc:src=z8_getpc();
                 z8_store((command>>4)+0xe0,src);
                 break;
        case 0xd:h=z8_getpc(); l=z8_getpc();
                 if ((mem[0xfc] & CARRY_FLAG) ==0) {
                   mem[Z8_PCL]=l; mem[Z8_PCH]=h;
                   }
                 break;
        case 0xe:d=(command>>4)+0xe0;
                 dst=z8_read(d);
                 acc=dst;
                 dst++;
                 z8_store(d,dst);
                 z8_incdec_flags(dst,acc);
                 break;
        }; break;
      }
    }

  private void z8_timer0() {
    if ((mem[0xf1] & 1)==1) {
      mem[0xf4]=mem[Z8_COUNT0];
      mem[0xf1]&=0xfe;
      mem[0xf5]=(mem[Z8_PRE0]<<2) |
                            (mem[0xf5] & 3);
      }
    else if ((mem[0xf1] & 2)==2) {
      if ((mem[0xf5]>>2)>1)
        mem[0xf5]=(((mem[0xf5]>>2)-1) << 2) |
                               (mem[0xf5] & 3);
      else {
        mem[0xf5]=(mem[Z8_PRE0]<<2) |
                              (mem[0xf5] & 3);
        if (mem[0xf4]>0) {
          mem[0xf4]--;
          if (mem[0xf4]==0) mem[0xfa] |= 16;
          }
          else {
            if ((mem[0xf5] & 1)==1) {
              mem[0xf4]=mem[Z8_COUNT0];
              }
            } 
        }
      }
    }
  
  private void z8_timer1() {
    if ((mem[0xf1] & 4)==4) {
      mem[0xf2]=mem[Z8_COUNT1];
      mem[0xf1]&=0xfb;
      mem[0xf3]=(mem[Z8_PRE1]<<2) |
                            (mem[0xf3] & 3);
      }
    else if ((mem[0xf1] & 8)==8) {
      if ((mem[0xf3]>>2)>1)
        mem[0xf3]=(((mem[0xf3]>>2)-1) << 2) |
                               (mem[0xf3] & 3);
      else {
        mem[0xf3]=(mem[Z8_PRE1]<<2) |
                              (mem[0xf3] & 3);
        if (mem[0xf2]>0) {
          mem[0xf2]--;
          if (mem[0xf2]==0) mem[0xfa] |= 32;
          }
          else {
            if ((mem[0xf3] & 1)==1) {
              mem[0xf2]=mem[Z8_COUNT1];
              }
            } 
        }
      }
    }
  private int z8_irq_priority() {
    int irq;
    int pri;
    int[] irqs=new int[6];
    int a,b,c;
    if ((mem[0xfa] & 1)==1) irqs[0]=1; else irqs[0]=0;
    if ((mem[0xfa] & 2)==2) irqs[1]=1; else irqs[1]=0;
    if ((mem[0xfa] & 4)==4) irqs[2]=1; else irqs[2]=0;
    if ((mem[0xfa] & 8)==8) irqs[3]=1; else irqs[3]=0;
    if ((mem[0xfa] & 16)==16) irqs[4]=1; else irqs[4]=0;
    if ((mem[0xfa] & 32)==32) irqs[5]=1; else irqs[5]=0;
    if ((mem[0xf9] & 2)==0) {
      if (irqs[1]==0 && irqs[4]==1) c=4; else c=1;
      }
    else if (irqs[4]==0 && irqs[1]==1) c=1; else c=4;
    if (irqs[c]==0) c=99;
    if ((mem[0xf9] & 4)==0) {
      if (irqs[2]==0 && irqs[0]==1) b=0; else b=2;
      }
    else if (irqs[0]==0 && irqs[2]==1) b=2; else b=0;
    if (irqs[b]==0) b=99;
    if ((mem[0xf9] & 32)==0) {
      if (irqs[5]==0 && irqs[3]==1) a=3; else a=5;
      }
    else if (irqs[3]==0 && irqs[5]==1) a=5; else a=3;
    if (irqs[a]==0) a=99;
    if ((mem[0xf9] & 1)==1) pri=1; else pri=0;
    if ((mem[0xf9] & 8)==8) pri+=2;
    if ((mem[0xf9] & 16)==16) pri+=4;
    switch (pri) {
      case 0:
      case 7:
      case 1:if (c!=99) return c; else if (a!=99) return a; else return b;
      case 2:if (a!=99) return a; else if (b!=99) return b; else return c;
      case 3:if (a!=99) return a; else if (c!=99) return c; else return b;
      case 4:if (b!=99) return b; else if (c!=99) return c; else return a;
      case 5:if (c!=99) return c; else if (b!=99) return b; else return a;
      case 6:if (b!=99) return b; else if (a!=99) return a; else return c;
      }
    return 0;
    }
  private void z8_irq() {
    int d;
    int irq;
    if (mem[0xfb] >127) {
      irq=z8_irq_priority();
      mem[Z8_HALT]=0;
      d=mem[0xff]-3;
      z8_store(d,z8_read(0xfc));
      z8_store(d+1,mem[Z8_PCH]);
      z8_store(d+2,mem[Z8_PCL]);
      mem[0xff]=d;
      switch (irq) {
        case 0:mem[Z8_PCH]=rom[0];
               mem[Z8_PCL]=rom[1];
               mem[0xfa]&=0xfe;
               break;
        case 1:mem[Z8_PCH]=rom[2];
               mem[Z8_PCL]=rom[3];
               mem[0xfa]&=0xfd;
               break;
        case 2:mem[Z8_PCH]=rom[4];
               mem[Z8_PCL]=rom[5];
               mem[0xfa]&=0xfb;
               break;
        case 3:mem[Z8_PCH]=rom[6];
               mem[Z8_PCL]=rom[7];
               mem[0xfa]&=0xf7;
               break;
        case 4:mem[Z8_PCH]=rom[8];
               mem[Z8_PCL]=rom[9];
               mem[0xfa]&=0xef;
               break;
        case 5:mem[Z8_PCH]=rom[10];
               mem[Z8_PCL]=rom[11];
               mem[0xfa]&=0xdf;
               break;
        }
      mem[0xfb] &= 0x7f;
      }
    }
  public int run() {
    if (pin[7]!=0 && mem[Z8_CLK_PHS]==0) {
      mem[Z8_CLK_CNT]++;
      if (mem[Z8_CLK_CNT]==2 && mem[0xfa]!=0)
        z8_irq();
      if (mem[Z8_CLK_CNT]==1) {
        z8_timer0();
        if ((mem[0xf3] & 2)==2) z8_timer1();
        }
      if (mem[Z8_CLK_CNT]>3) {
        if (mem[Z8_HALT]==0) z8_exec();
        mem[Z8_CLK_CNT]=0;
        }
      }
    if ((mem[0xf3] & 2)==0 && pin[8]!=0 &&
         mem[Z8_PORT31]==0) z8_timer1();
    if (mem[Z8_PORT32]==1 && pin[9]==0) 
      mem[0xfa]|=1;
    if (mem[Z8_PORT33]==1 && pin[10]==0) 
      mem[0xfa]|=2;
    if (mem[Z8_PORT31]==1 && pin[8]==0) 
      mem[0xfa]|=4;
    if (mem[Z8_PORT32]==0 && pin[9]==1) 
      mem[0xfa]|=8;
    mem[Z8_CLK_PHS]=pin[7];
    mem[Z8_PORT31]=pin[8];
    mem[Z8_PORT32]=pin[9];
    mem[Z8_PORT33]=pin[10];
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
        j=0; byt=0; mode='B'; newByte=0;
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
            case ' ':if (mode=='B' && newByte==1) rom[addr++]=byt;
                     byt=0; mode='B'; newByte=0;
                     break;
            }
          j++;
          }
        if (mode=='B' && newByte==1) rom[addr++]=byt;
        byt=0; mode='B'; newByte=0;
        }
      file.close();
      } catch (IOException e) {
      }
    }
  }
