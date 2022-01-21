class Solution {
   
    //basic use of adjacency matrix....
    public int minTrioDegree(int n, int[][] edges) {
      //leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/
      //leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/discuss/1064557/C%2B%2B-O(n-3)-Adjacency-List-vs.-Matrix
        
        int[] indeg=new int[n+1];
        int[][] adj=new int[n+1][n+1];
        int ans=Integer.MAX_VALUE;
        
        for(int[] edge:edges){
        
            adj[Math.min(edge[0],edge[1])][Math.max(edge[0],edge[1])]=1;
            ++indeg[edge[0]];
            ++indeg[edge[1]];
            
            //to save time all edges are said to be connecting from a low value node to a high value node..
            //eg: if there is an edge between node 2 and node 1..
            //then it adj[1][2]=1
            //but adj[2][1]=0..it is not done 1 ro save time in future iterations..
            
            //by doing this we are also making the graph directed..with small value node always pointing towards a higher value node if there exists an edge between them
            
        }
        
         //checking the edges in only increasing order since we made adj =1 by computing adj[Math.min(edge[0],edge[1])][Math.max(edge[0],edge[1])]...
         //k > j > i, so we do not check the same triplet twice.
         //- For that, we have used a directed adjacency list (or matrix).

        for(int i=1;i<=n;++i){
            for(int j=i+1;j<=n;++j){
                if(adj[i][j]!=0){
                    for(int k=j+1;k<=n;++k){
                        if(adj[i][k]!=0&&adj[j][k]!=0){
                            ans=Math.min(indeg[i]+indeg[j]+indeg[k]-6,ans);
                        }
                    }
                }
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
        
    }
}
