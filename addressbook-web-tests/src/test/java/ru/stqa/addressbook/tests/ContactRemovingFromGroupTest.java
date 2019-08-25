package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemovingFromGroupTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("name"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().homePage();
            app.contact().createContact(new ContactData()
                    .withFirstName("william")
                    .withLastName("Burroughs")
                    .withMiddleName("X")
                    .withMobilePhone("0")
                    .withEmail1("0"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        GroupData currentGroup = app.db().groups().iterator().next();
        app.contact().goTo(currentGroup);
        ContactData removedContact = app.db().contacts().iterator().next();
        Integer contactId = removedContact.getId();
        Groups groups = removedContact.groups();
        if (groups.size() > 0) {
            app.contact().removeContactFromGroup(removedContact);
        }
        ContactData updatedContact = app.db().contacts().iterator().next().withId(contactId);
        Groups updatedGroups = updatedContact.groups();
        assertThat(updatedGroups, equalTo(groups.without(currentGroup)));
    }
}
