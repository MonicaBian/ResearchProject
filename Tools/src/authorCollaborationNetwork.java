import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class authorCollaborationNetwork {

	public static void main(String[] args)  throws IOException {
		// TODO Auto-generated method stub
		System.out.println("BEGIN:");
		String networkConnectionFilePath = "/Users/monicarrbian/Downloads/DBLP/IS-meta.txt";
		
		String outputFilePath = "/Users/monicarrbian/Downloads/DBLP/IS-collaboration.txt";
		
		FileReader fr = new FileReader(networkConnectionFilePath);
		BufferedReader br = new BufferedReader(fr);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))){

			try {
				String s; 
				while((s = br.readLine()) != null) {
					if(s.contains("author = ")) {
						//String s2 = br.readLine();
						String[] authors;
						//System.out.println("s1: " + s);
						String authorsList = ProcessString(s);
						//System.out.println("authorsList2: " + authorsList);
						if(authorsList.contains(";")){
							authors = authorsList.split("; ");
							System.out.println("authorsSize3: " + authors.length);
						} else {
							authors = new String[]{authorsList};
						}
						
						if (authors.length>1){
							//System.out.println("momoIsHere " + authors.length);
							for(int i = 0; i < authors.length; i++) {
								for(int j = i+1; j < authors.length; j++) {
									System.out.println("momoIsHere " + i + j);
									//try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))){
										writer.write(authors[i] + " ==> " + authors[j]);
										writer.newLine();
	
									//} catch (IOException e) {
										//System.out.println("Exception");
									//}
									
								}
								
							}
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
	
	
	public static String ProcessString(String input) {
		String processedString = input.substring(input.indexOf("{") + 1, input.indexOf("}"));
		
		return processedString;
	}

}
