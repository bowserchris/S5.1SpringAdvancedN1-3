package cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.domain.Branch;
import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.dto.BranchDTO;
import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {
	
	@Autowired
	private final BranchRepository branchRepo;
	
	public BranchServiceImpl (BranchRepository repo) {
		super();
		this.branchRepo = repo;
	}

	@Override
	public List<BranchDTO> getAllBranches() {
		return branchRepo.findAll()
						.stream()
						.map(b -> mapEntityToDTO(b))
						.collect(Collectors.toList());
	}

	@Override
	public Branch saveBranch(Branch branch) {
		return branchRepo.save(branch);
	}

	@Override
	public BranchDTO getBranchById(long id) {
		Optional<Branch> optional = branchRepo.findById(id);
		BranchDTO branch = null;
		if (optional.isPresent()) {
			branch = mapEntityToDTO(optional.get());
		} else {
			throw new RuntimeException ("Branch not found for id: " + id);
		}
		return branch;
		//return mapEntityToDTO(branchRepo.findById(id).orElse(null));
	}

	@Override
	public void deleteBranchById(long id) {
		//or branchRepository.delete(mapDTOToEntity(branchDTO));
		this.branchRepo.deleteById(id);
		System.out.println("Branch deleted.");
	}

	@Override
	public Branch updateBranch(long id, Branch branchRequest) throws AttributeNotFoundException {
		Branch branch = branchRepo.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException());
		branch.setCountryBranch(branchRequest.getCountryBranch());
		branch.setNameBranch(branchRequest.getNameBranch());
		return branchRepo.save(branch);
	}

	@Override
	public Page<Branch> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(
					Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
					Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.branchRepo.findAll(pageable);
	}
	
	public BranchDTO mapEntityToDTO(Branch branch) {
		BranchDTO dto = new BranchDTO();
		dto.setPk_BranchId(branch.getPk_BranchId());
		dto.setNameBranch(branch.getNameBranch());
		dto.setCountryBranch(branch.getCountryBranch());
		dto.setTypeBranch(dto.assignCountryTypeToBranch(branch.getCountryBranch()));
		/*boolean eu = branchdto.getCountrys().stream()		//in case constructor doesnt work for static method of assigning type in constructor
                .anyMatch(x -> x.equalsIgnoreCase(branch.getCountry()));*/
		return dto;
	}
	
	public Branch mapDTOToEntity(BranchDTO dto) {
		Branch branch = new Branch();
		branch.setPk_BranchId(dto.getPk_BranchId());
		branch.setNameBranch(dto.getNameBranch());
		branch.setCountryBranch(dto.getCountryBranch());
		return branch;
	}

}
