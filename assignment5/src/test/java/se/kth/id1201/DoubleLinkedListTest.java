package se.kth.id1201;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import se.kth.id1201.DoubleLinkedList;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DoubleLinkedListTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void addSuccessTest()
    {
        DoubleLinkedList a = new DoubleLinkedList(3);
        a.add(10);
        assertEquals(10,a.first.val);
    }

    @Test
    public void findSuccessTest(){
        DoubleLinkedList a = new DoubleLinkedList(3);
        assertTrue(a.find(1));
    }

    @Test
    public void findFailTest(){
        DoubleLinkedList a = new DoubleLinkedList(3);
        assertFalse(a.find(100));
    }

    @Test
    public void removeSuccessTest(){
        DoubleLinkedList a = new DoubleLinkedList(10);
        a.add(100);
        a.remove(100);
        assertFalse(a.find(100));
    }

    @Test
    public void appendSuccessTest(){
        DoubleLinkedList a = new DoubleLinkedList(10);
        DoubleLinkedList b = new DoubleLinkedList(1);
        a.append(b);
        assertEquals(11, a.length());
    }

    @Test
    public void insertSuccessTest(){
        DoubleLinkedList.DoubleNode n = new DoubleLinkedList.DoubleNode(100,null,null);
        DoubleLinkedList a = new DoubleLinkedList(10);
        a.insert(n);
        assertEquals(100, a.first.val);
    }

    @Test
    public void unlinkSuccessTest(){
        DoubleLinkedList a = new DoubleLinkedList(10);
        DoubleLinkedList.DoubleNode n = a.first.next.next.next;
        int value = n.val;
        a.unlink(n);
        assertFalse(a.find(value));
    }

    @Test
    public void insertIUnlinkSuccessTest(){
        DoubleLinkedList a = new DoubleLinkedList(2);
        DoubleLinkedList.DoubleNode first_node = a.first;
        DoubleLinkedList.DoubleNode second_node = a.first.next;
        int first_value = first_node.val;
        int second_value = second_node.val;
        a.unlink(second_node);
        a.insert(second_node);
        assertTrue(a.find(second_value));

        a.unlink(first_node);
        a.insert(first_node);
        assertTrue(a.find(first_value));
    }

}
