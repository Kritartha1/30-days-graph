class Solution {
    int i;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        ArrayList<ArrayList<Integer>> g = new ArrayList<>(numCourses);

        buildGraph(g,numCourses,prerequisites);
        boolean[] vis=new boolean[numCourses];
        int[] order=new int[numCourses];
        i=numCourses-1;
        for(int at=0;at<numCourses;at++){
            // System.out.println(i);
            if(!vis[at]&&!dfs(at,vis,order,g,new boolean[numCourses])){
                return new int[0];
            }
        }
        
        return order;

    }
    boolean dfs(int at,boolean[] vis,int[] order,ArrayList<ArrayList<Integer>> g,boolean[] onStack){

        vis[at]=true;
        onStack[at]=true;
        for(int neighbour:g.get(at)){
            if(onStack[neighbour]||(!vis[neighbour]&&!dfs(neighbour,vis,order,g,onStack))){
                return false;
            }
        }
        order[i]=at;
        onStack[at]=false;
        i=i-1;
        return true;
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
    
    
    
}
