package dental_clinic.database.in_memory.manipulation;

import dental_clinic.core.domain.Manipulation;

import java.util.List;

public interface ManipulationInMemoryDatabase {

    void addManipulation(Manipulation manipulation);

    List <Manipulation> getManipulationsList();

    void deactivateManipulation(Long id);

    boolean containsTheSameManipulation (Manipulation manipulation);

    boolean containsId (Long id);

}
