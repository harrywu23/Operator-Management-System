package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {
  private ArrayList<Operator> operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorList = new ArrayList<Operator>();
  }

  public void searchOperators(String keyword) {

    int operatorCount = 0; // variable to track operator count

    // for (int i = 0; i < temporary.size(); i++) {
    //   String operator = temporary.get(i).getOperatorName(); // get operator name at current index
    //   if (operator.equals(keyword)) {
    //     operatorCount++;
    //   }
    // }

    // print a message if no operator is found
    if (operatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    }
  }

  public void createOperator(String operatorName, String location) {

    Operator newOperator = new Operator(operatorName, location);
    Location rawLocation = Location.fromString(location);
    operatorList.add(newOperator);

    // creating an acroynm from the first letter of each word
    String[] words = operatorName.split(" ");
    String result = "";

    for (String word : words) {
      result = result + word.charAt(0);
    }

    String abbreviation = rawLocation.getLocationAbbreviation();
    int locationCount = 0;
    for (int i = 0; i < operatorList.size(); i++) {
      if (location.equalsIgnoreCase(rawLocation.getNameEnglish())
          || location.equalsIgnoreCase(rawLocation.getNameTeReo())
          || location.equalsIgnoreCase(rawLocation.getLocationAbbreviation())) {
        locationCount++;
      }
    }

    String threeDigit = String.format("%03d", locationCount);
    String operatorCode = "('" + result + "-" + abbreviation + "-" + threeDigit + "')";

    // Trim any spaces in the operator name and check if it is at least 3 characters long
    operatorName = operatorName.trim();
    if (operatorName.length() >= 3) {
      System.out.println(
          "Successfully created operator '"
              + operatorName
              + "' "
              + operatorCode
              + " located in '"
              + rawLocation
              + "'.");
    }
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
