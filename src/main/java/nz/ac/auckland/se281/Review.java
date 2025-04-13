package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ReviewType;

public abstract class Review {
  private String reviewId;
  private String rating;
  private String comment;
  private String reviewerName;
  private boolean isAnonymous;
  private String reviewType;

  public Review(String reviewerName, boolean isAnonymous, String rating, String comment) {
    this.reviewerName = reviewerName;
    this.isAnonymous = isAnonymous;
    this.rating = rating;
    this.comment = comment;
  }

  public String getReviewType() {
    return reviewType;
  }

  public void setReviewType(ReviewType reviewType) {
    this.reviewType = reviewType.toString();
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
