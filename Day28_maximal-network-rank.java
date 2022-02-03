class Solution {
    //leetcode.com/problems/maximal-network-rank/
    
    
    public int maximalNetworkRank(int n, int[][] roads) {
        int[][] connections=new int[n][n];
        int[] deg=new int[n];
        for(int[] road:roads){
            deg[road[0]]++;
            deg[road[1]]++;
            connections[road[0]][road[1]]=1;
            connections[road[1]][road[0]]=1;
            
        }
        int ans=0;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                ans=Math.max(ans,deg[i]+deg[j]-connections[i][j]);
                //if there is a connection between the two cities ,then we have to desacard the extra degree added...bcoz one is road is common between them ..
            }
        }
        
        return ans;
        
    }
}
