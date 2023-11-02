package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.model.services;

import java.util.List;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.model.dto.FlowerDTO;

public interface FlowerServiceInt {

	//return types all should be flower? include the mapper functions here instead of mapper class?
	List<FlowerDTO> getAllFlowers();
	Flower saveFlower(Flower flower);
	Flower updateFlower(long id, FlowerDTO flower);
	FlowerDTO getFlowerById(long id);
	void deleteFlowerById(long id);
	
}
