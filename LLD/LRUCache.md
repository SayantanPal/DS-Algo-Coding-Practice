#### LRU Cache
-> When we use HashMap with DLL for maintaining tracking, then it is most optimal
-> Why tracking using DLL is the most optimum??
-> HashMap: 
-> DLL: With the power of prev & next node, 
        we can directly hit a node in between series of nodes in a DLL 
        and we can delete that particular node. This takes O(1) time complexity.

-> Why tracking using Queue(<-FS--RE<-) is not enough?
   Because deleting a element in between queue is not pre-defined from lib 
   and needs to be explicitly programmed which takes O(n) time complexity.
   Here, for deleting a element from in between queue, 
    a. we put all elements from Queue till the 'to be deleted' node is found to some temp Queue,
    b. then we remove 'to be deleted' node from actual queue
    c. then we copy those initial elements back to original queue from temp queue
    d. then again we rotate those leftover elements from front to back of queue to restore the sequence
   

-> Why SLL is not enough or replacement of DLL here?
   Because SLL can delete the very next node for a given 'to be deleted' node in O(1) time, not that exact given node
   since it needs full traversal from the beginning to get the very before node for that given 'to be deleted' node
   as it does not store/hold prev pointer for any node to traverse back.