package readData;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class StringDateConvertion {

	public static String getDate(String str) {

		int year = Integer.parseInt(str.substring(0, 4));
		int month = Integer.parseInt(str.substring(4, 6));
		int dt = Integer.parseInt(str.substring(6, 8));

		LocalDate myDate = LocalDate.of(year, month, dt);
		Date date = Date.from(myDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		return strDate;
	}

	public static String getDateTime(String str) {

		int hh = Integer.parseInt(str.substring(8, 10));
		int minute = Integer.parseInt(str.substring(10, 12));
		int year = Integer.parseInt(str.substring(0, 4));
		int month = Integer.parseInt(str.substring(4, 6));
		int dt = Integer.parseInt(str.substring(6, 8));

		LocalDateTime my = LocalDateTime.of(year, month, dt, hh, minute);
		Date date = Date.from(my.atZone(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String strDate = formatter.format(date);
		return strDate;

	}

}
