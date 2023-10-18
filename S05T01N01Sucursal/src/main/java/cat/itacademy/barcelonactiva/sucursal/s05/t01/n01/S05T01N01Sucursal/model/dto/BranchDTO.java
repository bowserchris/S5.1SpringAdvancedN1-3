package cat.itacademy.barcelonactiva.sucursal.s05.t01.n01.S05T01N01Sucursal.model.dto;

import java.util.Arrays;
import java.util.List;

public class BranchDTO {
	
	private int pk_BranchId;
	private String nameBranch;
	private String countryBranch;
	private String typeBranch;
	protected final List<String> countriesEU = Arrays.asList("Austria", "Belgium", "Bulgaria",
            "Croatia", "Cyprus", "Czech Republic", "Denmark", "Estonia", "Finland",
            "France", "Germany", "Greece", "Hungary", "Ireland", "Italy", "Latvia",
            "Lithuania", "Luxembourg", "Malta", "Netherlands", "Poland", "Portugal",
            "Romania", "Slovakia", "Slovenia", "Spain", "Sweden");
	
	public BranchDTO() { }
	
	public BranchDTO(int id, String name, String country) {
		this.pk_BranchId = id;
		this.nameBranch = name;
		this.countryBranch = country;
		this.typeBranch = assignCountryTypeToBranch(country);
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
	
	public void setCountryBranch(String country) {
		this.countryBranch = country;
	}

	public String getTypeBranch() {
		return typeBranch;
	}

	public void setTypeBranch(String typeBranch) {
		this.typeBranch = typeBranch;
	}

	public List<String> getCountriesEU() {
		return countriesEU;
	}
	
	public String assignCountryTypeToBranch(String country) {
		String countryType;
		if (countriesEU.contains(country)) {
			countryType = "EU";
		} else {
			countryType = "Non-EU";
		}
		return countryType;
	}

}
