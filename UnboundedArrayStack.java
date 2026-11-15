public class DynamicUnboundedArrayStack {
    private int[] arr;
    private int top;

    // Constructor with an initial default capacity
    public DynamicUnboundedArrayStack() {
        arr = new int[4]; // Starting with a small initial size for demonstration
        top = -1;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Return current number of elements
    public int size() {
        return top + 1;
    }

    // Helper method to resize the array
    private void resize(int newCapacity) {
        int[] newArr = new int[newCapacity];
        for (int i = 0; i <= top; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
        System.out.println("[INFO] Array resized to capacity: " + newCapacity);
    }

    // Push Operation: Expands array if full
    public void push(int x) {
        // If the array is full, double its capacity
        if (top == arr.length - 1) {
            resize(arr.length * 2);
        }
        arr[++top] = x;
    }

    // Pop Operation: Shrinks array if occupancy drops below half
    public int pop() {
        if (isEmpty()) {
            System.out.println("STACK EMPTY - cannot pop");
            return -1;
        }

        int poppedElement = arr[top--];

        // Shrinking condition:
        // If current size is less than half of array length AND capacity is greater
        // than default (e.g., 4)
        int currentSize = top + 1;
        if (currentSize > 0 && currentSize < arr.length / 2 && arr.length > 4) {
            resize(arr.length / 2);
        }

        return poppedElement;
    }

    // Peek Operation
    public int peek() {
        if (isEmpty()) {
            System.out.println("STACK EMPTY - nothing to peek");
            return -1;
        }
        return arr[top];
    }

    // --- Main method to test ---
    public static void main(String[] args) {
        DynamicUnboundedArrayStack stack = new DynamicUnboundedArrayStack();

        System.out.println("--- PUSH OPERATIONS (Triggering Expansion) ---");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50); // Triggers expansion (capacity 4 -> 8)

        System.out.println("Top element: " + stack.peek()); // 50
        System.out.println("Current Stack Size: " + stack.size()); // 5

        System.out.println("\n--- POP OPERATIONS (Triggering Shrinking) ---");
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Popped: " + stack.pop()); // Triggers shrinking back down

        System.out.println("Current Stack Size: " + stack.size()); // 2
    }
}