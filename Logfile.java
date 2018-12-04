package kivs_Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Logfile {

	// logfile objects consist only of a path

	String file_path;

	// constructor
	public Logfile(String path_of_file) {

		this.file_path = path_of_file;

	}

	public void file_reader_and_parser(url_accesser a) {

		a.siteAccesed = false;
		try {
			if (a.siteAccesed) {

				File afile = returnLastMod();
				FileInputStream file = new FileInputStream(afile.getPath());
				BufferedReader br = new BufferedReader(new InputStreamReader(file));
				String line = null;
				

				file.close();
			}

			else {

				FileInputStream file = new FileInputStream(this.file_path);
				BufferedReader br = new BufferedReader(new InputStreamReader(file));
				String line = null;
				ArrayList<PingPack> list = new ArrayList<PingPack>();
				ArrayList<Double> WList = new ArrayList<>();

				int counter = 0;
				String cmp1 = null;
				StringBuilder sb = new StringBuilder();
				StringBuilder sb1 = new StringBuilder();
				String cmp2 = null;
				String nmb = null;
				double tmp;

				String roundtrip = "roundtrip";
				String rtt = "rtt";
				String sixfour = "64";
				String time = "time";
				String thirteen = "13";
				String fourteen = "14";
				String fifteen = "15";
				int j;
				
				
				//gets first Timestamp
				String firstline = br.readLine();
				
				//gets first Ip-Adress
				line = br.readLine();
				for(int i=0;i<line.length();i++) {
					
					if(line.charAt(i)!='(') {
						i+=1;
						while(line.charAt(i)!=')'){
							sb.append(i);
							
						}
						cmp1 = sb.toString();
						sb.setLength(0);
						i = line.length();
					}
					
				}
				//get first 10 values
				for (int i = 0; i < 11; i++) {

					
					line = br.readLine();
					
					for (int v = 0; v < line.length(); v++) {

						sb.append(line.charAt(0));
						sb.append(line.charAt(1));
						cmp1 = sb.toString();
						sb.setLength(0);

						if (cmp1.contentEquals(sixfour)) {

							sb.setLength(0);
							sb.append(line.charAt(v));
							sb.append(line.charAt(v + 1));
							sb.append(line.charAt(v + 2));
							sb.append(line.charAt(v + 3));
							cmp2 = sb.toString();
							sb.setLength(0);

							if (cmp2.equals("time")) {

								while (!(line.charAt(v + 5) == 'm')) {
									sb1.append(line.charAt(v + 5));
									v++;
								}

								nmb = sb1.toString();
								sb1.setLength(0);
								tmp = Double.parseDouble(nmb);
								WList.add(tmp);
								v = line.length();

							}

						}
					}
				}
				
				//create first PingPack
				PingPack first = new PingPack();
				first.PackCreator(Integer.parseInt(firstline), WList);
				list.add(first);

				//the following section should go through the rest and get all the Pingpacks
				while ((line = br.readLine()) != null) {

					for (int i = 0; i < line.length(); i++) {

						sb.append(line.charAt(0));

						sb.append(line.charAt(1));
						cmp1 = sb.toString();

						sb.setLength(0);
						//clause for rtt
						if (cmp1.contentEquals(roundtrip) || cmp1.contentEquals(rtt)) {
						//clasuse for timestamp
						if (cmp1.contentEquals(thirteen) || cmp1.contentEquals(fourteen) || cmp1.contentEquals(fifteen))

							

								sb.append(line.charAt(i));
								sb.append(line.charAt(i + 1));
								sb.append(line.charAt(i + 2));
								sb.append(line.charAt(i + 3));
								cmp2 = sb.toString();

								sb.setLength(0);

								if (cmp2.contentEquals(time)) {
									j = i + 5;

									while (!(line.charAt(j) == ' ')) {

										sb1.append(line.charAt(j));

										j++;
									}

									nmb = sb1.toString();

									sb1.setLength(0);
									i = line.length();
									tmp = Double.parseDouble(nmb);

									//list.add(tmp);

								}
							}
					}

				}

				file.close();
			}

		} catch (IOException e) {

			System.out.println("problem with buffer.");
		}

	}

	public static File returnLastMod() {
		String path = (File.separator + "Users" + File.separator + "Coon" + File.separator + "Downloads");
		long currentMax;

		File[] paths;

		File f = new File(path);
		paths = f.listFiles();

		currentMax = paths[0].lastModified();
		File max = paths[0];

		for (int i = 0; i < paths.length; i++) {
			if (paths[i].lastModified() > currentMax) {
				currentMax = paths[i].lastModified();
				max = paths[i];

			}
		}

		return max;

	}

}
