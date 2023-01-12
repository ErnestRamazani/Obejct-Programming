//Class that represents a student 

import java.util.Vector;

public class Student {

  private int StudentId; 
  private String lastName, firstName, Sex;
  private Date birthDate; 
  private Major major; 

  //constructor
  public Student(int ce, String n, String p, Date d, String s, Major f) {
    StudentId = ce;
    lastName = n;
    firstName = p;
    birthDate = d;
    Sex = s;
    major = f;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public Major getMajor() {
    return major;
  }

  public int getStudentId() {
    return StudentId;
  }


  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getSex() {
    return Sex;
  }
  public void setBirthDate(Date birtDate) {
    this.birthDate = birtDate;
  }
  public void setMajor(Major major) {
    this.major = major;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public void setSex(String sex) {
    this.Sex = sex;
  }

}