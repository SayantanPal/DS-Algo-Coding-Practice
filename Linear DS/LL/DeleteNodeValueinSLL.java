import model.SLLNode;

// GIVEN: Note that by deleting the node, we do not mean removing it from memory.
//We mean:

//        The value of the given node should not exist in the linked list.
//        The number of nodes in the linked list should decrease by one.
//        All the values before node should be in the same order.
//        All the values after node should be in the same order.

// Link: https://leetcode.com/problems/delete-node-in-a-linked-list/description/
public class DeleteNodeValueinSLL {

    public void deleteNode(SLLNode node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }
}
