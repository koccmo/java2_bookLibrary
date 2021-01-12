package adventure_time.core.domain;

import java.util.Objects;

public class Guides {

    private long guideID;
    private String guideName;
    private String guideEmail;
    private String guidePhone;
    private boolean activity;

    public Guides(String guideName, String guideEmail, String guidePhone) {

        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.guidePhone = guidePhone;
        this.activity = true;
    }

    public long getGuideID() {
        return guideID;
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

    public void setGuideID(long guideID) {
        this.guideID = guideID;
    }

    public boolean isActivity() {
        return activity;
    }

    public void activityOn(boolean activity) {
        this.activity = true;
    }
    public void activityOff(boolean activity) {
        this.activity = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guides guides = (Guides) o;
        return guideID == guides.guideID && activity == guides.activity && Objects.equals(guideName, guides.guideName) && Objects.equals(guideEmail, guides.guideEmail) && Objects.equals(guidePhone, guides.guidePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guideID, guideName, guideEmail, guidePhone, activity);
    }

    @Override
    public String toString() {
        return "Guides{" +
                "guideID=" + guideID +
                ", guideName='" + guideName + '\'' +
                ", guideEmail='" + guideEmail + '\'' +
                ", guidePhone='" + guidePhone + '\'' +
                ", activity=" + activity +
                '}';
    }
}