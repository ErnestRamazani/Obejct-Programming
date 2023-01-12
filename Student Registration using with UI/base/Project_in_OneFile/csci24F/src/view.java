//class for the view option, helping to change the theme 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

public class view extends JComponent implements ActionListener {

  int styl=0;
  String name="Arial";
  int size=12;

  JDialog F=new JDialog(new JDialog());
  JPanel panels = new JPanel();
  JLabel label_choice = new JLabel();
  JPanel pn_choice = new JPanel();
  JComboBox styles = new JComboBox();
  JComboBox heigth = new JComboBox();
  JButton apply = new JButton();
  JButton back = new JButton();
  JCheckBox bold = new JCheckBox();
  JCheckBox italic = new JCheckBox();
  JComboBox choices = new JComboBox();
  JButton view = new JButton();
  JLabel title = new JLabel();
  JLabel lb_height = new JLabel();
  JLabel lb_style = new JLabel();
  JPanel pn_view = new JPanel();
  JLabel lb_test = new JLabel();

  public view() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    panels.setBackground(Color.lightGray);
    panels.setBounds(new Rectangle(0, 1, 406, 305));
    panels.setLayout(null);
    label_choice.setFont(new java.awt.Font("Monospaced", 0, 11));
  
    label_choice.setBounds(new Rectangle(3, 54, 193, 32));
    pn_choice.setBackground(SystemColor.inactiveCaptionText);
    pn_choice.setBounds(new Rectangle(10, 122, 372, 46));
    pn_choice.setLayout(null);
    styles.setBounds(new Rectangle(76, 14, 113, 19));
    styles.addActionListener(this);
    styles.addItem("Arial");
    styles.addItem("SansSerif");
    styles.addItem("Times New Roman");
    styles.addItem("Snap ITC");
    styles.addItem("Algerian");
    styles.setSelectedIndex(1);
    heigth.setBounds(new Rectangle(239, 14, 42, 19));
    heigth.addItem("8");
    heigth.addItem("9");
    heigth.addItem("10");
    heigth.addItem("11");
    heigth.addItem("12");
    heigth.addItem("14");
    heigth.addItem("16");
    heigth.addItem("20");
    heigth.addItem("24");
    heigth.setSelectedIndex(4);
    apply.setBounds(new Rectangle(90, 266, 108, 22));
    apply.setText("Apply");
    apply.addActionListener(this);//ecouteur
    back.setText("Back");
    back.setBounds(new Rectangle(196, 266, 108, 22));
    back.addActionListener(this);//ecouteur
    bold.setBackground(SystemColor.inactiveCaptionText);
    bold.setText("Bold");
    bold.setBounds(new Rectangle(287, 6, 80, 19));
    italic.setBounds(new Rectangle(287, 23, 80, 19));
    italic.setBackground(SystemColor.inactiveCaptionText);
    italic.setText("Italic");
    choices.setBounds(new Rectangle(132, 85, 125, 24));
    choices.addItem("Title");
    choices.addItem("Others");
    view.setBounds(new Rectangle(148, 181, 97, 24));
    view.setText("Preview");
    view.addActionListener(this);//ecouteur
    title.setFont(new Font("DialogInput", 0, 16));
    title.setForeground(SystemColor.textHighlight);
    
    title.setBounds(new Rectangle(105, 11, 184, 38));
    lb_height.setText("Height:");
    lb_height.setBounds(new Rectangle(199, 17, 34, 15));
    lb_style.setText("Style:");
    lb_style.setBounds(new Rectangle(24, 18, 34, 15));
    pn_view.setBackground(Color.white);
    pn_view.setBounds(new Rectangle(17, 208, 349, 48));
    pn_view.setLayout(null);
    lb_test.setText("View");
    lb_test.setBounds(new Rectangle(125, 4, 211, 41));
    lb_test.setFont(new Font(name,styl,size));
    pn_choice.add(italic, null);
    pn_choice.add(bold, null);
    pn_choice.add(heigth, null);
    pn_choice.add(lb_height, null);
    pn_choice.add(styles, null);
    pn_choice.add(lb_style, null);
    panels.add(view, null);
    panels.add(choices, null);
    panels.add(label_choice, null);
    panels.add(title, null);
    panels.add(apply, null);
    panels.add(back, null);

    F.setTitle("Theme");
    F.getContentPane().add(this);
    this.add(panels, null);
    panels.add(pn_view, null);
    pn_view.add(lb_test, null);
    panels.add(pn_choice, null);
    F.setSize(new Dimension(415, 341));
    F.show();

  }

  public void actionPerformed(ActionEvent ae){
    if(ae.getSource()==view){
      setfont();
      lb_test.setFont(new Font(name,styl,size));
    }else if(ae.getSource()==apply){
      setfont();
      if(choices.getSelectedItem().toString().equals("Title")){
         MainClass.titlefont=new Font(name,styl,size);

      }
      else if(choices.getSelectedItem().toString().equals("Others")){
        MyJlabel.font=new Font(name,styl,size);
  
      }
    }
    else if(ae.getSource()==back){
      MainMenu.F.enable();
      F.dispose();
    }
  }

  public static void main(String[] args) {
    new view();
  }

  //methond that change the font according to the user choice
  public void setfont(){
    size=Integer.parseInt(heigth.getSelectedItem().toString());
    name=styles.getSelectedItem().toString();
    if(bold.isSelected() && italic.isSelected()){
      styl=3;
    }else if(italic.isSelected()){
      styl=2;
    }else if(bold.isSelected()){
      styl=1;
    }else{
      styl=0;
    }
  }

}


