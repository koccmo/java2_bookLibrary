package java2.application_target_list.core.database;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.target.InMemoryTargetRepositoryImpl;
import java2.application_target_list.core.domain.Target;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InMemoryTargetRepositoryImplTest {

    private TargetRepository targetsDatabase;

    @BeforeEach
    public void setup() {
        targetsDatabase = new InMemoryTargetRepositoryImpl();
        Target firstTarget = new Target("Eat", "need to eat", 1L);
        Target secondTarget = new Target("Eat more", "need to eat more", 5L);
        Target thirdTarget = new Target("name", "description", 5L);
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        targetsDatabase.addTarget(thirdTarget);
    }

    @Test
    public void testSearchTargetByName_v3(){
        List<Target> targetList = targetsDatabase.findByTargetName("name");
        Assertions.assertEquals(targetList.size(), 1);
    }

    @Test
    public void testSearchTargetByName_v1(){
        List<Target> targetList = targetsDatabase.findByTargetName("Eat");
        Assertions.assertEquals(targetList.size(), 2);
    }

    @Test
    public void testSearchTargetByName_v2(){
        List<Target> targetList = targetsDatabase.findByTargetName("a");
        Assertions.assertEquals(targetList.size(), 3);
    }

    @Test
    public void testSearchTargetByDescription_v1(){
        List<Target> targetList = targetsDatabase.findByTargetDescription("description");
        Assertions.assertEquals(targetList.size(), 1);
    }

    @Test
    public void testSearchTargetByDescription_v2(){
        List<Target> targetList = targetsDatabase.findByTargetDescription("need");
        Assertions.assertEquals(targetList.size(), 2);
    }


    @Test
    public void testAddTarget_list_size() {
        Assertions.assertEquals(targetsDatabase.getTargetsList().size(), 3);
    }


    @Test
    public void testAddTarget_targetId_v1() {
        Long actual = targetsDatabase.getTargetsList().get(0).getId();
        Long expected = 1L;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testAddTarget_targetId_v2() {
        Long actual = targetsDatabase.getTargetsList().get(1).getId();
        Long expected = 2L;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testAddTarget_targetDeleteById_v1() {
        targetsDatabase.deleteTarget(2L);
        Assertions.assertEquals(targetsDatabase.getTargetsList().size(), 2);
        Assertions.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), "Eat");
        Assertions.assertEquals(targetsDatabase.getTargetsList().get(1).getName(), "name");
    }

    @Test
    public void testAddTarget_targetDeleteById_v2() {
        targetsDatabase.deleteTarget(1L);
        Assertions.assertEquals(targetsDatabase.getTargetsList().size(), 2);
        Assertions.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), "Eat more");
        Assertions.assertEquals(targetsDatabase.getTargetsList().get(1).getName(), "name");
    }


    @Test
    public void testAddTarget_changeTargetName_v1() {
        String newTargetName = "new name";
        targetsDatabase.changeTargetName(1L, newTargetName);
        Assertions.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), newTargetName);
    }

    @Test
    public void testAddTarget_changeTargetName_v2() {
        String newTargetName = "new name";
        targetsDatabase.changeTargetName(2L, newTargetName);
        Assertions.assertEquals(targetsDatabase.getTargetsList().get(1).getName(), newTargetName);
    }

    @Test
    public void testAddTarget_changeTargetDescription_v1() {
        String newTargetDescription = "new description";
        targetsDatabase.changeTargetDescription(1L, newTargetDescription);
        Assertions.assertEquals(targetsDatabase.getTargetsList().get(0).getDescription(), newTargetDescription);
    }

    @Test
    public void testAddTarget_changeTargetDescription_v2() {
        String newTargetDescription = "new description";
        targetsDatabase.changeTargetDescription(2L, newTargetDescription);
        Assertions.assertEquals(targetsDatabase.getTargetsList().get(1).getDescription(), newTargetDescription);
    }

    @Test
    public void testAddTarget_changeTargetDeadline_v1() {
        targetsDatabase.changeTargetDeadline(1L, 2L);
        Assertions.assertEquals(java.util.Optional.ofNullable(targetsDatabase.getTargetsList().get(0).getDeadline()), Optional.of(2L));
    }

    @Test
    public void testAddTarget_changeTargetDeadline_v2() {
        targetsDatabase.changeTargetDeadline(2L, 2L);
        Assertions.assertEquals(Optional.ofNullable(targetsDatabase.getTargetsList().get(1).getDeadline()), Optional.of(2L));
    }
}