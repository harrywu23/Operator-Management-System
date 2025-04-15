package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ReviewType;

public abstract class Review {
  private String reviewId;
  private String rating;
  private String comment;
  private String reviewerName;
  private String reviewType;

  public Review(String reviewerName, String rating, String comment) {
    this.reviewerName = reviewerName;
    this.rating = rating;
    this.comment = comment;

    int numericRating = -1;

    // Check if rating is a valid number
    if (rating != null) {
      numericRating = Integer.parseInt(rating);
      if (numericRating < 1) {
        numericRating = 1;
      } else if (numericRating > 5) {
        numericRating = 5;
      }
    }
    this.rating = String.valueOf(numericRating);
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
}
