package application_target_list.core.database;

import java.util.Objects;

public class Target {

    private String name;
    private String description;
    private Long id;
    private Integer deadline;


    public Target(String name, String description, Integer deadline) {
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

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Integer getDeadline() {
        return deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Target target = (Target) o;
        return deadline.equals(target.deadline) &&
                Objects.equals(name, target.name) &&
                Objects.equals(description, target.description) &&
                Objects.equals(id, target.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, deadline);
    }
}
