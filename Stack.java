/**
 * Stack.java
 *
 * A specification of the Stack ADT -- UVic CSC 115, Spring 2017
 *
 */
public interface Stack<T>
{
    /*
     * Purpose:
     *   return the number of items currently pushed onto the stack.
     *
     * Returns:
     *   the number of items in the stack
     *
     * Examples:
     *
     * If s is {x, y, z}:
     *    s.size() returns 3.
     * If s is {}:
     *    s.size() returns 0.
     */
	int size();


    /*
     * Purpose:
     *   returns the boolean state of the stack (empty or not empty)
     *
     * Returns:
     *   false if stack is empty, true otherwise
     *
     * Examples:
     *
     * If s is {x, y, z}:
     *    s.isEmpty() returns false.
     * If s is {}:
     *    s.isEmpty() returns true.
     */
	boolean isEmpty();


    /*
     * Purpose:
     *   places the values passed as a parameter onto the top of
     *   the stack.
     *
     * Returns:
     *   nothing.
     *
     * Examples:
     *
     * If s is {x, y, z}:
     *   then after s.push(m), s is {a, x, y, z} (i.e., top of stack
     *   is the left-most value in the sequence, bottom of stack is
     *   the right-most value in the sequence}.
     * If s is {}:
     *   then after s.push(m), s is {m}.
     */
	void push (T element);


    /*
     * Purpose:
     *   removes the value at the top of the stack (if it exists),
     *   and returns it to the caller. If the stack was empty before
     *   the call, then an exception is thrown by the method.
     *
     * Returns:
     *   The value of type T at the top of the stack (if stack is
     *   non-empty)
     *
     * Examples:
     *
     * If s is {x, y, z}:
     *    then after s.pop(), s is {y, z} and x is returned to the
     *    caller.
     * If s is {}:
     *    then a StackEmptyException() is thrown.
     */
	T pop() throws StackEmptyException;



    /*
     * Purpose:
     *   returns the value at the top of the stack (if it exists)
     *   but does not modify the contents of the stack. If the stack
     *   was empty before the call, then an exception is thrown
     *   by the method.
     *
     * Returns:
     *   The value of type T at the top of the stack (if stack is
     *   non-empty)
     *
     * Examples:
     *
     * If s is {x, y, z}:
     *    then after s.peek(), s is {x, y, z} and x is returned to the
     *    caller.
     * If s is {}:
     *    then a StackEmptyException() is thrown.
     */
	T peek() throws StackEmptyException;


    /*
     * Purpose:
     *   clear the stack of all its content.
     *
     * Returns:
     *   nothing.
     *
     * Examples:
     *
     * If s is {x, y, z}:
     *   then after s.makeEmpty(), s is {}.
     * If s is {}:
     *   then after s.makeEmpty(), s is {}.
     */
	void makeEmpty();

	/*
	 * Purpose:
	 *	return a string representation of the stack
	 *
	 * Returns:
	 *  A string representation of the stack.
	 *
	 * Examples:
	 *
	 * If is is {x,y,z}, s.toString() returns "x y z"
	 *
	 * If s is {}, s.toString() returns ""
	 *
	 * NOTE: This method is not directly tested in the assignment
	 *		 but is useful in Part II
	 */
	 String toString();
}
