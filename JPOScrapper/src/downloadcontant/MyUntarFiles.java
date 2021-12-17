package downloadcontant;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiverFactory;

import constants.GetDirectory;
import constants.MyConstants;

public class MyUntarFiles {

	public static void untarFiles(String tarFilePath) {

		for (String FILE_NAME : GetDirectory.getAllFileName(tarFilePath)) {

			File archive = new File(MyConstants.downloadLocation + "/" + FILE_NAME);

			File directory = new File(MyConstants.unTarLocation + "/" + FILE_NAME);

			File destination = directory;

			List<String> getAllFilesInUntarDirectory = Arrays
					.asList(GetDirectory.getAllFileName(MyConstants.unTarLocation));

			if (getAllFilesInUntarDirectory.contains(FILE_NAME)) {
			//	System.out.println("File already untar!!!");
			} else {
				if (!directory.exists()) {
					directory.mkdir();

				}
				Archiver archiver = ArchiverFactory.createArchiver("tar", "gz");
				try {
					archiver.extract(archive, destination);
					System.out.println("UnTar done.....");
				} catch (IOException e) {

					// e.printStackTrace();
					System.out.println("file not present in your directory");
				}
			}
		}
	}

}
