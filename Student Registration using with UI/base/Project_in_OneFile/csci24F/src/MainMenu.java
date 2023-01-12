//class for the main menu, main frame

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Vector;



public class MainMenu extends JPanel implements ActionListener{

  static File majorFile ;
  static File studentFile ;

  static Vector listStudents=new Vector();
  static Vector listMajor=new Vector();

  static MainClass F=new MainClass("CSCI 24000 Final_Project",652,460,new Color(01, 90, 50));
  
 

 
  JPanel button_pn = new JPanel();
  JButton quit = new JButton();
  JButton save = new JButton();
  JPanel student_pn = new JPanel();
  Border border1;
  TitledBorder titledBorder1;
  JPanel major_pn = new JPanel();
  Border border2;
  TitledBorder titledBorder2;
  JButton update = new JButton();
  JPanel stat_pn = new JPanel();
  Border border3;
  TitledBorder titledBorder3;
  JButton stats = new JButton();
  JButton major = new JButton();
  JButton student_class = new JButton();
  
  JLabel Insat_lb = new JLabel();
  JButton new_student = new JButton();
  JButton notes = new JButton();
  JButton delete = new JButton();
  JButton average = new JButton();
  JOptionPane confirm;

  //constructor
  public MainMenu() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
  
    F.Option.addActionListener(this);
    F.save.addActionListener(this);
    F.close.addActionListener(this);
    F.about.addActionListener(this);

    F.save.setEnabled(true);
    F.close.setEnabled(true);
    F.Option.setEnabled(true);
    F.about.setEnabled(true);

    F.copy.setEnabled(false);
    F.cut.setEnabled(false);
    F.paste.setEnabled(false);

    F.getContentPane().add(this,BorderLayout.CENTER);
    F.show();
  }



  //main
  public static void main(String[] args) {

    majorFile = new File("majors.txt");
    studentFile = new File("students.txt");

    listStudents=Files.readFileStudent(studentFile);
    listMajor=Files.readFileMaj(majorFile);

    MainMenu mainMenu = new MainMenu();

  }




  private void jbInit() throws Exception {


    border1 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(25, 90, 150));
    titledBorder1 = new TitledBorder(border1,"Students");
    border2 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(134, 134, 134));
    titledBorder2 = new TitledBorder(border2,"Majors");
    border3 = new EtchedBorder(EtchedBorder.RAISED,Color.white,new Color(134, 134, 134));
    titledBorder3 = new TitledBorder(border3,"Statistics");
    
    this.setLayout(null);
    this.setBackground(new Color(75, 51, 51));
    button_pn.setBackground(new Color(204, 255, 255));
    button_pn.setBorder(BorderFactory.createEtchedBorder());
    button_pn.setDoubleBuffered(true);
    button_pn.setBounds(new Rectangle(140, 290, 350, 60));
 
    button_pn.setLayout(null);
    quit.setBounds(new Rectangle(183, 8, 142, 40));
    quit.setText("Quit");
    
    quit.addActionListener(this); 

    save.setText("Save");
    save.setBounds(new Rectangle(24, 8, 142, 40));
   
    save.addActionListener(this);
    student_pn.setBackground(new Color(204, 255, 255));
    student_pn.setBorder(titledBorder1);
    student_pn.setBounds(new Rectangle(30, 10, 580, 75));
    student_pn.setLayout(null);
    major_pn.setBackground(new Color(204, 255, 255));
    major_pn.setBorder(titledBorder2);
    major_pn.setBounds(new Rectangle(30, 100, 580, 75));
    major_pn.setLayout(null);
    update.setBounds(new Rectangle(295, 20, 118, 39));
    update.setText("Update");
  
    update.addActionListener(this);
    stat_pn.setBackground(new Color(204, 255, 255));
    stat_pn.setBorder(titledBorder3);
    stat_pn.setBounds(new Rectangle(30, 190, 580, 75));
    stat_pn.setLayout(null);
    stats.setBounds(new Rectangle(155, 26, 132, 40));
    stats.setText("Stats");
    
    stats.addActionListener(this);
    major.setBounds(new Rectangle(155, 26, 132, 40));
    major.setText("Majors");
    
    major.addActionListener(this);
    student_class.setBounds(new Rectangle(295, 26, 132, 40));
    student_class.setText("Classes");
 
    student_class.addActionListener(this);
   
    
   
    new_student.setText("New");
    new_student.setBounds(new Rectangle(34, 20, 118, 39));
    new_student.addActionListener(this);
   
    notes.setText("Notes");
    notes.setBounds(new Rectangle(430, 20, 118, 39));
    notes.addActionListener(this);
  
    delete.setText("Delete");
    delete.setBounds(new Rectangle(162, 20, 118, 39));
    delete.addActionListener(this);
    
    average.setText("Avg");
    average.setBounds(new Rectangle(295, 26, 132, 40));
    average.addActionListener(this);
    button_pn.add(save, null);
    button_pn.add(quit, null);
    this.add(student_pn, null);
    student_pn.add(update, null);
    student_pn.add(new_student, null);
    student_pn.add(notes, null);
    student_pn.add(delete, null);
    major_pn.add(major, null);
    major_pn.add(student_class, null);
    this.add(Insat_lb, null);
    this.add(button_pn, null);
    this.add(stat_pn, null);
    stat_pn.add(stats, null);
    stat_pn.add(average, null);
   
    this.add(major_pn, null);
  }

  void quit_actionPerformed() {
    int result=confirm.showConfirmDialog(null,"Do you want to quit?","Warning",JOptionPane.YES_NO_CANCEL_OPTION);
    //answer is Yes
    
    if(result==0){
    	
      save_actionParformed();
      System.exit(0);
    }
    //answer is No
    else if(result==1){
        System.exit(0);
    }
  }

  void save_actionParformed(){
    Files.writeFile(studentFile,listStudents);
    Files.writeMajorFile(majorFile,listMajor);
  }

  void newStudent_actionPerformed(){
    F.disable();
    new NewStudent();
  }

  void update_supp_actionPerformed(){
    F.disable();
    new UpdateStudent();
  }

  void notes_actionPerformed(){
    F.disable();
    new Notes();
  }

  void stats_actionPerformed(){
    F.disable();
    new Statistics();
  }

  void class_actionPerformed(){
    F.disable();
    new AddClasses();
  }

  void major_actionPerformed(){
    F.disable();
    new AddMajor();
  }

  void avg_actionPerformed(){
    F.disable();
    new Average();
  }

  void aboutUs_actionPerformed(){
    F.disable();
    new AboutUs();
  }

  void Option_actionPerformed(){
    F.disable();
    new view();
    
  }

  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==quit || ae.getSource()==F.close){
      quit_actionPerformed();
    }
    else if(ae.getSource()==save || ae.getSource()==F.save){
      save_actionParformed();
    }
    else if(ae.getSource()==new_student){
      newStudent_actionPerformed();
    }
    else if(ae.getSource()==update || ae.getSource()==delete){
      update_supp_actionPerformed();
    }
    else if(ae.getSource()==notes){
      notes_actionPerformed();
    }
    else if(ae.getSource()==stats){
      stats_actionPerformed();
    }
    else if(ae.getSource()==student_class){
      class_actionPerformed();
    }
    else if(ae.getSource()==average){
      avg_actionPerformed();
    }
    else if(ae.getSource()==major){
      major_actionPerformed();
    }
    else if(ae.getSource()==F.about){
      aboutUs_actionPerformed();
    }
    else if(ae.getSource()==F.Option){
      Option_actionPerformed();
    }
  }
}