//Major class

import java.util.Vector;

public class Major {
  private String name;
  private int level;
  private Vector listClass;

  public Major(){

  }


  public Major(String n,int lev,Vector lc) {
    name=n;
    level = lev;
    listClass=lc;
  }
  public Vector getListClass() {
    return listClass;
  }
  public String getName() {
    return name;
  }

//average
  public double average(){
    StClasses stCla;
    double avg=0;
    int size=listClass.size(),coef=0;
    for(int i=0;i<size;i++){
      stCla=(StClasses)listClass.elementAt(i);
      avg+=stCla.average()*stCla.getCoef();
      coef+=stCla.getCoef();
    }
    return avg/coef;
  }
  public int getLevel() {
    return level;
  }




}