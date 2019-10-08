package parts.cpu;
import parts.*;
public class C8080A extends Ic {
  final int COMMAND=0;
  final int ARG_1=1;
  final int ARG_2=2;
  final int REG_A=3;
  final int REG_B=4;
  final int REG_C=5;
  final int REG_D=6;
  final int REG_E=7;
  final int REG_H=8;
  final int REG_L=9;
  final int REG_PSW=10;
  final int REG_PCL=11;
  final int REG_PCH=12;
  final int REG_SPL=13;
  final int REG_SPH=14;
  final int CYCLE=15;
  final int ADDR_LO=16;
  final int ADDR_HI=17;
  final int IE=18;
  final int HALT=19;
  final int DBUS=20;
  final int CLK_CNT=21;
  final int CLK_PHS=22;
  final int CLK_P=23;
  final int CLK_PHS2=24;
  final int WAIT=25;
  final int HOLD=26;
  final int HELD=27;
  final int WS=28;
  final int IRQ=29;
  final int CARRY_FLAG=1;
  final int ZERO_FLAG=64;
  final int SIGN_FLAG=128;
  final int AUX_FLAG=16;
  final int PARITY_FLAG=4;
  final int CYCLE_FETCH=1;
  final int CYCLE_FETCH1=2;
  final int CYCLE_FETCH2=3;
  final int CYCLE_EXEC=4;
  final int CYCLE_STORE1=5;
  final int CYCLE_STORE2=6;
  final int CYCLE_INT=7;
  final int CYCLE_RET2=8;
  final int CYCLE_OUT=9;
  final int CYCLE_IN=10;
  final int CYCLE_PREEXEC=11;
  final int CYCLE_FETCH3=12;
  final int CYCLE_FETCH4=13;
  final int CYCLE_STACKR1=14;
  final int CYCLE_STACKR2=15;
  final int CYCLE_STACKW1=16;
  final int CYCLE_STACKW2=17;

  final int WR_8080=18;
  final int RD_8080=17;
  final int PORT_8080=19;
  int[] mem;
  public C8080A() {
    super();
    name=new String("8080A");
    pin=new int[41];
    mem=new int[1024];
    }
  public int numPins() {
    return 40;
    }
  public int pinOut(int pn) {
    if (pn>=3 && pn<=10) {
      if (pin[17]==1) return 128; else return 129;
/*    if (dblock[pin[44]].mem[CYCLE]==CYCLE_STORE1 ||
        dblock[pin[44]].mem[CYCLE]==CYCLE_STORE2)
          return 129; else return 128;  */
      }
    else if (pn==1 || (pn>=16 && pn<=19) || pn==21 || (pn>=24 && pn<=27) ||
      (pn>=29 && pn<=40)) return 1; else return 0;
    }
  
  
  void set_address()
  {
    int i;
    i=mem[ADDR_LO];
    if ((i & 1)==1) pin[25]=1; else pin[25]=0;
    if ((i & 2)==2) pin[26]=1; else pin[26]=0;
    if ((i & 4)==4) pin[27]=1; else pin[27]=0;
    if ((i & 8)==8) pin[29]=1; else pin[29]=0;
    if ((i & 16)==16) pin[30]=1; else pin[30]=0;
    if ((i & 32)==32) pin[31]=1; else pin[31]=0;
    if ((i & 64)==64) pin[32]=1; else pin[32]=0;
    if ((i & 128)==128) pin[33]=1; else pin[33]=0;
    i=mem[ADDR_HI];
    if ((i & 1)==1) pin[34]=1; else pin[34]=0;
    if ((i & 2)==2) pin[35]=1; else pin[35]=0;
    if ((i & 4)==4) pin[1]=1; else pin[1]=0;
    if ((i & 8)==8) pin[40]=1; else pin[40]=0;
    if ((i & 16)==16) pin[37]=1; else pin[37]=0;
    if ((i & 32)==32) pin[38]=1; else pin[38]=0;
    if ((i & 64)==64) pin[39]=1; else pin[39]=0;
    if ((i & 128)==128) pin[36]=1; else pin[36]=0;
  }
  
  void set_dbus()
  {
    int i;
    i=mem[DBUS];
    if ((i & 1)==1) pin[10]=1; else pin[10]=0;
    if ((i & 2)==2) pin[9]=1; else pin[9]=0;
    if ((i & 4)==4) pin[8]=1; else pin[8]=0;
    if ((i & 8)==8) pin[7]=1; else pin[7]=0;
    if ((i & 16)==16) pin[3]=1; else pin[3]=0;
    if ((i & 32)==32) pin[4]=1; else pin[4]=0;
    if ((i & 64)==64) pin[5]=1; else pin[5]=0;
    if ((i & 128)==128) pin[6]=1; else pin[6]=0;
  }
  
  void get_dbus()
  {
    int i;
    i=0;
    if (pin[10]!=0) i=1;
    if (pin[9]!=0) i+=2;
    if (pin[8]!=0) i+=4;
    if (pin[7]!=0) i+=8;
    if (pin[3]!=0) i+=16;
    if (pin[4]!=0) i+=32;
    if (pin[5]!=0) i+=64;
    if (pin[6]!=0) i+=128;
    mem[DBUS]=i;
  }
  
  int carry_8080()
  {
    if ((mem[REG_PSW] & CARRY_FLAG)==CARRY_FLAG) return 1;
      else return 0;
  }
  
  void flags_8080(int acc,int oldacc)
  {
    int flags;
    int i;
    int p;
    flags=0;
    while (acc>255) acc-=256;
    while (oldacc>255) oldacc-=256;
    if (mem[REG_A]>127) flags=(int)(flags | SIGN_FLAG);
    if (mem[REG_A]==0) flags=(int)(flags | ZERO_FLAG);
    if (acc>255) flags=(int)(flags | CARRY_FLAG);
    p=0;
    for (i=1;i<256;i+=i) if ((mem[REG_A] &i)==i) p++;
    if ((p & 1)==0) flags=(int)(flags | PARITY_FLAG);
  }
  
  void incflags_8080(int acc,int oldacc)
  {
    int p,i;
    while (acc>255) acc-=256;
    while (oldacc>255) oldacc-=256;
    if (acc>127) mem[REG_PSW]|=SIGN_FLAG;
      else mem[REG_PSW]&=(255-SIGN_FLAG);
    if (acc==0) mem[REG_PSW]|=ZERO_FLAG;
      else mem[REG_PSW]&=(255-ZERO_FLAG);
    p=0;
    for (i=1;i<256;i+=i) if ((mem[REG_A] &i)==i) p++;
    if ((p & 1)==0) mem[REG_PSW]|=PARITY_FLAG;
      else mem[REG_PSW]&=(255-PARITY_FLAG);
  }
  
  void push_8080()
  {
    int adr;
    mem[REG_SPL]=(mem[REG_SPL]-1)&0xff;
    if (mem[REG_SPL]==255) mem[REG_SPH]=(mem[REG_SPH]-1)&0xff;
    mem[REG_SPL]=(mem[REG_SPL]-1)&0xff;
    if (mem[REG_SPL]==255) mem[REG_SPH]=(mem[REG_SPH]-1)&0xff;
    mem[ADDR_LO]=mem[REG_SPL];
    mem[ADDR_HI]=mem[REG_SPH];
    mem[CYCLE]=CYCLE_STACKW2;
  }
  
  void jump_8080()
  {
    mem[REG_PCL]=mem[ARG_1];
    mem[REG_PCH]=mem[ARG_2];
    mem[CYCLE]=CYCLE_FETCH;
  }
  
  void call_8080()
  {
    int i,j;
    int vl,vh;
    vl=mem[ARG_1]; vh=mem[ARG_2];
    j=mem[REG_SPL]+256*mem[REG_SPH];
    j-=2;
    mem[ARG_1]=mem[REG_PCL];
    mem[ARG_2]=mem[REG_PCH];
    mem[REG_PCL]=vl;
    mem[REG_PCH]=vh;
    mem[REG_SPL]=(int)(j % 256);
    mem[REG_SPH]=(int)(j / 256);
    mem[ADDR_LO]=(int)(j % 256);
    mem[ADDR_HI]=(int)(j / 256);
    mem[CYCLE]=CYCLE_STACKW2;
  }
  
  void getpc_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_PCL];
    mem[ADDR_HI]=mem[REG_PCH];
    mem[CYCLE]=CYCLE_FETCH1;
    mem[REG_PCL]=(mem[REG_PCL]+1)&0xff;
    if (mem[REG_PCL]==0) mem[REG_PCH]=(mem[REG_PCH]+1)&0xff;
  }
  
  void setpc_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_PCL];
    mem[ADDR_HI]=mem[REG_PCH];
    mem[REG_PCL]=(mem[REG_PCL]+1)&0xff;
    if (mem[REG_PCL]==0) mem[REG_PCH]=(mem[REG_PCH]+1)&0xff;
  }
  
  void getpc2_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_PCL];
    mem[ADDR_HI]=mem[REG_PCH];
    mem[CYCLE]=CYCLE_FETCH2;
    mem[REG_PCL]=(mem[REG_PCL]+1)&0xff;
    if (mem[REG_PCL]==0) mem[REG_PCH]=(mem[REG_PCH]+1)&0xff;
    mem[REG_PCL]=(mem[REG_PCL]+1)&0xff;
    if (mem[REG_PCL]==0) mem[REG_PCH]=(mem[REG_PCH]+1)&0xff;
  }
  
  void pop_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_SPL];
    mem[ADDR_HI]=mem[REG_SPH];
    mem[CYCLE]=CYCLE_STACKR2;
    mem[REG_SPL]=(mem[REG_SPL]+1)&0xff;
    if (mem[REG_SPL]==0) mem[REG_SPH]=(mem[REG_SPH]+1)&0xff;
    mem[REG_SPL]=(mem[REG_SPL]+1)&0xff;
    if (mem[REG_SPL]==0) mem[REG_SPH]=(mem[REG_SPH]+1)&0xff;
  }
  
  void gethl_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_L];
    mem[ADDR_HI]=mem[REG_H];
    mem[CYCLE]=CYCLE_FETCH1;
  }
  
  void getbc_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_C];
    mem[ADDR_HI]=mem[REG_B];
    mem[CYCLE]=CYCLE_FETCH1;
  }
  
  void getde_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_E];
    mem[ADDR_HI]=mem[REG_D];
    mem[CYCLE]=CYCLE_FETCH1;
  }
  
  void getsp_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_SPL];
    mem[ADDR_HI]=mem[REG_SPH];
    mem[CYCLE]=CYCLE_FETCH1;
  }
  
  void gethl2_8080()
  {
    int pc;
    mem[ADDR_LO]=mem[REG_L];
    mem[ADDR_HI]=mem[REG_H];
    mem[CYCLE]=CYCLE_FETCH2;
  }
  
  void preexec_8080()
  {
    mem[CYCLE]=CYCLE_EXEC;
    switch(mem[COMMAND] & 0xf0) {
      case 0x00: switch (mem[COMMAND] & 0xf) {
          case 0x1: /* LXI B,nn */
                   getpc2_8080(); break;
          case 0x6: /* MOV B,n*/
                   getpc_8080(); break;
          case 0xa: /* LDAX B */
                   getbc_8080(); break;
          case 0xe: /* MOV C,n*/
                   getpc_8080(); break;
        }; break;
      case 0x10: switch (mem[COMMAND] & 0xf) {
          case 0x1: /* LXI D,nn */
                   getpc2_8080(); break;
          case 0x6: /* MOV D,n*/
                   getpc_8080(); break;
          case 0xa: /* LDAX D */
                   getde_8080(); break;
          case 0xe: /* MOV E,n*/
                   getpc_8080(); break;
        }; break;
      case 0x20: switch (mem[COMMAND] & 0xf) {
          case 0x1: /* LXI H,nn */
                   getpc2_8080(); break;
          case 0x2: /* SHLD a */
                   getpc2_8080(); break;
          case 0x6: /* MOV H,n*/
                   getpc_8080(); break;
          case 0xa: /* LHLD a*/
                   getpc2_8080();
                   mem[CYCLE]=CYCLE_FETCH4; break;
          case 0xe: /* MOV L,n*/
                   getpc_8080(); break;
        }; break;
      case 0x30: switch (mem[COMMAND] & 0xf) {
          case 0x1: /* LXI SP,nn */
                   getpc2_8080(); break;
          case 0x2: /* SA a */
                   getpc2_8080(); break;
          case 0x4: /* INR M*/
                   gethl_8080(); break;
          case 0x5: /* DCR M*/
                   gethl_8080(); break;
          case 0x6: /* MVI M,n */
                   getpc_8080(); break;
          case 0xa: /* LDA a*/
                   getpc2_8080();
                   mem[CYCLE]=CYCLE_FETCH3; break;
          case 0xe: /* MOV A,n*/
                   getpc_8080(); break;
        }; break;
      case 0x40: switch (mem[COMMAND] & 0xf) {
          case 0x6: /* MOV B,M*/
                   gethl_8080(); break;
          case 0xe: /* MOV C,M*/
                   gethl_8080(); break;
        }; break;
      case 0x50: switch (mem[COMMAND] & 0xf) {
          case 0x6: /* MOV D,M*/
                   gethl_8080(); break;
          case 0xe: /* MOV E,M*/
                   gethl_8080(); break;
        }; break;
      case 0x60: switch (mem[COMMAND] & 0xf) {
          case 0x6: /* MOV H,M*/
                   gethl_8080(); break;
          case 0xe: /* MOV L,M*/
                   gethl_8080(); break;
        }; break;
      case 0x70: switch (mem[COMMAND] & 0xf) {
          case 0xe: /* MOV A,M*/
                   gethl_8080(); break;
        }; break;
      case 0x80: switch (mem[COMMAND] & 0xf) {
          case 0x6: /* ADD M*/
                   gethl_8080(); break;
          case 0xe: /* ADC M*/
                   gethl_8080(); break;
        }; break;
      case 0x90: switch (mem[COMMAND] & 0xf) {
          case 0x6: /* SUB M*/
                   gethl_8080(); break;
          case 0xe: /* SBB M*/
                   gethl_8080(); break;
        }; break;
      case 0xa0: switch (mem[COMMAND] & 0xf) {
          case 0x6: /* ANA M*/
                   gethl_8080(); break;
          case 0xe: /* XRA M */
                   gethl_8080(); break;
        }; break;
      case 0xb0: switch (mem[COMMAND] & 0xf) {
          case 0x6: /* ORA M */
                   gethl_8080(); break;
          case 0xf: /* CMP M */
                   gethl_8080(); break;
        }; break;
      case 0xc0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* RNZ */
                   getsp_8080(); break;
          case 0x1: /* POP B */
                   pop_8080(); break;
          case 0x2: /* JNZ a */
                   getpc2_8080(); break;
          case 0x3: /* JMP a */
                   getpc2_8080(); break;
          case 0x4: /* CNZ a */
                   getpc2_8080(); break;
          case 0x6: /* ADI n*/
                   getpc_8080(); break;
          case 0x8: /* RZ */
                   getsp_8080(); break;
          case 0x9: /* RET */
                   getsp_8080(); break;
          case 0xa: /* JZ a */
                   getpc2_8080(); break;
          case 0xc: /* CZ a */
                   getpc2_8080(); break;
          case 0xd: /* CALL a */
                   getpc2_8080(); break;
          case 0xe: /* ACI n*/
                   getpc_8080(); break;
        }; break;
      case 0xd0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* RNC */
                   getsp_8080(); break;
          case 0x1: /* POP D */
                   pop_8080(); break;
          case 0x2: /* JNC a */
                   getpc2_8080(); break;
          case 0x3: /* OUT p */
                   getpc_8080(); break;
          case 0x4: /* CNC a */
                   getpc2_8080(); break;
          case 0x6: /* SUI n*/
                   getpc_8080(); break;
          case 0x8: /* RC */
                   getsp_8080(); break;
          case 0xa: /* JC a */
                   getpc2_8080(); break;
          case 0xb: /* IN p */
                   getpc_8080(); break;
          case 0xc: /* CC a */
                   getpc2_8080(); break;
          case 0xe: /* SBI n*/
                   getpc_8080(); break;
        }; break;
      case 0xe0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* RPO */
                   getsp_8080(); break;
          case 0x1: /* POP H */
                   pop_8080(); break;
          case 0x2: /* JPO a */
                   getpc2_8080(); break;
          case 0x3: /* XTHL */
                   getsp_8080(); break;
          case 0x4: /* CPO a */
                   getpc2_8080(); break;
          case 0x6: /* ANI n*/
                   getpc_8080(); break;
          case 0x8: /* RPE */
                   getsp_8080(); break;
          case 0x9: /* ORI n */
                   gethl2_8080(); break;
          case 0xa: /* JPE a */
                   getpc2_8080(); break;
          case 0xc: /* CPE a */
                   getpc2_8080(); break;
          case 0xe: /* XRI n */
                   getpc_8080(); break;
        }; break;
      case 0xf0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* RP */
                   getsp_8080(); break;
          case 0x1: /* POP PSW */
                   pop_8080(); break;
          case 0x2: /* JP a */
                   getpc2_8080(); break;
          case 0x4: /* CP a */
                   getpc2_8080(); break;
          case 0x6: /* ORI n */
                   getpc_8080(); break;
          case 0x8: /* RM */
                   getsp_8080(); break;
          case 0xa: /* JM a */
                   getpc2_8080(); break;
          case 0xc: /* CM a */
                   getpc2_8080(); break;
          case 0xe: /* CPI n */
                   getpc_8080(); break;
        }
      }
  }
  
  void exec_8080()
  {
    int acc;
    int oldacc;
    int i,j;
    int vl,vh;
    long la,lb;
    oldacc=mem[REG_A];
    switch(mem[COMMAND] & 0xf0) {
      case 0x00: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* NOP */
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* LXI B,nn */
                   mem[REG_C]=mem[ARG_1];
                   mem[REG_B]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* STAX B */
                   mem[ADDR_LO]=mem[REG_C];
                   mem[ADDR_HI]=mem[REG_B];
                   mem[ARG_1]=mem[REG_A];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x3: /* INX B */
                   i=mem[REG_C]+256*mem[REG_B];
                   i++; i&=(int)0xffff;
                   mem[REG_C]=(int)(i % 256);
                   mem[REG_B]=(int)(i / 256);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* INR B */
                   mem[REG_B]++;
                   incflags_8080(mem[REG_B],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* DCR B */
                   mem[REG_B]--;
                   incflags_8080(mem[REG_B],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* MVI B,n */
                   mem[REG_B]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* RLC */
                   i=(mem[REG_A]>128) ? 1 : 0;
                   mem[REG_A]*=2;
                   mem[REG_A]+=i;
                   if (i==1) mem[REG_PSW]|=CARRY_FLAG;
                     else mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* DAD B */
                   la=mem[REG_L]+256*mem[REG_H];
                   lb=mem[REG_C]+256*mem[REG_B];
                   la=la+lb;
                   mem[REG_L]=(int)((la & 0xffff) % 256);
                   mem[REG_H]=(int)((la & 0xffff) / 256);
                   if (la>0xffff) mem[REG_PSW]|=CARRY_FLAG;
                     else mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* LDAX B */
                   mem[REG_A]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* DCX B */
                   i=mem[REG_C]+256*mem[REG_B];
                   i--; i&=(int)0xffff;
                   mem[REG_C]=(int)(i % 256);
                   mem[REG_B]=(int)(i / 256);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* INR C */
                   mem[REG_C]++;
                   incflags_8080(mem[REG_C],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* DCR C */
                   mem[REG_C]--;
                   incflags_8080(mem[REG_C],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* MVI C,n */
                   mem[REG_C]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* RRC */
                   i=((mem[REG_A] & 1)==1) ? 1 : 0;
                   mem[REG_A]/=2;
                   mem[REG_A]+=i*128;
                   if (i==1) mem[REG_PSW]|=CARRY_FLAG;
                     else mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x10: switch (mem[COMMAND] & 0xf) {
          case 0x1: /* LXI D,nn */
                   mem[REG_E]=mem[ARG_1];
                   mem[REG_D]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* STAX D */
                   mem[ADDR_LO]=mem[REG_E];
                   mem[ADDR_HI]=mem[REG_D];
                   mem[ARG_1]=mem[REG_A];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x3: /* INX D */
                   i=mem[REG_E]+256*mem[REG_D];
                   i++; i&=(int)0xffff;
                   mem[REG_E]=(int)(i % 256);
                   mem[REG_D]=(int)(i / 256);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* INR D */
                   mem[REG_D]++;
                   incflags_8080(mem[REG_D],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* DCR D */
                   mem[REG_D]--;
                   incflags_8080(mem[REG_D],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* MVI D,n */
                   mem[REG_D]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* RAL */
                   i=(mem[REG_A]>128) ? 1 : 0;
                   mem[REG_A]*=2;
                   mem[REG_A]+=
                     (mem[REG_PSW] & CARRY_FLAG);
                   if (i==1) mem[REG_PSW]|=CARRY_FLAG;
                     else mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* DAD D */
                   la=mem[REG_L]+256*mem[REG_H];
                   lb=mem[REG_E]+256*mem[REG_D];
                   la=la+lb;
                   mem[REG_L]=(int)((la & 0xffff) % 256);
                   mem[REG_H]=(int)((la & 0xffff) / 256);
                   if (la>0xffff) mem[REG_PSW]|=CARRY_FLAG;
                     else mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* LDAX D */
                   mem[REG_A]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* DCX D */
                   i=mem[REG_E]+256*mem[REG_D];
                   i--; i&=(int)0xffff;
                   mem[REG_E]=(int)(i % 256);
                   mem[REG_D]=(int)(i / 256);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* INR E */
                   mem[REG_E]++;
                   incflags_8080(mem[REG_E],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* DCR E */
                   mem[REG_E]--;
                   incflags_8080(mem[REG_E],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* MVI E,n */
                   mem[REG_E]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* RAR */
                   i=((mem[REG_A] & 1)==1) ? 1 : 0;
                   mem[REG_A]/=2;
                   mem[REG_A]+=
                     (mem[REG_PSW] & CARRY_FLAG)*128;
                   if (i==1) mem[REG_PSW]|=CARRY_FLAG;
                     else mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x20: switch (mem[COMMAND] & 0xf) {
          case 0x1: /* LXI H,nn */
                   mem[REG_L]=mem[ARG_1];
                   mem[REG_H]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* SHLD addr */
                   mem[ADDR_LO]=mem[ARG_1];
                   mem[ADDR_HI]=mem[ARG_2];
                   mem[ARG_1]=mem[REG_L];
                   mem[ARG_2]=mem[REG_H];
                   mem[CYCLE]=CYCLE_STORE2;
                   break;
          case 0x3: /* INX H */
                   i=mem[REG_L]+256*mem[REG_H];
                   i++; i&=(int)0xffff;
                   mem[REG_L]=(int)(i % 256);
                   mem[REG_H]=(int)(i / 256);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* INR H */
                   mem[REG_H]++;
                   incflags_8080(mem[REG_H],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* DCR H */
                   mem[REG_H]--;
                   incflags_8080(mem[REG_H],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* MVI H,n */
                   mem[REG_H]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* DAD H */
                   la=mem[REG_L]+256*mem[REG_H];
                   la=la+la;
                   mem[REG_L]=(int)((la & 0xffff) % 256);
                   mem[REG_H]=(int)((la & 0xffff) / 256);
                   if (la>0xffff) mem[REG_PSW]|=CARRY_FLAG;
                     else mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* LHLD a */
                   mem[REG_L]=mem[ARG_1];
                   mem[REG_H]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* DCX H */
                   i=mem[REG_L]+256*mem[REG_H];
                   i--; i&=(int)0xffff;
                   mem[REG_L]=(int)(i % 256);
                   mem[REG_H]=(int)(i / 256);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* INR L */
                   mem[REG_L]++;
                   incflags_8080(mem[REG_L],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* DCR L */
                   mem[REG_L]--;
                   incflags_8080(mem[REG_L],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* MVI L,n */
                   mem[REG_L]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* CMA */
                   mem[REG_A]=(int)(~mem[REG_A]);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x30: switch (mem[COMMAND] & 0xf) {
          case 0x1: /* LXI SP,nn */
                   mem[REG_SPL]=mem[ARG_1];
                   mem[REG_SPH]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* STA addr */
                   mem[ADDR_LO]=mem[ARG_1];
                   mem[ADDR_HI]=mem[ARG_2];
                   mem[ARG_1]=mem[REG_A];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x3: /* INX SP */
                   i=mem[REG_SPL]+256*mem[REG_SPH];
                   i++; i&=(int)0xffff;
                   mem[REG_SPL]=(int)(i % 256);
                   mem[REG_SPH]=(int)(i / 256);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* INR M */
                   mem[ARG_1]++;
                   incflags_8080(mem[ARG_1],oldacc);
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x5: /* DCR M */
                   mem[ARG_1]--;
                   incflags_8080(mem[ARG_1],oldacc);
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x6: /* MVI M,n */
                   mem[ADDR_LO]=mem[REG_L];
                   mem[ADDR_HI]=mem[REG_H];
                   mem[CYCLE]=CYCLE_STORE1; 
                   break;
          case 0x7: /* STC /*
                   mem[REG_PSW] |= CARRY_FLAG;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* DAD SP */
                   la=mem[REG_L]+256*mem[REG_H];
                   lb=mem[REG_SPL]+256*mem[REG_SPH];
                   la=la+lb;
                   mem[REG_L]=(int)((la & 0xffff) % 256);
                   mem[REG_H]=(int)((la & 0xffff) / 256);
                   if (la>0xffff) mem[REG_PSW]|=CARRY_FLAG;
                     else mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* LDA a */
                   mem[REG_A]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* DCX SP */
                   i=mem[REG_SPL]+256*mem[REG_SPH];
                   i--; i&=(int)0xffff;
                   mem[REG_SPL]=(int)(i % 256);
                   mem[REG_SPH]=(int)(i / 256);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* INR A */
                   mem[REG_A]++;
                   incflags_8080(mem[REG_A],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* DCR A */
                   mem[REG_A]--;
                   incflags_8080(mem[REG_A],oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* MVI A,n */
                   mem[REG_A]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* CMC */
                   mem[REG_PSW] ^= CARRY_FLAG;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x40: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* MOV B,B */
                   mem[REG_B]=mem[REG_B];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* MOV B,C */
                   mem[REG_B]=mem[REG_C];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* MOV B,D */
                   mem[REG_B]=mem[REG_D];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x3: /* MOV B,E */
                   mem[REG_B]=mem[REG_E];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* MOV B,H */
                   mem[REG_B]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* MOV B,L */
                   mem[REG_B]=mem[REG_L];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* MOV B,M */
                   mem[REG_B]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* MOV B,A */
                   mem[REG_B]=mem[REG_A];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x8: /* MOV C,B */
                   mem[REG_C]=mem[REG_B];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* MOV C,C */
                   mem[REG_C]=mem[REG_C];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* MOV C,D */
                   mem[REG_C]=mem[REG_D];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* MOV C,E */
                   mem[REG_C]=mem[REG_E];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* MOV C,H */
                   mem[REG_C]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* MOV C,L */
                   mem[REG_C]=mem[REG_L];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* MOV C,M */
                   mem[REG_C]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* MOV C,A */
                   mem[REG_C]=mem[REG_A];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x50: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* MOV D,B */
                   mem[REG_D]=mem[REG_B];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* MOV D,C */
                   mem[REG_D]=mem[REG_C];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* MOV D,D */
                   mem[REG_D]=mem[REG_D];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x3: /* MOV D,E */
                   mem[REG_D]=mem[REG_E];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* MOV D,H */
                   mem[REG_D]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* MOV D,L */
                   mem[REG_D]=mem[REG_L];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* MOV D,M */
                   mem[REG_D]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* MOV D,A */
                   mem[REG_D]=mem[REG_A];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x8: /* MOV E,B */
                   mem[REG_E]=mem[REG_B];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* MOV E,C */
                   mem[REG_E]=mem[REG_C];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* MOV E,D */
                   mem[REG_E]=mem[REG_D];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* MOV E,E */
                   mem[REG_E]=mem[REG_E];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* MOV E,H */
                   mem[REG_E]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* MOV E,L */
                   mem[REG_E]=mem[REG_L];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* MOV E,M */
                   mem[REG_E]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* MOV E,A */
                   mem[REG_E]=mem[REG_A];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x60: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* MOV H,B */
                   mem[REG_H]=mem[REG_B];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* MOV H,C */
                   mem[REG_H]=mem[REG_C];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* MOV H,D */
                   mem[REG_H]=mem[REG_D];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x3: /* MOV H,E */
                   mem[REG_H]=mem[REG_E];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* MOV H,H */
                   mem[REG_H]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* MOV H,L */
                   mem[REG_H]=mem[REG_L];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* MOV H,M */
                   mem[REG_H]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* MOV H,A */
                   mem[REG_H]=mem[REG_A];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x8: /* MOV L,B */
                   mem[REG_L]=mem[REG_B];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* MOV L,C */
                   mem[REG_L]=mem[REG_C];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* MOV L,D */
                   mem[REG_L]=mem[REG_D];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* MOV L,E */
                   mem[REG_L]=mem[REG_E];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* MOV L,H */
                   mem[REG_L]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* MOV L,L */
                   mem[REG_L]=mem[REG_L];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* MOV L,M */
                   mem[REG_L]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* MOV L,A */
                   mem[REG_L]=mem[REG_A];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x70: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* MOV M,B */
                   mem[ADDR_LO]=mem[REG_L];
                   mem[ADDR_HI]=mem[REG_H];
                   mem[ARG_1]=mem[REG_B];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x1: /* MOV M,C */
                   mem[ADDR_LO]=mem[REG_L];
                   mem[ADDR_HI]=mem[REG_H];
                   mem[ARG_1]=mem[REG_C];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x2: /* MOV M,D */
                   mem[ADDR_LO]=mem[REG_L];
                   mem[ADDR_HI]=mem[REG_H];
                   mem[ARG_1]=mem[REG_D];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x3: /* MOV M,E */
                   mem[ADDR_LO]=mem[REG_L];
                   mem[ADDR_HI]=mem[REG_H];
                   mem[ARG_1]=mem[REG_E];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x4: /* MOV M,H */
                   mem[ADDR_LO]=mem[REG_L];
                   mem[ADDR_HI]=mem[REG_H];
                   mem[ARG_1]=mem[REG_H];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x5: /* MOV M,L */
                   mem[ADDR_LO]=mem[REG_L];
                   mem[ADDR_HI]=mem[REG_H];
                   mem[ARG_1]=mem[REG_L];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x6: /* HLT */
                   mem[HALT]=1;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* MOV M,A */
                   mem[ADDR_LO]=mem[REG_L];
                   mem[ADDR_HI]=mem[REG_H];
                   mem[ARG_1]=mem[REG_A];
                   mem[CYCLE]=CYCLE_STORE1;
                   break;
          case 0x8: /* MOV A,B */
                   mem[REG_A]=mem[REG_B];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* MOV A,C */
                   mem[REG_A]=mem[REG_C];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* MOV A,D */
                   mem[REG_A]=mem[REG_D];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* MOV A,E */
                   mem[REG_A]=mem[REG_E];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* MOV A,H */
                   mem[REG_A]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* MOV A,L */
                   mem[REG_A]=mem[REG_L];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* MOV A,M */
                   mem[REG_A]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* MOV A,A */
                   mem[REG_A]=mem[REG_A];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x80: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* ADD B */
                   acc=mem[REG_A]+mem[REG_B];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* ADD C */
                   acc=mem[REG_A]+mem[REG_C];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* ADD D */
                   acc=mem[REG_A]+mem[REG_D];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x3: /* ADD E */
                   acc=mem[REG_A]+mem[REG_E];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* ADD H */
                   acc=mem[REG_A]+mem[REG_H];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* ADD L */
                   acc=mem[REG_A]+mem[REG_L];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* ADD M */
                   acc=mem[REG_A]+mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* ADD A */
                   acc=mem[REG_A]+mem[REG_A];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x8: /* ADC B */
                   acc=mem[REG_A]+mem[REG_B]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* ADC C */
                   acc=mem[REG_A]+mem[REG_C]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* ADC D */
                   acc=mem[REG_A]+mem[REG_D]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* ADC E */
                   acc=mem[REG_A]+mem[REG_E]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* ADC H */
                   acc=mem[REG_A]+mem[REG_H]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* ADC L */
                   acc=mem[REG_A]+mem[REG_L]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* ADC M */
                   acc=mem[REG_A]+mem[ARG_1]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* ADC A */
                   acc=mem[REG_A]+mem[REG_A]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0x90: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* SUB B */
                   acc=mem[REG_A]-mem[REG_B];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* SUB C */
                   acc=mem[REG_A]-mem[REG_C];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* SUB D */
                   acc=mem[REG_A]-mem[REG_D];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x3: /* SUB E */
                   acc=mem[REG_A]-mem[REG_E];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* SUB H */
                   acc=mem[REG_A]-mem[REG_H];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* SUB L */
                   acc=mem[REG_A]-mem[REG_L];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* SUB M */
                   acc=mem[REG_A]-mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* SUB A */
                   acc=mem[REG_A]-mem[REG_A];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x8: /* SBB B */
                   acc=mem[REG_A]-mem[REG_B]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* SBB C */
                   acc=mem[REG_A]-mem[REG_C]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* SBB D */
                   acc=mem[REG_A]-mem[REG_D]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* SBB E */
                   acc=mem[REG_A]-mem[REG_E]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* SBB H */
                   acc=mem[REG_A]-mem[REG_H]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* SBB L */
                   acc=mem[REG_A]-mem[REG_L]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* SBB M */
                   acc=mem[REG_A]-mem[ARG_1]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* SBB A */
                   acc=mem[REG_A]-mem[REG_A]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0xa0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* ANA B */
                   acc=mem[REG_A] & mem[REG_B];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* ANA C */
                   acc=mem[REG_A] & mem[REG_C];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* ANA D */
                   acc=mem[REG_A] & mem[REG_D];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x3: /* ANA E */
                   acc=mem[REG_A] & mem[REG_E];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* ANA H */
                   acc=mem[REG_A] & mem[REG_H];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* ANA L */
                   acc=mem[REG_A] & mem[REG_L];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* ANA M */
                   acc=mem[REG_A] & mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* ANA A */
                   acc=mem[REG_A] & mem[REG_A];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x8: /* XRA B */
                   acc=mem[REG_A] ^ mem[REG_B];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* XRA C */
                   acc=mem[REG_A] ^ mem[REG_C];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* XRA D */
                   acc=mem[REG_A] ^ mem[REG_D];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* XRA E */
                   acc=mem[REG_A] ^ mem[REG_E];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* XRA H */
                   acc=mem[REG_A] ^ mem[REG_H];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* XRA L */
                   acc=mem[REG_A] ^ mem[REG_L];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* XRA M */
                   acc=mem[REG_A] ^ mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* XRA A */
                   acc=mem[REG_A] ^ mem[REG_A];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0xb0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* ORA B */
                   acc=mem[REG_A] | mem[REG_B];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* ORA C */
                   acc=mem[REG_A] | mem[REG_C];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* ORA D */
                   acc=mem[REG_A] | mem[REG_D];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x3: /* ORA E */
                   acc=mem[REG_A] | mem[REG_E];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x4: /* ORA H */
                   acc=mem[REG_A] | mem[REG_H];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x5: /* ORA L */
                   acc=mem[REG_A] | mem[REG_L];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x6: /* ORA M */
                   acc=mem[REG_A] | mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* ORA A */
                   acc=mem[REG_A] | mem[REG_A];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x8: /* CMP B */
                   acc=mem[REG_A]-mem[REG_B];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* CMP C */
                   acc=mem[REG_A]-mem[REG_C];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* CMP D */
                   acc=mem[REG_A]-mem[REG_D];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xb: /* CMP E */
                   acc=mem[REG_A]-mem[REG_E];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* CMP H */
                   acc=mem[REG_A]-mem[REG_H];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xd: /* CMP L */
                   acc=mem[REG_A]-mem[REG_L];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xe: /* CMP M */
                   acc=mem[REG_A]-mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* CMP A */
                   acc=mem[REG_A]-mem[REG_A];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
        }; break;
      case 0xc0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* RNZ */
                   if ((mem[REG_PSW] & ZERO_FLAG)==0)
                     mem[CYCLE]=CYCLE_RET2;
                   else
                     mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* POP B */
                   mem[REG_C]=mem[ARG_1];
                   mem[REG_B]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2:/* JNZ a */
                   if ((mem[REG_PSW] & ZERO_FLAG)==0)
                   jump_8080();
                   break;
          case 0x3: /* JMP a */
                   jump_8080(); break;
          case 0x4: /* CNZ a */
                   if ((mem[REG_PSW] & ZERO_FLAG)==0)
                     call_8080();
                   break;
          case 0x5: /* PUSH B */
                   mem[ARG_1]=mem[REG_C];
                   mem[ARG_2]=mem[REG_B];
                   push_8080();
                   break;
          case 0x6: /* ADI n */
                   acc=mem[REG_A]+mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* RST 0H */
                   mem[ARG_1]=0;
                   mem[ARG_2]=0;
                   call_8080();
                   break;
          case 0x8: /* RZ */
                   if ((mem[REG_PSW] & ZERO_FLAG)==ZERO_FLAG)
                     mem[CYCLE]=CYCLE_RET2;
                   else
                     mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* RET */
                   mem[CYCLE]=CYCLE_RET2;
                   break;
          case 0xa: /* JZ a */
                   if ((mem[REG_PSW] & ZERO_FLAG)==ZERO_FLAG)
                   jump_8080();
                   break;
          case 0xc: /* CZ a */
                   if ((mem[REG_PSW] & ZERO_FLAG)==ZERO_FLAG)
                     call_8080();
                   break;
          case 0xd: /* CALL a */
                   call_8080();
                   break;
          case 0xe: /* ACI n */
                   acc=mem[REG_A]+mem[ARG_1]+
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* RST 8H */
                   mem[ARG_1]=8;
                   mem[ARG_2]=0;
                   call_8080();
                   break;
        }; break;
      case 0xd0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* RNC */
                   if ((mem[REG_PSW] & CARRY_FLAG)==0)
                     mem[CYCLE]=CYCLE_RET2;
                   else
                     mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* POP D */
                   mem[REG_E]=mem[ARG_1];
                   mem[REG_D]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* JNC a */
                   if ((mem[REG_PSW] & CARRY_FLAG)==0)
                   jump_8080();
                   break;
          case 0x3: /* OUT p */
                   mem[ADDR_LO]=mem[ARG_1];
                   mem[ARG_1]=mem[REG_A];
                   mem[CYCLE]=CYCLE_OUT;
                   break;
          case 0x4: /* CNC a */
                   if ((mem[REG_PSW] & CARRY_FLAG)==0)
                     call_8080();
                   break;
          case 0x5: /* PUSH D */
                   mem[ARG_1]=mem[REG_E];
                   mem[ARG_2]=mem[REG_D];
                   push_8080();
                   break;
          case 0x6: /* SUI n */
                   acc=mem[REG_A]-mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* RST 10H */
                   mem[ARG_1]=(int)0x10;
                   mem[ARG_2]=0;
                   call_8080();
                   break;
          case 0x8: /* RC */
                   if ((mem[REG_PSW] & CARRY_FLAG)==CARRY_FLAG)
                     mem[CYCLE]=CYCLE_RET2;
                   else
                     mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* JC a */
                   if ((mem[REG_PSW] & CARRY_FLAG)==CARRY_FLAG)
                   jump_8080();
                   break;
          case 0xb: /* IN p */
                   mem[ADDR_LO]=mem[ARG_1];
                   mem[CYCLE]=CYCLE_IN;
                   break;
          case 0xc: /* CC a */
                   if ((mem[REG_PSW] & CARRY_FLAG)==CARRY_FLAG)
                     call_8080();
                   break;
          case 0xe: /* SBI n */
                   acc=mem[REG_A]-mem[ARG_1]-
                     carry_8080();
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* RST 18H */
                   mem[ARG_1]=(int)0x18;
                   mem[ARG_2]=0;
                   call_8080();
                   break;
        }; break;
      case 0xe0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* RPO */
                   if ((mem[REG_PSW] & PARITY_FLAG)==0)
                     mem[CYCLE]=CYCLE_RET2;
                   else
                     mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* POP H */
                   mem[REG_L]=mem[ARG_1];
                   mem[REG_H]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* JPO a */
                   if ((mem[REG_PSW] & PARITY_FLAG)==0)
                   jump_8080();
                   break;
          case 0x3: /* XTHL */
                   vl=mem[ARG_1]; vh=mem[ARG_2];
                   mem[ARG_1]=mem[REG_L];
                   mem[ARG_2]=mem[REG_H];
                   mem[REG_L]=vl; mem[REG_H]=vh;
                   mem[ADDR_LO]=mem[REG_SPL];
                   mem[ADDR_HI]=mem[REG_SPH];
                   mem[CYCLE]=CYCLE_STORE2;
                   break;
          case 0x4: /* CPO a */
                   if ((mem[REG_PSW] & PARITY_FLAG)==0)
                     call_8080();
                   break;
          case 0x5: /* PUSH H */
                   mem[ARG_1]=mem[REG_L];
                   mem[ARG_2]=mem[REG_H];
                   push_8080();
                   break;
          case 0x6: /* ANI n */
                   acc=mem[REG_A] & mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* RST 20H */
                   mem[ARG_1]=(int)0x20;
                   mem[ARG_2]=0;
                   call_8080();
                   break;
          case 0x8: /* RPE */
                   if ((mem[REG_PSW] & PARITY_FLAG)==PARITY_FLAG)
                     mem[CYCLE]=CYCLE_RET2;
                   else
                     mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* PCHL */
                   mem[REG_PCL]=mem[REG_L];
                   mem[REG_PCH]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* JPE a */
                   if ((mem[REG_PSW] & PARITY_FLAG)==PARITY_FLAG)
                   jump_8080();
                   break;
          case 0xb: /* XCHG */
                   vl=mem[REG_L];
                   vh=mem[REG_H];
                   mem[REG_L]=mem[REG_E];
                   mem[REG_H]=mem[REG_D];
                   mem[REG_E]=vl;
                   mem[REG_D]=vh;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xc: /* CPE a*/
                   if ((mem[REG_PSW] & PARITY_FLAG)==PARITY_FLAG)
                     call_8080();
                   break;
          case 0xe: /* XRA n */
                   acc=mem[REG_A] ^ mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* RST 28H */
                   mem[ARG_1]=(int)0x28;
                   mem[ARG_2]=0;
                   call_8080();
                   break;
        }; break;
      case 0xf0: switch (mem[COMMAND] & 0xf) {
          case 0x0: /* RP */
                   if ((mem[REG_PSW] & SIGN_FLAG)==0)
                     mem[CYCLE]=CYCLE_RET2;
                   else
                     mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x1: /* POP PSW */
                   mem[REG_A]=mem[ARG_1];
                   mem[REG_PSW]=mem[ARG_2];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x2: /* JP a */
                   if ((mem[REG_PSW] & SIGN_FLAG)==0)
                   jump_8080();
                   break;
          case 0x3: /* DI */
                   mem[IE]=0;
                   mem[CYCLE]=CYCLE_FETCH;
                   pin[16]=0;
                   break;
          case 0x4: /* CP a */
                   if ((mem[REG_PSW] & SIGN_FLAG)==0)
                     call_8080();
                   break;
          case 0x5: /* PUSH PSW */
                   mem[ARG_1]=mem[REG_A];
                   mem[ARG_2]=mem[REG_PSW];
                   push_8080();
                   break;
          case 0x6: /* ORA n */
                   acc=mem[REG_A] | mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_PSW]&=(255-CARRY_FLAG-AUX_FLAG);
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x7: /* RST 30H */
                   mem[ARG_1]=(int)0x30;
                   mem[ARG_2]=0;
                   call_8080();
                   break;
          case 0x8: /* RM */
                   if ((mem[REG_PSW] & SIGN_FLAG)==SIGN_FLAG)
                     mem[CYCLE]=CYCLE_RET2;
                   else
                     mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0x9: /* SPHL */
                   mem[REG_SPL]=mem[REG_L];
                   mem[REG_SPH]=mem[REG_H];
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xa: /* JM a */
                   if ((mem[REG_PSW] & SIGN_FLAG)==SIGN_FLAG)
                   jump_8080();
                   break;
          case 0xb: /* EI */
                   mem[IE]=1; 
                   mem[CYCLE]=CYCLE_FETCH;
                   pin[16]=1;
                   break;
          case 0xc: /* CM a */
                   if ((mem[REG_PSW] & SIGN_FLAG)==SIGN_FLAG)
                     call_8080();
                   break;
          case 0xe: /* CMP n */
                   acc=mem[REG_A]-mem[ARG_1];
                   mem[REG_A]=(int)acc;
                   flags_8080(acc,oldacc);
                   mem[REG_A]=(int)oldacc;
                   mem[CYCLE]=CYCLE_FETCH;
                   break;
          case 0xf: /* RST 38H */
                   mem[ARG_1]=(int)0x38;
                   mem[ARG_2]=0;
                   call_8080();
                   break;
        }
      }
  }
  
  
  public int run() {
    int i;
    if (pin[13]!=0) mem[HOLD]=1; else {
      mem[HOLD]=0;
      mem[HELD]=0;
      pin[21]=0;
      }
    if (mem[HOLD]==1 && mem[CLK_CNT]==0) {
      mem[HELD]=1;
      pin[21]=1;
      }
    if (pin[23]==0) mem[WAIT]=1;
    if (mem[WAIT]==1 && mem[CLK_CNT]==2) {
      mem[WS]=1;
      pin[24]=1;
      }
    if (mem[IE]==1 && pin[14]!=0) mem[IRQ]=1;
    if (pin[12]!=0) {
      mem[REG_PCL]=0;
      mem[REG_PCH]=0;
      mem[REG_SPL]=(int)255;
      mem[REG_SPH]=(int)255;
      mem[CLK_CNT]=0;
      mem[CLK_PHS]=0;
      mem[CLK_PHS]=0;
      mem[CLK_P]=0;
      mem[WAIT]=0;
      mem[HOLD]=0;
      mem[HELD]=0;
      mem[WS]=0;
      mem[IRQ]=0;
      mem[CYCLE]=CYCLE_FETCH;
      pin[WR_8080]=1;
      pin[17]=0;
      pin[16]=0;
      }
    else if (mem[HELD]==1) {
      for (i=3;i<=10;i++) pin[i]=2;
      for (i=29;i<=40;i++) pin[i]=2;
      for (i=25;i<=27;i++) pin[i]=2;
      pin[1]=2;
      }
    else if (mem[WS]==1) {
      if (pin[23]!=0) {
        mem[WS]=0;
        mem[WAIT]=0;
        pin[24]=0;
        }
      }
    else {
      if (pin[23]==0) mem[WAIT]=1; else mem[WAIT]=0;
      if (pin[22]==1 && mem[CLK_PHS]==0 &&
          pin[15]==0)  {
        mem[CLK_P]=1;
        if (mem[CLK_CNT]==0) pin[WR_8080]=1;
        }
      if (pin[15]==1 && mem[CLK_PHS2]==0 &&
          pin[22]==0 && mem[CLK_P]==1) {
        mem[CLK_P]=0;
        mem[CLK_CNT]++;
        if (mem[CLK_CNT]==1 || mem[CLK_CNT]==4) {
          pin[19]=1;
          switch (mem[CYCLE]) {
            case CYCLE_FETCH: 
               if (mem[IRQ]==1) {
                 mem[DBUS]=(int)0x43;
                 mem[IRQ]=0;
                 mem[IE]=0;
                 pin[16]=0;
                 mem[REG_PCL]--;
                 if (mem[REG_PCL]==255) mem[REG_PCH]--;
                 }
               else mem[DBUS]=(int)0xa2;
               set_dbus();
               break;
            case CYCLE_FETCH1: 
            case CYCLE_FETCH2: 
            case CYCLE_FETCH3: 
            case CYCLE_FETCH4: 
               mem[DBUS]=(int)0x82;
               set_dbus();
               break;
            case CYCLE_STORE1: 
            case CYCLE_STORE2: 
               mem[DBUS]=(int)0x00;
               set_dbus();
               break;
            case CYCLE_IN: 
               mem[DBUS]=(int)0x42;
               set_dbus();
               break;
            case CYCLE_OUT: 
               mem[DBUS]=(int)0x10;
               set_dbus();
               break;
            case CYCLE_RET2: 
               mem[DBUS]=(int)0x86;
               set_dbus();
               break;
            case CYCLE_STACKR1: 
            case CYCLE_STACKR2: 
               mem[DBUS]=(int)0x86;
               set_dbus();
               break;
            case CYCLE_STACKW1: 
            case CYCLE_STACKW2: 
               mem[DBUS]=(int)0x04;
               set_dbus();
               break;
            }
          }
        if (mem[CLK_CNT]==2 || mem[CLK_CNT]==5) {
          pin[19]=0;
          }
  
        if (mem[WAIT]==0) { 
          if (mem[CYCLE]==CYCLE_FETCH) {
              if (mem[CLK_CNT]==1) setpc_8080();
              if (mem[CLK_CNT]==1) set_address();
              if (mem[CLK_CNT]==2) pin[RD_8080]=1;
              if (mem[CLK_CNT]==3) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==3) {
                mem[COMMAND]=mem[DBUS];
                mem[CYCLE]=CYCLE_PREEXEC;
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_PREEXEC) preexec_8080(); 
          if (mem[CYCLE]==CYCLE_EXEC) {
              if (mem[CLK_CNT]==1) {
                exec_8080();
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_FETCH1 ||
              mem[CYCLE]==CYCLE_STACKR1) {
              if (mem[CLK_CNT]==1) set_address();
              if (mem[CLK_CNT]==2) pin[RD_8080]=1;
              if (mem[CLK_CNT]==3) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==3) {
                mem[ARG_1]=mem[DBUS];
                mem[CYCLE]=CYCLE_EXEC;
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_FETCH2 ||
              mem[CYCLE]==CYCLE_STACKR2) {
              if (mem[CLK_CNT]==1) set_address();
              if (mem[CLK_CNT]==2) pin[RD_8080]=1;
              if (mem[CLK_CNT]==3) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==3) {
                mem[ARG_1]=mem[DBUS];
                mem[ADDR_LO]++;
                if (mem[ADDR_LO]==0) mem[ADDR_HI]++;
                }
              if (mem[CLK_CNT]==4) set_address();
              if (mem[CLK_CNT]==5) pin[RD_8080]=1;
              if (mem[CLK_CNT]==6) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==6) {
                mem[ARG_2]=mem[DBUS];
                mem[CYCLE]=CYCLE_EXEC;
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_RET2) {
              if (mem[CLK_CNT]==1) set_address();
              if (mem[CLK_CNT]==2) pin[RD_8080]=1;
              if (mem[CLK_CNT]==3) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==3) {
                mem[REG_PCL]=mem[DBUS];
                mem[ADDR_LO]++;
                if (mem[ADDR_LO]==0) mem[ADDR_HI]++;
                }
              if (mem[CLK_CNT]==4) set_address();
              if (mem[CLK_CNT]==5) pin[RD_8080]=1;
              if (mem[CLK_CNT]==6) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==6) {
                mem[REG_PCH]=mem[DBUS];
                mem[CYCLE]=CYCLE_FETCH;
                mem[CLK_CNT]=0;
                mem[ADDR_LO]++;
                if (mem[ADDR_LO]==0) mem[ADDR_HI]++;
                mem[REG_SPL]=mem[ADDR_LO];
                mem[REG_SPH]=mem[ADDR_HI];
                }
            }
          if (mem[CYCLE]==CYCLE_FETCH3) {
              if (mem[CLK_CNT]==1) set_address();
              if (mem[CLK_CNT]==2) pin[RD_8080]=1;
              if (mem[CLK_CNT]==3) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==3) {
                mem[ARG_1]=mem[DBUS];
                mem[ADDR_LO]++;
                if (mem[ADDR_LO]==0) mem[ADDR_HI]++;
                }
              if (mem[CLK_CNT]==4) set_address();
              if (mem[CLK_CNT]==5) pin[RD_8080]=1;
              if (mem[CLK_CNT]==6) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==6) {
                mem[ADDR_LO]=mem[ARG_1];
                mem[ADDR_HI]=mem[DBUS];
                mem[CYCLE]=CYCLE_FETCH1;
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_FETCH4) {
              if (mem[CLK_CNT]==1) set_address();
              if (mem[CLK_CNT]==2) pin[RD_8080]=1;
              if (mem[CLK_CNT]==3) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==3) {
                mem[ARG_1]=mem[DBUS];
                mem[ADDR_LO]++;
                if (mem[ADDR_LO]==0) mem[ADDR_HI]++;
                }
              if (mem[CLK_CNT]==4) set_address();
              if (mem[CLK_CNT]==5) pin[RD_8080]=1;
              if (mem[CLK_CNT]==6) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==6) {
                mem[ADDR_LO]=mem[ARG_1];
                mem[ADDR_HI]=mem[DBUS];
                mem[CYCLE]=CYCLE_FETCH2;
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_OUT) {
              if (mem[CLK_CNT]==1) {
                mem[ADDR_HI]=0;
                set_address();
                mem[DBUS]=mem[ARG_1];
                }
              if (mem[CLK_CNT]==2) set_dbus();
              if (mem[CLK_CNT]==3) pin[WR_8080]=0;
    /*          if (mem[CLK_CNT]==4) pin[WR_8080]=1; */
              if (mem[CLK_CNT]==3) {
                mem[CYCLE]=CYCLE_FETCH;
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_IN) {
              if (mem[CLK_CNT]==1) {
                mem[ADDR_HI]=0;
                set_address();
                }
              if (mem[CLK_CNT]==2) pin[RD_8080]=1;
              if (mem[CLK_CNT]==3) {
                get_dbus();
                pin[RD_8080]=0;
                }
              if (mem[CLK_CNT]==3) {
                mem[REG_A]=mem[DBUS];
                mem[CYCLE]=CYCLE_FETCH;
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_STORE1 ||
              mem[CYCLE]==CYCLE_STACKW1) {
              if (mem[CLK_CNT]==1) {
                set_address();
                mem[DBUS]=mem[ARG_1];
                }
              if (mem[CLK_CNT]==2) set_dbus();
              if (mem[CLK_CNT]==3) { pin[WR_8080]=0;
                set_dbus();
                }
    /*          if (mem[CLK_CNT]==4) pin[WR_8080]=1; */
              if (mem[CLK_CNT]==3) {
                mem[CYCLE]=CYCLE_FETCH;
                mem[CLK_CNT]=0;
                }
            }
          if (mem[CYCLE]==CYCLE_STORE2 ||
              mem[CYCLE]==CYCLE_STACKW2) {
              if (mem[CLK_CNT]==1) {
                set_address();
                mem[DBUS]=mem[ARG_1];
                }
              if (mem[CLK_CNT]==2) set_dbus();
              if (mem[CLK_CNT]==3) pin[WR_8080]=0;
              if (mem[CLK_CNT]==4) pin[WR_8080]=1;
              if (mem[CLK_CNT]==4) {
                mem[ADDR_LO]++;
                if (mem[ADDR_LO]==0) mem[ADDR_HI]++;
                }
              if (mem[CLK_CNT]==4) {
                set_address();
                mem[DBUS]=mem[ARG_2];
                }
              if (mem[CLK_CNT]==5) set_dbus();
              if (mem[CLK_CNT]==6) pin[WR_8080]=0;
    /*          if (mem[CLK_CNT]==7) pin[WR_8080]=1; */
              if (mem[CLK_CNT]==6) {
                mem[CYCLE]=CYCLE_FETCH;
                mem[CLK_CNT]=0;
                }
            }
          }
        }
      mem[CLK_PHS]=(int)pin[22];
      mem[CLK_PHS2]=(int)pin[15];
      }
    return 0;
    }
  }
