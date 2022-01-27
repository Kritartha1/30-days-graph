class Solution {
  //leetcode.com/problems/number-of-closed-islands/

    int n;
    int m;
    
    int[] row={-1,0,1,0};
    int[] col={0,1,0,-1};
    
    public int closedIsland(int[][] grid) {
        
        n=grid.length;
        m=grid[0].length;
        
        //The 0's present at the edges can't be a part of an island..so they are of no use ..
        //so all the zeroes directly or indirectly in contact with the edges are considered as water..
        //so make them grid[i][j]=1..make them water...
        
        
        
        //The top edge
        for(int j=0;j<m;j++){
            if( grid[0][j]==0){
                fill(grid,0,j);
            }
           
        }
        
        //the bottom edge
        for(int j=0;j<m;j++){
             if( grid[n-1][j]==0){
                fill(grid,n-1,j);
            }
            
        }
        
        //the left edge
        for(int i=1;i<n-1;i++){
             if( grid[i][0]==0){
                fill(grid,i,0);
            }
           
        }
        
        //the right edge
        for(int i=1;i<n-1;i++){
             if( grid[i][m-1]==0){
                fill(grid,i,m-1);
            }
            
        }
        
        int closedIsland=0;
        
        for(int i=1;i<n-1;i++){
            
            for(int j=1;j<m-1;j++){
                
               if(grid[i][j]==0){
                   
                  //now since all the edges are filled with water..so if there is any land present or grid[][]=0,then it must constitute an island..
                   //so increment the number of closedIsland...
                   
                   ++closedIsland;
                   fill(grid,i,j);
                   
               } 
            }
        }
        
        return closedIslands;
        
    }
    
    boolean valid(int r,int c,int[][] grid){
        return r>=0&&c>=0&&r<n&&c<m&&grid[r][c]==0;
    }
    
    void fill(int[][] grid,int i,int j){
        
        grid[i][j]=1;
        
        for(int k=0;k<4;k++){
            int r=i+row[k];
            int c=j+col[k];
            if(valid(r,c,grid)){
                fill(grid,r,c);
            }
        }
        
    }
}
