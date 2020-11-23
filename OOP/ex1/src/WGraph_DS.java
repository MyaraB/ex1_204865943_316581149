package ex1.src;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * This class represents a weighted graph.
 * includes all the basic operations of a weighted graph.
 */


public class WGraph_DS implements weighted_graph {
    private class NodeInfo implements node_info {
        private double tag;
        private int key;
        private int id = 0;
        private String info;

        /**
         *initializes a node
         */
            public NodeInfo(int key){
                this.key =key;
                this.tag =0;
                this.info ="";

        }

            public int compareTo(node_info other){
            if(this.getTag()==other.getTag())
                return 0;
            else if(this.getTag()>other.getTag())
                return 1;
            else
                return -1;
        }
            @Override
            public int getKey () {
                return this.key;
            }

            @Override
            public String getInfo () {
                return this.info;
            }

            @Override
            public void setInfo (String s){
                this.info = s;
            }

            @Override
            public double getTag () {
                return this.tag;
            }

            @Override
            public void setTag ( double t){
                this.tag = t;
            }
        }
    /**
     *basic parameters of a weighted graph
     */
        private HashMap<Integer,node_info> gi;
        private HashMap<node_info, HashMap<node_info,Double>> edge;
        private int nodeSize, edgeSize, mcSize;

    /**
     *initializes a weighted graph
     */
        public WGraph_DS(){
        gi = new HashMap<>();
        edge = new HashMap<>();
        nodeSize = 0;
        edgeSize = 0;
        mcSize = 0;
    }

    /**
     *copies a weighted graph data to another weight graph
     */
    public WGraph_DS(weighted_graph t) {
            edge = new HashMap<>();
            gi = new HashMap<>();
        if (t != null) {
            for (node_info it : t.getV()) {
                addNode(it.getKey());
            }
            for ( int i : gi.keySet())
                for (int j : gi.keySet()) {
                    if (t.hasEdge(i, j))
                        this.connect(i, j,t.getEdge(i,j));
                }
            this.nodeSize = t.nodeSize();
            this.edgeSize = t.edgeSize();
            this.mcSize = t.getMC();
        }
    }
    /**
     * returns a node by its key
     */
        @Override
        public node_info getNode(int key) {
            return gi.get(key);
        }
    /**
     *checks if the hashmap edge contains the two relevanct nodes.
     * it checks both ways
     */
        @Override
        public boolean hasEdge(int node1, int node2) {
            node_info node1n = getNode(node1), node2n =getNode(node2);
            if ((edge.get(getNode(node1))!= null) && (edge.get(getNode(node2))!= null) && (edge!=null)) {
                if (edge.get(node2n).containsKey(node1n) && edge.get(node1n).containsKey(node2n)) {
                    return true;
                }
            }
            return false;
        }
    /**
     *this method returns the weight stored in the connection of the two nodes.
     */

        @Override
        public double getEdge(int node1, int node2) {
            if(hasEdge(node1,node2)){
            node_info node1n =getNode(node1);
            node_info node2n =getNode(node2);
            return edge.get(node1n).get(node2n);}
            return -1;
        }

        @Override
        public void addNode(int key) {
            if(key>0){
                if (!gi.containsKey(key)) {
                gi.put(key, new NodeInfo(key));
                    nodeSize++;
                    mcSize++;
                }
            }
        }
    /**
     * connects two nodes and stores a double value in the edge.
     * makes sure nothing is null and that there is no prior connection. connects
     * both sides.
     */
        @Override
        public void connect(int node1, int node2, double w) {
            if (w >= 0) {
                if (this.getNode(node1) == null)
                    gi.put(node1, new NodeInfo(node1));
                if (this.getNode(node2) == null)
                    gi.put(node2, new NodeInfo(node2));
                node_info node1n = getNode(node1), node2n = getNode(node2);
                if (node1n != null && node2n != null) {
                    if (node1 != node2) {
                        if (!hasEdge(node1, node2)) {
                            if (edge.get(node1n) == null)
                                edge.put(node1n, new HashMap<>());
                            if (edge.get(node2n) == null)
                                edge.put(node2n, new HashMap<>());
                            edge.get(node1n).put(node2n, w);
                            edge.get(node2n).put(node1n, w);
                            edgeSize++;
                            mcSize++;

                        }
                    }
                }
            }
        }
    /**
     *returns a collection of all the nodes in the graph using the Hashmap Value() function
     */
        @Override
        public Collection<node_info> getV(){
               return gi.values();
        }
    /**
     *returns a collection of all the neighboring nodes by using the keySet() function of hashmap
     */
        @Override
        public Collection<node_info> getV(int node_id) {
            return edge.get(getNode(node_id)).keySet();
        }
    /**
     *this method removes a node and makes sure its connections are also severed to all its neighbors
     */
        @Override
        public node_info removeNode(int key) {
            if (getNode(key) != null) {
                node_info keyn = getNode(key);
                if (edge.containsKey(keyn)&& edge.get(keyn)!=null) {

                    Iterator<node_info> itr = getV(key).iterator();
                    while(itr.hasNext()){
                        node_info temp = itr.next();
                        this.removeEdge(key, temp.getKey());
                        itr=getV(key).iterator();
                    }
                    mcSize++;
                    nodeSize--;
                    edge.remove(keyn);
                    return gi.remove(key);
                }
            }
            return null;
        }

    /**
     * removes an edge between two nodes. does it both ways
     */

        @Override
        public void removeEdge(int node1, int node2) {
            if (hasEdge(node1, node2)) {
                edge.get(getNode(node2)).remove(getNode(node1));
                edge.get(getNode(node1)).remove(getNode(node2));
                edgeSize--;
                mcSize++;
            }

        }

    /**
     *
     */
        public boolean compare(node_info n,node_info m){
            if(n.getKey()==m.getKey())
                return true;
            else return false;
        }
    /**
     *
     */
        @Override
        public int nodeSize() {return nodeSize; }
    /**
     *
     */
        @Override
        public int edgeSize() {
            return edgeSize;
        }
    /**
     *
     */
        @Override
        public int getMC() {
            return mcSize;
        }
/**
 *
 */
}

