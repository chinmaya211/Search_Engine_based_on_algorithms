package htmljsoup;


import java.io.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;

public class HTMLJsoup {

	public static void Convert (String s) throws IOException {
		File file=new File("C:/Study_Mac/Advanced_Computing/W3C_Web_Pages1/"+s);	
		Document doc = Jsoup.parse(file,"UTF-8");
		String text = doc.text();
		// System.out.println(text);
		PrintWriter out = new PrintWriter("C:/Study_Mac/Advanced_Computing/W3C_Web_Pages1/Text/" + s.replace(".htm", ".txt"));
		out.println(text);
		out.close();
	}
	public static void main(String[] args) throws IOException
	{
		File f = new File("C:/Study_Mac/Advanced_Computing/W3C_Web_Pages1/");
		File[] nf = f.listFiles();
		
		for(File file: nf)
		{
			if(file.isFile())
			{
				Convert(file.getName());
			}
		}
	}
}
