import java.io.*;
import java.util.*;

public class Main {

	static String num;
	static int CNT = 0;
	static Set<Long> set = new HashSet<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = br.readLine();
		
		set.add(0L);
		for (int i = 0; i <= 9; i++) {
			recur(""+i);
			recur("");
		}
		
		System.out.println(set.size()-1);
	}

	private static void recur(String n) {
		
		if(isPalindrom(n) && Long.parseLong(n)<=Long.parseLong(num)) {
			set.add(Long.parseLong(n));
		}
		
		for (int i = 0; i <= 9; i++) {
			String tmp = ""+i+n+i;
			if(tmp.length()>=12) return;
			if(Long.parseLong(tmp)<=Long.parseLong(num)) recur(tmp);
		}
	}

	private static boolean isPalindrom(String n) {
		if(n.length()==0 || n.charAt(0)=='0') return false;
		
		for (int i = 0; i < n.length()/2; i++)
			if(n.charAt(i)!=n.charAt(n.length()-1-i)) return false;
		
		return true;
	}
}
