package java2.application_target_list.core.database;

import java2.application_target_list.core.domain.Target;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TargetListImplTest {

    Target firstTarget = new Target("Eat", "need to eat",1);
    Target secondTarget = new Target("Eat more", "need to eat more",5);
    Target thirdTarget = new Target("name", "description",5);
    TargetListImpl targetsDatabase = new TargetListImpl();


    @Test
    public void testSearchTargetByName_v3(){
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        targetsDatabase.addTarget(thirdTarget);
        List<Target> targetList = targetsDatabase.findByTargetName("name");
        Assert.assertEquals(targetList.size(), 1);
    }

    @Test
    public void testSearchTargetByName_v1(){
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        List<Target> targetList = targetsDatabase.findByTargetName("Eat");
        Assert.assertEquals(targetList.size(), 2);
    }

    @Test
    public void testSearchTargetByName_v2(){
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        List<Target> targetList = targetsDatabase.findByTargetName("name");
        Assert.assertEquals(targetList.size(), 0);
    }

    @Test
    public void testSearchTargetByDescription_v1(){
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        targetsDatabase.addTarget(thirdTarget);
        List<Target> targetList = targetsDatabase.findByTargetDescription("description");
        Assert.assertEquals(targetList.size(), 1);
    }

    @Test
    public void testSearchTargetByDescription_v2(){
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        List<Target> targetList = targetsDatabase.findByTargetDescription("need");
        Assert.assertEquals(targetList.size(), 2);
    }

    @Test
    public void testSearchTargetByDescription_v3(){
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        List<Target> targetList = targetsDatabase.findByTargetDescription("description");
        Assert.assertEquals(targetList.size(), 0);
    }

    @Test
    public void testAddTarget_list_size_v1() {
        targetsDatabase.addTarget(firstTarget);
        Assert.assertEquals(targetsDatabase.getTargetsList().size(), 1);
    }

    @Test
    public void testAddTarget_list_size_v2() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        Assert.assertEquals(targetsDatabase.getTargetsList().size(), 2);
    }

    @Test
    public void testAddTarget_targetId_v1() {
        targetsDatabase.addTarget(firstTarget);
        Long actual = firstTarget.getId();
        Long expected = 1L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddTarget_targetId_v2() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        Long actual = secondTarget.getId();
        Long expected = 2L;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testAddTarget_targetDeleteById_v1() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        targetsDatabase.deleteTarget(2L);
        Assert.assertEquals(targetsDatabase.getTargetsList().size(), 1);
    }

    @Test
    public void testAddTarget_targetDeleteById_v2() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        targetsDatabase.deleteTarget(1L);
        Assert.assertEquals(targetsDatabase.getTargetsList().size(), 1);
    }

    @Test
    public void testAddTarget_targetDeleteById_v3() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        targetsDatabase.deleteTarget(2L);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), firstTarget.getName());
    }

    @Test
    public void testAddTarget_targetDeleteById_v4() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        targetsDatabase.deleteTarget(1L);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), secondTarget.getName());
    }

    @Test
    public void testAddTarget_changeTargetName_v1() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        String newTargetName = "new name";
        targetsDatabase.changeTargetName(1L, newTargetName);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getName(), newTargetName);
    }

    @Test
    public void testAddTarget_changeTargetName_v2() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        String newTargetName = "new name";
        targetsDatabase.changeTargetName(2L, newTargetName);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(1).getName(), newTargetName);
    }

    @Test
    public void testAddTarget_changeTargetDescription_v1() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        String newTargetDescription = "new description";
        targetsDatabase.changeTargetDescription(1L, newTargetDescription);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getDescription(), newTargetDescription);
    }

    @Test
    public void testAddTarget_changeTargetDescription_v2() {
        targetsDatabase.addTarget(firstTarget);
        targetsDatabase.addTarget(secondTarget);
        String newTargetDescription = "new description";
        targetsDatabase.changeTargetDescription(2L, newTargetDescription);
        Assert.assertEquals(targetsDatabase.getTargetsList().get(1).getDescription(), newTargetDescription);
    }

//    @Test
//    public void testAddTarget_changeTargetDeadline_v1() {
//        targetsDatabase.addTarget(firstTarget);
//        targetsDatabase.addTarget(secondTarget);
//        int newTargetDeadline = 2;
//        targetsDatabase.changeTargetDeadline(1L, newTargetDeadline);
//        Assert.assertEquals(targetsDatabase.getTargetsList().get(0).getDeadline(), newTargetDeadline);
//    }

//    @Test
//    public void testAddTarget_changeTargetDeadline_v2() {
//        targetsDatabase.addTarget(firstTarget);
//        targetsDatabase.addTarget(secondTarget);
//        int newTargetDeadline = 2;
//        targetsDatabase.changeTargetDeadline(2L, newTargetDeadline);
//        Assert.assertEquals(java.util.Optional.ofNullable(targetsDatabase.getTargetsList().get(1).getDeadline()), newTargetDeadline);
//    }
}