

//class that represent a class

public class StClasses {
	
  private String name; 
  private float ds,hw,ex;
  private int coef;//Coefficient of the class


  public StClasses(String n,float e,float d,float t,int c) {
    name=n;
    ds=d;
    hw=t;
    ex=e;
    coef=c;
  }
  public float getDs() {
    return ds;
  }
  public float getEx() {
    return ex;
  }
  public String getName() {
    return name;
  }
  public float getHw() {
    return hw;
  }
  //calculating the average 
  public double average() {
    if(hw>0) return hw*0.4+0.6*(ex*2+ds)/3; //if the class has a homework 
    else return (ex*2+ds)/3;
  }
  public int getCoef() {
    return coef;
  }
  public void setDs(float ds) {
    this.ds = ds;
  }
  public void setEx(float ex) {
    this.ex = ex;
  }
  public void setHw(float hw) {
    this.hw = hw;
  }



}