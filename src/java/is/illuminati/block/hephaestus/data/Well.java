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
@Table(name = Well.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "well.findAll", query = "select w from Well w")
})
public class Well implements Serializable {
	private static final long serialVersionUID = 4774660293017194239L;

	public static final String ENTITY_NAME = "hep_well";
    
    private static final String COLUMN_WELL_ID = "well_id";
    private static final String COLUMN_WELL_NAME = "name";
    private static final String COLUMN_PAD = "pad";
    //@TODO Add GIS stuff
    private static final String COLUMN_MEASURED_DEPTH = "measured_depth";
    //@TODO Add connection to wellhead table
    private static final String COLUMN_CREATED_DATE = "created";
    private static final String COLUMN_CREATED_BY = "created_by";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Well.COLUMN_WELL_ID)
    private Long id;

    @Column(name = Well.COLUMN_WELL_NAME, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Well.COLUMN_PAD, nullable = false)
    private Pad pad;

    @Column(name = Well.COLUMN_MEASURED_DEPTH)
    private Double measuredDepth;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Well.COLUMN_CREATED_DATE, nullable = false)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Well.COLUMN_CREATED_BY, nullable = false)
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

	public Pad getPad() {
		return pad;
	}

	public void setPad(Pad pad) {
		this.pad = pad;
	}

	public Double getMeasuredDepth() {
		return measuredDepth;
	}

	public void setMeasuredDepth(Double measuredDepth) {
		this.measuredDepth = measuredDepth;
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