package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {
  private ArrayList<Operator> operatorList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorList = new ArrayList<Operator>();
  }

  private String generateOperatorCode(Operator operator) {
    String operatorName = operator.getOperatorName();
    String locationStr = operator.getLocation();
    Location rawLocation = Location.fromString(locationStr);

    // Build acronym from the operator name
    String[] words = operatorName.split(" ");
    String result = "";
    for (String word : words) {
      result += word.charAt(0);
    }

    // Count number of operators from the same location
    int locationCount = 0;
    for (Operator op : operatorList) {
      Location opLoc = Location.fromString(op.getLocation());
      if (opLoc.getNameEnglish().equalsIgnoreCase(rawLocation.getNameEnglish())
          || opLoc.getNameTeReo().equalsIgnoreCase(rawLocation.getNameTeReo())
          || opLoc
              .getLocationAbbreviation()
              .equalsIgnoreCase(rawLocation.getLocationAbbreviation())) {
        locationCount++;
      }
    }

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

    // Filter matching operators
    for (Operator operator : operatorList) {
      Location rawLocation = Location.fromString(operator.getLocation());
      String nameTeReo = rawLocation.getNameTeReo().toLowerCase();
      String nameEnglish = rawLocation.getNameEnglish().toLowerCase();
      String fullName = rawLocation.getFullName().toLowerCase();
      String opName = operator.getOperatorName().toLowerCase();

      if (keyword.equals("*")
          || nameTeReo.contains(keyword)
          || nameEnglish.contains(keyword)
          || fullName.contains(keyword)
          || opName.contains(keyword)) {
        matchingOperators.add(operator);
      }
    }

    int operatorCount = matchingOperators.size();

    if (operatorCount == 0) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else if (operatorCount == 1) {
      Operator op = matchingOperators.get(0);
      Location rawLocation = Location.fromString(op.getLocation());
      String operatorCode = generateOperatorCode(op);

      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      MessageCli.OPERATOR_ENTRY.printMessage(
          op.getOperatorName(), operatorCode, rawLocation.getFullName());
    } else {
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(operatorCount), "s", ":");

      for (Operator op : matchingOperators) {
        Location rawLocation = Location.fromString(op.getLocation());
        String operatorCode = generateOperatorCode(op);
        MessageCli.OPERATOR_ENTRY.printMessage(
            op.getOperatorName(), operatorCode, rawLocation.getFullName());
      }
    }
  }

  // } else if (operatorCount > 1) {
  //   MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(operatorCount), "s",
  // ":");

  //   for (int i = 0; i < operatorCount; i++) {
  //     String currentLocation = operatorList.get(i).getLocation();
  //     Location rawLocation = Location.fromString(currentLocation);
  //     String currentOperator = operatorList.get(i).getOperatorName();
  //     String[] words = currentOperator.split(" ");
  //     String result = "";

  //     for (String word : words) {
  //       result = result + word.charAt(0);
  //     }

  //     String abbreviation = rawLocation.getLocationAbbreviation();
  //     int locationCount = 0;
  //     for (Operator operator : operatorList) {
  //       String opLocation = operator.getLocation();
  //       if (opLocation.equalsIgnoreCase(rawLocation.getNameEnglish())
  //           || opLocation.equalsIgnoreCase(rawLocation.getNameTeReo())
  //           || opLocation.equalsIgnoreCase(rawLocation.getLocationAbbreviation())) {
  //         locationCount++;
  //         String threeDigit = String.format("%03d", locationCount);
  //         String operatorCode = result + "-" + abbreviation + "-" + threeDigit;
  //         MessageCli.OPERATOR_ENTRY.printMessage(
  //             currentOperator, operatorCode, rawLocation.getFullName());
  //       }
  //     }
  //   }

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

    Operator newOperator = new Operator(operatorName, location);
    operatorList.add(newOperator);

    // creating an acroynm from the first letter of each word
    String[] words = operatorName.split(" ");
    String result = "";

    for (String word : words) {
      result = result + word.charAt(0);
    }

    String operatorCode = generateOperatorCode(newOperator);

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
