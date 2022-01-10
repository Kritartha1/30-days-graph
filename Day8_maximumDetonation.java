class Solution {
    public int ans=0;
    public int maximumDetonation(int[][] bombs) {
        int max=0;
        int n=bombs.length;
        boolean vis[]=new boolean[n];
        for(int i=0;i<n;i++){
            //we are checking for each circle/bomb
            //i.e if ith bomb is detonated, then getting the answer for that bomb..and comparing with our max answer
            Arrays.fill(vis,false);
            ans=0;
            dfs(i,bombs,vis);
            max=Math.max(max,ans);
        }
        return max;
        
    }
    
    public void dfs(int i,int[][] bombs,boolean[] vis){
        vis[i]=true;
        ans++;
        
        for(int j=0;j<vis.length;j++){
            if(i!=j&&!vis[j]&&neighbourCircle(bombs[i],bombs[j])){
                
                dfs(j,bombs,vis);
            }
        }
        
    }
    //for bomb2 be inside the circumference of bomb1..
    //the dist between their centres must be less than/equal to the radius of the circle 1/bomb1
    public boolean neighbourCircle(int[] bomb1,int bomb2[]){
        long x2=bomb1[0]-bomb2[0];
        long y2=bomb1[1]-bomb2[1];
        long r2=bomb1[2];
        return x2*x2+y2*y2<=r2*r2;
        
    }
    
}
