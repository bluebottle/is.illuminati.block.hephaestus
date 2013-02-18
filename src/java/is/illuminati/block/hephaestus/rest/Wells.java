package is.illuminati.block.hephaestus.rest;

import is.illuminati.block.hephaestus.business.CoordinateConversion;
import is.illuminati.block.hephaestus.dao.HephaestusDao;
import is.illuminati.block.hephaestus.data.Project;
import is.illuminati.block.hephaestus.rest.pojo.Well;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.idega.util.expression.ELUtil;

@Path("/wells/")
public class Wells {

	@Autowired
	private HephaestusDao dao;
	
	@GET
	@Path("/{project}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Well> getWellsByProject(@PathParam("project") Long projectId) {
		Project project = getDao().getProject(projectId);
		List<is.illuminati.block.hephaestus.data.Well> wells = getDao().getWells(project);
		
		CoordinateConversion conversion = new CoordinateConversion();
		
		List<Well> pojoWells = new ArrayList<Well>();
		for (is.illuminati.block.hephaestus.data.Well well : wells) {
			Well pojo = new Well();
			pojo.setId(well.getId());
			pojo.setName(well.getName());
			
			double[] latLong = conversion.utm2LatLon(well.getX(), well.getY(), well.getZone(), well.getLatzone());
			pojo.setLatitude(String.valueOf(latLong[0]));
			pojo.setLongitude(String.valueOf(latLong[1]));
			
			pojoWells.add(pojo);
		}
		
		return pojoWells;
	}

	private HephaestusDao getDao() {
		if (dao == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return dao;
	}
}