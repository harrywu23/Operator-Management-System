package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  String email;
  private boolean resolved;
  private String operatorResponse;

  public PrivateReview(
      String reviewerName, boolean isAnonymous, String rating, String comment, String email) {
    super(reviewerName, isAnonymous, rating, comment);
    this.email = email;
    this.operatorResponse = "";
    this.resolved = false;
  }

  public void resolveReview(String response) {
    this.resolved = true;
    this.operatorResponse = response;
  }

  public boolean isResolved() {
    return resolved;
  }

  public String getOperatorResponse() {
    return operatorResponse;
  }
}
