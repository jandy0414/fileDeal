import java.io.*;
import java.util.Base64;
import java.util.Date;

import com.shenfenbao.utils.SFBCryptoService;

public class demo {
	
	

/** 
* 将字节流转换成文件 
* @param filename 
* @param data 
* @throws Exception 
*/  
public static void saveFile(String filename,byte [] data)throws Exception{   
    if(data != null){   
      String filepath ="C:\\upload\\" + filename;
      File file  = new File(filepath);   
      if(file.exists()){   
         file.delete();   
      }   
      FileOutputStream fos = new FileOutputStream(file);   
      fos.write(data,0,data.length);   
      fos.flush();   
      fos.close();   
    }
}
	private static   void  saveBytetoFile(String filePath,byte [] imageByte) throws IOException {
		OutputStream out = null;
		try {
			File file  = new File(filePath);
			if(file.exists()){
				file.delete();
			}
			out = new FileOutputStream(filePath);
			out.write(imageByte);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException("生成到指定目录失败，path：" + filePath);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static byte[] getBytes(String filePath){
		byte[] buffer = null;
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
	
	public static void main(String[] args) 
	{
		String tripleDESkey = "296CD6EB2CA94321ABEF575F4CFC10EC";
//		String data = "oauY2OGdeYEW101JNaVcIja5RJHrZtsCuNgROxb6PFuZZPi79KO/+duH4UiK4i8ZBA892S/R57HojnIjuR6WX3846iWpMzlLYihbakdlCaPxLiFtBTb3hXs6Bj3djqJjaFW6mnhphDXkPRLkmC9gliCoKBOpLCHIlmj0XvZ8XC+1wkoYvGQEeNuXDlzDZbC8CUdjkOh7aQBbtpWPEsq/pF1CD3sRXKDyMtuLY+pGlEt+bVyn3sd4IpbhPMXzrCZbimfplHqwFYlwf+UoYE9GAbBvyVpTy9w3jk/qWOMxs+OEQIzkPH8OBpEyOcgmzv8oiNBdidDv0MHVAd3x+J5A2udKD7/VNT9LFLBswCELjO73sERsRmSDUpSu82lO7u/5+iS+AeIeRm5JSK9NRlf3xU1Ssu+Ada5bo8kLYaeU/KI7I2gp92xJ7ddr6yp5NY8Imtr/bFOz35nFDahkWfZiVPyL+HtakljEDAQL3wbF6HCDv/dAh1kWxMWg9YWPEdzhW/0ZBiRLeyV59ODT7vFNhoeRu1KUMczhO54j4ZhyX0S8/VVK67MgN3s6Bj3djqJje1jFQGgv7hXH4V3iI+usUnAHmfXcqaD806kHx9zByTy0mgpyuALyxYlf/EsQVEvvjQ2nxgx1uknQCmCcuES80xNfHPeDN7/xoJ9eruiUtwMXBpHjcPuhOId4G3/XWM5DQYxSZdVQdpJJxl0Va7/GzFcUnztDK1/ahNSuvm7Ul8aAbDOZwYJ5+/S7p1NSn5WvBYBQQwF3Sctu/F3rRxJyrNwcAHLzyK8yWNhYEUlixnGzABYDYXBxoSWG4hs53sxLpCWpe0Mo7zmESTfNcd1FTNOmts0cLOMGuLONkrLrAWW3HexqOpcG8D3ryjqXEgBePtNoQznxeHtcjbmNCcfeGmUyDtYe6eaOg/H1Q8keDfmpJnFOme6GOo5CGAUEMFiFPtNoQznxeHuNrXhs/+Y5/VklFhY23wz3m8RGxOhAOwuY0RCGsUheI49jViz+I/ZQXCuOaJ8of2UW1SQrGmnQQgHy5evOo6gNnkWIQHKmumHCmKxapvstOU2oDylR5ICpSKi3eIY9OVbSlAFC9sKx5DV0n29g1xk4AfLl686jqA3/p04acenHyKcVfPGUMORyoQGw88hiit57OgY93Y6iYyd+KnQleITj6LJk3ueiDVSxz+L6eDGJusgIdQsmx3+BfzjqJakzOUupJjqFPKnCx5qjf2LI6QMAw6NEWl9QDYxMBWN6KE5N+bZQMMqV8lS7F4ozEqHnpj9t8ZoTo9Cuphy3hBM9HzY5r+Be/CN/jpeHkbtSlDHM4Yh+8ixptsFJfhbZGS+9sRFdO53EUSK+pCMMZPJGBrWWWugIdwbr0+tBqpHY1jdElLDPL5VrsfmCj4HK4Ooc+6ktJ4u42SZwJhdZ3aY368+a5E+LeJUMrpENtfAC2pnrtQ==";
//		final Base64.Decoder decoder = Base64.getDecoder();
//		byte[] encryptData = decoder.decode(data);
//		String clearData = SFBCryptoService.tripleDESDecrypt(tripleDESkey, encryptData, encryptData.length);
		
//		System.out.println("clear data:" + clearData);
		String filePath="C:/upload/BasicFaceData-2018121510999.json";

//		String json = "[{\"face_image_id\":\"33EDC539-F383-6BDC-E468-20E2ba36964C\",\"feature_code\":\"\",\"picture_name\":\"35020677001111700000120181101101010123F\" ,\"picture_md5\":\"4C35DEED1AFD94154C0B02C311334974\",\"identification_type\":\"01\",\"identification_number\":\"350624198504042512\",\"gender\":1,\"name\":\"吴晓龙\",\"nation\":\"01\",\"region\":\"350624\",\"born_date\":\"19850404\",\"timestamp\": 1543893165,\"phone\":\"18250206244\",\"cj_ga_code\":\"350602770000\",\"cj_ga_name\":\"漳州市公安局芝山派出所\",\"st_address_code\":\"32EDC739-F303-4BDC-E054-90E2ba54908C\",\"st_address\":\"福建省漳州市芗城区惠民路9号\",\"st_address_gps\":[117.627248,24.532877],\"city_code\":\"350600\",\"city_name\":\"漳州市\",\"area_code\":\"350602\",\"area_name\":\"芗城区\",\"toponym_code\":\"350600120000000709423\",\"toponym_name\":\"惠民路\",\"street_code\":\"350206770\",\"street_name\":\"芝山镇\",\"committees_code\":\"350602008031\",\"committees_name\":\"金永社区居民委员会\",\"ga_code\":\"350602770000\",\"ga_name\":\"漳州市公安局芝山派出所\",\"ga_grid_code\":\"350600120000000089815\",\"ga_grid_name\":\"漳州市公安局芝山派出所第三警务网格\"}]";
		String json = "[{\"face_image_id\":\"33EDC539-F383-6BDC-E468-20E2ba36964C\",\"feature_code\":\"\",\"picture_name\":\"3506020001132700000120181101101010123F\" ,\"picture_md5\":\"96D4ACA8210E2A88040A97A465FA7A40\",\"identification_type\":\"01\",\"identification_number\":\"350624198504042512\",\"gender\":1,\"name\":\"吴晓龙\",\"nation\":\"01\",\"region\":\"350624\",\"born_date\":\"19850404\",\"timestamp\": 1543893165,\"phone\":\"18250206244\",\"cj_ga_code\":\"350602770000\",\"cj_ga_name\":\"漳州市公安局芝山派出所\",\"st_address_code\":\"32EDC739-F303-4BDC-E054-90E2ba54908C\",\"st_address\":\"福建省漳州市芗城区惠民路9号\",\"st_address_gps\":[117.627248,24.532877],\"city_code\":\"350600\",\"city_name\":\"漳州市\",\"area_code\":\"350602\",\"area_name\":\"芗城区\",\"toponym_code\":\"350600120000000709423\",\"toponym_name\":\"惠民路\",\"street_code\":\"350602008\",\"street_name\":\"芝山镇\",\"committees_code\":\"350602008031\",\"committees_name\":\"金永社区居民委员会\",\"ga_code\":\"350602103000\",\"ga_name\":\"漳州市公安局芝山派出所\",\"ga_grid_code\":\"350602103002\",\"ga_grid_name\":\"漳州市公安局芝山派出所第三警务网格\"}]";
		byte[] encryptData = SFBCryptoService.tripleDESEncrypt(tripleDESkey, json);
//		final Base64.Encoder encoder = Base64.getEncoder();
//		final String encodedText = encoder.encodeToString(encryptData);
		System.out.println(filePath);
		try {
//			saveFile("BasicFaceData-20150623213300.json", encryptData);
			saveBytetoFile(filePath,encryptData);

			byte[] decryptData = getBytes(filePath);
			String clearData = SFBCryptoService.tripleDESDecrypt(tripleDESkey, decryptData, decryptData.length);
			System.out.println("clear data:" + clearData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}


}
