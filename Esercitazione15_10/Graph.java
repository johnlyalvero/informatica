import java.util.*;

/*
Si consideri un grafo orientato G non necessariamente connesso (un esempio di
grafo non connesso è quello della figura).

Partendo dal DFS scrivere un algoritmo che, dato in input il grafo G e due vertici
distinti u e v, restituisce true se e solo se esiste un cammino orientato che parte
dal vertice u e conduce al vertice v, false se tale cammino non esiste.
 */

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
    //DFS() ricorsiva
    public String DFS(T v){
        //return DFSricorsiva(v,new LinkedList<>(), "");
        return DFSiterativa(v);
    }

    public String DFSricorsiva(T v, LinkedList<T> visited, String str){
        str += v.toString() + " ";
        System.out.println(v.toString());
        visited.add(v); //segna il vertice visitato
        
        for(T next: adj.get(v))
            if(!visited.contains(next))
                str = DFSricorsiva(next, visited, str);

        return str;
    }

    public String DFSiterativa(T v){
        LinkedList<T> visited = new LinkedList<>();
        Stack<T> stack = new Stack<>();

        stack.push(v);
        System.out.println("Push "+v);
        visited.add(v);

        while(!stack.isEmpty()){

            T current = stack.pop();
            System.out.println("Pop: "+ current);

            for(T e: adj.get(current)){
                if(!visited.contains(e)){
                    visited.add(e);
                    stack.push(e);
                    System.out.println("Push " + e);
                }
            }
        }

        return visited.toString();
    }

    /*
     Partendo dal DFS scrivere un algoritmo che, dato in input il grafo G e due vertici
    distinti u e v, restituisce true se e solo se esiste un cammino orientato che parte
    dal vertice u e conduce al vertice v, false se tale cammino non esiste.
     */
    public boolean existCamminoOrientato(T u, T v){
        return searchV(u,v,new LinkedList<T>());
    }

    public boolean searchV(T u, T v, LinkedList<T> visited){
        visited.add(u); //segna il vertice visitato
        
        for(T next: adj.get(u)){
            if(next == v)
                return true;
            if(!visited.contains(next))
                return searchV(next ,v , visited);
        }

        return false;
    }
}