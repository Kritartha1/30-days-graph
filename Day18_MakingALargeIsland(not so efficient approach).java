class Solution {
  //dfs
    //leetcode.com/problems/making-a-large-island/
  
    //Pair: to store the index where grid[i][j]=0 
    class Pair{
        int i;
        int j;
        Pair(int i,int j){
            this.i=i;
            this.j=j;
        }
    }
    
    //M*N=dimension of the edge matrix
    int M;
    int N;
    
    //row and col are the possible directions of a particular box in a grid..
    int row[]={-1,0,1,0};
    int col[]={0,1,0,-1};
    
    //temp will return the size of island of each components
    int temp;
    
    //boxes of a same island are given same colors
    int color;
    
    public int largestIsland(int[][] grid) {
        
        M=grid.length;
        N=grid[0].length;
        
        temp=0;
        
        int max=-1;//initializing our max ans..as -1
        
        color=2;//since gris is already colored as 0 and 1..so we will try to give distinct colors apart from 1 and 0.so we started color initialization at 2..
        
        boolean[][] vis=new boolean[M][N];//as usual visited matrix ..important for dfs
        
        for(boolean[] rows: vis){//initializing vis matrix as false
            Arrays.fill(rows,false);
        }
        
        ArrayList<Pair> colorMap=new ArrayList<>();//to store the grid elements which are 0
        
        HashMap<Integer,Integer> mp=new HashMap<>();//stores the max area of each island.. key=color...value=area.
        
        
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(grid[i][j]!=0&&!vis[i][j]){
                    
                    temp=0;
                    
                    dfs(grid,vis,i,j);
                    
                    mp.put(color,temp);
                    
                    color++;//after an island is found color is increased to give the next island a distinct color
                    
                }else if(grid[i][j]==0){
                    //adding 0 elements ..
                    colorMap.add(new Pair(i,j));
                }
            }
        }
        
        for(Pair p:colorMap){
            
            int ans=1;
            
            Set<Integer> s=new HashSet<>();
            
            for(int k=0;k<4;k++){
                //checking it's adjacent up,down ,right and left boxes..
                //for eg: if grid[2][3]=0..then it may act as a bridge to link the adjacent islands and make a bigger island
                
                //for a grid[i][j]=0...
                //making grid[i][j]=1 and adding it's area to its adjacent islands..if there is any....
                
                 int r=p.i+row[k];
                 int c=p.j+col[k];
                
                 if(isValid(grid,r,c))
                 {
                    s.add(grid[r][c]);
                 }
                
            }
            
            for(int colors:s){
                    ans+=mp.get(colors);
               
            }
            
            max=Math.max(ans,max);
        }
        
        
        return max==-1?(M*N):max;
    }
    
    
    public boolean isValid(int[][] grid,boolean[][] vis,int r,int c){
        return (r<M)&&(r>=0)&&(c<N)&&(c>=0)&&(!vis[r][c])&&(grid[r][c]!=0);
    }
    
    public boolean isValid(int[][] grid,int r,int c){
        return (r<M)&&(r>=0)&&(c<N)&&(c>=0)&&(grid[r][c]!=0);
    }
    
    public void dfs(int[][] grid,boolean[][] vis,int i,int j){
        vis[i][j]=true;
        
        grid[i][j]=color;//coloring the boxes of a particular island a same color
        
        temp++;//increasing area by 1
        
        for(int k=0;k<4;k++){
            int r=i+row[k];
            int c=j+col[k];
            if(isValid(grid,vis,r,c)){
                dfs(grid,vis,r,c);
            }
        }
        
    }
}
