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
@Table(name = Cellar.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "cellar.findAll", query = "select c from Cellar c")
})
public class Cellar implements Serializable {
	private static final long serialVersionUID = 4114306844648055579L;

	public static final String ENTITY_NAME = "hep_cellar";
    
    private static final String COLUMN_CELLAR_ID = "cellar_id";
    private static final String COLUMN_CELLAR_NAME = "cellar_name";
    private static final String COLUMN_PAD = "pad";
    //@TODO Add GIS stuff
    private static final String COLUMN_CREATED_DATE = "created";
    private static final String COLUMN_CREATED_BY = "created_by";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Cellar.COLUMN_CELLAR_ID)
    private Long id;

    @Column(name = Cellar.COLUMN_CELLAR_NAME, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Cellar.COLUMN_PAD)
    private Pad pad;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = Cellar.COLUMN_CREATED_DATE)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Cellar.COLUMN_CREATED_BY)
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
