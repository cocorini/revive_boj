import java.util.*;
import java.io.*;

class Main {
    static int ANS = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
    
    public static void main (String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder initKey = new StringBuilder();
        for(int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) initKey.append(st.nextToken());
        }
        bfs(initKey.toString());
        if(ANS == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ANS);
    }
    
    private static void bfs(String key) {
        Deque<String> q = new ArrayDeque<>();
        q.offer(key);
        Map<String, Integer> distance = new HashMap<>();
        distance.put(key, 0);
        
        while(!q.isEmpty()) {
            String curKey = q.poll();
            int CNT = distance.get(curKey);
            if(curKey.equals("123456780")) {
                ANS = Math.min(ANS, CNT);
                break;
            }
            
            int zeroIdx = curKey.indexOf('0');
            int r = zeroIdx / 3;
            int c = zeroIdx % 3;
            
            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isIn(nr, nc)) {
                    StringBuilder newKey = new StringBuilder(curKey);
                    int swapIdx = nr * 3 + nc;
                    newKey.setCharAt(zeroIdx, newKey.charAt(swapIdx));
                    newKey.setCharAt(swapIdx, '0');
                    String nextKey = newKey.toString();
                    
                    if(distance.getOrDefault(nextKey, -1) < 0) {
                        distance.put(nextKey, CNT + 1);
                        q.offer(nextKey);
                    }
                }
            }
        }
    }
    
    private static boolean isIn(int r, int c) {
        return r < 3 && c < 3 && r >= 0 && c >= 0;
    }
}