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
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test the tree is balanced or not

// @SuppressWarnings("rawtypes")
public class BALSTTest {

  BALST<String, String> balst1;
  BALST<Integer, String> balst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    balst1 = createInstance();
    balst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    balst1 = null;
    balst2 = null;
  }

  protected BALST<String, String> createInstance() {
    return new BALST<String, String>();
  }

  protected BALST<Integer, String> createInstance2() {
    return new BALST<Integer, String>();
  }

  /** 
   * Insert three values in sorted order and then check 
   * the root, left, and right keys to see if rebalancing 
   * occurred.
   */
  @Test
  void testBALST_001_insert_sorted_order_simple() {
    try {
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(10))
        fail("avl insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfRightChildOf(10).equals(20))
        fail("avl insert to right child of root does not work");

      balst2.insert(30, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("avl rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), Integer.valueOf(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), Integer.valueOf(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), Integer.valueOf(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /** 
   * Insert three values in reverse sorted order and then check 
   * the root, left, and right keys to see if rebalancing 
   * occurred in the other direction.
   */
  @Test
  void testBALST_002_insert_reversed_sorted_order_simple() {

    try {
      balst2.insert(10, "10");
      balst2.insert(8, "8");
      balst2.insert(6, "6");
    } catch (IllegalNullKeyException | DuplicateKeyException e) { // handle exception
      fail("shouldn't throw exception");
      e.printStackTrace();
    }
    if (balst2.getKeyAtRoot() != 8) {// check root
      fail("root key should be 8");
    }

    try {
      if (balst2.getKeyOfLeftChildOf(8) != 6) { // check left
        fail("left key should be 6");
      }
      if (balst2.getKeyOfRightChildOf(8) != 10) {// check right
        fail("right key should be 10");
      }
    } catch (IllegalNullKeyException | KeyNotFoundException e) {// handle exception
      fail("shouldn't throw exception");
      e.printStackTrace();
    }
  }

  /** 
   * Insert three values so that a right-left rotation is
   * needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing 
   * occurred in the other direction.
   */
  @Test
  void testBALST_003_insert_smallest_largest_middle_order_simple() {

    try {
      balst2.insert(10, "10");
      balst2.insert(30, "30");
      balst2.insert(20, "20");
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }
    if (balst2.getKeyAtRoot() != 20) { // check root
      fail("root key should be 20");
    }
    try {
      if (balst2.getKeyOfLeftChildOf(20) != 10) { // check left child
        fail("left key should be 10");
      }
      if (balst2.getKeyOfRightChildOf(20) != 30) { // check right child
        fail("right key should be 30");
      }
    } catch (IllegalNullKeyException | KeyNotFoundException e) { // handle exception
      fail("shouldn't throw exception");
      e.printStackTrace();
    }
  }



  /** 
   * Insert three values so that a left-right rotation is
   * needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing 
   * occurred in the other direction.
   */
  @Test
  void testBALST_004_insert_largest_smallest_middle_order_simple() {
    try {
      balst2.insert(30, "30");
      balst2.insert(10, "10");
      balst2.insert(20, "20");
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }
    if (balst2.getKeyAtRoot() != 20) {
      fail("root key should be 20");
    }
    try {
      if (balst2.getKeyOfLeftChildOf(20) != 10) {
        fail("left key should be 10");
      }
      if (balst2.getKeyOfRightChildOf(20) != 30) {
        fail("right key should be 30");
      }
    } catch (IllegalNullKeyException | KeyNotFoundException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }

  }

  /**
   * Insert three keys into the adt and the check that left
   * right rotation is taking place. Then check root, left and right.
   * After that remove right child and root.
   * Check that root is equal to 19
   */
  @Test
  void testBALST_005_insert_three_keys_and_remove_two() {
    try {
      balst2.insert(30, "30");
      balst2.insert(10, "10");
      balst2.insert(20, "20");
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }
    if (balst2.getKeyAtRoot() != 20) { // check right
      fail("root key should be 20");
    }
    try {
      if (balst2.getKeyOfLeftChildOf(20) != 10) { // check left
        fail("left key should be 10");
      }
      if (balst2.getKeyOfRightChildOf(20) != 30) { // check right
        fail("right key should be 30");
      }
    } catch (IllegalNullKeyException | KeyNotFoundException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }

    try {
      balst2.remove(30); // remove right child
      balst2.remove(20); // remove root
    } catch (Exception e) {
      fail("shouldn't throw exception");
    }

    if (balst2.getKeyAtRoot() != 10) { // check root after removing previous nodes
      fail("root key should be 10 but isn't");
    }

    int keys = balst2.numKeys();
    if (keys != 1) {
      fail("number of keys should be one but isn't");
    }
  }

  /**
   * insert three nodes into tree and check that get method returns correct value
   */
  @Test
  void testBALST_006_get_correct_vale() {
    try {
      balst2.insert(30, "30"); // insert nodes into empty adt
      balst2.insert(10, "10");
      balst2.insert(20, "20");
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }


    try {
      String a = balst2.get(10); // get value from node key 10
      if (a != "10") {
        fail("value should be but isn't");
      }
    } catch (IllegalNullKeyException | KeyNotFoundException e) { // handle possible exception
      fail("shouldn't throw error");
      e.printStackTrace();
    }

    int keys = balst2.numKeys();
    if (keys != 3) {
      fail("number of keys in tree aren't correct");
    }
  }

  /**
   * insert 7 nodes into empty tree and check that contains method finds the specified keys
   */
  @Test
  void testBALST_007_contains_method_check() {
    try {
      balst2.insert(30, "30");// add nodes into empty adt
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(31, "31");
      balst2.insert(32, "32");
      balst2.insert(33, "33");
      balst2.insert(34, "34");
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }

    try {
      if (!balst2.contains(30)) { // check if contains added key
        fail("should return true but coudln't find key");
      }
      if (!balst2.contains(32)) { // check if contains added key
        fail("should return true but coudln't find key");
      }
      if (balst2.contains(44)) { // check if contains unadded key
        fail("should return false but found the nonexistent key");
      }
    } catch (IllegalNullKeyException e) { // handle null exception which shouldn't reach this
      fail("shouldn't throw error");
      e.printStackTrace();
    }
  }

  /**
   * add 7 nodes into empty tree and check that numkey is keeping correct count of number of keys
   * present in the tree
   */
  @Test
  void testBALST_008_check_num_keys_variable() {
    try {
      balst2.insert(30, "30"); // add 7 nodes into empty adt
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(31, "31");
      balst2.insert(32, "32");
      balst2.insert(33, "33");
      balst2.insert(34, "34");
    } catch (IllegalNullKeyException | DuplicateKeyException e) { // handle exception
      fail("shouldn't throw exception");
      e.printStackTrace();
    }

    if (balst2.numKeys() != 7) { // check adt if it knows it has 7 nodes
      fail("should return with 7 keys but found more/less");
    }
  }

  /**
   * add 7 nodes into empty tree and check that the get height method is able to function properly
   * and return correct number of height
   */
  @Test
  void testBALST_009_check_height_of_avl_tree() {
    try {
      balst2.insert(30, "30"); // add 7 nodes into empty adt
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(31, "31");
      balst2.insert(32, "32");
      balst2.insert(33, "33");
      balst2.insert(34, "34");
      balst2.insert(838328, "d");
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }
    if (balst2.getHeight() != 4) { // compare with 3 to height
      fail("tree height is three but returned with something else");
    }
  }

  /**
   * adds 7 nodes into empty tree and checks that the inorder traversal method returns the list of 
   * key in the correct order
   */
  @Test
  void testBALST_010_check_in_order_traversal() {
    try {
      balst2.insert(30, "30"); // add 7 nodes to empty adt
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(31, "31");
      balst2.insert(32, "32");
      balst2.insert(33, "33");
      balst2.insert(34, "34");
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }


    List<Integer> results = balst2.getInOrderTraversal(); // call inorder traversal
    if (!results.get(0).equals(10)) { // check if list contains 10 in correct place
      fail("didn't return right key");
    }
    if (!results.get(3).equals(31)) {// check if list contains 31 in correct place
      fail("didn't return right key");
    }
    if (!results.get(5).equals(33)) {// check if list contains 33 in correct place
      fail("didn't return right key");
    }
  }

  /**
   * adds 7 nodes into empty tree and checks that the level order traversal method returns the list of 
   * key in the correct order
   */
  @Test
  void testBALST_011_check_level_order_traversal() {
    try {
      balst2.insert(30, "30"); // add 7 nodes to empty adt
      balst2.insert(10, "10");
      balst2.insert(20, "20");
      balst2.insert(31, "31");
      balst2.insert(32, "32");
      balst2.insert(33, "33");
      balst2.insert(34, "34");
    } catch (IllegalNullKeyException | DuplicateKeyException e) {
      fail("shouldn't throw exception");
      e.printStackTrace();
    }
    List<Integer> results = balst2.getLevelOrderTraversal(); // call levelorder traversal
    if (!results.get(2).equals(33)) { // check list at element 3
      fail("didn't return right key");
    }
    if (!results.get(4).equals(30)) { // check list at element 5
      fail("didn't return right key");
    }
    if (!results.get(6).equals(34)) { // check list at element 7
      fail("didn't return right key");
    }
    balst2.print();
  }

}
