package tech.madest.eparser.entity.shopizer.marketplace;


import tech.madest.eparser.entity.shopizer.common.description.Description;
import tech.madest.eparser.entity.shopizer.reference.language.Language;


/*@Entity
@Table(name="CATEGORY_DESCRIPTION",uniqueConstraints={
		@UniqueConstraint(columnNames={
			"CATEGORY_ID",
			"LANGUAGE_ID"
		})
	}
)*/
public class CatalogDescription extends Description {

	

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*	@ManyToOne(targetEntity = Catalog.class)
	@JoinColumn(name = "CATALOG_ID", nullable = false)*/
	private Catalog catalog;


	
	public CatalogDescription() {
	}
	
	public CatalogDescription(String name, Language language) {
		this.setName(name);
		this.setLanguage(language);
		super.setId(0L);
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	

}
