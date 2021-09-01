package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordReset() throws IOException, MessagingException {
        String username = "administrator";
        String password = "test12345";
        String newPassword = "test123456";
        app.adminHelper().login(username, password);
        app.adminHelper().goToUserMgmt();
        UserData selectedUser = app.db().getUsers().iterator().next();
        app.adminHelper().selectUser(selectedUser.getUsername());
        app.adminHelper().sendResetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 100000);
        String userEmail = selectedUser.getEmail();
        String resetLink = findResetLink(mailMessages, userEmail);
        app.adminHelper().resetPassword(resetLink, newPassword);
        Assert.assertTrue(app.newSession().login(selectedUser.getUsername(), newPassword));
    }

    private String findResetLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
