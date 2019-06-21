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
        if (app.group().all().size()==0) {
            app.group().create(new GroupData().withName("name"));
        }

    }

    @Test
    public void testGroupEdit() {
        Set<GroupData> before = app.group().all();
        GroupData editedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(editedGroup.getId()).withName("name").withHeader("head").withFooter("foot");
        app.group().edit(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(editedGroup);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}
