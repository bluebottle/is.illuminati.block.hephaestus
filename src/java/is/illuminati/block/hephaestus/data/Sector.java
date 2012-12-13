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

import com.idega.user.data.bean.User;

@Entity
@Table(name = Sector.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "sector.findAll", query = "select s from Sector s")
})
public class Sector implements Serializable {
	private static final long serialVersionUID = 8205425547951437646L;

	public static final String ENTITY_NAME = "hep_sector";
    
    private static final String COLUMN_SECTOR_ID = "sector_id";
    private static final String COLUMN_SECTOR_CODE = "sector_code";
    private static final String COLUMN_PROJECT = "project";
    //@TODO Add GIS stuff
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created";
    private static final String COLUMN_CREATED_BY = "created_by";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Sector.COLUMN_SECTOR_ID)
    private Long id;

    @Column(name = Sector.COLUMN_SECTOR_CODE, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Sector.COLUMN_PROJECT)
    private Project project;

    @Column(name = Sector.COLUMN_REMARKS)
    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Sector.COLUMN_CREATED_DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Sector.COLUMN_CREATED_BY)
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
