package kivs_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Logfile {

	//logfile objects consist only of a path
	
	String file_path;

	//constructor
	public Logfile(String path_of_file) {

		this.file_path = path_of_file;

	}

	public void file_reader_and_parser() {

		try {
			
			FileInputStream file = new FileInputStream(this.file_path);

			BufferedReader br = new BufferedReader(new InputStreamReader(file));

			String strLine;
			while ((strLine = br.readLine()) != null) {

				System.out.println(strLine);
			}
			file.close();

		} catch (IOException e) {

			System.out.println("problem with buffer.");
		}

	}

}
