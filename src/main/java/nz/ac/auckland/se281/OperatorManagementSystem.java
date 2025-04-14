package nz.ac.auckland.se281;

import java.util.ArrayList;

public class OperatorManagementSystem {
  private ArrayList<Operator> operatorList;
  private ArrayList<Activity> activityList;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    operatorList = new ArrayList<Operator>();
    activityList = new ArrayList<Activity>();
  }

  // method that generates a unique operator code based on the operator's name and location.
  private String generateOperatorCode(Operator operator) {
    String operatorName = operator.getOperatorName();
    Types.Location rawLocation = operator.getLocation();

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
      Types.Location opLoc = op.getLocation();
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
      Types.Location rawLocation = operator.getLocation();
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
      Types.Location rawLocation = op.getLocation();
      String operatorCode = op.getOperatorCode();

      MessageCli.OPERATORS_FOUND.printMessage("is", "1", "", ":");
      MessageCli.OPERATOR_ENTRY.printMessage(
          op.getOperatorName(), operatorCode, rawLocation.getFullName());
    } else {
      // If multiple operators are found, print the count and details of each one.
      MessageCli.OPERATORS_FOUND.printMessage("are", Integer.toString(operatorCount), "s", ":");

      for (Operator op : matchingOperators) {
        Types.Location rawLocation = op.getLocation(); // Get location.
        String operatorCode = generateOperatorCode(op); // Generate the operator code.
        MessageCli.OPERATOR_ENTRY.printMessage(
            op.getOperatorName(), operatorCode, rawLocation.getFullName());
      }
    }
  }

  public void createOperator(String operatorName, String location) {

    // Trim any spaces in the operator name and check if it is at least 3 characters long
    operatorName = operatorName.trim();

    Types.Location rawLocation = Types.Location.fromString(location);
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

    // Test # 7 - Viewing activites at a saved single operator

    int activityCount = matchedOperator.getActivities().size();

    // Testing 0 activity count
    if (activityCount == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ":");
    }

    // Testing 1 activity count
    if (activityCount == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", Integer.toString(activityCount), "y", ":");
      for (int i = 0; i < matchedOperator.getActivities().size(); i++) {
        Activity activityList = matchedOperator.getActivities().get(i);
        MessageCli.ACTIVITY_ENTRY.printMessage(
            activityList.getActivityName(),
            activityList.getActivityId(),
            activityList.getActivityType(),
            matchedOperator.getOperatorName());
      }
    }
    // Testing if activity count > 1
    if (activityCount > 1) {

      MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(activityCount), "ies", ":");

      for (int i = 0; i < matchedOperator.getActivities().size(); i++) {
        Activity activityList = matchedOperator.getActivities().get(i);
        MessageCli.ACTIVITY_ENTRY.printMessage(
            activityList.getActivityName(),
            activityList.getActivityId(),
            activityList.getActivityType(),
            matchedOperator.getOperatorName());
      }
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {

    activityName = activityName.trim();

    // activity name invalid or nothing entered but blank space
    if (activityName == null || activityName.length() == 0) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }

    activityType = Types.ActivityType.fromString(activityType).toString();

    // Test #3 activity name is too short
    if (activityName.length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }
    // Test #4 create activity invalid operator
    Operator matchedOperator = null;
    for (Operator operator : operatorList) {
      if (operator.getOperatorCode().equals(operatorId)) {
        matchedOperator = operator;
        break;
      }
    }
    // Test #4
    if (matchedOperator == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }
    // Test #5,6 - create activity with extra id letters
    int activityCount = matchedOperator.getActivities().size();
    String activityCountDigits = String.format("%03d", activityCount + 1);
    String activityId = operatorId + "-" + activityCountDigits;

    // create new activity
    Activity newActivity = new Activity(activityName, activityType, activityId, matchedOperator);
    matchedOperator.addActivity(newActivity);
    activityList.add(newActivity);

    MessageCli.ACTIVITY_CREATED.printMessage(
        activityName, activityId, activityType, matchedOperator.getOperatorName());
  }

  public void searchActivities(String keyword) {
    keyword = keyword.toLowerCase().trim();
    ArrayList<Activity> matchingActivity = new ArrayList<>();

    // test case # 9 - no activities found
    if (activityList.isEmpty()) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }

    for (Activity activity : activityList) {
      if (activity.matchesKeyword(keyword)) {
        matchingActivity.add(activity);
      }
    }

    int activityCount = matchingActivity.size();

    // test 10 search activies none found
    if (activityCount == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else if (activityCount == 1) {
      MessageCli.ACTIVITIES_FOUND.printMessage("is", "1", "y", ":");
    } else {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", Integer.toString(activityCount), "ies", ":");
    }

    for (Activity activity : matchingActivity) {
      MessageCli.ACTIVITY_ENTRY.printMessage(
          activity.getActivityName(),
          activity.getActivityId(),
          activity.getActivityType(),
          activity.getOperator().getOperatorName());
    }
  }

  public void addPublicReview(String activityId, String[] options) {

    Activity matchedActivity = null;

    // Search for the activity with the given ID
    for (Activity activity : activityList) {
      if (activity.getActivityId().equals(activityId)) {
        matchedActivity = activity;
        break;
      }
    }

    // Test # 2 add public review invalid activity
    if (matchedActivity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    // Test # 3 add public review ok
    int reviewNumber = matchedActivity.getNextReviewNumber();
    String reviewId = activityId + "-R" + reviewNumber;
    MessageCli.REVIEW_ADDED.printMessage("Public", reviewId, matchedActivity.getActivityName());

    // Test # 8 saving the public review info into system
    String reviewerName = options[0];
    String rating = options[2];
    String comment = options[3];
    boolean isAnonymous = options[1].equalsIgnoreCase("y");

    Review newReview = new PublicReview(reviewerName, isAnonymous, rating, comment);
    matchedActivity.addReview(newReview);
    // storing the new reviewID in the reviewClass and setting it so we can get it later
    newReview.setReviewId(reviewId);
    newReview.setReviewType(Types.ReviewType.PUBLIC);
  }

  public void addPrivateReview(String activityId, String[] options) {

    Activity matchedActivity = null;

    for (Activity activity : activityList) {
      if (activity.getActivityId().equals(activityId)) {
        matchedActivity = activity;
        break;
      }
    }

    // Test add private review invalid activity
    if (matchedActivity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    // Test # 4 add private review ok
    int reviewNumber = matchedActivity.getNextReviewNumber();
    String reviewId = activityId + "-R" + reviewNumber;
    MessageCli.REVIEW_ADDED.printMessage("Private", reviewId, matchedActivity.getActivityName());
    // Test # 9 saving private review info

    String reviewerName = options[0];
    boolean followUpRequired = options[4].equals("y");
    String rating = options[2];
    String comment = options[3];
    String email = options[1];

    Review newReview = new PrivateReview(reviewerName, rating, comment, email, followUpRequired);
    matchedActivity.addReview(newReview);
    // storing the new reviewID in the reviewClass and setting it so we can get it later
    newReview.setReviewId(reviewId);
    newReview.setReviewType(Types.ReviewType.PRIVATE);
  }

  public void addExpertReview(String activityId, String[] options) {

    Activity matchedActivity = null;

    for (Activity activity : activityList) {
      if (activity.getActivityId().equals(activityId)) {
        matchedActivity = activity;
        break;
      }
    }

    // Test add Expert review invalid activity
    if (matchedActivity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    // Test # 4 add Expert review ok
    int reviewNumber = matchedActivity.getNextReviewNumber();
    String reviewId = activityId + "-R" + reviewNumber;
    MessageCli.REVIEW_ADDED.printMessage("Expert", reviewId, matchedActivity.getActivityName());

    // Test # 9 saving the expert review info into system
    String reviewerName = options[0];
    String rating = options[1];
    String comment = options[2];
    boolean wouldRecommend = options[3].equals("y");

    Review newReview = new ExpertReview(reviewerName, rating, comment, wouldRecommend);
    matchedActivity.addReview(newReview);
    // storing the new reviewID in the reviewClass and setting it so we can get it later
    newReview.setReviewId(reviewId);
    newReview.setReviewType(Types.ReviewType.EXPERT);
  }

  public void displayReviews(String activityId) {
    // Test case # 1 display reviews no reviews
    Activity matchedActivity = null;

    for (Activity activity : activityList) {
      if (activity.getActivityId().equals(activityId)) {
        matchedActivity = activity;
        break;
      }
    }

    // if no reviews @ activity
    if (matchedActivity.getReviews().size() == 0) {
      String matchedActivityName = matchedActivity.getActivityName();
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", matchedActivityName);
      return;
    }

    // Test # 8 saving the public review info into system and DISPLAYING IT
    if (matchedActivity.getReviews().size() == 1) {
      for (Review review : matchedActivity.getReviews()) {

        // Access the values from the Review object
        String rating = review.getRating();
        String reviewId = review.getReviewId();
        String reviewerName = review.getReviewerName();
        String reviewComment = review.getComment();
        String reviewType = review.getReviewType();

        // print out the info for public review
        if (review.getReviewType().equals(Types.ReviewType.PUBLIC.toString())) {
          MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", matchedActivity.getActivityName());
          if (review instanceof PublicReview) {
            PublicReview pubReview = (PublicReview) review;
            if (pubReview.isAnonymous()) {
              // review is anonymous
              MessageCli.REVIEW_ENTRY_HEADER.printMessage(
                  rating, "5", reviewType, reviewId, "Anonymous");
            } else {
              // review is not anonymous
              MessageCli.REVIEW_ENTRY_HEADER.printMessage(
                  rating, "5", reviewType, reviewId, reviewerName);
            }
            // printing review comment
            MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(reviewComment);
            // Test case #11 printing out public review if its endorsed
            if (pubReview.isEndorsed()) {
              MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
            }
          }
        }
        // if private review
        if (review.getReviewType().equals(Types.ReviewType.PRIVATE.toString())) {
          MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", matchedActivity.getActivityName());
          MessageCli.REVIEW_ENTRY_HEADER.printMessage(
              rating, "5", reviewType, reviewId, reviewerName);
          MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(reviewComment);
          if (review instanceof PrivateReview) {
            PrivateReview privateReview = (PrivateReview) review;

            // Test case # 13 resolve private review
            if (privateReview.isResolved()) {
              MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(privateReview.getOperatorResponse());
            } else if (privateReview.isFollowUpRequired()) {
              MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(privateReview.getEmail());
            }
          }
        }
        // Test # 10 printing extra things if expert review
        // print out info for expert review
        if (review.getReviewType().equals(Types.ReviewType.EXPERT.toString())) {
          MessageCli.REVIEWS_FOUND.printMessage("is", "1", "", matchedActivity.getActivityName());
          MessageCli.REVIEW_ENTRY_HEADER.printMessage(
              rating, "5", reviewType, reviewId, reviewerName);
          MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(reviewComment);
          if (review instanceof ExpertReview) {
            ExpertReview expertReview = (ExpertReview) review;
            if (expertReview.isRecommended()) {
              MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
            }
            // test 15 upload image expert review
            // test 16 - upload multiple images expert review
            if (expertReview.getImageName() != null) {
              MessageCli.REVIEW_ENTRY_IMAGES.printMessage(expertReview.getImagesAsString());
            }
          }
        }
      }
    }
  }

  public void endorseReview(String reviewId) {

    for (Activity activity : activityList) {
      for (Review review : activity.getReviews()) {
        if (review.getReviewId().equals(reviewId)) {
          // Found matchingID. Check if it's public
          if (review instanceof PublicReview) {
            PublicReview pubReview = (PublicReview) review;
            pubReview.endorseReview();
            MessageCli.REVIEW_ENDORSED.printMessage(reviewId);
          } else {
            MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
          }
          return;
        }
      }
    }
    // If not found
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void resolveReview(String reviewId, String response) {
    for (Activity activity : activityList) {
      for (Review review : activity.getReviews()) {
        if (review.getReviewId().equals(reviewId)) {
          // Found the review. now check if Private
          if (review instanceof PrivateReview) {
            PrivateReview privateReview = (PrivateReview) review;
            privateReview.resolveReview(response); // pass response to set it

            // Test case # 13 resolve private review
            MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
          } else {
            MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
          }
          return;
        }
      }
    }

    // Test #12 resolve private review not found
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    for (Activity activity : activityList) {
      for (Review review : activity.getReviews()) {
        if (review.getReviewId().equals(reviewId)) {
          // Found the review. now check if Private
          if (review instanceof ExpertReview) {
            ExpertReview expertReview = (ExpertReview) review;
            // upload image logic
            expertReview.uploadImage(imageName);
            MessageCli.REVIEW_IMAGE_ADDED.printMessage(expertReview.getImageName(), reviewId);
          } else {
            MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
          }
          return;
        }
      }
    }
    // If not found
    MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
  }

  public void displayTopActivities() {
    ArrayList<String> ratings = new ArrayList<>();
    int ratingIntegerTotal = 0;

    for (Types.Location location : Types.Location.values()) {
      boolean hasReviewedActivity = false;

      for (Activity activity : activityList) {
        if (activity.getLocation().equals(location))
          for (Review review : activity.getReviews()) {
            if (review instanceof PublicReview || review instanceof ExpertReview) {
              hasReviewedActivity = true;
              ratings.add(review.getRating());
            }
          }

        // If there are reviewed activities
        if (hasReviewedActivity) {
          for (int i = 0; i < ratings.size(); i++) {
            ratingIntegerTotal += Integer.parseInt(ratings.get(i));
          }
          int averageRating = ratingIntegerTotal / ratings.size();
          String averageString = Integer.toString(averageRating);

          MessageCli.TOP_ACTIVITY.printMessage(
              location.toString(), activity.getActivityName(), averageString);
        }
      }
      // If there are no reviewed activities
      if (!hasReviewedActivity) {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(location.toString());
      }
    }
  }
}
