package source;

import java.io.*;

import com.csvreader.*;

public class CreatCSV {
	
	public void creatCSV() {
		String filepath = "C:\\Users\\Administrator\\Desktop\\疫情数据\\全国各省疫情数据.csv";
		String[] str = {"Time","Province","currentConfirmedCount","confirmedCount","curedCount","deadCount","suspectedCount"};
		
		File outFile = new File(filepath);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

			CsvWriter cwriter = new CsvWriter(writer, ',');

			//cwriter.endRecord();


				cwriter.writeRecord(str, true);
				cwriter.flush();
				cwriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 刷新数据


	}



	
}//end class
