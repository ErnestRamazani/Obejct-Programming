//class for updating and deleting student 

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.net.*;



public class UpdateStudent extends MainClass implements ActionListener, ItemListener {

  private int index = -1; //student index searched in the student vector
 

  MyJlabel idSearch_lb;
  JTextField idSearch; 

  MyJlabel oldValue, newValue; 
  MyJlabel lastName_lb;
  MyJlabel firstName_lb;
  MyJlabel dn_lb;
  MyJlabel sex_lb;
  MyJlabel major_lb;
  MyJlabel state_lb;

  JTextField lastName, newLastName;
  JTextField firstName, newFirstName;
  JTextField dn;
  JTextField oldSex; 
  JTextField oldMajorLev; 

  JComboBox day, month, year; 

  ButtonGroup sex; 
  JRadioButton male;
  JRadioButton female;

  JComboBox major;
  JComboBox level;
  JComboBox state;

  JButton update, back, search, delete;

  JOptionPane confirm;

 
  JPanel north_pn, champs_pn, center_pn, search_pn, dn_pn, sex_pn,
      major_pn, south_pn;

  public UpdateStudent() {
    super("Update/Delete Student", 487, 343,new Color(01, 90, 50));

    idSearch_lb = new MyJlabel("Student Id:");
    idSearch = new JTextField(10);
    search = new JButton("Search");

    oldValue = new MyJlabel("Old Values");
    newValue = new MyJlabel("New Values");

    lastName_lb = new MyJlabel("Last Name:");
    lastName = new JTextField(10);
    newLastName = new JTextField(10);
    lastName.disable();


    firstName_lb = new MyJlabel("First:");
    firstName = new JTextField(10);
    newFirstName = new JTextField(10);
    firstName.disable();

    dn_lb = new MyJlabel("BirthDay:");
    dn = new JTextField(10);
    dn.disable();
    day = new JComboBox();
    month = new JComboBox();
    year = new JComboBox();

    //initialisation of list day 
    int i = 0;
    for (i = 1; i <= 31; i++) {
      day.addItem("" + i);
    }
    day.setSelectedIndex(0); //first default element is the one choosen 

// initialisation of list month 
    for (i = 1; i <= 12; i++) {
      month.addItem("" + i);
    }
    month.setSelectedIndex(0); //first default element is the one choosen 
    
//initialisation of list year
    for (i = 1970; i <= 2007; i++) {
      year.addItem("" + i);
    }
    year.setSelectedIndex(16); //first default element is the one choosen 

    
    sex_lb = new MyJlabel("Sex:");
    oldSex = new JTextField(10);
    oldSex.disable();
    sex = new ButtonGroup();
    male = new JRadioButton("Male", true);
    female = new JRadioButton("Female", false);
    sex.add(male);
    sex.add(female);

   
    major_lb = new MyJlabel("Major:");

    oldMajorLev = new JTextField(10);
    oldMajorLev.disable();

    major = new JComboBox();

    //initializing major list from majorVector
    
    Major fi;
    for(i=0;i<MainMenu.listMajor.size();i++){
      fi=(Major)MainMenu.listMajor.elementAt(i);
      if(fi.getLevel()==1 || fi.getLevel()==2) major.addItem(fi.getName());
    }


    major.addItemListener(this); 
    major.setSelectedIndex(0); 

   
    level = new JComboBox();
    
//initialisation of level element 
    for (i = 2; i <= 5; i++) {
      level.addItem("" + i);
    }
    level.setVisible(false); 
    level.setSelectedIndex(0);

   
    update = new JButton("Update");

    update.addActionListener(this);
    back = new JButton("Back");
    back.addActionListener(this);
    search.addActionListener(this);
    delete = new JButton("Delete");
    delete.addActionListener(this);


    search_pn = new JPanel();
    search_pn.add(idSearch_lb);
    search_pn.add(idSearch);
    search_pn.add(search);
    search_pn.add(back);


    dn_pn = new JPanel();
    dn_pn.add(day);
    dn_pn.add(month);
    dn_pn.add(year);
 
    sex_pn = new JPanel();
    sex_pn.add(male);
    sex_pn.add(female);


    major_pn = new JPanel();
    major_pn.add(major);
    major_pn.add(level);
 
    champs_pn = new JPanel();
    champs_pn.setLayout(new GridLayout(6, 3));
    champs_pn.add(new Panel()); 
    champs_pn.add(oldValue);
    champs_pn.add(newValue);
    champs_pn.add(lastName_lb);
    champs_pn.add(lastName);
    champs_pn.add(newLastName);
    champs_pn.add(firstName_lb);
    champs_pn.add(firstName);
    champs_pn.add(newFirstName);
    champs_pn.add(dn_lb);
    champs_pn.add(dn);
    champs_pn.add(dn_pn);
    champs_pn.add(sex_lb);
    champs_pn.add(oldSex);
    champs_pn.add(sex_pn);
    champs_pn.add(major_lb);
    champs_pn.add(oldMajorLev);
    champs_pn.add(major_pn);
  
    center_pn = new JPanel();
    center_pn.setLayout(new BorderLayout());
    center_pn.add("North", search_pn);
    center_pn.add("Center", champs_pn);

   
    south_pn = new JPanel();
    south_pn.add(update);
    south_pn.add(delete);


    champs_pn.setVisible(false);
    south_pn.setVisible(false);


    getContentPane().add(center_pn, BorderLayout.CENTER);
    getContentPane().add(south_pn, BorderLayout.SOUTH);

    show();

  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == search) {
      search_actionPerformed();
    }
    else if (ae.getSource() == update) {
      update_actionPerformed();
    }
    else if (ae.getSource() == delete) {
      delete_actionPerformed();
    }
    else if (ae.getSource() == back) {
      MainMenu.F.enable();
      dispose();
    }
  }

  public void itemStateChanged(ItemEvent ie) {

    //event when major is choosen 
    
      level.setVisible(true);

    }
  

  //search button clicked
  public void search_actionPerformed() {
    int id = 0;
    reset();
    try {
      id = Integer.parseInt(idSearch.getText());
    }
    catch (NumberFormatException ex) {
    }
    if (id <= 0) {
      champs_pn.setVisible(false);
      south_pn.setVisible(false);

      idSearch_lb.setText("Student Id: Enter a correct Id");
      idSearch_lb.setForeground(Color.red);
    }
    else {
      idSearch_lb.setText("Student Id:");
      idSearch_lb.setForeground(Color.black);
      index = searchSt(id);
      if (index == -1) {
        champs_pn.setVisible(false);
        south_pn.setVisible(false);

        confirm.showMessageDialog(null, "Student not Found", "Student does not exist", JOptionPane.WARNING_MESSAGE);
        idSearch.setText("");

      }
      else {
        Student e = (Student) MainMenu.listStudents.elementAt(index);
        lastName.setText(e.getLastName());
        firstName.setText(e.getFirstName());
        dn.setText(e.getBirthDate().getDay() + "/" + e.getBirthDate().getMonth() + "/" +
                   e.getBirthDate().getYear());
        oldSex.setText(e.getSex());
        oldMajorLev.setText(e.getMajor().getName() +
                                 e.getMajor().getLevel());

        champs_pn.setVisible(true);
        south_pn.setVisible(true);
      }
    }

  }

  //delete button clicked
  public void delete_actionPerformed() {
    int result = confirm.showConfirmDialog(null,
        "Do you want to delete this student?", "Warning",
        JOptionPane.YES_NO_OPTION);
    System.out.println(result);

    if (index >= 0 && result == 0) {
    	
    	//removing from the vector
    	
      MainMenu.listStudents.removeElementAt(index); 
      reset();
      champs_pn.setVisible(false);
      south_pn.setVisible(false);
    }
  }

  //update button clicked
  public void update_actionPerformed() {
    Student std;
    Major maj = new Major();
    int ce=0,d = 0, m = 0, y = 0, lev = 0;
    String ln = "", fn = "", s = "", f = "";
    Date bd;
    boolean state = true;

    ln = newLastName.getText();
    if (ln.equals("")) {
      lastName_lb.setForeground(Color.red);
      state = false;
    }
    else {
      lastName_lb.setForeground(Color.black);
    }
    fn = newFirstName.getText();
    if (fn.equals("")) {
      firstName_lb.setForeground(Color.red);
      state = false;
    }
    else {
      firstName_lb.setForeground(Color.black);
    }
    //date
    try {
      d = Integer.parseInt(day.getSelectedItem().toString());
      m = Integer.parseInt(month.getSelectedItem().toString());
      y = Integer.parseInt(year.getSelectedItem().toString());
    }
    catch (NumberFormatException ex) {
      state = false;
    }
    bd = new Date(d, m, y);
    //sex
    if (male.isSelected()) {
      s = "Male";
    }
    else {
      s = "Female";
    }
    //major
    f = major.getSelectedItem().toString();
    //level
   
      lev = Integer.parseInt(level.getSelectedItem().toString());
    

    if (state) { 
      //getting the major

      int result = confirm.showConfirmDialog(null,
          "Do you want to Update this Student?", "Warning",
          JOptionPane.YES_NO_OPTION);

      if (result == 0) { 
        maj = getMajors(f, lev);
        System.out.println(ln + fn + d + m + y + s + f + lev + maj.getName() + ((StClasses) maj.getListClass().elementAt(2)).getName());
        ce=((Student)MainMenu.listStudents.elementAt(index)).getStudentId();
        std=new Student(ce,ln,fn,bd,s,maj);
        MainMenu.listStudents.setElementAt(std,index);
        Student e1=(Student)MainMenu.listStudents.elementAt(index);
        champs_pn.setVisible(false);
        south_pn.setVisible(false);

      }
    }
  }


  //getting major from name and level to use it for updating students 
  public Major getMajors(String nomFil,int niv) {
	  //getting classList
    Vector claList=new Vector();
    Major maj=new Major();
    for (int i = 0; i < MainMenu.listMajor.size(); i++) {
      maj = ( (Major) MainMenu.listMajor.elementAt(i));

      if (maj.getName().equals(nomFil) && maj.getLevel()==niv) {
        String majName=maj.getName();
        int levelMaj=maj.getLevel();
        StClasses stCla;
        for(int j=0;j<maj.getListClass().size();j++){
          stCla=(StClasses)maj.getListClass().elementAt(j);
          claList.addElement(new StClasses(stCla.getName(),stCla.getEx(),stCla.getDs(),stCla.getHw(),stCla.getCoef()));
        }

        return new Major(majName,levelMaj,claList);
      }
    }
    return maj;
  }


//seaching student from id
  public int searchSt(int num) {
    Student e;
    for (int i = 0; i < MainMenu.listStudents.size(); i++) {
      e = (Student) MainMenu.listStudents.elementAt(i);
      if (e.getStudentId() == num) {
        return i;
      }
    }
    return -1;
  }


  //reset
  public void reset(){
    newLastName.setText("");
    newFirstName.setText("");
    day.setSelectedIndex(0);
    month.setSelectedIndex(0);
    year.setSelectedIndex(16);
    male.setSelected(true);
    female.setSelected(false);
    major.setSelectedIndex(0);
    level.setVisible(false);
    level.setSelectedIndex(0);
  }



}