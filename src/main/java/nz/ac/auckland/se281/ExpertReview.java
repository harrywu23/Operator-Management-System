package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ExpertReview extends Review {
  private boolean wouldRecommend;
  private String imageName;
  private ArrayList<String> imageList = new ArrayList<>();

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
    imageList.add(imageName); // add it straight away
  }

  public String getImageName() {
    return imageName;
  }

  public String getImagesAsString() {
    return String.join(",", imageList);
  }
}
