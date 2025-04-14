package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private boolean endorsed;
  private boolean isAnonymous;

  public PublicReview(String reviewerName, boolean isAnonymous, String rating, String comment) {
    super(reviewerName, rating, comment);
    this.endorsed = false;
    this.isAnonymous = isAnonymous;
  }

  public boolean isAnonymous() {
    return this.isAnonymous;
  }

  public boolean endorseReview() {
    this.endorsed = true;
    return this.endorsed;
  }

  public boolean isEndorsed() {
    return endorsed;
  }
}
