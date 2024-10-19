package edu.grinnell.csc207.util;

import static edu.grinnell.csc207.util.MatrixAssertions.assertMatrixEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;


/**
 * This class contains tests for each method of MatrixV0
 *
 * @author Leonardo Alves Nunes
 */
public class TestsByStudent {

  /**
   * Helper method to set a Matrix, which is used in the following tests.
   * 
   * @return MatrixV0
   */
  public static MatrixV0 defaultMatrix() {
    MatrixV0 testMatrix = new MatrixV0<>(10, 10);
    for (int i = 0; i < testMatrix.height(); i++) {
      for (int j = 0; j < testMatrix.width(); j++) {
        testMatrix.set(i, j, i + j);
      } // for
    } // for
    return testMatrix;
  } // defaultMatrix()

  /**
   * Tests set method by creating a matrix just like defaultMatrix.
   * 
   * @return void.
   */
  @Test
  public void testSet() {
    MatrixV0 testMatrix = new MatrixV0<>(10, 10);
    try {
      for (int i = 0; i < testMatrix.height(); i++) {
        for (int j = 0; j < testMatrix.width(); j++) {
          testMatrix.set(i, j, i + j);
        } // for
      } // for
    } catch (Exception e) {
      fail("Could not set the values in the array" + e);
    } // try-catch
  } // testSet()

  /**
   * Test if it can get several items of the matrix.
   * 
   * @return void.
   */
  @Test
  public void testGet() {
    MatrixV0 testMatrix = defaultMatrix();
    assertEquals(0, testMatrix.get(0, 0), "Gets one of the elements in the array");
    assertEquals(2, testMatrix.get(1, 1), "Gets one of the elements in the array");
    assertEquals(4, testMatrix.get(2, 2), "Gets one of the elements in the array");
    assertEquals(5, testMatrix.get(2, 3), "Gets one of the elements in the array");
  } // testGet()

  /**
   * Test if the height of the matrix change after deletion of a row.
   * 
   * @return void.
   */
  @Test
  public void testHeight() {
    MatrixV0 testMatrix = defaultMatrix();
    assertEquals(10, testMatrix.height());
    testMatrix.deleteRow(1);
    assertEquals(9, testMatrix.height());
  } // testHeight

  /**
   * Test if the width of the matrix change after deletion of a width.
   * 
   * @return void.
   */
  @Test
  public void testWidth() {
    MatrixV0 testMatrix = defaultMatrix();
    assertEquals(10, testMatrix.width());
    testMatrix.deleteCol(1);
    assertEquals(9, testMatrix.width());
  } // testWidth()

  /**
   * Test inserting one row.
   * 
   * @return void.
   */
  @Test
  public void testInsertRow() {
    MatrixV0 testMatrix = defaultMatrix();
    assertEquals(10, testMatrix.height());
    try {
      testMatrix.insertRow(1);
      assertEquals(11, testMatrix.height(), "Rows increased");
    } catch (Exception e) {
      fail("Could not insert Row " + e);
    } // try-catch
  } // testInsertRow()

  /**
   * Test inserting one column.
   * 
   * @return void.
   */
  @Test
  public void testInserCol() {
    MatrixV0 testMatrix = defaultMatrix();
    assertEquals(10, testMatrix.width());
    try {
      testMatrix.insertCol(1);
      assertEquals(11, testMatrix.width(), "Columns increased");
    } catch (Exception e) {
      fail("Could not insert Column " + e);
    } // try-catch
  } // testInsertCol()

  /**
   * Test deleting all rows of the matrix.
   * 
   * @return void.
   */
  @Test
  public void testDeleteRow() {
    MatrixV0 testMatrix = defaultMatrix();
    assertEquals(10, testMatrix.height());
    try {
      int counter = 0;
      while (counter < 10) {
        testMatrix.deleteRow(0);
        counter++;
      } // while
      assertEquals(0, testMatrix.height(), "All rows deleted");
    } catch (Exception e) {
      fail("Could not delete row" + e);
    } // try-catch
  } // testDeleteRow()

  /**
   * Test deleting all columns of the matrix.
   * 
   * @return void.
   */
  @Test
  public void testDeleteCol() {
    MatrixV0 testMatrix = defaultMatrix();
    assertEquals(10, testMatrix.width());
    try {
      int counter = 0;
      while (counter < 10) {
        testMatrix.deleteCol(0);
        counter++;
      } // while
      assertEquals(0, testMatrix.width(), "All columns deleted");
    } catch (Exception e) {
      fail("Could not delete col" + e);
    } // try-catch
  } // testDeleteCol()

  /**
   * Test filling a 5x5 region with 1000 and getting them.
   * 
   * @return void.
   */
  @Test
  public void testFillRegion() {
    MatrixV0 testMatrix = defaultMatrix();
    try {
      testMatrix.fillRegion(0, 0, 5, 5, 1000);
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          assertEquals(1000, testMatrix.get(i, j), "Values should be 1000");
        } // for
      } // for
    } catch (Exception e) {
      fail("Could not fill region " + e);
    } // try-catch
  } // testfillRegion

  /**
   * Test filling a vertical line.
   * 
   * @return void.
   */
  @Test
  public void testFillLine() {
    MatrixV0 testMatrix = defaultMatrix();
    testMatrix.fillLine(0, 0, 1, 0, 10, 1, 1);
    for (int i = 0; i < testMatrix.height(); i++) {
      assertEquals(1, testMatrix.get(i, 0), "All values are one");
    } // for
  } // void testFillLine()

  /**
   * Test if cloned matrix has elements in the same position.
   * 
   * @return void.
   */
  @Test
  public void testClone() {
    MatrixV0 testMatrix = defaultMatrix();
    try {
      Matrix testClonedMatrix = testMatrix.clone();
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          assertEquals(testMatrix.get(i, j), testClonedMatrix.get(i, j), "All values are one");
        } // for
      } // for
    } catch (Exception e) {
      fail("Could not clone matrix");
    } // try-catch
  } // testClone()

  /**
   * Test if two matrix keep the same of different after deletions.
   * 
   * @return void.
   */
  @Test
  public void testEquals() {
    MatrixV0 testMatrix = defaultMatrix();
    Matrix testClonedMatrix = testMatrix.clone();

    assertTrue(testMatrix.equals(testClonedMatrix));
    testMatrix.deleteRow(1);
    assertFalse(testMatrix.equals(testClonedMatrix));
    testClonedMatrix.deleteRow(1);
    assertTrue(testMatrix.equals(testClonedMatrix));
  } // testEquals()
} // class TestByStudent
