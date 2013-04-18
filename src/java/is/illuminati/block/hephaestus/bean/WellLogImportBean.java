package is.illuminati.block.hephaestus.bean;

import is.illuminati.block.hephaestus.business.DepthUnit;
import is.illuminati.block.hephaestus.business.LoggingCondition;
import is.illuminati.block.hephaestus.business.LoggingDirection;
import is.illuminati.block.hephaestus.business.PressureUnit;
import is.illuminati.block.hephaestus.business.TemperatureUnit;
import is.illuminati.block.hephaestus.data.Tool;
import is.illuminati.block.hephaestus.data.Well;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("wellLogImportBean")
@Scope("request")
public class WellLogImportBean {
	
	private Well well;
	private List<Well> wells;
	private List<Tool> tools;
	private List<LoggingCondition> conditions;
	private Date startDate;
	private Date endDate;
	private Double shift;
	private List<LoggingDirection> directions;
	private String engineers;
	private String comment;
	private String serviceCompany;
	
	private List<DepthUnit> depthUnits;
	private List<TemperatureUnit> temperatureUnits;
	private List<PressureUnit> pressureUnits;
	
	private DepthUnit defaultDepthUnit;
	private TemperatureUnit defaultTemperatureUnit;
	private PressureUnit defaultPressureUnit;
	
	public Well getWell() {
		return well;
	}
	
	public void setWell(Well well) {
		this.well = well;
	}
	
	public List<Well> getWells() {
		return wells;
	}

	public void setWells(List<Well> wells) {
		this.wells = wells;
	}

	public List<Tool> getTools() {
		return tools;
	}

	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}

	public List<LoggingCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<LoggingCondition> conditions) {
		this.conditions = conditions;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getShift() {
		return shift;
	}

	public void setShift(Double shift) {
		this.shift = shift;
	}

	public List<LoggingDirection> getDirections() {
		return directions;
	}

	public void setDirections(List<LoggingDirection> directions) {
		this.directions = directions;
	}

	public String getEngineers() {
		return engineers;
	}

	public void setEngineers(String engineers) {
		this.engineers = engineers;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getServiceCompany() {
		return serviceCompany;
	}

	public void setServiceCompany(String serviceCompany) {
		this.serviceCompany = serviceCompany;
	}

	public List<DepthUnit> getDepthUnits() {
		return depthUnits;
	}

	public void setDepthUnits(List<DepthUnit> depthUnits) {
		this.depthUnits = depthUnits;
	}

	public List<TemperatureUnit> getTemperatureUnits() {
		return temperatureUnits;
	}

	public void setTemperatureUnits(List<TemperatureUnit> temperatureUnits) {
		this.temperatureUnits = temperatureUnits;
	}

	public List<PressureUnit> getPressureUnits() {
		return pressureUnits;
	}

	public void setPressureUnits(List<PressureUnit> pressureUnits) {
		this.pressureUnits = pressureUnits;
	}

	public DepthUnit getDefaultDepthUnit() {
		return defaultDepthUnit;
	}

	public void setDefaultDepthUnit(DepthUnit defaultDepthUnit) {
		this.defaultDepthUnit = defaultDepthUnit;
	}

	public TemperatureUnit getDefaultTemperatureUnit() {
		return defaultTemperatureUnit;
	}

	public void setDefaultTemperatureUnit(TemperatureUnit defaultTemperatureUnit) {
		this.defaultTemperatureUnit = defaultTemperatureUnit;
	}

	public PressureUnit getDefaultPressureUnit() {
		return defaultPressureUnit;
	}

	public void setDefaultPressureUnit(PressureUnit defaultPressureUnit) {
		this.defaultPressureUnit = defaultPressureUnit;
	}

}
