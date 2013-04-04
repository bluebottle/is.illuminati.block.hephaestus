package is.illuminati.block.hephaestus.business;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("hephaestusService")
public class HephaestusService {
	public List<DataPoint> importExcelFile(FileInputStream input) {
		List<DataPoint> ret = new ArrayList<DataPoint>();

		try {
			HSSFWorkbook wb = new HSSFWorkbook(input);
			HSSFSheet sheet = wb.getSheetAt(0);

			for (int a = sheet.getFirstRowNum() + 1; a <= sheet.getLastRowNum(); a++) {
				HSSFRow row = sheet.getRow(a);

				int column = 0;
				Double depth = getCellValue(row.getCell(column++));
				Double value = getCellValue(row.getCell(column++));

				DataPoint point = new DataPoint(depth, value);
				ret.add(point);
			}

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;	
	}
	
	private Double getCellValue(HSSFCell cell) {
		if (cell == null) {
			return null;
		}

		Double value = null;
		if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				value = new Double(cell.getNumericCellValue());
		} else {
			value = null;
		}

		return value;
	}
}