public class StackRefBased<T> implements Stack<T> {
    private int count;
    private StackNode<T> top;

    public StackRefBased() {
      count = 0;
      top = null;
    }

    public int size() {
        return count;
    }


    public boolean isEmpty() {
        return count == 0 && top == null;
    }


    public void push(T data) {
        StackNode<T> p = new StackNode<T>(data);
        p.data = data;

        if (top == null)
          top = p;
        else {
          p.next = top;
          top = p;
        }
        count++;
    }


    public T pop() throws StackEmptyException {
        if (count == 0)
          throw new StackEmptyException();
        T removedVal = top.data;//NullPointerException
        top = top.next;
        count--;
        return removedVal;
    }


    public T peek() throws StackEmptyException {
      if (count == 0) {
        throw new StackEmptyException();
      }
        return top.data;
    }


    public void makeEmpty() {
      count = 0;
      top = null;
    }


    public String toString() {
      String result = "";
      StackNode<T> temp = top;
      while (temp != null || temp.next != null) {
        result += temp.data+" ";
        temp = temp.next;
      }
      result += temp.data;
      return result;
    }
}
