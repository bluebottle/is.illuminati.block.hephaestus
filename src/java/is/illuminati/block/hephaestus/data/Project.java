package is.illuminati.block.hephaestus.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.idega.user.data.bean.Group;
import com.idega.user.data.bean.User;

@Entity
@Table(name = Project.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "project.findAll", query = "select p from Project p")
})
public class Project implements Serializable {
	private static final long serialVersionUID = 8600088375547845117L;

	public static final String ENTITY_NAME = "hep_project";
    
    private static final String COLUMN_PROJECT_ID = "project_id";
    private static final String COLUMN_PROJECT_CODE = "project_code";
    private static final String COLUMN_ADDRESS = "address";
    //@TODO Add GIS stuff
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_OWNER = "owner";
    private static final String COLUMN_CREATED_DATE = "created";
    private static final String COLUMN_CREATED_BY = "created_by";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Project.COLUMN_PROJECT_ID)
    private Long id;

    @Column(name = Project.COLUMN_PROJECT_CODE, nullable = false)
    private String code;

    @Column(name = Project.COLUMN_ADDRESS)
    private String address;

    @Column(name = Project.COLUMN_REMARKS)
    private String remarks;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Project.COLUMN_OWNER)
    private Group owner;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Project.COLUMN_CREATED_DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Project.COLUMN_CREATED_BY)
    private User createdBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Group getOwner() {
		return owner;
	}

	public void setOwner(Group owner) {
		this.owner = owner;
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
