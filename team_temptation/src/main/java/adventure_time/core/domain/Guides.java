package adventure_time.core.domain;

import java.util.Objects;

public class Guides {

    private long guideID;
    private String guideName;
    private String guideEmail;
    private String guidePhone;
    private int guideRating;

    public Guides(long guideID, String guideName, String guideEmail, String guidePhone, int guideRating) {
        this.guideID = guideID;
        this.guideName = guideName;
        this.guideEmail = guideEmail;
        this.guidePhone = guidePhone;
        this.guideRating = guideRating;
    }

    public long getguideID() {
        return guideID;
    }

    public void setguideID(long guideID) {
        this.guideID = guideID;
    }

    public String getguideName() {
        return guideName;
    }

    public void setguideName(String guideName) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guides guides = (Guides) o;
        return guideID == guides.guideID &&
                guideRating == guides.guideRating &&
                Objects.equals(guideName, guides.guideName) &&
                Objects.equals(guideEmail, guides.guideEmail) &&
                Objects.equals(guidePhone, guides.guidePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guideID, guideName, guideEmail, guidePhone, guideRating);
    }

    @Override
    public String toString() {
        return "Guide{" +
                "guideID=" + guideID +
                ", guideName='" + guideName + '\'' +
                ", guideEmail='" + guideEmail + '\'' +
                ", guidePhone='" + guidePhone + '\'' +
                ", guideRating=" + guideRating +
                '}';
    }
}