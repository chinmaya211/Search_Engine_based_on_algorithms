package org.chinmaya.search;

import org.chinmaya.search.SpellChecker;
import org.chinmaya.search.TrieST;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class WordSearch {
	static HashMap<String, List<String>> fileContentByLines ;
	public static int x=0;
	public WordSearch(String folderPath){
		if(fileContentByLines == null){
			fileContentByLines = new HashMap<String, List<String>>();  // list of files with their lines
			File[] files = new File(folderPath).listFiles();
			try {
				loadFilesContent(files);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Entry<String, Integer>> search(String searchContent,int topNRecords) throws IOException { // search the query in the database.
		searchContent = searchContent.toLowerCase();
		Map<String, Integer> fileContentCount = new HashMap<String, Integer>();
		if(fileContentByLines!=null){
			List<String> contentList = null;
			int count = 0;
			String tokens[] = null;
			for(String fileName : fileContentByLines.keySet()){   //load the file list
				contentList = fileContentByLines.get(fileName);
				count = 0;
				if(contentList!=null){
					tokens = null;
					for(String line : contentList){   // load lines for each file
						if(line.toLowerCase().contains(searchContent)){
							tokens = line.split(" ");
							for(String word : tokens){ // check whether the word is present or not
								if(word.toLowerCase().contains(searchContent))
									count++;
							}
						}
					}
					if(count>0)
						fileContentCount.put(fileName, count);
				}
			}
		}
		
		//quickSelect_Method(fileContentCount);
		List<Entry<String, Integer>> sortedFileContentCount = entriesSortedByValues(fileContentCount);
		if(topNRecords > sortedFileContentCount.size()){
			topNRecords = sortedFileContentCount.size();
		}				
		List<Entry<String, Integer>> topNSortedFileContentCount = sortedFileContentCount.subList(0, topNRecords);
		//System.out.println("Top " + topNRecords + " Sorted Files: ");
		if(topNSortedFileContentCount.isEmpty()) {
			SpellChecker.SpellCheck(searchContent);  // integrated spell checker.
		}	
		for (Object o : topNSortedFileContentCount)
		    System.out.println(o);
		return topNSortedFileContentCount;
	}

	
	public static void loadFilesContent(File[] files) throws IOException{
	    for (File file : files) {
	        if (file.isDirectory()) {
	            loadFilesContent(file.listFiles());
	        } else {
	            fileContentByLines.put(file.getName(), FilesUtil.readTextFileByLines(file.getPath()));
	        }
	    }
	}
	
	
	public static <K,V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {
		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());
		Collections.sort(sortedEntries, 
	            new Comparator<Entry<K,V>>() {
	                @Override
	                public int compare(Entry<K,V> e1, Entry<K,V> e2) {
	                    return e2.getValue().compareTo(e1.getValue());
	                }
	            }
	    );

		return sortedEntries;
	}

	public static Integer[] a;
	public static int i =0;
	@SuppressWarnings("unchecked")
	public static <K, V> Object quickSelect_Method(Map<K,V> map) {  // added quickSelect to pick top 10
		@SuppressWarnings({ "rawtypes", "unused" })
		Iterator it = map.entrySet().iterator();
        List<String> key = new ArrayList<>( );
        List<Integer> value = new ArrayList<>( );

		key.addAll((Collection<? extends String>) map.keySet());
		value.addAll((Collection<? extends Integer>) map.values());
		 //   System.out.println(key);
		//System.out.println(value);
		Integer[] strArray = (Integer[]) value.toArray(new Integer[0]);
		for(int g =0;g<strArray.length;g++) {
		System.out.println(strArray[g]);
		}
		QuickSelect.quickSelect1(strArray, 1);
		return null;
	}

	public static int Implement_Trie(String query) throws FileNotFoundException { // implemented trie for total no of occurrences. 
    	String folderPath = "C:/Study_Mac/Advanced_Computing/W3C_Web_Pages1/Text/";
    	String file_name[] = {"About W3C Standards.txt","Accessibility - W3C.txt","Accessible Rich Internet Applications (WAI-ARIA) 1.0.txt","All Standards and Drafts - W3C.txt","Architecture Principles - W3C.txt","Audio and Video - W3C.txt","Authoring Tools and Social Media - W3C.txt","Best practices for creating MMI Modality Components.txt","Best Practices for Publishing Linked Data.txt","Browsers and Authoring Tools - W3C.txt","Browsers and Media Players- W3C.txt","CC PP Implementors Guide  Privacy and Protocols.txt","Component Extension (CX) API requirements Version 1.0.txt","Components - W3C.txt","Composite Capabilities Preference Profiles  Terminology and Abbreviations.txt","Composite Capability Preference Profiles (CC PP)  Structure and Vocabularies 1.0.txt","Cross-Origin Resource Sharing.txt","CSS Namespaces Module Level 3.txt","CSS3 module  Presentation Levels.txt","Data - W3C.txt","DataDictionary.txt","Defining N-ary Relations on the Semantic Web.txt","Design of HTTP-NG Testbed.txt","Device APIs Requirements.txt","Device Independence and Content Adaptation - W3C.txt","Graphics - W3C.txt","Guidelines for Web Content Transformation Proxies 1.0.txt","HTML & CSS - W3C.txt","HTML Imports.txt","HTML Templates.txt","HTTP-NG Binary Wire Protocol.txt","Identifiers - W3C.txt","Inference - W3C.txt","Internationalization - W3C.txt","JavaScript Web APIs - W3C.txt","LBase  Semantics for Languages of the Semantic Web.txt","Legacy extended IRIs for XML resource identification.txt","Linked Data Glossary.txt","MBUI - Task Models.txt","Meta Formats - W3C.txt","Metadata API for Media Resources 1.0.txt","Mobile Web - W3C.txt","Mobile Web Application Best Practices.txt","Multimodal Access - W3C.txt","Offline Web Applications.txt","Ontologies - W3C.txt","OWL 2 RL in RIF (Second Edition).txt","OWL 2 Web Ontology Language Direct Semantics (Second Edition).txt","OWL 2 Web Ontology Language Mapping to RDF Graphs (Second Edition).txt","OWL 2 Web Ontology Language New Features and Rationale (Second Edition).txt","OWL 2 Web Ontology Language RDF-Based Semantics (Second Edition).txt","OWL 2 Web Ontology Language XML Serialization (Second Edition).txt","OWL Web Ontology Language Overview.txt","PEP - an Extension Mechanism for HTTP.txt","Permissions for Device API Access.txt","Privacy - W3C.txt","Processing - W3C.txt","Protocols - W3C.txt","Publishing - W3C.txt","Query - W3C.txt","RDF 1.1 Turtle.txt","Representing Classes As Property Values on the Semantic Web.txt","Representing Specified Values in OWL   value partitions  and  value sets.txt","Roadmap for Accessible Rich Internet Applications (WAI-ARIA Roadmap).txt","Role Attribute 1.0.txt","Schema - W3C.txt","Security - W3C.txt","Semantic Web - W3C.txt","Service Description - W3C.txt","Subresource Integrity.txt","Techniques for User Agent Accessibility Guidelines 1.0.txt","Terms for describing people.txt","The ILU Requestor for HTTP servers.txt","The RDF Data Cube Vocabulary.txt","The WebSocket API.txt","Tracking Compliance and Scope.txt","Transformation - W3C.txt","Vertical Applications - W3C.txt","Vocabularies for EmotionML.txt","Voice Browsing - W3C.txt","W3C activities related to Electronic Commerce.txt","W3C WD  SMUX Protocol Specification.txt","WAI-ARIA 1.0 Authoring Practices.txt","Web and TV - W3C.txt","Web Architecture - W3C.txt","Web Audio Processing  Use Cases and Requirements.txt","Web Design and Applications - W3C.txt","Web MIDI API.txt","Web of Devices - W3C.txt","Web of Services - W3C.txt","Web Security Context  User Interface Guidelines.txt","Web Services Architecture.txt","Web Services Policy 1.5 - Framework.txt","WordBank.txt","World Wide Web Consortium (W3C).txt","XHTML Basic 1.1 - Second Edition.txt","XML Essentials - W3C.txt","XML Linking Language (XLink) Version 1.1.txt","XML Schema Datatypes in RDF and OWL.txt","XML Signature Syntax and Processing Version 1.1.txt","XML Technology - W3C.txt","XMLHttpRequest Level 1.txt","XPath and XQuery Functions and Operators 3.0.txt"};
    	@SuppressWarnings("unused")
		HashMap<String, List<String>> fileContentByLines = new HashMap<String, List<String>>();
			for(int i =0;i<file_name.length;i++) {
			@SuppressWarnings("resource")
			String txt =  new Scanner(new File(folderPath+file_name[i])).useDelimiter("\\A").next();
			TrieST<Integer> tst = new TrieST<Integer>();

		     TST<Queue<Integer>> stt = new TST<Queue<Integer>>();
		       // StdOut.println("key_match(\"protein\"):");
		        
		        for(String s:tst.matchingkeys(query)) {
		            if(!   stt.contains(s))
		                  stt.put( s,   new  Queue<Integer>());
		            else
		          stt.get(s).enqueue(tst.get(query));                          
		         //System.out.println("Occurence at "+tst.get("planarity"));
		        }  
		         int y = tst.count(txt, query);
		          x = x+y;	 
		          //System.out.println("No of occurrence:"+x);  
		}
			 System.out.println("Total occurrences:"+x);
			 return x;
  }

	@SuppressWarnings("static-access")
	public static void main(String args[]) throws IOException {
		WordSearch ws = new WordSearch("C:/Study_Mac/Advanced_Computing/W3C_Web_Pages1/Text/");
		ws.Implement_Trie("information");
		System.out.println();
		System.out.println("Top 10 Sorted Files with Occurrences:");
		System.out.println("--------------------------------------------------------------");
		ws.search("information", 10);	
	}
}
