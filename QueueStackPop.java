import java.util.LinkedList;
import java.util.Queue;

public class PopFriendlyStack<E> {
    private Queue<E> pushQueue;
    private Queue<E> popQueue;

    // Constructor
    public PopFriendlyStack() {
        pushQueue = new LinkedList<>();
        popQueue = new LinkedList<>();
    }

    // Push Operation: O(N) time complexity
    public void push(E element) {
        // 1. Enqueue the new element into the push queue
        pushQueue.add(element);

        // 2. Dequeue all elements from the pop queue and enqueue them one by one into
        // the push queue
        while (!popQueue.isEmpty()) {
            pushQueue.add(popQueue.remove());
        }

        // 3. Swap the names/references of pushQueue and popQueue
        Queue<E> temp = pushQueue;
        pushQueue = popQueue;
        popQueue = temp;
    }

    // Pop Operation: O(1) time complexity
    public E pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty - cannot pop.");
            return null;
        }

        // Simply dequeue from the pop queue (which always holds the top of the stack at
        // the front)
        return popQueue.remove();
    }

    // Peek Operation: O(1) time complexity
    public E peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty - nothing to peek.");
            return null;
        }

        // Return the front element of the pop queue without removing it
        return popQueue.peek();
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return pushQueue.isEmpty() && popQueue.isEmpty();
    }

    // Return current size of the stack
    public int size() {
        return pushQueue.size() + popQueue.size();
    }

    // Test
    public static void main(String[] args) {
        PopFriendlyStack<Integer> stack = new PopFriendlyStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek()); // Should be 30
        System.out.println("Popped: " + stack.pop()); // Should be 30
        System.out.println("Top element after pop: " + stack.peek()); // Should be 20
        System.out.println("Stack size: " + stack.size()); // Should be 2
    }
}