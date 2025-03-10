package algo.practice.graph.basic;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

class Edge {
    int destination;
    int weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class WeightedGraphVisualizer extends JPanel {
    private final Map<Integer, List<Edge>> adjacencyList;
    private final Map<Integer, Point> nodePositions;
    private final int radius = 20;

    public WeightedGraphVisualizer(Map<Integer, List<Edge>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.nodePositions = new HashMap<>();
        layoutGraph();
    }

    private void layoutGraph() {
        int width = 500, height = 500;
        int centerX = width / 2, centerY = height / 2;
        int angleStep = 360 / Math.max(1, adjacencyList.size());
        int index = 0;
        for (int node : adjacencyList.keySet()) {
            int angle = index * angleStep;
            int x = centerX + (int) (150 * Math.cos(Math.toRadians(angle)));
            int y = centerY + (int) (150 * Math.sin(Math.toRadians(angle)));
            nodePositions.put(node, new Point(x, y));
            index++;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 12));

        for (Map.Entry<Integer, List<Edge>> entry : adjacencyList.entrySet()) {
            int node = entry.getKey();
            Point nodePos = nodePositions.get(node);
            for (Edge edge : entry.getValue()) {
                Point neighborPos = nodePositions.get(edge.destination);
                g.drawLine(nodePos.x, nodePos.y, neighborPos.x, neighborPos.y);

                // Draw weight in the middle of the edge
                int midX = (nodePos.x + neighborPos.x) / 2;
                int midY = (nodePos.y + neighborPos.y) / 2;
                g.setColor(Color.RED);
                g.drawString(String.valueOf(edge.weight), midX, midY);
                g.setColor(Color.BLACK);
            }
        }

        for (Map.Entry<Integer, Point> entry : nodePositions.entrySet()) {
            int node = entry.getKey();
            Point pos = entry.getValue();
            g.setColor(Color.WHITE);
            g.fillOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
            g.setColor(Color.BLACK);
            g.drawOval(pos.x - radius, pos.y - radius, 2 * radius, 2 * radius);
            g.drawString(String.valueOf(node), pos.x - 5, pos.y + 5);
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        graph.put(1, Arrays.asList(new Edge(2, 5), new Edge(3, 2), new Edge(4, 7)));
        graph.put(2, Arrays.asList(new Edge(5, 3), new Edge(6, 8)));
        graph.put(3, Arrays.asList(new Edge(7, 4)));
        graph.put(4, Arrays.asList(new Edge(8, 6), new Edge(9, 1)));
        graph.put(5, Arrays.asList());
        graph.put(6, Arrays.asList(new Edge(10, 9)));
        graph.put(7, Arrays.asList());
        graph.put(8, Arrays.asList());
        graph.put(9, Arrays.asList());
        graph.put(10, Arrays.asList());

        JFrame frame = new JFrame("Weighted Graph Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(new WeightedGraphVisualizer(graph));
        frame.setVisible(true);
    }
}
