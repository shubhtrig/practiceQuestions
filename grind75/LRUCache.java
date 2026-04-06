/** LRU Cache Implementation 
 * https://leetcode.com/problems/lru-cache/
*/
class LRUCache {

    HashMap<Integer, DLL> map;
    DLL head;
    DLL tail;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = null;
        tail = null;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            DLL ll = map.get(key);
            updateKeyPosition(ll);
            return ll.value;
        }

        return -1;
    }
    
    public void put(int key, int value) {
        if (!map.containsKey(key) && this.capacity == 0) {
            removeElement();
        }

        if (map.containsKey(key)) {
            DLL ll = map.get(key);
            ll.value = value;
            updateKeyPosition(ll);
        } else {
            DLL ll = new DLL(key, value);
            map.put(key, ll);
            insertLL(ll);
            this.capacity--;
        }
    }

    private void printLl(DLL ll) {
        System.out.println("keys");
        while (ll != null) {
            System.out.print(ll.key + " ");
            ll = ll.next;

        }
        System.out.println("");
    }

    private void printLlOpp(DLL ll) {

        System.out.println("keys opposit - ");
        while (ll != null) {
            System.out.print (ll.key + " ");
            ll = ll.prev;
        }
        System.out.println("");
    }

    private void insertLL(DLL ll) {
        if (head == null) {
            head = ll;
            tail = ll;
            return;
        }
        ll.next = head;
        head.prev = ll;
        head = ll;
    }

    private void updateKeyPosition(DLL ll) {
        if (head == ll) {
            return;
        }

        if (tail == ll) {
            tail = ll.prev;
        }

        DLL llNext = ll.next;
        DLL llPrev = ll.prev;
        if (ll.next != null) {
            ll.next.prev = llPrev;
        }

        if (ll.prev != null) {
            ll.prev.next = llNext;
        }

        ll.prev = null;
        ll.next = head;
        head.prev = ll;
        head = ll;
    }

    private void removeElement() {
        this.capacity++;
        map.remove(tail.key);
        if (tail == head) {
            head = null;
        }
        tail = tail.prev;
        if (tail != null)
            tail.next = null;
    }

    class DLL {
        DLL prev;
        DLL next;
        int value;
        int key;

        DLL(int key, int value) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */