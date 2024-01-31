package edu.cmu.cs.cs214.rec02;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


/**
 * TODO: 
 * 1. The {@link LinkedIntQueue} has no bugs. We've provided you with some example test cases.
 * Write your own unit tests to test against IntQueue interface with specification testing method 
 * using mQueue = new LinkedIntQueue();
 * 
 * 2. 
 * Comment `mQueue = new LinkedIntQueue();` and uncomment `mQueue = new ArrayIntQueue();`
 * Use your test cases from part 1 to test ArrayIntQueue and find bugs in the {@link ArrayIntQueue} class
 * Write more unit tests to test the implementation of ArrayIntQueue, with structural testing method
 * Aim to achieve 100% line coverage for ArrayIntQueue
 *
 * @author Alex Lockwood, George Guo, Terry Li
 */
public class IntQueueTest {

    private IntQueue mQueue;
    private List<Integer> testList;
    private List<Integer> testList2;

    /**
     * Called before each test.
     */
    @Before
    public void setUp() {
        // comment/uncomment these lines to test each class
        // mQueue = new LinkedIntQueue();
        mQueue = new ArrayIntQueue();

        
        testList = new ArrayList<>(List.of(-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        testList2= new ArrayList<>(List.of(-1000, -100, -10,0,10,100));
    }

    @Test
    public void testIsEmpty() {
        // This is an example unit test
        assertTrue(mQueue.isEmpty());
    }

    @Test
    public void testNotEmpty() {
        // TODO: write your own unit test
        mQueue.enqueue((testList.get(0)));
        assertFalse(mQueue.isEmpty());
    }

    @Test
    public void testPeekEmptyQueue() {
        // TODO: write your own unit test
        assertNull(mQueue.peek());
    }

    @Test
    public void testPeekNoEmptyQueue() {
        // TODO: write your own unit test
        mQueue.enqueue((testList.get(0)));
        assertEquals(testList.get(0),mQueue.peek());
    }

    @Test
    public void testEnqueue() {
        // This is an example unit test
        for (int i = 0; i < testList.size(); i++) {
            mQueue.enqueue(testList.get(i));
            assertEquals(testList.get(0), mQueue.peek());
            assertEquals(i + 1, mQueue.size());
        }
    }

    @Test
    public void testDequeueEmpty() {
        // TODO: write your own unit test
        assertNull(mQueue.dequeue());
    }

    @Test
    public void testDequeueNotEmpty() {
        // TODO: write your own unit test
        for (int i=0;i<testList.size();i++){
            mQueue.enqueue(testList.get(i));
        }
        for (int i=0;i<testList.size();i++){
            assertEquals(mQueue.dequeue(),testList.get(i));
        }
    }
    
    @Test
    public void testClearEmpty(){
        mQueue.clear();
        assertEquals(mQueue.size(),0);
    }

    @Test
    public void testClearNotEmpty(){
        for (int i=0;i<testList.size();i++){
            mQueue.enqueue(testList.get(i));
        }
        mQueue.clear();
        assertEquals(mQueue.size(),0);
    }

    @Test
    public void testContent() throws IOException {
        // This is an example unit test
        InputStream in = new FileInputStream("src/test/resources/data.txt");
        try (Scanner scanner = new Scanner(in)) {
            scanner.useDelimiter("\\s*fish\\s*");

            List<Integer> correctResult = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                correctResult.add(input);
                System.out.println("enqueue: " + input);
                mQueue.enqueue(input);
            }

            for (Integer result : correctResult) {
                assertEquals(mQueue.dequeue(), result);
            }
        }
    }

    @Test
    public void testEnsureCapacity(){
        for (int i=0;i<testList2.size();i++){
            mQueue.enqueue(testList2.get(i));
        }
        for (int i=0;i<testList2.size();i++){
            mQueue.dequeue();
        }
        for (int i=0;i<testList.size();i++){
            mQueue.enqueue(testList.get(i));
            assertEquals(mQueue.peek(),testList.get(0));
        }
    }


}
