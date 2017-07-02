package main;
import classes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



public class ExecuteProcess {

	public static void main(String[] args) throws IOException {

		System.out.println("BEGIN:");
/*																// Diagram 1
		String citationNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/paper-citation-network.txt";
		String authorNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/author-collaboration-network.txt";
		String networkConnectionFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/acl-metadata.txt";
		
		String outputFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/communities.txt";*/
		
		
/*		// Diagram 2
		String citationNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/paper-citation-network.txt";
		String authorNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/Diagram2/author-collaboration-network.txt";
		String networkConnectionFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/Diagram2/acl-metadata.txt";
		
		String outputFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/Diagram2/communities.txt";*/
		
		
/*		// Diagram 3
		//String citationNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/paper-citation-network.txt";
		String authorNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/Diagram3/author-collaboration-network.txt";
		String networkConnectionFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/Diagram3/acl-metadata.txt";
		
		String outputFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/Diagram3/communities.txt";*/
		
/*		// AAN dataset
		String citationNetworkFilePath = "/Users/rbia002/Documents/paper-citation-network.txt";
		String authorNetworkFilePath = "/Users/rbia002/Documents/author-collaboration-network.txt";
		String networkConnectionFilePath = "/Users/rbia002/Documents/acl-metadata.txt";

		String outputFilePath = "/Users/rbia002/Documents/communities.txt";*/
		
/*		// AAN SmallOneThird
		String citationNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/OriginalAAI201629Sep/SmallOneThird/paper-citation-network.txt";
		String authorNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/OriginalAAI201629Sep/SmallOneThird/author-collaboration-network.txt";
		String networkConnectionFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/OriginalAAI201629Sep/SmallOneThird/acl-metadata.txt";
		
		String outputFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/OriginalAAI201629Sep/SmallOneThird/communities.txt";*/
		
		// AAN Half
		String citationNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/OriginalAAI201629Sep/Half/paper-citation-network.txt";
		String authorNetworkFilePath = "/Users/monicarrbian/Downloads/DBLP/HCI-collaboration.txt";
		String networkConnectionFilePath = "/Users/monicarrbian/Downloads/DBLP/HCI-meta.txt";
		
		String outputFilePath = "/Users/monicarrbian/Downloads/DBLP/communities.txt";

/*		//Diagram Metrics
		String citationNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/DiagramMetrics/TestScenario5/paper-citation-network.txt";
		String authorNetworkFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/DiagramMetrics/TestScenario5/author-collaboration-network.txt";
		String networkConnectionFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/DiagramMetrics/TestScenario5/acl-metadata.txt";
		
		String outputFilePath = "/Volumes/SONY_128W3/Except/PhDImplementation/OurDatasets/TestDatasetsFromAAAI201629Sep/cfolder/DiagramMetrics/TestScenario5/communities.txt";*/
				
		String delimiter = "==>";
		int threshold = 1;
		int communityThreshold = 400000000;
		ArrayList<Integer> totalCoauthorDegrees = new ArrayList<Integer>();
		ArrayList<Double> sdTotalCoauthorDegrees = new ArrayList<Double>();
		int coauthorDegreesSum = 0;
		double averageCoauthorDegree = 0;
		
		
		AuthorList authorList = ProcessAuthors(authorNetworkFilePath, delimiter);	
		//PaperList paperList = ProcessPapers(citationNetworkFilePath, delimiter);
		PaperList paperList = new PaperList();
		
		
		Hashtable<String, String> specialAuthorNames = authorList.GetSpecialNamesHashtable();
		//authorList.DisplayAllAuthorsInfo();
		//paperList.DisplayAllPapersInfo();
		//authorList.DisplayAllSpecialAuthorNameMappings();
		//System.out.println(authorList.getAverageDegree());
		
		FileReader fr = new FileReader(networkConnectionFilePath);
		BufferedReader br = new BufferedReader(fr);
	
		try {
			String s; 
			while((s = br.readLine()) != null) {
				// If current line has an paper name
				if(s.contains("id = ")) {
					
					String s2 = br.readLine();
					if (s2.contains(";;")) {
						s2 = s2.replace(";;", ";");
					}
					
					Enumeration<String> en = specialAuthorNames.keys();
					while (en.hasMoreElements()) {
						String specialAuthorName = en.nextElement();
						if(s2.contains(specialAuthorName)) {		
							s2 = s2.replace(specialAuthorName, specialAuthorNames.get(specialAuthorName));						
						}
					}

					
					String s3 = br.readLine();
					
					String paperName = ProcessString(s).trim();
					String title = ProcessString(s3).trim();
					

					
					if (!(paperList.CheckIfPaperExistsInLookupTable(paperName))) {
						//System.out.println("Could not find paper: " + paperName);
						Paper paper = new Paper(paperName, paperList.GetUniqueId(),title);
						paperList.AddPaper(paper);		
						paperList.IncrementUniqueId();
					} else {
						int idOfExistingPaper = paperList.GetPaperIdFromLookupTable(paperName);
						paperList.SetTitleToPaper(idOfExistingPaper, title);
						
						if (title.equals("Syntactic And Semantic Knowledge In The DELPHI Unification Grammar")) {
							//System.out.println("found it");
							//System.out.println(paperName);
							//System.out.println(idOfExistingPaper);
							//System.out.println("found: " + paperList.GetTitleFromPaperId(idOfExistingPaper));
						}
					}
					
					int paperId = paperList.GetPaperIdFromLookupTable(paperName);
					String[] listOfAuthors = ProcessAuthorListString(s2);
					//System.out.println(paperName);
					//System.out.println(title);
							
					for(int i = 0; i < listOfAuthors.length; i++){
						if (listOfAuthors[i].contains("SpecialName")){
							//System.out.println(listOfAuthors[i]);
						}
						if (!(authorList.CheckIfAuthorExistsInLookupTable(listOfAuthors[i]))) {
							//System.out.println("Author: '" + listOfAuthors[i] + "' does not exist, adding author");
							Author author = new Author(listOfAuthors[i], authorList.GetUniqueId());
							authorList.AddAuthor(author);		
							authorList.IncrementUniqueId();
						}

						//System.out.println("size of list of authors " + listOfAuthors.length);
						int authorId = authorList.GetAuthorIdFromLookupTable(listOfAuthors[i]);
						//Author currentAuthor = authorList.GetAuthor(authorId);
						//ArrayList<Integer> allCoAuthorDegrees = new ArrayList<Integer>();
						//allCoAuthorDegrees = currentAuthor.getAllCoauthorDegrees();
						//int h = 0;
						//for (int j: allCoAuthorDegrees){
							//h++;
							//System.out.println("h is " + h + " j is" + j);
							//totalCoauthorDegrees.add(j);
						//}
						
						
						authorList.AddPaperToAuthor(authorId, paperId);	
						paperList.AddAuthorToPaper(authorId, paperId);
					}
					
					paperList.SetTitleToPaper(paperId, title);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fr.close();
			br.close();
		}
		
		for (int i = 0; i < authorList.GetTotalNumberOfAuthors(); i++){
			//System.out.println("1:" + authorList.GetTotalNumberOfAuthors());
			Author currentAuthor = authorList.GetAuthor(i);
			//System.out.println("2:" + i);
			//System.out.println("2:" + authorList.GetAuthor(i).name);
			ArrayList<Integer> allCoAuthorDegrees = new ArrayList<Integer>();
			allCoAuthorDegrees = currentAuthor.getAllCoauthorDegrees();
			//System.out.println("3:" + allCoAuthorDegrees);
			//int h = 0;
			for (int j: allCoAuthorDegrees){
				//h++;
				//System.out.println("h is " + h + " j is" + j);
				totalCoauthorDegrees.add(j);
			}
			
		}
		
		for (int i: totalCoauthorDegrees){
			coauthorDegreesSum += i;
		}
	
		averageCoauthorDegree = (double)coauthorDegreesSum / (double)totalCoauthorDegrees.size();
		//System.out.println("cal coauthorDegreesSum: " + coauthorDegreesSum);
		//System.out.println("cal totalCoauthorDegrees.size(): " + totalCoauthorDegrees.size());
		//System.out.println("cal: " + totalCoauthorDegrees.size());
		
		
		for (int q : totalCoauthorDegrees){
			sdTotalCoauthorDegrees.add(Math.pow((q-averageCoauthorDegree), 2));
			//int h = 0;
		}
		
		double totalSDCoauthorDegree = 0;
		
		for (double w : sdTotalCoauthorDegrees){
			totalSDCoauthorDegree += w;
		}
		
		double coauthorSD = Math.sqrt(totalSDCoauthorDegree / sdTotalCoauthorDegrees.size());
		
		/*		averageCommunitySize = (double)totalCommunitySize/(double)numberOfCommunitiesOverThreshold;
		//System.out.println(allCommunitySizes.length);
		for(int i = 0; i < allCommunitySizes.length; i++){
			sdArray = addElementDouble(sdArray, Math.pow((allCommunitySizes[i]-averageCommunitySize),2));
			//System.out.println("sd " + sdArray.length);
			}
		double totalSD = 0;

		for(int i = 0; i < sdArray.length; i++){
		   //System.out.println(sdArray[i]);
		   totalSD += sdArray[i]; // this is the calculation for summing up all the values
		}

		double communitySizeSD = Math.sqrt(totalSD / sdArray.length);
		//System.out.println("total " + totalSD);
		//System.out.println("sdArray " + sdArray.length);
		 * 
		 */
		
		System.out.println("Data Processing Complete");
		//System.out.println("Sum " + coauthorDegreesSum);
		//System.out.println("Size " + totalCoauthorDegrees.size());
		System.out.println("Average edge weight in author-collaboration network: " + averageCoauthorDegree);
		System.out.println("Standard deviation edge weight in author-collaboration network: " + coauthorSD);
		int numberOfCommunitiesOverThreshold = 0;
		int numberOfIterations = 0;
		while ((numberOfCommunitiesOverThreshold > communityThreshold) || (numberOfIterations == 0)) {
			long startTime = System.currentTimeMillis();
			
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))){
				numberOfCommunitiesOverThreshold = CreateCommunities(authorList, paperList, threshold, writer);

			} catch (IOException e) {
				System.out.println("Exception");
			} 
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			System.out.println("Total Running time (milliseconds): " + totalTime);
			System.out.println("Threshold: " + threshold);
			System.out.println("Total Number of Authors:" + authorList.GetTotalNumberOfAuthors());
			System.out.println("Total Number of Papers:" + paperList.GetTotalNumberOfPapers());
			numberOfIterations++;
			System.out.println("Iteration: " + numberOfIterations);
			threshold += 2;
			System.out.println();
			
		}
	}

	
	public static int CreateCommunities(AuthorList authorList, PaperList paperList, int threshold, BufferedWriter writer) throws IOException {
		Hashtable<ArrayList<Integer>, Integer> communities = new Hashtable<ArrayList<Integer>,Integer>();
		Hashtable<ArrayList<Integer>, ArrayList<String>> papers = new Hashtable<ArrayList<Integer>, ArrayList<String>>();
		//int totalAuthorPaperNumber = totalAuthorNumber + totalPaperNumber;
		int totalTPR = 0;
		double totalFractionOfHigher = 0;
		double totalExpansion = 0;
		double totalConductance = 0;
		double totalFlakeODF = 0;
		//double[] sdArray = new double[0];
		int totalCommunitySize = 0;
		double averageCommunitySize = 0;
		//12 June Validation
		List<Double> listallFOMDValues = new ArrayList<Double>();
		//double[] allFOMDValues = new double[0];
		Hashtable<ArrayList<Integer>, Double> commFOMD = new Hashtable<ArrayList<Integer>,Double>();
		List<Double> listallExpansionValues = new ArrayList<Double>();
		//double[] allExpansionValues = new double[0];
		Hashtable<ArrayList<Integer>, Double> commExpansion = new Hashtable<ArrayList<Integer>,Double>();
		List<Double> listallConductanceValues = new ArrayList<Double>();
		//double[] allConductanceValues = new double[0];
		Hashtable<ArrayList<Integer>, Double> commconductance = new Hashtable<ArrayList<Integer>,Double>();
		List<Double> listallFlakeODFValues = new ArrayList<Double>();
		//double[] allFlakeODFValues = new double[0];
		Hashtable<ArrayList<Integer>, Double> commFlakeODF = new Hashtable<ArrayList<Integer>,Double>();
		
		
		//6 June
		ArrayList<Integer> totalCommSizesSD = new ArrayList<Integer>();
		ArrayList<Double> sdTotalCommSizes = new ArrayList<Double>();
	
		//26 April FOMD
		//int[] allDegreeValues = new int[0];
		List<Integer> listallDegreeValues = new ArrayList<Integer>();
		ArrayList<Paper> listOfPapersFOMD = paperList.ReturnListOfPapers();
		for(int paperId = 0; paperId < listOfPapersFOMD.size(); paperId++) {
			Paper thePaper = listOfPapersFOMD.get(paperId);
			int thePaperAuthorSize = thePaper.authors.size();
			//allDegreeValues = addElement(allDegreeValues, thePaperAuthorSize);
			listallDegreeValues.add(thePaperAuthorSize);		
		}
		ArrayList<Author> listOfAuthorsFOMD = authorList.ReturnListOfAuthors();
		for(int authorId = 0; authorId < listOfAuthorsFOMD.size(); authorId++) {
			Author theAuthor = listOfAuthorsFOMD.get(authorId);
			int theAuthorDegree = theAuthor.getAllCombinedDegree();
			//allDegreeValues = addElement(allDegreeValues, theAuthorDegree);
			listallDegreeValues.add(theAuthorDegree);
		}
		int[] allDegreeValues = new int[listallDegreeValues.size()];
		for(int i = 0; i < allDegreeValues.length; i++){
			allDegreeValues[i] = listallDegreeValues.get(i);
		}
		double medianDegree = getDegreeMedian(allDegreeValues);
		//for (int i : allDegreeValues){
			//System.out.println("26 April allDegreeValues: " + i);
		//}

		
		//Step1A
		ArrayList<Paper> listOfPapers = paperList.ReturnListOfPapers();
		for(int currentPaperId = 0; currentPaperId < listOfPapers.size(); currentPaperId++) {
			Paper currentPaper = listOfPapers.get(currentPaperId);
			ArrayList<Integer> currentPaperAuthors = currentPaper.authors;
			if(!communities.containsKey(currentPaperAuthors)) {
				communities.put(currentPaperAuthors, 1);
				ArrayList<String> firstPaper = new ArrayList<String>();
				firstPaper.add(currentPaper.title);
				papers.put(currentPaperAuthors, firstPaper);
			} else {
				int currentCommunityWeighting = communities.get(currentPaperAuthors);
				ArrayList<String> currentPapers = new ArrayList<String>();
				for (String currentPaperTitleStep1 : papers.get(currentPaperAuthors)){
					currentPapers.add(currentPaperTitleStep1);
				}
				currentPapers.add(currentPaper.title);
				papers.put(currentPaperAuthors, currentPapers);
				communities.replace(currentPaperAuthors, currentCommunityWeighting, currentCommunityWeighting +1 );
			}
		}
		//Step1A
		
		
		//Step1B - getting rid of communities with solo author & sort community authors
		try {
			Enumeration<ArrayList<Integer>> en = communities.keys();
			while (en.hasMoreElements()) {
				ArrayList<Integer> currentCommunity = en.nextElement();
				
				int communityAuthorSize = currentCommunity.size();
				if(communityAuthorSize == 1) {
					communities.remove(currentCommunity);
					
					//8 May
					papers.remove(currentCommunity);
				}
				
				if (communityAuthorSize > 1){
					ArrayList<Integer> sortedCommunities = new ArrayList<Integer>();
					for (Integer element : currentCommunity){
						sortedCommunities.add(element);
					}
					sortedCommunities.sort(null);
					if (!sortedCommunities.equals(currentCommunity)){
						communities.put(sortedCommunities, communities.get(currentCommunity));
						papers.put(sortedCommunities, papers.get(currentCommunity));
						communities.remove(currentCommunity);
						
						//8 May
						papers.remove(currentCommunity);
					}
				}
			}
			}catch (Exception e) {
				throw e;
			}
		
		//Step1B
		
		
		//Step2
		//16 May Right before Step2
		Hashtable<ArrayList<Integer>, Integer> newCommunities = new Hashtable<ArrayList<Integer>,Integer>();
		Hashtable<ArrayList<Integer>, ArrayList<String>> newPapers = new Hashtable<ArrayList<Integer>, ArrayList<String>>();
		try {
			//8 May
			//Hashtable copiedCommunities = new Hashtable();
			//copiedCommunities.putAll(communities);
			//9 May
			//Hashtable copiedPapers = new Hashtable();
			//copiedPapers.putAll(papers);
			// 8 May
			Enumeration<ArrayList<Integer>> enFirstSec = communities.keys();
			while (enFirstSec.hasMoreElements()) {
				ArrayList<Integer> communityFirstSec = enFirstSec.nextElement();
				//16 May
				ArrayList<Integer> originalKey = new ArrayList<Integer>();
				ArrayList<Integer> currentKey = new ArrayList<Integer>();
				//30 May
				Hashtable<ArrayList<Integer>, ArrayList<Integer>> recCoauthors = new Hashtable<ArrayList<Integer>, ArrayList<Integer>>();
				for(int i = 0; i < communityFirstSec.size(); i++) {
					int authorId = communityFirstSec.get(i);
					originalKey.add(authorId);
					currentKey.add(authorId);
//					//28 May 97
//					String authorName = authorList.GetAuthor(97).name;
//					System.out.println("97 is: " + authorName);	
				}
				//24 May
				AtomicInteger myCurrentWeighting = new AtomicInteger(communities.get(currentKey));
				ArrayList<String> myCurrentPapers = new ArrayList<String>();
				for (String thePaper : papers.get(currentKey)){
					myCurrentPapers.add(thePaper);
				}
				ArrayList<ArrayList<Integer>> authorIDComFirst = new ArrayList<>();
				for (int i = 0; i<originalKey.size(); i++){
					for (int j = i+1; j<originalKey.size(); j++) {
						ArrayList<Integer> tempAuthorIDs = new ArrayList<Integer>();
						tempAuthorIDs.add(originalKey.get(i));
						tempAuthorIDs.add(originalKey.get(j));
						authorIDComFirst.add(tempAuthorIDs);
					}
				}
				//core logic engine for finding triangle-forming coauthor
				for ( ArrayList<Integer> idList : authorIDComFirst){
					ArrayList<ArrayList<Integer>> firstCoauthors = new ArrayList<>();
					ArrayList<ArrayList<Integer>> secondCoauthors = new ArrayList<>();
					Integer firstID = idList.get(0);
					Integer secondID = idList.get(1);
					Enumeration<ArrayList<Integer>> enSecondSec = communities.keys();
					while (enSecondSec.hasMoreElements()) {
						ArrayList<Integer> communitySecondSec = enSecondSec.nextElement();		
						//16 May
						ArrayList<Integer> authorIDsSecond = new ArrayList<Integer>();
						for(int i = 0; i < communitySecondSec.size(); i++) {
							int authorId = communitySecondSec.get(i);
							authorIDsSecond.add(authorId);
							//String authorNameSec = authorList.GetAuthor(authorId).name;
							//System.out.println(authorNameSec  + authorId);
						}

						if ((!authorIDsSecond.containsAll(idList)) && authorIDsSecond.contains(firstID)){
							authorIDsSecond.remove(Integer.valueOf(firstID));
							firstCoauthors.add(authorIDsSecond);
						}
								
						if ((!authorIDsSecond.containsAll(idList)) && authorIDsSecond.contains(secondID)){
							authorIDsSecond.remove(Integer.valueOf(secondID));
							secondCoauthors.add(authorIDsSecond);		
						}
					}

					for ( ArrayList<Integer> coauthor : firstCoauthors){
						for (Integer coauthorIndividual : coauthor){
							for (ArrayList<Integer> coauthorSec : secondCoauthors){
								if (coauthorSec.contains(coauthorIndividual)){
										ArrayList<Integer> firstCoauthorID = new ArrayList<Integer>();
										firstCoauthorID.add(firstID);
										for (Integer individualCoauthor : coauthor){
											firstCoauthorID.add(individualCoauthor);
										}
										firstCoauthorID.sort(null);
										ArrayList<String> firstIDPapers = papers.get(firstCoauthorID);
										
										ArrayList<Integer> secondCoauthorID = new ArrayList<Integer>();
										secondCoauthorID.add(secondID);
										for (Integer individualCoauthorSec : coauthorSec){
											secondCoauthorID.add(individualCoauthorSec);
										};
										secondCoauthorID.sort(null);
										ArrayList<String> secondIDPapers = papers.get(secondCoauthorID);
										
										for (String firstPaper : firstIDPapers){
											if (!myCurrentPapers.contains(firstPaper)){
												myCurrentPapers.add(firstPaper);
												myCurrentWeighting.incrementAndGet();
											}
										}
										
										for (String secondPaper : secondIDPapers){
											if (!myCurrentPapers.contains(secondPaper)){
												myCurrentPapers.add(secondPaper);
												myCurrentWeighting.incrementAndGet();
											}
										}
										
										if (!currentKey.contains(coauthorIndividual)){
											currentKey.add(coauthorIndividual);
											currentKey.sort(null);
										}
										
										//30 May
//										ArrayList<Integer> recKey = new ArrayList<Integer>();
//										boolean recToGo = true;
//										recKey.add(firstID);
//										recKey.add(secondID);
//										recKey.sort(null);
//										if (recCoauthors.containsKey(recKey)){
//											if (recCoauthors.get(recKey).contains(coauthorIndividual)){
//												recToGo = false;
//											}
//										}
										
										
										//if (!originalKey.contains(coauthorIndividual) && recToGo){
										if (!originalKey.contains(coauthorIndividual)){	
											//30 May
//											if(!recCoauthors.containsKey(recKey)) {
//												ArrayList<Integer> recCoauthorIndi = new ArrayList<Integer>();
//												recCoauthorIndi.add(coauthorIndividual);
//												recCoauthors.put(recKey, recCoauthorIndi);
//											} else {
//												ArrayList<Integer> recCoauthorCurrent = new ArrayList<Integer>();
//												for (Integer eachCoauthor : recCoauthors.get(recKey)){
//													recCoauthorCurrent.add(eachCoauthor);
//												}
//												recCoauthorCurrent.add(coauthorIndividual);
//												//recCoauthorCurrent.sort(null);
//												recCoauthors.replace(recKey, recCoauthors.get(recKey),  recCoauthorCurrent);
//											}
											
											
											recOrder(coauthorIndividual, firstID, secondID, originalKey, currentKey, 
													communities, papers, myCurrentPapers, myCurrentWeighting, recCoauthors);
										}
									}
								}
								}
							}
					}
				//16 May
				if (!newCommunities.containsKey(currentKey)) {
					//24 May
					Integer integerMyCurrentWeighting = myCurrentWeighting.get();
					newCommunities.put(currentKey, integerMyCurrentWeighting);
					newPapers.put(currentKey, myCurrentPapers);
				} else {
					ArrayList<String> existingPapers = new ArrayList<String>();
					for (String existingPaperTitle : newPapers.get(currentKey)){
						existingPapers.add(existingPaperTitle);
					}
					int toAddWeighting = 0;
					for (String toAddPaper : myCurrentPapers){
						if (!existingPapers.contains(toAddPaper)){
							existingPapers.add(toAddPaper);
							toAddWeighting++;
						}
					}
					newCommunities.replace(currentKey, newCommunities.get(currentKey), newCommunities.get(currentKey)+toAddWeighting);
					newPapers.replace(currentKey, newPapers.get(currentKey), existingPapers);
				}
			}
		} catch (Exception e) {
				throw e;
		}
		//Step2
		
		
		//Step3
		try {
			//17 May
			Enumeration<ArrayList<Integer>> enStepThree = newCommunities.keys();	
			while (enStepThree.hasMoreElements()) {
				ArrayList<Integer> communityStepThree = enStepThree.nextElement();
				ArrayList<Integer> authorIDsStepThree = new ArrayList<Integer>();
				for(int i = 0; i < communityStepThree.size(); i++) {
					int authorIdThree = communityStepThree.get(i);
					authorIDsStepThree.add(authorIdThree);
				}
				//17 May
				ArrayList<String> paperStepThree = newPapers.get(communityStepThree);
				//17 May
				Enumeration<ArrayList<Integer>> enStepThreeOne = newCommunities.keys();
				while (enStepThreeOne.hasMoreElements()) {
					ArrayList<Integer> communityStepThreeOne = enStepThreeOne.nextElement();
					ArrayList<Integer> authorIDsStepThreeOne = new ArrayList<Integer>();
					for(int i = 0; i < communityStepThreeOne.size(); i++) {
						int authorIdThreeOne = communityStepThreeOne.get(i);
						authorIDsStepThreeOne.add(authorIdThreeOne);
					}
					//17 May
					ArrayList<String> paperStepThreeOne = newPapers.get(communityStepThreeOne);
					if ((!authorIDsStepThreeOne.equals(authorIDsStepThree)) && (authorIDsStepThreeOne.containsAll(authorIDsStepThree)) && (paperStepThreeOne.containsAll(paperStepThree))){
						//17 May
						newCommunities.remove(authorIDsStepThree);
						//17 May
						newPapers.remove(authorIDsStepThree);
						}
					}
				}
			} catch (Exception e) {
				throw e;
			}
		//Step3
	
		
		//Calculations
		int numberOfCommunitiesOverThreshold = 0;
		try {
			//17 May
			Enumeration<ArrayList<Integer>> en = newCommunities.keys();
			while (en.hasMoreElements()) {
				ArrayList<Integer> community = en.nextElement();
				int communityAuthorSize = community.size();
				//17 May
				int communityWeighting = newCommunities.get(community);				
				// Print out all communities in threshold
				if((communityAuthorSize > 1) && (communityWeighting >= threshold)) {
					numberOfCommunitiesOverThreshold++;
					int totalExternalDegree = 0;
					int totalInternalDegree = 0;
					int numberOfHigher = 0;
					double fractionOfHihger = 0;
					int triangleParticipationRatio = 0;
					//25 April
					double expansion = 0;

					double conductance = 0;
					int numberOfLower = 0;
					double flakeODF = 0;
					ArrayList<Integer> paperAuthors = new ArrayList<Integer>();
					ArrayList<Integer> commAuthors = new ArrayList<Integer>();
					Hashtable<Integer, Integer> commAuthorsDegrees = new Hashtable<Integer,Integer>();
					//17 May
					int communityPaperSize = newPapers.get(community).size();
					int CommunityCombinedSize = communityAuthorSize + communityPaperSize;
					writer.write("Paper: " + newPapers.get(community).toString());
					writer.newLine();
					writer.write("Community: " + GetAuthorNames(community, authorList));
					writer.newLine();
					writer.write("Weighting: " + communityWeighting);
					writer.newLine();
					//24 April
					for(int i = 0; i < communityAuthorSize; i++){
						int authorIndiId = community.get(i);
						commAuthors.add(authorIndiId);
					}
					for (int j = 0; j < communityPaperSize; j++){
						//17 May
						String paperTitle = newPapers.get(community).get(j);
						//24 April
						int paperInternalDegree = 0;
						int paperExternalDegree = 0;
						int includedAuthorNO = 0;
						ArrayList<Integer> includedAuthors = new ArrayList<Integer>();
						paperAuthors = paperList.GetAuthorsBasedOnPaperTitle(paperTitle);
						for(int paperAuthor: paperAuthors){
							if (commAuthors.contains(paperAuthor)){
								paperInternalDegree++;
								totalInternalDegree++;
								includedAuthorNO++;
								includedAuthors.add(paperAuthor);
								if(!commAuthorsDegrees.containsKey(paperAuthor)) {
									commAuthorsDegrees.put(paperAuthor, 1);
								}else{
									int currentAuthorDegree = commAuthorsDegrees.get(paperAuthor);
									commAuthorsDegrees.replace(paperAuthor, currentAuthorDegree, currentAuthorDegree +1 );
								}
							} else {
								paperExternalDegree++;
								totalExternalDegree++;
							}
						}
						if (paperInternalDegree < paperExternalDegree){
							numberOfLower++;
						}
						//26 April FOMD
						if (paperInternalDegree > medianDegree){
							numberOfHigher++;
						}
						if (includedAuthorNO > 1){
							for (int includedAuthor : includedAuthors){
								int currentIncludedAuthorDegree = commAuthorsDegrees.get(includedAuthor);
								commAuthorsDegrees.replace(includedAuthor, currentIncludedAuthorDegree, currentIncludedAuthorDegree + (includedAuthorNO -1) );
							}
						}
					}
					
					for (int CommAuthor : commAuthors){
						int authorInternalDegree = 0;
						int authorExternalDegree = 0;
						Author currentCommAuthor = authorList.GetAuthor(CommAuthor);
						authorInternalDegree = commAuthorsDegrees.get(CommAuthor);
						authorExternalDegree = currentCommAuthor.getAllCombinedDegree() - authorInternalDegree;
						if (authorInternalDegree < authorExternalDegree){
							numberOfLower++;
						}
						if (authorInternalDegree > medianDegree){
							numberOfHigher++;
						}
						totalExternalDegree += authorExternalDegree;
						totalInternalDegree += authorInternalDegree;
						
					}
					fractionOfHihger = (double)numberOfHigher / (double)CommunityCombinedSize;
					//25 April
					expansion = (double)totalExternalDegree / (double)CommunityCombinedSize;

					conductance = (double) totalExternalDegree / (double) (totalInternalDegree + totalExternalDegree);
					flakeODF = (double)numberOfLower / (double)CommunityCombinedSize;
					//monica4
					if (CommunityCombinedSize >= 3){
						triangleParticipationRatio = 1;
						writer.write( "Combined community size is " + CommunityCombinedSize + " ");
						writer.write("Triangle participation ratio (TPR): " + triangleParticipationRatio);
						writer.newLine();
					}else{
						writer.write( "Combined community size is " + CommunityCombinedSize + " ");
						writer.write("Triangle participation ratio (TPR): " + triangleParticipationRatio);
						writer.newLine();
					}
					totalTPR += triangleParticipationRatio;
					totalCommunitySize += CommunityCombinedSize;
					//6 June 2017
					totalCommSizesSD.add(CommunityCombinedSize);

					writer.write("Median degree: " + medianDegree + " Number of higher: " + numberOfHigher + " ");
					writer.write("Fraction over median degree (FOMD): " + fractionOfHihger);
					writer.newLine();
					totalFractionOfHigher += fractionOfHihger;
					//25 April
					writer.write("External: " + totalExternalDegree + " ");
					writer.write("Expansion: " + expansion);
					writer.newLine();
					totalExpansion += expansion;
					writer.write("Internal: " + totalInternalDegree + " ");
					writer.write("External: " + totalExternalDegree + " ");
					writer.write("Conductance: " + conductance);
					writer.newLine();
					totalConductance += conductance;
					writer.write("Community Size: " + CommunityCombinedSize + " Number of lower: " + numberOfLower + " ");
					writer.write("Flake ODF: " + flakeODF);
					writer.newLine();
					writer.newLine();
					totalFlakeODF += flakeODF;
					
					//12 June Validation
					listallFOMDValues.add(fractionOfHihger);
					//allFOMDValues = addElementDouble(allFOMDValues, fractionOfHihger);
					commFOMD.put(community, fractionOfHihger);
					
					//14 June Validation
					listallExpansionValues.add(expansion);
					//allExpansionValues = addElementDouble(allExpansionValues, expansion);
					commExpansion.put(community, expansion);
					
					//14 June Validation
					listallConductanceValues.add(conductance);
					//allConductanceValues = addElementDouble(allConductanceValues, conductance);
					commconductance.put(community, conductance);
					
					//14 June Validation
					listallFlakeODFValues.add(flakeODF);
					//allFlakeODFValues = addElementDouble(allFlakeODFValues, flakeODF);
					commFlakeODF.put(community, flakeODF);
					
					}
				}
		} catch (IOException e) {
			throw e;
		}
		//Calculations
		

		//12 June Validation - FOMD
		writer.write("Validation FOMD begins");
		writer.newLine();
		double[] allFOMDValues = new double[listallFOMDValues.size()];
		for(int i = 0; i < allFOMDValues.length; i++){
			allFOMDValues[i] = listallFOMDValues.get(i);
		}
		Arrays.sort(allFOMDValues);
		int allFOMDValuesLength = allFOMDValues.length;
		int topK = 1;
		int totalCombined = 0;
		int numHigher = 0;
		double lastFOMDValue = 0;
		for (int i=allFOMDValuesLength-1; i>=0; i--){
			if (topK <= 10){
				double currentTopFOMD = allFOMDValues[i];
				int timeToRun = 1;
				if ((currentTopFOMD != lastFOMDValue)){
				    for (ArrayList<Integer> commAuthorsKey : commFOMD.keySet()) {
				        if ((timeToRun == 1) && (commFOMD.get(commAuthorsKey).equals(currentTopFOMD))) {
//				        	writer.write("Top " + topK);
//				        	writer.newLine();
//							writer.write("Paper: " + newPapers.get(commAuthorsKey).toString());
//							writer.newLine();
//							writer.write("Community: " + GetAuthorNames(commAuthorsKey, authorList));
//							writer.newLine();
//							writer.write("Fraction over median degree (FOMD): " + currentTopFOMD);
//							writer.newLine();
//							writer.newLine();
							
							int combined = 1;
							topK++;
							timeToRun++;
							lastFOMDValue = currentTopFOMD;
							
							//19 June Getting rid of duplicate combined communities
							ArrayList<ArrayList<Integer>> alreadyCombinedAuthors = new ArrayList<ArrayList<Integer>>();
							
							for (Integer commEachAuthor : commAuthorsKey){
								Enumeration<ArrayList<Integer>> enVal = newCommunities.keys();
								while (enVal.hasMoreElements()) {
									ArrayList<Integer> communityVal = enVal.nextElement();
									if ((communityVal.contains(commEachAuthor)) && (!communityVal.equals(commAuthorsKey))){
										int combinedNumberOfHigher = 0;
										Hashtable<Integer, Integer> combinedCommAuthorsDegrees = new Hashtable<Integer,Integer>();
										ArrayList<Integer> combinedAuthors = new ArrayList<Integer>();
										for (Integer authorFOMD : commAuthorsKey){
											combinedAuthors.add(authorFOMD);
										}
										for (Integer authorFOMDOne : communityVal){
											if (!combinedAuthors.contains(authorFOMDOne)) {
												combinedAuthors.add(authorFOMDOne);
											}
										}
										
										ArrayList<String> combinedPapers = new ArrayList<String>();
										for (String paperFOMD : newPapers.get(commAuthorsKey)){
											combinedPapers.add(paperFOMD);
										}
										for (String paperFOMDOne : newPapers.get(communityVal)){
											if (!combinedPapers.contains(paperFOMDOne)) {
												combinedPapers.add(paperFOMDOne);
											}
										}
										
										//19 June Getting rid of duplicate combined communities
										combinedAuthors.sort(null);
										if (!alreadyCombinedAuthors.contains(combinedAuthors)){
											int combinedPapersSize = combinedPapers.size();
											int combinedSize = combinedPapersSize + combinedAuthors.size();
											
											for (int j = 0; j < combinedPapersSize; j++){
												String combinePaperTitle = combinedPapers.get(j);
												int combinedPaperInternalDegree = 0;
												int combinedIncludedAuthorNO = 0;
												ArrayList<Integer> combinedincludedAuthors = new ArrayList<Integer>();
												ArrayList<Integer> combinedPaperAuthors = new ArrayList<Integer>();
												combinedPaperAuthors = paperList.GetAuthorsBasedOnPaperTitle(combinePaperTitle);
												for(int combinedPaperAuthor: combinedPaperAuthors){
													if (combinedAuthors.contains(combinedPaperAuthor)){
														combinedPaperInternalDegree++;
														combinedIncludedAuthorNO++;
														combinedincludedAuthors.add(combinedPaperAuthor);
														if(!combinedCommAuthorsDegrees.containsKey(combinedPaperAuthor)) {
															combinedCommAuthorsDegrees.put(combinedPaperAuthor, 1);
														}else{
															int currentCombinedAuthorDegree = combinedCommAuthorsDegrees.get(combinedPaperAuthor);
															combinedCommAuthorsDegrees.replace(combinedPaperAuthor, currentCombinedAuthorDegree, currentCombinedAuthorDegree +1);
														}
													}
												}
												if (combinedPaperInternalDegree > medianDegree){
													combinedNumberOfHigher++;
												}
												if (combinedIncludedAuthorNO > 1){
													for (int combinedIncludedAuthor : combinedincludedAuthors){
														int currentCombinedIncludedAuthorDegree = combinedCommAuthorsDegrees.get(combinedIncludedAuthor);
														combinedCommAuthorsDegrees.replace(combinedIncludedAuthor, currentCombinedIncludedAuthorDegree, currentCombinedIncludedAuthorDegree+(combinedIncludedAuthorNO-1));
													}
												}
											}
											for (int combinedCommAuthor : combinedAuthors){
												int combinedAuthorInternalDegree = 0;
												combinedAuthorInternalDegree = combinedCommAuthorsDegrees.get(combinedCommAuthor);
												if (combinedAuthorInternalDegree > medianDegree){
													combinedNumberOfHigher++;
												}
											}
											
											double combinedFOMD = 0;
											combinedFOMD = (double)combinedNumberOfHigher / (double)combinedSize;
											
//								        	writer.write("Combined " + combined);
//								        	writer.newLine();
//											writer.write("Combined Paper: " + combinedPapers);
//											writer.newLine();
//											writer.write("Combined Community: " + GetAuthorNames(combinedAuthors, authorList));
//											writer.newLine();
//											writer.write("Combined Fraction over median degree (FOMD): " + combinedFOMD);
//											writer.newLine();
//											writer.newLine();
											
											combined++;
											totalCombined++;
											if (combinedFOMD > currentTopFOMD){
												numHigher++;
											}
											
											//19 June Getting rid of duplicate combined communities
											alreadyCombinedAuthors.add(combinedAuthors);
										}
									}
									
							}
				        }
							
							
				      }
				    }
				    
				}
			}
		}
		double perHigher = ((double)numHigher / (double)totalCombined) * 100;
		writer.write("numHigher: " + numHigher);
		writer.newLine();
		writer.write("totalCombined: " + totalCombined);
		writer.newLine();
		writer.write("Percentage of higher FOMD: " + perHigher + "%");
		writer.newLine();
		writer.newLine();
		//12 June Validation - FOMD
		
		
		//14 June Validation - Expansion
		writer.write("Validation expansion begins");
		writer.newLine();
		double[] allExpansionValues = new double[listallExpansionValues.size()];
		for(int i = 0; i < allExpansionValues.length; i++){
			allExpansionValues[i] = listallExpansionValues.get(i);
		}
		Arrays.sort(allExpansionValues);
		int allExpansionValuesLength = allExpansionValues.length;
		int topKExpansion = 1;
		int totalCombinedExpansion = 0;
		int numLower = 0;
		double lastExpansionValue = 0;
		for (int i=0; i<allExpansionValuesLength; i++){
			if (topKExpansion <= 10){
				double currentLowestExpansion = allExpansionValues[i];
				int timeToRun = 1;
				if ((currentLowestExpansion != lastExpansionValue)){
				    for (ArrayList<Integer> commAuthorsKey : commExpansion.keySet()) {
				        if ((timeToRun == 1) && (commExpansion.get(commAuthorsKey).equals(currentLowestExpansion))) {
				        	writer.write("Top " + topKExpansion);
				        	writer.newLine();
							writer.write("Paper: " + newPapers.get(commAuthorsKey).toString());
							writer.newLine();
							writer.write("Community: " + GetAuthorNames(commAuthorsKey, authorList));
							writer.newLine();
							writer.write("Expansion: " + currentLowestExpansion);
							writer.newLine();
							writer.newLine();
							
							int combined = 1;
							topKExpansion++;
							timeToRun++;
							lastExpansionValue = currentLowestExpansion;
							
							//19 June Getting rid of duplicate combined communities
							ArrayList<ArrayList<Integer>> alreadyCombinedAuthors = new ArrayList<ArrayList<Integer>>();
							

							for (Integer commEachAuthor : commAuthorsKey){
								Enumeration<ArrayList<Integer>> enVal = newCommunities.keys();
								while (enVal.hasMoreElements()) {
									ArrayList<Integer> communityVal = enVal.nextElement();
									if ((communityVal.contains(commEachAuthor)) && (!communityVal.equals(commAuthorsKey))){
										//int combinedNumberOfHigher = 0;
										int totalExternalDegreeExpansion = 0;
										Hashtable<Integer, Integer> combinedCommAuthorsDegrees = new Hashtable<Integer,Integer>();
										ArrayList<Integer> combinedAuthors = new ArrayList<Integer>();
										for (Integer authorExpansion : commAuthorsKey){
											combinedAuthors.add(authorExpansion);
										}
										for (Integer authorExpansionOne : communityVal){
											if (!combinedAuthors.contains(authorExpansionOne)) {
												combinedAuthors.add(authorExpansionOne);
											}
										}
										
										ArrayList<String> combinedPapers = new ArrayList<String>();
										for (String paperExpansion : newPapers.get(commAuthorsKey)){
											combinedPapers.add(paperExpansion);
										}
										for (String paperExpansionOne : newPapers.get(communityVal)){
											if (!combinedPapers.contains(paperExpansionOne)) {
												combinedPapers.add(paperExpansionOne);
											}
										}
										
										//19 June Getting rid of duplicate combined communities
										combinedAuthors.sort(null);
										if (!alreadyCombinedAuthors.contains(combinedAuthors)){
											int combinedPapersSize = combinedPapers.size();
											int combinedSize = combinedPapersSize + combinedAuthors.size();
											
											for (int j = 0; j < combinedPapersSize; j++){
												String combinePaperTitle = combinedPapers.get(j);
												//int combinedPaperInternalDegree = 0;
												int combinedIncludedAuthorNO = 0;
												ArrayList<Integer> combinedincludedAuthors = new ArrayList<Integer>();
												ArrayList<Integer> combinedPaperAuthors = new ArrayList<Integer>();
												combinedPaperAuthors = paperList.GetAuthorsBasedOnPaperTitle(combinePaperTitle);
												for(int combinedPaperAuthor: combinedPaperAuthors){
													if (combinedAuthors.contains(combinedPaperAuthor)){
														//combinedPaperInternalDegree++;
														combinedIncludedAuthorNO++;
														combinedincludedAuthors.add(combinedPaperAuthor);
														if(!combinedCommAuthorsDegrees.containsKey(combinedPaperAuthor)) {
															combinedCommAuthorsDegrees.put(combinedPaperAuthor, 1);
														}else{
															int currentCombinedAuthorDegree = combinedCommAuthorsDegrees.get(combinedPaperAuthor);
															combinedCommAuthorsDegrees.replace(combinedPaperAuthor, currentCombinedAuthorDegree, currentCombinedAuthorDegree +1);
														}
													} else {
														totalExternalDegreeExpansion++;
													}
												}
												//if (combinedPaperInternalDegree > medianDegree){
													//combinedNumberOfHigher++;
												//}
												if (combinedIncludedAuthorNO > 1){
													for (int combinedIncludedAuthor : combinedincludedAuthors){
														int currentCombinedIncludedAuthorDegree = combinedCommAuthorsDegrees.get(combinedIncludedAuthor);
														combinedCommAuthorsDegrees.replace(combinedIncludedAuthor, currentCombinedIncludedAuthorDegree, currentCombinedIncludedAuthorDegree+(combinedIncludedAuthorNO-1));
													}
												}
											}
											for (int combinedCommAuthor : combinedAuthors){
												int combinedAuthorInternalDegree = 0;
												combinedAuthorInternalDegree = combinedCommAuthorsDegrees.get(combinedCommAuthor);
												int combinedAuthorExternalDegree = 0;
												Author combinedCurrentCommAuthor = authorList.GetAuthor(combinedCommAuthor);
												combinedAuthorExternalDegree = combinedCurrentCommAuthor.getAllCombinedDegree() - combinedAuthorInternalDegree;
												totalExternalDegreeExpansion += combinedAuthorExternalDegree;
													
											}
											
											double combinedExpansion = 0;
											//combinedFOMD = (double)combinedNumberOfHigher / (double)combinedSize;
											combinedExpansion = (double) totalExternalDegreeExpansion / (double) combinedSize;
											
								        	writer.write("Combined " + combined);
								        	writer.newLine();
											writer.write("Combined Paper: " + combinedPapers);
											writer.newLine();
											writer.write("Combined Community: " + GetAuthorNames(combinedAuthors, authorList));
											writer.newLine();
											writer.write("Combined Expansion: " + combinedExpansion);
											writer.newLine();
											writer.newLine();
											
											combined++;
											totalCombinedExpansion++;
											if (combinedExpansion < currentLowestExpansion){
												numLower++;
											}
											
											//19 June Getting rid of duplicate combined communities
											alreadyCombinedAuthors.add(combinedAuthors);
										}
										
									}
									
							}
				        }
							
							
				      }
				    }
				    
				}
			}
		}
		double perLower = ((double)numLower / (double)totalCombinedExpansion) * 100;
		writer.write("numLower: " + numLower);
		writer.newLine();
		writer.write("totalCombined: " + totalCombinedExpansion);
		writer.newLine();
		writer.write("Percentage of lower Expansion: " + perLower + "%");
		writer.newLine();
		writer.newLine();
		//14 June Validation - Expansion
		
		
		
		//14 June Validation - Conductance
		writer.write("Validation Conductance begins");
		writer.newLine();
		double[] allConductanceValues = new double[listallConductanceValues.size()];
		for(int i = 0; i < allConductanceValues.length; i++){
			allConductanceValues[i] = listallConductanceValues.get(i);
		}
		Arrays.sort(allConductanceValues);
		int allConductanceValuesLength = allConductanceValues.length;
		int topKConductance = 1;
		int totalCombinedConductance = 0;
		int numLowerConductance = 0;
		double lastConductanceValue = 0;
		for (int i=0; i<allConductanceValuesLength; i++){
			if (topKConductance <= 10){
				double currentLowestCconductance = allConductanceValues[i];
				int timeToRun = 1;
				if ((currentLowestCconductance != lastConductanceValue)){
				    for (ArrayList<Integer> commAuthorsKey : commconductance.keySet()) {
				        if ((timeToRun == 1) && (commconductance.get(commAuthorsKey).equals(currentLowestCconductance))) {
				        	writer.write("Top " + topKConductance);
				        	writer.newLine();
							writer.write("Paper: " + newPapers.get(commAuthorsKey).toString());
							writer.newLine();
							writer.write("Community: " + GetAuthorNames(commAuthorsKey, authorList));
							writer.newLine();
							writer.write("Conductance: " + currentLowestCconductance);
							writer.newLine();
							writer.newLine();
							
							int combined = 1;
							topKConductance++;
							timeToRun++;
							lastConductanceValue = currentLowestCconductance;
							
							//19 June Getting rid of duplicate combined communities
							ArrayList<ArrayList<Integer>> alreadyCombinedAuthors = new ArrayList<ArrayList<Integer>>();
							
							for (Integer commEachAuthor : commAuthorsKey){
								Enumeration<ArrayList<Integer>> enVal = newCommunities.keys();
								while (enVal.hasMoreElements()) {
									ArrayList<Integer> communityVal = enVal.nextElement();
									if ((communityVal.contains(commEachAuthor)) && (!communityVal.equals(commAuthorsKey))){
										//int combinedNumberOfHigher = 0;
										int totalExternalDegreeConductance = 0;
										int totalInternalDegreeConductance = 0;
										Hashtable<Integer, Integer> combinedCommAuthorsDegrees = new Hashtable<Integer,Integer>();
										ArrayList<Integer> combinedAuthors = new ArrayList<Integer>();
										for (Integer authorConductance : commAuthorsKey){
											combinedAuthors.add(authorConductance);
										}
										for (Integer authorConductanceOne : communityVal){
											if (!combinedAuthors.contains(authorConductanceOne)) {
												combinedAuthors.add(authorConductanceOne);
											}
										}
										
										ArrayList<String> combinedPapers = new ArrayList<String>();
										for (String paperConductance : newPapers.get(commAuthorsKey)){
											combinedPapers.add(paperConductance);
										}
										for (String paperConductanceOne : newPapers.get(communityVal)){
											if (!combinedPapers.contains(paperConductanceOne)) {
												combinedPapers.add(paperConductanceOne);
											}
										}
										
										
										//19 June Getting rid of duplicate combined communities
										combinedAuthors.sort(null);
										if (!alreadyCombinedAuthors.contains(combinedAuthors)){
											int combinedPapersSize = combinedPapers.size();
											
											for (int j = 0; j < combinedPapersSize; j++){
												String combinePaperTitle = combinedPapers.get(j);
												int combinedIncludedAuthorNO = 0;
												ArrayList<Integer> combinedincludedAuthors = new ArrayList<Integer>();
												ArrayList<Integer> combinedPaperAuthors = new ArrayList<Integer>();
												combinedPaperAuthors = paperList.GetAuthorsBasedOnPaperTitle(combinePaperTitle);
												for(int combinedPaperAuthor: combinedPaperAuthors){
													if (combinedAuthors.contains(combinedPaperAuthor)){
														totalInternalDegreeConductance++;
														combinedIncludedAuthorNO++;
														combinedincludedAuthors.add(combinedPaperAuthor);
														if(!combinedCommAuthorsDegrees.containsKey(combinedPaperAuthor)) {
															combinedCommAuthorsDegrees.put(combinedPaperAuthor, 1);
														}else{
															int currentCombinedAuthorDegree = combinedCommAuthorsDegrees.get(combinedPaperAuthor);
															combinedCommAuthorsDegrees.replace(combinedPaperAuthor, currentCombinedAuthorDegree, currentCombinedAuthorDegree +1);
														}
													} else {
														totalExternalDegreeConductance++;
													}
												}
												if (combinedIncludedAuthorNO > 1){
													for (int combinedIncludedAuthor : combinedincludedAuthors){
														int currentCombinedIncludedAuthorDegree = combinedCommAuthorsDegrees.get(combinedIncludedAuthor);
														combinedCommAuthorsDegrees.replace(combinedIncludedAuthor, currentCombinedIncludedAuthorDegree, currentCombinedIncludedAuthorDegree+(combinedIncludedAuthorNO-1));
													}
												}
											}
											for (int combinedCommAuthor : combinedAuthors){
												int combinedAuthorInternalDegree = 0;
												combinedAuthorInternalDegree = combinedCommAuthorsDegrees.get(combinedCommAuthor);
												int combinedAuthorExternalDegree = 0;
												Author combinedCurrentCommAuthor = authorList.GetAuthor(combinedCommAuthor);
												combinedAuthorExternalDegree = combinedCurrentCommAuthor.getAllCombinedDegree() - combinedAuthorInternalDegree;
												totalExternalDegreeConductance += combinedAuthorExternalDegree;
												totalInternalDegreeConductance += combinedAuthorInternalDegree;
													
											}
											
											double combinedConductance = 0;
											//combinedFOMD = (double)combinedNumberOfHigher / (double)combinedSize;
											combinedConductance = (double) totalExternalDegreeConductance / (double) (totalInternalDegreeConductance + totalExternalDegreeConductance);
											
								        	writer.write("Combined " + combined);
								        	writer.newLine();
											writer.write("Combined Paper: " + combinedPapers);
											writer.newLine();
											writer.write("Combined Community: " + GetAuthorNames(combinedAuthors, authorList));
											writer.newLine();
											writer.write("Combined Conductance: " + combinedConductance);
											writer.newLine();
											writer.newLine();
											
											combined++;
											totalCombinedConductance++;
											if (combinedConductance < lastConductanceValue){
												numLowerConductance++;
											}
											
											//19 June Getting rid of duplicate combined communities
											alreadyCombinedAuthors.add(combinedAuthors);
										}
										
										
									}
									
							}
				        }
							
							
				      }
				    }
				    
				}
			}
		}
		double perLowerConductance = ((double)numLowerConductance / (double)totalCombinedConductance) * 100;
		writer.write("numLower: " + numLowerConductance);
		writer.newLine();
		writer.write("totalCombined: " + totalCombinedConductance);
		writer.newLine();
		writer.write("Percentage of lower Conductance: " + perLowerConductance + "%");
		writer.newLine();
		writer.newLine();
		//14 June Validation - Conductance
		
		
		//14 June Validation - FlakeODF
		writer.write("Validation FlakeODF begins");
		writer.newLine();
		double[] allFlakeODFValues = new double[listallFlakeODFValues.size()];
		for(int i = 0; i < allFlakeODFValues.length; i++){
			allFlakeODFValues[i] = listallFlakeODFValues.get(i);
		}
		Arrays.sort(allFlakeODFValues);
		int allFlakeODFValuesLength = allFlakeODFValues.length;
		int topKFlakeODF = 1;
		int totalCombinedFlakeODF = 0;
		int numLowerFlakeODF = 0;
		double lastFlakeODFValue = 10000;
		for (int i=0; i<allFlakeODFValuesLength; i++){
			if (topKFlakeODF <= 10){
				double currentLowestFlakeODF = allFlakeODFValues[i];
				int timeToRun = 1;
				if ((currentLowestFlakeODF != lastFlakeODFValue)){
				    for (ArrayList<Integer> commAuthorsKey : commFlakeODF.keySet()) {
				        if ((timeToRun == 1) && (commFlakeODF.get(commAuthorsKey).equals(currentLowestFlakeODF))) {
//				        	writer.write("Top " + topKFlakeODF);
//				        	writer.newLine();
//							writer.write("Paper: " + newPapers.get(commAuthorsKey).toString());
//							writer.newLine();
//							writer.write("Community: " + GetAuthorNames(commAuthorsKey, authorList));
//							writer.newLine();
//							writer.write("Flake ODF: " + currentLowestFlakeODF);
//							writer.newLine();
//							writer.newLine();
							
							int combined = 1;
							topKFlakeODF++;
							timeToRun++;
							lastFlakeODFValue = currentLowestFlakeODF;
							
							//19 June Getting rid of duplicate combined communities
							ArrayList<ArrayList<Integer>> alreadyCombinedAuthors = new ArrayList<ArrayList<Integer>>();
							
							for (Integer commEachAuthor : commAuthorsKey){
								Enumeration<ArrayList<Integer>> enVal = newCommunities.keys();
								while (enVal.hasMoreElements()) {
									ArrayList<Integer> communityVal = enVal.nextElement();
									if ((communityVal.contains(commEachAuthor)) && (!communityVal.equals(commAuthorsKey))){
										int combinedNumberOfLower = 0;
										Hashtable<Integer, Integer> combinedCommAuthorsDegrees = new Hashtable<Integer,Integer>();
										ArrayList<Integer> combinedAuthors = new ArrayList<Integer>();
										for (Integer authorFlakeODF : commAuthorsKey){
											combinedAuthors.add(authorFlakeODF);
										}
										for (Integer authorFlakeODFOne : communityVal){
											if (!combinedAuthors.contains(authorFlakeODFOne)) {
												combinedAuthors.add(authorFlakeODFOne);
											}
										}
										
										ArrayList<String> combinedPapers = new ArrayList<String>();
										for (String paperFlakeODF : newPapers.get(commAuthorsKey)){
											combinedPapers.add(paperFlakeODF);
										}
										for (String paperFlakeODFOne : newPapers.get(communityVal)){
											if (!combinedPapers.contains(paperFlakeODFOne)) {
												combinedPapers.add(paperFlakeODFOne);
											}
										}
										
										
										//19 June Getting rid of duplicate combined communities
										combinedAuthors.sort(null);
										if (!alreadyCombinedAuthors.contains(combinedAuthors)){
											int combinedPapersSize = combinedPapers.size();
											int combinedSize = combinedPapersSize + combinedAuthors.size();
											
											for (int j = 0; j < combinedPapersSize; j++){
												String combinePaperTitle = combinedPapers.get(j);
												int combinedPaperInternalDegree = 0;
												int paperExternalDegree = 0;
												int combinedIncludedAuthorNO = 0;
												ArrayList<Integer> combinedincludedAuthors = new ArrayList<Integer>();
												ArrayList<Integer> combinedPaperAuthors = new ArrayList<Integer>();
												combinedPaperAuthors = paperList.GetAuthorsBasedOnPaperTitle(combinePaperTitle);
												for(int combinedPaperAuthor: combinedPaperAuthors){
													if (combinedAuthors.contains(combinedPaperAuthor)){
														combinedPaperInternalDegree++;
														combinedIncludedAuthorNO++;
														combinedincludedAuthors.add(combinedPaperAuthor);
														if(!combinedCommAuthorsDegrees.containsKey(combinedPaperAuthor)) {
															combinedCommAuthorsDegrees.put(combinedPaperAuthor, 1);
														}else{
															int currentCombinedAuthorDegree = combinedCommAuthorsDegrees.get(combinedPaperAuthor);
															combinedCommAuthorsDegrees.replace(combinedPaperAuthor, currentCombinedAuthorDegree, currentCombinedAuthorDegree +1);
														}
													}  else {
														paperExternalDegree++;
													}
												}
												//if (combinedPaperInternalDegree > medianDegree){
													//combinedNumberOfHigher++;
												//}
												
												if (combinedPaperInternalDegree < paperExternalDegree){
													combinedNumberOfLower++;
												}
												
												if (combinedIncludedAuthorNO > 1){
													for (int combinedIncludedAuthor : combinedincludedAuthors){
														int currentCombinedIncludedAuthorDegree = combinedCommAuthorsDegrees.get(combinedIncludedAuthor);
														combinedCommAuthorsDegrees.replace(combinedIncludedAuthor, currentCombinedIncludedAuthorDegree, currentCombinedIncludedAuthorDegree+(combinedIncludedAuthorNO-1));
													}
												}
											}
											for (int combinedCommAuthor : combinedAuthors){
												int combinedAuthorInternalDegree = 0;
												combinedAuthorInternalDegree = combinedCommAuthorsDegrees.get(combinedCommAuthor);
												//if (combinedAuthorInternalDegree > medianDegree){
													//combinedNumberOfHigher++;
												//}
												int combinedAuthorExternalDegree = 0;
												Author combinedCurrentCommAuthor = authorList.GetAuthor(combinedCommAuthor);
												combinedAuthorExternalDegree = combinedCurrentCommAuthor.getAllCombinedDegree() - combinedAuthorInternalDegree;
												if (combinedAuthorInternalDegree < combinedAuthorExternalDegree){
													combinedNumberOfLower++;
												}
											}
											
											double combinedFlakeODF = 0;
											combinedFlakeODF = (double)combinedNumberOfLower / (double)combinedSize;
											
//								        	writer.write("Combined " + combined);
//								        	writer.newLine();
//											writer.write("Combined Paper: " + combinedPapers);
//											writer.newLine();
//											writer.write("Combined Community: " + GetAuthorNames(combinedAuthors, authorList));
//											writer.newLine();
//											writer.write("Combined FlakeODF: " + combinedFlakeODF);
//											writer.newLine();
//											writer.newLine();
											
											combined++;
											totalCombinedFlakeODF++;
											if (combinedFlakeODF < currentLowestFlakeODF){
												numLowerFlakeODF++;
											}
											
											//19 June Getting rid of duplicate combined communities
											alreadyCombinedAuthors.add(combinedAuthors);
										}									
									}
									
							}
				        }
							
							
				      }
				    }
				    
				}
			}
		}
		double perLowerFlakeODF = ((double)numLowerFlakeODF / (double)totalCombinedFlakeODF) * 100;
		writer.write("numLower: " + numLowerFlakeODF);
		writer.newLine();
		writer.write("totalCombined: " + totalCombinedFlakeODF);
		writer.newLine();
		writer.write("Percentage of lower FlakeODF: " + perLowerFlakeODF + "%");
		writer.newLine();
		writer.newLine();
		//14 June Validation - FlakeODF
		
		
		averageCommunitySize = (double)totalCommunitySize/(double)numberOfCommunitiesOverThreshold;
		//6 June 2017
		for (int s : totalCommSizesSD){
			sdTotalCommSizes.add(Math.pow((s-averageCommunitySize), 2));
		}
		
		double sumSDCommunitySize = 0;
		
		for (double w : sdTotalCommSizes){
			sumSDCommunitySize += w;
		}
		
		double communitySizeSD = Math.sqrt(sumSDCommunitySize / sdTotalCommSizes.size());
		//17 May
		System.out.println("Total number of author-anchoring communities: " + newCommunities.size());
		System.out.println("Number of communities over threshold: " + numberOfCommunitiesOverThreshold);
		System.out.println("Average Community size: " + averageCommunitySize);
		System.out.println("Standard Deviation Community size: " + communitySizeSD);
		System.out.println("Average Triangle participation ratio (TPR): " + ((double)totalTPR / (double)numberOfCommunitiesOverThreshold));
		System.out.println("Average Fraction over median degree (FOMD): " + (totalFractionOfHigher / numberOfCommunitiesOverThreshold));
		System.out.println("Average Expansion: " + (totalExpansion / numberOfCommunitiesOverThreshold));
		System.out.println("Average Conductance: " + (totalConductance / numberOfCommunitiesOverThreshold));
		System.out.println("Average Flake ODF: " + (totalFlakeODF / numberOfCommunitiesOverThreshold));
		return numberOfCommunitiesOverThreshold;
	}
	//CreateCommunities
	
	
	public static String GetAuthorNames(ArrayList<Integer> communityAuthorList, AuthorList authorList) {
		if (communityAuthorList.size() == 0 ){
			return "";
		}
		
		String authors = "";
		for(int i = 0; i < communityAuthorList.size(); i++) {
			int authorId = communityAuthorList.get(i);
			String authorName = authorList.GetAuthor(authorId).name;
			authors += authorName + " - ";
		}
		authors = authors.trim();
		
		return authors.substring(0, authors.length()-1);
	}
	
	public static String ProcessString(String string) {
		String processedString = string.substring(string.indexOf("{") + 1, string.indexOf("}"));
		
		return processedString;
	}
	
	public static String[] ProcessAuthorListString(String authorString) {
		String[] listOfAuthors;
		String authors = ProcessString(authorString);
		if(authors.contains(";")){
			listOfAuthors = authors.split("; ");
		} else {
			listOfAuthors = new String[]{authors};
		}
		
		return listOfAuthors;
	}
	
	public static PaperList ProcessPapers(String filePath, String paperDelimiter) throws IOException{
		PaperList paperList = new PaperList();
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr); 
		
		try {
			String s; 

			while((s = br.readLine()) != null) { 
				
				String[] paperPair = s.split(paperDelimiter);
				
				for(int i = 0; i < paperPair.length; i++) {
					
					// Trim the author name
					paperPair[i] = paperPair[i].trim();
					
					if(!paperList.CheckIfPaperExistsInLookupTable(paperPair[i])) {
						Paper paper = new Paper(paperPair[i], paperList.GetUniqueId());
						paperList.AddPaper(paper);		
						paperList.IncrementUniqueId();
					}
				}
				
				int paperOneId = paperList.GetPaperIdFromLookupTable(paperPair[0]);
				int paperTwoId = paperList.GetPaperIdFromLookupTable(paperPair[1]);
				paperList.papers.get(paperOneId).AddNewReference(paperTwoId);
			
			} 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fr.close();
			br.close();
		}
		
		return paperList;
	}
	
	public static AuthorList ProcessAuthors(String filePath, String authorDelimiter) throws IOException{
		AuthorList authorList = new AuthorList();
		
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr); 
		System.out.println(authorDelimiter);
		try {
			String s; 
			
			while((s = br.readLine()) != null) { 
				String[] authorPair = s.split(authorDelimiter);
				
				/*
				System.out.println(authorPair[0]);
				System.out.println(authorPair[1]);
				System.out.println();
				*/
				for(int i = 0; i < authorPair.length; i++) {
					
					// Trim the author name
					authorPair[i] = authorPair[i].trim();	
					
					
					// Check if author has special characters in name, espcially delimiters such as ";"
					if(authorPair[i].contains(";")) {
						if (authorList.CheckIfAuthorExistsInSpecialCharacterTable(authorPair[i])) {
							authorPair[i] = authorList.GetSpecialCharacterMapping(authorPair[i]);
						} else {
							authorList.AddNewSpecialAuthorNameToMapping(authorPair[i]);
							authorPair[i] = authorList.GetSpecialCharacterMapping(authorPair[i]);
						}
					}
					
					// Check if author exists in lookup table, if not add the author to the lookup table and authorlist
					if(!authorList.CheckIfAuthorExistsInLookupTable(authorPair[i])) {
						Author author = new Author(authorPair[i], authorList.GetUniqueId());
						authorList.AddAuthor(author);		
						authorList.IncrementUniqueId();
					}
					
				}
				
				// Add a weighting for both authors
				// Get both author ids from lookup table
				int authorOneId = authorList.GetAuthorIdFromLookupTable(authorPair[0]);
				int authorTwoId = authorList.GetAuthorIdFromLookupTable(authorPair[1]);
				
				if(authorOneId != authorTwoId){
					// if author 1 has co-authored with author 2
					if(authorList.authors.get(authorOneId).HasCoAuthoredWith(authorTwoId)) {
						// increment weighting 
						authorList.authors.get(authorOneId).IncrementWeighting(authorTwoId);
					} else {
						// else add author 2 as a new author
						authorList.authors.get(authorOneId).AddNewCoAuthor(authorTwoId);
					}
					
					// vice versa
					if(authorList.authors.get(authorTwoId).HasCoAuthoredWith(authorOneId)) {
						authorList.authors.get(authorTwoId).IncrementWeighting(authorOneId);
					} else {
						authorList.authors.get(authorTwoId).AddNewCoAuthor(authorOneId);
					}
				}
				
			} 
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			fr.close();
			br.close();
		}
			
		return authorList;
	}
	
	static int[] addElement(int[] a, int e) {
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
	}
	
	static double[] addElementDouble(double[] a, double e) {
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
	}
	
	//monica4
	public static double getDegreeMedian(int[] allDegrees) {
		double medianValue = 0.0;
		Arrays.sort(allDegrees);
		int lengthOfAllDegrees = allDegrees.length;
		if (lengthOfAllDegrees != 0){
			if (lengthOfAllDegrees == 1){
				medianValue = allDegrees[0];
				return medianValue;
			}
			if (lengthOfAllDegrees % 2 == 0){
				medianValue = ((double)allDegrees[lengthOfAllDegrees/2] + (double)allDegrees[lengthOfAllDegrees/2 - 1])/2;
				return medianValue;
			}else{
				medianValue = allDegrees[(lengthOfAllDegrees)/2];
				return medianValue;
			}
		}
		return medianValue;	
	}
	
	
	//23 May recursion method to solve the order issue
	public static void recOrder(Integer pNewFirstID, Integer pNewSecondID, Integer pAnotherSecondID, ArrayList<Integer> pOriginalKey, ArrayList<Integer> pCurrentKey, 
			Hashtable<ArrayList<Integer>, Integer> pCommunities, Hashtable<ArrayList<Integer>, ArrayList<String>> pPapers,
			ArrayList<String>pMyCurrentPapers, AtomicInteger pMyCurrentWeighting, Hashtable<ArrayList<Integer>, ArrayList<Integer>> pRecCoauthors){
		
		ArrayList<Integer> firstSet = new ArrayList<Integer>();
		firstSet.add(pNewFirstID);
		firstSet.add(pNewSecondID);
		firstSet.sort(null);
		
		ArrayList<Integer> secondSet = new ArrayList<Integer>();
		secondSet.add(pNewFirstID);
		secondSet.add(pAnotherSecondID);
		secondSet.sort(null);
		
		ArrayList<ArrayList<Integer>> newFirstCoauthors = new ArrayList<>();
		ArrayList<ArrayList<Integer>> newSecondCoauthors = new ArrayList<>();
		
		ArrayList<ArrayList<Integer>> anotherFirstCoauthors = new ArrayList<>();
		ArrayList<ArrayList<Integer>> anotherSecondCoauthors = new ArrayList<>();
		
		Enumeration<ArrayList<Integer>> enSecondThrird = pCommunities.keys();
		while (enSecondThrird.hasMoreElements()) {
			ArrayList<Integer> communitySecondThird = enSecondThrird.nextElement();

			ArrayList<Integer> authorIDsThird = new ArrayList<Integer>();
			for(int i = 0; i < communitySecondThird.size(); i++) {
				int authorIdThird = communitySecondThird.get(i);
				authorIDsThird.add(authorIdThird);
			}
			
			if ((!authorIDsThird.equals(pOriginalKey)) && (!authorIDsThird.containsAll(firstSet)) && authorIDsThird.contains(pNewFirstID)){
				ArrayList<Integer> updatedAuthorIDsThirdOne = new ArrayList<Integer>();
				for(int i = 0; i < authorIDsThird.size(); i++) {
					int indiAuthorIDsThird = authorIDsThird.get(i);
					if (indiAuthorIDsThird != pNewFirstID){
						updatedAuthorIDsThirdOne.add(indiAuthorIDsThird);
					}
					
				}
				newFirstCoauthors.add(updatedAuthorIDsThirdOne);
							
			}
			
			
			if ((!authorIDsThird.equals(pOriginalKey)) && (!authorIDsThird.containsAll(firstSet)) && authorIDsThird.contains(pNewSecondID)){
				ArrayList<Integer> updatedAuthorIDsThirdTwo = new ArrayList<Integer>();
				for(int i = 0; i < authorIDsThird.size(); i++) {
					int indiAuthorIDsThird = authorIDsThird.get(i);
					if (indiAuthorIDsThird != pNewSecondID){
						updatedAuthorIDsThirdTwo.add(indiAuthorIDsThird);
					}
					
				}
				newSecondCoauthors.add(updatedAuthorIDsThirdTwo);
							
			}
			
			if ((!authorIDsThird.equals(pOriginalKey)) && (!authorIDsThird.containsAll(secondSet)) && authorIDsThird.contains(pNewFirstID)){
				ArrayList<Integer> updatedAuthorIDsThirdThree = new ArrayList<Integer>();
				for(int i = 0; i < authorIDsThird.size(); i++) {
					int indiAuthorIDsThird = authorIDsThird.get(i);
					if (indiAuthorIDsThird != pNewFirstID){
						updatedAuthorIDsThirdThree.add(indiAuthorIDsThird);
					}
					
				}
				anotherFirstCoauthors.add(updatedAuthorIDsThirdThree);
							
			}
			
			
			if ((!authorIDsThird.equals(pOriginalKey)) && (!authorIDsThird.containsAll(secondSet)) && authorIDsThird.contains(pAnotherSecondID)){
				ArrayList<Integer> updatedAuthorIDsThirdFour = new ArrayList<Integer>();
				for(int i = 0; i < authorIDsThird.size(); i++) {
					int indiAuthorIDsThird = authorIDsThird.get(i);
					if (indiAuthorIDsThird != pAnotherSecondID){
						updatedAuthorIDsThirdFour.add(indiAuthorIDsThird);
					}
					
				}
				anotherSecondCoauthors.add(updatedAuthorIDsThirdFour);
							
			}
		}
		
		for ( ArrayList<Integer> newCoauthor : newFirstCoauthors){
			for (Integer newCoauthorIndividual : newCoauthor){
				for (ArrayList<Integer> newCoauthorSec : newSecondCoauthors){
					if (newCoauthorSec.contains(newCoauthorIndividual)){
							ArrayList<Integer> newFirstCoauthorID = new ArrayList<Integer>();
							newFirstCoauthorID.add(pNewFirstID);
							for (Integer newIndividualCoauthor : newCoauthor){
								newFirstCoauthorID.add(newIndividualCoauthor);
							}
							newFirstCoauthorID.sort(null);
							ArrayList<String> newFirstIDPapers = pPapers.get(newFirstCoauthorID);
							
							ArrayList<Integer> newSecondCoauthorID = new ArrayList<Integer>();
							newSecondCoauthorID.add(pNewSecondID);
							for (Integer newIndividualCoauthorSec : newCoauthorSec){
								newSecondCoauthorID.add(newIndividualCoauthorSec);
							};
							newSecondCoauthorID.sort(null);
							ArrayList<String> newSecondIDPapers = pPapers.get(newSecondCoauthorID);
							
							if (newFirstIDPapers != null){
								for (String newFirstPaper : newFirstIDPapers){
									if (!pMyCurrentPapers.contains(newFirstPaper)){
										pMyCurrentPapers.add(newFirstPaper);
										pMyCurrentWeighting.incrementAndGet();
									}
								}
							} else {
								System.out.println("28 May newFirstIDPapers is null");
								System.out.println("28 May newFirstIDPapers is: " + newFirstCoauthorID);
							}
							
							if (newSecondIDPapers != null){
								for (String newSecondPaper : newSecondIDPapers){
									if (!pMyCurrentPapers.contains(newSecondPaper)){
										pMyCurrentPapers.add(newSecondPaper);
										pMyCurrentWeighting.incrementAndGet();
									}
								}
							} else {
								System.out.println("28 May newSecondIDPapers is null");
								System.out.println("28 May newSecondIDPapers is: " + newSecondCoauthorID);
							}
													
							if (!pCurrentKey.contains(newCoauthorIndividual)){
								pCurrentKey.add(newCoauthorIndividual);
								pCurrentKey.sort(null);
							}
												
							//30 May
							boolean recToGoOne = true;
							if (pRecCoauthors.containsKey(firstSet)){
								if (pRecCoauthors.get(firstSet).contains(newCoauthorIndividual)){
									recToGoOne = false;
								}
							}
							
							if (((!pOriginalKey.contains(newCoauthorIndividual)) && recToGoOne) || 
								((pOriginalKey.contains(newCoauthorIndividual)) && (!pOriginalKey.contains(pNewFirstID)) && (!pOriginalKey.contains(pNewSecondID)) && recToGoOne) ){
														
								//30 May
								if(!pRecCoauthors.containsKey(firstSet)) {
									ArrayList<Integer> recCoauthorSingle = new ArrayList<Integer>();
									recCoauthorSingle.add(newCoauthorIndividual);
									pRecCoauthors.put(firstSet, recCoauthorSingle);
								} else {
									ArrayList<Integer> recCoauthorCurrentOne = new ArrayList<Integer>();
									for (Integer eachCoauthorOne : pRecCoauthors.get(firstSet)){
										recCoauthorCurrentOne.add(eachCoauthorOne);
									}
									recCoauthorCurrentOne.add(newCoauthorIndividual);
									//recCoauthorCurrentOne.sort(null);
									pRecCoauthors.replace(firstSet, pRecCoauthors.get(firstSet),  recCoauthorCurrentOne);
								}
								//30 May
								
								recOrder(newCoauthorIndividual, pNewFirstID, pNewSecondID, pOriginalKey, pCurrentKey, 
										pCommunities, pPapers, pMyCurrentPapers, pMyCurrentWeighting, pRecCoauthors);
							}
					}
				}
			}
		}
				
		for ( ArrayList<Integer> recCoauthor : anotherFirstCoauthors){
			for (Integer recCoauthorIndividual : recCoauthor){
				for (ArrayList<Integer> recCoauthorSec : anotherSecondCoauthors){
					if (recCoauthorSec.contains(recCoauthorIndividual)){
							ArrayList<Integer> recFirstCoauthorID = new ArrayList<Integer>();
							recFirstCoauthorID.add(pNewFirstID);
							for (Integer recIndividualCoauthor : recCoauthor){
								recFirstCoauthorID.add(recIndividualCoauthor);
							}
							recFirstCoauthorID.sort(null);
							ArrayList<String> recFirstIDPapers = pPapers.get(recFirstCoauthorID);
							
							ArrayList<Integer> recSecondCoauthorID = new ArrayList<Integer>();
							recSecondCoauthorID.add(pAnotherSecondID);
							for (Integer recIndividualCoauthorSec : recCoauthorSec){
								recSecondCoauthorID.add(recIndividualCoauthorSec);
							};
							recSecondCoauthorID.sort(null);
							ArrayList<String> recSecondIDPapers = pPapers.get(recSecondCoauthorID);
							
							if (recFirstIDPapers != null){
								for (String recFirstPaper : recFirstIDPapers){
									if (!pMyCurrentPapers.contains(recFirstPaper)){
										pMyCurrentPapers.add(recFirstPaper);
										pMyCurrentWeighting.incrementAndGet();
									}
								}
							} else {
								System.out.println("28 May recFirstIDPapers is null");
								System.out.println("28 May recFirstIDPapers is: " + recFirstCoauthorID);
							}
							
							if (recSecondIDPapers != null){
								for (String recSecondPaper : recSecondIDPapers){
									if (!pMyCurrentPapers.contains(recSecondPaper)){
										pMyCurrentPapers.add(recSecondPaper);
										pMyCurrentWeighting.incrementAndGet();
									}
								}
							} else {
								System.out.println("28 May recSecondIDPapers is null");
								System.out.println("28 May recSecondCoauthorID is: " + recSecondCoauthorID);
							}
							
							if (!pCurrentKey.contains(recCoauthorIndividual)){
								pCurrentKey.add(recCoauthorIndividual);
								pCurrentKey.sort(null);

							}
							
							//30 May
							boolean recToGoTwo = true;
							if (pRecCoauthors.containsKey(secondSet)){
								if (pRecCoauthors.get(secondSet).contains(recCoauthorIndividual)){
									recToGoTwo = false;
								}
							}
							
							if (((!pOriginalKey.contains(recCoauthorIndividual)) && recToGoTwo) || 
								((pOriginalKey.contains(recCoauthorIndividual)) && (!pOriginalKey.contains(pNewFirstID)) && (!pOriginalKey.contains(pAnotherSecondID)) && recToGoTwo)) {
													
								//30 May
								if(!pRecCoauthors.containsKey(secondSet)) {
									ArrayList<Integer> recCoauthorSin = new ArrayList<Integer>();
									recCoauthorSin.add(recCoauthorIndividual);
									pRecCoauthors.put(secondSet, recCoauthorSin);
								} else {
									ArrayList<Integer> recCoauthorCurrentTwo = new ArrayList<Integer>();
									for (Integer eachCoauthorTwo : pRecCoauthors.get(secondSet)){
										recCoauthorCurrentTwo.add(eachCoauthorTwo);
									}
									recCoauthorCurrentTwo.add(recCoauthorIndividual);
									//recCoauthorCurrentTwo.sort(null);
									pRecCoauthors.replace(secondSet, pRecCoauthors.get(secondSet),  recCoauthorCurrentTwo);
								}
								//30 May
									
								recOrder(recCoauthorIndividual, pNewFirstID, pAnotherSecondID, pOriginalKey, pCurrentKey, 
										pCommunities, pPapers, pMyCurrentPapers, pMyCurrentWeighting, pRecCoauthors);
							}
							
					}
				}
			}	
		}
	}
	//23 May recursion method to solve the order issue
	
}


