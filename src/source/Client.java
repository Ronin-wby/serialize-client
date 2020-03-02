package source;
import java.io.File;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import source.DemoSerializer.Demo;


public class Client {

	public static void main(String[] args) {

		int port = 8888; // 定义端口
		byte[] buf = new byte[10240];// 存储发来的消息
		String filepath = "C:\\Users\\Administrator\\Desktop\\疫情数据\\全国各省疫情数据.csv";
		File outFile = new File(filepath);
		
	
		try {
			if(!outFile.exists()) {
				new CreatCSV().creatCSV();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		while (true) {
			try {
				DatagramSocket ds = new DatagramSocket(port);
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
                System.out.println("监听广播端口打开：");
                ds.receive(dp);
                ds.close();
                
                byte[] data = new byte[dp.getLength()];
                data = Arrays.copyOf(dp.getData(), dp.getLength());
//              int len = buf.length;              
//              String msg = new String(buf, 0, len);
//
//              System.out.println("接受到广播消息："+msg);
//              sbuf.delete(0, sbuf.length());
              
                Demo mydemo = deserialize(data);
                
                if(mydemo.getFileName().equals("全国疫情最新数据")) {
                	
                    List<String> list = new ArrayList<String>();
                    list = mydemo.getFileContentList();
                    new SaveData().savadata(list);

            		System.out.println("----接受的信息-----");
            	
            		System.out.println(mydemo.getFileName());
            		int i = 0;
            		for( i = 0 ; i<mydemo.getFileContentList().size();i++){
            		System.out.println(mydemo.getFileContent(i)); 
            		}
            		i = 0;
                }
                

        		
        				
                
                
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}

	}// end main
	
	private static Demo deserialize(byte[] byteArray) throws Exception {
		
		Demo demo = DemoSerializer.Demo.parseFrom(byteArray);
		
		return demo;
		
	}
	
	

}// end class























