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
        type(By.name("lastname"), contact.getLastName());

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
        wd.findElement(By.id(Integer.toString(id))).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void submitContactDeletion() {

        wd.switchTo().alert().accept();
        wd.findElement(By.cssSelector("div.msgbox"));
    }

    public void initContactEdit(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
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

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tbody tr[name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> attributes = element.findElements(By.tagName("td"));
            String lastname = attributes.get(1).getText();
            String firstname = attributes.get(2).getText();
            //String middlename = attributes.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contacts;
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        contactCache = null;
        submitContactCreation();
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        submitContactDeletion();
    }
    public void edit(ContactData contact) {
        initContactEdit(contact.getId());
        fillContactForm(contact, false);
        contactCache = null;
        submitContactEdit();
    }


    public ContactData infoFormEditForm(ContactData contact) {
        initContactEditById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstName(firstname)
                .withLastName(lastname)
                .withMobilePhone(mobile)
                .withHomePhone(home)
                .withWorkPhone(work);
    }

    private void initContactEditById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value = '%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

    }
}