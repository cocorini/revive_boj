import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int paper[][] = new int[10][10];
	private static int colorpaper[] = new int[]{0,5,5,5,5,5};
	private static int Ans = -1;
	private static boolean flag = false;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 입력 받기
    	for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 10; j++)
				paper[i][j] = Integer.parseInt(st.nextToken());
		}
    	
    	// 전체 종이에서 색종이 무작위로 배치하기 - Backtracking
        recur(0, 0, 0);
        System.out.println(Ans);
    }
    
    private static void recur(int R, int C, int cnt) {
    	if(Ans>=0 && cnt>=Ans) return;
    	
    	//base condition
    	if(R==10 && C==0) {
    		if(Ans<0)
    			Ans = cnt;
    		else
    			Ans = Math.min(Ans, cnt);
    		return;
    	}
    	// paper 칸의 값이 1일 때, 정사각형 크기 확인하기.
    	if(paper[R][C]==1) {
    		for (int size = 5; size > 0; size--) {
				// 만일, size로 정해진 정사각형안에 1밖에 없고, 10*10을 삐져나가지 않으면~
    			if(colorpaper[size]>0 && isSquare(R, C, size)) {
					stickColor(R, C, size);
					colorpaper[size]--;
    				if(C==9) recur(R+1, 0, cnt+1);
    				else recur(R, C+1, cnt+1);
    				removeColor(R, C, size);
    				colorpaper[size]++;
				}
			}
    	}else {
    		if(C==9) recur(R+1, 0, cnt);
			else recur(R, C+1, cnt);
    	}
    }

	private static void stickColor(int R, int C, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nR = R+i, nC = C+j;
				paper[nR][nC] = 2;
			}
		}
	}
	
	private static void removeColor(int R, int C, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nR = R+i, nC = C+j;
				paper[nR][nC] = 1;
			}
		}
	}

	private static boolean isSquare(int R, int C, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int nR = R+i, nC = C+j;
				if(nR<10 && nC<10) {
					if(paper[nR][nC]!=1) return false;
				}else return false;
			}
		}
		return true;
	}
}