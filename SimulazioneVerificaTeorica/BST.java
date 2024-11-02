package esercitazioni.SimulazioneVerificaTeorica;
/*
Continuare il seguente codice (in cui è già stato implementato il metodo put che permette l'inserimento di un nodo nell'albero) con la scrittura 
dei metodi 
get (int value) e printInOrder (TreeNode n). 
Il metodo get, dato un valore, applica l'algoritmo della ricerca binaria e cerca nell'albero 
il nodo contenente quel valore restituendolo, se presente. Altrimenti restituisce null.
Provare a fornire sia un'implementazione iterativa sia una ricorsiva dell'algoritmo.
Per il metodo printInOrder seguire le indicazioni viste a lezione e scrivere quindi soltanto la versione ricorsiva.

Suggerimenti:
Per la versione ricorsiva la firma diventa get (TreeNode n, int value).
 */

public class BST {

    private class TreeNode {
        private int dato;
        private TreeNode left, right;

        public TreeNode(int item) {
            dato = item;
        }
    }
    private TreeNode root;

    public BST(){
        root=null;
    }
    public TreeNode getRoot() {  //se non uso i metodi "wrapper" ho bisogno di vedere la radice nel main
        return root;
    }

    //versione iterativa di put
    public void put(int item) {
        if (root == null) {
            root = new TreeNode(item);
            return;
        }
        TreeNode tmp = root;
        while (true) {
            if (item < tmp.dato) {
                if (tmp.left == null) {
                    tmp.left = new TreeNode(item);
                    return;
                } else
                    tmp = tmp.left;
            } else if (item > tmp.dato) {
                if (tmp.right == null) {
                    tmp.right = new TreeNode(item);
                    return;
                } else
                    tmp = tmp.right;
            } else { // item esiste già nell'albero, lo sovrascrive
                tmp.dato = item;
                return;
            }
        }
    }

    //versione ricorsiva di put
    public TreeNode put(TreeNode n, int item) {
        if (n == null)           //base case
            n = new TreeNode(item);
        if (item < n.dato)
            n.left = put(n.left, item);
        else if (item > n.dato)
            n.right = put(n.right, item);
        else // item esiste già nell'albero, lo sovrascrive
            n.dato = item;

        return n;
    }

    //versione iterativa di get
    
    public TreeNode get(int value){
        if(root == null) {
            return null;
        }
        TreeNode tmp = root;
        while(tmp != null){
            if(value == tmp.dato){
                return tmp;
            } else if(value > tmp.dato){
                tmp = tmp.right;
            } else{
                tmp = tmp.left;
            }
        }
        return null;
    }
    
    //versione ricorsica di get
    public TreeNode get(TreeNode n, int value){
        if(n == null)
            return null;
        if(n.dato == value)
            return n;
        else if(value > n.dato)
            return get(n.right,value);
        else
            return get(n.left,value);
    }
    
    
    //versione ricorsiva getMin
    public TreeNode getMin(TreeNode n){
        if(n == null) //controllo se il root è vuoto
            return null;
        
        if(n.left == null) //controlla se sono all'ultimo nodo a sinistra
            return n;
        
        return getMin(n.left);
    }
    
    //versione ricorsiva getMax
    public TreeNode getMax(TreeNode n){
        if(n == null) //controllo se il root è vuoto
            return null;
        
        if(n.right == null) //controlla se sono all'ultimo nodo a sinistra
            return n;
        
        return getMax(n.right);
    }
    
    //printInOrder
    public void printInOrder(TreeNode n){
        if(n.left != null)
            printInOrder(n.left);
        System.out.println(n.dato);
        if(n.right != null)
            printInOrder(n.right);
    }
    
    //printPreOrder
    public void printPreOrder(TreeNode n){
        //Stampa
        System.out.print(n.dato + "-");
        //Visita sx
        if(n.left != null)
            printInOrder(n.left);
        //Visita dx
        if(n.right != null)
            printInOrder(n.right);
    }
    
    //printPostOrder
    public void printPostOrder(TreeNode n){
        //Visita sx
        if(n.left != null)
            printInOrder(n.left);
        //Visita dx
        if(n.right != null)
            printInOrder(n.right);
        //Stampa
        System.out.print(n.dato + "-");
    }
}