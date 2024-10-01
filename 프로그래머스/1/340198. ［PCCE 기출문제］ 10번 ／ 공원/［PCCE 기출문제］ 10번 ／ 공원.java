import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        Integer[] matsArray = Arrays.stream(mats)
                                .boxed()
                                .toArray(Integer[]::new);
        
        Arrays.sort(matsArray, (e1, e2)->Integer.compare(e2, e1));
        System.out.print(Arrays.toString(matsArray));
        
        for(int k=0;k<matsArray.length;k++){
            int size = matsArray[k];
            for(int i=0;i<park.length-size+1;i++){
                a: for(int j=0;j<park[i].length-size+1;j++){
                    if(park[i][j].charAt(0)=='-') {
                        for(int ii=0;ii<size;ii++){
                            for(int jj=0;jj<size;jj++){
                                if(park[i+ii][j+jj].charAt(0)!='-') continue a;
                            }
                        }
                        return size;
                    }
                }
            }
        }
        
        return -1;
    }
}