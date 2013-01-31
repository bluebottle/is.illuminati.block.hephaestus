package is.illuminati.block.hephaestus.dao.impl;

import is.illuminati.block.hephaestus.dao.HephaestusDao;
import is.illuminati.block.hephaestus.data.LogHeader;
import is.illuminati.block.hephaestus.data.Pad;
import is.illuminati.block.hephaestus.data.Project;
import is.illuminati.block.hephaestus.data.Well;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.idega.core.persistence.Param;
import com.idega.core.persistence.impl.GenericDaoImpl;
import com.idega.user.data.bean.Group;
import com.idega.user.data.bean.User;
import com.idega.util.IWTimestamp;

@Repository("hephaestusDao")
@Transactional(readOnly = true)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class HephaestusDaoImpl extends GenericDaoImpl implements HephaestusDao {
	/* Project methods */
	public Project getProject(Long projectID) {
		return find(Project.class, projectID);
	}

	public Project getProject(String name) {
		return getSingleResult("project.findByName", Project.class, new Param(
				"name", name));
	}

	public List<Project> getProject() {
		return getResultList("project.findAll", Project.class);
	}

	public List<Project> getProjects(Group owner) {
		Param param1 = new Param("owner", owner);
		return getResultList("project.findByOwner", Project.class, param1);
	}
	
	@Transactional(readOnly = false)
	public Project storeProject(Long projectID, String name, String address,
			String remarks, Group owner, User createdBy) {
		Project project = projectID != null ? getProject(projectID) : null;
		if (project == null) {
			project = new Project();
			project.setCreatedDate(IWTimestamp.getTimestampRightNow());
		}

		project.setName(name);
		project.setAddress(address);
		project.setRemarks(remarks);
		project.setOwner(owner);
		project.setCreatedBy(createdBy);

		getEntityManager().persist(project);

		return project;
	}

	@Transactional(readOnly = false)
	public boolean removeProject(Long projectID) {
		Project project = getProject(projectID);
		if (project != null) {
			getEntityManager().remove(project);
			return true;
		}

		return false;
	}

	/* Pad methods */
	public Pad getPad(Long padID) {
		return find(Pad.class, padID);
	}

	public Pad getPad(String name) {
		return getSingleResult("pad.findByName", Pad.class, new Param("name",
				name));
	}

	public List<Pad> getPads(Project project) {
		if (project != null)
			return getResultList("pad.findByProject", Pad.class, new Param("project", project));
		
		return getResultList("pad.findAll", Pad.class);
	}

	@Transactional(readOnly = false)
	public Pad storePad(Long padID, String name, Project project,
			String remarks, User createdBy) {
		Pad pad = padID != null ? getPad(padID) : null;
		if (pad == null) {
			pad = new Pad();
			pad.setCreatedDate(IWTimestamp.getTimestampRightNow());
		}

		pad.setName(name);
		pad.setProject(project);
		pad.setRemarks(remarks);
		pad.setCreatedBy(createdBy);

		getEntityManager().persist(pad);

		return pad;
	}

	@Transactional(readOnly = false)
	public boolean removePad(Long padID) {
		Pad pad = getPad(padID);
		if (pad != null) {
			getEntityManager().remove(pad);
			return true;
		}

		return false;
	}

	/* Well methods */
	public Well getWell(Long wellID) {
		return find(Well.class, wellID);
	}

	public Well getWell(String name) {
		return getSingleResult("well.findByName", Well.class, new Param("name",
				name));
	}

	public List<Well> getWells(Pad pad) {
		if (pad != null)
			return getResultList("well.findByPad", Well.class, new Param("pad", pad));
		
		return getResultList("well.findAll", Well.class);
	}

	@Transactional(readOnly = false)
	public Well storeWell(Long wellID, String name, Pad pad, Double depth,
			Double gis_x, Double gis_y, Double gis_z, User createdBy) {
		Well well = wellID != null ? getWell(wellID) : null;
		if (well == null) {
			well = new Well();
			well.setCreatedDate(IWTimestamp.getTimestampRightNow());
		}

		well.setName(name);
		well.setPad(pad);
		well.setDrilledDepth(depth);
		well.setX(gis_x);
		well.setY(gis_y);
		well.setZ(gis_z);
		well.setCreatedBy(createdBy);

		getEntityManager().persist(well);

		return well;
	}

	@Transactional(readOnly = false)
	public boolean removeWell(Long wellID) {
		Well well = getWell(wellID);
		if (well != null) {
			getEntityManager().remove(well);
			return true;
		}

		return false;
	}

	public List<LogHeader> getLogHeaders(Well well) {
		return getResultList("logHeader.findAllByWell", LogHeader.class, new Param("well", well));
	}
}