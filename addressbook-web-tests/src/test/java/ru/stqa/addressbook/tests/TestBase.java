package ru.stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import ru.stqa.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    public void tearDown() throws Exception {
        app.stop();
    }

}
