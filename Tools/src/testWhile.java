
public class testWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean stop = false;
		int i=0;
		while (stop == false){
			//System.out.println(i);
			i++;
			if (i==10){
				stop = true;
			}
			
			if ((i%2==0) || (i==2)){
				System.out.println(i);
				//break;
			}
			
			if (i>5){
				System.out.println("greater");
			}
		}
	}

}
