package kivs_Package;


import java.io.File;


public class main {

	public static void main(String[] args) {
		
		String test = ("http://rogue-01.cs.uni-bonn.de/PA.log");
		url_accesser test2 = new url_accesser(test);
		test2.URL_opener();
		
		
		String path = (File.separator + "Users" + File.separator+ "Coon"+ File.separator+"Downloads"+File.separator+"PA.log");
		
		Logfile test3 = new Logfile(path);
		test3.file_reader_and_parser();

	}

}
