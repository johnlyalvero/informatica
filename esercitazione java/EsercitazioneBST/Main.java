package esercitazioni.EsercitazioneBST;

public class Main {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.put(7);
        bst.put(12);
        bst.put(9);
        bst.put(8);
        bst.put(18);
        bst.put(4);
        bst.put(5);
        bst.put(2);
        bst.put(1);
        
        System.out.println("------------------");    
        System.out.println("get: " + bst.get(bst.getRoot(), 0));
        
        
        System.out.println("------------------");    
        System.out.println("print In Order");
        bst.printInOrder(bst.getRoot());
        System.out.println("\nprint Pre Order");
        bst.printPreOrder(bst.getRoot());       
        System.out.println("\nprint Post Order");
        bst.printPostOrder(bst.getRoot());
        
        System.out.println("\n------------------");    
        System.out.println("min: " + bst.getMin(bst.getRoot()));
        System.out.println("max: " + bst.getMax(bst.getRoot()));
    }
}
