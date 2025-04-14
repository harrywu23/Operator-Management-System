package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  private String email;
  private boolean resolved;
  private String operatorResponse;
  private boolean followUpRequired;

  public PrivateReview(
      String reviewerName, String rating, String comment, String email, boolean followUpRequired) {
    super(reviewerName, rating, comment);
    this.followUpRequired = followUpRequired;
    this.email = email;

    if (!followUpRequired) {
      this.resolved = true;
      this.operatorResponse = "-";
    } else {
      this.resolved = false;
      this.operatorResponse = "";
    }
  }

  public void resolveReview(String response) {
    this.resolved = true;
    this.operatorResponse = response;
  }

  public String getEmail() {
    return email;
  }

  public boolean isResolved() {
    return resolved;
  }

  public String getOperatorResponse() {
    return operatorResponse;
  }

  public boolean isFollowUpRequired() {
    return followUpRequired;
  }
}
