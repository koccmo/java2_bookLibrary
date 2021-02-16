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

    @ManyToOne
    @JoinColumn(name = "target_id" , nullable = false, insertable = false, updatable = false)
    private Target target;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

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

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}

