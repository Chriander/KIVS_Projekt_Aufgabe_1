package kivs_Package;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
			
			Files.copy(new URL(this.url).openStream(), Paths.get("/builds/kivs_ws1819/gruppe_28/"+ "PA.log"),StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}