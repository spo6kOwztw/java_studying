package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

public class ContactRemovingFromGroupTest extends TestBase{

    @BeforeMethod
    //   1. Для всех тестов: проверки предусловий наличия групп и контактов.

    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("name"));
                app.goTo().homePage();
            }
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Bill").withLastName("B").withGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testRemoveContactFromGroup() {


    }
}
