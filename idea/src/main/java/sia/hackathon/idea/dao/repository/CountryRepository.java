package sia.hackathon.idea.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sia.hackathon.idea.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

	 Country findFirstByCityCode(String cityCode);
}
