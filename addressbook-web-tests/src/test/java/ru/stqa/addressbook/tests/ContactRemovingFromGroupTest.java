package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemovingFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {

            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData()
                        .withName("new1"));
                app.goTo().homePage();
            }
            app.contact().create(new ContactData()
                    .withFirstName("william")
                    .withLastName("Burroughs")
                    .withMiddleName("X")
                    .withMobilePhone("0")
                    .withEmail1("0"));
        }
            app.goTo().homePage();
        }


    @Test
    public void testRemoveContactFromGroup() {

        app.goTo().homePage();
        Contacts contactBefore = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData group = app.db().groupId(contactForRemoving(contactBefore, groups).getId());
        app.goTo().homePage();
        app.contact().selectGroupToRemove(group);
        Contacts contactForRemoving = group.getContacts();
        ContactData contactsToRemove = contactForRemoving.iterator().next();
        app.contact().removeContactFromGroup(contactsToRemove, group);
        Contacts removedContacts = app.db().groupId(group.getId()).getContacts();
        assertThat(removedContacts, equalTo(contactForRemoving.without(contactsToRemove)));
        verifyContactListInUI();

    }

    private GroupData contactForRemoving(Contacts contacts, Groups groups) {
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() > 0) {
                Groups groupsWithContacts =  contact.getGroups();
                return groupsWithContacts.iterator().next();
            }
        }
        ContactData addedContact = contacts.iterator().next();
        GroupData group = groups.iterator().next();
        app.goTo().homePage();
        app.contact().addContactToGroup(group, addedContact);
        return group;
    }
}