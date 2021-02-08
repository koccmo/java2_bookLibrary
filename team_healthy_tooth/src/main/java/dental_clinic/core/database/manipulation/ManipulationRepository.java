package dental_clinic.core.database.manipulation;

import dental_clinic.core.domain.Manipulation;

import java.util.List;

public interface ManipulationRepository {

    void addManipulation(Manipulation manipulation);

    List <Manipulation> getManipulationsList();

    Manipulation getManipulationById (Long id);

    void deactivateManipulation(Long id);

    boolean containsTheSameManipulation (Manipulation manipulation);

    boolean manipulationIsActive(Long id);

    boolean containsId (Long id);

}
