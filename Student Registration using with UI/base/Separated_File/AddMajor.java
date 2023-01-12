

// class for adding a major

import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddMajor extends MainClass implements ActionListener{

  Vector VectMat=new Vector();

  private MyJlabel maj_lb=new MyJlabel("Major: ");
  private JTextField addMaj=new JTextField(5);

  private JButton add=new JButton("Add");
  private JButton back=new JButton("Back");

  private JOptionPane confirm;

  private JPanel center=new JPanel(),sud=new JPanel();


  public AddMajor(){
    super("Add Major",300,300,new Color(01, 90, 50));

    add.addActionListener(this);
    back.addActionListener(this);

    center.add(maj_lb);
    center.add(addMaj);

    sud.add(add);
    sud.add(back);

    getContentPane().add(center,BorderLayout.CENTER);
    getContentPane().add(sud,BorderLayout.SOUTH);
;
    pack();
    show();
  }



  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==add){
      add_actionPerformed();
    }
    else if(ae.getSource()==back){
      MainMenu.F.enable();
      dispose();
    }
  }


  public void add_actionPerformed(){
    String name=addMaj.getText();
    if(name.equals("")){
      maj_lb.setForeground(Color.red);
    }
    else{
      maj_lb.setForeground(Color.black);
      
      //if major already exists
      
      if(majExists(name)){
        confirm.showMessageDialog(null,"The Major Already Exists","Error",JOptionPane.ERROR_MESSAGE);
      }
      else{
        int result=confirm.showConfirmDialog(null,"Do you want to add this MAjor","Adding Major",JOptionPane.YES_NO_OPTION);
        if(result==0){
        	//adding majors with all 5 level
          for (int i = 2; i <= 5; i++) { 
            MainMenu.listMajor.addElement(new Major(name, i,
                new Vector()));
          }
        }
      }
      addMaj.setText("");
    }
  }


  //verifying of major already exists
  public boolean majExists(String name){
    Major f;
    for(int i=0;i<MainMenu.listMajor.size();i++){
      f=(Major)MainMenu.listMajor.elementAt(i); 
      if(f.getName().equalsIgnoreCase(name)){
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args){
    new AddMajor();
  }
}
