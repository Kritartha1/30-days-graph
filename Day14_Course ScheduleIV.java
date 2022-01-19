class Solution {
    
  //floyd warshal is used..
  //time complexity:O(V^3)
  //https://leetcode.com/problems/course-schedule-iv/
  //https://www.youtube.com/watch?v=nV_wOZnhbog
  //space: O(V^2)
     int[][] graph;
     int INT_MAX=(int)(1e7)+9;
    
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> ans=new ArrayList<>();
        graph=new int[numCourses][numCourses];
        
        buildGraph(numCourses,prerequisites);
        
        //idea is to run a floyd warshal algorithm..
        //for all the prerequisites courses to take to take the next course, a positive edge weight is given..
        //if there is no prereuisites edge given to us, we can't take that course..
        //so we have put other edges other than the prerequisites to infinity..
        //so in the end, if we get distance from i to j i.e graph[i][j]=infinity..then we can't take course j because i was not a prerequisite mentioned before.
        
        floydWarshal(numCourses);
        
        for(int[] query:queries){
            if(graph[query[0]][query[1]]!=INT_MAX){
                //if dist between any two nodes is infinity , then we can say that course i is not a prerequisite of course j. 
                ans.add(true);
            }else{
                ans.add(false);
            }
        }
        return ans;
    }
    
    /////////////////////////////////////////////////////////////////
    
    
    public void buildGraph(int V,int[][] prq){
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                graph[i][j]=INT_MAX;//dist nodes are initialized with infinity
                graph[i][i]=0;//dist between self loops are 0
               
            }
        }
        //graph buillding from the prerequisites array given
        for(int[] edge:prq){
            //i=edge[0]
            //j=edge[1]
            //if i is a prerequiste of node j, then there exists a positve edge weight between them..else infinity
            graph[edge[0]][edge[1]]=1;
        }
        
    }
    
    /////////////////////////////////////////////////////////////////
    
    public void floydWarshal(int V){
        for(int k=0;k<V;k++){
            for(int i=0;i<V;i++){
                for(int j=0;j<V;j++){
                    if(graph[i][k]==INT_MAX||graph[k][j]==INT_MAX){
                        continue;
                    }
                    //typical floyd warshall algorithm..
                    graph[i][j]=Math.min(graph[i][j],graph[i][k]+graph[k][j]);
                }
            }
        }
    }
}
