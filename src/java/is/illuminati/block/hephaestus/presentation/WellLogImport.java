package is.illuminati.block.hephaestus.presentation;

import is.illuminati.block.hephaestus.HephaestusConstants;
import is.illuminati.block.hephaestus.bean.WellLogImportBean;
import is.illuminati.block.hephaestus.business.DataPoint;
import is.illuminati.block.hephaestus.business.DepthUnit;
import is.illuminati.block.hephaestus.business.HephaestusService;
import is.illuminati.block.hephaestus.business.LoggingCondition;
import is.illuminati.block.hephaestus.business.LoggingDirection;
import is.illuminati.block.hephaestus.business.PressureUnit;
import is.illuminati.block.hephaestus.business.TemperatureUnit;
import is.illuminati.block.hephaestus.business.ToolType;
import is.illuminati.block.hephaestus.business.WellLogImportSession;
import is.illuminati.block.hephaestus.dao.HephaestusDao;
import is.illuminati.block.hephaestus.data.LogHeader;
import is.illuminati.block.hephaestus.data.Project;
import is.illuminati.block.hephaestus.data.Tool;
import is.illuminati.block.hephaestus.data.Well;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.idega.block.web2.business.JQuery;
import com.idega.block.web2.business.JQueryPlugin;
import com.idega.facelets.ui.FaceletComponent;
import com.idega.idegaweb.IWBundle;
import com.idega.io.UploadFile;
import com.idega.presentation.IWBaseComponent;
import com.idega.presentation.IWContext;
import com.idega.presentation.ui.handlers.IWDatePickerHandler;
import com.idega.util.CoreConstants;
import com.idega.util.FileUtil;
import com.idega.util.IWTimestamp;
import com.idega.util.PresentationUtil;
import com.idega.util.expression.ELUtil;

public class WellLogImport extends IWBaseComponent {
	private static final String PARAMETER_ACTION = "prm_action";
	private static final int ACTION_SELECT_FILE = 1;
	private static final int ACTION_OVERVIEW = 2;
	private static final int ACTION_DONE = 3;
	private static final int ACTION_ERROR = 4;

	@Autowired
	private HephaestusService service;

	@Autowired
	private JQuery jQuery;

	private IWBundle iwb;

	@Autowired
	private HephaestusDao dao;


	@Override
	protected void initializeComponent(FacesContext context) {
		IWContext iwc = IWContext.getIWContext(context);
		iwb = getBundle(context, getBundleIdentifier());

		PresentationUtil.addJavaScriptSourceLineToHeader(iwc, getJQuery()
				.getBundleURIToJQueryLib());
		PresentationUtil.addJavaScriptSourcesLinesToHeader(
				iwc,
				getJQuery().getBundleURISToValidation(
						iwc.getCurrentLocale().getLanguage()));
		PresentationUtil.addJavaScriptSourceLineToHeader(iwc, getJQuery()
				.getBundleURIToJQueryPlugin(JQueryPlugin.MASKED_INPUT));

		PresentationUtil.addJavaScriptSourceLineToHeader(iwc,
				CoreConstants.DWR_ENGINE_SCRIPT);
		PresentationUtil.addJavaScriptSourceLineToHeader(iwc,
				CoreConstants.DWR_UTIL_SCRIPT);
		//PresentationUtil.addJavaScriptSourceLineToHeader(iwc,
		//		"/dwr/interface/PheidippidesService.js");

		PresentationUtil
				.addJavaScriptSourceLineToHeader(
						iwc,
						iwb.getVirtualPathWithFileNameString("javascript/wellLogImport.js"));
		PresentationUtil.addStyleSheetToHeader(iwc,
				iwb.getVirtualPathWithFileNameString("style/hephaestus.css"));

		WellLogImportBean bean = getBeanInstance("wellLogImportBean");
		Project project = null;
		bean.setWells(getDao().getWells(project));
		bean.setTools(getDao().getTools());
		bean.setDepthUnits(Arrays.asList(DepthUnit.values()));
		bean.setTemperatureUnits(Arrays.asList(TemperatureUnit.values()));
		bean.setPressureUnits(Arrays.asList(PressureUnit.values()));
		bean.setConditions(Arrays.asList(LoggingCondition.values()));
		bean.setDirections(Arrays.asList(LoggingDirection.values()));
		bean.setStartDate(IWTimestamp.getTimestampRightNow());
		bean.setEndDate(IWTimestamp.getTimestampRightNow());
		
		switch (parseAction(iwc)) {
		case ACTION_SELECT_FILE:
			showImportForm(iwc);
			break;

		case ACTION_OVERVIEW:
			showOverview(iwc);
			break;

		case ACTION_ERROR:
			showError(iwc);
			break;

		case ACTION_DONE:
			showDone(iwc);
			break;

		}
	}

	private void showImportForm(IWContext iwc) {
		FaceletComponent facelet = (FaceletComponent) iwc.getApplication()
				.createComponent(FaceletComponent.COMPONENT_TYPE);
		facelet.setFaceletURI(iwb.getFaceletURI("wellLogImport/import.xhtml"));
		add(facelet);
	}

	private void showOverview(IWContext iwc) {
		WellLogImportSession session = getBeanInstance("wellLogImportSession");
		
		String wellId = iwc.getParameter("prm_well_pk");
		String conditionId = iwc.getParameter("prm_condition_pk");
		String toolId = iwc.getParameter("prm_tool_pk");
		String startDate = iwc.getParameter("prm_start_date");
		String endDate = iwc.getParameter("prm_end_date");
		String shift = iwc.getParameter("prm_shift");
		String directionId = iwc.getParameter("prm_direction_pk");
		String engineers = iwc.getParameter("prm_engineers");
		String comment = iwc.getParameter("prm_comment");
		String serviceCompany = iwc.getParameter("prm_service_company");
		String depthUnitId = iwc.getParameter("prm_depth_pk");
		String temperatureUnitId = iwc.getParameter("prm_temperature_pk");
		String pressureUnitId = iwc.getParameter("prm_pressure_pk");

		List<DataPoint> toImport = getValuesFromFile(iwc);
		if (toImport != null && !toImport.isEmpty()) {
			Well well = getDao().getWell(Long.valueOf(wellId));
			Tool tool = getDao().getTool(Long.valueOf(toolId));
			LoggingCondition condition = null;
			if (!conditionId.equals("")) {
				condition = LoggingCondition.valueOf(conditionId);
			}
			IWTimestamp start = new IWTimestamp(IWDatePickerHandler.getParsedDate(startDate));
			IWTimestamp end = new IWTimestamp(IWDatePickerHandler.getParsedDate(endDate));
			LoggingDirection direction = null;
			if (!directionId.equals("")) {
				direction = LoggingDirection.valueOf(directionId);
			}
			
			DepthUnit depthUnit = DepthUnit.meter;
			if (!depthUnitId.equals("")) {
				depthUnit = DepthUnit.valueOf(depthUnitId);
			}

			TemperatureUnit temperatureUnit = TemperatureUnit.celcius;
			if (!temperatureUnitId.equals("")) {
				temperatureUnit = TemperatureUnit.valueOf(temperatureUnitId);
			}

			PressureUnit pressureUnit = PressureUnit.bar;
			if (!pressureUnitId.equals("")) {
				pressureUnit = PressureUnit.valueOf(pressureUnitId);
			}

			if (shift == null || shift.equals("")) {
				shift = "0.0";
			}
			
			List<DataPoint> convertedDataPoint = new ArrayList<DataPoint>();
			
			for (DataPoint dataPoint : toImport) {
				Double depth = dataPoint.getX();
				Double value = dataPoint.getY();
				
				if (depthUnit == DepthUnit.feet) {
					depth = convertFeetToMeter(depth);
				}
				
				if (tool.getType() == ToolType.temperature) {
					if (temperatureUnit == TemperatureUnit.fahrenheit) {
						value = convertFahrenheitToCelcius(value);
					}
				} else if (tool.getType() == ToolType.pressure) {
					if (pressureUnit == PressureUnit.psi) {
						value = convertPSIToBar(value);
					} else if (pressureUnit == PressureUnit.pascal) {
						
					}
				}
				
				DataPoint converted = new DataPoint(depth, value);
				convertedDataPoint.add(converted);

			}
			
			session.setComment(comment);
			session.setCondition(condition);
			session.setDepthUnit(depthUnit);
			session.setDataPoint(convertedDataPoint);
			session.setDirection(direction);
			session.setEndDate(end);
			session.setEngineers(engineers);
			session.setPressureUnit(pressureUnit);
			session.setServiceCompany(serviceCompany);
			session.setShift(Double.valueOf(shift));
			session.setStartDate(start);
			session.setTemperatureUnit(temperatureUnit);
			session.setTool(tool);
			session.setWell(well);
		}
		
		FaceletComponent facelet = (FaceletComponent) iwc.getApplication()
				.createComponent(FaceletComponent.COMPONENT_TYPE);
		facelet.setFaceletURI(iwb.getFaceletURI("wellLogImport/overview.xhtml"));
		add(facelet);
	}
	
	private void showDone(IWContext iwc) {
		WellLogImportSession session = getBeanInstance("wellLogImportSession");
		
		LogHeader header = getDao().storeLogHeader(session.getWell(), session.getCondition(), session.getTool(), session.getStartDate().getDate(), 
				session.getEndDate().getDate(), session.getShift(), session.getDirection(), session.getEngineers(), session.getComment(), session.getServiceCompany(), iwc.getLoggedInUser());
		List<DataPoint> data = session.getDataPoint();
		for (DataPoint dataPoint : data) {
			getDao().storeDepthDate(header, dataPoint.getX(), dataPoint.getY());			
		}
			
		FaceletComponent facelet = (FaceletComponent) iwc.getApplication()
				.createComponent(FaceletComponent.COMPONENT_TYPE);
		facelet.setFaceletURI(iwb.getFaceletURI("wellLogImport/done.xhtml"));
		add(facelet);
	}

	private Double convertFeetToMeter(Double feet) {
		return feet / 3.281d;
	}
	
	private Double convertFahrenheitToCelcius(Double fahrenheit) {
		return (fahrenheit - 32d) * 5d / 9d;
	}
	
	private Double convertPSIToBar(Double psi) {
		return psi / 14.5037738007d;
	}
	
	private List<DataPoint> getValuesFromFile(IWContext iwc) {
		UploadFile uploadFile = iwc.getUploadedFile();
		if (uploadFile != null && uploadFile.getName() != null
				&& uploadFile.getName().length() > 0) {
			try {

				FileInputStream input = new FileInputStream(
						uploadFile.getRealPath());
				List<DataPoint> toImport = getService()
						.importExcelFile(input);

				try {
					FileUtil.delete(uploadFile);
				} catch (Exception ex) {
					System.err
							.println("MediaBusiness: deleting the temporary file at "
									+ uploadFile.getRealPath() + " failed.");
				}

				//if (hasError) {
				//	showError(iwc);
				//	return;
				//}
				
				return toImport;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				uploadFile.setId(-1);
			}
		}
		
		return null;
	}
	
	private void showError(IWContext iwc) {
		FaceletComponent facelet = (FaceletComponent) iwc.getApplication()
				.createComponent(FaceletComponent.COMPONENT_TYPE);
		facelet.setFaceletURI(iwb.getFaceletURI("wellLogImport/error.xhtml"));
		add(facelet);
	}

	private String getBundleIdentifier() {
		return HephaestusConstants.IW_BUNDLE_IDENTIFIER;
	}

	private HephaestusService getService() {
		if (service == null) {
			ELUtil.getInstance().autowire(this);
		}

		return service;
	}

	private JQuery getJQuery() {
		if (jQuery == null) {
			ELUtil.getInstance().autowire(this);
		}

		return jQuery;
	}

	private HephaestusDao getDao() {
		if (dao == null) {
			ELUtil.getInstance().autowire(this);
		}

		return dao;
	}

	private int parseAction(IWContext iwc) {
		int action = iwc.isParameterSet(PARAMETER_ACTION) ? Integer
				.parseInt(iwc.getParameter(PARAMETER_ACTION))
				: ACTION_SELECT_FILE;

		return action;
	}
}