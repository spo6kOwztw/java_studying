package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData()
                        .withName("new1")
                        .withHeader("test")
                        .withFooter("test"));
                app.goTo().homePage();
            }
            app.contact().create(new ContactData()
                    .withFirstName("william")
                    .withLastName("Burroughs")
                    .withMiddleName("X")
                    .withMobilePhone("0")
                    .withEmail1("0"));
            app.goTo().homePage();
        }
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contactData : contacts) {
            if (contactData.getGroups().size() != groups.size()) {
                app.contact().create(new ContactData()
                        .withFirstName("william")
                        .withLastName("Burroughs")
                        .withMiddleName("X")
                        .withMobilePhone("0")
                        .withEmail1("0"));
            }
            return;
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactAddToGroup() {

        app.goTo().homePage();

        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();

        ContactData contact = contacts.iterator().next();
        int contactId = contact.getId();
        Groups contactGroupsBefore = contact.groups();

        if (groups.size() == 0) {
            contact = contacts.iterator().next();
        }
        app.goTo().homePage();

        GroupData groupForAdd = groups.iterator().next();
        app.goTo().homePage();

        app.contact().addContactToGroup(groupForAdd, contact);

        Contacts after = app.db().contacts();
        ContactData contactAfter = after.stream().filter(data -> Objects.equals(data.getId(), contactId)).findFirst().get();
        Groups contactGroupsAfter = contactAfter.groups();
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.withAdded(groupForAdd)));
        verifyContactListInUI();


    }
}