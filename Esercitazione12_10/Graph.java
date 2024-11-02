import java.util.*;

public class Graph<T>{
    private Map<T,LinkedList<T>> adj;

    public Graph(){
        adj=new HashMap<>();
    }

    //aggiunge un nodo
    public void addVertex(T v){
        adj.put(v,new LinkedList<T>());
    }
    //aggiunge un arco tra due vertici
    public void addEdge(T start, T end, boolean bidirectional){
        if (!adj.containsKey(start))  addVertex(start);
        if (!adj.containsKey(end))  addVertex(end);
       
        adj.get(start).add(end);
        if(bidirectional)  
            adj.get(end).add(start);
    } 
    //restituisce il numero di nodi del grafo
    public int getNumberVertex(){
        return adj.keySet().size();
    }
    //restituisce il numero di archi del grafo
    public int getNumberEdges(boolean bidirectional){
        int archi = 0;
        for(T k : adj.keySet()){
            archi += adj.get(k).size();
        }
        if(bidirectional)
            archi = archi/2;
           
        return archi;
    }
    //hasVertex(T v): restituisce true se nel grafo è presente il vertice v, false in
    //caso contrario.
    public boolean hasVertex(T v){
        return adj.containsKey(v);
    }
    //hasEdge(T start, T end): restituisce true se nel grafo è presente l’arco che
    //collega start con end, false in caso contrario.
    public boolean hasEdge(T start, T end){
        return adj.get(start).contains(end);
    }
    //degree (T v): restituisce il grado del vertice v.
    public int degree(T v){
        return adj.get(v).size();
    }
    //toString(): stampa la lista di adiacenza del grafo.
    @Override
    public String toString(){
        String str = "";
        for(T v: adj.keySet())
            str += v.toString() + " = " + adj.get(v).toString() + "\n";
        return str;
    }
}