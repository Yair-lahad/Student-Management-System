import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryTreeInOrderIterator<T> implements Iterator<T>{
    private Stack<BinaryNode<T>> stack;

    public BinaryTreeInOrderIterator(BinaryNode<T> root) {
        stack = new StackAsDynamicArray<BinaryNode<T>>();
        prepareNext(root);
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public T next() {
        if(!hasNext())
            throw new NoSuchElementException();
        BinaryNode<T> node = (stack.pop());
        if (node.right != null)
            prepareNext(node.right);
        return node.data;
    }

    private void prepareNext(BinaryNode<T> node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
    public void remove(){

        throw new UnsupportedOperationException("Do not change this line");

    }
}