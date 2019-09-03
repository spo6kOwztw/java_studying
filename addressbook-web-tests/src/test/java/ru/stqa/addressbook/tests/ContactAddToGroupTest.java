package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

    @BeforeMethod
    //   1. Для всех тестов: проверки предусловий наличия групп и контактов.
    public void ensurePreconditions() {

        app.goTo().homePage();

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("new1"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData()
                    .withFirstName("william")
                    .withLastName("Burroughs")
                    .withMiddleName("X")
                    .withMobilePhone("0")
                    .withEmail1("0"));
                    }
        app.goTo().homePage();
    }

    @Test
    public void testContactAddToGroup() {
        app.goTo().homePage();
        Contacts contactBefore = app.db().contacts();
        ContactData addedContact = contactBefore.iterator().next();
        app.contact().addContactToGroup(addedContact);

        Contacts contactAfter =
        Groups groupsAfter =

        assertThat(groupsAfter, equalTo(groupsBefore.withAdded());

    }

}