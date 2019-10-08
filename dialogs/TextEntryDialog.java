package dialogs;
import java.awt.*;
import java.awt.event.*;

public class TextEntryDialog extends Dialog implements ActionListener {
  Panel dialogPanel;
  Panel textPanel;
  Button yesButton;
  Button noButton;
  TextField textEntry;
  int yesNo;
  public TextEntryDialog(Frame window,String title,String prompt,int x,int y) {
    super(window,title,true);
    this.yesNo=-1;
    this.setSize(400,100);
    this.setLocation(x,y);
    textPanel=new Panel();
    textPanel.setVisible(true);
    textEntry=new TextField(prompt,40);
    textEntry.setVisible(true);
    textPanel.add(textEntry);
    dialogPanel=new Panel();
    dialogPanel.setVisible(true);
    yesButton=new Button("OK");
    yesButton.setVisible(true);
    yesButton.addActionListener(this);
    noButton=new Button("Cancel");
    noButton.setVisible(true);
    noButton.addActionListener(this);
    dialogPanel.add(yesButton);
    dialogPanel.add(noButton);
    this.add(textPanel);
    this.add(dialogPanel,"South");
    this.setVisible(true);
    }
  public int getValue() {
    return yesNo;
    }
  public String getText() {
    if (yesNo==1) return textEntry.getText();
      else return null;
    }
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand()=="OK") {
      this.yesNo=1;
      setVisible(false);
      }
    if (e.getActionCommand()=="Cancel") {
      this.yesNo=0;
      setVisible(false);
      }
    }
}

