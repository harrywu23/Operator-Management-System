package nz.ac.auckland.se281;

public class ExpertReview extends Review {
  private boolean wouldRecommend;
  private String imageName;

  public ExpertReview(String reviewerName, String rating, String comment, boolean wouldRecommend) {
    super(reviewerName, rating, comment);
    this.wouldRecommend = wouldRecommend;
    this.imageName = null;
  }

  public boolean isRecommended() {
    return wouldRecommend;
  }

  public void uploadImage(String imageName) {
    this.imageName = imageName;
  }

  public String getImageName() {
    return imageName;
  }
}
