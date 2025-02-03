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
    public void addEdge(T start, T end,boolean bidirectional){
        if (!adj.containsKey(start))  addVertex(start);
        if (!adj.containsKey(end))  addVertex(end);

        adj.get(start).add(end);
        if(bidirectional)
            adj.get(end).add(start);
    }

    public int getNumberVertex(){
        return adj.keySet().size();
    }

    public int getNumberEdges(boolean bidirectional){
        int archi = 0;
        for(T k : adj.keySet()){
            archi += adj.get(k).size();
        }
        if(bidirectional)
            archi = archi/2;

        return archi;
    }
    public boolean hasVertex(T v){
        return adj.containsKey(v);
    }
    public boolean hasEdge(T start, T end){
        return adj.get(start).contains(end);
    }

    @Override
    public String toString(){
        String s="";

        for (T v : adj.keySet()) {
            s+=(v.toString() + ": ");
            for (T w : adj.get(v)) {
                s+= w.toString() + " ";
            }
            s+="\n";
        }

        return s;
    }
    //ritorna il grado di un vertice
    public int degree(T v){
        if (adj.containsKey(v)){
            return adj.get(v).size();
        }
        return -1;
    }

    //DFT iterativo
    public void DFTiterative(int v){
        Stack<Integer> raggiungibili = new Stack<>();
        boolean[] visited = new boolean[adj.size()];
        raggiungibili.push(v);
        while(!raggiungibili.isEmpty()){
            int current=raggiungibili.pop();
            if(!visited[current]){
                visited[current]=true;
                System.out.print(current+" ");
            }
            LinkedList<Integer> adiacenti=(LinkedList<Integer>) adj.get(current);
            for(int n: adiacenti){
                if(!visited[n])
                    raggiungibili.push(n);
            }
        }

    }


    public boolean hashPath(int start, int end){
        Stack<Integer> raggiungibili = new Stack<>();
        boolean[] visited = new boolean[adj.size()];
        raggiungibili.push(start);
        while(!raggiungibili.isEmpty()){
            int current=raggiungibili.pop();
            if(!visited[current]){
                visited[current]=true;
                //System.out.print(current+" "); stampare il nodo visitato in questo caso non serve più
            }
            LinkedList<Integer> adiacenti=(LinkedList<Integer>) adj.get(current);
            for(int n: adiacenti){
                if(!visited[n])
                    raggiungibili.push(n);
            }
        }
        if(visited[end]==false)    
            return false;
        return true;
    }




    public void DFTrecursive(int v, boolean[] visited){
        System.out.print(v+" ");
        visited[v]=true;
        LinkedList<Integer> adiacenti=(LinkedList<Integer>) adj.get(v);
        for (int n: adiacenti){
            if(!visited[n])
                DFTrecursive(n,visited);
        }

    }



    public void BFS(int v){
        LinkedList<Integer> coda = new LinkedList<>(); //coda creata tramite una lista
        boolean[] visited = new boolean[adj.size()];
        visited[v]=true;
        coda.add(v);
        while(!coda.isEmpty()){
            int current=coda.removeFirst();
            System.out.print(current+" ");
            LinkedList<Integer> adiacenti=(LinkedList<Integer>) adj.get(current);
            for(int n: adiacenti){
                if(!visited[n])
                    coda.add(n);
                    visited[n]=true;
            }
        }

    }





}
