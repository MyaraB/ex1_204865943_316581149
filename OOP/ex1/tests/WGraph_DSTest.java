package ex1.tests;


import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_DSTest {
    private weighted_graph b = new WGraph_DS();
    public static weighted_graph graph_creator(int vsize) {
        weighted_graph g = new WGraph_DS();
        for (int i = 0; i < vsize; i++) {
            g.addNode(i);
            g.getNode(i).setInfo("");
            g.getNode(i).setTag(0);
        }
        return g;
    }


        @Test
        void getNode () {
        b.addNode(1);
        b.addNode(2);
        b.getNode(1).getKey();
        b.getNode(1).setInfo("hello");
        b.getNode(2).setInfo("bye");
        b.getNode(1).setTag(100);
            Assertions.assertEquals(b.getNode(1).getTag(),100);
            Assertions.assertEquals(b.getNode(2).getInfo(),"bye");
        }



        @Test
        void hasEdge () {
            b.addNode(1);
            b.addNode(2);
            b.addNode(3);
            b.getNode(1).setInfo("hello");
            b.getNode(2).setInfo("bye");
            b.getNode(1).setTag(100);
            b.getNode(2).setTag(0);
        b.connect(1,2,3);
            b.connect(1,3,3);
        assertTrue(b.hasEdge(1,2));


        }

        @Test
        void getEdge () {

            b.addNode(1);
            b.getNode(1).setTag(0);
            b.getNode(1).setInfo("hello");
            b.addNode(2);
            b.getNode(2).setTag(0);
            b.getNode(2).setInfo("hello");
            b.addNode(3);
            b.getNode(3).setTag(0);
            b.getNode(3).setInfo("hello");
            b.connect(1,2,10);
            b.connect(1,3,9);
            b.connect(2,3,7);
            assertEquals(b.getEdge(1,3),9);
            assertEquals(b.getEdge(1,2),10);
            assertEquals(b.getEdge(2,3),7);
        }

        @Test
        void addNode () {
        b.addNode(1);
        b.addNode(1);
        b.addNode(2);
        assertEquals(b.getNode(1).getKey(),1);
            assertEquals(b.getNode(2).getKey(),2);
        }

        @Test
        void connect () {
            for (int i = 0; i < 10; i++) {
                b.addNode(i);
            }
                b.connect(1,2,10);
            b.connect(1,3,10);
            b.connect(1,4,10);
            b.connect(1,5,10);
            b.connect(1,6,10);
            b.connect(1,7,10);
            b.connect(1,8,10);
            b.connect(1,9,10);
            b.connect(2,3,10);
            b.connect(2,4,10);
            b.connect(2,4,8);
            b.connect(2,1,10);
            assertEquals(b.getEdge(2,4),10);

        }



        @Test
        void getV () {
            for (int i = 0; i < 10; i++) {
                b.addNode(i);
            }
            for(node_info itr: b.getV()){
                itr.setTag(5);
            }
            assertEquals(b.getNode(2).getTag(),5);

        }

        @Test
        void testGetV () {
            for (int i = 0; i < 10; i++) {
                b.addNode(i);
            }
            b.connect(2,3,10);
            b.connect(1,4,10);
            b.connect(4,5,10);
            b.connect(1,6,10);
            b.connect(1,7,10);
            b.connect(6,8,10);
            b.connect(1,9,10);
            b.connect(2,3,10);
            b.connect(2,4,10);
            b.connect(3,4,8);
            b.connect(2,1,10);
            for(node_info itr: b.getV(1)){
                itr.setTag(3);
            }
            assertEquals(b.getNode(6).getTag(),3);
            assertEquals(b.getNode(7).getTag(),3);
            assertEquals(b.getNode(9).getTag(),3);
        }

        @Test
        void removeNode () {
            for (int i = 0; i < 10; i++) {
                b.addNode(i);
            }
            b.connect(1,2,10);
            b.connect(1,3,10);
            b.connect(2,3,10);
            b.removeNode(1);
            assertEquals(b.edgeSize(),1);
            assertEquals(b.nodeSize(),8);

        }

        @Test
        void removeEdge () {
            for (int i = 0; i < 10; i++) {
                b.addNode(i);
            }
            b.connect(1,3,5);
            b.connect(2,4,4);
            assertEquals(b.hasEdge(2,4),true);
            b.removeEdge(2,4);
            assertEquals(!b.hasEdge(2,4),true);

        }

        @Test
        void nodeSize () {
            for (int i = 0; i < 10; i++) {
                b.addNode(i);
            }
        assertEquals(b.nodeSize(),9);
        }

        @Test
        void edgeSize () {
            for (int i = 0; i < 10; i++) {
                b.addNode(i);
            }
            b.connect(1,3,5);
            b.connect(2,4,4);
            assertEquals(b.edgeSize(),2);
        }

        @Test
        void getMC () {  for (int i = 0; i < 10; i++) {
            b.addNode(i);
        }
            assertEquals(b.getMC(),9);
        }
    }
