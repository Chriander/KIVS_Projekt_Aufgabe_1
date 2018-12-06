package kivs_Package;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.Desktop;

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

		/*
		try {
			String home = System.getProperty("user.home");
			Files.copy(new URL(this.url).openStream(), Paths.get(home + "/PA.log"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/

		/*
		 * if (Desktop.isDesktopSupported()) {
		 * 
		 * try {
		 * 
		 * // opens page in native browser
		 * 
		 * Desktop d = Desktop.getDesktop(); d.browse(new URI(this.url));
		 * this.siteAccesed = true;
		 * 
		 * 
		 * } catch (IOException e) { System.out.println("You have an IOExpception."); }
		 * catch (URISyntaxException e) {
		 * System.out.println("You have an URI SyntaxExeption."); } } else {
		 * System.out.println("The Desktop function is not supported on this device.");
		 * 
		 * // not necessary this.siteAccesed = false;
		 * 
		 * }
		 */
	}

}