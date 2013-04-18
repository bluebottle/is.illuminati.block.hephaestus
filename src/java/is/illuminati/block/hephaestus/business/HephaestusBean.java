package is.illuminati.block.hephaestus.business;

import is.illuminati.block.hephaestus.data.LogHeader;
import is.illuminati.block.hephaestus.data.Pad;
import is.illuminati.block.hephaestus.data.Project;
import is.illuminati.block.hephaestus.data.Well;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("hephaestusBean")
@Scope("request")
public class HephaestusBean {

	private String responseURL;
	
	private Long projectId;
	private Long padId;
	
	private Well well;
	
	private List<Project> projects;
	private List<Pad> pads;
	private List<Well> wells;
	private List<LogHeader> logs;

	public String getResponseURL() {
		return responseURL;
	}

	public void setResponseURL(String responseURL) {
		this.responseURL = responseURL;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getPadId() {
		return padId;
	}

	public void setPadId(Long padId) {
		this.padId = padId;
	}

	public Well getWell() {
		return well;
	}

	public void setWell(Well well) {
		this.well = well;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Pad> getPads() {
		return pads;
	}

	public void setPads(List<Pad> pads) {
		this.pads = pads;
	}

	public List<Well> getWells() {
		return wells;
	}

	public void setWells(List<Well> wells) {
		this.wells = wells;
	}

	public List<LogHeader> getLogs() {
		return logs;
	}

	public void setLogs(List<LogHeader> logs) {
		this.logs = logs;
	}
}