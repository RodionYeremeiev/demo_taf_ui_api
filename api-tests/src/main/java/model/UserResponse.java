package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class UserResponse {
    @JsonProperty
    public String name;
    @JsonProperty
    public String job;
    @JsonProperty
    public String id;
    @JsonProperty
    public String createdAt;
}
