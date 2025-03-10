package algo.practice.graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.util.*;
import java.util.List;

public class GraphVisualizer extends JPanel {
    private final Object graph;
    private final Map<Integer, Point> nodePositions;
    private final int radius = 20;
    private final boolean isDirected;
    private final String graphType;

    public GraphVisualizer(Object graph) {
        this.graph = graph;
        this.nodePositions = new HashMap<>();
        this.isDirected = graph instanceof DirectedGraph;
        this.graphType = isDirected ? "Directed Graph" : "Undirected Graph";
    }

    public void draw() {
        layoutGraph();
        createAndShowFrame();
    }

    private void layoutGraph() {
        int width = 600, height = 600;
        int centerX = width / 2, centerY = height / 2;
        int V = (graph instanceof DirectedGraph) ? ((DirectedGraph) graph).V : ((UndirectedGraph) graph).V;
        double angleStep = 2 * Math.PI / V;

        for (int i = 0; i < V; i++) {
            int x = centerX + (int) (200 * Math.cos(i * angleStep));
            int y = centerY + (int) (200 * Math.sin(i * angleStep));
            nodePositions.put(i, new Point(x, y));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString(graphType, 250, 20);

        List<List<Integer>> adjList = (graph instanceof DirectedGraph) ? ((DirectedGraph) graph).adjList : ((UndirectedGraph) graph).adjList;
        int V = (graph instanceof DirectedGraph) ? ((DirectedGraph) graph).V : ((UndirectedGraph) graph).V;

        for (int u = 0; u < V; u++) {
            Point pos1 = nodePositions.get(u);
            for (int v : adjList.get(u)) {
                Point pos2 = nodePositions.get(v);

                if (u == v) { // Detect self-loop
                    drawSelfLoop(g2, pos1.x, pos1.y);
                } else if (isDirected) {
                    drawArrowLine(g2, pos1.x, pos1.y, pos2.x, pos2.y);
                } else if (u < v) {
                    g2.drawLine(pos1.x, pos1.y, pos2.x, pos2.y);
                }
            }
        }

        for (int node = 0; node < V; node++) {
            Point pos = nodePositions.get(node);
            g2.setColor(Color.WHITE);
            g2.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
            g2.setColor(Color.BLACK);
            g2.drawOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
            g2.drawString(String.valueOf(node), pos.x - 5, pos.y + 5);
        }
    }

    private void drawSelfLoop(Graphics2D g, int x, int y) {
        int loopSize = 40; // Size of the loop
        int arrowSize = 10; // Arrowhead size

        // Control points to create a smooth loop
        int ctrlX1 = x - loopSize;
        int ctrlY1 = y - 2 * loopSize;
        int ctrlX2 = x + loopSize;
        int ctrlY2 = y - 2 * loopSize;

        // Create a cubic Bézier curve for a smoother self-loop
        CubicCurve2D loop = new CubicCurve2D.Float();
        loop.setCurve(x, y - radius, ctrlX1, ctrlY1, ctrlX2, ctrlY2, x, y - radius);

        // Draw the self-loop curve
        g.setColor(Color.BLACK);
        g.draw(loop);

        // Compute the arrowhead position at the endpoint of the curve
        double angle = Math.atan2(y - ctrlY2, x - ctrlX2);
        int xArrow1 = (int) (x - arrowSize * Math.cos(angle - Math.PI / 6));
        int yArrow1 = (int) (y - radius - arrowSize * Math.sin(angle - Math.PI / 6));
        int xArrow2 = (int) (x - arrowSize * Math.cos(angle + Math.PI / 6));
        int yArrow2 = (int) (y - radius - arrowSize * Math.sin(angle + Math.PI / 6));

        // Draw the arrowhead
        g.fillPolygon(new int[]{x, xArrow1, xArrow2}, new int[]{y - radius, yArrow1, yArrow2}, 3);
    }

    private void drawArrowLine(Graphics2D g, int x1, int y1, int x2, int y2) {
        g.setStroke(new BasicStroke(2)); // Make the line thicker
        g.drawLine(x1, y1, x2, y2); // Draw the edge line

        int arrowSize = 20; // Increase arrow size
        double angle = Math.atan2(y2 - y1, x2 - x1);

        // Move arrowhead back a little so it's visible
        int newX2 = x2 - (int) (Math.cos(angle) * arrowSize);
        int newY2 = y2 - (int) (Math.sin(angle) * arrowSize);

        int xArrow1 = newX2 - (int) (arrowSize * Math.cos(angle - Math.PI / 6));
        int yArrow1 = newY2 - (int) (arrowSize * Math.sin(angle - Math.PI / 6));
        int xArrow2 = newX2 - (int) (arrowSize * Math.cos(angle + Math.PI / 6));
        int yArrow2 = newY2 - (int) (arrowSize * Math.sin(angle + Math.PI / 6));

        // Change color to red so it's visible
        g.setColor(Color.BLACK);
        g.fillPolygon(new int[]{x2, xArrow1, xArrow2}, new int[]{y2, yArrow1, yArrow2}, 3);

        // Reset to black after drawing
        g.setColor(Color.BLACK);
    }

    private void createAndShowFrame() {
        JFrame frame = new JFrame("Graph Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.add(this);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        UndirectedGraph undirectedGraph = new UndirectedGraph(10);
        undirectedGraph.addEdge(0, 1);
        undirectedGraph.addEdge(0, 2);
        undirectedGraph.addEdge(1, 3);

        GraphVisualizer undirectedGraphVisualizer = new GraphVisualizer(undirectedGraph);
        undirectedGraphVisualizer.draw();

        DirectedGraph directedGraph = new DirectedGraph(10);
        directedGraph.addEdge(0, 1);
        directedGraph.addEdge(0, 2);
        directedGraph.addEdge(1, 3);

        GraphVisualizer direcetdGraphVisualizer = new GraphVisualizer(directedGraph);
        direcetdGraphVisualizer.draw();
    }
}
