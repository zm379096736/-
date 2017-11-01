package idf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class idf {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/results.txt"), "UTF-8"));
		
		BufferedWriter rt = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/_idf.txt"), "UTF-8"));
		
		int counter = 0;
		int tf;
		Iterator <String> it; 
		String temp;
		HashMap< String, Integer> hmap=new HashMap< String, Integer>();
		HashMap< String, Double> idfmap=new HashMap< String, Double>();     
		
		/*
		String idf_temp;
		while ((idf_temp = idf.readLine()) != null) {
			String [] idf_data = idf_temp.split("=");
			idfmap.put(idf_data[0], Double.parseDouble(idf_data[1]));			
		}*/
		
		
		
		
		
		while((temp = br.readLine()) != null) {			
			counter++;
			String [] data = temp.split(" ");				
			
			HashMap< String, Double> tfmap = new HashMap< String, Double>();
			HashSet<String> hset = new HashSet<String>();		
			
			for(String datas : data) {
	            hset.add(datas);
	        }
			
			it = hset.iterator(); 
			
			
			while (it.hasNext()) {
				String s = it.next();
				
				if(hmap.containsKey(s))
					hmap.put(s, hmap.get(s)+1);
				else
					hmap.put(s, 1);
				
			}
		
			
			
			
		}
		
		for (Object key : hmap.keySet()) {
			//System.out.println(key + "=" + hmap.get(key));
			rt.write(key + " " + hmap.get(key));
			rt.write("\r\n");
        }
        
		
		System.out.println("done");	 
		br.close();
		//idf.close();
		rt.close();
	}

}
