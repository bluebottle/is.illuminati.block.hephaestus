package is.illuminati.block.hephaestus.business;

import is.illuminati.block.hephaestus.data.Tool;
import is.illuminati.block.hephaestus.data.Well;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.util.IWTimestamp;

@Scope("session")
@Service("wellLogImportSession")
public class WellLogImportSession {
	private Well well;
	private Tool tool;
	private LoggingCondition condition;
	private IWTimestamp startDate;
	private IWTimestamp endDate;
	private Double shift;
	private LoggingDirection direction;
	private String engineers;
	private String comment;
	private String serviceCompany;
	
	private DepthUnit depthUnit;
	private TemperatureUnit temperatureUnit;
	private PressureUnit pressureUnit;
	
	private List<DataPoint> dataPoint;
	
	public Well getWell() {
		return well;
	}
	public void setWell(Well well) {
		this.well = well;
	}
	public Tool getTool() {
		return tool;
	}
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	public LoggingCondition getCondition() {
		return condition;
	}
	public void setCondition(LoggingCondition condition) {
		this.condition = condition;
	}
	public IWTimestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(IWTimestamp startDate) {
		this.startDate = startDate;
	}
	public IWTimestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(IWTimestamp endDate) {
		this.endDate = endDate;
	}
	public Double getShift() {
		return shift;
	}
	public void setShift(Double shift) {
		this.shift = shift;
	}
	public LoggingDirection getDirection() {
		return direction;
	}
	public void setDirection(LoggingDirection direction) {
		this.direction = direction;
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
	public DepthUnit getDepthUnit() {
		return depthUnit;
	}
	public void setDepthUnit(DepthUnit depthUnit) {
		this.depthUnit = depthUnit;
	}
	public TemperatureUnit getTemperatureUnit() {
		return temperatureUnit;
	}
	public void setTemperatureUnit(TemperatureUnit temperatureUnit) {
		this.temperatureUnit = temperatureUnit;
	}
	public PressureUnit getPressureUnit() {
		return pressureUnit;
	}
	public void setPressureUnit(PressureUnit pressureUnit) {
		this.pressureUnit = pressureUnit;
	}
	public String getServiceCompany() {
		return serviceCompany;
	}
	public void setServiceCompany(String serviceCompany) {
		this.serviceCompany = serviceCompany;
	}
	public List<DataPoint> getDataPoint() {
		return dataPoint;
	}
	public void setDataPoint(List<DataPoint> dataPoint) {
		this.dataPoint = dataPoint;
	}

}
