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
        @NamedQuery(name = "well.findAll", query = "select w from Well w"),
        @NamedQuery(name = "well.findByName", query = "select w from Well w where w.name = :name")
})
public class Well implements Serializable {
	private static final long serialVersionUID = 4774660293017194239L;

	public static final String ENTITY_NAME = "hep_well";
    
    private static final String COLUMN_WELL_ID = "well_id";
    private static final String COLUMN_WELL_NAME = "name";
    private static final String COLUMN_PAD = "pad_id";
    //@TODO Add GIS stuff
    private static final String COLUMN_DRILLED_DEPTH = "drilled_depth";
    //@TODO Add connection to wellhead table
    private static final String COLUMN_CREATED_DATE = "created";
    private static final String COLUMN_CREATED_BY = "created_by_id";
    
    private static final String COLUMN_X = "x";
    private static final String COLUMN_Y = "y";
    private static final String COLUMN_Z = "z";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Well.COLUMN_WELL_ID)
    private Long id;

    @Column(name = Well.COLUMN_WELL_NAME, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Well.COLUMN_PAD, nullable = false)
    private Pad pad;

    @Column(name = Well.COLUMN_DRILLED_DEPTH)
    private Double drilledDepth;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Well.COLUMN_CREATED_DATE, nullable = false)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Well.COLUMN_CREATED_BY, nullable = false)
    private User createdBy;

    @Column(name = Well.COLUMN_X)
    private Double x;

    @Column(name = Well.COLUMN_Y)
    private Double y;

    @Column(name = Well.COLUMN_Z)
    private Double z;

    
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

	public Double getDrilledDepth() {
		return drilledDepth;
	}

	public void setDrilledDepth(Double drilledDepth) {
		this.drilledDepth = drilledDepth;
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

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}   
}