package application_target_list;

import java.util.Objects;

public class Target {

    private String name;
    private String description;
    private Long id;
    private int deadline;


    public Target(String name, String description, int deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getDeadline() {
        return deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return deadline == target.deadline &&
                Objects.equals(name, target.name) &&
                Objects.equals(description, target.description) &&
                Objects.equals(id, target.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, deadline);
    }
}
