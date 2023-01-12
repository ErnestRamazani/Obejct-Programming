// This class is the About US class. 
// It is a new opened panel that talks about the developper of this application

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;


public class AboutUs extends JComponent implements ActionListener{
  JDialog window=new JDialog();
  JPanel jPanel1 = new JPanel();
  TitledBorder titledBorder1;
  JButton jButton1 = new JButton();
  TitledBorder titledBorder2;
  TitledBorder titledBorder3;
  JLabel jLabel1 = new JLabel();

 
  JButton back = new JButton();
  public AboutUs() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.setOpaque(true);
    this.setLayout(null);
    jPanel1.setBackground(Color.gray);
    jPanel1.setEnabled(true);
    jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel1.setBounds(new Rectangle(36, 8, 382, 283));
    jPanel1.setLayout(null);
   
    jLabel1.setFont(new java.awt.Font("Monospaced", 0, 10));
    jLabel1.setForeground(Color.white);
    jLabel1.setText("type some bout this project");
    jLabel1.setBounds(new Rectangle(10, 58, 250, 42));
    
    back.setBackground(Color.LIGHT_GRAY);
    back.setBounds(new Rectangle(190, 307, 78, 28));
    back.setForeground(Color.black);
    back.setText("Back");
    back.addActionListener(this);
    jPanel1.add(jButton1, null);
   
    jPanel1.add(jLabel1, null);
   
    this.add(back, null);
    this.add(jPanel1, null);

    window.getContentPane().add(this);
    window.setSize(450,400);
    window.setLocation(300,300);
    window.show();
  }



  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==back){
      MainMenu.F.enable();
      window.dispose();
    }
  }
}

