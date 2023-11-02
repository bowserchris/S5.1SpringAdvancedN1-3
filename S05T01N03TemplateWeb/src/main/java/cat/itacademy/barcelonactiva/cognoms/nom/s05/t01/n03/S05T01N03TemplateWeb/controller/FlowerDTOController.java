package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.config.CommonConstants;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.model.domain.Flower;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.model.dto.FlowerDTO;
import cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.model.services.FlowerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

//localhost:9001/swagger-ui/index.html

//@CrossOrigin(origins = CommonConstants.ORIGIN)
@Tag(name = "FlowerDTOController", description = "REST APIs related to Flower Entity!!!!")
@RestController		
@RequestMapping(CommonConstants.FLOWER_INDEX)		//	/api needed to connect to swagger correctly?
public class FlowerDTOController {
	
	@Autowired
	private final WebClient.Builder client;

	public FlowerDTOController(WebClient.Builder builder) {
		this.client = builder;
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
		FlowerDTO[] list = client.build()
								.get()
								.uri(CommonConstants.GET_ALL_FLOWERS_9001)
								.retrieve()
								.bodyToMono(FlowerDTO[].class)
								.block();
				/*.bodyToFlux(FlowerDTO.class)
				.blockLast();*/
		//List<FlowerDTO> list = service.getAllFlowers();
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
		
		FlowerDTO result = WebClient.create(CommonConstants.ADD_FLOWER_9001)
					.post()
					.accept(MediaType.APPLICATION_JSON)
					.contentType(MediaType.APPLICATION_JSON)
					.body(Mono.just(flowerDTO), FlowerDTO.class)
					.retrieve()
					.bodyToMono(FlowerDTO.class) //create a response type class with a data type class
					.block();

		return ResponseEntity.ok(result);
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
	public ResponseEntity<?> updateFlower(@PathVariable long id, @RequestBody FlowerDTO flowerDTO) {

		FlowerDTO result = null;
		try {
			result = client.build().put()
				     .uri(CommonConstants.UPDATE_FLOWER_9001 + id)
				     .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				     .body(Mono.just(flowerDTO), FlowerDTO.class)
				     .retrieve()
				     .bodyToMono(FlowerDTO.class)
				     .block();
			//flower = service.updateFlower(id, flowerDTO);
		} catch (ResponseStatusException rse) {
			rse.printStackTrace();
		}
		if (result != null) {
			// entity to DTO
			//FlowerDTO flowerResponse = service.mapToDto(flower);		//modelMapper.map(flower, FlowerDTO.class);
			return ResponseEntity.ok(result);
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
		Void flower = client.build().delete()
			     .uri(CommonConstants.DELETE_FLOWER_9001 + id)
			     .retrieve()
			     .bodyToMono(Void.class)
			     .block();
		//service.deleteFlowerById(id);
		return ResponseEntity.ok(flower);
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
		FlowerDTO flower = client.build().get()
			     .uri(CommonConstants.GET_FLOWER_9001 + id)
			     .retrieve()
			     .bodyToMono(FlowerDTO.class)
			     .block();
		//FlowerDTO flower = service.getFlowerById(id);
		//students.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
		if (flower == null) {
			return new ResponseEntity<FlowerDTO> (HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FlowerDTO> (flower, HttpStatus.OK);
	}
	
}
