package ex1.src;

import ex0.node_data;

import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
/**
 *this class uses weighted graphs in order to use weighted graph algo
 * more advanced functions of a weighted graph
 */
public class WGraph_Algo implements weighted_graph_algorithms,Serializable {
    /**
     *
     */
            private weighted_graph wga;
            private node_info a;
    /**
     *
     */
            @Override
            public void init (weighted_graph g){
                this.wga = g;
            }
    /**
     *
     */
            @Override
            public weighted_graph getGraph () {
                return wga;
            }
    /**
     *copies a weighted graph algo
     */
            @Override
            public weighted_graph copy () {
                return new WGraph_DS(wga);
            }
    /**
     *checks if all the nodes in the graph are connected. using an iterator we go over all the nodes
     * making sure we visited all of them just by getting the neighbors of a node.
     */
            @Override
            public boolean isConnected () {
                if(wga.nodeSize()==1)
                    return true;
                else if(wga.nodeSize()>1){
                    Queue<node_info> tagged = new LinkedList<>();
                    Iterator<node_info> itr = getGraph().getV().iterator();
                    while(itr.hasNext()){
                        itr.next().setTag(-1);
                    }
                    Iterator<node_info> tagger = wga.getV().iterator();
                    node_info base = tagger.next();
                    tagged.add(base);
                    while(!tagged.isEmpty()) {
                        base = tagged.remove();
                        if (!wga.getV(base.getKey()).isEmpty()) {
                            Iterator<node_info> itr2 = wga.getV(base.getKey()).iterator();
                            while (itr2.hasNext())
                                if (itr2.next() != null)
                                    if (itr2.next().getTag() == -1) {
                                        itr2.next().setTag(0);
                                        tagged.add(itr2.next());
                                    }
                        }
                    }
                    for (node_info a : wga.getV())
                        if (a.getTag() == -1)
                            return false;
                }
                return true;

            }
    /**
     *finds the shortest path between two nodes by storing each distance from the source in the tag.
     * the dijkstra algorithm is implemnted in this function.
     */
            @Override
            public double shortestPathDist ( int src, int dest){
                if(wga.getNode(src)==null || wga.getNode(dest)==null)
                    return -1;
                int counter = wga.nodeSize();
                PriorityQueue<node_info> pq = new PriorityQueue<node_info>(wga.nodeSize(),Comparator.comparingDouble(node_info::getTag));
                for (node_info a : wga.getV()) {
                    a.setTag(-1);
                }
                node_info srca = wga.getNode(src);
                pq.add(srca);
                srca.setTag(0);
                    while (!pq.isEmpty()) {
                        node_info spot = pq.poll();
                        counter--;
                        for (node_info neigh : wga.getV(spot.getKey())) {
                            if (neigh.getTag() == -1 || spot.getTag()+wga.getEdge(neigh.getKey(), spot.getKey())< neigh.getTag()) {
                                neigh.setTag(wga.getEdge(spot.getKey(), neigh.getKey())+spot.getTag());
                                pq.add(neigh);
                            }
                        }
                    }
                if(counter==wga.nodeSize()&&wga.getNode(dest).getTag()==-1)
                    return -1;
                else return wga.getNode(dest).getTag();

            }

    /**
     *using the function of shortestPathDist we find all the nodes int the shortest path from the src to dest
     * starting from the dest we add each node to a linked list. making sure to add only the nodes with the shortest distance
     */
            @Override
            public List<node_info> shortestPath ( int src, int dest) {
                node_info sra = wga.getNode(src);
                node_info desa = wga.getNode(dest);
                if (sra == null || desa == null)
                    return null;
                List<node_info> shortPath = new ArrayList<>();
                double length = shortestPathDist(src, dest);
                node_info destination = wga.getNode(dest);
                destination.setTag(length);
                if (length == -1)
                    return null;
                shortPath.add(sra);
                if (length == 0)
                    return shortPath;
               double min = destination.getTag();
               node_info minN = destination;
                shortPath.remove(sra);
                node_info pointer = destination;
                while (pointer.getKey() != src) {
                    shortPath.add(pointer);
                    node_info n;
                    Iterator<node_info> itr = wga.getV(pointer.getKey()).iterator();
                    while(itr.hasNext()){
                        n = itr.next();
                        if(min>n.getTag() && n.getTag()>=0) {
                            min=n.getTag();
                            minN=n;
                            }
                        }
                    pointer = minN;
                    }
                shortPath.add(pointer);
                Collections.reverse(shortPath);
                return shortPath;
                }

    /**
     *implementation of a save function in order to save an object to a file
     */

            @Override
            public boolean save (String file) {
                boolean check = false;
                ObjectOutputStream out;
                try {
                    FileOutputStream fout = new FileOutputStream(file,true);
                    out = new ObjectOutputStream(fout);
                    out.writeObject(this);
                    check = true;
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return check;
            }
    /**
     * implementation of a load function in order to load a saved file.
     */
            @Override
            public boolean load (String file) {
                try {
                    FileInputStream sIn = new FileInputStream(file);
                    ObjectInputStream ObjIn = new ObjectInputStream(sIn);
                    weighted_graph read = (weighted_graph) ObjIn.readObject();
                    this.init(read);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

        }