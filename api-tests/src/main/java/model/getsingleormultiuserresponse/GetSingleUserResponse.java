package model.getsingleormultiuserresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GetSingleUserResponse {

	@JsonProperty("data")
	private DataItem data;

	@JsonProperty("_meta")
	private Meta meta;

	@JsonProperty("support")
	private Support support;
}