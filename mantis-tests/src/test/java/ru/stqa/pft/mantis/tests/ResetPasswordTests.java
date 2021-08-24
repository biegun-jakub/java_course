package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordReset() throws IOException, MessagingException {
        String username = "administrator";
        String password = "newpassword";
        String email = "user111@localhost.localdomain";
        String newPassword = "newpassword";
        app.adminHelper().login(username, password);
        app.adminHelper().goToUserMgmt();
        app.adminHelper().selectUser();
        String user = app.adminHelper().getUser();
        app.adminHelper().sendResetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 100000);
        String resetLink = findResetLink(mailMessages, email);
        app.adminHelper().resetPassword(resetLink, newPassword);
        Assert.assertTrue(app.newSession().login(user, newPassword));
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
