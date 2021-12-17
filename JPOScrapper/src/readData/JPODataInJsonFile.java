package readData;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import constants.GetDirectory;
import constants.MyConstants;

public class JPODataInJsonFile {

	public static void createJsonDoc() throws ParseException, IOException {

		List<JPOData> jpListObj = ReadTSV.parseTsv();
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String jplist = gson.toJson(jpListObj);

		for (String FILE_NAME : GetDirectory.getAllFileName(MyConstants.unTarLocation)) {
			String fileName = MyConstants.unTarLocation + "\\" + FILE_NAME + "\\JPWAE\\";
			for (String fileUnderfile : GetDirectory.getAllFileName(fileName)) {
				if ("upd_app_exam_pub_art.tsv".equalsIgnoreCase(fileUnderfile)) {
					int a = fileUnderfile.lastIndexOf(".");
					String fname = fileUnderfile.substring(0, a);
					String f = fname + ".json";
					FileWriter file = new FileWriter(MyConstants.jsonLocation + "/" + f);
					file.write(jplist);
					file.close();
				}
			}
		}
	}
}