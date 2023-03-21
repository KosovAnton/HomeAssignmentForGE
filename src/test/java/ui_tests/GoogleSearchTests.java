package ui_tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GoogleSearchTests extends BaseTest {
    @Test
    public void searchJavaAndValidateResultsTest() {
        //b. Go to https://www.google.co.il/
        open("https://www.google.co.il/?hl=en-US");

        SelenideElement acceptCookiesDialog = $("#CXQnmb").as("accept cookies dialog popup").shouldBe(visible);

        acceptCookiesDialog.$("#L2AGLb").as("'accept all' button")
                .shouldBe(visible)
                .click();
        acceptCookiesDialog.$("#CXQnmb").should(disappear);

        //c. Type “Java” into search field
        $("input[name=q]").shouldBe(visible).setValue("Java");

        //d. Press on “Google Search” button
        $$("center input").shouldHave(sizeGreaterThanOrEqual(2)).filter(visible)
                .find(attribute("value", "Google Search")).click();

        ElementsCollection searchResults = $("div #rso").shouldBe(visible)
                .$$x("./div").shouldHave(sizeGreaterThan(0));

        List<String> links = searchResults.asFixedIterable().stream()
                .map(element -> element.$$x(".//cite").get(0).text())
                .filter(s -> s.contains("http"))
                .map(s -> s.split(" ")[0])
                .collect(Collectors.toList());

        //e. Print only search result links(without internal references* see pic. below)
        links.forEach(System.out::println);

        //f. Verify first search result contains the word “Java
        searchResults.first().shouldHave(text("Java"));
        //g. Verify last search result doesn’t contain the word “Interview”
        searchResults.last().shouldNotHave(text("Interview"));
    }
}