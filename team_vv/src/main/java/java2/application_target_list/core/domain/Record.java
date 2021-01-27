package java2.application_target_list.core.domain;

import javax.persistence.*;

@Entity
@Table(name="targets_board")
public class Record {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @Column(name="target_id", nullable = false)
    private Long targetId;

    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="target_added_date", columnDefinition = "DATETIME",  nullable = false)
    private String dateAdded;

    @Column(name="target_date_of_completion", columnDefinition = "DATETIME")
    private String dateComplete;

//    @JoinColumn()
//    private String targetName;
//
//    @Column(name="target_description", nullable = false)
//    private String targetDescription;
//
//    @Column(name="target_deadline", nullable = false)
//    private Integer targetDeadline;
//
//    @Column(name="user_first_name", nullable = false)
//    private String userFirstName;
//
//    @Column(name="user_last_name", nullable = false)
//    private String userLastName;


    public Record() {
    }

    public Record(Long targetId, Long userId) {
        this.targetId = targetId;
        this.userId = userId;
    }

    public Record(Long targetId, Long userId, String dateAdded) {
        this.targetId = targetId;
        this.userId = userId;
        this.dateAdded = dateAdded;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(String dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getDateAdded() {
        return dateAdded;
    }

//    public String getTargetName() {
//        return targetName;
//    }
//
//    public void setTargetName(String targetName) {
//        this.targetName = targetName;
//    }
//
//    public String getTargetDescription() {
//        return targetDescription;
//    }
//
//    public void setTargetDescription(String targetDescription) {
//        this.targetDescription = targetDescription;
//    }
//
//    public Integer getTargetDeadline() {
//        return targetDeadline;
//    }
//
//    public void setTargetDeadline(Integer targetDeadline) {
//        this.targetDeadline = targetDeadline;
//    }
//
//    public String getUserFirstName() {
//        return userFirstName;
//    }
//
//    public void setUserFirstName(String userFirstName) {
//        this.userFirstName = userFirstName;
//    }
//
//    public String getUserLastName() {
//        return userLastName;
//    }
//
//    public void setUserLastName(String userLastName) {
//        this.userLastName = userLastName;
//    }
}

