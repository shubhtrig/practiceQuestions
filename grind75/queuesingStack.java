class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;
    int peekElement;

    public MyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int x) {
        if (s1.empty())
            peekElement = x;
        s1.push(x);
    }
    
    public int pop() {
        while (s1.empty() == false) {
            s2.push(s1.pop());
        }

        int result = s2.pop();
        if (!s2.empty()) peekElement = s2.peek();

        while(s2.empty() == false) {
            s1.push(s2.pop());
        }

        return result;
    }
    
    public int peek() {
        return peekElement;
    }
    
    public boolean empty() {
        return s1.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 * 
 * https://leetcode.com/problems/implement-queue-using-stacks/
 */