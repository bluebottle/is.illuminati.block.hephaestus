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

@Entity
@Table(name = TimeData.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "timeData.findAll", query = "select t from TimeData t")
})
public class TimeData implements Serializable {
	private static final long serialVersionUID = 272483576081403673L;

	public static final String ENTITY_NAME = "hep_ddata";
    
    private static final String COLUMN_DEPTH_DATA_ID = "ddata_id";
    private static final String COLUMN_LOG_HEADER = "logf";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_VALUE = "value";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TimeData.COLUMN_DEPTH_DATA_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = TimeData.COLUMN_LOG_HEADER, nullable = false)
    private LogHeader header;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = TimeData.COLUMN_TIME, nullable = false)
    private Date time;

    @Column(name = TimeData.COLUMN_VALUE, nullable = false)
    private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LogHeader getHeader() {
		return header;
	}

	public void setHeader(LogHeader header) {
		this.header = header;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}