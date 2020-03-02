package source;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class SaveData {
	static String[] bufferdata = new String[7];
	public void savadata(List<String> list) throws IOException {
		ArrayList<String []> List0 = new ArrayList<String[]>();
		
		int i = 0;
		String filepath = "C:\\Users\\Administrator\\Desktop\\疫情数据\\全国各省疫情数据.csv";
		File inFile = new File(filepath);
		


		try {
			BufferedReader reader = new BufferedReader(new FileReader(inFile));
			CsvReader creader = new CsvReader(reader, ',');

			creader.readHeaders();
			List0.add(creader.getValues());
			
			
			for( i = 0 ; i<list.size();i++){
				bufferdata=list.get(i).split(",");
				List0.add(bufferdata);
			}
			i = 0;
			
			while (creader.readRecord()) {
				List0.add(creader.getValues());
			}
			reader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//给list添加疫情数据

		
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(inFile));

			CsvWriter cwriter = new CsvWriter(writer, ',');

			// cwriter.endRecord();

			String[] str = { "Time", "Province", "currentConfirmedCount", "confirmedCount", "curedCount", "deadCount","suspectedCount" };
			cwriter.writeRecord(str, true);
			for (i = 0; i < List0.size(); i++) {
				String[] Data = List0.get(i);
				cwriter.writeRecord(Data);
			}
			i = 0;
			cwriter.flush();
			cwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 刷新数据

	}

}
