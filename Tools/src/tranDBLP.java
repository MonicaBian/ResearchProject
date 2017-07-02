import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class tranDBLP {

	public static void main(String[] args)   throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("BEGIN:");
		ArrayList<String> writtenPaperIDs = new ArrayList<String>();
		ArrayList<String> writtenPaperTitles = new ArrayList<String>();
		
		String inputFilePath = "/Users/monicarrbian/Downloads/DBLP/Interdisciplinary Studies.txt";


		String outputFilePath = "/Users/monicarrbian/Downloads/DBLP/IS-meta.txt";
		
		FileReader fr = new FileReader(inputFilePath);
		BufferedReader br = new BufferedReader(fr);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))){

			try {
				String s; 
				while((s = br.readLine()) != null) {
					if(s.contains("#*")) {
						//String[] newAuthors = new String[]{};
						String paperTitle = ProcessString(s, "#*");
						String s1 = br.readLine();
						String[] authors = new String[]{};
						String authorsList = ProcessString(s1, "#@");
						ArrayList<String> addedAuthors = new ArrayList<String>();
						if(authorsList.contains(",")){
							authors = authorsList.split(",");
							//System.out.println("authorsSize: " + authors.length);
							
							
							for(int i = 0; i < authors.length; i++) {
								if (!addedAuthors.contains(authors[i])){
									//newAuthors = addElement(newAuthors, authors[i]);
									addedAuthors.add(authors[i]);
								}
							}
						}

						
						String s2 = br.readLine();
						String paperYear = ProcessString(s2, "#t");
						
//						String s3 = br.readLine();
//						String paperVenue = "";
//						if  (s3.length() > 2){
//							String subOfS3 = s3.substring(0, 2);
//							if (subOfS3.equals("#c")){
//								paperVenue = ProcessString(s3, "#c");
//							}
//						}
//						
//						String s4 = br.readLine();
//						String paperID = "";
//						if (s4.length() > 6){
//							String subOfS4 = s4.substring(0, 6);
//							if (subOfS4.equals("#index")){
//								paperID = ProcessString(s4, "#index");
//							}
//						}
						
						String s3 = br.readLine();
						String paperVenue = ProcessString(s3, "#c");
						
						String s4 = br.readLine();
						String paperID = ProcessString(s4, "#index");
						
						int sizeOfAddedAuthors = addedAuthors.size();
						//System.out.println("addedAuthorsSize: " + sizeOfAddedAuthors);
						
						if ((sizeOfAddedAuthors> 1) & (!writtenPaperIDs.contains(paperID)) & (!writtenPaperTitles.contains(paperTitle)) & (!paperID.isEmpty()) & (!paperTitle.isEmpty())){
							//System.out.println("momoIsHere " + authors.length);
//							for(int i = 0; i < authors.length; i++) {
//								for(int j = i+1; j < authors.length; j++) {
//									System.out.println("momoIsHere " + i + j);
//									//try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))){
//										writer.write(authors[i] + " ==> " + authors[j]);
//										writer.newLine();
//	
//									//} catch (IOException e) {
//										//System.out.println("Exception");
//									//}
//									
//								}
//								
//							}
							
							
							writer.write("id = {" + paperID + "}");
							writer.newLine();
							writtenPaperIDs.add(paperID);
							
							String prepareAuthors = new String();				
							for(int i = 0; i < addedAuthors.size()-1; i++) {
								//writer.write("author = {" + authors[i] + "}");
								//prepareAuthors.concat(addedAuthors.get(i) + "; ");
								prepareAuthors = prepareAuthors + addedAuthors.get(i) + "; ";
							}
							prepareAuthors = prepareAuthors + addedAuthors.get(sizeOfAddedAuthors-1);
							writer.write("author = {" + prepareAuthors + "}");
							writer.newLine();
							
							writer.write("title = {" + paperTitle + "}");
							writer.newLine();
							writtenPaperTitles.add(paperTitle);
							
							writer.write("venue = {" + paperVenue + "}");
							writer.newLine();
							
							writer.write("year = {" + paperYear + "}");
							writer.newLine();
							writer.newLine();
							
						}
	
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		} catch (IOException e) {
			//System.out.println("Exception");
		}
		
		finally {
			fr.close();
			br.close();
		}
	}
	
	public static String ProcessString(String input, String delimiter) {
		String processedString = "";	
		int lenghtOfInput = input.length();
		int lenghtOfDelimiter = delimiter.length();
		if (lenghtOfInput > lenghtOfDelimiter){
			String subOfInput = input.substring(0, lenghtOfDelimiter);
			if (subOfInput.equals(delimiter)){
				processedString = input.substring(input.indexOf(delimiter) + delimiter.length(), input.length());
			}
		}	
		return processedString;
	}
	
	
//	static String[] addElement(String[] a, String e) {
//	    a  = Arrays.copyOf(a, a.length + 1);
//	    a[a.length - 1] = e;
//	    return a;
//	}

}
