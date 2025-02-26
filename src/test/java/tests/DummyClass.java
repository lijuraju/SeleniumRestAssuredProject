package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DummyClass extends BaseClass {

    @Test
    public void dummyTest(){

       String title =  driver.getTitle();
        Assert.assertEquals("OrangeHRM",title);
        System.out.println("Title is: "+title);

    }
}
