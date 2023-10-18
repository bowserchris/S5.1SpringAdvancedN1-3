package cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.services;

import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.data.domain.Page;

import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.domain.Branch;
import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.dto.BranchDTO;

public interface BranchService {
	
	List<BranchDTO> getAllBranches();
	Branch saveBranch(Branch branch);
	Branch updateBranch(long id, Branch branch) throws AttributeNotFoundException;
	BranchDTO getBranchById(long id);
	void deleteBranchById(long id);
	Page<Branch> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
