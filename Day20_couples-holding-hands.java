class Solution {
    //Union find:
    //https://leetcode.com/problems/couples-holding-hands/submissions/
    //Explanation://leetcode.com/problems/couples-holding-hands/discuss/117520/Java-union-find-easy-to-understand-5-ms
    
    
    //check group theory:youtube..
    //for a cycle of n1 nodes..it takes n1-1 swaps..
    //so, from n nodes...check the number of connected components..
    //each component constitute a cycle( may be a self loop also..)
    //so for k connected components..
    //swaps=(n1-1)+(n2-1)+...(nk-1)=N-k swaps
    
    public int minSwapsCouples(int[] row) {
        
        int N=row.length/2;
        
        UnionFind uf=new UnionFind(N);
        
        for(int i=0;i<N;i++){
            
            int u=uf.find(row[2*i]/2);
            int v=uf.find(row[2*i+1]/2);
            //division by 2 is done..
            //to show that two partners of a pair should have same parent..
            //eg 0 and 1 are couples..
            //0/2=1. 1/2=0... same parent..
            
            uf.union(u,v);
            
        }
        
        return N-uf.count;
    }
    class UnionFind{
        int[] parent;
        int count;
        
        UnionFind(int N){
            this.count=N;
            this.parent=new int[N];
            for(int i=0;i<N;i++){
                this.parent[i]=i;
            }
        }
        
        int find(int v){
            if(parent[v]==v){
                return v;
            }
            return parent[v]=find(parent[v]);
        }
        
        void union(int u,int v){
            
            int from=find(u);
            int to=find(v);
            
            if(from!=to){//connect an edge between them
                parent[u]=v;
                --count;
            }
             
        }
        
    }
}
