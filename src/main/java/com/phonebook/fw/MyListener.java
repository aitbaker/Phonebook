package com.ebay.fw;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Random;

public class MyListener implements WebDriverListener {

    Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        WebDriverListener.super.onError(target, method, args, e);
        logger.info("The test have a problem");
        logger.info("***************************************************************");

        logger.info("Method --> " + method.getName());
        logger.info("***************************************************************");

        logger.info("Target exception --> " + e.getTargetException());
        logger.info("***************************************************************");

        logger.info("Object target --> " + target.toString());
        logger.info("***************************************************************");

        WebDriver driver = (ChromeDriver)target;

        int i = new Random().nextInt(1000);
        String link = "screenshots/screen-" + i + ".png";
        logger.info("Screenshot with error --> " + link);

        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElement(driver, locator);

        logger.info("Before find element --> " + locator);
        logger.info("***************************************************************");
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
        logger.info("After find element --> " + locator);
        logger.info("***************************************************************");
    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElements(driver, locator);

        logger.info("Before find elements --> " + locator);
        logger.info("***************************************************************");
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        WebDriverListener.super.afterFindElements(driver, locator, result);

        logger.info("List size is " + result.size());
    }
}
