/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    //we are doing simple dfs
    public HashMap<Integer, Node> mp = new HashMap<>();
    //map is like the visited array in dfs
    
    public Node cloneGraph(Node node) {
        return clone(node);
    }
    public Node clone(Node node){
        if(node==null){
            return null;
        }
        //if we encountered the node before, then we must have run a dfs of the node earlier..
        //so the node must have the complete structure which can be used as it is... 
        if(mp.containsKey(node.val)){
            return mp.get(node.val);
        }
        Node temp=new Node(node.val,new ArrayList<Node>());
        mp.put(node.val,temp);
        for(Node neighbor :node.neighbors){
            temp.neighbors.add(clone(neighbor));
        }
        return temp;
    }
}
