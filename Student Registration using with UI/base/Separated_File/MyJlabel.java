

//class for helping to change font of labels of all frames from the option view

import java.awt.*;
import javax.swing.*;

public class MyJlabel extends JLabel{
  static Font font=new Font("Arial",0,12);
  public MyJlabel(String title) {
    super(title);
    setFont(font);
  }
}