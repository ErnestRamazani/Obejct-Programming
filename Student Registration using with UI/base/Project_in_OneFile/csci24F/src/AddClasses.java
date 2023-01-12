//This class is for adding a new class in the database

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;

public class AddClasses extends MainClass implements ActionListener, ItemListener {

  private int lev = 1;
  private String majorName = "Please choose a Major";
  
//showing major list
  
  private JComboBox listFil, listNiv; 

  private MyJlabel fil_lb, className_lb, coef_lb;
  private JTextField class_name, coef;
  
  //showing list of classes 
  private List listClass; 

  private JButton back, add, delete;

  private JPanel center_pn, south_pn, fil_pn, delete_pn, add_pn;

  // radio button to show if the class has a homework
  
  private JRadioButton hwExists; 
  private JOptionPane confirm;

  public AddClasses() {
    super("Add/Delete Class", 400, 300,new Color(01, 90, 50));

    fil_lb = new MyJlabel("Major ");
    listFil = new JComboBox();
    //initializing major list from thr vectorlist 
    listFil.addItem("Select Major");
    Major fi;
    for (int i = 0; i < MainMenu.listMajor.size(); i++) {
      fi = (Major) MainMenu.listMajor.elementAt(i);
      if (fi.getLevel() == 1 || fi.getLevel() == 2) {
        listFil.addItem(fi.getName());
      }
      
    }
    listFil.addItemListener(this); 
    listFil.setSelectedIndex(0); //choosing a major selected by default

    //design for majors list
    listNiv = new JComboBox();
    
    
    //Initializing level elements
    for (int i = 2; i <= 5; i++) {
      listNiv.addItem("" + i);
    }
    listNiv.addItemListener(this);
    listNiv.setVisible(false); 
    listNiv.setSelectedIndex(0);

    //showing level of a major 
    fil_pn = new JPanel();
    fil_pn.setBackground(Color.gray);
    fil_pn.add(fil_lb);
    fil_pn.add(listFil);
    fil_pn.add(listNiv);

    // delete panel
    listClass = new List();
    listClass.setSize(10, 10);
    delete = new JButton("Delete");
    delete.addActionListener(this);
    
    delete_pn = new JPanel();
    delete_pn.add(listClass);
    delete_pn.add(delete);

    //add panel
    className_lb = new MyJlabel("Class Name ");
    class_name = new JTextField(10);
    coef_lb = new MyJlabel("Credit");
    coef = new JTextField(2);
    add = new JButton("Add");
    add.addActionListener(this);
    add.disable();
    hwExists = new JRadioButton("Homework");
    hwExists.setSelected(false);

    JPanel info_mat = new JPanel();
    info_mat.setBackground(Color.gray);
    info_mat.add(className_lb);
    info_mat.add(class_name);
    info_mat.add(coef_lb);
    info_mat.add(coef);
    info_mat.add(add);
    JPanel tp_pn = new JPanel();
    tp_pn.setBackground(Color.gray);
    tp_pn.add(hwExists);
    hwExists.setBackground(Color.gray);
    add_pn = new JPanel(new BorderLayout());
    add_pn.add("North", info_mat);
    add_pn.add("South", tp_pn);

    //center panel
    center_pn = new JPanel(new BorderLayout());
    center_pn.add("North", fil_pn);
    center_pn.add("West", delete_pn);
    center_pn.add("South", add_pn);

    //south panel
    back = new JButton("Back");
    back.addActionListener(this);
    south_pn = new JPanel();
    south_pn.add(back);

    getContentPane().add(center_pn, BorderLayout.CENTER);
    getContentPane().add(south_pn, BorderLayout.SOUTH);

    show();

  }

  public void itemStateChanged(ItemEvent ie) {
    //If the major changes, show level of this major
    if (ie.getSource() == listFil) {
      
        listNiv.setVisible(true);
      }
    

    //showing classes for each major if majors are changed
    
    majorName = listFil.getSelectedItem().toString();
    listClass.removeAll();
    if (!majorName.equals("Choose a major")) { 

      lev = 1;
      
      lev = Integer.parseInt(listNiv.getSelectedItem().toString());
      
      Major f = retMajor(majorName, lev);
      
      //adding news classes in vector ListClass
      StClasses m;
      for (int i = 0; i < f.getListClass().size(); i++) {
        m = (StClasses) f.getListClass().elementAt(i);
        listClass.addItem(m.getName());
      }
    }
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
      add_actionPerformed();
    }
    else if (ae.getSource() == delete) {
      delete_actionPerformed();
    }
    else if (ae.getSource() == back) {
      MainMenu.F.enable();
      dispose();
    }
  }

  //return searched major
  
  public Major retMajor(String nom, int n) {
    Major f = new Major();
    for (int i = 0; i < MainMenu.listMajor.size(); i++) {
      f = (Major) MainMenu.listMajor.elementAt(i);
      if (f.getName().equals(nom) && f.getLevel() == n) {
        return f;
      }
    }
    return f;
  }

  //button add has been clicked
  public void add_actionPerformed() {
    int c = -1, t = -1;
    String name = "";
    boolean state = true; //Show if the writting is correct
    
    try {
      c = Integer.parseInt(coef.getText());
    }
    catch (NumberFormatException ex) {
    }

    if (c < 1) {
      coef_lb.setForeground(Color.red);
      state = false;
    }
    else {
      coef_lb.setForeground(Color.black);
    }
    name = class_name.getText();
    if (name.equals("")) {
      className_lb.setForeground(Color.red);
      state = false;
    }
    else {
      className_lb.setForeground(Color.black);
    }
    if (majorName.equals("Choose a major")) {
      confirm.showMessageDialog(null, "No Major Added",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
    }
    else {
      if (state) { //writting is correct 
    	  
        int result = confirm.showConfirmDialog(null,
            "Do you want to add this class?", "Adding class",
            JOptionPane.YES_NO_OPTION);
        if (result == 0) { // result Yes
          if (hwExists.isSelected()) { 
            t = 0;
          }
          Major fil = retMajor(majorName, lev);
          StClasses m = new StClasses(name, 0, 0, t, c);
          
          //Adding class to major
          fil.getListClass().addElement(m);  
          listClass.addItem(name);
          //Adding class to student in the major
          Student e;
          for (int i = 0; i < MainMenu.listStudents.size(); i++) {
            e = (Student) MainMenu.listStudents.elementAt(i);
            if (e.getMajor().getName().equals(majorName) &&
                e.getMajor().getLevel() == lev) {
              e.getMajor().getListClass().addElement(m); //adding class to student 
            }
          } 
        }
        class_name.setText("");
        coef.setText("");
      }
    }
  }

  public void delete_actionPerformed() {
    System.out.println(majorName);
    //no class chosen
    if (majorName.equals("Choose a major")) { 
      confirm.showMessageDialog(null, "No Major Chosen",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
    }
    else {
    	
    	// index of the chosen class
      int selec = listClass.getSelectedIndex(); 
      //no class chosen
      if (selec == -1) { 
        confirm.showMessageDialog(null, "No Class Chosen",
                                  "ERROR",
                                  JOptionPane.ERROR_MESSAGE);
      }
      else {
        int resultat = confirm.showConfirmDialog(null,
            "Do you want to delete this class",
            "Warning",
            JOptionPane.YES_NO_OPTION);
        if (resultat == 0) { 
        	
        	//deleting class from the list 
        	
          listClass.remove(selec); 
          
          //deleting major
          
          Major fil = retMajor(majorName, lev);
          fil.getListClass().removeElementAt(selec); 
          
        //deleting class from student 
          Student e;
          for (int i = 0; i < MainMenu.listStudents.size(); i++) {
            e = (Student) MainMenu.listStudents.elementAt(i);
            if (e.getMajor().getName().equals(majorName) &&
                e.getMajor().getLevel() == lev) {
              e.getMajor().getListClass().removeElementAt(selec);
            }
          }
        }
      }
    }
    {

    }
  }

}