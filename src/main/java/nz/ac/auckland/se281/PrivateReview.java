package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  String email;

  public PrivateReview(
      String reviewerName, boolean isAnonymous, String rating, String comment, String email) {
    super(reviewerName, isAnonymous, rating, comment);
    this.email = email;
  }
}
