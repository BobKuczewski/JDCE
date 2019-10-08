package parts.pld;

import java.io.*;
import java.awt.*;
import parts.*;

public class C5C031 extends Ic {
  int[] mem;
  public C5C031() {
    super();
    int i;
    name=new String("5C031");
    pin=new int[41];
    mem=new int[1024];
    for (i=0;i<1000;i++) mem[i]=0;
    }
  public void init(Frame window) {
    int j;
    StringBuffer buffer=new StringBuffer();
    FileDialog fileDialog;
    fileDialog=new FileDialog(window,"Load PLD file",FileDialog.LOAD);
    fileDialog.show();
    if (fileDialog.getFile()!=null) {
      buffer.setLength(0);
      buffer.append(fileDialog.getFile());
      j=0;
      while (j<buffer.length() && buffer.charAt(j)!='.') j++;
      buffer.setLength(j);
      name=new String(buffer.toString());
      loadPld();
      }
    fileDialog=null;
    }
  public int numPins() {
    return 20;
    }
  public int pinOut(int pn) {
    if ((pn<12) || (pn==20))  return 0;
     else
        return 128+pin[37-(pn-12)];
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
    name=buffer2.toString();
    loadPld();
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
    buffer.append(name);
    buffer.append("\n");
    return buffer.toString();
    }
  private int ma(int ct,int st) {
    int m;
    int c;
    int t;
    int i;
    m=1; t=0; c=st; i=1;
    while (i<=ct) {
        if ((mem[c] & m)!=m) {
            if (mem[i]!=0)  t+=1;
             else i=ct;
         } else t++;
        m+=m;
        if (m==256) {
            m=1;
            c++;
         }
        i++;
     }
    if (t==ct)  return 1; else  return 0;
    }
  public int run() {
    int i;
    int p;
    int j;
    int l;
    int pre;
    int clr;
    for (i=1;i<=10;i++)
      {
        p=i; if (p==10)  p=11;
        if (pin[p]==0) 
          {
            mem[i]=0;
            mem[18+i]=1;
         }
       else
          {
            mem[i]=1;
            mem[18+i]=0;
         }
     }
    for (i=0;i<=7;i++)
      {
        l=100+50*i;
        j=ma(36,l);
        if (j==0)  j=ma(36,l+5);
        if (j==0)  j=ma(36,l+10);
        if (j==0)  j=ma(36,l+15);
        if (j==0)  j=ma(36,l+20);
        if (j==0)  j=ma(36,l+25);
        if (j==0)  j=ma(36,l+30);
        if (j==0)  j=ma(36,l+35);
        if (j!=0)  mem[70+i]=1; else mem[70+i]=0;
     }
    pre=ma(36,500);
    clr=ma(36,550);
    for (i=0;i<=7;i++)
      {
        if (mem[80+i]>1)  mem[80+i]=0;
        if (pin[1]!=0 && pin[21]==0) 
          mem[80+i]=mem[70+i];
        if (pre!=0)  mem[80+i]=1;
        if (clr!=0)  mem[80+i]=0;
        switch (mem[50+i]) {
         case 1:mem[90+i]=mem[70+i]; break;
         case 2:mem[90+i]=f_inv(mem[70+i]); break;
         case 3:mem[90+i]=mem[80+i]; break;
         case 4:mem[90+i]=f_inv(mem[80+i]); break;
         }
        l=100+50*i;
        j=ma(36,l+40);
        pin[30+i]=j;
        if (j!=0)  pin[19-i]=mem[90+i]; else pin[19-i]=2;
        switch (mem[60+i]) {
         case 1:
              mem[11+i]=mem[70+i];
              mem[29+i]=f_inv(mem[70+i]);
              break;
         case 2:
              mem[11+i]=mem[80+i];
              mem[29+i]=f_inv(mem[80+i]);
              break;
         case 3:
              mem[11+i]=pin[19-i];
              mem[29+i]=f_inv(pin[19-i]);
              break;
         }
     }
    pin[21]=pin[1];
    return 0;
    }
  private int pos(char key,String strng) {
    int ret;
    int i;
    ret=0;
    i=0;
    while (ret==0 && i<strng.length()) {
      if (key==strng.charAt(i)) ret=i+1;
      i++;
      }
    return ret;
    }

  void progMcell(String buffer,int elem,int mcell)
  {
    int i,p,m,j;
    int[] macro=new int[37];
    int element;
    char chr;
    element=elem;
    for (j=1;j<=36;j++) macro[j]=1;
    for (i=0;i<buffer.length();i++) {
        chr=buffer.charAt(i);
        if (chr=='+') {
          p=100+(mcell-1)*50+(element-1)*5;
          mem[p]=0; m=1;
          for (j=1;j<=36;j++) {
              if (macro[j]==1) mem[p]=mem[p]+m;
              m=m+m;
              if (m>128) { p++; m=1; }
              }
          for (j=1;j<=36;j++) macro[j]=1;
          element++;
          } else {
              if (chr=='/') {
                i++; chr=buffer.charAt(i);
                macro[18+pos(chr,"ABCDEFGHIJKLMNOPQR")]=0;
              } else macro[pos(chr,"ABCDEFGHIJKLMNOPQR")]=0;
            }
       }
    p=100+(mcell-1)*50+(element-1)*5;
    mem[p]=0; m=1;
    for (i=1;i<=36;i++) {
        if (macro[i]==1) mem[p]=mem[p]+m;
        m=m+m;
        if (m>128) { p++; m=1; }
        }
  }
  
  void loadPld() {
    int i,k;
    StringBuffer buffer=new StringBuffer();;
    BufferedReader file;
    buffer.setLength(0);
    buffer.append(name);
    buffer.append(".pld");
    try {
      file=new BufferedReader(new FileReader(buffer.toString()));
      while (file.ready()) {
        buffer.setLength(0);
        buffer.append(file.readLine());
        if (buffer.length()>8)
          if ("OUTMODE".equals(buffer.toString().substring(0,7))) {
            k=(new Integer(buffer.toString().substring(7,8))).intValue();
            i=(new Integer(buffer.toString().substring(9,10))).intValue();
            mem[49+k]=i;
            }
        if (buffer.length()>8)
          if ("FEEDBACK".equals(buffer.toString().substring(0,8))) {
            k=(new Integer(buffer.toString().substring(8,9))).intValue();
            i=(new Integer(buffer.toString().substring(10,11))).intValue();
            mem[59+k]=i;
            }
        if (buffer.length()>3)
          if (buffer.charAt(0)=='Q') {
            k=(new Integer(buffer.toString().substring(1,2))).intValue();
            progMcell(buffer.toString().substring(3,buffer.length()),1,k);
            }
        if (buffer.length()>3)
          if (buffer.charAt(0)=='O' && buffer.charAt(1)!='U') {
            k=(new Integer(buffer.toString().substring(1,2))).intValue();
            progMcell(buffer.toString().substring(3,buffer.length()),9,k);
            }
        if (buffer.length()>6)
          if ("PRESET".equals(buffer.toString().substring(0,6))) {
            progMcell(buffer.toString().substring(7,buffer.length()),1,9);
            }
        if (buffer.length()>6)
          if ("RESET".equals(buffer.toString().substring(0,5))) {
            progMcell(buffer.toString().substring(6,buffer.length()),1,10);
            }
        } /* end while */
        file.close();
      } catch (IOException e) {
        }
    }
}
