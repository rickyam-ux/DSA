import java.util.Stack;

public class StackQueueDequeueFriendly {
    private Stack<Integer> stack1 = new Stack<>(); // front of queue is on top
    private Stack<Integer> stack2 = new Stack<>(); // helper

    // O(n)
    public void enqueue(int value) {
        while (!stack1.isEmpty()) stack2.push(stack1.pop());
        stack1.push(value);
        while (!stack2.isEmpty()) stack1.push(stack2.pop());
    }

    // O(1)
    public int dequeue() {
        if (stack1.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return stack1.pop();
    }

    public int peek() { return stack1.isEmpty() ? -1 : stack1.peek(); }
    public boolean isEmpty() { return stack1.isEmpty(); }

    public static void main(String[] args) {
        StackQueueDequeueFriendly q = new StackQueueDequeueFriendly();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        q.dequeue();                   
    }
}