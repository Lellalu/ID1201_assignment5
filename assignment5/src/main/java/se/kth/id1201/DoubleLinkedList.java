package se.kth.id1201;

public class DoubleLinkedList {

    public static class DoubleNode{ 
        int val; 
        DoubleNode next;
        DoubleNode previous;

        DoubleNode(int val, DoubleNode p, DoubleNode n){
             this.val=val; 
             this.next=n; 
             this.previous=p;
        }
    }

    public class StackEmptyException extends RuntimeException {};

    public DoubleNode first;
    public int length;

    public DoubleLinkedList(int n){
        DoubleNode node = null; 
        DoubleNode pre = null;
        for(int i=0; i<n;i++){ 
            node = new DoubleNode(i, pre, node); 
            pre = node.previous;
        }
        this.first = node;
        this.length = n;
    }

    public void add(int val){
        DoubleNode node = new DoubleNode(val, null, first);
        first = node;
        this.length += 1;
    }

    public int length(){
        return length;
    }
    
    public boolean find(int value){
        DoubleNode node = first;
        do{
            if(node.val == value){
                return true;
            }
            node = node.next;
        }while(node.next != null);
        return false;
    }

    public void remove(int value){
        DoubleNode node = first;
        while(node.next != null){
            if(node.val == value){
                if(node.previous != null){
                    node.previous.next = node.next;
                }else{
                    first = first.next;
                }
                length -= 1;
                break;
            }
            node = node.next;   
        }
    }

    public void append(DoubleLinkedList b){ 
        if(first == null){
            first = b.first;
            length = b.length;
            return;
        }

        DoubleNode node = first; 
        while(node.next != null){
            node = node.next; 
        } 
        node.next = b.first;
        length += b.length;
    }

    public void insert(DoubleNode n){
        if(first == null){
            first = n;
            return;
        }
        n.next = first;
        n.previous = null;
        first.previous = n; 
        first = n;
    }

    public void unlink(DoubleNode n){
        DoubleNode previous_node = n.previous;
        DoubleNode next_node = n.next;
        if(previous_node == null && next_node == null){
            first = null;
            return;
        }
        if(previous_node == null){
            n.next = null;
            next_node.previous = null;
            first = next_node;
            return;
        }
        if(next_node == null){
            previous_node.next = null;
            n.previous = null;
            return;
        }

        previous_node.next = n.next;
        next_node.previous = n.previous;
        n.next = null;
        n.previous = null;
    }

}
