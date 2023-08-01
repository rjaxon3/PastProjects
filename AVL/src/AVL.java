import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
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
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The Collection entered is null!");
        }
        for (T t: data) {
            add(t);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data entered is null!");
        }
        root = addHelper(data, root);
    }
    /**
     * Returns node that becomes root depending on the shift of data
     * @param node to check where it should be placed on AVL
     * @param data the data to add
     * @return the root of the tree
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> node) {
        if (node == null) {
            size++;
            return new AVLNode<>(data);
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(addHelper(data, node.getLeft()));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(addHelper(data, node.getRight()));
        }
        update(node);
        return balance(node);
    }
    /**
     * This method updates the balance factor and height of the node entered
     * @param node to update balance factor and height
     */
    private void update(AVLNode<T> node) {
        int lHeight = 0;
        int rHeight = 0;
        if (node.getLeft() != null) {
            lHeight = node.getLeft().getHeight();
        }
        if (node.getRight() != null) {
            rHeight = node.getRight().getHeight();
        }
        if (node.getLeft() == null && node.getRight() == null) {
            node.setHeight(0);
            node.setBalanceFactor(0);
        } else if (node.getLeft() == null) {
            node.setHeight(1);
            node.setBalanceFactor(-1 - rHeight);
        } else if (node.getRight() == null) {
            node.setHeight(1);
            node.setBalanceFactor(lHeight + 1);
        } else {
            node.setHeight(Math.max(lHeight, rHeight) + 1);
            node.setBalanceFactor(lHeight - rHeight);
        }
    }
    /**
     * This method balances the AVL if the conditions of an unbalanced tree are met
     * @param node to check change in balance factor
     * @return the node with updated data
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            node = rotateLeft(node);
        } else if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            node = rotateRight(node);
        }
        return node;
    }
    /**
     * Returns node that has shifted due to right rotation
     * @param node to shift in right rotation
     * @return new node in updated position
     */
    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);
        update(node);
        update(temp);
        return temp;
    }
    /**
     * Returns node that has shifted due to left rotation
     * @param node to shift in left rotation
     * @return new node in updated position
     */
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        update(node);
        update(temp);
        return temp;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data, NOT successor. As a reminder, rotations can occur
     * after removing the predecessor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data inputted is null!");
        }
        AVLNode<T> dummy = new AVLNode<T>(null);
        root = removeHelper(root, data, dummy);
        return dummy.getData();
    }
    /**
     * Returns node after checking for its children in the AVL
     * @param node to check where it should be placed on AVL
     * @param data the data to add
     * @param dummy the node to record the predecessor of the node being removed
     * @throws java.util.NoSuchElementException if the data inputted is not in the AVL
     * @return the root of the tree
     */
    private AVLNode<T> removeHelper(AVLNode<T> node, T data, AVLNode<T> dummy) {
        if (node == null) {
            throw new NoSuchElementException("The data inputted is not in the AVL!");
        } else if (node.getData().compareTo(data) > 0) {
            node.setLeft(removeHelper(node.getLeft(), data, dummy));
        } else if (node.getData().compareTo(data) < 0) {
            node.setRight(removeHelper(node.getRight(), data, dummy));
        } else {
            dummy.setData(node.getData());
            size--;
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() != null && node.getRight() != null) {
                AVLNode<T> dummy2 = new AVLNode<T>(null);
                node.setLeft(predecessor(node.getLeft(), dummy2));
                node.setData(dummy2.getData());
            }
        }
        update(node);
        return balance(node);
    }

    /**
     * Returns dummy node after node removed is found in the AVL
     * @param node to check where it should be placed on AVL
     * @param dummy the node to record the predecessor of the node being removed
     * @return the predecessor of the node removed
     */
    private AVLNode<T> predecessor(AVLNode<T> node, AVLNode<T> dummy) {
        if (node.getRight() == null) {
            dummy.setData(node.getData());
            return node.getLeft();
        } else {
            node.setRight(predecessor(node.getRight(), dummy));
            update(node);
        }
        return balance(node);
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
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
     * Returns data of the node recursively ran through the AVL
     * @param temp to check where it should be placed on AVL
     * @param data the data to be obtained from AVL
     * @throws NoSuchElementException when data entered is null
     * @return the data from the node needed to be obtained
     */
    private T getHelper(AVLNode<T> temp, T data) {
        if (temp == null) {
            throw new java.util.NoSuchElementException("The node is not found in AVL!");
        }
        if (data.compareTo(temp.getData()) == 0) {
            return temp.getData();
        } else if (data.compareTo(temp.getData()) < 0) {
            return getHelper(temp.getLeft(), data);
        } else {
            return getHelper(temp.getRight(), data);
        }

    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
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
     * Returns boolean statement of whether or not the node was recursively found in the AVL
     * @param temp to check where it should be placed on AVL
     * @param data the data to be compared to
     * @return if whether or not node was found
     */
    private boolean containHelper(AVLNode<T> temp, T data) {
        if (temp == null) {
            return false;
        }
        if (data.compareTo(temp.getData()) == 0) {
            return true;
        } else if (data.compareTo(temp.getData()) < 0) {
            return containHelper(temp.getLeft(), data);
        } else {
            return containHelper(temp.getRight(), data);
        }
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the data on branches of the tree with the maximum depth. If you
     * encounter multiple branches of maximum depth while traversing, then you
     * should list the remaining data from the left branch first, then the
     * remaining data in the right branch. This is essentially a preorder
     * traversal of the tree, but only of the branches of maximum depth.
     *
     * This must be done recursively.
     *
     * Your list should not have duplicate data, and the data of a branch should be
     * listed in order going from the root to the leaf of that branch.
     *
     * Should run in worst case O(n), but you should not explore branches that
     * do not have maximum depth. You should also not need to traverse branches
     * more than once.
     *
     * Hint: How can you take advantage of the balancing information stored in
     * AVL nodes to discern deep branches?
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * Returns: [10, 5, 2, 1, 0, 7, 8, 9, 15, 20, 25, 30]
     *
     * @return the list of data in branches of maximum depth in preorder
     * traversal order
     */
    public List<T> deepestBranches() {
        List<T> list = new ArrayList<>();
        deepestBranchesHelper(list, root);
        return list;
    }

    /**
     * This method recursively runs through the tree and adds nodes if it belongs
     * to the deepest branch
     * @param list the ArrayList the nodes of the deepest branch are added to
     * @param node to check recursively through the tree
     */
    private void deepestBranchesHelper(List<T> list, AVLNode<T> node) {
        if (node == null) {
            return;
        } else {
            list.add(node.getData());
            if (node.getLeft() != null
                    && !(node.getHeight() - node.getLeft().getHeight() > 1)) {
                deepestBranchesHelper(list, node.getLeft());
            }
            if (node.getRight() != null
                    && !(node.getHeight() - node.getRight().getHeight() > 1)) {
                deepestBranchesHelper(list, node.getRight());
            }
        }
    }

    /**
     * Returns a sorted list of data that are within the threshold bounds of
     * data1 and data2. That is, the data should be > data1 and < data2.
     *
     * This must be done recursively.
     *
     * Should run in worst case O(n), but this is heavily dependent on the
     * threshold data. You should not explore branches of the tree that do not
     * satisfy the threshold.
     *
     * Example Tree:
     *                           10
     *                       /        \
     *                      5          15
     *                    /   \      /    \
     *                   2     7    13    20
     *                  / \   / \     \  / \
     *                 1   4 6   8   14 17  25
     *                /           \          \
     *               0             9         30
     *
     * sortedInBetween(7, 14) returns [8, 9, 10, 13]
     * sortedInBetween(3, 8) returns [4, 5, 6, 7]
     * sortedInBetween(8, 8) returns []
     *
     * @param data1 the smaller data in the threshold
     * @param data2 the larger data in the threshold
     * @return a sorted list of data that is > data1 and < data2
     * @throws IllegalArgumentException if data1 or data2 are null
     * or if data1 > data2
     */
    public List<T> sortedInBetween(T data1, T data2) {
        if (data1 == null || data2 == null || data1.compareTo(data2) > 0) {
            if (data1 == null || data2 == null) {
                throw new IllegalArgumentException("Data inputted cannot be null!");
            }
            if (data1.compareTo(data2) > 0) {
                throw new IllegalArgumentException("Data1 cannot be greater than Data2!");
            }
        }
        List<T> list = new ArrayList<>();
        if (data1.equals(data2)) {
            return list;
        } else {
            sortedInBetweenHelper(data1, data2, root, list);
        }
        return list;
    }

    /**
     * This method recursively runs through the tree and adds elements to a list
     * if they are within the 2 inputted nodes
     * @param data1 first input minimum boundary
     * @param data2 second input maximum boundary
     * @param node node that is compared to boundary data
     * @param list ArrayList of all the accepted nodes
     */
    private void sortedInBetweenHelper(T data1, T data2,
                                       AVLNode<T> node, List<T> list) {
        if (node == null) {
            return;
        } else {
            if (node.getData().compareTo(data1) > 0) {
                sortedInBetweenHelper(data1, data2, node.getLeft(), list);
            }
            if (node.getData().compareTo(data1) > 0
                    && node.getData().compareTo(data2) < 0) {
                list.add(node.getData());
            }
            if (node.getData().compareTo(data2) < 0) {
                sortedInBetweenHelper(data1, data2, node.getRight(), list);
            }
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
    public AVLNode<T> getRoot() {
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
