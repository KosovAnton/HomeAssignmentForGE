package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonPropertyOrder({
        "API",
        "Description",
        "Auth",
        "HTTPS",
        "Cors",
        "Link",
        "Category",
})
@Data
public class Entry {
    @JsonProperty("API")
    private String api;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Auth")
    private String auth;
    @JsonProperty("HTTPS")
    private String https;
    @JsonProperty("Cors")
    private String cors;
    @JsonProperty("Link")
    private String link;
    @JsonProperty("Category")
    private String category;
}