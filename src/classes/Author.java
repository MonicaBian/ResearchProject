package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;

public class Author {
	
	public String name;
	public int authorId;
	public Hashtable<Integer, Integer> coAuthors;
	public ArrayList<Integer> publishedPapers;

	public Author(String name, int authorId) {
		this.name = name;
		this.authorId = authorId;
		coAuthors = new Hashtable <Integer, Integer>();
		publishedPapers = new ArrayList<Integer>();
	}
	
	public void SetName(String newName) {
		name = newName;
	}
	
	public void SetAuthorId(int newAuthorId) {
		authorId = newAuthorId;
	}
	
	public void AddNewCoAuthor(int authorId) {
		coAuthors.put(authorId, 1);
	}
	
	public void RemoveCoAuthor(int authorId) {
		coAuthors.remove(authorId);
	}
	
	public boolean HasCoAuthor(int authorId) {
		return coAuthors.containsKey(authorId);
	}
	
	public int CoAuthorWeighting(int authorId) {
		return coAuthors.get(authorId);
	}
	
	public void IncrementWeighting(int authorId) {
		int currValue = coAuthors.get(authorId);
		coAuthors.replace(authorId, currValue, currValue + 1);
	}
	
	public void AddPublishedPaper(int paperId) {
		publishedPapers.add(paperId);
	}
	
	public boolean HasCoAuthoredWith(int authorId) {
		if(coAuthors.containsKey(authorId)) {
			return true;
		}
		return false;
	}
	
	public void PrintAllCoAuthors() {
		Enumeration<Integer> en = coAuthors.keys();
		while (en.hasMoreElements()) {
			int coAuthorId = en.nextElement();
			System.out.println("Coauthor: " + coAuthorId + " Weighting: " + coAuthors.get(coAuthorId));
		}
	}
	
	//monica2
	public int getAllAuthorDegree() {
		ArrayList<Integer> allCoAuthorWeightings = new ArrayList<Integer>();
		Enumeration<Integer> en = coAuthors.keys();
		while (en.hasMoreElements()) {
			int coAuthorId = en.nextElement();
			allCoAuthorWeightings.add(coAuthors.get(coAuthorId));
			//System.out.println(name);
			//System.out.println("Coauthor: " + coAuthorId + " Weighting: " + coAuthors.get(coAuthorId));
			
		}
		int totalAuthorDegree = 0;
		for (int i : allCoAuthorWeightings){
			totalAuthorDegree += i;
		}
		return totalAuthorDegree;
	}
	
	//monica6
	public int getAllPaperDegree() {
		return publishedPapers.size();
	}
	
	//monica7
	public int getAllCombinedDegree() {
		ArrayList<Integer> allCoAuthorWeightings = new ArrayList<Integer>();
		Enumeration<Integer> en = coAuthors.keys();
		while (en.hasMoreElements()) {
			int coAuthorId = en.nextElement();
			allCoAuthorWeightings.add(coAuthors.get(coAuthorId));	
		}
		int totalAuthorDegree = 0;
		for (int i : allCoAuthorWeightings){
			totalAuthorDegree += i;
		}
		int totalCombinedDegree = 0;
		totalCombinedDegree = totalAuthorDegree + publishedPapers.size();
		return totalCombinedDegree;
	}
	
	//monica4
	public double getMedianValue() {
		//int coAuthorWeight = 0;
		double medianValue = 0;
		int[] allCoAuthorWeightings = new int[0];
		Enumeration<Integer> en = coAuthors.keys();
		while (en.hasMoreElements()) {
			int coAuthorId = en.nextElement();
			//coAuthorWeight = coAuthors.get(coAuthorId);
			/*
			if (name.equals("Soricut, Radu")){
				System.out.println("Before Add Element:" + allCoAuthorWeightings.length);
			}
			*/
			allCoAuthorWeightings = addElement(allCoAuthorWeightings, coAuthors.get(coAuthorId)/2);
			//if (name.equals("Soricut, Radu")){
				//System.out.println("After" + allCoAuthorWeightings.length);
			//}
			//System.out.println(name);
			//System.out.println("Coauthor: " + coAuthorId + " Weighting: " + coAuthors.get(coAuthorId));
			
		}
		Arrays.sort(allCoAuthorWeightings);
		/*
		if (name.equals("Soricut, Radu")){
			System.out.println(name);
			System.out.println(allCoAuthorWeightings.length);
			PrintAllCoAuthors();
			//System.out.println(coAuthors.get(0));
			//System.out.println(allCoAuthorWeightings[1]);
		}
		*/
		if (allCoAuthorWeightings.length != 0){
			if (allCoAuthorWeightings.length == 1){
				medianValue = allCoAuthorWeightings[0];
				//System.out.println(name + " Median: " + medianValue);
				return medianValue;
			}
			if (allCoAuthorWeightings.length % 2 == 0)
				medianValue = ((double)allCoAuthorWeightings[allCoAuthorWeightings.length/2] + (double)allCoAuthorWeightings[allCoAuthorWeightings.length/2 - 1])/2;
			else
				medianValue = allCoAuthorWeightings[allCoAuthorWeightings.length/2];
			return medianValue;
		}else{
			System.out.println(name + " no coauthors");
			return 0.00;
		}
	}
	
	public void PrintAllPublishedPapers() {
		for (int i = 0; i < publishedPapers.size(); i++){
			System.out.println(publishedPapers.get(i));
		}
	}
	
	static int[] addElement(int[] a, int e) {
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
	}
	
	//monica3
	public ArrayList<Integer> getAllCoauthorDegrees() {
		ArrayList<Integer> allCoAuthorWeightings = new ArrayList<Integer>();
		Enumeration<Integer> en = coAuthors.keys();
		while (en.hasMoreElements()) {
			int coAuthorId = en.nextElement();
			//System.out.println("2May coAuthorId: " + coAuthorId);
			allCoAuthorWeightings.add(coAuthors.get(coAuthorId));
			//System.out.println("2May: " + coAuthors.get(coAuthorId));
			//System.out.println(name);
			//System.out.println("Coauthor: " + coAuthorId + " Weighting: " + coAuthors.get(coAuthorId));
			
		}
		return allCoAuthorWeightings;
	}
	
}
