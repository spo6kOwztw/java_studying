package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;
import ru.stqa.addressbook.model.Groups;

<<<<<<< Updated upstream
=======
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

=======
        Contacts contacts = app.db().contacts();
        ContactData removedContact = contacts.iterator().next();
        int contactId = removedContact.getId();
        Groups contactGroupsBefore = removedContact.groups();
        if (contactGroupsBefore.size() == 0) {
            Groups groups = app.db().groups();
            GroupData group = groups.stream().iterator().next();
            app.goTo().homePage();
            app.contact().addContactToGroup(removedContact, group);
            app.db().contacts();
        }

        contactGroupsBefore = removedContact.groups();
        GroupData group = contactGroupsBefore.iterator().next();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(removedContact, group);
        Contacts after = app.db().contacts();
        ContactData contactAfter = after.stream().filter(data -> Objects.equals(data.getId(), contactId)).findFirst().get();
        Groups contactGroupsAfter = contactAfter.groups();
        assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(group)));
        verifyContactListInUI();
>>>>>>> Stashed changes

    }
}
