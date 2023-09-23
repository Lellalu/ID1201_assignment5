package se.kth.id1201;

public class LinkedList {

    public class Node{ 
        int val; 
        Node next;

        Node(int val, Node n){
             this.val=val; 
             this.next=n; 
        }
    }

    public class StackEmptyException extends RuntimeException {};

    public Node first;
    public int length;

    public LinkedList(int n){
        Node node = null; 
        for(int i=0; i<n;i++){ 
            node = new Node(i, node); 
        } 
        this.first = node;
        this.length = n;
    }

    public void add(int val){
        Node node = new Node(val, first);
        first = node;
        this.length += 1;
    }

    public int length(){
        return length;
    }
    
    public boolean find(int value){
        Node node = first;
        do{
            if(node.val == value){
                return true;
            }
            node = node.next;
        }while(node.next != null);
        return false;
    }

    public void remove(int value){
        Node node = first;
        Node previous = null;
        while(node.next != null){
            if(node.val == value){
                if(previous != null){
                    previous.next = node.next;
                }else{
                    first = first.next;
                }
                length -= 1;
                break;
            }
            previous = node;
            node = node.next;   
        }
    }

    public void append(LinkedList b){ 
        if(first == null){
            first = b.first;
            length = b.length;
            return;
        }

        Node node = first; 
        while(node.next != null){
            node = node.next; 
        } 
        node.next = b.first;
        length += b.length;
    }

    public void insert(Node n){
        n.next = first;
        first = n;
    }

    public void unlink(Node n){
        Node node = first;
        Node previous = null;
        while(node.next != null){
            if(node == n){
                if(previous != null){
                    previous.next = node.next;
                }else{
                    first = first.next;
                }
                length -= 1;
                break;
            }
            previous = node;
            node = node.next;   
        }
    }

    public void pushFirst(int i){
        Node newNode = new Node(i, first);
        first = newNode;
        length += 1;
    }

    public int popFirst(){
        if(first == null){
            throw new StackEmptyException();
        }
        int value = first.val;
        first = first.next;
        length -= 1;
        return value;
    }

    public void push(int i){
        Node newNode = new Node(i, null);
        Node node = first;
        if(node == null){
            first = newNode;
            return;
        }
        while(node.next != null){
            node = node.next;
        }
        node.next = newNode;
    }

    public int pop(){
        Node pre = null;
        Node node = first;
        if(first == null){
            throw new StackEmptyException();
        }
        while(node.next != null){
            pre = node;
            node = node.next;
        }
        int value = node.val;
        if(pre != null){
            pre.next = null;
        }      
        return value;
    }
}

