package is.illuminati.block.hephaestus.data;

import is.illuminati.block.hephaestus.business.ToolType;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.idega.user.data.bean.User;

@Entity
@Table(name = Tool.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "tool.findAll", query = "select t from Tool t")
})
public class Tool implements Serializable {
	private static final long serialVersionUID = -6834526838649534405L;

	public static final String ENTITY_NAME = "hep_tool";
    
    private static final String COLUMN_TOOL_ID = "tool_id";
    private static final String COLUMN_TOOL_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_BRAND = "brand";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CREATED_DATE = "created";
    private static final String COLUMN_CREATED_BY = "created_by_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Tool.COLUMN_TOOL_ID)
    private Long id;

    @Column(name = Tool.COLUMN_TOOL_NAME, nullable = false)
    private String name;

    @Column(name = Tool.COLUMN_TYPE, nullable = false)
    @Enumerated(EnumType.STRING)
    private ToolType type;
    
    @Column(name = Tool.COLUMN_BRAND)
    private String brand;

    @Column(name = Tool.COLUMN_DESCRIPTION)
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Tool.COLUMN_CREATED_DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Tool.COLUMN_CREATED_BY)
    private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ToolType getType() {
		return type;
	}

	public void setType(ToolType type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
}