package is.illuminati.block.hephaestus.dao;


import is.illuminati.block.hephaestus.data.Pad;
import is.illuminati.block.hephaestus.data.Project;
import is.illuminati.block.hephaestus.data.Well;

import java.util.List;

import com.idega.core.persistence.GenericDao;
import com.idega.user.data.bean.Group;
import com.idega.user.data.bean.User;

public interface HephaestusDao extends GenericDao {
	public Project getProject(Long projectID);

	public Project getProject(String name);

	public List<Project> getProject();
	
	public Project storeProject(Long projectID, String name, String address, String remarks,
			Group owner, User createdBy);

	public boolean removeProject(Long projectID);
	
	public Pad getPad(Long padID);
	
	public Pad getPad(String name);
	
	public List<Pad> getPads(Project project);
	
	public Pad storePad(Long padID, String name, Project project, String remarks,
			User createdBy);
	
	public boolean removePad(Long padID);
	
	public Well getWell(Long wellID);
	
	public Well getWell(String name);
	
	public List<Well> getWells(Pad pad);
	
	public Well storeWell(Long wellID, String name, Pad pad, Double depth,
			Double gis_x, Double gis_y, Double gis_z, User createdBy);
	
	public boolean removeWell(Long wellID);
	
}