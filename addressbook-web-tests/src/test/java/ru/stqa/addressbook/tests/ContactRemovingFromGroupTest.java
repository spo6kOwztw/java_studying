package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemovingFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("new1"));
        }
        Groups groups = app.db().groups();
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData()
                    .withFirstName("william")
                    .withLastName("Burroughs")
                    .withMiddleName("X")
                    .withMobilePhone("0")
                    .withEmail1("0")
                    .withGroup(groups.iterator().next()))
            ;
        }
        app.goTo().homePage();
    }

    @Test
    public void testRemoveContactFromGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData contact = contacts.iterator().next();
        GroupData group = groups.stream().iterator().next();
        int contactId = contact.getId();
        Groups contactGroupsBefore = contact.groups();

        if (contact.groups().size() == 0) {
            app.contact().selectContactById(contactId);
            app.contact().addContactToGroup(contact, group);
            app.goTo().homePage();
            Contacts after = app.db().contacts();
            contact = after.iterator().next().withId(contactId);
            contactGroupsBefore = contact.groups();

        }

        GroupData removedGroup = contactGroupsBefore.iterator().next();

        app.contact().removeContactFromGroup(contact,removedGroup);
        app.goTo().homePage();

        Contacts after = app.db().contacts();
        ContactData newContactGroups = after.iterator().next().withId(contact.getId());
        assertThat(newContactGroups, equalTo(contact.groups().without(removedGroup)));
    }
}
