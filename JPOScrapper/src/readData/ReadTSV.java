package readData;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import constants.GetDirectory;
import constants.MyConstants;

public class ReadTSV {

	public static List<JPOData> parseTsv() throws ParseException {

		List<JPOData> jpListObj = new ArrayList<JPOData>();

		TsvParserSettings settings = new TsvParserSettings();
		settings.selectFields("appl_num", "exam_pub_num", "exam_pub_dt", "updt_dttm");
		TsvParser parser = new TsvParser(settings);

		for (String FILE_NAME : GetDirectory.getAllFileName(MyConstants.unTarLocation)) {
			String fileName = MyConstants.unTarLocation + "\\" + FILE_NAME + "\\JPWAE\\";
			for (String fileUnderfile : GetDirectory.getAllFileName(fileName)) {
				if ("upd_app_exam_pub_art.tsv".equalsIgnoreCase(fileUnderfile)) {
					List<String[]> allRows = parser.parseAll(new File(fileName + "\\" + fileUnderfile));
					for (int i = 1; i < allRows.size(); i++) {

						JPOData jpdata = new JPOData();

						List<String> currentrow = Arrays.asList(allRows.get(i));

						try {
							jpdata.appl_num = Long.parseLong(currentrow.get(0));
						} catch (Exception e) {
							jpdata.appl_num = 0;
						}

						try {
							jpdata.exam_pub_num = Long.parseLong(currentrow.get(1));
						} catch (Exception e) {
							jpdata.exam_pub_num = 0;
						}

						try {
							jpdata.exam_pub_dt = StringDateConvertion.getDate(currentrow.get(2));
						} catch (Exception e) {
							jpdata.exam_pub_dt = " ";
						}

						try {
							jpdata.updt_dttm = StringDateConvertion.getDateTime(currentrow.get(3));
						} catch (Exception e) {
							jpdata.updt_dttm = " ";
						}

						jpListObj.add(jpdata);

					}
				}

			}

		}

		return jpListObj;
	}

}
