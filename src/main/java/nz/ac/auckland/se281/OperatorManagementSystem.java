package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorManagementSystem {

  private ArrayList<String> operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorList = new ArrayList<String>();
  }

  public void searchOperators(String keyword) {

    int operatorCount = 0;

    for (int i = 0; i < operatorList.size(); i++) {
      String operator = operatorList.get(i);
      if (operator.equals(keyword)) {
        operatorCount++;
      }
    }

    if (operatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    }
  }

  public void createOperator(String operatorName, String location) {

    operatorName = operatorName.trim();
    if (operatorName.length() >= 3) {
      System.out.println("Successfully created operator " + "'" + operatorName + "'");
    }

    String[] words = operatorName.split(" ");
    String result = "";

    for (String word : words) {
      result = result + word.charAt(0);
    }
    System.out.println(result);
  }

  public void viewActivities(String operatorId) {}

  public void createActivity(String activityName, String activityType, String operatorId) {}

  public void searchActivities(String keyword) {}

  public void addPublicReview(String activityId, String[] options) {}

  public void addPrivateReview(String activityId, String[] options) {}

  public void addExpertReview(String activityId, String[] options) {}

  public void displayReviews(String activityId) {}

  public void endorseReview(String reviewId) {}

  public void resolveReview(String reviewId, String response) {}

  public void uploadReviewImage(String reviewId, String imageName) {}

  public void displayTopActivities() {}
}
