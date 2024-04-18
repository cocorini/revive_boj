import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int b_cnt = 0, ans = Integer.MAX_VALUE;
		for (int i = 0; i < input.length(); i++)
			if(input.charAt(i)=='b') b_cnt++;
		for (int i = 0; i < input.length(); i++) {
			int tmp_bcnt_in_str = 0;
			if(i+b_cnt<=input.length()) {
				for (int j = 0; j < b_cnt; j++)
					if(input.charAt(i+j)=='b') tmp_bcnt_in_str++;
			} else {
				for (int j = 0; j < b_cnt; j++) {
					if(i+j<input.length()) {
						if(input.charAt(i+j)=='b') tmp_bcnt_in_str++;
					} else {
						if(input.charAt(i+j-input.length())=='b') tmp_bcnt_in_str++;
					}
				}
			}
			ans = Math.min(ans, b_cnt-tmp_bcnt_in_str);
		}
		
		if(ans==Integer.MAX_VALUE) ans=0;
		System.out.println(ans);
	}
}