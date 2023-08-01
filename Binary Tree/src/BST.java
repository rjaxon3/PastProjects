import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 *
 * @author Rhea Jaxon
 * @version 1.0
 * @userid rjaxon3
 * @GTID 903760234
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data inputted is null!");
        }
        for (T t: data) {
            add(t);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Error,data is null");
        }
        root = addHelper(root, data);
    }

    /**
     * Returns node that becomes root depending on the shift of data
     * @param node to check where it should be placed on BST
     * @param data the data to add
     * @return the root of the tree
     */

    private BSTNode<T> addHelper(BSTNode<T> node, T data) {
        if (node == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (data.compareTo(node.getData()) == 0) {
            return node;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(addHelper(node.getLeft(), data));
        } else {
            node.setRight(addHelper(node.getRight(), data));
        }
        return node;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data. You MUST use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data inputted is null!");
        }
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = remover(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Returns node after checking for its children in the BST
     * @param node to check where it should be placed on BST
     * @param data the data to add
     * @param dummy the node to record the successor of the node being removed
     * @throws java.util.NoSuchElementException if the data inputted is not in the BST
     * @return the root of the tree
     */

    private BSTNode<T> remover(BSTNode<T> node, T data, BSTNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("The data inputted is not in the BST!");
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(remover(node.getLeft(), data, dummy));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(remover(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            size--;
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() != null && node.getRight() != null) {
                BSTNode<T> dummy2 = new BSTNode<T>(null);
                node.setRight(successor(node.getRight(), dummy2));
                node.setData(dummy2.getData());
            }
        }
        return node;
    }

    /**
     * Returns dummy node after node removed is found in the BST
     * @param node to check where it should be placed on BST
     * @param dummy the node to record the successor of the node being removed
     * @return the successor of the node removed
     */

    private BSTNode<T> successor(BSTNode<T> node, BSTNode<T> dummy) {
        if (node.getLeft() == null) {
            dummy.setData(node.getData());
            return node.getRight();
        } else {
            node.setLeft(successor(node.getLeft(), dummy));
        }
        return node;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data inputted is null!");
        }
        return getHelper(root, data);
    }

    /**
     * Returns data of the node recursively ran through the BST
     * @param node to check where it should be placed on BST
     * @param data the data to be obtained from BST
     * @throws NoSuchElementException when data entered is null
     * @return the data from the node needed to be obtained
     */

    private T getHelper(BSTNode<T> node, T data) {
        if (node == null) {
            throw new java.util.NoSuchElementException("The data entered is null!");
        } else if (data.compareTo(node.getData()) == 0) {
            return node.getData();
        } else if (data.compareTo(node.getData()) < 0) {
            return getHelper(node.getLeft(), data);
        } else {
            return getHelper(node.getRight(), data);
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data inputted is null!");
        }
        return containHelper(root, data);
    }

    /**
     * Returns boolean statement of whether or not the node was recursively found in the BST
     * @param node to check where it should be placed on BST
     * @param data the data to be compared to
     * @return the whether or not node was found
     */

    private boolean containHelper(BSTNode<T> node, T data) {
        if (node == null) {
            return false;
        } else if (data.compareTo(node.getData()) == 0) {
            return true;
        } else if (data.compareTo(node.getData()) < 0) {
            return containHelper(node.getLeft(), data);
        } else {
            return containHelper(node.getRight(), data);
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<T>();
        preorderHelper(root, list);
        return list;
    }

    /**
     * Changes list to arranging elements in the BST in parent, left, then right node order
     * @param node to check where it should be placed on BST
     * @param list that contains all the BST elements
     */

    private void preorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        list.add(node.getData());
        preorderHelper(node.getLeft(), list);
        preorderHelper(node.getRight(), list);
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<T>();
        inorderHelper(root, list);
        return list;
    }

    /**
     * Changes list to arranging elements in the BST in left, parent, then right node order
     * @param node to check where it should be placed on BST
     * @param list that contains all the BST elements
     */

    private void inorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        inorderHelper(node.getLeft(), list);
        list.add(node.getData());
        inorderHelper(node.getRight(), list);
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<T>();
        postorderHelper(root, list);
        return list;
    }

    /**
     * Changes list to arranging elements in the BST in left, right, then parent node order
     * @param node to check where it should be placed on BST
     * @param list that contains all the BST elements
     */

    private void postorderHelper(BSTNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        postorderHelper(node.getLeft(), list);
        postorderHelper(node.getRight(), list);
        list.add(node.getData());
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        BSTNode<T> node;
        List<T> list = new ArrayList<T>();
        Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            node = queue.remove();
            list.add(node.getData());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
        return list;
    }


    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Returns an int value of the amount of generations found in the BST
     * @param node to check how many generations there are on the BST
     * @return int value of the amount of generations found on the BST
     */

    private int heightHelper(BSTNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return 1 + Math.max(heightHelper(node.getLeft()),
                    heightHelper(node.getRight()));
        }
    }


    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Finds and retrieves the k-largest elements from the BST in sorted order,
     * least to greatest.
     *
     * This must be done recursively.
     *
     * In most cases, this method will not need to traverse the entire tree to
     * function properly, so you should only traverse the branches of the tree
     * necessary to get the data and only do so once. Failure to do so will
     * result in an efficiency penalty.
     *
     * EXAMPLE: Given the BST below composed of Integers:
     *
     *                50
     *              /    \
     *            25      75
     *           /  \
     *          12   37
     *         /  \    \
     *        10  15    40
     *           /
     *          13
     *
     * kLargest(5) should return the list [25, 37, 40, 50, 75].
     * kLargest(3) should return the list [40, 50, 75].
     *
     * Should have a running time of O(log(n) + k) for a balanced tree and a
     * worst case of O(n + k), with n being the number of data in the BST
     *
     * @param k the number of largest elements to return
     * @return sorted list consisting of the k largest elements
     * @throws java.lang.IllegalArgumentException if k < 0 or k > size
     */
    public List<T> kLargest(int k) {
        if (k < 0 || k > size) {
            if (k < 0) {
                throw new IllegalArgumentException("The inputted amount is a negative number!");
            } else if (k > size) {
                throw new IllegalArgumentException("The inputted amount is greater than BST size!");
            }
        }
        List<T> list = new LinkedList<T>();
        largestHelper(root, k, list);
        return list;
    }

    /**
     * Changes list to arranging elements so that the largest k set of elements can be returned
     * @param node to check where it should be placed on BST
     * @param k the number of nodes needed to be returned from the linked list made
     * @param list that contains all the BST elements
     */
    private void largestHelper(BSTNode<T> node, int k, List<T> list) {
        if (node == null) {
            return;
        }
        largestHelper(node.getRight(), k, list);
        if (list.size() < k) {
            list.add(0, node.getData());
            largestHelper(node.getLeft(), k, list);
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
