package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        //  System.out.println("Before Method");
        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test(priority = 1)
    public void loginPositiveTest() {

        logger.info("Login with data --> " + UserData.EMAIL + " *** " + UserData.PASSWORD);

        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test(priority = 2)
    public void loginNegativeWithoutEmailTest() {
        app.getUser().clickOnLoginLink();

        app.getUser().fillRegisterLoginForm(new User()
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();

        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test(priority = 1)
    public void loginPositiveTestWithScreenCast() throws IOException, AWTException {

        app.getUser().startRecording();

        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnLoginButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
        app.getUser().pause(3000);
        app.getUser().stopRecording();
    }

}

//    @BeforeClass
//    public void beforeClassPrecondition() {
//        System.out.println("Before Class");
//    }
//
//    @BeforeTest
//    public void beforeTestPrecondition() {
//        System.out.println("Before Test");
//    }
//
//    @AfterClass
//    public void afterClassPostCondition() {
//        System.out.println("After Class");
//    }
//
//    @AfterClass
//    public void afterTestPostCondition() {
//        System.out.println("After Test");
//    }
