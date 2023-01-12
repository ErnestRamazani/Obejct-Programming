//class for scores of student 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;

public class Notes
    extends MainClass
    implements ItemListener, ActionListener {

  private int index = -1; //index of searched student in the student vectors
  private boolean hw_exists = true; 

  private MyJlabel id_lb, className_lb, ex_lb, ds_lb, hw_lb, lName_lb, fName_lb,
      maj_lb;
  private JLabel lName, fName, maj;

  private JComboBox className;

  private JTextField id, ex, ds, hw;

  private JPanel search_pn, notes_pn, class_pn, infos_pn, ss_pn, center_pn;

  private JButton searchSt, add, back;

  private JOptionPane confirm;

  public Notes() {
    super("Scores", 400, 300,new Color(01, 90, 50));

   
    id_lb = new MyJlabel("Student Id");
    id = new JTextField(10);
    searchSt = new JButton("Search");
    searchSt.addActionListener(this);
    back = new JButton("Back");
    back.addActionListener(this);
    search_pn = new JPanel();
    search_pn.add(id_lb);
    search_pn.add(id);
    search_pn.add(searchSt);
    search_pn.add(back);
    search_pn.setBackground(Color.gray);

    
    lName_lb = new MyJlabel("LastName");
    lName_lb.setForeground(Color.gray);
    fName_lb = new MyJlabel("FirstName");
    fName_lb.setForeground(Color.gray);
    maj_lb = new MyJlabel("Major");
    maj_lb.setForeground(Color.gray);
    lName = new JLabel();
    fName = new JLabel();
    maj = new JLabel();
    infos_pn = new JPanel();
    infos_pn.add(lName_lb);
    infos_pn.add(lName);
    infos_pn.add(fName_lb);
    infos_pn.add(fName);
    infos_pn.add(maj_lb);
    infos_pn.add(maj);

    className_lb = new MyJlabel("Class");
    className = new JComboBox();
    className.addItemListener(this); 
    class_pn = new JPanel();
    class_pn.add(className_lb);
    class_pn.add(className);

    ex_lb = new MyJlabel("Ex:");
    ex = new JTextField(5);
    ds_lb = new MyJlabel("Ds:");
    ds = new JTextField(5);
    hw_lb = new MyJlabel("TP:");
    hw = new JTextField(5);
    add = new JButton("Add");
    add.addActionListener(this);
    notes_pn = new JPanel();
    notes_pn.add(ex_lb);
    notes_pn.add(ex);
    notes_pn.add(ds_lb);
    notes_pn.add(ds);
    notes_pn.add(hw_lb);
    notes_pn.add(hw);
    notes_pn.add(add);

   
    ss_pn = new JPanel();
    ss_pn.setLayout(new BorderLayout());
    class_pn.setBackground(Color.gray);
    notes_pn.setBackground(Color.gray);
    ss_pn.add("North", class_pn);
    ss_pn.add("South", notes_pn);

    center_pn = new JPanel();
    center_pn.setLayout(new BorderLayout());
    center_pn.add("North", search_pn);
    center_pn.add("Center", infos_pn);
    center_pn.add("South", ss_pn);

    infos_pn.setVisible(false);
    ss_pn.setVisible(false);

    getContentPane().add(center_pn, BorderLayout.CENTER);

    show();

  }

  public void itemStateChanged(ItemEvent ie) {
    className_itemStateChanged();
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == searchSt) {
      search_actionPerformed();
    }
    else if (ae.getSource() == add) {
      add_actionPerformed();
    }
    else if(ae.getSource()==back){
      id.setText("");
      id_lb.setText("Student Id");
      id_lb.setForeground(Color.black);
      MainMenu.F.enable();
      dispose();
    }
  }

  //search button clicked 
  public void search_actionPerformed() {
    int num = 0;
    reset();
    try {
      num = Integer.parseInt(id.getText()); //reading the string to int
    }
    catch (NumberFormatException ex) {
    }

    if (num <= 0) { 
      infos_pn.setVisible(false);
      ss_pn.setVisible(false);
      id_lb.setText("Wrong Id");
      id_lb.setForeground(Color.red);
    }
    else { 
      id_lb.setText("Student Id");
      id_lb.setForeground(Color.black);
      
      //searching student from the id
      index = searchStu(num); 
     
      //student not found
      if (index == -1) {
        infos_pn.setVisible(false);
        ss_pn.setVisible(false);
        confirm.showMessageDialog(null,
                                  "Student not found",
                                  "Warning",
                                  JOptionPane.WARNING_MESSAGE);

        id.setText("");

      }
      //student found
      else { 
        Student e = (Student) MainMenu.listStudents.elementAt(index);
        StClasses m;
        lName.setText(e.getLastName());
        fName.setText(e.getFirstName());
        maj.setText(e.getMajor().getName() + e.getMajor().getLevel());
        className.addItem("Choose Class");
        //initialisation de la liste des matieres
        for (int i = 0; i < e.getMajor().getListClass().size(); i++) {
          m = (StClasses) e.getMajor().getListClass().elementAt(i);
          className.addItem(m.getName());
        }
        className.setSelectedIndex(0);
        infos_pn.setVisible(true);

        ss_pn.setVisible(true);
        notes_pn.setVisible(false);
      }
    }
  }

  //add button clicked
  public void add_actionPerformed() {
	  
    //verifying if the user correctly wrote 
	float e = -1, d = -1, t = -1;
    boolean state = true; 
    try {
      e = Float.parseFloat(ex.getText());
    }
    catch (NumberFormatException ex) {
      ex_lb.setForeground(Color.red); //********************************************
    }

    try {
      d = Float.parseFloat(ds.getText());
    }
    catch (NumberFormatException ex) {
    }

    if (hw_exists) { 
      try {
        t = Float.parseFloat(hw.getText());
      }
      catch (NumberFormatException ex) {
      }
    }

    if (e < 0 || e>20) {
      ex_lb.setForeground(Color.red);
      state = false;
    }
    else {
      ex_lb.setForeground(Color.black);
    }

    if (d < 0 || d>20) {
      ds_lb.setForeground(Color.red);
      state = false;
    }
    else {
      ds_lb.setForeground(Color.black);
    }

    if ((t < 0 || t>20)&& hw_exists) {
      hw_lb.setForeground(Color.red);
      state = false;
    }
    else if (hw_exists && t >= 0) {
      hw_lb.setForeground(Color.black);
    }
    if (state) { 
      Student st = (Student) MainMenu.listStudents.elementAt(index);
      StClasses stClas = (StClasses) st.getMajor().getListClass().elementAt(
          className.getSelectedIndex()-1);
      stClas.setEx(e);
      stClas.setDs(d);
      if (hw_exists) {
        stClas.setHw(t);
      }
//      }
    }
  }

  //when the state of the class list changes 
  public void className_itemStateChanged() {
    ex_lb.setForeground(Color.black);
    ds_lb.setForeground(Color.black);
    hw_lb.setForeground(Color.black);

    String clName = className.getSelectedItem().toString();
    System.out.println(clName);
    if (clName.equals("Choose Class")) {
      notes_pn.setVisible(false);
    }
    else { 
      Student st = (Student) MainMenu.listStudents.elementAt(index);
      StClasses stClas = (StClasses) st.getMajor().getListClass().elementAt(
            className.getSelectedIndex()-1);
        notes_pn.setVisible(true);
        
        //class does not have an homework
        
        if (stClas.getHw() == -1) { 
          hw_exists = false;
          hw_lb.setVisible(false);
          hw.setVisible(false);
        }
        
        //class has a homework 
        
        else {
          hw_exists = true;
          hw_lb.setVisible(true);
          hw.setVisible(true);
        }
       
        ex.setText(stClas.getEx() + "");
        ds.setText(stClas.getDs() + "");
        hw.setText(stClas.getHw() + "");
    }
  }

  //searching student from the Id number
  public int searchStu(int num) {
    Student st;
    for (int i = 0; i < MainMenu.listStudents.size(); i++) {
      st = (Student) MainMenu.listStudents.elementAt(i);
      if (st.getStudentId() == num) {
        return i;
      }
    }
    return -1;
  }


  //initialisation
  public void reset() {
    ex.setText("");
    ds.setText("");
    hw.setText("");
    className.removeAllItems();
  }

}