package read_content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class read_content {
	
    public static void main(String[] args) throws IOException
    {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/cx_results.txt"), "UTF-8"));
		BufferedWriter rt = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/New/AliTianChi-master/AliTianChi-master/新浪微博互动预测大赛/Season1/trick/results.txt"), "UTF-8"));
		
		int rnum = 0;
		String temp;
		
		while ((temp = br.readLine()) != null) 
		{
			rnum++;
			
			String [] sentence = temp.split("\t");
			
			if(sentence.length % 2 == 1)
			{
				rt.write(temp);
				System.out.println("Spliting Error!");
				break;
			}
			
			for(int i = 0; i < sentence.length; i+=2)
			{
			
				if(sentence[i+1].equals("n") || sentence[i+1].equals("vn") || sentence[i+1].equals("v")) //可再多過濾一些詞性				
					rt.write(sentence[i] + " ");
			}
			
			rt.write("\r\n");
		}
		rt.close();		
		br.close();
    }
}
