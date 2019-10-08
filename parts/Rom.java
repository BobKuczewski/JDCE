package parts;

import java.awt.*;
import java.io.*;
import parts.*;

public class Rom extends Ram {
  public byte[] memory;
  public String name2;
  public Rom () {
    super();
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
      name=new String(buffer.toString());
      loadRom();
      }
    fileDialog=null;
    }
  public void drawBottom(Graphics g,int offsetX,int offsetY) {
    super.drawBottom(g,offsetX,offsetY);
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=(y-offsetY)*8;
    g.setColor(Color.white);
    g.drawString(name2,ox,oy+32);
    }
  public void drawTop(Graphics g,int offsetX,int offsetY,int size) {
    super.drawTop(g,offsetX,offsetY,size);
    int ox,oy;
    ox=(x-offsetX)*8;
    oy=size-(y-offsetY)*8;
    g.setColor(Color.white);
    g.drawString(name2,ox,oy-32);
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
    buffer.append(name);
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
    buffer.append(name);
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
            case ' ':if (mode=='B' && newByte==1) memory[addr++]=(byte)byt;
                     byt=0; mode='B'; newByte=0;
                     break;
            }
          j++;
          }
        if (mode=='B' && newByte==1) memory[addr++]=(byte)byt;
        byt=0; mode='B'; newByte=0;
        }
      file.close();
      } catch (IOException e) {
      }
    }
  }
