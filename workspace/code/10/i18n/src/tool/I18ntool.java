package tool;

import java.util.*;

public class I18ntool {
	public static String getString(String key, String bundleFile, String locale) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(bundleFile,
					new Locale(locale));
			return bundle.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
