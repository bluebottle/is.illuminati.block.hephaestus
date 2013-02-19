package is.illuminati.block.hephaestus.presentation;

import is.illuminati.block.hephaestus.HephaestusConstants;
import is.illuminati.block.hephaestus.bean.WellLogImportBean;
import is.illuminati.block.hephaestus.business.HephaestusService;
import is.illuminati.block.hephaestus.dao.HephaestusDao;
import is.illuminati.block.hephaestus.data.Project;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.idega.block.web2.business.JQuery;
import com.idega.block.web2.business.JQueryPlugin;
import com.idega.facelets.ui.FaceletComponent;
import com.idega.idegaweb.IWBundle;
import com.idega.presentation.IWBaseComponent;
import com.idega.presentation.IWContext;
import com.idega.util.CoreConstants;
import com.idega.util.PresentationUtil;
import com.idega.util.expression.ELUtil;

public class WellLogImport extends IWBaseComponent {
	private static final String PARAMETER_ACTION = "prm_action";
	private static final int ACTION_SELECT_FILE = 1;
	private static final int ACTION_DONE = 2;
	private static final int ACTION_ERROR = 3;

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
		//bean.setTools(getDao().get)

		switch (parseAction(iwc)) {
		case ACTION_SELECT_FILE:
			showImportForm(iwc);
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

	private void showDone(IWContext iwc) {
		/*CompanyImportSession session = getBeanInstance("companyImportSession");
		PheidippidesCompanyBean bean = getBeanInstance("pheidippidesCompanyBean");
		String races[] = iwc.getParameterValues("prm_race_pk");
		String shirts[] = iwc.getParameterValues("prm_shirt_size");

		List<Participant> participants = session.getParticipantsToImport();
		List<ParticipantHolder> holders = new ArrayList<ParticipantHolder>();
		int counter = 0;
		for (Participant participant : participants) {
			ParticipantHolder holder = new ParticipantHolder();
			holder.setParticipant(participant);
			holder.setRace(dao.getRace(Long.parseLong(races[counter])));
			holder.setShirtSize(dao.getShirtSize(Long.parseLong(shirts[counter++])));

			holders.add(holder);
		}

		if (!holders.isEmpty()) {
			getService().storeCompanyRegistration(holders, bean.getCompany(), iwc.getCurrentUser().getUniqueId(), iwc.getCurrentLocale());
		}

		FaceletComponent facelet = (FaceletComponent) iwc.getApplication()
				.createComponent(FaceletComponent.COMPONENT_TYPE);
		facelet.setFaceletURI(iwb.getFaceletURI("companyImporter/done.xhtml"));
		add(facelet);*/
	}

	private void showError(IWContext iwc) {
		FaceletComponent facelet = (FaceletComponent) iwc.getApplication()
				.createComponent(FaceletComponent.COMPONENT_TYPE);
		facelet.setFaceletURI(iwb.getFaceletURI("companyImporter/error.xhtml"));
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