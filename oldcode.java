package kivs_Package;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class oldcode {

	/*
	 * FileInputStream file = new FileInputStream(this.file_path);
			BufferedReader br = new BufferedReader(new InputStreamReader(file));
			String line = null;
			ArrayList<PingPack> list = new ArrayList<PingPack>();
			ArrayList<Double> WList = new ArrayList<Double>();
			int slashcounter = 0;

			String cmp1 = null;
			StringBuilder sb = new StringBuilder();
			StringBuilder sb1 = new StringBuilder();
			String cmp2 = null;
			String nmb = null;
			double tmp;
			String rou = "rou";
			String rtt = "rtt";
			
	// gets first Timestamp
	String firstline = br.readLine();
	// gets first Ip-Adress
	line = br.readLine();

	// get first 10 values
	for (int i = 0; i < 11; i++) {

		line = br.readLine();

		if (line != null) {
			for (int v = 0; v < line.length(); v++) {

				sb.append(line.charAt(0));
				sb.append(line.charAt(1));
				cmp1 = sb.toString();
				sb.setLength(0);

				if (cmp1.contentEquals("64")) {

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
	}

	// create first PingPack
	PingPack first = new PingPack();
	first.PackCreator(Integer.parseInt(firstline), WList, "173.194.35.191");
	list.add(first);

	// the following section should go through the rest and get all the Pingpacks
	while ((line = br.readLine()) != null) {

		PingPack pack = new PingPack();
		if (!(line.isEmpty() || line == null)) {

			sb.append(line.charAt(0));

			sb.append(line.charAt(1));
			sb.append(line.charAt(2));
			cmp1 = sb.toString();

			sb.setLength(0);

			if (cmp1.contentEquals(rou) || cmp1.contentEquals(rtt)) {

				// clause to get the rtt times
				pack.rttavgB = true;
				pack.rttmaxB = true;
				pack.rttminB = true;

				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '=') {
						for (int q = i + 2; q < line.length(); q++) {

							if (line.charAt(q) == '/') {
								slashcounter = slashcounter + 1;
							}

							if (slashcounter == 0) {
								if (line.charAt(q) != '/') {
									sb.append(line.charAt(q));
									if (line.charAt(q + 1) == '/') {
										pack.rttmin = Double.parseDouble(sb.toString());

										sb.setLength(0);

									}

								}

							}

							if (slashcounter == 1) {

								if (line.charAt(q) != '/') {
									sb.append(line.charAt(q));
									if (line.charAt(q + 1) == '/') {
										pack.rttavg = Double.parseDouble(sb.toString());

										sb.setLength(0);

									}
								}

							}

							if (slashcounter == 2) {

								if (line.charAt(q) != '/' && line.charAt(q) != ' ') {

									sb.append(line.charAt(q));
									if (line.charAt(q + 1) == '/' || line.charAt(q + 1) == ' ') {

										pack.rttmax = Double.parseDouble(sb.toString());
										sb.setLength(0);
										q = line.length();
										i = line.length();

										slashcounter = 0;

									}

								}

							}

						}
					}

				}
				// get timestamp

				cmp1 = null;

				line = br.readLine();

				if (line != null) {
					if (!(line.isEmpty())) {

						sb.append(line.charAt(0));
						sb.append(line.charAt(1));
						cmp1 = sb.toString();
						sb.setLength(0);

						// clasuse for timestamp
						if (cmp1.contentEquals("13") || cmp1.contentEquals("14") || cmp1.contentEquals("15")) {

							pack.stamp = Integer.parseInt(line);
							pack.stampB = true;

						}
					}
				}

				// init for IP
				cmp1 = null;
				if (line != null) {
					line = br.readLine();
				}
				if (line != null) {
					if (!(line.isEmpty())) {

						sb.append(line.charAt(0));
						sb.append(line.charAt(1));
						sb.append(line.charAt(2));
						sb.append(line.charAt(3));
						cmp1 = sb.toString();
						sb.setLength(0);

						// get IP and create
						if (cmp1.contentEquals("PING")) {

							pack.IPB = true;
							for (int i = 0; i < line.length(); i++) {
								if (line.charAt(i) == '(') {
									for (int u = i + 1; u < line.length(); u++) {
										if (line.charAt(u) != ')') {
											sb.append(line.charAt(u));
										} else {
											pack.IP = sb.toString();

											u = line.length();
											i = line.length();
											sb.setLength(0);
										}
									}
								}
							}
							list.add(pack);
						}

					}
				}
			}

		}

	}
	*/

}
