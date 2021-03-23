package tech.madest.eparser.entity.shopizer.catalog;


import tech.madest.eparser.entity.shopizer.SchemaConstant;
import tech.madest.eparser.entity.shopizer.category.Category;
import tech.madest.eparser.entity.shopizer.common.audit.AuditSection;
import tech.madest.eparser.entity.shopizer.common.audit.Auditable;
import tech.madest.eparser.entity.shopizer.generic.SalesManagerEntity;

import javax.persistence.*;

@Entity
@EntityListeners(value = tech.madest.eparser.entity.shopizer.common.audit.AuditListener.class)
@Table(name = "CATALOG_ENTRY",uniqueConstraints=
@UniqueConstraint(columnNames = {"CATEGORY_ID", "CATALOG_ID"}) )
public class CatalogCategoryEntry extends SalesManagerEntity<Long, CatalogCategoryEntry> implements Auditable {
	
	
    @Embedded
    private AuditSection auditSection = new AuditSection();
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, 
	generator = "TABLE_GEN")
	
	@TableGenerator(name = "TABLE_GEN", 
	table = "SM_SEQUENCER", 
	pkColumnName = "SEQ_NAME",
	valueColumnName = "SEQ_COUNT",
	allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE,
	initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE,
	pkColumnValue = "CATALOG_ENT_SEQ_NEXT_VAL")
	private Long id;
 
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
	Category category;
    
	@ManyToOne
	@JoinColumn(name = "CATALOG_ID", nullable = false)
	private Catalog catalog;
	
	//TODO d products ????
	
    @Column(name = "VISIBLE")
    private boolean visible;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
		
	}

	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}

	@Override
	public void setAuditSection(AuditSection audit) {
		auditSection = audit;
		
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
