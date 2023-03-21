package api_tests;

import dto.Entry;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class RestApiTests {
    @Test
    public void publicapisTest() {
        List<Entry> entries = RestAssured.when().get("https://api.publicapis.org/entries")
                .then()
                .statusCode(200)
                .and()
                .and()
                .extract().response().jsonPath()
                .getList("entries", Entry.class)
                .stream().filter(entry -> entry.getCategory().equals("Authentication & Authorization"))
                .collect(Collectors.toList());

        System.out.println("Number of objects with property “Category: Authentication & Authorization”: " + entries.size());
        entries.forEach(System.out::println);
    }
}