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
@Table(name = Pad.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "pad.findAll", query = "select p from Pad p")
})
public class Pad implements Serializable {
	private static final long serialVersionUID = -8250222613886504560L;

	public static final String ENTITY_NAME = "hep_pad";
    
    private static final String COLUMN_PAD_ID = "pad_id";
    private static final String COLUMN_PAD_NAME = "pad_name";
    private static final String COLUMN_PROJECT = "project_id";
    //@TODO Add GIS stuff
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created";
    private static final String COLUMN_CREATED_BY = "created_by_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Pad.COLUMN_PAD_ID)
    private Long id;

    @Column(name = Pad.COLUMN_PAD_NAME, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Pad.COLUMN_PROJECT, nullable = false)
    private Project project;

    @Column(name = Pad.COLUMN_REMARKS)
    private String remarks;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Pad.COLUMN_CREATED_DATE, nullable = false)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Pad.COLUMN_CREATED_BY, nullable = false)
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
