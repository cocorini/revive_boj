import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] arr;
	static Set<Integer> sumOfTwoNumSet = new TreeSet<>();
	static Integer[] sumOfTwoNumArr;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		for (int i = 0; i < N; i++) {
			int a = Integer.parseInt(br.readLine());
			arr[i] = a;
		}
		Arrays.sort(arr);
		
		// x+y
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				sumOfTwoNumSet.add(arr[i]+arr[j]);
		
		sumOfTwoNumArr = sumOfTwoNumSet.toArray(new Integer[0]);
		
		sumOfThreeNum();
	}

	private static void sumOfThreeNum() {
		
		for (int i = N-1; i >= 0; i--) {
			int k = arr[i];
			for (int j = 0; j < N; j++) {
				int z = arr[j];
				int sum = k-z;
				if(sum<=0) continue;
				// x+y를 찾는 거
				if (Arrays.binarySearch(sumOfTwoNumArr, sum)>=0) {
					System.out.println(k);
					return;
				}
			}
		}
		
	}

}