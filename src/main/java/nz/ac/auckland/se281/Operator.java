package nz.ac.auckland.se281;

public class Operator {
  private String operatorName;
  private String location;
  private String operatorCode;

  public Operator(String operatorName, String location, String operatorCode) {
    this.operatorName = operatorName;
    this.location = location;
    this.operatorCode = operatorCode;
  }

  public String getOperatorName() {
    return this.operatorName;
  }

  public String getLocation() {
    return this.location;
  }

  public String operatorCode() {
    return this.operatorCode;
  }

  public void setOperatorName(String name) {
    this.operatorName = name;
  }

  public void setLocation(String locName) {
    this.location = locName;
  }
}
