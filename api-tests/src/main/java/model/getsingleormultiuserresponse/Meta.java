package model.getsingleormultiuserresponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Meta{

	@JsonProperty("docs_url")
	private String docsUrl;

	@JsonProperty("features")
	private List<String> features;

	@JsonProperty("powered_by")
	private String poweredBy;

	@JsonProperty("template_gallery")
	private String templateGallery;

	@JsonProperty("message")
	private String message;

	@JsonProperty("upgrade_url")
	private String upgradeUrl;

	@JsonProperty("upgrade_cta")
	private String upgradeCta;
}