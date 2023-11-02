package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.model.domain;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Flower")
@Schema(description = "Details of Flower object")
@Table(name = "flower")
public class Flower {
	
	@Id
	//@Hidden
	@Schema(description = "Unique id of the Flower", name="pk_FlowerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int pk_FlowerId;
	
	@Schema(description = "Name of the Flower",name="nameFlower")
	@NotNull(message = "Flower name cannot be empty")
	@Column(name = "flower_name")
	private String nameFlower;
	
	@Schema(description = "Country of the Flower",name="countryFlower")
	@NotNull(message = "Flower country cannot be empty")
	@Column(name = "flower_country")
	private String countryFlower;
	
}