package shared;

import java.io.*;

public class MyBufferedInputStream extends BufferedInputStream {
  int lineTerm;
  public MyBufferedInputStream(InputStream stream) {
    super(stream);
    lineTerm=0;
    }
  public boolean ready() throws IOException {
    if (available()>0) return true; else return false;
    }
  public String readLine() throws IOException  {
    int i=0,j=0;
    char flag;
    StringBuffer buffer=new StringBuffer();
    buffer.setLength(0);
    flag='Y';
    while (flag=='Y' && available()>0) {
      i=read();
      if (i>=32 || i==9) buffer.append((char)i);
        else flag='N';
      }
    if (available()>0) {
      mark(2);
      j=read();
      if (j>=32 || j==9 || j==i) reset();
      }
    return buffer.toString();
    }
  }
