package tech.madest.eparser.entity.shopizer.catalog;


import tech.madest.eparser.entity.shopizer.SchemaConstant;
import tech.madest.eparser.entity.shopizer.common.audit.AuditSection;
import tech.madest.eparser.entity.shopizer.common.audit.Auditable;
import tech.madest.eparser.entity.shopizer.generic.SalesManagerEntity;
import tech.madest.eparser.entity.shopizer.merchant.MerchantStore;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

/**
 * Allows grouping products and category
 * Catalog
 *      - category 1
 *      - category 2
 *      
 *      - product 1
 *      - product 2
 *      - product 3
 *      - product 4
 *      
 * @author carlsamson
 *
 */


@Entity
@EntityListeners(value = tech.madest.eparser.entity.shopizer.common.audit.AuditListener.class)
@Table(name = "CATALOG",
uniqueConstraints=@UniqueConstraint(columnNames = {"MERCHANT_ID", "CODE"}))
public class Catalog extends SalesManagerEntity<Long, Catalog> implements Auditable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, 
    	generator = "TABLE_GEN")
  	@TableGenerator(name = "TABLE_GEN", 
    	table = "SM_SEQUENCER", 
    	pkColumnName = "SEQ_NAME",
    	valueColumnName = "SEQ_COUNT",
    	pkColumnValue = "CATALOG_SEQ_NEXT_VAL",
    	allocationSize = SchemaConstant.DESCRIPTION_ID_ALLOCATION_SIZE,
    	initialValue = SchemaConstant.DESCRIPTION_ID_START_VALUE
  		)
    private Long id;

    @Embedded
    private AuditSection auditSection = new AuditSection();
    
    @Valid
    @OneToMany(mappedBy="catalog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CatalogCategoryEntry> entry = new HashSet<CatalogCategoryEntry>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MERCHANT_ID", nullable=false)
    private MerchantStore merchantStore;


    @Column(name = "VISIBLE")
    private boolean visible;
    
    @Column(name="DEFAULT_CATALOG")
    private boolean defaultCatalog;
    
    @NotEmpty
    @Column(name="CODE", length=100, nullable=false)
    private String code;

    @Column(name = "SORT_ORDER")
    private Integer sortOrder = 0;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Catalog() {
    }
    
    public Catalog(MerchantStore store) {
        this.merchantStore = store;
        this.id = 0L;
    }
    
    @Override
    public Long getId() {
        return this.id;
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
    public void setAuditSection(AuditSection auditSection) {
        this.auditSection = auditSection;
    }


    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public MerchantStore getMerchantStore() {
        return merchantStore;
    }

    public void setMerchantStore(MerchantStore merchantStore) {
        this.merchantStore = merchantStore;
    }

	public Set<CatalogCategoryEntry> getEntry() {
		return entry;
	}

	public void setEntry(Set<CatalogCategoryEntry> entry) {
		this.entry = entry;
	}

	public boolean isDefaultCatalog() {
		return defaultCatalog;
	}

	public void setDefaultCatalog(boolean defaultCatalog) {
		this.defaultCatalog = defaultCatalog;
	}



}