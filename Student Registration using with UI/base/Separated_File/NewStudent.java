//class for the frame adding student 



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

public class NewStudent extends MainClass implements ItemListener, ActionListener {

  

  MyJlabel stCa_lb;
  MyJlabel lastName_lb;
  MyJlabel firstName_lb;
  MyJlabel birthDate_lb;
  MyJlabel sex_lb;
  MyJlabel major_lb;
  MyJlabel state_lb;

  JTextField stCa;
  JTextField lastName;
  JTextField firstName;

  JComboBox day, month, year; 

  ButtonGroup sex; 
  JRadioButton male;
  JRadioButton female;

  JComboBox major;
  JComboBox level;
  JComboBox state;

  JButton add, back;

  JPanel center_pn, south_pn;

  JOptionPane confirm;


  //constructor
  public NewStudent() {

    super("New Student", 350, 350,new Color(51, 51, 51));

    
    stCa_lb = new MyJlabel("Student Id Number:");
    stCa = new JTextField();
    stCa.setText("" + numAuto());
    stCa.disable();

   
    lastName_lb = new MyJlabel("Last Name:");
    lastName = new JTextField();


    firstName_lb = new MyJlabel("First Name:");
    firstName = new JTextField();

   
    birthDate_lb = new MyJlabel("Birth Date:");
    day = new JComboBox();
    month = new JComboBox();
    year = new JComboBox();
    
    //initializing list of days
    int i = 0;
    for (i = 1; i <= 31; i++) {
      day.addItem("" + i);
    }
    day.setSelectedIndex(0); //first default element is the one choosen 

    //initializing list of months
    for (i = 1; i <= 12; i++) {
      month.addItem("" + i);
    }
    month.setSelectedIndex(0); //first default element is the one choosen 

    //initializing list of years
    for (i = 1970; i <= 2022; i++) {
      year.addItem("" + i);
    }
    year.setSelectedIndex(16); //first default element is the one choosen 
    
 
    sex_lb = new MyJlabel("Sex:");
    sex = new ButtonGroup();
    male = new JRadioButton("Male", true);
    female = new JRadioButton("Female", false);
    sex.add(male);
    sex.add(female);

 
    major_lb = new MyJlabel("Major:");


    major = new JComboBox();
    
    //initiazing list of majors from vector
    Major ma;
    for(i=0;i<MainMenu.listMajor.size();i++){
      ma=(Major)MainMenu.listMajor.elementAt(i);
      if(ma.getLevel()==1 || ma.getLevel()==2) major.addItem(ma.getName());
    }
    major.addItemListener(this); 
    major.setSelectedIndex(0); 
    
    
    level = new JComboBox();
    
    //initializing level element
    for (i = 2; i <= 5; i++) {
      level.addItem("" + i);
    }
    level.setVisible(false); 
    level.setSelectedIndex(0);

    add = new JButton("Add");
    add.addActionListener(this);
    back = new JButton("Back");
    back.addActionListener(this);

    
    center_pn = new JPanel();
    center_pn.setLayout(new GridLayout(6, 2));
    center_pn.add(stCa_lb);
    center_pn.add(stCa);
    center_pn.add(lastName_lb);
    center_pn.add(lastName);
    center_pn.add(firstName_lb);
    center_pn.add(firstName);
    center_pn.add(birthDate_lb);

    //adding date to panles
    JPanel dn_pn = new JPanel();
    dn_pn.add(day);
    dn_pn.add(month);
    dn_pn.add(year);


    center_pn.add(dn_pn);
    center_pn.add(sex_lb);

    //adding sex to panels
    JPanel sex_pn = new JPanel();
    sex_pn.add(male);
    sex_pn.add(female);
  

    center_pn.add(sex_pn);

    //adding majors and level to panels
    JPanel major_pn = new JPanel();
    major_pn.add(major);
    major_pn.add(level);

    center_pn.add(major_lb);
    center_pn.add(major_pn);


    //adding buttons to south panels
    south_pn = new JPanel();
    south_pn.add(add, BorderLayout.PAGE_END);
    south_pn.add(back, BorderLayout.PAGE_END);
  


    getContentPane().add(center_pn, BorderLayout.CENTER);
    getContentPane().add(south_pn, BorderLayout.SOUTH);

    setResizable(false);
    show();

  }

  //element choosen changed in majors 
  public void itemStateChanged(ItemEvent ie) { 
    
      level.setVisible(true);
    }
  

  public void actionPerformed(ActionEvent ae) { 
    if (ae.getSource() == back) {
      MainMenu.F.enable();
      dispose();
    }
    else if (ae.getSource() == add) {
      add_actionPerformed();
    }
  }


  //method that give automatic Id to students
  public int numAuto() {
    if (MainMenu.listStudents.size() == 0) {
      return 1;
    }
    else {
      return ( ( (Student) MainMenu.listStudents.elementAt(MainMenu.listStudents.size() - 1)).
              getStudentId() + 1);
    }
  }

  //add button clicked
  public void add_actionPerformed() {
    Student st;
    Major maj=new Major();
    int id = 0, d = 0, m = 0, y = 0,lev=0;
    String ln = "", fn = "", s = "", ma = "";
    Date dn;
    boolean state = true;

    id = Integer.parseInt(stCa.getText());
    ln = lastName.getText();
    if (ln.equals("")) {
      lastName_lb.setText("Name : Enter Last Name");
      lastName_lb.setForeground(Color.red);
      state = false;
    }else{
      lastName_lb.setText("Name :");
      lastName_lb.setForeground(Color.black);
    }
    fn = firstName.getText();
    if (fn.equals("")) {
      firstName_lb.setText("First Name : Enter First Name");
      firstName_lb.setForeground(Color.red);
      state = false;
    }else{
      firstName_lb.setText("First Name :");
      firstName_lb.setForeground(Color.black);
    }
    //date
    try {
      d = Integer.parseInt(day.getSelectedItem().toString());
      m = Integer.parseInt(month.getSelectedItem().toString());
      y = Integer.parseInt(year.getSelectedItem().toString());
    }
    catch (NumberFormatException ex) {
      state=false;
    }
    dn = new Date(d, m, y);
    //sex
    if (male.isSelected()) {
      s = "Male";
    }
    else {
      s = "Female";
    }
    //major
      ma=major.getSelectedItem().toString();
    //level
    
      lev = Integer.parseInt(level.getSelectedItem().toString());
    

    if (state) { 
      int result=confirm.showConfirmDialog(null,"Do you want to add this student","Warning",JOptionPane.YES_NO_OPTION);
      if(result==0){

        //getting the major
        maj = majorAdding(ma, lev);
        Student stu = new Student(id, ln, fn, dn, s, maj);

        MainMenu.listStudents.addElement(stu);

        stCa.setText(numAuto() + "");
      }
      lastName.setText("");
      firstName.setText("");
    }
  }


  //getting major from name and level to use it for updating students 
  public Major majorAdding(String majorName,int lev) {
	  //getting class list
	  
    Vector listClasses=new Vector();
    Major maj=new Major();
    for (int i = 0; i < MainMenu.listMajor.size(); i++) {
      maj = ( (Major) MainMenu.listMajor.elementAt(i));

      if (maj.getName().equals(majorName) && maj.getLevel()==lev) {
        String nameMajor=maj.getName();
        int levMaj=maj.getLevel();
        StClasses mat;
        for(int j=0;j<maj.getListClass().size();j++){
          mat=(StClasses)maj.getListClass().elementAt(j);
          listClasses.addElement(new StClasses(mat.getName(),mat.getEx(),mat.getDs(),mat.getHw(),mat.getCoef()));
        }

        return new Major(nameMajor,levMaj,listClasses);
      }
    }
    return maj;
  }


}