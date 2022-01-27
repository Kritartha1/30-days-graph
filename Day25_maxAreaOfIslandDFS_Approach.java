class Solution {
  //leetcode.com/problems/max-area-of-island/submissions/
  //DFS approach
    int n;
    int m;
  
    int max=0;
    int ans=0;
  
    int[][] neighbours={{-1,0},{0,1},{1,0},{0,-1}};
  
    public int maxAreaOfIsland(int[][] grid) {
      
        n=grid.length;
        m=grid[0].length;
       
        
        boolean[][] vis=new boolean[n][m];
      
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
              
                if(grid[i][j]==1&&!vis[i][j]){
                  
                    dfs(i,j,grid,vis);
                    max=Math.max(ans,max);
                    ans=0;
                    
                }
            }
        }
        return max;
        
    }
    public boolean valid(int r,int c,boolean[][] vis,int[][] grid){
        return r>=0&&c>=0&&r<n&&c<m&&(!vis[r][c])&&grid[r][c]!=0;
    }
    
    
   public void  dfs(int i,int j,int[][] grid,boolean[][] vis){
       
        vis[i][j]=true;
        ans++;
        
        for(int[] neighbour:neighbours){
          
            int r=i+neighbour[0];
            int c=j+neighbour[1];
            
            if(valid(r,c,vis,grid)){
               dfs(r,c,grid,vis);
            }
        }
       
       
        
    }
}
