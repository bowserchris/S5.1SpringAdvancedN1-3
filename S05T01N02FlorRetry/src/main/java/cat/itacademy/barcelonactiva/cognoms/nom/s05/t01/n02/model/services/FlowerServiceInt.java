package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.services;

import java.util.List;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.dto.FlowerDTO;

public interface FlowerServiceInt {

	//return types all should be flower? include the mapper functions here instead of mapper class?
	List<FlowerDTO> getAllFlowers();
	Flower saveFlower(Flower flower);
	Flower updateFlower(int id, FlowerDTO flower);
	FlowerDTO getFlowerById(long id);
	void deleteFlowerById(long id);
	
}
