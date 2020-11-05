package dental_clinic.domain;

import java.util.*;

public class ToothInfo {

    private List <Optional <String>> comment = new ArrayList<>();
    private List<ToothStatus> status;

    public ToothInfo (Optional<String> comment, ToothStatus...status){
        this.comment.add(comment);
        this.status = new ArrayList<>(Arrays.asList(status));
    }

    public List <Optional <String>> getComment() {
        return comment;
    }

    public void addComment(Optional <String> comment) {
        this.comment.add(comment);
    }

    public List<ToothStatus> getStatus() {
        return status;
    }

    public void addStatus(ToothStatus status) {
        this.status.add(status);
    }

    @Override
    public String toString() {
        String result = "ToothInfo{ ";
        for (int i = 0; i < comment.size(); i++){
            if (comment.get(i).isPresent()){
                result += " " + comment.get(i) + " ";
            }
            result += "status=" + status +'}';
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToothInfo toothInfo = (ToothInfo) o;
        return Objects.equals(comment, toothInfo.comment) &&
                Objects.equals(status, toothInfo.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, status);
    }
}
