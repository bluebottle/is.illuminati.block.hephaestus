package is.illuminati.block.hephaestus.presentation;

import is.illuminati.block.hephaestus.HephaestusConstants;
import is.illuminati.block.hephaestus.business.HephaestusBean;
import is.illuminati.block.hephaestus.dao.HephaestusDao;
import is.illuminati.block.hephaestus.data.Pad;
import is.illuminati.block.hephaestus.data.Project;

import java.rmi.RemoteException;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.idega.builder.business.BuilderLogicWrapper;
import com.idega.business.IBORuntimeException;
import com.idega.core.builder.data.ICPage;
import com.idega.facelets.ui.FaceletComponent;
import com.idega.idegaweb.IWBundle;
import com.idega.presentation.IWBaseComponent;
import com.idega.presentation.IWContext;
import com.idega.util.PresentationUtil;
import com.idega.util.expression.ELUtil;

public class PadsList extends IWBaseComponent {

	private static final String PARAMETER_PROJECT_ID = "prm_project_id";
	
	@Autowired
	private HephaestusDao dao;
	
	@Autowired
	private BuilderLogicWrapper builderLogicWrapper;

	private IWBundle iwb;
	
	private ICPage responsePage;
	
	protected void initializeComponent(FacesContext context) {
		IWContext iwc = IWContext.getIWContext(context);
		iwb = getBundle(context, getBundleIdentifier());
		
		if (iwc.isLoggedOn() && iwc.isParameterSet(PARAMETER_PROJECT_ID)) {
			PresentationUtil.addStyleSheetToHeader(iwc, iwb.getVirtualPathWithFileNameString("style/hephaestus.css"));

			Long projectId = Long.parseLong(iwc.getParameter(PARAMETER_PROJECT_ID));
			Project project = getDao().getProject(projectId);
			
			List<Pad> pads = getDao().getPads(project);
			
			HephaestusBean bean = getBeanInstance("hephaestusBean");
			bean.setPads(pads);
			
			if (responsePage != null) {
				try {
					bean.setResponseURL(getBuilderLogicWrapper().getBuilderService(iwc).getPageURI(responsePage));
				}
				catch (RemoteException re) {
					throw new IBORuntimeException(re);
				}
			}
			
			FaceletComponent facelet = (FaceletComponent) iwc.getApplication().createComponent(FaceletComponent.COMPONENT_TYPE);
			facelet.setFaceletURI(iwb.getFaceletURI("pads/list.xhtml"));
			add(facelet);
		}
	}
	
	private String getBundleIdentifier() {
		return HephaestusConstants.IW_BUNDLE_IDENTIFIER;
	}
	
	private HephaestusDao getDao() {
		if (dao == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return dao;
	}
	
	private BuilderLogicWrapper getBuilderLogicWrapper() {
		if (builderLogicWrapper == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return builderLogicWrapper;
	}
	
	public void setResponsePage(ICPage page) {
		this.responsePage = page;
	}
}