package dialogs;
import java.awt.*;
import java.awt.event.*;

public class MsgBox extends Dialog implements ActionListener {
  Panel dialogPanel;
  Panel textPanel;
  Button yesButton;
  public MsgBox(Frame window,String title,String prompt) {
    super(window,title,true);
    this.setSize(200,100);
    textPanel=new Panel();
    textPanel.setVisible(true);
    textPanel.add(new Label(prompt));
    dialogPanel=new Panel();
    dialogPanel.setVisible(true);
    yesButton=new Button("OK");
    yesButton.setVisible(true);
    yesButton.addActionListener(this);
    dialogPanel.add(yesButton);
    this.add(textPanel);
    this.add(dialogPanel,"South");
    this.setVisible(true);
    }
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand()=="OK") {
      setVisible(false);
      }
    }
}

