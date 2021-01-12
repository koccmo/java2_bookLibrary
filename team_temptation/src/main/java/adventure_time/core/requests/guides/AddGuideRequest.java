package adventure_time.core.requests.guides;

public class AddGuideRequest {

    private String guideName;
    private String guideEmail;
    private String guidePhone;

    public AddGuideRequest(String guideName, String guideEmail, String guidePhone) {
        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.guidePhone = guidePhone;
    }
     public String getGuideName() {
        return guideName;
    }

      public String getGuideEmail() {
        return guideEmail;
    }

    public String getGuidePhone() {
        return guidePhone;
    }
}
