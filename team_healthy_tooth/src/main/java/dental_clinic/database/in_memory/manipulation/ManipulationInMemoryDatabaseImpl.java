package dental_clinic.database.in_memory.manipulation;

import dental_clinic.core.domain.Manipulation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ManipulationInMemoryDatabaseImpl implements ManipulationInMemoryDatabase{

    private List<Manipulation> manipulations = new ArrayList<>();
    private Long id = 1L;


    @Override
    public void addManipulation(Manipulation manipulation) {
        manipulation.setId(id);
        id++;
        manipulations.add(manipulation);
    }

    @Override
    public List<Manipulation> getManipulationsList() {
        return manipulations;
    }

    @Override
    public void deactivateManipulation(Long id) {
        for (Manipulation manipulation : manipulations) {
            manipulation.setIsActive(false);
        }
    }

    @Override
    public boolean containsTheSameManipulation(Manipulation manipulation) {
        return manipulations.stream()
                .anyMatch(manipulation1 -> manipulation1.equals(manipulation));
    }

    @Override
    public boolean containsId(Long id) {
        return manipulations.stream()
                .anyMatch(manipulation -> manipulation.getId().equals(id));
    }
}
