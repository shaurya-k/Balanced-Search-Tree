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
//
// Classes that use this type: <BALST.java, BALSTADT.java, BALSTTest.java>
class BSTNode<K, V> {

  K key;
  V value;
  BSTNode<K, V> left;
  BSTNode<K, V> right;
  int balanceFactor;
  int height;


  /**
   * @param key
   * @param value
   * @param leftChild
   * @param rightChild
   */
  BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild) {
    this.key = key;
    this.value = value;
    this.left = leftChild;
    this.right = rightChild;
    this.height = 0;
    this.balanceFactor = 0;
  }

  BSTNode(K key, V value) {
    this(key, value, null, null);
  }


}
