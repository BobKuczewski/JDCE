package dialogs;
import java.awt.*;
import java.awt.event.*;

public class YesNoDialog extends Dialog implements ActionListener {
  Panel dialogPanel;
  Panel textPanel;
  Button yesButton;
  Button noButton;
  int yesNo;
  public YesNoDialog(Frame window,String title,String prompt) {
    super(window,title,true);
    this.yesNo=-1;
    this.setSize(200,100);
    textPanel=new Panel();
    textPanel.setVisible(true);
    textPanel.add(new Label(prompt));
    dialogPanel=new Panel();
    dialogPanel.setVisible(true);
    yesButton=new Button("Yes");
    yesButton.setVisible(true);
    yesButton.addActionListener(this);
    noButton=new Button("No");
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
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand()=="Yes") {
      this.yesNo=1;
      setVisible(false);
      }
    if (e.getActionCommand()=="No") {
      this.yesNo=0;
      setVisible(false);
      }
    }
}

