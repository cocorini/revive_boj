import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	static String inputStr;
	static int len;
	static int[] score = new int[50];
	static Stack<Character> stack = new Stack();
	
	public static void main (String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inputStr = br.readLine();
		len = inputStr.length();
		
		int depth = 0;
		for(int i=0;i<len;i++){
			char c = inputStr.charAt(i);
			if(c=='(' || c=='['){
				depth++;
				stack.push(c);
			}
			else if(c==')'){
				if(stack.isEmpty()){
					System.out.println(0);
					return;
				}
				char popChar = stack.peek();
				if(popChar!='(') {
					System.out.println(0);
					return;
				}
				if(score[depth]==0){
					score[--depth]+=2;
				}
				else{
					score[depth-1]+=score[depth--]*2;
					score[depth+1]=0;
				}
				stack.pop();
			}
			else if(c==']'){
				if(stack.isEmpty()){
					System.out.println(0);
					return;
				}
				char popChar = stack.peek();
				if(popChar!='[') {
					System.out.println(0);
					return;
				}
				if(score[depth]==0){
					score[--depth]+=3;
				}
				else{
					score[depth-1]+=score[depth--]*3;
					score[depth+1]=0;
				}
				stack.pop();
			}
		}
		System.out.println(score[0]);
	}
}