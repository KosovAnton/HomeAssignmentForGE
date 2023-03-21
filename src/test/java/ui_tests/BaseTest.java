package ui_tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;

import static com.codeborne.selenide.Selenide.closeWindow;

public class BaseTest {
    @BeforeSuite
    public void beforeSuiteMethod() {
        clearScreenshots();
    }

    @BeforeMethod
    public void beforeTestMethod() {
        Selenide.open();
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void afterTestMethod() {
        closeWindow();
    }

    public void clearScreenshots() {
        String pathname = "src/test/resources/screenshots";

        File screenshotDir = new File(pathname);
        if (screenshotDir.exists() && screenshotDir.isDirectory()) {
            screenshotDir.delete();
        }

        new File(pathname).mkdirs();
    }
}
