package is.illuminati.block.hephaestus.servlet;

import is.illuminati.block.hephaestus.business.HephaestusService;
import is.illuminati.block.hephaestus.data.LogHeader;
import is.illuminati.block.hephaestus.data.Well;
import is.illuminati.block.hephaestus.util.HephaestusUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.idega.presentation.IWContext;

public class PlotGraphServlet extends HttpServlet {

	private static final long serialVersionUID = -3076346145084631921L;

	private static final String PARAMETER_WELL_PK = "prm_well_pk";
	private static final String PARAMETER_MEASUREMENT_TYPE = "prm_measurement_type";
	private static final String PARAMETER_LOG_PK = "prm_log_pk";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IWContext iwc = new IWContext(req, resp, req.getSession().getServletContext());
		if (iwc.isParameterSet(PARAMETER_WELL_PK)) {
			resp.setStatus(200);
			resp.setContentType("image/png");
			
			WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(iwc.getServletContext());
			HephaestusService service = (HephaestusService) springContext.getBean("hephaestusService");

			Well well = service.getWell(Long.parseLong(iwc.getParameter(PARAMETER_WELL_PK)));
			List<LogHeader> headers = new ArrayList<LogHeader>();
			if (iwc.isParameterSet(PARAMETER_LOG_PK)) {
				String[] logPKs = iwc.getParameterValues(PARAMETER_LOG_PK);
				for (String pk : logPKs) {
					headers.add(service.getLogHeader(Long.parseLong(pk)));
				}
			}
			
			String measurementType = iwc.getParameter(PARAMETER_MEASUREMENT_TYPE);
			
			String imageURL = HephaestusUtil.getImageForWell(well, headers, measurementType);

			File f = new File(imageURL);
			BufferedImage bi = ImageIO.read(f);
			OutputStream out = resp.getOutputStream();
			ImageIO.write(bi, "png", out);
			out.close();
			
		}
		else {
			resp.setStatus(404);
		}
	}
}