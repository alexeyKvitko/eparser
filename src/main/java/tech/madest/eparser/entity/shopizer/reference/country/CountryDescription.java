package tech.madest.eparser.entity.shopizer.reference.country;

import com.fasterxml.jackson.annotation.JsonIgnore;
import tech.madest.eparser.entity.shopizer.SchemaConstant;
import tech.madest.eparser.entity.shopizer.common.description.Description;
import tech.madest.eparser.entity.shopizer.reference.language.Language;

import javax.persistence.*;

@Entity
@Table(name = "COUNTRY_DESCRIPTION", uniqueConstraints={
	@UniqueConstraint(columnNames={
			"COUNTRY_ID",
			"LANGUAGE_ID"
		})
	}
)
@TableGenerator(name = "description_gen", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "country_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE, initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE)
//@SequenceGenerator(name = "description_gen", sequenceName = "country_description_seq", allocationSize = SchemaConstant.DESCRIPTION_ID_SEQUENCE_START)
public class CountryDescription extends Description {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Country.class)
	@JoinColumn(name = "COUNTRY_ID", nullable = false)
	private Country country;
	
	public CountryDescription() {
	}
	
	public CountryDescription(Language language, String name) {
		this.setLanguage(language);
		this.setName(name);
	}
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
