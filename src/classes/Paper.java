package classes;

import java.util.ArrayList;

public class Paper {
	
	public String name;
	public String title;
	public int paperId;
	public ArrayList<Integer> references;
	public ArrayList<Integer> authors;
	
	public Paper(String name, int id) {
		this.name = name;
		paperId = id;
		references = new ArrayList<Integer>();
		authors = new ArrayList<Integer>();
	}
	
	public Paper(String name, int id, String title) {
		this.name = name;
		this.title = title;
		paperId = id;
		references = new ArrayList<Integer>();
		authors = new ArrayList<Integer>();
	}
	
	public void SetName(int newId) {
		paperId = newId;
	}
	
	public void AddNewReference(int referenceId) {
		references.add(referenceId);
	}
	
	public void AddAuthor(int authorId) {
		authors.add(authorId);
	}
	
	public void PrintAllReferneces() {
		for (int i = 0; i < references.size(); i++){
			System.out.println(references.get(i));
		}
	}
	
	public void PrintAllAuthors() {
		for (int i = 0; i < authors.size(); i++){
			System.out.println(authors.get(i));
		}
	}
	
	public void SetTitle(String newTitle) {
		title = newTitle;
	}
	
	public String GetTitle() {
		return title;
	}
	
	public boolean IsTitleNull(){
		if(title.isEmpty()){
			return true;
		}
		return false;
	}
}
