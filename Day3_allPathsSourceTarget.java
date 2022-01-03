class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //vis array is not taken bcoz any node having indegree>1 can be visited multiple times.
        List<List<Integer>> ans=new ArrayList<>();
        int n=graph.length;
        
        List<Integer> temp=new ArrayList<>();
        temp.add(0);
        
        dfs(0,n-1,graph,ans,temp);
        return ans;
    }
    public void dfs(int src,int dest,int[][] graph,List<List<Integer>> ans,List<Integer> temp){
        if(src==dest){
            ans.add(new ArrayList<Integer>(temp));
            return;
        }
        for(int neighbour:graph[src]){
            temp.add(neighbour);
            dfs(neighbour,dest,graph,ans,temp);
            temp.remove(temp.size()-1);
        }
    }
}
