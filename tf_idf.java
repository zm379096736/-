package tf_idf;

import java.io.*;
import java.util.*;

import weibo.String;

import java.lang.*;
 
public class tf_idf {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/results.txt"), "UTF-8"));
		BufferedReader idf = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/_idf.txt"), "UTF-8"));
		//BufferedWriter rt = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/idf/cixing/tf_idf.txt"), "UTF-8"));
		BufferedWriter rt = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/tf_idf.txt"), "UTF-8"));
		
		int counter = 0;
		int tf;
		Iterator <String> it; 
		String temp;
		HashMap< String, Integer> hmap=new HashMap< String, Integer>();
		HashMap< String, Double> idfmap=new HashMap< String, Double>();     
		
		
		String idf_temp;
		while ((idf_temp = idf.readLine()) != null) {
			String [] idf_data = idf_temp.split(" ");
			idfmap.put(idf_data[0], Double.parseDouble(idf_data[1]));	
		}
		
		System.out.println(idfmap.size());
		
		
		HashMap< String, Double> tfmap = new HashMap< String, Double>();
		
		while((temp = br.readLine()) != null) {			
			
			counter++;
			
			String [] data = temp.split(" ");				
			
			tfmap.clear();
			
			
			System.out.println(counter);
			for(int i = 0; i < data.length; i++){
				if(tfmap.containsKey(data[i]))
					tfmap.put(data[i],tfmap.get(data[i])+1); 
				else
					tfmap.put(data[i],1.0);
			}
			
			
			for (String key : tfmap.keySet()) {
				//System.out.print(key + "=" + (tfmap.get(key)/data.length) * Math.log(1225088/idfmap.get(key)));
				//if(data.length != 0 && tfmap.containsKey(key) && idfmap.containsKey(key))
				if(!idfmap.containsKey(key))
					System.out.println(key);
				rt.write(key + " " + (tfmap.get(key)/data.length) * Math.log(1229617.0/idfmap.get(key)) + " ");
			}
			rt.write("\r\n");
			
		}
		/*
		for (Object key : idfmap.keySet()) 
			System.out.println(key + "=" + idfmap.get(key));
		
		for (Object key : hmap.keySet()) {
			//System.out.println(key + "=" + hmap.get(key));
			rt.write(key + " " + hmap.get(key));
			
        }
        */
		
		System.out.println("done");	 
		br.close();
		idf.close();
		rt.close();
	}

}
