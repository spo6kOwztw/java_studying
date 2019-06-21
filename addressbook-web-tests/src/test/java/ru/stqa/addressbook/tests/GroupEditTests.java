package ru.stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.*;

public class GroupEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size()==0) {
            app.group().create(new GroupData().withName("name"));
        }

    }

    @Test
    public void testGroupEdit() {
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        GroupData group = new GroupData()
                .withId(before.get(index).getId()).withName("name").withHeader("head").withFooter("foot");
        app.group().edit(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}
