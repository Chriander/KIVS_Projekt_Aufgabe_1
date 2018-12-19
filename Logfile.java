package kivs_Package;

import java.io.BufferedReader;
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

	public PingPack[] file_reader_and_parser(url_accesser a) {

		PingPack[] Liste = null;
		try {

			FileInputStream file = new FileInputStream(this.file_path);
			BufferedReader br = new BufferedReader(new InputStreamReader(file));
			String line = null;
			ArrayList<PingPack> list = new ArrayList<PingPack>();

			int slashcounter = 0;

			String cmp1 = null;
			StringBuilder sb = new StringBuilder();

			String rou = "rou";
			String rtt = "rtt";

			while ((line = br.readLine()) != null) {

				PingPack pack = new PingPack();

				cmp1 = null;

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
						cmp1 = null;

						line = br.readLine();

						if (line != null) {
							if (!(line.isEmpty())) {
								sb.append(line.charAt(0));
								sb.append(line.charAt(1));
								cmp1 = sb.toString();
								sb.setLength(0);

								while (cmp1.contentEquals("13") || cmp1.contentEquals("14")
										|| cmp1.contentEquals("15")) {
									pack.stamp = Integer.parseInt(line);
									line = br.readLine();
									if (line != null) {
										sb.append(line.charAt(0));
										sb.append(line.charAt(1));
										cmp1 = sb.toString();
									} else {
										cmp1 = "break";
									}

								}
								sb.setLength(0);
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

								}

							}
						}
						line = br.readLine();

						if (line != null && !(line.isEmpty())) {
							sb.append(line.charAt(0));
							sb.append(line.charAt(1));
							sb.append(line.charAt(2));
							cmp1 = sb.toString();
							sb.setLength(0);
						}
						while (!(cmp1.contentEquals(rou)) && !(cmp1.contentEquals(rtt))) {
							line = br.readLine();
							if (line != null && !(line.isEmpty())) {
								sb.append(line.charAt(0));
								sb.append(line.charAt(1));
								sb.append(line.charAt(2));
								cmp1 = sb.toString();
								sb.setLength(0);

							}
						}

						// clause to get the rtt times
						pack.rttavgB = true;
						pack.rttmaxB = true;
						pack.rttminB = true;

						for (int k = 0; k < line.length(); k++) {
							if (line.charAt(k) == '=') {
								for (int q = k + 2; q < line.length(); q++) {

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
												k = line.length();

												slashcounter = 0;

											}

										}

									}
								}
							}

						}
						list.add(pack);

					}
				}

			}

			// remove all bad entries
			int size = list.size();

			for (int i = 0; i < size; i++) {
				if (list.get(i).IPB != true || list.get(i).rttavgB != true || list.get(i).rttmaxB != true
						|| list.get(i).rttminB != true || list.get(i).stampB != true) {
					list.remove(i);
					size -= 1;
				}
			}

			// transfer values to array for performance improvements

			Liste = new PingPack[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Liste[i] = list.get(i);
			}

			file.close();

		} catch (IOException e) {

			System.out.println("problem with buffer.");
		}
		return Liste;

	}

	// for Aufgabe 1.2
	public int[] Segmenter(PingPack[] Liste) {
		ArrayList<Integer> segments = new ArrayList<Integer>();

		for (int i = 0; i < (Liste.length) - 1; i++) {

			if ((Liste[i + 1].stamp - Liste[i].stamp) > 7773178) {

				segments.add(i);

			}
		}
		segments.add(Liste.length - 1);

		int[] segs = new int[segments.size()];

		for (int i = 0; i < segments.size(); i++) {
			segs[i] = segments.get(i);
		}

		return segs;
	}

}
