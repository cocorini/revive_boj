import java.util.*;
class Solution {
    
    /*
    + (0,0) 에서 4방으로 시작 하는 애를 찾아서, 다음 여기를 방문했을 때 같은 방향으로 나가는 애까지가 한 사이클
    + 사이클에는 모든 노드가 다 포함되어 있어야 함
    + 노드 거칠때 마다 +1
    + 노드랑 방향을 기록해야하나? dp? 분/정?
    + 노드*방향 boolean[R][C][4] 배열
    + 상 -> s, l, r == 상, 좌, 우 0 3 1
    + 우 -> s, l, r == 우, 상, 하 1 0 2
    + 하 -> s, l, r == 하, 우, 좌 2 1 3
    + 좌 -> s, l, r == 좌, 하, 상 3 2 0
    */
    
    static boolean[][][] visited;
    // 상, 우, 하, 좌
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    // s 0, l 1, r 2 순
    static int[][] ddir = {{0, 3, 1}, {1, 0, 2}, {2, 1, 3}, {3, 2, 0}};
    static int R, C;
    static List<Integer> ans = new ArrayList<>();
    static String[] grid;
    
    public int[] solution(String[] g) {
        R = g.length; C = g[0].length();
        visited = new boolean[R][C][4];

        grid = new String[R];
        
        for(int i=0;i<R;i++) grid[i] = g[i];
        
        solve();
        
        return ans.stream().sorted()
	            .mapToInt(Integer::intValue)
    	        .toArray();
    }
    
    private void solve() {
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                for(int d=0;d<4;d++){
                    if(!visited[i][j][d]){
                        findCycle(i, j, d);
                    }
                }
            }
        }
    }
    
    private void findCycle(int sr, int sc, int sdir){
        int count = 0;
        int r = sr, c = sc, dir = sdir;
        
        while(!visited[r][c][dir]){
            count++;
            visited[r][c][dir] = true;
            if(grid[r].charAt(c)=='L')
                dir = ddir[dir][1];
            if(grid[r].charAt(c)=='R')
                dir = ddir[dir][2];
            
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            
            if(isIn(nr, nc)){
                r = nr; c = nc;
            } else{
                if(nr==R) r = 0;
                if(nr<0) r = R-1;
                if(nc==C) c = 0;
                if(nc<0) c = C-1;
            }
        }
        
        ans.add(count);
    }
    
    private boolean isIn(int r, int c){
        return r<R && r>=0 && c<C && c>=0;
    }
}