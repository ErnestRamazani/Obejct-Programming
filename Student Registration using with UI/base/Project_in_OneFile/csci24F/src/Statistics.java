//class that display students stats 

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class Statistics extends MainClass implements ActionListener{

  private JScrollPane sp;
  private JTable table;
  private Vector colName;
  private Vector data; 

  private JButton back;

  private JPanel button_pn;

  private TableColumn c1,c2,c3,c4,c5;

  private DecimalFormat df = new DecimalFormat("00.00");

  public Statistics() {
    super("Statistics", 600, 400,new Color(01, 90, 50));

    sp = new JScrollPane();


    //initializing column name
    colName = new Vector();
    colName.addElement("Major");
    colName.addElement("NumOf Stduents");
    colName.addElement("Max Avg");
    colName.addElement("Min Avg");
    colName.addElement("% Pass");

    //adding new data to vectors
    data = new Vector();
    Major ma;
    for (int i = 0; i < MainMenu.listMajor.size(); i++) {
      ma = (Major) MainMenu.listMajor.elementAt(i);
      data.addElement(stat(ma.getName(), ma.getLevel()));
    }

    table = new javax.swing.JTable(data, colName); 
    sp.setViewportView(table); 


   
    c1 = table.getColumnModel().getColumn(0);
    c1.setPreferredWidth(50);

    c2 = table.getColumnModel().getColumn(1);
    c2.setPreferredWidth(50);

    c3 = table.getColumnModel().getColumn(2);
    c3.setPreferredWidth(50);

    c4 = table.getColumnModel().getColumn(3);
    c4.setPreferredWidth(50);

    c5 = table.getColumnModel().getColumn(4);
    c5.setPreferredWidth(75);


    sp.createVerticalScrollBar();
    getContentPane().add(sp, BorderLayout.CENTER);

    
    back = new JButton("Back");
    back.addActionListener(this);
    button_pn = new JPanel();
    button_pn.add(back);

    getContentPane().add(button_pn, BorderLayout.SOUTH);

    show();

  }

 
  //class to return stats by majors in the vector 
  public Vector stat(String majName, int lev) {
    int number = 0, pass = 0; //
    double max = 0, min = 21, mean = 0;
    Student e;
    for (int i = 0; i < MainMenu.listStudents.size(); i++) {
      e = (Student) MainMenu.listStudents.elementAt(i);
      if (e.getMajor().getName().equals(majName) &&
          e.getMajor().getLevel() == lev) { 
        number++;
        mean = e.getMajor().average();
        if (mean > max) {
          max = mean;
        }
        if (mean < min) {
          min = mean;
        }
        if (mean >= 10) {
          pass++;
        }
      }
    }

    int pourcent = 0;
    if (pass != 0) {
      pourcent = pass * 100 / number; ;
    }

    Vector vec = new Vector();
    vec.addElement(majName + lev);
    vec.addElement(number + "");
    vec.addElement(df.format(max));
    if(min>20) min=0;
    vec.addElement(df.format(min));
    vec.addElement(pourcent + "");

    return vec;
  }

  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==back){
      MainMenu.F.enable();
      dispose();
    }
  }
}
