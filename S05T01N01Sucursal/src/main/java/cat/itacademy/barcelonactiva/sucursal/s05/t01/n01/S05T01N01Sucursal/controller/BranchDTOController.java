package cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.domain.Branch;
import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.services.BranchServiceImpl;

@CrossOrigin(origins = "http://localhost:9000")
@Controller		//or RestController tho restcontroller doesnt work with thymeleaf, have to do separate classes if need of a view and a crud separately
@RequestMapping("")
public class BranchDTOController {
	
	private static final String INDEX = "/branch";
	private static final String ADD = INDEX + "/add";
	private static final String UPDATE = INDEX + "/update/{id}";
	private static final String DELETE = INDEX + "/delete/{id}";
	private static final String GET_ONE = INDEX + "/getOne/{id}";
	private static final String GET_ALL = INDEX + "/getAll";
	private static final String FORM_NEW = "/showNewBranchForm";
	private static final String FORM_UPDATE = "/showFormForUpdate/{id}";
	private static final String FORM_DELETE = "/showFormForDelete/{id}";
	private static final String FORM_GET_ONE = "/showFormForOne/{id}";
	private static final String HTML_HOME = "home";
	private static final String HTML_INDEX = "index";
	private static final String HTML_NEW_BRANCH = "new_branch";
	private static final String HTML_UPDATE_BRANCH = "update_branch";
	private static final String HTML_DELETE_BRANCH = "delete_branch";
	private static final String HTML_GET_ONE = "get_one";
	private static final String HTML_REDIRECT = "redirect:" + INDEX;
	//private static final String NEW_PAGE = INDEX + "/page/{pageNo}";
	
	@Autowired
	private BranchServiceImpl service;
	
	public BranchDTOController(BranchServiceImpl service) {
		super();
		this.service = service;
	}
	
	@GetMapping({"", "/"})
    public String viewHomePage(){
        return HTML_HOME;
    }
	
	@GetMapping({INDEX, GET_ALL})
    public String viewIndexPage(Model model) {
        model.addAttribute("getAllBranches", service.getAllBranches());
        return HTML_INDEX;
		//return findPaginated(1, "nameBranch", "asc", model);
    }
	
	@GetMapping(FORM_NEW)		//FORM_NEW, /showNewBranchForm
    public String showNewBranchForm(Model model) {
        // create model attribute to bind form data
        Branch branch = new Branch();
        model.addAttribute("branch", branch);
        return HTML_NEW_BRANCH;
    }
	
	@PostMapping({ADD, UPDATE})		//ADD, "/branch/add"
    public String saveBranch(@ModelAttribute("branch") Branch branch) {
        // save branch to database
        service.saveBranch(branch);
        return HTML_REDIRECT;
    }
	
	@GetMapping(FORM_UPDATE)			//FORM_UPDATE , /showFormForUpdate/{id}
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get branch from the service
        BranchDTO dto = service.getBranchById(id);
        // set branch as a model attribute to pre-populate the form
        model.addAttribute("branch", dto);
        return HTML_UPDATE_BRANCH;
    }
	
	@GetMapping(DELETE)
	public String deleteBranch(@PathVariable(value = "id") long id) {
		service.deleteBranchById(id);
		return HTML_REDIRECT;
	}
	
	@GetMapping(FORM_DELETE)		//FORM_DELETE, "/deleteBranch/{id}"
    public String showFormForDelete(@PathVariable(value = "id") long id, Model model) {
		BranchDTO dto = service.getBranchById(id);
        // call delete branch method 
		model.addAttribute("branch", dto);
        
        return HTML_DELETE_BRANCH;
    }
	
	
	@GetMapping(FORM_GET_ONE)		
    public String showFormForOne(@PathVariable(value = "id") long id, Model model) {			//@PathVariable(value = "id") long id, 
		//BranchDTO dto = service.getBranchById(id);
        // call delete branch method 
		
		BranchDTO dto = service.getBranchById(id);
        model.addAttribute("branch", dto);
		//model.addAttribute("branch", dto);
        
        return HTML_GET_ONE;
    }
	
	@GetMapping(GET_ONE)
	public String getOne(@PathVariable(value = "id") Model model, long id) {
		BranchDTO branch = service.getBranchById(id);
		if(branch != null) {
			model.addAttribute("branch", branch);
		}else {
			model.addAttribute("branch", service.getAllBranches());
		}
		return HTML_REDIRECT;
	}
	
	/*@GetMapping(NEW_PAGE) 		//NEW_PAGE = "/page/{pageNo}"
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Branch> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Branch> listBranches = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("getAllBranches", listBranches);
		return HTML_INDEX;
	}*/
	
	////////////methods that were used for daos and responseentities//////////
	
	/*@GetMapping(GET_ALL)		//GET_ALL , INDEX + "/getAll"
	public List<BranchDTO> getAllBranches() {
		return service.getAllBranches()
				.stream()
				//the below grayed out code is able to cut out the info i dont want displyed (country list etc.)
				//but it doesnt call the assign method i want from the DTO if its EU or not EU
				/*.map(branch -> new BranchDTORecord(
						branch.getPk_BranchId(),
						branch.getNameBranch(),
						branch.getCountryBranch(), null)).collect(Collectors.toList());
				
				*//*
				.map(branch -> modelMapper.map(branch,BranchDTO.class))
				.collect(Collectors.toList());
	} 
	
	*@PostMapping(SAVE)		//ADD, INDEX + "/add"
	public ResponseEntity<BranchDTO> saveBranch(@RequestBody BranchDTO branchDTO) {

		// convert DTO to entity
		Branch branchRequest = modelMapper.map(branchDTO, Branch.class);

		Branch branch = service.saveBranch(branchRequest);

		// convert entity to DTO
		BranchDTO branchResponse = modelMapper.map(branch, BranchDTO.class);

		return new ResponseEntity<BranchDTO>(branchResponse, HttpStatus.CREATED);
	}
	*
	*@PutMapping(UPDATE)		//UPDATE, INDEX + "/update/{id}"
	public ResponseEntity<BranchDTO> updateBranch(@PathVariable long id, @RequestBody BranchDTO branchDTO) {

		// convert DTO to Entity
		Branch branchRequest = modelMapper.map(branchDTO, Branch.class);

		Branch branch = null;
		try {
			branch = service.updateBranch(id, branchRequest);
		} catch (AttributeNotFoundException anfe) {
			anfe.printStackTrace();
		}
		
		if (branch != null) {
			// entity to DTO
			BranchDTO branchResponse = modelMapper.map(branch, BranchDTO.class);
			return ResponseEntity.ok().body(branchResponse);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(DELETE)		//DELETE, INDEX + "/delete/{id}"
	public ResponseEntity<BranchDTO> deleteBranch(@PathVariable(name = "id") Long id) {
		service.deleteBranchById(id);
		return new ResponseEntity<BranchDTO>(HttpStatus.OK);
	}
	
	*@GetMapping(GET_ONE)		//GET_ONE, INDEX + "/getOne/{id}"
	public ResponseEntity<BranchDTO> getBranchById(@PathVariable(name="id") Long id) {
		BranchDTO branch = service.getBranchById(id);
		BranchDTO response = modelMapper.map(branch, BranchDTO.class);
		return ResponseEntity.ok().body(response);
		
	}
	*
	*/
	
	
	
	
}
