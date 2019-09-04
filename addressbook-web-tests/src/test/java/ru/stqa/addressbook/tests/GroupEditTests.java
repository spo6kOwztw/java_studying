package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupEditTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("name"));
        }

    }

    @Test
    public void testGroupEdit() {
        Groups before = app.db().groups();
        GroupData editedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(editedGroup.getId()).withName("name").withHeader("head").withFooter("foot");
        app.goTo().groupPage();
        app.group().edit(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(editedGroup).withAdded(group)));
        verifyGroupListInUI();



    }

}
