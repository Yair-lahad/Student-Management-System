/*
I, Yair Lahad (205493018), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilteredStudentCardIterator implements Iterator<StudentCard> {
    private BinaryTreeInOrderIterator<StudentCard> iterator;
    private StudentCard current;
    private Filter<StudentCard> filter;

    public FilteredStudentCardIterator(StudentCardBinarySearchTree StudentCardsTree, Filter<StudentCard> filter) {
        iterator= new BinaryTreeInOrderIterator<>(StudentCardsTree.root);
        this.filter=filter;
        boolean gotFil=false;
        current=null;
        while(iterator.hasNext() & !gotFil) {
            StudentCard temp=iterator.next();
            if(filter.accept(temp)) {          //making the current value to be the first filtered value
                current = temp;
                gotFil=true;
            }
        }
    }

    public boolean hasNext() {
        return current!=null;
    }

    public StudentCard next() {
        if (current==null)
            throw new NoSuchElementException("next student card does not exists");
        StudentCard ans=current;
        StudentCard next;
        boolean filterd=false;
        while (iterator.hasNext() &!filterd){
            next=iterator.next();
            if(filter.accept(next)) {       // only allow filtered values to get to filtered iterator
                current = next;
                filterd=true;
            }
        }
        if (ans.equals(current))
            current=null;
        return ans;
    }
    public void remove(){

        throw new UnsupportedOperationException("Do not change this line");

    }
}
