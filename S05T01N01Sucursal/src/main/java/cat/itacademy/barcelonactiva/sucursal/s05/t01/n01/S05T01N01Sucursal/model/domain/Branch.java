package cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "branch")
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pk_BranchId;
	
	@Column(name = "branch_name")
	private String nameBranch;
	
	@Column(name = "branch_country")
	private String countryBranch;
	
	public Branch() { }
	
	public Branch(String name, String country) {
		this.nameBranch = name;
		this.countryBranch = country;
	}

	public int getPk_BranchId() {
		return pk_BranchId;
	}

	public void setPk_BranchId(int pk_BranchId) {
		this.pk_BranchId = pk_BranchId;
	}

	public String getNameBranch() {
		return nameBranch;
	}

	public void setNameBranch(String nameBranch) {
		this.nameBranch = nameBranch;
	}

	public String getCountryBranch() {
		return countryBranch;
	}

	public void setCountryBranch(String countryBranch) {
		this.countryBranch = countryBranch;
	}

	@Override
	public String toString() {
		return "Branch [pk_BranchId=" + pk_BranchId + ", nameBranch=" + nameBranch + ", countryBranch=" + countryBranch
				+ "]";
	}

}
