/*
I, Yair Lahad (205493018), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Comparator;
import java.util.Iterator;

public class StudentCardBinarySearchTree extends BinarySearchTree<StudentCard> {


    public StudentCardBinarySearchTree(Comparator<StudentCard> myComparator) {
        super(myComparator);
    }

    public void balance() {
        BinaryTreeInOrderIterator<StudentCard> it= new BinaryTreeInOrderIterator<>(this.root);
        DynamicArray<StudentCard> list= new DynamicArray<>();
        while(it.hasNext()){
            StudentCard temp=it.next();
            // adding student cards to list and emptying the tree
            list.add(temp);
            this.remove(temp);
        }
        buildBalancedTree(this,list,0,list.size()-1); //using sub function to balance tree
    }

    private void buildBalancedTree(StudentCardBinarySearchTree tree, List<StudentCard> list, int low, int high) {
        if(low <= high){
            int middle = (low + high+1) / 2;
            tree.insert(list.get(middle));                  // adding values in order and after that balancing it to have max 1 level of depth between values
            buildBalancedTree(tree,list,middle+1,high);
            buildBalancedTree(tree,list,low,middle-1);
        }
    }

}

