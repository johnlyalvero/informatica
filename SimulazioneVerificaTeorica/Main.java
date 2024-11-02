package esercitazioni.SimulazioneVerificaTeorica;

public class Main {

    //perfectBalance.java
    public static void perfect(BST bst, int[] array, int low, int high) {
	    //completare il metodo
        bst.put(array[(low+high)/2]);
        perfect(bst,array,low,(low+high)/2 - 1);
        perfect(bst,array,array.length - (low+high)/2 - 1 ,high);
    }

    public static void main(String[] args) {
        int[] vett = {1,2,3,4,5,6,7};
        BST bst = new BST();
        perfect(bst, vett, 0, vett.length - 1);
    }
}
