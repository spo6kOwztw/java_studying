package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().createContact(new ContactData()
                    .withFirstName("william")
                    .withLastName("Burroughs")
                    .withGroup("name")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333")
                    .withEmail1("email1")
                    .withEmail2("email2")
                    .withEmail3("email3")
                    .withAddress("test"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactPhone() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFormEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).filter(s->!s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));

    }
    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail1(), contact.getEmail2(), contact.getEmail3()).filter(s->!s.equals(""))
                .collect(Collectors.joining("\n"));

    }

        public static String cleaned(String phone) {
            return phone.replaceAll("[-()\\s]", "");
        }


    }



