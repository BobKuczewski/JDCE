package parts.input;
import java.awt.*;
import dce.*;
import parts.*;
import java.awt.*;
import java.awt.event.*;

public class CKeyboard extends Ic implements KeyListener {
  private int[] keys;
  private int[] matrix;
  public CKeyboard() {
    super();
    name=new String("Keyboard");
    pin=new int[41];
    keys=new int[91];
    matrix=new int[8];
    }
  public int numPins() {
    return 24;
    }
  public void reset() {
    int i;
    for (i=0;i<88;i++) keys[i]=0;
    }
  public int pinOut(int pn) {
    if (pn>=13 && pn<=24) return 1; else return 0;
    }
  private void setMatrix(int num,int sig) {
    if (matrix[num]==2) matrix[num]=sig;
    else if (matrix[num]==0 && sig==1) matrix[num]=1;
    }
  private void readMatrix(int row) {
    int offset=row*8;
    if (keys[offset+0]==1) setMatrix(0,pin[row+1]);
    if (keys[offset+1]==1) setMatrix(1,pin[row+1]);
    if (keys[offset+2]==1) setMatrix(2,pin[row+1]);
    if (keys[offset+3]==1) setMatrix(3,pin[row+1]);
    if (keys[offset+4]==1) setMatrix(4,pin[row+1]);
    if (keys[offset+5]==1) setMatrix(5,pin[row+1]);
    if (keys[offset+6]==1) setMatrix(6,pin[row+1]);
    if (keys[offset+7]==1) setMatrix(7,pin[row+1]);
    }
  public int run() {
    int i;
    matrix[0]=2; matrix[1]=2; matrix[2]=2; matrix[3]=2;
    matrix[4]=2; matrix[5]=2; matrix[6]=2; matrix[7]=2;
    for (i=0;i<11;i++) readMatrix(i);
    pin[13]=matrix[0]; pin[14]=matrix[1];
    pin[15]=matrix[2]; pin[16]=matrix[3];
    pin[17]=matrix[4]; pin[18]=matrix[5];
    pin[19]=matrix[6]; pin[20]=matrix[7];
    return 0;
    }
  public void keyTyped(KeyEvent e) {
    }
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyChar()) {
/* row 1 */
      case '0':keys[0]=1; keys[88]=0; break;
      case '1':keys[1]=1; keys[88]=0; break;
      case '!':keys[1]=1; keys[88]=1; break;
      case '2':keys[2]=1; keys[88]=0; break;
      case '"':keys[2]=1; keys[88]=1; break;
      case '3':keys[3]=1; keys[88]=0; break;
      case '#':keys[3]=1; keys[88]=1; break;
      case '4':keys[4]=1; keys[88]=0; break;
      case '$':keys[4]=1; keys[88]=1; break;
      case '5':keys[5]=1; keys[88]=0; break;
      case '%':keys[5]=1; keys[88]=1; break;
      case '6':keys[6]=1; keys[88]=0; break;
      case '&':keys[6]=1; keys[88]=1; break;
      case '7':keys[7]=1; keys[88]=0; break;
      case '\'':keys[7]=1; keys[88]=1; break;
/* row 2 */
      case '8':keys[8]=1; keys[88]=0; break;
      case '(':keys[8]=1; keys[88]=1; break;
      case '9':keys[9]=1; keys[88]=0; break;
      case ')':keys[9]=1; keys[88]=1; break;
      case ':':keys[10]=1; keys[88]=0; break;
      case '*':keys[10]=1; keys[88]=1; break;
      case ';':keys[11]=1; keys[88]=0; break;
      case '+':keys[11]=1; keys[88]=1; break;
      case ',':keys[12]=1; keys[88]=0; break;
      case '<':keys[12]=1; keys[88]=1; break;
      case '-':keys[13]=1; keys[88]=0; break;
      case '=':keys[13]=1; keys[88]=1; break;
      case '.':keys[14]=1; keys[88]=0; break;
      case '>':keys[14]=1; keys[88]=1; break;
      case '/':keys[15]=1; keys[88]=0; break;
      case '?':keys[15]=1; keys[88]=1; break;
/* row 3 */
      case '@':keys[16]=1; keys[88]=0; break;
      case '`':keys[16]=1; keys[88]=1; break;
      case 'a':keys[17]=1; keys[88]=0; break;
      case 'A':keys[17]=1; keys[88]=1; break;
      case 'b':keys[18]=1; keys[88]=0; break;
      case 'B':keys[18]=1; keys[88]=1; break;
      case 'c':keys[19]=1; keys[88]=0; break;
      case 'C':keys[19]=1; keys[88]=1; break;
      case 'd':keys[20]=1; keys[88]=0; break;
      case 'D':keys[20]=1; keys[88]=1; break;
      case 'e':keys[21]=1; keys[88]=0; break;
      case 'E':keys[21]=1; keys[88]=1; break;
      case 'f':keys[22]=1; keys[88]=0; break;
      case 'F':keys[22]=1; keys[88]=1; break;
      case 'g':keys[23]=1; keys[88]=0; break;
      case 'G':keys[23]=1; keys[88]=1; break;
/* row 4 */
      case 'h':keys[24]=1; keys[88]=0; break;
      case 'H':keys[24]=1; keys[88]=1; break;
      case 'i':keys[25]=1; keys[88]=0; break;
      case 'I':keys[25]=1; keys[88]=1; break;
      case 'j':keys[26]=1; keys[88]=0; break;
      case 'J':keys[26]=1; keys[88]=1; break;
      case 'k':keys[27]=1; keys[88]=0; break;
      case 'K':keys[27]=1; keys[88]=1; break;
      case 'l':keys[28]=1; keys[88]=0; break;
      case 'L':keys[28]=1; keys[88]=1; break;
      case 'm':keys[29]=1; keys[88]=0; break;
      case 'M':keys[29]=1; keys[88]=1; break;
      case 'n':keys[30]=1; keys[88]=0; break;
      case 'N':keys[30]=1; keys[88]=1; break;
      case 'o':keys[31]=1; keys[88]=0; break;
      case 'O':keys[31]=1; keys[88]=1; break;
/* row 5 */
      case 'p':keys[32]=1; keys[88]=0; break;
      case 'P':keys[32]=1; keys[88]=1; break;
      case 'q':keys[33]=1; keys[88]=0; break;
      case 'Q':keys[33]=1; keys[88]=1; break;
      case 'r':keys[34]=1; keys[88]=0; break;
      case 'R':keys[34]=1; keys[88]=1; break;
      case 's':keys[35]=1; keys[88]=0; break;
      case 'S':keys[35]=1; keys[88]=1; break;
      case 't':keys[36]=1; keys[88]=0; break;
      case 'T':keys[36]=1; keys[88]=1; break;
      case 'u':keys[37]=1; keys[88]=0; break;
      case 'U':keys[37]=1; keys[88]=1; break;
      case 'v':keys[38]=1; keys[88]=0; break;
      case 'V':keys[38]=1; keys[88]=1; break;
      case 'w':keys[39]=1; keys[88]=0; break;
      case 'W':keys[39]=1; keys[88]=1; break;
/* row 6 */
      case 'x':keys[40]=1; keys[88]=0; break;
      case 'X':keys[40]=1; keys[88]=1; break;
      case 'y':keys[41]=1; keys[88]=0; break;
      case 'Y':keys[41]=1; keys[88]=1; break;
      case 'z':keys[42]=1; keys[88]=0; break;
      case 'Z':keys[42]=1; keys[88]=1; break;
      case '[':keys[43]=1; keys[88]=0; break;
      case '{':keys[43]=1; keys[88]=1; break;
      case '|':keys[44]=1; keys[88]=0; break;
      case '\\':keys[44]=1; keys[88]=1; break;
      case ']':keys[45]=1; keys[88]=0; break;
      case '}':keys[45]=1; keys[88]=1; break;
      case '^':keys[46]=1; keys[88]=0; break;
      case '~':keys[46]=1; keys[88]=1; break;
      }
    }
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyChar()) {
/* row 1 */
      case '0':keys[0]=0; keys[88]=0; break;
      case '1':keys[1]=0; keys[88]=0; break;
      case '!':keys[1]=0; keys[88]=0; break;
      case '2':keys[2]=0; keys[88]=0; break;
      case '"':keys[2]=0; keys[88]=0; break;
      case '3':keys[3]=0; keys[88]=0; break;
      case '#':keys[3]=0; keys[88]=0; break;
      case '4':keys[4]=0; keys[88]=0; break;
      case '$':keys[4]=0; keys[88]=0; break;
      case '5':keys[5]=0; keys[88]=0; break;
      case '%':keys[5]=0; keys[88]=0; break;
      case '6':keys[6]=0; keys[88]=0; break;
      case '&':keys[6]=0; keys[88]=0; break;
      case '7':keys[7]=0; keys[88]=0; break;
      case '\'':keys[7]=0; keys[88]=0; break;
/* row 2 */
      case '8':keys[8]=0; keys[88]=0; break;
      case '(':keys[8]=0; keys[88]=0; break;
      case '9':keys[9]=0; keys[88]=0; break;
      case ')':keys[9]=0; keys[88]=0; break;
      case ':':keys[10]=0; keys[88]=0; break;
      case '*':keys[10]=0; keys[88]=0; break;
      case ';':keys[11]=0; keys[88]=0; break;
      case '+':keys[11]=0; keys[88]=0; break;
      case ',':keys[12]=0; keys[88]=0; break;
      case '<':keys[12]=0; keys[88]=0; break;
      case '-':keys[13]=0; keys[88]=0; break;
      case '=':keys[13]=0; keys[88]=0; break;
      case '.':keys[14]=0; keys[88]=0; break;
      case '>':keys[14]=0; keys[88]=0; break;
      case '/':keys[15]=0; keys[88]=0; break;
      case '?':keys[15]=0; keys[88]=0; break;
/* row 3 */
      case '@':keys[16]=0; keys[88]=0; break;
      case '`':keys[16]=0; keys[88]=0; break;
      case 'a':keys[17]=0; keys[88]=0; break;
      case 'A':keys[17]=0; keys[88]=0; break;
      case 'b':keys[18]=0; keys[88]=0; break;
      case 'B':keys[18]=0; keys[88]=0; break;
      case 'c':keys[19]=0; keys[88]=0; break;
      case 'C':keys[19]=0; keys[88]=0; break;
      case 'd':keys[20]=0; keys[88]=0; break;
      case 'D':keys[20]=0; keys[88]=0; break;
      case 'e':keys[21]=0; keys[88]=0; break;
      case 'E':keys[21]=0; keys[88]=0; break;
      case 'f':keys[22]=0; keys[88]=0; break;
      case 'F':keys[22]=0; keys[88]=0; break;
      case 'g':keys[23]=0; keys[88]=0; break;
      case 'G':keys[23]=0; keys[88]=0; break;
/* row 4 */
      case 'h':keys[24]=0; keys[88]=0; break;
      case 'H':keys[24]=0; keys[88]=0; break;
      case 'i':keys[25]=0; keys[88]=0; break;
      case 'I':keys[25]=0; keys[88]=0; break;
      case 'j':keys[26]=0; keys[88]=0; break;
      case 'J':keys[26]=0; keys[88]=0; break;
      case 'k':keys[27]=0; keys[88]=0; break;
      case 'K':keys[27]=0; keys[88]=0; break;
      case 'l':keys[28]=0; keys[88]=0; break;
      case 'L':keys[28]=0; keys[88]=0; break;
      case 'm':keys[29]=0; keys[88]=0; break;
      case 'M':keys[29]=0; keys[88]=0; break;
      case 'n':keys[30]=0; keys[88]=0; break;
      case 'N':keys[30]=0; keys[88]=0; break;
      case 'o':keys[31]=0; keys[88]=0; break;
      case 'O':keys[31]=0; keys[88]=0; break;
/* row 5 */
      case 'p':keys[32]=0; keys[88]=0; break;
      case 'P':keys[32]=0; keys[88]=0; break;
      case 'q':keys[33]=0; keys[88]=0; break;
      case 'Q':keys[33]=0; keys[88]=0; break;
      case 'r':keys[34]=0; keys[88]=0; break;
      case 'R':keys[34]=0; keys[88]=0; break;
      case 's':keys[35]=0; keys[88]=0; break;
      case 'S':keys[35]=0; keys[88]=0; break;
      case 't':keys[36]=0; keys[88]=0; break;
      case 'T':keys[36]=0; keys[88]=0; break;
      case 'u':keys[37]=0; keys[88]=0; break;
      case 'U':keys[37]=0; keys[88]=0; break;
      case 'v':keys[38]=0; keys[88]=0; break;
      case 'V':keys[38]=0; keys[88]=0; break;
      case 'w':keys[39]=0; keys[88]=0; break;
      case 'W':keys[39]=0; keys[88]=0; break;
/* row 6 */
      case 'x':keys[40]=0; keys[88]=0; break;
      case 'X':keys[40]=0; keys[88]=0; break;
      case 'y':keys[41]=0; keys[88]=0; break;
      case 'Y':keys[41]=0; keys[88]=0; break;
      case 'z':keys[42]=0; keys[88]=0; break;
      case 'Z':keys[42]=0; keys[88]=0; break;
      case '[':keys[43]=0; keys[88]=0; break;
      case '{':keys[43]=0; keys[88]=0; break;
      case '|':keys[44]=0; keys[88]=0; break;
      case '\\':keys[44]=0; keys[88]=0; break;
      case ']':keys[45]=0; keys[88]=0; break;
      case '}':keys[45]=0; keys[88]=0; break;
      case '^':keys[46]=0; keys[88]=0; break;
      case '~':keys[46]=0; keys[88]=0; break;
      }
    }
  }

