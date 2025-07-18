package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Operator {
  private String operatorName;
  private Types.Location location;
  private String operatorCode;
  private ArrayList<Activity> activityList;

  public Operator(String operatorName, Types.Location location, String operatorCode) {
    this.operatorName = operatorName;
    this.location = location;
    this.operatorCode = operatorCode;
    activityList = new ArrayList<Activity>();
  }

  public String getOperatorName() {
    return this.operatorName;
  }

  public Types.Location getLocation() {
    return this.location;
  }

  public String getOperatorCode() {
    return this.operatorCode;
  }

  public void setOperatorName(String name) {
    this.operatorName = name;
  }

  public void setLocation(Types.Location loc) {
    this.location = loc;
  }

  public void addActivity(Activity activity) {
    activityList.add(activity);
  }

  public ArrayList<Activity> getActivities() {
    return activityList;
  }
}
