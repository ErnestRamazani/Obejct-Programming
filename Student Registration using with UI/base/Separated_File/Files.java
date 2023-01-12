

//class for file manipulation

import java.util.Vector;
import java.io.*;

public class Files {

  //writting the file students from a vector 
  static public void writeFile(File f, Vector v) {
    Student st;
    StClasses stC;  
    StringBuffer line;
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(f));
      for (int i = 0; i < v.size(); i++) {
        st = (Student) v.elementAt(i);
        line = new StringBuffer(st.getStudentId() + ";" + st.getLastName() + ";" +
                                 st.getFirstName() + ";" + st.getBirthDate().getDay() +
                                 ";" + st.getBirthDate().getMonth() + ";" +
                                 st.getBirthDate().getYear() + ";" + st.getSex() + ";" +
                                 st.getMajor().getName() + ";" +
                                 st.getMajor().getLevel() + ";"); 

        //adding the class list 
        for (int j = 0; j < st.getMajor().getListClass().size(); j++) {
          stC = (StClasses) st.getMajor().getListClass().elementAt(j);
          line.append(stC.getName() + ";" + stC.getCoef() + ";" + stC.getEx() + ";" +
                       stC.getDs() + ";" +
                       stC.getHw() + ";");
        }    

        //writing line on the file 
        out.write(line.toString());
        out.newLine();
      }
      out.close();
    }
    catch (IOException ex) {
     
      error();
    }
    
  }

  	//method for read a student from a line in the file 
  
  static public Student readStudent(String line) {
    int id = 0;
    try {
      id = Integer.parseInt(line.substring(0, line.indexOf(';')));
      line = line.substring(line.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      error();
    }

    String lastName = line.substring(0, line.indexOf(';'));
    line = line.substring(line.indexOf(';') + 1);

    String firstName = line.substring(0, line.indexOf(';'));
    line = line.substring(line.indexOf(';') + 1);

    //getting the date
    int day = 0, month = 0, year = 0;
    try {
      day = Integer.parseInt(line.substring(0, line.indexOf(';')));
      line = line.substring(line.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      error();
    }
    try {
      month = Integer.parseInt(line.substring(0, line.indexOf(';')));
      line = line.substring(line.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      error();
    }
    try {
      year = Integer.parseInt(line.substring(0, line.indexOf(';')));
      line = line.substring(line.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      error();
    }
    Date dt = new Date(day, month, year);
    //end of date

    String sex = line.substring(0, line.indexOf(';'));
    line = line.substring(line.indexOf(';') + 1);

    //getting the major
    Major major;
    String majorName = line.substring(0, line.indexOf(';'));
    line = line.substring(line.indexOf(';') + 1);

    //level of the major
    int lev = 0;
    try {
      lev = Integer.parseInt(line.substring(0, line.indexOf(';')));
      line = line.substring(line.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      error();
    }

    Vector listClasses = new Vector();
    String nameClasse;
    float ds = 0, ex = 0, hw = 0;
    int coef = 0;
    //getting the class
    while (line.length() != 0) {
      nameClasse = line.substring(0, line.indexOf(';'));
      line = line.substring(line.indexOf(';') + 1);

      try {
        coef = Integer.parseInt(line.substring(0, line.indexOf(';')));
        line = line.substring(line.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        error();
      }

      try {
        ex = Float.parseFloat(line.substring(0, line.indexOf(';')));
        line = line.substring(line.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        error();
      }

      try {
        ds = Float.parseFloat(line.substring(0, line.indexOf(';')));
        line = line.substring(line.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        error();
      }

      try {
        hw = Float.parseFloat(line.substring(0, line.indexOf(';')));
        line = line.substring(line.indexOf(';') + 1);
      }
      catch (Exception exp) {
        error();
      }
      //adding to the list of classes
      listClasses.addElement(new StClasses(nameClasse, ex, ds, hw, coef));
    }

    major = new Major(majorName, lev, listClasses);

    return (new Student(id, lastName, firstName, dt, sex, major));
  }

//mothod for dealing with errors
  static public void error() {
    System.out.println("ERROR");
  }

//moethod for reading a student from the file and putting it to the vector
  static public Vector readFileStudent(File f) {
    Vector v = new Vector();
    if (f.exists()) {
      try {
        String ch;
        Student e;
        BufferedReader in = new BufferedReader(new FileReader(f));
        do {
          ch = in.readLine();
          if (ch != null) {
            e = readStudent(ch);
            v.addElement(e);
          }
        }
        while (ch != null);
      }
      catch (IOException ex) {
        System.out.println(ex.toString());
      }
    }
    return v;
  }

  //reading a major from lines in file
  static public Major readFileMajor(String line) {
    String name = line.substring(0, line.indexOf(';'));
    line = line.substring(line.indexOf(';') + 1);

    //majors level 
    int lev=0;
    try {
      lev = Integer.parseInt(line.substring(0, line.indexOf(';')));
      line = line.substring(line.indexOf(';') + 1);
    }
    catch (NumberFormatException ex) {
      error();
    }


    //getting classes from file to vectors
    Vector listCla = new Vector();
    int coef = 0;
    float ex = 0, ds = 0, hw = 0;
    String nameClass = " ";
    while (line.length() != 0) {
      nameClass = line.substring(0, line.indexOf(';'));
      line = line.substring(line.indexOf(';') + 1);

      try {
        coef = Integer.parseInt(line.substring(0, line.indexOf(';')));
        line = line.substring(line.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        error();
      }
      try {
        ex = Float.parseFloat(line.substring(0, line.indexOf(';')));
        line = line.substring(line.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        error();
      }

      try {
        ds = Float.parseFloat(line.substring(0, line.indexOf(';')));
        line = line.substring(line.indexOf(';') + 1);
      }
      catch (NumberFormatException exp) {
        error();
      }
      try {
        hw = Float.parseFloat(line.substring(0, line.indexOf(';')));
        line = line.substring(line.indexOf(';') + 1);
      }
      catch (Exception exp) {
        error();
      }

      listCla.addElement(new StClasses(nameClass, ex, ds, hw, coef));
    }

    return (new Major(name,lev, listCla));
  } 

  //method to extract majors from file
  
  static public Vector readFileMaj(File f) {
    Vector v = new Vector();
    if (f.exists()) {
      try {
        String ch;
        Major maj;
        BufferedReader in = new BufferedReader(new FileReader(f));
        do {
          ch = in.readLine();
          if (ch != null) {
            maj = readFileMajor(ch);
            v.addElement(maj);
          }
        }
        while (ch != null);
      }
      catch (IOException ex) {
        System.out.println(ex.toString());
      }
    }
    else { 
    	System.out.println("File does not exist"); 
    	

    }
    return v;
    
  } 

  //method to write to the file from a vector
  
  static public void writeMajorFile(File f, Vector v) {
    Major maj;
    StClasses stC;
    StringBuffer line;
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(f));
      for (int i = 0; i < v.size(); i++) {
        maj = (Major) v.elementAt(i);
        line = new StringBuffer(maj.getName() + ";"+maj.getLevel()+";");

        //adding list of classes
        for (int j = 0; j < maj.getListClass().size(); j++) {
          stC = (StClasses) maj.getListClass().elementAt(j);
          line.append(stC.getName() + ";" + stC.getCoef() + ";" + stC.getEx() + ";" +
                       stC.getDs() + ";" + stC.getHw() + ";");
        }

        //writing of the line to the file 
        out.write(line.toString());
        out.newLine();
      }
      out.close();
    }
    catch (IOException ex) {
    
      error();
    }
 
  }

}