package com.phonebook.tests;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnSignOutButton();
        }
    }

    @Test(priority = 1)
    public void loginPositiveTest() {
        //click on Login link
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegistrationForm(new User()
                .setEmail("kr@gmail.com")
                .setPassword("Kr1234567$"));
        //click on Registration button
        app.getUser().clickOnLoginButton();
        //assert Sign out button is displayed
        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test(priority = 2)
    public void loginNegativeWithoutPasswordTest() {
        //click on Login link
        app.getUser().clickOnLoginLink();
        //enter email
        app.getUser().fillLoginRegistrationForm(new User()
                .setEmail("kr@gmail.com"));
        //click on Registration button
        app.getUser().clickOnLoginButton();
        //assert Sign out button is displayed
        Assert.assertTrue(app.getUser().isAlertPresent());
    }

    @Test
    public void loginRegisteredUserPositiveTestWithScreenCast() throws IOException, AWTException {
        app.getUser().deleteScreenCast("record");
        app.getUser().startRecording();
        app.getUser().clickOnLoginLink();
        app.getUser().fillLoginRegistrationForm(new User()
                .setEmail("kr@gmail.com")
                .setPassword("Kr1234567$"));

        app.getUser().clickOnLoginButton();
        app.getUser().pause(2000);

        app.getUser().stopRecording();
    }


}
