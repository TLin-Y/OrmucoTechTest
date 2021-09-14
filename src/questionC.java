/*
Tianlin Yang, 11/13/2021
 */

/*
Question C
At Ormuco, we want to optimize every bits of software we write. Your goal is to write a new library that can be
integrated to the Ormuco stack. Dealing with network issues everyday, latency is our biggest problem.
Thus, your challenge is to write a new Geo Distributed LRU (Least Recently Used) cache with time expiration.
This library will be used extensively by many of our services so it needs to meet the following criteria:
    1 - Simplicity. Integration needs to be dead simple.
    2 - Resilient to network failures or crashes.
    3 - Near real time replication of data across Geolocation. Writes need to be in real time.
    4 - Data consistency across regions
    5 - Locality of reference, data should almost always be available from the closest region
    6 - Flexible Schema
    7 - Cache can expire
 */

import java.util.HashMap;

class Node{

    // Initialize the Node
    Node(String key,String value){
        this.key=key;
        this.value=value;
    }
    public Node pre;
    public Node next;
    public String key;
    public String value;
}
class simpleLRUCache {

    private Node head;
    private Node end;
    //Max cache limitation
    private int limit;

    private HashMap<String, Node> hashMap;

    public simpleLRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<String, Node>();
    }

    /**
     * Delete node
     * @param node node deleted
     */
    private String removeNode(Node node) {
        if (node == end) {
            //remove tail
            end = end.pre;
        } else if (node == head) {
            //remove head
            head = head.next;
        } else {
            //remove middle
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * add the node on the tail
     * @param node node to be insert
     * */
    private void addNode(Node node){
        if(end!=null){
            end.next=node;
            node.pre=end;
            node.next=null;
        }
        end=node;
        if(head==null){
            head=node;
        }
    }

    /*
     * refreshing
     * @param node node to be travel
     * */
    private void refreshNode(Node node){
        //if node = end, than that's closest one
        if(node==end){
            return;
        }
        //remove the node
        removeNode(node);
        //re-insert the node
        addNode((node));
    }

    public void remove(String key){
        Node node=hashMap.get(key);
        removeNode(node);
        hashMap.remove(key);
    }
    public void put(String key,String value){
        Node node=hashMap.get(key);
        if(node==null){
            //if key not here, add key-value
            if(hashMap.size()>=limit){
                String oldKey=removeNode(head);
                hashMap.remove(oldKey);
            }
            node=new Node(key,value);
            addNode(node);
            hashMap.put(key,node);
        }
        else{
            //if key here, refresh
            node.value=value;
            refreshNode(node);
        }
    }
    public String get(String key){
        Node node=hashMap.get(key);
        if(node==null){
            return null;
        }
        refreshNode(node);
        return node.value;
    }
}
class questionC {
    public static void main(String[] args){
        //Set the cache size 5, time to live 4
        simpleLRUCache simpleLruCache =new simpleLRUCache(5);
        //First add 001-005
        simpleLruCache.put("001","montreal");
        simpleLruCache.put("002","toronto");
        simpleLruCache.put("003","beijing");
        simpleLruCache.put("004","tokyo");
        simpleLruCache.put("005","ottawa");
        //After that, get the 002, time updated
        //timeline: 001->003->004->005->002   now 002 is newest cache position
        simpleLruCache.get("002");
        //now update the tokyo information
        //timeline: 001->003->005->002->004
        simpleLruCache.put("004","tokyoUpdate");
        //Now add final node in cache:
        //timeline: 001->003->005->002->004->006
        simpleLruCache.put("006","newYork");

        System.out.println(simpleLruCache.get("001"));//Oldest, should be dropped by LRU Cache, return null
        System.out.println(simpleLruCache.get("006"));//Newest, should return 'newYork'
    }
}