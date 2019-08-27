package test.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class NaverGeoApi {
	public static void main(String[] args) {
		String clientId ="cwj22u1kk0";//clientId
		String clientSecret ="a07f1b2fzuo7MeKA5MRwVsFVJLWCJeVimeaesNPt"; //clientSecret
		
		try {
			String addr = URLEncoder.encode("���� ���Ǳ� û���߾ӱ� 16-7","UTF-8"); //�ּ��Է�
			String apiURL ="https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query="+addr;
			//"https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query={�ּ�}&coordinate=#{�˻�_�߽�_��ǥ}" 
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        	} catch (Exception e) {
            System.out.println(e);
        	}	
		}

}
