package is.illuminati.block.hephaestus.data;

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
@Table(name = ModelLayer.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "modelLayer.findAll", query = "select m from ModelLayer m")
})
public class ModelLayer {

	public static final String ENTITY_NAME = "num_model_layer";
    
    private static final String COLUMN_LAYER_ID = "layer_id";
    private static final String COLUMN_LAYER_NAME = "name";
    private static final String COLUMN_LAYER_ELEVATION = "elevation";
    private static final String COLUMN_LAYER_THICKNESS = "thickness";
    private static final String COLUMN_MODEL = "model_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ModelLayer.COLUMN_LAYER_ID)
    private Long id;

    @Column(name = ModelLayer.COLUMN_LAYER_NAME, nullable = false)
    private String name;

    @Column(name = ModelLayer.COLUMN_LAYER_ELEVATION, nullable = false)
    private Double elevation;

    @Column(name = ModelLayer.COLUMN_LAYER_THICKNESS, nullable = false)
    private Double thickness;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ModelLayer.COLUMN_MODEL)
    private Model model;

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
	
	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}
	
	public double getThickness() {
		return thickness;
	}

	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
