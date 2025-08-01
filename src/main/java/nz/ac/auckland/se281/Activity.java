package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Activity {
  private String activityName;
  private String activityType;
  private String activityId;
  private Operator operator;
  private Types.Location location;
  private int reviewCounter = 0;
  private ArrayList<Review> reviews = new ArrayList<Review>();

  public Activity(String activityName, String activityType, String activityId, Operator operator) {
    this.activityName = activityName;
    this.activityType = activityType;
    this.activityId = activityId;
    this.operator = operator;
    this.location = operator.getLocation();
  }

  // method to check if it matches keyword
  public boolean matchesKeyword(String keyword) {
    keyword = keyword.trim().toLowerCase();
    return activityName.toLowerCase().contains(keyword)
        || activityType.toLowerCase().contains(keyword)
        || location.getNameTeReo().toLowerCase().contains(keyword)
        || location.getNameEnglish().toLowerCase().contains(keyword)
        || location.getFullName().toLowerCase().contains(keyword)
        || location.getLocationAbbreviation().toLowerCase().contains(keyword)
        || keyword.equals("*");
  }

  public String getActivityId() {
    return activityId;
  }

  public Types.Location getLocation() {
    return this.location;
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

  public ArrayList<Review> getReviews() {
    return reviews;
  }

  public void addReview(Review review) {
    reviews.add(review);
  }

  public int getNextReviewNumber() {
    reviewCounter++;
    return reviewCounter;
  }
}
