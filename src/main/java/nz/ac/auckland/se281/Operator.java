package nz.ac.auckland.se281;

public class Operator {
  private String operatorName;
  private String location;
  private String operatorID;

  public Operator(String operatorName, String location, String operatorID) {
    this.operatorName = operatorName;
    this.location = location;
    this.operatorID = operatorID;
  }

  public String getOperatorName() {
    return this.operatorName;
  }

  public String getLocation() {
    return this.location;
  }

  public String getOperatorID() {
    return this.operatorID;
  }

  public void setOperatorName(String name) {
    this.operatorName = name;
  }

  public void setLocation(String locName) {
    this.location = locName;
  }
}
