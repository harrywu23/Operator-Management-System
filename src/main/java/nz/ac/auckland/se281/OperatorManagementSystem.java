package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {
  private ArrayList<Operator> operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorList = new ArrayList<Operator>();
  }

  // method that generates a unique operator code based on the operator's name and location.
  private String generateOperatorCode(Operator operator) {
    String operatorName = operator.getOperatorName();
    Location rawLocation = operator.getLocation();

    // Build acronym from the operator name
    String[] words = operatorName.split(" ");
    String result = "";
    for (String word : words) {
      result += word.charAt(0);
    }

    result = result.toUpperCase();

    // Count number of operators from the same location
    int locationCount = 0;
    for (Operator op : operatorList) {
      if (op == operator) {
        break;
      }
      Location opLoc = op.getLocation();
      if (opLoc.getLocationAbbreviation().equalsIgnoreCase(rawLocation.getLocationAbbreviation())) {
        locationCount++;
      }
    }

    locationCount += 1;
    String threeDigit = String.format("%03d", locationCount);
    return result + "-" + rawLocation.getLocationAbbreviation() + "-" + threeDigit;
  }

  public void searchOperators(String keyword) {
    keyword = keyword.toLowerCase().trim();
    ArrayList<Operator> matchingOperators = new ArrayList<>();

    // No operators exist
    if (operatorList.isEmpty()) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      return;
    }

    // Loop through all operators to find matches based on the keyword.
    for (Operator operator : operatorList) {
      Location rawLocation = operator.getLocation();
      String nameTeReo = rawLocation.getNameTeReo().toLowerCase();
      String nameEnglish = rawLocation.getNameEnglish().toLowerCase();
      String fullName = rawLocation.getFullName().toLowerCase();
      String opName = operator.getOperatorName().toLowerCase();
      String abbreviation = rawLocation.getLocationAbbreviation().toLowerCase();

      // If the keyword matches any of the location or operator details, add the operator to the
      // list.
      if (keyword.equals("*")
          || nameTeReo.contains(keyword)
          || nameEnglish.contains(keyword)
          || fullName.contains(keyword)
          || opName.contains(keyword)
          || abbreviation.contains(keyword)) {
        matchingOperators.add(operator);
      }
    }

    int operatorCount = matchingOperators.size();

    if (operatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (operatorCount == 1) {
      Operator op = matchingOperators.get(0);
      Location rawLocation = op.getLocation();
      String operatorCode = op.getOperatorCode();

      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      MessageCli.OPERATOR_ENTRY.printMessage(
          op.getOperatorName(), operatorCode, rawLocation.getFullName());
    } else {
      // If multiple operators are found, print the count and details of each one.
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(operatorCount), "s", ":");

      for (Operator op : matchingOperators) {
        Location rawLocation = op.getLocation(); // Get location.
        String operatorCode = generateOperatorCode(op); // Generate the operator code.
        MessageCli.OPERATOR_ENTRY.printMessage(
            op.getOperatorName(), operatorCode, rawLocation.getFullName());
      }
    }
  }

  public void createOperator(String operatorName, String location) {

    // Trim any spaces in the operator name and check if it is at least 3 characters long
    operatorName = operatorName.trim();

    Location rawLocation = Location.fromString(location);
    if (rawLocation == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    } else if (operatorName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    // Check if an operator with the same name and location already exists.
    for (Operator op : operatorList) {
      if (op.getOperatorName().equalsIgnoreCase(operatorName) && op.getLocation() == rawLocation) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, rawLocation.getFullName());
        return;
      }
    }

    // Create a temporary operator generate the code.
    Operator tempOperator = new Operator(operatorName, rawLocation, "TEMP");
    String operatorCode = generateOperatorCode(tempOperator);

    // Create a new operator and add it to the list - including operatorID NAME AND LOCATION
    Operator newOperator = new Operator(operatorName, rawLocation, operatorCode);
    operatorList.add(newOperator);

    MessageCli.OPERATOR_CREATED.printMessage(
        operatorName, newOperator.getOperatorCode(), rawLocation.getFullName());
  }

  public void viewActivities(String operatorId) {

    Operator matchedOperator = null;

    // TEST # 1 - looping through operatorList to find the one that matches
    for (Operator operator : operatorList) {
      if (operator.getOperatorCode().equals(operatorId)) {
        matchedOperator = operator;
        break;
      }
    }

    // Test #1 - is an invalid operator ID
    if (matchedOperator == null) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }
    // Test #2 - if no activites found when viewing
    if (matchedOperator.getActivities() == null || matchedOperator.getActivities().isEmpty()) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    
    if (activityName == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
      
      // Test #3 activtiy name is too short
    } else if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }
  }

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
