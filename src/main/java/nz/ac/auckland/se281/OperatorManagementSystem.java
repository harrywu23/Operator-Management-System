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

    int operatorCount = operatorList.size(); // variable to track operator count
    // print a message if no operator is found
    if (operatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (operatorCount == 1) {
      String currentLocation = operatorList.get(0).getLocation();
      Location rawLocation = Location.fromString(currentLocation);
      String currentOperator = operatorList.get(0).getOperatorName();
      String[] words = currentOperator.split(" ");
      String result = "";

      for (String word : words) {
        result = result + word.charAt(0);
      }

      String abbreviation = rawLocation.getLocationAbbreviation();
      int locationCount = 0;
      for (int i = 0; i < operatorList.size(); i++) {
        if (currentLocation.equalsIgnoreCase(rawLocation.getNameEnglish())
            || currentLocation.equalsIgnoreCase(rawLocation.getNameTeReo())
            || currentLocation.equalsIgnoreCase(rawLocation.getLocationAbbreviation())) {
          locationCount++;
        }
      }

      String threeDigit = String.format("%03d", locationCount);

      String operatorCode = result + "-" + abbreviation + "-" + threeDigit;
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      MessageCli.OPERATOR_ENTRY.printMessage(
          currentOperator, operatorCode, rawLocation.getFullName());
    } else {
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(operatorCount), "s", ":");
    }
  }

  public void createOperator(String operatorName, String location) {

    // Trim any spaces in the operator name and check if it is at least 3 characters long
    operatorName = operatorName.trim();
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

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
    String operatorCode = result + "-" + abbreviation + "-" + threeDigit;

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorCode, rawLocation.getFullName());
  }

  //   for (int i = 0; i < operatorList.size(); i++) {
  //     if (operatorName.length() >= 3) {
  //       MessageCli.OPERATOR_CREATED.printMessage(
  //           operatorName, operatorCode, rawLocation.getFullName());
  //     } else if (operatorName.length() >= 3) {
  //       MessageCli.OPERATOR_CREATED.printMessage(
  //           operatorName, operatorCode, rawLocation.getFullName());
  //     }
  //   }
  // }

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
