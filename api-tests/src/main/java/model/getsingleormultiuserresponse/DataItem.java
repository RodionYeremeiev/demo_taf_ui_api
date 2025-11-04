package model.getsingleormultiuserresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataItem{

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("avatar")
	private String avatar;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("email")
	private String email;
}