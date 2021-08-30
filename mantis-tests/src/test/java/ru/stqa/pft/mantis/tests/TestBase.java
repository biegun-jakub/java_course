package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import javax.xml.rpc.ServiceException;


public class TestBase {

    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File
                ("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak" );
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(BigInteger issueId) throws RemoteException, ServiceException, MalformedURLException {
        IssueData issue = app.soap().getIssue(issueId);
        ObjectRef status = issue.getStatus();
        if (String.valueOf(status) == "resolved") {
            return false;
        } else {
            return true;
        }
    }

    public void skipIfNotFixed(BigInteger issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
