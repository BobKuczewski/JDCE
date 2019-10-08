package parts.cdp1800;
import parts.*;
public class CCDP1802BC extends Ic {
  int[] mem;
  public CCDP1802BC() {
    super();
    name=new String("CDP1802BC");
    pin=new int[51];
    mem=new int[1024];
    }
  public int numPins() {
    return 40;
    }
  public int pinOut(int pn) {
    if (pn>=8 && pn<=15)
      {
        if (pin[42]==4)  return 129;
         else return 128;
     }
   else if ((pn>=4 && pn<=7) || (pn>=17 && pn<=19) || (pn>=25 && pn<=35))
      return 1; else return 0;
    }
  void setp()
  {
    int i;
    i=mem[40+2*mem[15]]+mem[41+2*mem[15]]*256;
    mem[0]=(int)(i / 256);
    mem[2]=(int)(i % 256);
    i++;
    mem[40+2*mem[15]]=(int)(i % 256);
    mem[41+2*mem[15]]=(int)(i / 256);
  }
  
  void setx()
  {
    int i;
    i=mem[40+2*mem[14]]+mem[41+2*mem[14]]*256;
    mem[0]=(int)(i / 256);
    mem[2]=(int)(i % 256);
  }
  
  void setn()
  {
    int i;
    int n;
    n=(int)(pin[47] & 15);
    i=mem[40+2*n]+mem[41+2*n]*256;
    mem[0]=(int)(i / 256);
    mem[2]=(int)(i % 256);
  }
  
  void exec_rinc(int i)
  {
    int x;
    x=mem[40+2*i]+256*mem[40+2*i+1];
    x++;
    mem[40+2*i]=(int)(x % 256);
    mem[40+2*i+1]=(int)(x / 256);
  }
  
  void exec_rdec(int i)
  {
    int x;
    x=mem[40+2*i]+256*mem[40+2*i+1];
    x--;
    mem[40+2*i]=(int)(x % 256);
    mem[40+2*i+1]=(int)(x / 256);
  }
   
  void exec_setaddress(int i)
  {
    mem[0]=mem[40+i*2+1];
    mem[2]=mem[40+i*2];
  }
   
  void execute()
  {
    int i,n;
    int t;
    int mb;
    int ad;
    mb=pin[44];
    if (pin[46]==2) 
      {
        i=(int)pin[48];
        pin[48]=pin[49];
        pin[49]=i;
     }
    i=(int)(pin[47] / 16);
    n=(int)(pin[47] & 15);
    mem[10]=i;
    mem[11]=n;
    pin[42]=9;
    switch (i) {
      case 0:
           if (n>0)  mem[16]=(int)pin[48];
           if (n==0)  pin[42]=6;
           break;
      case 1:exec_rinc(n); break;
      case 2:exec_rdec(n); break;
      case 3:switch (n) {
           case 0:mem[40+2*mem[15]]=(int)pin[48]; break;
           case 1:if (mem[12]!=0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
           case 2:if (mem[16]==0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
           case 3:if (mem[17]!=0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
           case 4:if (pin[24]!=0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
           case 5:if (pin[23]!=0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
           case 6:if (pin[22]!=0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
           case 7:if (pin[21]!=0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
           case 9:if (mem[12]==0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
          case 10:if (mem[16]!=0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
          case 11:if (mem[17]==0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
          case 12:if (pin[24]==0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
          case 13:if (pin[23]==0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
          case 14:if (pin[22]==0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
          case 15:if (pin[21]==0) 
                mem[40+2*mem[15]]=(int)pin[48]; break;
          } break;
      case 4:
           mem[16]=(int)pin[48];
           exec_rinc(n);;
           break;
      case 5:
           pin[48]=mem[16];
           exec_setaddress(n);
           pin[42]=4;
           break;
      case 6:switch (n) {
              case 0:exec_rinc(mem[14]);; break;
           case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                   ad=mem[40+2*mem[14]]+
                      mem[41+2*mem[14]]*256;
                   mem[0]=(int)(ad / 256);
                   mem[2]=(int)(ad % 256);
                   exec_rinc(mem[14]);
                   pin[42]=10;
                   if (n==1 || n==3 || n==5 || n==7)
                     pin[19]=1; else pin[19]=0;
                   if (n==2 || n==3 || n==6 || n==7)
                     pin[18]=1; else pin[18]=0;
                   if (n==4 || n==5 || n==6 || n==7)
                     pin[17]=1; else pin[17]=0;
                   break;
          case 9: case 10: case 11: case 12: case 13: case 14: case 15:
                   ad=mem[40+2*mem[14]]+
                      mem[41+2*mem[14]]*256;
                   mem[0]=(int)(ad / 256);
                   mem[2]=(int)(ad % 256);
                   pin[42]=11;
                   n-=8;
                   if (n==1 || n==3 || n==5 || n==7)
                     pin[19]=1; else pin[19]=0;
                   if (n==2 || n==3 || n==6 || n==7)
                     pin[18]=1; else pin[18]=0;
                   if (n==4 || n==5 || n==6 || n==7)
                     pin[17]=1; else pin[17]=0;
                   break;
          } break;
      case 7:switch (n) {
           case 0:
                mem[14]=(int)(pin[48] / 16);
                mem[15]=(int)(pin[48] & 15);
                exec_rinc(mem[14]);;
                mem[13]=1;
                break;
           case 1:
                mem[14]=(int)(pin[48] / 16);
                mem[15]=(int)(pin[48] & 15);
                exec_rinc(mem[14]);;
                mem[13]=0;
                break;
           case 2:
                mem[16]=(int)pin[48];
                exec_rinc(mem[14]);;
                break;
           case 3:
                pin[48]=mem[16];
                exec_setaddress(mem[14]);
                exec_rdec(mem[14]);
                break;
           case 4:
                mem[16]+=pin[48]+
                  mem[17];
                mem[17]=(int)((mem[16]+pin[48]+mem[17]) / 256);
                break;
           case 5:
                mem[16]=(int)(mem[16]-pin[48]-mem[17]);
                mem[17]=(int)((mem[16]-pin[48]-mem[17]) / 256);
                break;
           case 6:
                t=mem[17];
                mem[17]=(int)(mem[16] & 1);
                mem[16]=(int)((mem[16] / 2)+t*128);
                break;
           case 7:
                mem[16]=(int)(pin[48]-mem[16]- mem[17]);
                mem[17]=(int)((pin[48]-mem[16]- mem[17]) / 256);
                break;
           case 8:
                pin[48]=mem[18];
                exec_setaddress(mem[14]);
                pin[42]=4;
                break;
           case 9:
                mem[18]=(int)(mem[14]*16+ mem[15]);
                pin[48]=mem[18];
                exec_setaddress(2);
                exec_rdec(2);
                pin[42]=4;
                break;
          case 10:
                mem[12]=0;
                pin[4]=0;
                break;
          case 11:
                mem[12]=1;
                pin[4]=1;
                break;
          case 12:
                mem[16]+=(int)(pin[48]+ mem[17]);
                mem[17]=(int)((mem[16]+pin[48]+ mem[17]) / 256);
                break;
          case 13:
                mem[16]=(int)(mem[16]-pin[48]- mem[17]);
                mem[17]=(int)((mem[16]-pin[48]- mem[17]) / 256);
                break;
          case 14:
                t=mem[17];
                mem[17]=(int)(mem[16] & 128);
                mem[16]=(int)((mem[16]*2)+t);
                if (mem[17]>=128)  mem[17]=1;
                 else mem[17]=0;
                break;
          case 15:
                mem[16]=(int)(pin[48]-mem[16]- mem[17]);
                mem[17]=(int)((pin[48]-mem[16]- mem[17]) / 256);
                break;
          } break;
      case 8:mem[16]=mem[40+n*2]; break;
      case 9:mem[16]=mem[40+n*2+1]; break;
     case 10:mem[40+n*2]=mem[16]; break;
     case 11:mem[40+n*2+1]=mem[16]; break;
     case 12:switch (n) {
           case 0:
                mem[41+2*mem[15]]=(int)pin[48];
                mem[40+2*mem[15]]=(int)pin[49];
                break;
           case 1:if (mem[12]!=0) 
                {
                  mem[41+2*mem[15]]=(int)pin[48];
                  mem[40+2*mem[15]]=(int)pin[49];
               } break;
           case 2:if (mem[16]==0) 
                {
                  mem[41+2*mem[15]]=(int)pin[48];
                  mem[40+2*mem[15]]=(int)pin[49];
               } break;
           case 3:if (mem[17]!=0) 
                {
                  mem[41+2*mem[15]]=(int)pin[48];
                  mem[40+2*mem[15]]=(int)pin[49];
               } break;
           case 5:if (mem[12]==0) 
                {
                  exec_rinc(mem[15]);
                  exec_rinc(mem[15]);
               } break;
           case 6:if (mem[16]!=0) 
                {
                  exec_rinc(mem[15]);
                  exec_rinc(mem[15]);
               } break;
           case 7:if (mem[17]==0) 
                {
                  exec_rinc(mem[15]);
                  exec_rinc(mem[15]);
               } break;
           case 8:{
                exec_rinc(mem[15]);
                exec_rinc(mem[15]);
             } break;
           case 9:if (mem[12]==0) 
                {
                  mem[41+2*mem[15]]=(int)pin[48];
                  mem[40+2*mem[15]]=(int)pin[49];
               } break;
          case 10:if (mem[16]!=0) 
                {
                  mem[41+2*mem[15]]=(int)pin[48];
                  mem[40+2*mem[15]]=(int)pin[49];
               } break;
          case 11:if (mem[17]==0) 
                {
                  mem[41+2*mem[15]]=(int)pin[48];
                  mem[40+2*mem[15]]=(int)pin[49];
               } break;
          case 12:if (mem[13]!=0) 
                {
                  exec_rinc(mem[15]);
                  exec_rinc(mem[15]);
               } break;
          case 13:if (mem[12]!=0) 
                {
                  exec_rinc(mem[15]);
                  exec_rinc(mem[15]);
               } break;
          case 14:if (mem[16]==0) 
                {
                  exec_rinc(mem[15]);
                  exec_rinc(mem[15]);
               } break;
          case 15:if (mem[17]!=0) 
                {
                  exec_rinc(mem[15]);
                  exec_rinc(mem[15]);
               } break;
          } break;
     case 13:mem[15]=n; break;
     case 14:mem[14]=n; break;
     case 15:switch (n) {
           case 0:mem[16]=(int)(pin[48]); break;
           case 1:mem[16]=(int)(mem[16] | pin[48]); break;
           case 2:mem[16]=(int)(mem[16] & pin[48]); break;
           case 3:mem[16]=(int)(mem[16] ^ pin[48]); break;
           case 4:
                mem[16]+=(int)pin[48];
                mem[17]=(int)((mem[16]+pin[48]) / 256);
                break;
           case 5:
                mem[16]=(int)(mem[16]-pin[48]);
                mem[17]=(int)((mem[16]-pin[48]) / 256);
                break;
           case 6:
                mem[17]=(int)(mem[16] & 1);
                mem[16]=(int)(mem[16] / 2);
                break;
           case 7:
                mem[16]=(int)(pin[48]-mem[16]);
                mem[17]=(int)((pin[48]-mem[16]) / 256);
                break;
           case 8:mem[16]=(int)(pin[48]); break;
           case 9:mem[16]=(int)(mem[16] | pin[48]); break;
          case 10:mem[16]=(int)(mem[16] & pin[48]); break;
          case 11:mem[16]=(int)(mem[16] ^ pin[48]); break;
          case 12:
                mem[16]+=(int)(pin[48]);
                mem[17]=(int)((mem[16]+pin[48]) / 256);
                break;
          case 13:
                mem[16]=(int)(mem[16]-pin[48]);
                mem[17]=(int)((mem[16]-pin[48]) / 256);
                break;
          case 14:
                t=(int)(mem[16] & 128);
                if (t>=128)  t=1; else t=0;
                mem[17]=t;
                mem[16]=(int)(mem[16]*2);
                break;
          case 15:
                mem[16]=(int)(pin[48]-mem[16]);
                mem[17]=(int)((pin[48]-mem[16]) / 256);
                break;
          } break;
     }
  }
  
  void need_1()
  {
    pin[46]=1;
    pin[42]=2;
    setp();
  }
  
  void need_x()
  {
    pin[46]=1;
    pin[42]=2;
    setx();
  }
  
  void need_n()
  {
    pin[46]=1;
    pin[42]=2;
    setn();
  }
  
  void need_2()
  {
    pin[46]=2;
    pin[42]=3;
    setp();
  }
  
  void exec_1()
  {
    int i,n;
    int inst;
    inst=(int)pin[47];
    i=(int)(pin[47] / 16);
    n=(int)(pin[47] & 15);
    if (inst>=0x01 && inst<=0x0f) need_n();
    else if (inst>=0x30 && inst<=0x3f) need_1();
    else if (inst>=0x40 && inst<=0x4f) need_n();
    else if (inst==0x72) need_x();
    else if (inst>=0x74 && inst<=0x75) need_x();
    else if (inst==0x77) need_x();
    else if (inst>=0x7c && inst<=0x7d) need_1();
    else if (inst==0x7f) need_1();
    else if (inst>=0xc0 && inst<=0xc3) need_2();
    else if (inst>=0xc5 && inst<=0xcf) need_2();
    else if (inst>=0xf0 && inst<=0xf5) need_x();
    else if (inst==0xf7) need_x();
    else if (inst>=0xf8 && inst<=0xfd) need_1();
    else if (inst==0xff) need_1();
    else execute();
  }
  
  
  void set_address(int i)
  {
    if ((i & 1)==1)  pin[25]=1; else pin[25]=0;
    if ((i & 2)==2)  pin[26]=1; else pin[26]=0;
    if ((i & 4)==4)  pin[27]=1; else pin[27]=0;
    if ((i & 8)==8)  pin[28]=1; else pin[28]=0;
    if ((i & 16)==16)  pin[29]=1; else pin[29]=0;
    if ((i & 32)==32)  pin[30]=1; else pin[30]=0;
    if ((i & 64)==64)  pin[31]=1; else pin[31]=0;
    if ((i & 128)==128)  pin[32]=1; else pin[32]=0;
  }
  
  int get_data()
  {
    int i;
    if (pin[15]!=0)  i=1; else i=0;
    if (pin[14]!=0)  i+=2;
    if (pin[13]!=0)  i+=4;
    if (pin[12]!=0)  i+=8;
    if (pin[11]!=0)  i+=16;
    if (pin[10]!=0)  i+=32;
    if (pin[9]!=0)  i+=64;
    if (pin[8]!=0)  i+=128;
    return i;
  }
  
  void put_data(int i)
  {
    if ((i & 1)==1)  pin[15]=1; else pin[15]=0;
    if ((i & 2)==2)  pin[14]=1; else pin[14]=0;
    if ((i & 4)==4)  pin[13]=1; else pin[13]=0;
    if ((i & 8)==8)  pin[12]=1; else pin[12]=0;
    if ((i & 16)==16)  pin[11]=1; else pin[11]=0;
    if ((i & 32)==32)  pin[10]=1; else pin[10]=0;
    if ((i & 64)==64)  pin[9]=1; else pin[9]=0;
    if ((i & 128)==128)  pin[8]=1; else pin[8]=0;
  }
  
  void fetch(int edge)
  {
    int i;
    if (edge==1) 
      {
        if (pin[41]==2)  pin[7]=0;
        if (pin[41]==3) 
          set_address(mem[2]);
        if (pin[41]==0) 
          switch (pin[42]) {
           case 1:pin[47]=get_data(); break;
           case 2:pin[48]=get_data(); break;
           case 3:pin[49]=get_data(); break;
           }
     }
   else
      {
        if (pin[41]==0) 
          {
            pin[7]=1;
            set_address(mem[0]);
         }
     }
  }
  
  void store(int edge)
  {
    int i;
    if (edge==1) 
      {
        if (pin[41]==3) 
          set_address(mem[2]);
        if (pin[41]==2)  put_data((int)pin[48]);
     }
   else
      {
        if (pin[41]==5)  pin[35]=0;
        if (pin[41]==7)  pin[35]=1;
        if (pin[41]==0) 
          set_address(mem[0]);
     }
  }
  
  void inport(int edge)
  {
    int i;
    if (edge==1) 
      {
        if (pin[41]==3) 
          set_address(mem[2]);
        if (pin[41]==2) 
          mem[16]=get_data();
     }
   else
      {
        if (pin[41]==5)  pin[35]=0;
        if (pin[41]==7)  pin[35]=1;
        if (pin[41]==0) 
          set_address(mem[0]);
     }
  }
  
  void dmain(int edge)
  {
    int i;
    if (edge==1) 
      {
        if (pin[41]==0) 
          {
            mem[40]++;
            if (mem[40]==0) 
              mem[41]++;
         }
     }
   else
      {
        if (pin[41]==5)  pin[35]=0;
        if (pin[41]==7)  pin[35]=1;
     }
  }
  
  void dmaout(int edge)
  {
    int i;
    if (edge==1) 
      {
        if (pin[41]==2)  pin[7]=0;
        if (pin[41]==3) 
          set_address(mem[2]);
     }
   else
      {
        if (pin[41]==0) 
          {
            pin[7]=1;
            set_address(mem[0]);
         }
     }
  }
  
  void intrpt(int edge)
  {
    int i;
    int mb;
    if (edge==1) 
      {
        if (pin[41]==0) 
          {
            mb=pin[44];
            mem[18]=(int)(16*mem[14]+ mem[15]);
            mem[14]=2;
            mem[15]=1;
            mem[13]=0;
         }
     }
  }
  
  void check_dma()
  {
    if (pin[45]==1) 
      {
        pin[42]=7;
        pin[45]=0;
        mem[0]=mem[41];
        mem[2]=mem[40];
     }
   else if (pin[45]==2) 
      {
        pin[42]=13;
        pin[45]=0;
        mem[0]=mem[41];
        mem[2]=mem[40];
     }
   else if (pin[45]==3) 
      {
        pin[42]=12;
        pin[45]=0;
     }
   else if (pin[42]!=6) 
      {
        pin[42]=1;
        setp();
     }
  }

  public int run() {
    int i;
    if (pin[41]>6) 
      {
        if (pin[36]==0) 
          if (mem[13]!=0)  pin[45]=3;
        if (pin[37]==0)  pin[45]=2;
        if (pin[38]==0)  pin[45]=1;
     }
    if ((pin[3]==0) && (pin[2]!=0)) 
      {
        mem[0]=0;
        mem[2]=0;
        mem[10]=0;
        mem[11]=0;
        mem[12]=0;
        mem[13]=1;
        mem[14]=0;
        mem[15]=0;
        mem[40]=0;
        mem[41]=0;
        pin[4]=0; pin[5]=0; pin[6]=1;
        pin[50]=pin[1];
        pin[41]=7; pin[42]=5;
        pin[45]=0;
        for (i=17;i<=19;i++) pin[i]=0;
     }
    if ((pin[3]==0) && (pin[2]==0)) 
      pin[42]=8;
    if ((pin[3]!=0) && (pin[2]!=0) && (pin[42]==8)) 
      pin[42]=5;
    if (pin[1]!=pin[50]) 
      {
        if (pin[1]!=0) 
          {
            pin[41]++;
            if (pin[41]>7) 
              {
                pin[41]=0;
                pin[7]=1;
             }
            if (pin[41]==7)  pin[33]=1;
            if (pin[41]==0)  pin[33]=0;
            if (pin[41]==3) 
              set_address(mem[2]);
            switch (pin[42]) {
             case 1: case 2: case 3:fetch(1);
                 case 4:store(1); break;
                 case 7:dmain(1); break;
                case 10:fetch(1); break;
                case 11:inport(1); break;
                case 12:intrpt(1); break;
                case 13:dmaout(1); break;
             }
            if (pin[41]==0) 
              {
                pin[41]=0;
                switch (pin[42]) {
                 case 1:exec_1(); break;
                 case 2:execute(); break;
                 case 3:{
                      pin[42]=2;
                      setp();
                   } break;
                 case 4:check_dma(); break;
                 case 5:check_dma(); break;
                 case 6:check_dma(); break;
                 case 7:check_dma(); break;
                 case 9:check_dma(); break;
                case 10:{
                      pin[17]=0;
                      pin[18]=0;
                      pin[19]=0;
                      check_dma();
                   } break;
                case 11:{
                      pin[17]=0;
                      pin[18]=0;
                      pin[19]=0;
                      check_dma();
                   } break;
                case 12:{
                      check_dma();
                   } break;
                case 13:check_dma(); break;
                 }
  
                switch (pin[42]) {
                 case 1: case 2: case 3:{
                         pin[5]=0;
                         pin[6]=0;
                      } break;
                    case 7:{
                         pin[5]=1;
                         pin[6]=0;
                      } break;
                    case 9:{
                         pin[5]=0;
                         pin[6]=1;
                      } break;
                   case 12:{
                         pin[5]=1;
                         pin[6]=1;
                      } break;
                   case 13:{
                         pin[5]=1;
                         pin[6]=0;
                      } break;
                 }
             }
         }
       else
          {
            if (pin[41]==1)  pin[34]=1;
            if (pin[41]==2)  pin[34]=0;
            if (pin[41]==0) 
              set_address(mem[0]);
            switch (pin[42]) {
             case 1: case 2: case 3:fetch(0); break;
                 case 4:store(0); break;
                 case 7:dmain(0); break;
                case 10:fetch(0); break;
                case 11:inport(0); break;
                case 12:intrpt(0); break;
                case 13:dmaout(0); break;
             }
         }
     }
  
    pin[50]=pin[1];
    return 0;
    }
  }
