package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.services;

import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.dto.FlowerDTO;

@Service
@Component
public class FlowerDTOMapper implements Function<Flower, FlowerDTO> {

	@Override
	public FlowerDTO apply(Flower flower) {		
		FlowerDTO dto = new FlowerDTO();
		dto.setPk_FlowerId(flower.getPk_FlowerId());
		dto.setNameFlower(flower.getNameFlower());
		dto.setCountryFlower(flower.getCountryFlower());
		dto.setTypeFlower(dto.assignCountryTypeToFlower(flower.getCountryFlower()));
		return dto;
	}
	
	public Flower applyToEntity(FlowerDTO dto) {
		Flower flower = new Flower();
		flower.setPk_FlowerId(dto.getPk_FlowerId());
		flower.setNameFlower(dto.getNameFlower());
		flower.setCountryFlower(dto.getCountryFlower());
		return flower;
	}

}
