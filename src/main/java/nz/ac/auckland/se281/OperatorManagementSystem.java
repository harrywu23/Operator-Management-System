package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {
  private ArrayList<Operator> operatorList;
  private int specificNameCount = 0;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorList = new ArrayList<Operator>();
  }

  public void searchOperators(String keyword) {
    keyword = keyword.toLowerCase().trim();
    int operatorCount = operatorList.size(); // variable to track operator count
    for (Operator operator : operatorList) {
      // Initialize rawLocation for each operator
      Location rawLocation = Location.fromString(operator.getLocation());

      // Get location name in te reo Māori and english
      String operatorLocation = rawLocation.getNameTeReo();
      String operatorLocation2 = rawLocation.getNameEnglish();
      String operatorLocation3 = rawLocation.toString();

      // Case-insensitive matching for location
      if (operatorLocation.toLowerCase().contains(keyword)
          || operatorLocation3.toLowerCase().contains(keyword)
          || operatorLocation2.toLowerCase().contains(keyword)) {
        specificNameCount++;
        operatorCount = specificNameCount;
      }
    }

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
      for (Operator operator : operatorList) {
        String opLocation = operator.getLocation();
        if (opLocation.equalsIgnoreCase(rawLocation.getNameEnglish())
            || opLocation.equalsIgnoreCase(rawLocation.getNameTeReo())
            || opLocation.equalsIgnoreCase(rawLocation.getLocationAbbreviation())) {
          locationCount++;
        }
      }

      String threeDigit = String.format("%03d", locationCount);

      String operatorCode = result + "-" + abbreviation + "-" + threeDigit;
      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      MessageCli.OPERATOR_ENTRY.printMessage(
          currentOperator, operatorCode, rawLocation.getFullName());
    } else if (operatorCount > 1) {
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(operatorCount), "s", ":");

      for (int i = 0; i < operatorCount; i++) {
        String currentLocation = operatorList.get(i).getLocation();
        Location rawLocation = Location.fromString(currentLocation);
        String currentOperator = operatorList.get(i).getOperatorName();
        String[] words = currentOperator.split(" ");
        String result = "";

        for (String word : words) {
          result = result + word.charAt(0);
        }

        String abbreviation = rawLocation.getLocationAbbreviation();
        int locationCount = 0;
        for (Operator operator : operatorList) {
          String opLocation = operator.getLocation();
          if (opLocation.equalsIgnoreCase(rawLocation.getNameEnglish())
              || opLocation.equalsIgnoreCase(rawLocation.getNameTeReo())
              || opLocation.equalsIgnoreCase(rawLocation.getLocationAbbreviation())) {
            locationCount++;
            String threeDigit = String.format("%03d", locationCount);
            String operatorCode = result + "-" + abbreviation + "-" + threeDigit;
            MessageCli.OPERATOR_ENTRY.printMessage(
                currentOperator, operatorCode, rawLocation.getFullName());
          }
        }
      }
    }
  }

  public void createOperator(String operatorName, String location) {

    // Trim any spaces in the operator name and check if it is at least 3 characters long
    operatorName = operatorName.trim();
    if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    Location rawLocation = Location.fromString(location);

    for (Operator op : operatorList) {
      if (op.getOperatorName().equalsIgnoreCase(operatorName)
          && op.getLocation().equalsIgnoreCase(location)) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, rawLocation.getFullName());
        return;
      }
    }

    String abbreviation = rawLocation.getLocationAbbreviation();
    Operator newOperator = new Operator(operatorName, location);
    operatorList.add(newOperator);

    // creating an acroynm from the first letter of each word
    String[] words = operatorName.split(" ");
    String result = "";

    for (String word : words) {
      result = result + word.charAt(0);
    }

    int locationCount = 0;
    for (Operator operator : operatorList) {
      String opLocation = operator.getLocation();
      if (opLocation.equalsIgnoreCase(rawLocation.getNameEnglish())
          || opLocation.equalsIgnoreCase(rawLocation.getNameTeReo())
          || opLocation.equalsIgnoreCase(rawLocation.getLocationAbbreviation())) {
        locationCount++;
      }
    }

    String threeDigit = String.format("%03d", locationCount);
    String operatorCode = result + "-" + abbreviation + "-" + threeDigit;

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorCode, rawLocation.getFullName());
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
