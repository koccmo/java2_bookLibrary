package adventure_time.core.requests.guides;

public class AddGuideRequest {

    private long guideID;
    private String guideName;
    private String guideEmail;
    private String guidePhone;
    private int guideRating;

    public AddGuideRequest(long guideID, String guideName, String guideEmail, String guidePhone, int guideRating) {
        this.guideID = guideID;
        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.guidePhone = guidePhone;
        this.guideRating = guideRating;
    }

    public long getGuideID() {
        return guideID;
    }

    public void setGuideID(long guideID) {
        this.guideID = guideID;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getGuideEmail() {
        return guideEmail;
    }

    public void setGuideEmail(String guideEmail) {
        this.guideEmail = guideEmail;
    }

    public String getGuidePhone() {
        return guidePhone;
    }

    public void setGuidePhone(String guidePhone) {
        this.guidePhone = guidePhone;
    }

    public int getGuideRating() {
        return guideRating;
    }

    public void setGuideRating(int guideRating) {
        this.guideRating = guideRating;
    }
}
