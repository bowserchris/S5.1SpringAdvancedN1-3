package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.config.CommonConstants;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n02.model.services.FlowerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//localhost:9001/swagger-ui/index.html

//@CrossOrigin(origins = CommonConstants.ORIGIN)
@Tag(name = "FlowerDTOController", description = "REST APIs related to Flower Entity!!!!")
@RestController		
@RequestMapping(CommonConstants.FLOWER_INDEX)		//	/api needed to connect to swagger correctly?
public class FlowerDTOController {
	
	///  over each method there is an @Operation and various @ApiResponse   ///
	//private static final Properties properties = new Properties();

	@Autowired
	private FlowerServiceImpl service;
	
	public FlowerDTOController(FlowerServiceImpl service) {
		super();
		this.service = service;
	}
	
	@Operation(summary = "Returns list of flowers")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = CommonConstants.CODE_200, description = CommonConstants.LIST_RETURNED, content = { 
					@Content(mediaType = CommonConstants.MEDIA_TYPE_JSON, schema = @Schema(implementation = FlowerDTO.class))
			}),//implementation might be a double here
			@ApiResponse(responseCode = CommonConstants.CODE_404, description = CommonConstants.FLOWER_NOT_FOUND, content = @Content),
			@ApiResponse(responseCode = CommonConstants.CODE_500, description = CommonConstants.INTERNAL_SERVER_ERR, content = @Content),
			@ApiResponse(responseCode = CommonConstants.CODE_1001, description = CommonConstants.APPLICATION_ERROR, content = @Content)
	})
	@GetMapping(CommonConstants.GET_ALL_FLOWERS)	
	public ResponseEntity<?> getAllFlowers() {
		List<FlowerDTO> list = service.getAllFlowers();
										/*.stream()
										.map(flower -> service.getDTOMapper().apply(flower))		//modelMapper.map(flower,FlowerDTO.class)
										.collect(Collectors.toList());*/
		return ResponseEntity.ok(list);
	}
	
	@Operation(summary = "Save a flower in the System")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = CommonConstants.CODE_200, description = CommonConstants.FLOWER_CREATED, content = { 
					@Content(mediaType = CommonConstants.MEDIA_TYPE_JSON, schema = @Schema(implementation = Flower.class))
				}),
				@ApiResponse(responseCode = CommonConstants.CODE_401, description = CommonConstants.UNAUTHORIZED, content = @Content), 
				@ApiResponse(responseCode = CommonConstants.CODE_403, description = CommonConstants.VALIDATION_ERROR, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_404, description = CommonConstants.FLOWER_NOT_FOUND, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_500, description = CommonConstants.INTERNAL_SERVER_ERR, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_1001, description = CommonConstants.APPLICATION_ERROR, content = @Content)
	})
	@PostMapping(CommonConstants.ADD_FLOWER)		
	public ResponseEntity<?> saveFlower(@RequestBody FlowerDTO flowerDTO) {

		// convert DTO to entity
		Flower flowerRequest = service.mapToEntity(flowerDTO);		//or modelMapper.map(flower, FlowerDTO.class);

		Flower flower = service.saveFlower(flowerRequest);

		// convert entity to DTO
		FlowerDTO flowerResponse = service.mapToDto(flower);				//modelMapper.map(flower, FlowerDTO.class);

		return ResponseEntity.ok(flowerResponse);
	}
	
	@Operation(summary = "Update a flower in the System ")
	@ApiResponses(value = { 
				@ApiResponse(responseCode = CommonConstants.CODE_201, description = CommonConstants.FLOWER_UPDATED, content = { 
						@Content(mediaType = CommonConstants.MEDIA_TYPE_JSON, schema = @Schema(implementation = FlowerDTO.class))
				}),
				@ApiResponse(responseCode = CommonConstants.CODE_401, description = CommonConstants.UNAUTHORIZED, content = @Content), 
				@ApiResponse(responseCode = CommonConstants.CODE_403, description = CommonConstants.VALIDATION_ERROR, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_404, description = CommonConstants.FLOWER_NOT_FOUND, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_500, description = CommonConstants.INTERNAL_SERVER_ERR, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_1001, description = CommonConstants.APPLICATION_ERROR, content = @Content)
	})
	@PutMapping(CommonConstants.UPDATE_FLOWER)		
	public ResponseEntity<?> updateFlower(@PathVariable int id, @RequestBody FlowerDTO flowerDTO) {

		Flower flower = null;
		try {
			flower = service.updateFlower(id, flowerDTO);
		} catch (ResponseStatusException rse) {
			rse.printStackTrace();
		}
		if (flower != null) {
			// entity to DTO
			FlowerDTO flowerResponse = service.mapToDto(flower);		//modelMapper.map(flower, FlowerDTO.class);
			return ResponseEntity.ok(flowerResponse);
		}
		return ResponseEntity.notFound().build();
	}
	
	@Operation(summary = "Delete a flower in the System ")
	@ApiResponses(value = { 
				@ApiResponse(responseCode = CommonConstants.CODE_200, description = CommonConstants.FLOWER_DELETED, content = { 
						@Content(mediaType = CommonConstants.MEDIA_TYPE_JSON, schema = @Schema(implementation = FlowerDTO.class))
				}),
				@ApiResponse(responseCode = CommonConstants.CODE_401, description = CommonConstants.UNAUTHORIZED, content = @Content), 
				@ApiResponse(responseCode = CommonConstants.CODE_403, description = CommonConstants.VALIDATION_ERROR, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_404, description = CommonConstants.FLOWER_NOT_FOUND, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_500, description = CommonConstants.INTERNAL_SERVER_ERR, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_1001, description = CommonConstants.APPLICATION_ERROR, content = @Content)
	})
	@DeleteMapping(CommonConstants.DELETE_FLOWER)		
	public ResponseEntity<?> deleteFlower(@PathVariable(name = "id") Long id) {
		service.deleteFlowerById(id);
		return ResponseEntity.ok(id);
	}
	
	
	@Operation(summary = "Get specific flower from the System ")
	@ApiResponses(value = { 
				@ApiResponse(responseCode = CommonConstants.CODE_200, description = CommonConstants.SUCCESSFUL, content = { 
						@Content(mediaType = CommonConstants.MEDIA_TYPE_JSON, schema = @Schema(implementation = FlowerDTO.class))
				}),
				@ApiResponse(responseCode = CommonConstants.CODE_401, description = CommonConstants.UNAUTHORIZED, content = @Content), 
				@ApiResponse(responseCode = CommonConstants.CODE_403, description = CommonConstants.VALIDATION_ERROR, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_404, description = CommonConstants.FLOWER_NOT_FOUND, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_500, description = CommonConstants.INTERNAL_SERVER_ERR, content = @Content),
				@ApiResponse(responseCode = CommonConstants.CODE_1001, description = CommonConstants.APPLICATION_ERROR, content = @Content)
	})
	@GetMapping(CommonConstants.GET_FLOWER)		
	public ResponseEntity<?> getFlowerById(@PathVariable("id") Long id) {
		FlowerDTO flower = service.getFlowerById(id);
		//students.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
		if (flower == null) {
			return new ResponseEntity<FlowerDTO> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FlowerDTO> (flower, HttpStatus.OK);
	}
	
	////////html calls for flower controller if needed ////////
	
	/*
	@GetMapping({"", "/"})
  public String viewHomePage(){
      return HTML_HOME;
  }
	
	@GetMapping({INDEX, GET_ALL})
  public String viewIndexPage(Model model) {
      model.addAttribute("getAllFlowers", service.getAllFlowers());
      return HTML_INDEX;
		//return findPaginated(1, "nameBranch", "asc", model);
  }
	
	@GetMapping(FORM_NEW)
  public String showNewFlowerForm(Model model) {
		Flower flower = new Flower();
      model.addAttribute("flower", flower);
      return HTML_NEW_FLOWER;
  }
	
	@PostMapping({ADD, UPDATE})		
  public String saveFlower(@ModelAttribute("flower") Flower flower) {
      service.saveFlower(flower);
      return HTML_REDIRECT;
  }
	
	@GetMapping(FORM_UPDATE)			
  public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
      FlowerDTO dto = service.getFlowerById(id);
      model.addAttribute("flower", dto);
      return HTML_UPDATE_FLOWER;
  }
	
	@GetMapping(DELETE)
	public String deleteFlower(@PathVariable(value = "id") long id) {
		service.deleteFlowerById(id);
		return HTML_REDIRECT;
	}
	
	@GetMapping(FORM_DELETE)		
  public String showFormForDelete(@PathVariable(value = "id") long id, Model model) {
		FlowerDTO dto = service.getFlowerById(id);
		model.addAttribute("flower", dto);
      return HTML_DELETE_FLOWER;
  }
	
	@GetMapping(FORM_GET_ONE)		
  public String showFormForOne(@PathVariable(value = "id") long id, Model model) { 
		FlowerDTO dto = service.getFlowerById(id);
      model.addAttribute("flower", dto);
		return HTML_GET_ONE;
  }
	
	@GetMapping(GET_ONE)
	public String getOne(@PathVariable(value = "id") Model model, long id) {
		FlowerDTO flower = service.getFlowerById(id);
		if(flower != null) {
			model.addAttribute("flower", flower);
		}else {
			model.addAttribute("flower", service.getAllFlowers());
		}
		return HTML_GET_ONE;
	}
	
	*/
}
