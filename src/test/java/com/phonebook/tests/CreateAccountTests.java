package com.phonebook.tests;

import com.phonebook.data.UserData;
import com.phonebook.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        if (!app.getUser().isLoginLinkPresent()) {
           app.getUser().clickOnSignOutButton();
        }
    }

    @Test
    public void createAccountPositiveTest() {

        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnRegistrationButton();

        Assert.assertTrue(app.getUser().isSignOutButtonPresent());
    }

    @Test
    public void createExistedAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();

        logger.info("Existed account data are " + UserData.EMAIL + " " + UserData.PASSWORD);

        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(new User()
                .setEmail(UserData.EMAIL)
                .setPassword(UserData.PASSWORD));
        app.getUser().clickOnRegistrationButton();

        softAssert.assertTrue(app.getUser().isAlertPresent());
        softAssert.assertTrue(app.getUser().isError409Present());
        softAssert.assertAll();

        app.getUser().clearLoginRegisterForm();

    }

}
