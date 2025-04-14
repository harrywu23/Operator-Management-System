package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private boolean endorsed;

  public PublicReview(String reviewerName, boolean isAnonymous, String rating, String comment) {
    super(reviewerName, isAnonymous, rating, comment);
    this.endorsed = false;
  }

  public boolean endorseReview() {
    this.endorsed = true;
    return this.endorsed;
  }

  public boolean isEndorsed() {
    return endorsed;
  }
}
