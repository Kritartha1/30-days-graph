class Solution {
//leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        //simple logic..
        //if any node has no indegree..means it is the part of our ans..
        int[] indeg=new int[n];
        for(List<Integer>edge:edges){
            ++indeg[edge.get(1)];
        }
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(indeg[i]==0){
                ans.add(i);
            }
        }
        
        return ans;
    }
    
}
