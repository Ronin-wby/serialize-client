package source;
import java.net.*;

import org.json.*;

import source.DemoSerializer.Demo;


public class Client {

	public static void main(String[] args) {

		int port = 8888; // 定义端口
		// Peter: 这个buffer太小，从1024改为10240
		byte[] buf = new byte[10240];// 存储发来的消息
		//byte[] buf = new byte[1024];// 存储发来的消息
		
		while (true) {
			try {
				DatagramSocket ds = new DatagramSocket(port);
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
                System.out.println("监听广播端口打开：");
                ds.receive(dp);
                ds.close();
                
                // Peter: Begin
		byte[] data = new byte[dp.getLength()];
		data = Arrays.copyOf(dp.getData(), dp.getLength());
                //buf = dp.getData();
	        // Peter: End
//              int len = buf.length;              
//              String msg = new String(buf, 0, len);
//
//              System.out.println("接受到广播消息："+msg);
//              sbuf.delete(0, sbuf.length());
              
	        // Peter: Begin
		Demo mydemo = deserialize(data);
                //Demo mydemo = deserialize(buf);
		// Peter: End
//                JSONArray array = new JSONArray(myData.getfileContent());
//                System.out.println(array);
//        		System.out.println("-------end4------");

        		JSONObject jsonobj3 = new JSONObject(mydemo.getFileContent());
        		System.out.println(jsonobj3);
        		
        		System.out.println("省份："+jsonobj3.get("preProvinceName"));
        		System.out.println("现存确诊病例："+jsonobj3.get("currentConfirmedCount"));
        		System.out.println("累计确诊病例："+jsonobj3.get("confirmedCount"));
        		System.out.println("治愈病例："+jsonobj3.get("curedCount"));
        		System.out.println("死亡病例："+jsonobj3.get("deadCount"));
        		System.out.println("疑似病例："+jsonobj3.get("suspectedCount"));
        		
        		System.out.println("----接受的信息-----");
                
                System.out.println(mydemo.getFileName());
                System.out.println(mydemo.getSendDate());

                
                

                
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























