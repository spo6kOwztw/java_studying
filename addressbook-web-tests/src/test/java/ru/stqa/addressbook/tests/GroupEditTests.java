package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.addressbook.model.GroupData;

public class GroupEditTests extends TestBase {

    @Test
    public void testGroupEdit() {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupEdit();
        app.getGroupHelper().fillGroupForm(new GroupData("New1", "header1", "footer1"));
        app.getGroupHelper().submitGroupEdit();

    }

}
