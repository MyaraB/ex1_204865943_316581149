package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WGraph_AlgoTest {
    private weighted_graph b = new WGraph_DS();
    private weighted_graph_algorithms c = new WGraph_Algo();

    @Test
    void init() {
        for (int i = 0; i < 10; i++) {
            b.addNode(i);
        }
        c.init(b);
        assertEquals(c.getGraph().getNode(1).getKey(), 1);


    }

    @Test
    void getGraph() {
        for (int i = 0; i < 10; i++) {
            b.addNode(i);
        }
        c.init(b);
        assertEquals(c.getGraph().getNode(1).getKey(), 1);

    }

    @Test
    void copy() {
        for (int i = 0; i < 10; i++) {
            b.addNode(i);
        }
        c.init(b);
        weighted_graph test = new WGraph_DS();
        test = c.copy();
        assertEquals(test.getNode(1).getKey(),1);

    }

    @Test
    void isConnected() {
        for (int i = 1; i < 10; i++) {
            b.addNode(i);
        }
        c.init(b);
        assertTrue(!(c.isConnected()));
        b.connect(1,2,5);
        b.connect(1,3,5);
        b.connect(1,4,5);
        b.connect(1,5,5);
        b.connect(1,6,5);
        b.connect(1,7,5);
        b.connect(1,8,5);
        b.connect(2,9,5);
        c.init(b);
        assertTrue(c.isConnected());
    }

    @Test
    void shortestPathDist() {
        for (int i = 1; i < 10; i++) {
            b.addNode(i);
        }
        b.connect(1,2,1);
        b.connect(2,3,1);
        b.connect(3,4,1);
        b.connect(4,5,1);
        b.connect(2,5,5);
        b.connect(5,6,1);
        b.connect(6,7,1);
        b.connect(1,7,10);
        c.init(b);
        assertEquals(c.shortestPathDist(1,7),6);
    }

    @Test
    void shortestPath() {
        for (int i = 1; i < 10; i++) {
            b.addNode(i);
        }
        b.connect(1,2,1);
        b.connect(2,3,1);
        b.connect(3,4,1);
        b.connect(4,5,1);
        b.connect(2,5,5);
        b.connect(5,6,1);
        b.connect(6,7,1);
        b.connect(1,7,10);
        c.init(b);

        List<node_info> shortPaths = new ArrayList<>();
        shortPaths.add(b.getNode(1));
        shortPaths.add(b.getNode(2));
        shortPaths.add(b.getNode(3));
        shortPaths.add(b.getNode(4));
        shortPaths.add(b.getNode(5));
        shortPaths.add(b.getNode(6));
        shortPaths.add(b.getNode(7));
        Collections.reverse(shortPaths);
        assertEquals(c.shortestPath(1,7),shortPaths);
    }

    @Test
    void save() {

    }

    @Test
    void load() {
    }
}