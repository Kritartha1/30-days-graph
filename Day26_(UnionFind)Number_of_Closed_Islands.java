class Solution {
    //leetcode.com/problems/number-of-closed-islands/submissions/
    
    public int[][] direction={{-1,0},{1,0},{0,-1},{0,1}};
    
    public int closedIsland(int[][] grid) {
        UnionFind uf=new UnionFind(grid);
        int m=grid.length;
        int n=grid[0].length;
        
        for(int i=1;i<grid.length-1;i++){
            for(int j=1;j<grid[0].length-1;j++){
                
                if(grid[i][j]==0){// (i, j) is land.
                    
                    for(int[] dir:direction){
                        
                        int r=dir[0]+i;
                        int c=dir[1]+j;
                        if(grid[r][c]==0){// (r, c) is a land neighbor.
                            uf.union(i*n+j,r*n+c);
                        }
                    }
                }
                
            }
        }
        
        int ans=0;// number of closed islands: number of the non-boundary lands that are ids (parent) of itself. Why? just analyze the code of union..I will get it..
        
         for(int i=1;i<grid.length-1;i++){
            for(int j=1;j<grid[0].length-1;j++){
                
                if(grid[i][j]==0&&uf.parent[i*n+j]==i*n+j){// Is (i, j) a land as well as the id (parent) of self? 
                    
                    ++ans;
                }
            }
         }
        
        return ans;
        
    }
    
    
    
    class UnionFind{
        
        int m;
        int n;
        int[] parent;
        
        UnionFind(int[][] grid){
            this.m=grid.length;
            this.n=grid[0].length;
            this.parent=new int[n*m];
            
           for(int i=0;i<n*m;i++){
               this.parent[i]=i;// Initialized as i * n + j the parent id of cell (i, j).
           }
            
        }
        
        int find(int idx){
            if(idx==parent[idx]){
                return idx;
            }
            return parent[idx]=find(parent[idx]);
        }
        
        void union(int x,int y){
            int from=find(x);
            int to=find(y);
            if(from==to){
                return;
            }else if(isBoundary(to)){
                parent[from]=to;
            }else{
                parent[to]=from;
            }
        }
        
        boolean isBoundary(int idx){
            int i=idx/n;
            int j=idx%n;
            return (i==0||j==0||i==m-1||j==n-1);
                
            
        }
    }
}
