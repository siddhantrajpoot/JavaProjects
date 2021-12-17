package jsonToDatabase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import constants.GetDirectory;
import constants.MyConstants;
import mydrivers.MyConnection;

public class JsonDataInMySql {

	public static void insertJsonDataInSQL(String dbName, String tableName)
			throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

		for (String FILE_NAME : GetDirectory.getAllFileName(MyConstants.jsonLocation)) {

			if ("upd_app_exam_pub_art.json".equalsIgnoreCase(FILE_NAME)) {

				// System.out.println("file found");

				Path path = Paths.get(MyConstants.jsonLocation + "\\" + FILE_NAME);

				try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

					JsonParser parser = new JsonParser();
					JsonElement elementInJsonFile = parser.parse(reader);

					JsonArray array = elementInJsonFile.getAsJsonArray();

					for (JsonElement element : array) {

						if (element.isJsonObject()) {

							JsonObject record = element.getAsJsonObject();

							Connection con = MyConnection.connectJdbc(dbName);

							if (con != null) {

								PreparedStatement pstmt = con
										.prepareStatement("INSERT INTO " + tableName + " values (?, ?, ?, ?)");
								long appl_num = record.get("appl_num").getAsLong();
								long exam_pub_num = record.get("exam_pub_num").getAsLong();
								String exam_pub_dt = record.get("exam_pub_dt").getAsString();
								String updt_dttm = record.get("updt_dttm").getAsString();
								pstmt.setLong(1, appl_num);
								pstmt.setLong(2, exam_pub_num);
								pstmt.setString(3, exam_pub_dt);
								pstmt.setString(4, updt_dttm);
								pstmt.executeUpdate();
							}

						}
					}
					System.out.println("Records inserted.....");
					System.out.println(".........................");
				}

			}

		}

	}

}
