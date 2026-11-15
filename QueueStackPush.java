import java.util.LinkedList;
import java.util.Queue;

public class PushFriendlyStack<E> {
    private Queue<E> pushQueue;
    private Queue<E> popQueue;

    // Constructor
    public PushFriendlyStack() {
        pushQueue = new LinkedList<>();
        popQueue = new LinkedList<>();
    }

    // Push Operation: O(1) time complexity
    public void push(E element) {
        // Simply enqueue into the push queue
        pushQueue.add(element);
    }

    // Pop Operation: O(N) time complexity
    public E pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty - cannot pop.");
            return null;
        }

        // 1. Transfer all elements from pushQueue to popQueue, except the last one
        while (pushQueue.size() > 1) {
            popQueue.add(pushQueue.remove());
        }

        // 2. The remaining element in pushQueue is the top of the stack
        E poppedElement = pushQueue.remove();

        // 3. Swap the names/references of pushQueue and popQueue
        Queue<E> temp = pushQueue;
        pushQueue = popQueue;
        popQueue = temp;

        return poppedElement;
    }

    // Peek Operation: O(N) time complexity
    public E peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty - nothing to peek.");
            return null;
        }

        // Transfer all elements except the last one to popQueue
        while (pushQueue.size() > 1) {
            popQueue.add(pushQueue.remove());
        }

        // Get the top element
        E topElement = pushQueue.peek();

        // Transfer the last element as well to popQueue so state remains consistent
        popQueue.add(pushQueue.remove());

        // Swap the queues
        Queue<E> temp = pushQueue;
        pushQueue = popQueue;
        popQueue = temp;

        return topElement;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return pushQueue.isEmpty() && popQueue.isEmpty();
    }

    // Return current size of the stack
    public int size() {
        return pushQueue.size() + popQueue.size();
    }

    // --- Main method to test ---
    public static void main(String[] args) {
        PushFriendlyStack<Integer> stack = new PushFriendlyStack<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top element: " + stack.peek()); // Should be 30
        System.out.println("Popped: " + stack.pop()); // Should be 30
        System.out.println("Top element after pop: " + stack.peek()); // Should be 20
        System.out.println("Stack size: " + stack.size()); // Should be 2
    }
}