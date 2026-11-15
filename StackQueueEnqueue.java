import java.util.Stack;

public class EnqueueFriendlyStackQueue<E> {
    private Stack<E> mainStack;
    private Stack<E> auxStack;

    // Constructor
    public EnqueueFriendlyStackQueue() {
        mainStack = new Stack<>();
        auxStack = new Stack<>();
    }

    // Enqueue Operation: O(1) time complexity
    public void enqueue(E element) {
        // Simply push to the main stack
        mainStack.push(element);
    }

    // Dequeue Operation: O(N) time complexity
    public E dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty - cannot dequeue.");
            return null;
        }

        // 1. Transfer all elements from mainStack to auxStack except the last element
        while (mainStack.size() > 1) {
            auxStack.push(mainStack.pop());
        }

        // 2. Pop the last element (which is the front of the queue)
        E dequeuedElement = mainStack.pop();

        // 3. Push all elements back from auxStack to mainStack to restore the state
        while (!auxStack.isEmpty()) {
            mainStack.push(auxStack.pop());
        }

        return dequeuedElement;
    }

    // Peek Operation: O(N) time complexity
    public E peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty - nothing to peek.");
            return null;
        }

        // 1. Transfer all elements except the last one to auxStack
        while (mainStack.size() > 1) {
            auxStack.push(mainStack.pop());
        }

        // 2. Get the front element
        E frontElement = mainStack.peek();

        // 3. Push the last element back to auxStack temporarily, then restore
        // everything
        auxStack.push(mainStack.pop());

        while (!auxStack.isEmpty()) {
            mainStack.push(auxStack.pop());
        }

        return frontElement;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return mainStack.isEmpty();
    }

    // Return current size of the queue
    public int size() {
        return mainStack.size();
    }

    // Test
    public static void main(String[] args) {
        EnqueueFriendlyStackQueue<Integer> queue = new EnqueueFriendlyStackQueue<>();

        queue.enqueue(3);
        queue.enqueue(5);
        queue.enqueue(7);

        System.out.println("Front element (Peek): " + queue.peek()); // Should be 3
        System.out.println("Dequeued: " + queue.dequeue()); // Should be 3
        System.out.println("Front element after dequeue: " + queue.peek()); // Should be 5
        System.out.println("Queue size: " + queue.size()); // Should be 2
    }
}