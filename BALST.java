//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (P2 AVL TREE)
// Course: (CS 400, Fall 2019)
//
// Author: (Shaurya Kethireddy)
// Email: (skethireddy@wisc.edu)
// Lecturer's Name: (Debra Deppeler)
// Description: This project allows user to add, remove, get, and check contain
// in the tree that is built
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class to implement a BalanceSearchTree. Can be of type AVL or Red-Black. Note which tree you
 * implement here and as a comment when you submit.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */
public class BALST<K extends Comparable<K>, V> implements BALSTADT<K, V> {

  private BSTNode<K, V> root;

  private List<K> orderList;

  int numKeys;

  public BALST() {}

  /**
   * Returns the key that is in the root node of this BST.
   * If root is null, returns null.
   * @return key found at root node, or null
   */
  @Override
  public K getKeyAtRoot() {
    if (root != null) { // checks if there's a root or not
      return root.key;
    }
    return null;
  }

  /**
   * Tries to find a node with a key that matches the specified key.
   * If a matching node is found, it returns the returns the key that is in the left child.
   * If the left child of the found node is null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  @Override
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) { // checks if key is null
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // checks if key is in tree
      throw new KeyNotFoundException();
    } else {

      return get(root, key).left.key; // goes through helper method recursively and finds the node
    }
  }

  /**
   * Tries to find a node with a key that matches the specified key.
   * If a matching node is found, it returns the returns the key that is in the right child.
   * If the right child of the found node is null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  @Override
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) { // checks if key is null
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // checks if key is in tree
      throw new KeyNotFoundException();
    } else {
      return get(root, key).right.key; // goes through helper method recursively and finds the node
    }
  }

  /**
   * Returns the height of this BST.
   * H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0
   * If root is a leaf, return 1
   * Else return 1 + max( height(root.left), height(root.right) )
   * 
   * Examples:
   * A BST with no keys, has a height of zero (0).
   * A BST with one key, has a height of one (1).
   * A BST with two keys, has a height of two (2).
   * A BST with three keys, can be balanced with a height of two(2)
   *                        or it may be linear with a height of three (3)
   * ... and so on for tree with other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  @Override
  public int getHeight() {
    if (root == null) {
      return 0;
    }
    BSTNode<K, V> temp = root;
    return heightHelper(temp); // calls helper method to get height starting from root

  }

  /**
   * helper method which helps getHeight() method handle the height
   * @param node - node
   * @return - height of tree
   */
  private int heightHelper(BSTNode<K, V> node) {
    int right = 0; // initializes the variable
    int left = 0; // initializes the variable
    if (node == null) { // null check
      return -1;
    } else {
      if (node.right != null) // checks if right node is null
        right = heightHelper(node.right);
      // increment right int
      if (node.left != null) // checks if left node is null
        left = heightHelper(node.left);
      // increment left int
      return Math.max(right, left) + 1; // finds the max height and increments one
    }
  }

  /**
   * helper method which helps getBalance() method handle the balance factor
   * @param node - node which needs to be checked for height
   * @return - height of certain node
   */
  private int height(BSTNode<K, V> node) {
    if (node == null) // null check
      return 0;

    if (node.left == null && node.right == null) { // checks if children of current node are
                                                   // selected
      return 1;
    } else {
      return 1 + Math.max(height(node.right), height(node.left)); // recursive to get height
    }

  }

  /**
   * private method which allows the user to get height of certain node
   * @param node - selected node by user
   * @return - left subtree height - right subtree height
   */
  private int getBalance(BSTNode<K, V> node) {
    if (node == null)
      return 0;

    return height(node.left) - height(node.right);
  }

  /**
   * Returns the keys of the data structure in sorted order.
   * In the case of binary search trees, the visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  @Override
  public List<K> getInOrderTraversal() {
    orderList = new ArrayList<>(); // initialize new arraylist
    if (root == null) { // checks if tree is empty
      return orderList;
    }

    return inOrderHelper(root); // calls helper method
  }

  /**
   * helper method which assists getInOrderTraversal() in handling the function
   * @param node - the recursion node 
   * @return - list with all the keys in order
   */
  private List<K> inOrderHelper(BSTNode<K, V> node) {
    if (node.left != null) { // checks if left child exists
      inOrderHelper(node.left);
    }
    orderList.add(node.key);
    if (node.right != null) { // checks if right child exists
      inOrderHelper(node.right);
    }

    return orderList;
  }

  /**
   * Returns the keys of the data structure in pre-order traversal order.
   * In the case of binary search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  @Override
  public List<K> getPreOrderTraversal() {
    orderList = new ArrayList<>(); // initialize new arraylist
    if (root == null) { // checks if tree is empty
      return orderList;
    }
    return preOrderHelper(root); // calls helper method
  }

  /**
   * helper method which assits getPreOrderTraversal() with logic
   * @param node - recursion node to go through tree
   * @return - list of keys in pre order
   */
  private List<K> preOrderHelper(BSTNode<K, V> node) {
    orderList.add(node.key); // adds to list of keys
    if (node.left != null) { // checks if left child exists
      preOrderHelper(node.left);
    }
    if (node.right != null) { // checks if right child exists
      preOrderHelper(node.right);
    }

    return orderList;
  }

  /**
   * Returns the keys of the data structure in post-order traversal order.
   * In the case of binary search trees, the order is: L R V 
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    orderList = new ArrayList<>(); // initialize new arraylist
    if (root == null) { // checks if tree is empty
      return orderList;
    }
    return postOrderHelper(root);
  }

  /**
   * helper method which assists getPostOrderTraversal() with logic
   * @param node - recursive node in tree
   * @return - list of keys in tree in post order
   */
  private List<K> postOrderHelper(BSTNode<K, V> node) {
    if (node.left != null) { // checks if left child exists
      postOrderHelper(node.left);
    }
    if (node.right != null) { // checks if right child exists
      postOrderHelper(node.right);
    }
    orderList.add(node.key); // adds the key to list

    return orderList;
  }

  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down,
   * and so on. 
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  @Override
  public List<K> getLevelOrderTraversal() {
    orderList = new ArrayList<>(); // initialize new list
    int height = getHeight(); // get height of current tree
    for (int i = 0; i <= height; i++) { // loop through helper method with each level
      levelOrderHelper(root, i);
    }
    return orderList;
  }

  private void levelOrderHelper(BSTNode<K, V> node, int height) {
    if (node == null) // null check
      return;
    if (height == 1) // base case
      orderList.add(node.key);
    else if (height > 1) {
      int recursiveHeight = height - 1;
      levelOrderHelper(node.left, recursiveHeight);
      levelOrderHelper(node.right, recursiveHeight);

    }

  }

  /** 
   * Add the key,value pair to the data structure and increase the number of keys.
   * If key is null, throw IllegalNullKeyException;
   * If key is already in data structure, throw DuplicateKeyException(); 
   * Do not increase the num of keys in the structure, if key,value pair is not added.
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    BSTNode<K, V> newObject = new BSTNode<>(key, value); // creates an object with the key and value
    if (key == null) { // null check
      throw new IllegalNullKeyException();
    }

    if (root == null) { // checks if tree empty
      root = newObject; // assigns
      numKeys++; // increment
      return;
    } else if (contains(key)) { // checks if key in tree
      throw new DuplicateKeyException();
    }

    root = insertHelper(key, value, root); // assigns root with new node attached as reference

  }

  /**
   * helper method which assists insert() with logic
   * @param key - key of new object being added
   * @param value - value of new object being added
   * @param node - node to compare key with recursively
   * @return - node
   * @throws DuplicateKeyException
   */
  private BSTNode<K, V> insertHelper(K key, V value, BSTNode<K, V> node)
      throws DuplicateKeyException {
    BSTNode<K, V> newNode = new BSTNode<K, V>(key, value); // new object with the key and value
    if (node == null) { // if root is null
      numKeys++; // increment
      return newNode; // return node with new reference
    }

    if (key.compareTo(node.key) > 0) { // checks if key is greater than node.key
      node.right = insertHelper(key, value, node.right);
    } else if (key.compareTo(node.key) < 0) { // checks if key is less than node.key
      node.left = insertHelper(key, value, node.left);
    } else {
      return node; // return node with correct reference
    }

    int balanceFactor = getBalance(node); // get the out of balance factor

    if (balanceFactor > 1 && node.left != null) { // checks balance factor for right rotate
      if (key.compareTo(node.left.key) < 0) {
        return rotateRight(node);
      }
    }
    if (balanceFactor > 1 && node.left != null) { // checks balance factor for left right rotate
      if (key.compareTo(node.left.key) > 0) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
      }
    }

    if (balanceFactor < -1 && node.right != null) { // checks balance factor for left rotate
      if (key.compareTo(node.right.key) > 0) {
        return rotateLeft(node);
      }
    }

    if (balanceFactor < -1 && node.right != null) { // checks balance factor for right left rotate
      if (key.compareTo(node.right.key) < 0) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
      }
    }

    return node;
  }

  /** 
   * If key is found, remove the key,value pair from the data structure and decrease num keys.
   * If key is not found, do not decrease the number of keys in the data structure.
   * If key is null, throw IllegalNullKeyException
   * If key is not found, throw KeyNotFoundException().
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) { // null check
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // checks if tree contains the certain key
      throw new KeyNotFoundException();
    }
    root = removeHelper(key, root); // returns node with correct references
    numKeys--; // decrement
    return true;
  }

  /**
   * helper method which assists remove() with logic
   * @param key - key of node trying to be removed
   * @param node - recursive node to check against
   * @return - removed node with new pointers
   */
  private BSTNode<K, V> removeHelper(K key, BSTNode<K, V> node) {
    if (node == null) // null check
      return node;
    if (key.compareTo(node.key) < 0) // compares param key to node key
      node.left = removeHelper(key, node.left);
    else if (key.compareTo(node.key) > 0) // compares param key to node key
      node.right = removeHelper(key, node.right);
    else if (node.left != null && node.right != null) { // checks if children are both null
      node.key = findMin(node.right).key;
      node.right = removeHelper(node.key, node.right);
    } else {
      if (node.left != null) {
        node = node.left; // assigns
      } else {
        node = node.right; // assigns
      }
    }
    return node;
  }

  /**
   * helper method which rotates right to balance tree after inserting
   * @param node - node which is out of balance after inserting
   * @return - balanced node
   */
  private BSTNode<K, V> rotateRight(BSTNode<K, V> node) {
    BSTNode<K, V> newNode = node.left; // creates a temp variable with left reference
    BSTNode<K, V> nextNode = newNode.right; // creates a temp variable with left right reference
    newNode.right = node; // reassign
    node.left = nextNode;
    return newNode;
  }

  /**
   * helper method which rotates left to balance the tree after inserting
   * @param node - the node which is out of balance
   * @return - node in balance
   */
  private BSTNode<K, V> rotateLeft(BSTNode<K, V> node) {
    BSTNode<K, V> newNode = node.right; // temp variable with right reference
    BSTNode<K, V> rightNode = newNode.left; // temp variable with right left reference
    newNode.left = node; // reassign
    node.right = rightNode;
    return newNode;
  }

  /**
   *  Returns the value associated with the specified key
   *
   * Does not remove key or decrease number of keys
   * If key is null, throw IllegalNullKeyException 
   * If key is not found, throw KeyNotFoundException().
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) { // null check
      throw new IllegalNullKeyException();
    }
    if (!contains(key)) { // check if key exists in tree
      throw new KeyNotFoundException();
    }
    return get(root, key).value; // helper method returns certain node which .value is called
  }

  /** 
   * Returns true if the key is in the data structure
   * If key is null, throw IllegalNullKeyException 
   * Returns false if key is not null and is not present 
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) { // null check
      throw new IllegalNullKeyException();
    }

    return containsHelper(root, key);

  }

  /**
   * helper method which assists contains() with logic
   * @param node - node to check with
   * @param key - key to compare with
   * @return - false if key not found
   */
  private boolean containsHelper(BSTNode<K, V> node, K key) {
    while (node != null) { // null check
      if (key.compareTo(node.key) == 0) { // compare key with node key
        return true;
      } else if (key.compareTo(node.key) > 0) { // checks if key is greater than node key
        node = node.right;

      } else {
        node = node.left;
      }
    }
    return false;
  }

  /**
   *  Returns the number of key,value pairs in the data structure
   */
  @Override
  public int numKeys() {
    if (numKeys != 0) { // 0 check
      return numKeys;
    }
    return 0;
  }

  private BSTNode<K, V> get(BSTNode<K, V> node, K key) throws KeyNotFoundException {
    if (node == null) { // null check
      throw new KeyNotFoundException();
    }
    while (node != null) { // run through until node is null
      if (node.key.compareTo(key) == 0) { // if node key equals key
        return node;
      } else if (key.compareTo(node.key) < 0) { // if key is less that node key
        node = node.left;
      } else {
        node = node.right;
      }
    }
    return node;


  }

  /**
   * Print the tree. 
   */
  @Override
  public void print() {
    BSTNode<K, V> temp = root; // assign to temp variable to avoid reassigning actual root reference
    if (root == null) { // null check
      System.out.println("The tree is currently empty");
      return;
    }
    printHelper(temp, 0); // helper method which handles logic
  }

  /**
   * helper method which handles in the print logic
   * @param node
   * @param place
   */
  private void printHelper(BSTNode<K, V> node, int height) {
    if (node == null) { //null check to stop recursion
      return;
    }
    printHelper(node.right, height + 1); //calls with right child
    if (height != 0) {
      for (int i = 0; i < height - 1; i++) { //go 
        System.out.print("|\t");
      }
      System.out.println("----->" + node.key); //to print out node key with tags
    } else {
      System.out.println(node.key); //print out key
    }
    printHelper(node.left, height + 1); //calls with left child

  }


  /**
   * helper method which finds the min attached to a certain node
   * @param node - node to check with
   * @return - pointer of node which is the min
   */
  private BSTNode<K, V> findMin(BSTNode<K, V> node) {
    if (node == null) { // checks null
      return null;
    } else if (node.left == null) { // checks left child null
      return node;
    }
    return findMin(node.left); // recursive with left child
  }

}
