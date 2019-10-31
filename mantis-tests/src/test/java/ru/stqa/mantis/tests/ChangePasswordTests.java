package ru.stqa.mantis.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.mantis.model.MailMessage;
import ru.stqa.mantis.model.UserData;
import ru.stqa.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws MessagingException, IOException {
        String jamesUser = "administrator";
        String jamesPassword = "root";
        app.james().createUser(jamesUser, jamesPassword);

        app.goTo().loginPage();
        app.login().user(app.getProperty("web.adminLogin"));
        app.login().password(app.getProperty("web.adminPassword"));
        app.login().submitLogin();
        app.goTo().managePage();
        app.goTo().userManager();

        Users users = app.db().users();
        UserData user = users.iterator().next();
        app.user().initModificationById(user.getId());
        app.user().resetPassword();
        //app.log().out();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 50000);
        String resetPasswordLink = findResetPasswordLink(mailMessages, user.getEmail());
        String newPassword = "paassword";
        app.user().changePassword(resetPasswordLink, newPassword);
        assertTrue(app.newSession().login(user.getUsername(), newPassword));

    }

    private String findResetPasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

}