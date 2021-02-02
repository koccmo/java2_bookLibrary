package dental_clinic.core.database.manipulation;

import dental_clinic.core.domain.Manipulation;

import java.util.ArrayList;
import java.util.List;
/*
//@Component
public class InMemoryManipulationRepositoryImpl implements ManipulationRepository {

    private List<Manipulation> manipulations = createSomeManipulations();
    private Long id = 4L;


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
            manipulation.setActive(false);
        }
    }

    @Override
    public boolean containsTheSameManipulation(Manipulation manipulation) {
        return manipulations.stream()
                .anyMatch(manipulation1 -> manipulation1.equals(manipulation));
    }

    @Override
    public boolean manipulationIsActive(Long id) {
        return manipulations.stream()
                .filter(manipulation -> manipulation.getId().equals(id))
                .anyMatch(manipulation -> manipulation.isActive());
    }

    @Override
    public boolean containsId(Long id) {
        return manipulations.stream()
                .anyMatch(manipulation -> manipulation.getId().equals(id));
    }

    private List<Manipulation> createSomeManipulations() {
        Manipulation manipulation1 = new Manipulation("Zoba izraušana", 5);
        manipulation1.setId(1L);
        Manipulation manipulation2 = new Manipulation("Morālais atbalsts", 55);
        manipulation2.setId(2L);
        Manipulation manipulation3 = new Manipulation("Universāla arstēšana", 500);
        manipulation3.setId(3L);
        List<Manipulation>manipulations = new ArrayList<>();
        manipulations.add(manipulation1);
        manipulations.add(manipulation2);
        manipulations.add(manipulation3);
        return manipulations;
    }
}*/
