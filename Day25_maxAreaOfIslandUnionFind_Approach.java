class Solution {
    
    //Union find
    //leetcode.com/problems/max-area-of-island/submissions/
    int[][] directions={{-1,0},{0,1},{1,0},{0,-1}};//directions (row,col)
    
   
    public int maxAreaOfIsland(int[][] grid) {
        
        int m=grid.length;
        int n=grid[0].length;
        
        int area=0;
        UnionFind u=new UnionFind(grid);
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                
                if(grid[i][j]==1){
                    
                    area=Math.max(area,connect(grid,i,j,u,m,n));
                    
                }
            }
        }
        
        return area;
       
       
    }
    
    private int connect(int[][] grid,int i,int j,UnionFind u,int m,int n){
        
        //if we say numbered the boxes from top left to bottom right starting from 0..
        //let say 3*3 matrix..
        //idx(grid[0][0])=0
        //idx(grid[0][1])=1//idx(grid[0][2])=2
        //idx(grid[1][0])=3
        //idx(grid[1][1])=4
        //idx(grid[1][2])=5..
        //and so on..
        int idx=getIdx(i,j,n);
        
        int ar=u.getSize(idx);//area of the island which the current box is a part of
        
        for(int[] dir:directions){
            
            int r=i+dir[0];
            int c=j+dir[1];
            
            if(isValid(r,c,grid,m,n)){
                
                ar=u.union(idx,r*n+c);
                
            }
        }
        
        return ar;
    }
    
    //getIdx fncn will apoint each boxes a different single index..
    //eg:grid[][]={0,1,0
                // 0,1,0
                // 0,1,0}
    //so idx will be like:
    //grid[][]={1,2,3
            //  4,5,6
            //  7,8,9}
    private int getIdx(int i,int j,int n){
        
        return i*n + j;
        
    }
    
     private boolean isValid(int r,int c,int[][] grid,int m,int n){
         
        return r>=0&&c>=0&&r<m&&c<n&&grid[r][c]!=0;
         
    }
    
    
    //**********************************************************************************************//
    
    class UnionFind{
        
        int[] parent;
        int[] size;
        int[] rank
            ;
        UnionFind(int[][] grid){
            
            int m=grid.length;
            int n=grid[0].length;
            
            this.parent=new int[m*n];
            this.rank=new int[n*m];
            this.size=new int[n*m];
            
            Arrays.fill(size,1);
            Arrays.fill(parent,-1);
        }
        
        
        int find(int V){
            
            if(this.parent[V]==-1){
                return V;
            }
            
            return this.parent[V]=find(this.parent[V]);
        }
        
        
        int union(int x,int y){
            
            int p=find(x);
            int q=find(y);
            
            if(p==q){//means alreasy connected..then return the size/area which is stored in ots parent element
                return this.size[p];
            }
            
            else if(this.rank[p]>this.rank[q]){
                this.parent[q]=p;
                this.size[p]+=this.size[q];
                return this.size[p];
            }
            
            else if(this.rank[p]<this.rank[q]){
                this.parent[p]=q;
                this.size[q]+=this.size[p];//when connecting the parent nodes add the area of the smaller tree to the bigger tree and return the size...
                return this.size[q];
            }
            
            else{
                 this.parent[p]=q;
                 this.rank[q]++;
                 this.size[q]+=this.size[p];
                 return this.size[q];
            }
            
        }
        
        int getSize(int V){
            return this.size[find(V)];//will return the area of the island where the vertex is part of it
        }
        
    }
    
    
    
    
   
}
