package kivs_Package;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class url_accesser {

	String url;
	boolean siteAccesed;

	// method creates new Url (Constructor)
	public url_accesser(String url_name) {

		url = url_name;
		siteAccesed = false;
	}

	// method opens URL and show you what url you are accessing

	public void URL_opener() {

		try {
			String home = System.getProperty("user.home");
			Files.copy(new URL(this.url).openStream(), Paths.get(home + File.separator + "PA.log"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}