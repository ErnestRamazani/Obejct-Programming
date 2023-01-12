//Class for showing student average  

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.text.DecimalFormat;


public class Average extends MainClass implements ItemListener, ActionListener {

  private int lev = 1;
  private String majName = "Choose a major";

  private JComboBox listMaj = new JComboBox();
  private JComboBox listLevel = new JComboBox();

  private MyJlabel fil_lb = new MyJlabel("Major:"),
      nomMat_lb = new MyJlabel("major");

  private JButton back = new JButton("Back");
  private JPanel back_pn = new JPanel(), center = new JPanel(),
      maj_pn = new JPanel();

  private JTable table;
  private JScrollPane sp = new JScrollPane();

  private Vector nCol = new Vector();
  private Vector data = new Vector();

  private DecimalFormat df = new DecimalFormat("00.00"); 

  public Average() {

    super("Average", 500, 400,new Color(01, 90, 50));

    //initialisation of major list
    listMaj.addItem("Choose a major");
    Major ma;
    for (int i = 0; i < MainMenu.listMajor.size(); i++) {
      ma = (Major) MainMenu.listMajor.elementAt(i);
      if (ma.getLevel() == 1 || ma.getLevel() == 2) {
        listMaj.addItem(ma.getName());
      }
    }
    listMaj.addItemListener(this); 
    listMaj.setSelectedIndex(0); //choosing a default major 

    //initialisation of level elements
    listLevel = new JComboBox();
    for (int i = 2; i <= 5; i++) {
      listLevel.addItem("" + i);
    }
    listLevel.addItemListener(this);
    listLevel.setVisible(false);
    listLevel.setSelectedIndex(0);

    //initialisation of vector column names
    nCol.addElement("ID");
    nCol.addElement("Last Name");
    nCol.addElement("First Name");
    nCol.addElement("Average");
    nCol.addElement("Decision");

   //panels
    maj_pn.add(fil_lb);
    maj_pn.add(listMaj);
    maj_pn.add(listLevel);
    center.setLayout(new BorderLayout());
    center.add("North", maj_pn);
    center.add("Center", sp);
    back.addActionListener(this);
    back_pn.add(back);

    table = new JTable(data, nCol);
    sp.setViewportView(table);

    getContentPane().add(center, BorderLayout.CENTER);
    getContentPane().add(back_pn, BorderLayout.SOUTH);

    show();

  }



  public void itemStateChanged(ItemEvent ie) {
    if (ie.getSource() == listMaj) {
      if (ie.getItem().equals("Choose a major")) {
        listLevel.setVisible(false);
      }
      else {
        listLevel.setVisible(true);
      }
    }

    majName = listMaj.getSelectedItem().toString();
    //a major is chosen 
    
    if (!majName.equals("Choose a major")) { 
      lev = 1;
      if (!majName.equals("MPI") && !majName.equals("CBA")) {
        lev = Integer.parseInt(listLevel.getSelectedItem().toString());
      }

      //filling vector
      Student st;
      Vector v, d = new Vector();
      data.removeAllElements();
      for (int i = 0; i < MainMenu.listStudents.size(); i++) {
        st = (Student) MainMenu.listStudents.elementAt(i);
        
        // student is in the choosen major 
        
        if (st.getMajor().getName().equals(majName) &&
            st.getMajor().getLevel() == lev) { 
          v = new Vector();
          v.addElement(st.getStudentId() + "");
          v.addElement(st.getLastName());
          v.addElement(st.getFirstName());
          double moy = st.getMajor().average();
          v.addElement(df.format(moy)); 
          if (moy >= 10) {
            v.addElement("Pass");
          }
          else {
            v.addElement("Test");
          }
          d.addElement(v);
        }
      }
      table = new JTable(d, nCol);
      sp.setViewportView(table);
    }
    else{
      table=new JTable(new Vector(),nCol);
      sp.setViewportView(table);
    }
  }

  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == back) {
      MainMenu.F.enable();
      dispose();
    }
  }

}