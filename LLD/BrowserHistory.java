/*
* You have a browser of one tab where you start on the homepage
* and you can visit another url,
* get back in the history number of steps or move forward in the history number of steps.

Implement the BrowserHistory class:

BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
void visit(string url) Visits url from the current page. It clears up all the forward history.
string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
*
* Example walkthrough:
*
* ["BrowserHistory", "visit", "visit", "visit",   "back", "back",  "forward", "visit", "forward",  "back","back"]
[["leet"],         ["go"],  ["fb"], ["utube"],   [1],    [1],       [1],    ["fb"],     [2],       [2],    [7]]
[null,null,null,null,"facebook.com","google.com","facebook.com",null,"facebook.com","google.com","leetcode.com"]

leet -> go -> fb -> utube  ["BrowserHistory" leet, "visit" go, "visit" fb , "visit" utube, | null,null,null,null
fb <- utube  "back" [1], | fb
go <- fb <- utube  "back" [1], | go
go -> fb           "forward",  [1],| fb
go -> fb -> (fb)   "visit" fb  | null   [V.V.V.IMP]
go -> fb -> (fb)   "forward" [2] | fb
(go) <- fb <- fb    "back" [2] fb | go
*
* More Details/Knowledge about Problem Statement:
* 1. Whenever we are visting any website from a current node/website, then all the forwarded node/website[i.e., future websites already visited](if any) will be discarded
* and node with new visiting website will be appended to the next of current website/node to the existing chain(continuing from the back of current node till the end)
*
* Eg: a -> b -> (c) -> d -> e
* if current node/website = c
* and visit(z)
* then a -> b -> c -> (z)
* and chain d -> e are discarded
*
* 2. Whenever we are going forward/back/visit from current Node,
* we have to make sure in each operation we have to maintain current node also
* */

// Link: https://leetcode.com/problems/design-browser-history/
class BrowserHistory {

    class Node{
        Node prev = null;
        Node next = null;
        String dataVal;

        Node(String dataVal){
            this.dataVal = dataVal;
        }
    }

    Node currentNode;

    public BrowserHistory(String homepage) {
        this.currentNode = new Node(homepage);
    }

    public void visit(String url) {
        // create new node next to curr node
        Node newNode = new Node(url);
        Node current = this.currentNode;
        Node nextNode = current.next;
        if(nextNode != null){ // make existing next node eligible for GC if present
            nextNode.prev = null;
        }
        current.next = newNode;
        newNode.prev = current;
        newNode.next = null;

        this.currentNode = newNode; // make new node as current node
    }

    public String back(int steps) {
        Node backTraversal = this.currentNode;
        while(backTraversal.prev != null
                && steps-- > 0){
            backTraversal = backTraversal.prev;
        }
        this.currentNode = backTraversal;
        return backTraversal.dataVal;
    }

    public String forward(int steps) {
        Node forwardTraversal = this.currentNode;
        while(forwardTraversal.next != null
                && steps-- > 0){
            forwardTraversal = forwardTraversal.next;
        }
        this.currentNode = forwardTraversal;
        return forwardTraversal.dataVal;
    }
}





