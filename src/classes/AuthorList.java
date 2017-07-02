package classes;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class AuthorList {
	public ArrayList<Author> authors;
	public Hashtable<String, Integer> authorLookupTable;
	public int uniqueId;
	public int specialCharacterCounter;
	public Hashtable<String, String> specialNamesMapping;

	public AuthorList() {
		authors = new ArrayList<Author>();
		uniqueId = 0;
		specialCharacterCounter = 0;
		authorLookupTable = new Hashtable<String, Integer>();
		specialNamesMapping = new Hashtable <String, String>();
	}
	
	public void AddAuthor(Author author) {
		authorLookupTable.put(author.name, author.authorId);
		authors.add(author);
	}
	
	public void IncrementUniqueId(){
		uniqueId += 1;
	}
	
	public int GetUniqueId() {
		return uniqueId;
	}
	
	public boolean CheckIfAuthorExistsInLookupTable(String name) {
		if(authorLookupTable.containsKey(name)) {
			return true;
		} 
		return false;
	}
	
	public void AddAuthorToLookupTable(String name, int id) {
		authorLookupTable.put(name, id);
	}
	
	public ArrayList<Author> ReturnListOfAuthors() {
		return authors;
	}
	
	public int GetAuthorIdFromLookupTable(String name) {
		return authorLookupTable.get(name);
	}
	
	public Author GetAuthor(int id) {
		return authors.get(id);
	}
	
	public int GetTotalNumberOfAuthors() {
		return authors.size();
	}
	
	
	public void DisplayAllAuthorsInfo() {
		for(int i = 0; i < authors.size(); i++) {	
			System.out.println("Author Name: " + authors.get(i).name);	
			System.out.println("Author Id: " + authors.get(i).authorId);
			System.out.println("CoAuthors: ");
			authors.get(i).PrintAllCoAuthors();
			System.out.println("Published Papers:");
			authors.get(i).PrintAllPublishedPapers();
			System.out.println();
		}
	}
	
	//monica1
	/*
	public double getAverageDegree() {
		double medDegree = 0;
		int totalDegree = 0;
		ArrayList<Integer> coAuthorWeightings = new ArrayList<Integer>();
		for(int i = 0; i < authors.size(); i++) {
			/
			System.out.println("Author Name: " + authors.get(i).name);	
			System.out.println("Author Id: " + authors.get(i).authorId);
			System.out.println("CoAuthors: ");
			authors.get(i).PrintAllCoAuthors();
			System.out.println("Published Papers:");
			authors.get(i).PrintAllPublishedPapers();
			System.out.println();
			/
			coAuthorWeightings = authors.get(i).getAllCoAuthorsWeightings();
			for (int j = 0; j < coAuthorWeightings.size(); j++){
				totalDegree += coAuthorWeightings.get(j);
			}
			coAuthorWeightings.clear();
		}
		//System.out.println(totalDegree);
		medDegree = (double)totalDegree / (double)authors.size();
		return medDegree;
	}
	*/
	
	public void DisplayAllSpecialAuthorNameMappings() {
		Enumeration<String> en = specialNamesMapping.keys();
		while (en.hasMoreElements()) {
			String specialAuthorName = en.nextElement();
			System.out.println("Original: " + specialAuthorName + " New: " + specialNamesMapping.get(specialAuthorName));
		}
	}
	
	public void AddPaperToAuthor(int authorId, int paperId) {
		authors.get(authorId).AddPublishedPaper(paperId);
	}
	
	public Hashtable<String,String> GetSpecialNamesHashtable() {
		return specialNamesMapping;
	}
	
	public void IncrementSpecialNamesMapping(){
		specialCharacterCounter += 1;
	}
	
	public void AddNewSpecialAuthorNameToMapping(String authorName) {
		String specialCharacterName = "SpecialName" + Integer.toString(specialCharacterCounter);
		IncrementSpecialNamesMapping();
		specialNamesMapping.put(authorName, specialCharacterName);
		Author author = new Author(specialCharacterName, GetUniqueId());
		AddAuthor(author);
		IncrementUniqueId();
	}
	
	public boolean CheckIfAuthorExistsInSpecialCharacterTable(String authorName) {
		if(specialNamesMapping.containsKey(authorName)) {
			return true;
		} else{
			return false;
		}
	}
	
	public String GetSpecialCharacterMapping(String authorName) {
		if(specialNamesMapping.containsKey(authorName)) {
			return specialNamesMapping.get(authorName);
		} else {
			System.out.println("Cannot find author in special character mappings");
			return "";
		}
	}
	
}
