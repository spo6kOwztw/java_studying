package ru.stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.Contacts;

import java.util.List;


public class ContactHelper extends BaseHelper {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contact, boolean creation) {
        type(By.name("firstname"), contact.getFirstName());
        type(By.name("middlename"), contact.getMiddleName());
        type(By.name("lastname"), contact.getLastName());
        type(By.name("mobile"), contact.getMobilePhone());
        type(By.name("email"), contact.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int id) {
        wd.findElement(By.id(Integer.toString(id))).click();    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void initContactEdit() {
        click(By.xpath("//img[@title='Edit']"));
    }

    public void submitContactEdit() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();

    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tbody tr[name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> attributes = element.findElements(By.tagName("td"));
            String firstname = attributes.get(2).getText();
            //String middlename = attributes.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastname = attributes.get(1).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteSelectedContact();
        submitContactDeletion();
    }
    public void edit(ContactData contact) {
        selectContact(contact.getId());
        initContactEdit();
        fillContactForm(contact, false);
        submitContactEdit();
    }


}