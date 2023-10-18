package cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.domain.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{

	Page<Branch> findAll(Pageable pageable);
	/*
	Couldn´t start the app because springboot tried to initialise each bean
	and couldnt do so. with these specific queries no more errors ocurred, 
	 but also these methods are not called so instead not needed, only the 
	 implementation and the <Branch,Long> parameter
	*/
	/*@Query("SELECT b FROM Branch b WHERE b.nameBranch = :name")
	List<Branch> findByName(String name);
	@Query("SELECT b FROM Branch b WHERE b.countryBranch = :country")
	List<Branch> findByCountry(String country);*/

}
