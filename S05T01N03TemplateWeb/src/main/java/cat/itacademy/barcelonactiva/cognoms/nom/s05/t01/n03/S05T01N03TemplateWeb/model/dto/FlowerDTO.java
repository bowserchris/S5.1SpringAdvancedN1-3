package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.model.dto;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.config.CommonConstants;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {
	
	//@schema, not empty and patter annotations needed?
	@Hidden
	private int pk_FlowerId;
	
	@Schema(description = "Name of the Flower",name="nameFlower")
	@NotNull(message = "Flower name cannot be empty")
	@Column(name = "flower_name")
	private String nameFlower;
	
	@Schema(description = "Country of the Flower",name="countryFlower")
	@NotNull(message = "Flower country cannot be empty")
	@Column(name = "flower_country")
	private String countryFlower;
	private String typeFlower;
	
	public String assignCountryTypeToFlower(String country) {
		String countryType;
		if (CommonConstants.COUNTRIES_EU.contains(country)) {
			countryType = "EU";
		} else {
			countryType = "Non-EU";
		}
		return countryType;
	}

}
