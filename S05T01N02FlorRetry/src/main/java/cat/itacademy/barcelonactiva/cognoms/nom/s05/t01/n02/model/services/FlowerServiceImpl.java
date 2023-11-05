package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.config.CommonConstants;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.repository.FlowerRepository;

@Service
public class FlowerServiceImpl implements FlowerServiceInt {
	
	@Autowired
	private final FlowerRepository flowerRepo;
	@Autowired
	private final FlowerDTOMapper dtoMapper;
	
	public FlowerServiceImpl (FlowerRepository repo, FlowerDTOMapper dtoMapper) {
		super();
		this.flowerRepo = repo;
		this.dtoMapper = dtoMapper;
	}
	
	/*public FlowerDTOMapper getDTOMapper() {
		return dtoMapper;
	}*/
	
	@Override
	public List<FlowerDTO> getAllFlowers() {
		return flowerRepo.findAll()
				.stream()
				.map(b -> dtoMapper.apply((b)))
				.collect(Collectors.toList());
	}

	@Override
	public Flower saveFlower(Flower flower) {
		return flowerRepo.save(flower);
	}

	@Override
	public Flower updateFlower(int id, FlowerDTO flowerRequest) {
		FlowerDTO flowerInDB = getFlowerById(id);
		Flower flowerUpdate = null;
		if (flowerInDB != null) {
			flowerUpdate = mapToEntity(flowerRequest);
			flowerUpdate.setPk_FlowerId(id);
			flowerRepo.save(flowerUpdate);
		}
		return flowerUpdate;
	}

	@Override
	public FlowerDTO getFlowerById(long id) {
		Optional<Flower> optional = flowerRepo.findById(id);
		FlowerDTO flower = null;
		if (optional.isPresent()) {
			flower = mapToDto(optional.get());
		} else {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,
					CommonConstants.returnFlowerIdDoesNotExistMSG(id));
		}
		return flower;
	}

	@Override
	public void deleteFlowerById(long id) {
		flowerRepo.deleteById(id);
	}
	
	public FlowerDTO mapToDto(Flower f) {
		return dtoMapper.apply(f);
	}
	
	public Flower mapToEntity(FlowerDTO f) {
		return dtoMapper.applyToEntity(f);
	}
	
	/*@Override
	public Page<Flower> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(
					Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
					Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.flowerRepo.findAll(pageable);
	}*/
	
}
