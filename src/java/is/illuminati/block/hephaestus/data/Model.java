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
@Table(name = Model.ENTITY_NAME)
@NamedQueries({
        @NamedQuery(name = "model.findAll", query = "select m from Model m")
})
public class Model {

	public static final String ENTITY_NAME = "num_model";
    
    private static final String COLUMN_MODEL_ID = "model_id";
    private static final String COLUMN_MODEL_NAME = "model_name";
    private static final String COLUMN_PROJECT = "project_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = Model.COLUMN_MODEL_ID)
    private Long id;

    @Column(name = Model.COLUMN_MODEL_NAME, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Model.COLUMN_PROJECT)
    private Project project;

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
}