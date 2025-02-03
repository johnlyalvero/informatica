public class Main {
    public static void main(String[] args) {
        Graph<Integer> grafo = new Graph<>();
        
        grafo.addVertex(0);
        grafo.addVertex(1);
        grafo.addVertex(2);
        grafo.addVertex(3);
        grafo.addVertex(4);

        grafo.addEdge(0, 1, true);
        grafo.addEdge(0, 4, true);
        grafo.addEdge(1, 3, true);
        grafo.addEdge(1, 4, true);
        grafo.addEdge(2, 1, true);
        grafo.addEdge(2, 3, true);
        grafo.addEdge(3, 4, true);

        System.out.println(grafo.hasVertex(1));
        System.out.println(grafo.hasVertex(9));
        System.out.println(grafo.hasEdge(0,1));
        System.out.println(grafo.hasEdge(0,2));
        System.out.println(grafo.toString());
    }
}
