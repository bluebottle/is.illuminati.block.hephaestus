package is.illuminati.block.hephaestus.bean;

import is.illuminati.block.hephaestus.data.Tool;
import is.illuminati.block.hephaestus.data.Well;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("wellLogImportBean")
@Scope("request")
public class WellLogImportBean {
	private List<Well> wells;
	private List<Tool> tools;

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

}
