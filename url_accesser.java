package kivs_Package;

import java.awt.GridBagLayout;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class url_accesser {

	String url;

	// method creates new Url (Constructor)
	public url_accesser(String url_name) {

		url = url_name;
	}

	// method opens URL and show you what url you are accessing

	public void URL_opener() {

		if (Desktop.isDesktopSupported()) {
		
			try {

				// opens page in native browser

				Desktop d = Desktop.getDesktop();
				d.browse(new URI(this.url));

				// creates window with Information about URL
				JFrame url_win = new JFrame();
				url_win.setLocation(650, 0);
				url_win.setSize(600, 400);
				url_win.setLayout(new GridBagLayout());
				JLabel message_url_worked = new JLabel("You have accessed " + this.url);
				url_win.add(message_url_worked);
				url_win.setVisible(true);

				TimeUnit.SECONDS.sleep(5);
				url_win.dispose();

			} catch (InterruptedException e1) {
				System.out.println("There was a Problem with the URL displayer.");

			} catch (IOException e) {
				System.out.println("You have an IOExpception.");
			} catch (URISyntaxException e) {
				System.out.println("You have an URI SyntaxExeption.");
			}
		}
		else {
			System.out.println("The Desktop function is not supported on this device.");
		}

	}

}