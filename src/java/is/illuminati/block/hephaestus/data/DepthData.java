package is.illuminati.block.hephaestus.data;

import java.io.Serializable;

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

@Entity
@Table(name = DepthData.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "depthData.findAll", query = "select d from DepthData d")
})
public class DepthData implements Serializable {
	private static final long serialVersionUID = -6945322409844027077L;

	public static final String ENTITY_NAME = "hep_ddata";
    
    private static final String COLUMN_DEPTH_DATA_ID = "ddata_id";
    private static final String COLUMN_LOG_HEADER = "logf";
    private static final String COLUMN_DEPTH = "depth";
    private static final String COLUMN_VALUE = "value";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DepthData.COLUMN_DEPTH_DATA_ID)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DepthData.COLUMN_LOG_HEADER)
    private Well well;

    @Column(name = DepthData.COLUMN_DEPTH)
    private Double depth;

    @Column(name = DepthData.COLUMN_VALUE)
    private Double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Well getWell() {
		return well;
	}

	public void setWell(Well well) {
		this.well = well;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}