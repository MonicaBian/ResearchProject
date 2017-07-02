package classes;
import java.util.ArrayList;
import java.util.Hashtable;

public class PaperList {
	public ArrayList<Paper> papers;
	public Hashtable<String, Integer> paperLookupTable;
	public int uniqueId;
	
	public PaperList() {
		papers = new ArrayList<Paper>();
		paperLookupTable = new Hashtable<String, Integer>();
		uniqueId = 0;
	}
	
	public void AddPaper(Paper paper) {
		paperLookupTable.put(paper.name, paper.paperId);
		papers.add(paper);
	}
	
	public void IncrementUniqueId(){
		uniqueId += 1;
	}
	
	public int GetUniqueId() {
		return uniqueId;
	}
	
	public boolean CheckIfPaperExistsInLookupTable(String name) {
		if(paperLookupTable.containsKey(name)) {
			return true;
		} 
		return false;
	}
	
	public ArrayList<Paper> ReturnListOfPapers() {
		return papers;
	}
	
	public void AddPaperToLookupTable(String name, int id) {
		paperLookupTable.put(name, id);
	}
	/*
	public void SetTitleToAuthorListByPaperId (int paperId, String titleName) {
		GetPaper
	}*/
	
	public int GetPaperIdFromLookupTable(String name) {
		return paperLookupTable.get(name);
	}
	
	public void DisplayAllPapersInfo() {
		for(int i = 0; i < papers.size(); i++){
			System.out.println("Paper Name: " + papers.get(i).name);
			System.out.println("Paper Title: " + papers.get(i).title);
			System.out.println("Paper Id: " + papers.get(i).paperId);
			System.out.println("References: ");
			papers.get(i).PrintAllReferneces();
			System.out.println("Authors: ");
			papers.get(i).PrintAllAuthors();			
			System.out.println();
			
		}
	}
	
	public void AddAuthorToPaper(int authorId, int paperId) {
		papers.get(paperId).AddAuthor(authorId);
	}
	
	public void SetTitleToPaper(int paperId, String title) {
		papers.get(paperId).SetTitle(title);
	}

	public String GetTitleFromPaperId(int paperId) {
		return papers.get(paperId).title;
	}
	
	public ArrayList<Integer> GetAuthorsBasedOnPaperTitle(String title) {
		for(int i = 0; i < papers.size();i++){
			if(!(papers.get(i).IsTitleNull())){
				if((papers.get(i).title).equals(title)){
					//System.out.println("Found Authors based on title: " + title);
					//System.out.println(papers.get(i).authors);
					return papers.get(i).authors;
				}
			}
		}
		System.out.println("COULD NOT FIND PAPER BASED ON TITLE!");
		return null;
	}
	
	//momo
	public Integer GetPaperIdBasedOnPaperTitle(String title) {
		for(int i = 0; i < papers.size();i++){
			if(!(papers.get(i).IsTitleNull())){
				if((papers.get(i).title).equals(title)){
					//System.out.println("Found Authors based on title: " + title);
					//System.out.println(papers.get(i).authors);
					return papers.get(i).paperId;
				}
			}
		}
		System.out.println("COULD NOT FIND PAPER BASED ON TITLE!");
		return null;
	}
	
	public int GetTotalNumberOfPapers() {
		return papers.size();
	}
}