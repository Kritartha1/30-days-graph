class Solution {
    //Applying Kahn's algorithm!!!! with a little modification
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        ArrayList<ArrayList<Integer>> g = new ArrayList<>(numCourses);

        buildGraph(g,numCourses,prerequisites);
        return findTopOrdering(g,numCourses);

    }
    
    public void buildGraph(ArrayList<ArrayList<Integer>> g,int numCourses, int[][] prerequisites){
        for (int i = 0; i < numCourses; i++) {
            g.add(new ArrayList<Integer>());
        }


        for (int i = 0; i < prerequisites.length; i++) {
            ArrayList<Integer> a = g.get(prerequisites[i][1]);

            a.add(prerequisites[i][0]);
            g.set(prerequisites[i][1], a);

        }
    }
    
    //Aplying Kahn's algorithm for topoligical sort
    public boolean findTopOrdering( ArrayList<ArrayList<Integer>> g,int numCourses){
        int[] indeg=new int[numCourses];
        for(int i=0;i<numCourses;i++){
            for(int to:g.get(i)){
                indeg[to]++;
            }
        }
        Queue<Integer> q=new LinkedList<>();
        for(int i=0;i<numCourses;i++){
            if(indeg[i]==0){
                q.add(i);
            }
        }
        int idx=0;
        while (!q.isEmpty()){
            int temp=q.poll();
            idx++;
            for(int to:g.get(temp)){
                indeg[to]--;
                if(indeg[to]==0){
                    q.add(to);
                }
            }
        }
        if(idx!=numCourses){
            return false;
        }return true;
    }
    
}
