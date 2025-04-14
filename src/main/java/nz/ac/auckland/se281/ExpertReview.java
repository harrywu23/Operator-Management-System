package nz.ac.auckland.se281;

public class ExpertReview extends Review {
  private boolean wouldRecommend;

  public ExpertReview(String reviewerName, String rating, String comment, boolean wouldRecommend) {
    super(reviewerName, rating, comment);
    this.wouldRecommend = wouldRecommend;
  }

  public boolean isRecommended() {
    return wouldRecommend;
  }
}
