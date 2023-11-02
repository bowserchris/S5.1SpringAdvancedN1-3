package cat.itacademy.barcelonactiva.cognoms.nom.s05.t01.n03.S05T01N03TemplateWeb.config;

import java.util.Arrays;
import java.util.List;

public class CommonConstants {
	
	public CommonConstants() { }
	
	//Software INFO
		public static final String SOFTWARE_NAME = "Flower Shop\u2122 RestTemplate or WebClient";
		
		//URL mappings for authentication controller
		//start with /auth
		public static final String ORIGIN = "http://localhost:9002";
		public static final String FLOWER_INDEX = "/flower";
		
		public static final String ID = "/{id}";
		public static final String ADD_FLOWER = "/clientFlowerAdd";					
		public static final String GET_ALL_FLOWERS = "/clientFlowerGetAll"; 
		public static final String UPDATE_FLOWER = "/clientFlowerUpdate" + ID;
		public static final String DELETE_FLOWER = "/clientFlowerDelete" + ID;
		public static final String GET_FLOWER = "/clientFlowerGetOne" + ID;
		
		//9001 port end points
		public static final String ORIGIN_9001 = "http://localhost:9001/flower";
		
		public static final String ADD_FLOWER_9001 = ORIGIN_9001 + "/add";					
		public static final String GET_ALL_FLOWERS_9001 = ORIGIN_9001 + "/getAll"; 
		public static final String UPDATE_FLOWER_9001 = ORIGIN_9001 + "/update/";// + ID;
		public static final String DELETE_FLOWER_9001 = ORIGIN_9001 + "/delete/";// + ID;
		public static final String GET_FLOWER_9001 = ORIGIN_9001 + "/getOne/";// + ID;
		
		//Security constants
		public static final String HEADER_TYPE_OBJECT = "Accept=application/json";
		public static final String CRUD_METHOD_GET = "GET";
		public static final String CRUD_METHOD_POST = "POST";
		public static final String CRUD_METHOD_PUT = "PUT";
		public static final String CRUD_METHOD_DELETE = "DELETE";
		public static final String CONTENT_TYPE = "Content-Type";
		public static final String MEDIA_TYPE_JSON = "application/json";

		//Message constructs
		public static final String FLOWER_ID = "Flower id "; // id value goes in between
		public static final String DOES_NOT_EXIST = " does not exist.";
		
		//error messages
		public static final String FLOWER_EXISTS = "Flower already exists.";
		public static final String UNKNOWN_INTERNAL_ERROR = "Unknown internal server error";
		public static final String VALIDATION_ERROR = "Validation Errors";
		public static final String CONSTRAINT_VIOLATION = "Constraint Violation";
		public static final String FLOWER_NOT_FOUND = "Flower Not Found";
		public static final String INTERNAL_SERVER_ERR = "Internal Server Error";
		public static final String APPLICATION_ERROR = "Application specific error";
		public static final String LIST_IS_EMPTY = "The list is empty. There are no flowers in the Database";
		public static final String UNAUTHORIZED = "Access in unauthorized";
				
		//response messages
		public static final String SUCCESSFUL = "Request successfuly made";
		public static final String FLOWER_CREATED = "The flower was created successfully";
		public static final String FLOWER_UPDATED = "The flower was updated successfully";
		public static final String LIST_RETURNED = "The list was returned successfully";
		public static final String FLOWER_DELETED = "The flower was deleted correctly";
		
		//swagger api code responses
		public static final String CODE_200 = "200";
		public static final String CODE_201 = "201";
		public static final String CODE_204 = "204";
		public static final String CODE_400 = "400";
		public static final String CODE_401 = "401";
		public static final String CODE_403 = "403";
		public static final String CODE_404 = "404";
		public static final String CODE_500 = "500";
		public static final String CODE_1001 = "1001";
		
		public static final List<String> COUNTRIES_EU = Arrays.asList("Austria", "Belgium", "Bulgaria",
	            "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland",
	            "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia",
	            "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal",
	            "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");
		
		public static String returnFlowerIdDoesNotExistMSG(long id) {
			return FLOWER_ID + id + DOES_NOT_EXIST;
		}

}
