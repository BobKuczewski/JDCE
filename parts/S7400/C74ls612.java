package parts.S7400;
import parts.*;
public class C74ls612 extends Ic {
  byte[] mem;
  public C74ls612() {
    super();
    name=new String("74LS612");
    pin=new int[41];
    mem=new byte[1024];
    }
  public int numPins() {
    return 40;
    }
  public int pinOut(int pn) {
    if (pin[6]==0)
      {
        if ((pn>=14 && pn<=19) || (pn>=22 && pn<=27))
          return 129; else return 128;
      }
    else
      {
        if ((pn>=7 && pn<=12) || (pn>=14 && pn<=19) ||
            (pn>=29 && pn<=34) || (pn>=22 && pn<=27))
          return 129; else return 128;
     }
    }
  public int run() {
    int i=0;
    byte jh=0;
    byte jl=0;
    if (pin[4]!=0)
      {
        for (i=7;i<=12;i++) pin[i]=2;
        for (i=29;i<=34;i++) pin[i]=2;
        for (i=14;i<=19;i++) pin[i]=2;
        for (i=22;i<=27;i++) pin[i]=2;
     }
   else
      {
        if (pin[6]!=0)
          {
            if (pin[36]!=0)  i=1;
            if (pin[38]!=0)  i+=2;
            if (pin[1]!=0)  i+=4;
            if (pin[3]!=0)  i+=8;
            i=i*2;
            jl=mem[i];
            jh=mem[i+1];
            if ((jl & 1)==1)  pin[7]=1; else pin[7]=0;
            if ((jl & 2)==2)  pin[8]=1; else pin[8]=0;
            if ((jl & 4)==4)  pin[9]=1; else pin[9]=0;
            if ((jl & 8)==8)  pin[10]=1; else pin[10]=0;
            if ((jl & 16)==16)  pin[11]=1; else pin[11]=0;
            if ((jl & 32)==32)  pin[12]=1; else pin[12]=0;
            if ((jl & 64)==64)  pin[29]=1; else pin[29]=0;
            if ((jl & 128)==128)  pin[30]=1; else pin[30]=0;
            if ((jh & 1)==1)  pin[31]=1; else pin[31]=0;
            if ((jh & 2)==2)  pin[32]=1; else pin[32]=0;
            if ((jh & 4)==4)  pin[33]=1; else pin[33]=0;
            if ((jh & 8)==8)  pin[34]=1; else pin[34]=0;
         }
       else
          if (pin[5]==0)
            {
              if (pin[7]!=0)  jl=1;
              if (pin[8]!=0)  jl+=2;
              if (pin[9]!=0)  jl+=4;
              if (pin[10]!=0)  jl+=8;
              if (pin[11]!=0)  jl+=16;
              if (pin[12]!=0)  jl+=32;
              if (pin[29]!=0)  jl+=64;
              if (pin[30]!=0)  jl+=128;
              if (pin[31]!=0)  jh=1;
              if (pin[32]!=0)  jh+=2;
              if (pin[33]!=0)  jh+=4;
              if (pin[34]!=0)  jh+=8;
              if (pin[36]!=0)  i=1;
              if (pin[38]!=0)  i+=2;
              if (pin[1]!=0)  i+=4;
              if (pin[3]!=0)  i+=8;
              i=i*2;
              mem[i]=jl;
              mem[i+1]=jh;
           }
        if (pin[21]!=0)
          {
            for (i=14;i<=19;i++) pin[i]=2;
            for (i=22;i<=27;i++) pin[i]=2;
         }
       else
          {
            if (pin[13]!=0)
              {
                for (i=14;i<=19;i++) pin[i]=1;
                for (i=22;i<=23;i++) pin[i]=1;
                if (pin[35]!=0)  pin[24]=1; else pin[24]=0;
                if (pin[37]!=0)  pin[25]=1; else pin[25]=0;
                if (pin[39]!=0)  pin[26]=1; else pin[26]=0;
                if (pin[2]!=0)  pin[27]=1; else pin[27]=0;
             }
           else
              {
                if (pin[35]!=0)  i=1;
                if (pin[37]!=0)  i+=2;
                if (pin[39]!=0)  i+=4;
                if (pin[2]!=0)  i+=8;
                i=i*2;
                jl=mem[i];
                jh=mem[i+1];
                if ((jl & 1)==1)  pin[14]=1; else pin[14]=0;
                if ((jl & 2)==2)  pin[15]=1; else pin[15]=0;
                if ((jl & 4)==4)  pin[16]=1; else pin[16]=0;
                if ((jl & 8)==8)  pin[17]=1; else pin[17]=0;
                if ((jl & 16)==16)  pin[18]=1; else pin[18]=0;
                if ((jl & 32)==32)  pin[19]=1; else pin[19]=0;
                if ((jl & 64)==64)  pin[22]=1; else pin[22]=0;
                if ((jl & 128)==128)  pin[23]=1; else pin[23]=0;
                if ((jh & 1)==1)  pin[24]=1; else pin[24]=0;
                if ((jh & 2)==2)  pin[25]=1; else pin[25]=0;
                if ((jh & 4)==4)  pin[26]=1; else pin[26]=0;
                if ((jh & 8)==8)  pin[27]=1; else pin[27]=0;
             }
         }
     }
    return 0;
    }
  }

