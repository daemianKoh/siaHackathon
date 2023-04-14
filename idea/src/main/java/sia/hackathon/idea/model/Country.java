package sia.hackathon.idea.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Country")
public class Country {
	
	@Id
	private Long id;
	
	private String country;
	
	private String cityCode;
	
	private String airport;
}
