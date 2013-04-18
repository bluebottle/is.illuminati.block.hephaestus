package is.illuminati.block.hephaestus.dao;


import is.illuminati.block.hephaestus.business.LoggingCondition;
import is.illuminati.block.hephaestus.business.LoggingDirection;
import is.illuminati.block.hephaestus.data.DepthData;
import is.illuminati.block.hephaestus.data.LogHeader;
import is.illuminati.block.hephaestus.data.Pad;
import is.illuminati.block.hephaestus.data.Project;
import is.illuminati.block.hephaestus.data.Tool;
import is.illuminati.block.hephaestus.data.Well;

import java.util.Date;
import java.util.List;

import com.idega.core.persistence.GenericDao;
import com.idega.user.data.bean.Group;
import com.idega.user.data.bean.User;

public interface HephaestusDao extends GenericDao {
	public Project getProject(Long projectID);

	public Project getProject(String name);

	public List<Project> getProject();

	public List<Project> getProjects(Group owner);

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
	
	public List<Well> getWells(Project project);

	public Well storeWell(Long wellID, String name, Pad pad, Double depth,
			Double gis_x, Double gis_y, Double gis_z, User createdBy);
	
	public boolean removeWell(Long wellID);
	
	public LogHeader getLogHeader(Long logHeaderID);
	public List<LogHeader> getLogHeaders(Well well);
	
	public List<Tool> getTools();
	
	public Tool getTool(Long toolID);
	
	public LogHeader storeLogHeader(Well well, LoggingCondition condition,
			Tool tool, Date startDate, Date endDate, Double shift,
			LoggingDirection logDirection, String engineers, String comment, String serviceCompany, User createdBy);
	
	public DepthData storeDepthDate(LogHeader header, Double depth, Double value);
}