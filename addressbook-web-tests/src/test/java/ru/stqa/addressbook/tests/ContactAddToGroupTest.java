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

        app.goTo().homePage();
    }

    @Test
    public void testContactAddToGroup() {

        app.goTo().homePage();
        Groups groups = app.db().groups();
        GroupData group = groups.iterator().next();
        Contacts contactBefore = app.db().contacts();
        ContactData addedContact = contactToAdd(contactBefore);
        app.goTo().homePage();
        app.contact().addContactToGroup(group, addedContact);
        assertThat(addedContact.getGroups().withAdded(group), equalTo(app.db().contactId(addedContact.getId()).getGroups()));
        verifyContactListInUI();
    }

    private ContactData contactToAdd(Contacts before) {
        for (ContactData contact : before) {
            if (contact.getGroups().size() == 0) {
                return contact;
            }
        }
        app.contact().create(new ContactData()
                .withFirstName("william")
                .withLastName("Burroughs")
                .withMiddleName("X")
                .withMobilePhone("0")
                .withEmail1("0"));
        app.goTo().homePage();
        Contacts contactsToAdd = app.db().contacts();
        return contactsToAdd.iterator().next();

    }
}
