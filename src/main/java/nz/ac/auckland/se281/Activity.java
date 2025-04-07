package nz.ac.auckland.se281;

public class Activity {
  private String activityName;
  private String activityType;
  private String activityId;
  private Operator operator;

  public Activity(String activityName, String activityType, String activityId, Operator operator) {
    this.activityName = activityName;
    this.activityType = activityType;
    this.activityId = activityId;
    this.operator = operator;
  }

  public String getActivityId() {
    return activityId;
  }

  public String getActivityName() {
    return activityName;
  }

  public String getActivityType() {
    return activityType;
  }

  public Operator getOperator() {
    return operator;
  }
}
