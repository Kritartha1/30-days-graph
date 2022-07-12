class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] vis=new int[numCourses];
        HashMap<Integer,ArrayList<Integer>> mp=new HashMap<>();
        buildGraph(numCourses,mp,prerequisites);
        for(int i=0;i<numCourses;i++){
            if(vis[i]==0&&!dfs(mp,vis,i)){
                return false;
            }
        }
        return true;
    }
    void buildGraph(int n,HashMap<Integer,ArrayList<Integer>> mp,int[][] edges){
        for(int[] edge:edges){
            mp.putIfAbsent(edge[1],new ArrayList<Integer>());
            mp.get(edge[1]).add(edge[0]);
        }
    }
    boolean dfs(HashMap<Integer,ArrayList<Integer>> mp,int[] vis,int v){
        if(vis[v]==1){
            return false;
        }
        vis[v]=1;
        for(int neighbours:mp.getOrDefault(v,new ArrayList<Integer>())){
            if((vis[neighbours]!=2&&!dfs(mp,vis,neighbours))){
                return false;
            }
        }
        vis[v]=2;
        return true;
    }
}
