package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private ArrayList<String> operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorList = new ArrayList<String>(); // initialising operatorList as a new arraylist
  }

  public void searchOperators(String keyword) {

    int operatorCount = 0; // variable to track operator count

    for (int i = 0; i < operatorList.size(); i++) {
      String operator = operatorList.get(i); // get operator name at current index
      if (operator.equals(keyword)) {
        operatorCount++;
      }
    }

    // print a message if no operator is found
    if (operatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    }
  }

  public void createOperator(String operatorName, String location) {

    Location rawLocation = Location.fromString(location);

    // Trim any spaces in the operator name and check if it is at least 3 characters long
    operatorName = operatorName.trim();
    if (operatorName.length() >= 3) {
      System.out.println(
          "Successfully created operator '" + operatorName + "' located in '" + rawLocation + "'.");
    }

    // creating an acroynm from the first letter of each word
    String[] words = operatorName.split(" ");
    String result = "";

    for (String word : words) {
      result = result + word.charAt(0);
    }
    System.out.println(result);
    String abbreviation = rawLocation.getLocationAbbreviation();
    String operatorCode = result + "-" + abbreviation;
    System.out.println(operatorCode);
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
