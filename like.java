package like;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class like {

	 public static void main(String[] args) throws IOException {
			BufferedReader weibo = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/weibo_train_data.txt"), "UTF-8"));
			BufferedReader idf = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/_idf.txt"), "UTF-8"));
			BufferedReader tfidf = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/tf_idf.txt"), "UTF-8"));
			
			String weibo_temp;
			String tfidf_temp;
			String idf_temp;
			
			double counter_1 = 0.0;
			double counter_2 = 0.0;
			double counter_3 = 0.0;
			double counter_4 = 0.0;
			double counter_5 = 0.0;
			double counter_6 = 0.0;
			
			HashMap< String, Double> idfmap_1=new HashMap< String, Double>();     
			HashMap< String, Double> idfmap_2=new HashMap< String, Double>();   
			HashMap< String, Double> idfmap_3=new HashMap< String, Double>(); 
			HashMap< String, Double> idfmap_4=new HashMap< String, Double>(); 
			HashMap< String, Double> idfmap_5=new HashMap< String, Double>(); 
			HashMap< String, Double> idfmap_6=new HashMap< String, Double>(); 
			
			while ((idf_temp = idf.readLine()) != null) {
				String [] idf_data = idf_temp.split(" ");
				idfmap_1.put(idf_data[0], 0.0);	
				idfmap_2.put(idf_data[0], 0.0);	
				idfmap_3.put(idf_data[0], 0.0);	
				idfmap_4.put(idf_data[0], 0.0);	
				idfmap_5.put(idf_data[0], 0.0);	
				idfmap_6.put(idf_data[0], 0.0);	
			}
			
		
			
			
			while((weibo_temp = weibo.readLine()) != null) {
				String [] weibo_data = weibo_temp.split("\t");
				
				tfidf_temp = tfidf.readLine();
				String [] tfidf_data = tfidf_temp.split(" ");	
				
				if(weibo_data[5].equals("0")) {		
					counter_1+=1;
					for(int i = 0; i < tfidf_data.length; i++) {
						if(idfmap_1.containsKey(tfidf_data[i])) {
							idfmap_1.put(tfidf_data[i], idfmap_1.get(tfidf_data[i]) + Double.parseDouble(tfidf_data[i+1]));
							//System.out.print(tfidf_data[i]);
						}							
					}
				}
					
					
				else if(weibo_data[5].equals("1")) {
					counter_2+=1;
					for(int i = 0; i < tfidf_data.length; i++) {
						if(idfmap_2.containsKey(tfidf_data[i])) {
							idfmap_2.put(tfidf_data[i], idfmap_2.get(tfidf_data[i]) + Double.parseDouble(tfidf_data[i+1]));
							//System.out.print(tfidf_data[i]);
						}							
					}
				}
				else if(weibo_data[5].equals("2")) {
					counter_3+=1;
					for(int i = 0; i < tfidf_data.length; i++) {
						if(idfmap_3.containsKey(tfidf_data[i])) {
							idfmap_3.put(tfidf_data[i], idfmap_3.get(tfidf_data[i]) + Double.parseDouble(tfidf_data[i+1]));
							//System.out.print(tfidf_data[i]);
						}							
					}
				}
				
				
				else if( weibo_data[5].equals("3") || weibo_data[5].equals("4")) {
					counter_4+=1;
					for(int i = 0; i < tfidf_data.length; i++) {
						if(idfmap_4.containsKey(tfidf_data[i])) {
							idfmap_4.put(tfidf_data[i], idfmap_4.get(tfidf_data[i]) + Double.parseDouble(tfidf_data[i+1]));
							//System.out.print(tfidf_data[i]);
						}							
					}
				}
				
				else if(Integer.parseInt(weibo_data[5])>4 &&  Integer.parseInt(weibo_data[5])<10) {
					counter_5+=1;
					for(int i = 0; i < tfidf_data.length; i++) {
						if(idfmap_5.containsKey(tfidf_data[i])) {
							idfmap_5.put(tfidf_data[i], idfmap_5.get(tfidf_data[i]) + Double.parseDouble(tfidf_data[i+1]));
							//System.out.print(tfidf_data[i]);
						}							
					}
				}
				else if(Integer.parseInt(weibo_data[5])>9) {
					counter_6+=1;
					for(int i = 0; i < tfidf_data.length; i++) {
						if(idfmap_6.containsKey(tfidf_data[i])) {
							idfmap_6.put(tfidf_data[i], idfmap_6.get(tfidf_data[i]) + Double.parseDouble(tfidf_data[i+1]));
							//System.out.print(tfidf_data[i]);
						}							
					}
				}
				else {
					System.out.println("------------");
				}
				//System.out.println();
			}	
			
			//for (Object key : idfmap_1.keySet()) 
				//System.out.println(key + " " + idfmap_1.get(key));
			
			for (String key : idfmap_1.keySet()) 
				idfmap_1.put(key, (idfmap_1.get(key))/counter_1);
			
			for (String key : idfmap_2.keySet()) 
				idfmap_2.put(key, (idfmap_2.get(key))/counter_2);
			
			for (String key : idfmap_3.keySet()) 
				idfmap_3.put(key, (idfmap_3.get(key))/counter_3);
			
			for (String key : idfmap_4.keySet()) 
				idfmap_4.put(key, (idfmap_4.get(key))/counter_4);
			
			for (String key : idfmap_5.keySet()) 
				idfmap_5.put(key, (idfmap_5.get(key))/counter_5);
			
			for (String key : idfmap_6.keySet()) 
				idfmap_6.put(key, (idfmap_6.get(key))/counter_6);
			
			weibo.close();
		 	idf.close();
		 	tfidf.close();
		 
		 	BufferedReader tfidf_2 = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/final_tfidf.txt"), "UTF-8"));
		 	BufferedReader fp = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/weibo_pred_c.txt"), "UTF-8"));
		 	BufferedWriter wp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/weibo_pred_l.txt"), "UTF-8"));
		 	String final_temp;
		 	String fp_temp;
		 	int cc = 0;
		 	
		 	
		 	Double Max;
		 	
		 	while((final_temp = tfidf_2.readLine()) != null) {
		 		fp_temp = fp.readLine();
		 		String [] fp_data = fp_temp.split("\t");
		 		String [] final_data = final_temp.split(" ");	
		 		double sum_1 = 0;
		 		double sum_2 = 0;
		 		double sum_3 = 0;
		 		double sum_4 = 0;
		 		double sum_5 = 0;
		 		double sum_6 = 0;
		 		List<Double> nums = new ArrayList<Double>();
		 		cc += 1;
		 		for(int i = 0; i < final_data.length; i++) {
					if(idfmap_1.containsKey(final_data[i])) {
						
						if((i+1)<final_data.length)
							sum_1 += Double.parseDouble(final_data[i+1])*idfmap_1.get(final_data[i]);
						
					}				
					if(idfmap_2.containsKey(final_data[i])) {
						
						if((i+1)<final_data.length)
							sum_2 += Double.parseDouble(final_data[i+1])*idfmap_2.get(final_data[i]);
						
					}	
					if(idfmap_3.containsKey(final_data[i])) {
						
						if((i+1)<final_data.length)
							sum_3 += Double.parseDouble(final_data[i+1])*idfmap_3.get(final_data[i]);
						
					}	
					if(idfmap_4.containsKey(final_data[i])) {
						
						if((i+1)<final_data.length)
							sum_4 += Double.parseDouble(final_data[i+1])*idfmap_4.get(final_data[i]);
						
					}	
					if(idfmap_5.containsKey(final_data[i])) {
						
						if((i+1)<final_data.length)
							sum_5 += Double.parseDouble(final_data[i+1])*idfmap_5.get(final_data[i]);
						
					}	
					if(idfmap_6.containsKey(final_data[i])) {
						
						if((i+1)<final_data.length)
							sum_6 += Double.parseDouble(final_data[i+1])*idfmap_6.get(final_data[i]);
						
					}	
				}
		 		nums.add(sum_1);
		 		nums.add(sum_2);
		 		nums.add(sum_3);
		 		nums.add(sum_4);
		 		nums.add(sum_5);
		 		nums.add(sum_6);
		 		Max = Collections.max(nums);
		 		if(Max == sum_1) {
		 			System.out.println(cc+" "+"sum_1"+" "+Max);
		 			wp.write(fp_data[0]+"\t"+fp_data[1]+"\t"+fp_data[2]+","+0);
		 			wp.write("\r\n");
		 		}
		 		
		 		else if(Max == sum_2) {
		 			System.out.println(cc+" "+"sum_2"+" "+Max);
		 			wp.write(fp_data[0]+"\t"+fp_data[1]+"\t"+fp_data[2]+","+1);
		 			wp.write("\r\n");
		 		}
		 		
		 		else if(Max == sum_3) {
		 			System.out.println(cc+" "+"sum_3"+" "+Max);
		 			wp.write(fp_data[0]+"\t"+fp_data[1]+"\t"+fp_data[2]+","+2);
		 			wp.write("\r\n");
	 			}
	 			
	 			else if(Max == sum_4) {
		 			System.out.println(cc+" "+"sum_4"+" "+Max);
		 			wp.write(fp_data[0]+"\t"+fp_data[1]+"\t"+fp_data[2]+","+3);
		 			wp.write("\r\n");
				}
	 			
				else if(Max == sum_5) {
		 			System.out.println(cc+" "+"sum_5"+" "+Max);
		 			wp.write(fp_data[0]+"\t"+fp_data[1]+"\t"+fp_data[2]+","+7);
		 			wp.write("\r\n");
	 			}
		 		
	 			else if(Max == sum_6) {
		 			System.out.println(cc+" "+"sum_6"+" "+Max);
		 			wp.write(fp_data[0]+"\t"+fp_data[1]+"\t"+fp_data[2]+","+850);
		 			wp.write("\r\n");
	 			}
		 		
	 		}	 		
		 	tfidf_2.close();
		 	fp.close();
		 	wp.close();
	 } 	 	 

}