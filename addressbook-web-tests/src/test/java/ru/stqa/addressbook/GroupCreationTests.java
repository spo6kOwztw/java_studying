package ru.stqa.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupCreationTests {
  private WebDriver wc;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wc = new FirefoxDriver();
    wc.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testGroupCreation() throws Exception {
    wc.get("http://localhost/addressbook/");
    wc.findElement(By.name("user")).click();
    wc.findElement(By.name("user")).clear();
    wc.findElement(By.name("user")).sendKeys("admin");
    wc.findElement(By.name("pass")).clear();
    wc.findElement(By.name("pass")).sendKeys("secret");
    wc.findElement(By.xpath("//input[@value='Login']")).click();
    wc.findElement(By.linkText("groups")).click();
    wc.findElement(By.name("new")).click();
    wc.findElement(By.name("group_name")).click();
    wc.findElement(By.name("group_name")).clear();
    wc.findElement(By.name("group_name")).sendKeys("New");
    wc.findElement(By.name("group_header")).click();
    wc.findElement(By.name("group_header")).clear();
    wc.findElement(By.name("group_header")).sendKeys("header");
    wc.findElement(By.name("group_footer")).clear();
    wc.findElement(By.name("group_footer")).sendKeys("footer");
    wc.findElement(By.name("submit")).click();
    wc.findElement(By.linkText("group page")).click();
    wc.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wc.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wc.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wc.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

}
