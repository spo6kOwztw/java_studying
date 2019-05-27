package ru.stqa.addressbook;

import org.testng.annotations.Test;


public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("New", "header", "footer"));
        submitGroupCreation();
        returnToGroupPage();
    }


}
