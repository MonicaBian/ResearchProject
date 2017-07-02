import java.util.ArrayList;
import java.util.Hashtable;

public class DoSomething2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> A = new ArrayList<Integer>();
		A.add(1);
		A.add(2);
		A.add(3);
		
		ArrayList<Integer> B = new ArrayList<Integer>();
		B.add(2);
		B.add(3);
		//B.add(4);
		//B.add(1);
		
		ArrayList<Integer> C = new ArrayList<Integer>();
		C=A;
		
		ArrayList<Integer> D = new ArrayList<Integer>();
		for (int i : A){
			D.add(i);
		}
		
		A.remove(Integer.valueOf(1));
		
		System.out.println("16May A: " + A);
		System.out.println("16May C1: " + C);
		//C.remove(Integer.valueOf(2));
		//C.add(888);
		A.remove(Integer.valueOf(3));
		System.out.println("16May C2: " + C);
		System.out.println("16May A1: " + A);
		System.out.println("16May D: " + D);
		System.out.println("17May: " + A);
		System.out.println("17May: " + B);
		System.out.println("17May: " + A.equals(B));
		
		System.out.println(B);
		
		System.out.println(B.containsAll(A));
		
		Hashtable<Integer, ArrayList<Integer>> eligibleCoauthors = new Hashtable<Integer, ArrayList<Integer>>();
		
		ArrayList<ArrayList<Integer>> removedAuthorIDsFirst = new ArrayList<>();
		
		removedAuthorIDsFirst.add(A);
		System.out.println("8 May: " + !removedAuthorIDsFirst.contains(B));

		Integer first = null;
		Integer id = 888;
		
		first = id;
		
		//System.out.println(first);
		
		ArrayList<ArrayList<Integer>> firstCoauthors = new ArrayList<>();
		firstCoauthors.add(A);
		firstCoauthors.add(B);
		//System.out.println(firstCoauthors);
	}

}
