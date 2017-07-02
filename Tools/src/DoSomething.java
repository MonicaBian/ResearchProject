import java.util.ArrayList;

public class DoSomething {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> A = new ArrayList<Integer>();
		A.add(1);
		A.add(2);
		A.add(3);
		
		ArrayList<Integer> B = new ArrayList<Integer>();
		B.add(3);
		B.add(1);
		B.add(2);
		
		System.out.println(A);
		
		System.out.println(B);
		
		System.out.println(B.containsAll(A));
	}

}
