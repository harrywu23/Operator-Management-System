package nz.ac.auckland.se281;

public class Operator {
  private String operatorName;
  private String location;

  public Operator(String operatorName, String location) {
    this.operatorName = operatorName;
    this.location = location;
  }

  public String getOperatorName() {
    return this.operatorName;
  }

  public String getLocation() {
    return this.location;
  }

  public void setOperatorName(String name) {
    this.operatorName = name;
  }

  public void setLocation(String locName) {
    this.location = locName;
  }
}
