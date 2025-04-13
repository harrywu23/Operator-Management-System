package nz.ac.auckland.se281;

public abstract class Review {
  protected String reviewId;
  protected String rating;
  protected String comment;
  protected String reviewerName;
  protected boolean isAnonymous;

  public Review(String reviewerName, boolean isAnonymous, String rating, String comment) {
    this.reviewerName = reviewerName;
    this.isAnonymous = isAnonymous;
    this.rating = rating;
    this.comment = comment;
  }

  public String getReviewId() {
    return reviewId;
  }

  public String getRating() {
    return rating;
  }

  public String getComment() {
    return comment;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewId(String reviewId) {
    this.reviewId = reviewId;
  }

  public boolean isAnonymous() {
    return isAnonymous;
  }
}
