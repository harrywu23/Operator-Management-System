package nz.ac.auckland.se281;

public class Activity {
  private String activityName;
  private Types.ActivityType activityType;
  private String id;
  private Operator operator;

  public Activity(
      String activityName, Types.ActivityType activityType, String id, Operator operator) {
    this.activityName = activityName;
    this.activityType = activityType;
    this.id = id;
    this.operator = operator;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return activityName;
  }

  public Types.ActivityType getType() {
    return activityType;
  }

  public Operator getOperator() {
    return operator;
  }
}
