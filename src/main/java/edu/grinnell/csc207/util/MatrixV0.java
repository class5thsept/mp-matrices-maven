package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Leonardo Alves Nunes
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * 2D array with generic types [row][column].
   */
  T[][] contents;

  /**
   * Height of the matrix (amount of rows).
   */
  int matrixHeight;

  /**
   * Width of the matrix (amount of columns).
   */
  int matrixWidth;

  /**
   * Value used for filling spaces when another value is not specified.
   */
  T defaultVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    this.matrixHeight = height;
    this.matrixWidth = width;
    this.defaultVal = def;
    this.contents = (T[][]) new Object[this.matrixHeight][this.matrixWidth];

    for (int i = 0; i < this.matrixHeight; i++) {
      for (int j = 0; j < this.matrixWidth; j++) {
        this.contents[i][j] = def;
      } // for
    } // for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) {
    return this.contents[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    this.contents[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.matrixHeight;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.matrixWidth;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    this.matrixHeight += 1;

    T[][] updatedContent = (T[][]) new Object[this.matrixHeight][this.matrixWidth];

    for (int i = 0; i < row; i++) {
      updatedContent[i] = this.contents[i];
    } // for

    updatedContent[row] = (T[]) new Object[this.matrixWidth];
    for (int j = 0; j < this.matrixWidth; j++) {
      updatedContent[row][j] = this.defaultVal;
    } // for
    for (int i = row + 1; i <= this.matrixHeight - 1; i++) {
      updatedContent[i] = this.contents[i - 1];
    } // for
    this.contents = updatedContent;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if (vals.length != this.matrixWidth) {
      throw new ArraySizeException("Size of vals != matrix width");
    } // if

    insertRow(row);
    for (int j = 0; j < this.matrixWidth; j++) {
      this.contents[row][j] = vals[j];
    } // for
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    this.matrixWidth += 1;

    T[][] updatedContent = (T[][]) new Object[this.matrixHeight][this.matrixWidth];
    for (int i = 0; i < this.matrixHeight; i++) {
      for (int j = 0; j < col; j++) {
        updatedContent[i][j] = this.contents[i][j];
      } // for
      updatedContent[i][col] = this.defaultVal;
      for (int j = col + 1; j <= this.matrixWidth - 1; j++) {
        updatedContent[i][j] = this.contents[i][j - 1];
      } // for
    } // for
    this.contents = updatedContent;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if (vals.length != this.matrixHeight) {
      throw new ArraySizeException("Size of vals != matrix height");
    } // if

    insertCol(col);
    for (int i = 0; i < this.matrixHeight; i++) {
      this.contents[i][col] = vals[i];
    } // for
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    this.matrixHeight -= 1;
    T[][] updatedContent = (T[][]) new Object[this.matrixHeight][this.matrixWidth];
    for (int i = 0; i < row; i++) {
      updatedContent[i] = this.contents[i];
    } // for
    for (int i = row; i < this.matrixHeight; i++) {
      updatedContent[i] = this.contents[i + 1];
    } // for
    this.contents = updatedContent;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    this.matrixWidth -= 1;

    T[][] updatedContent = (T[][]) new Object[this.matrixHeight][this.matrixWidth];
    for (int i = 0; i < this.matrixHeight; i++) {
      for (int j = 0; j < col; j++) {
        updatedContent[i][j] = this.contents[i][j];
      } // for
      for (int j = col; j < this.matrixWidth; j++) {
        updatedContent[i][j] = this.contents[i][j + 1];
      } // for
    } // for
    this.contents = updatedContent;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        this.contents[i][j] = val;
      } // for
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    int row = startRow;
    int col = startCol;

    while (row < endRow && col < endCol) {
      this.contents[row][col] = val;
      row += deltaRow;
      col += deltaCol;
    } // while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix clone() {
    MatrixV0 clonedMatrixV0 = new MatrixV0<T>(this.matrixWidth, this.matrixHeight, this.defaultVal);

    for (int i = 0; i < this.matrixHeight; i++) {
      for (int j = 0; j < this.matrixWidth; j++) {
        clonedMatrixV0.set(i, j, this.get(i, j));
      } // for
    } // for
    return clonedMatrixV0;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  public boolean equals(Object other) {
    if (other instanceof Matrix) {
      Matrix otherMatrix = (Matrix) other;

      if (this.width() != otherMatrix.width() || this.height() != otherMatrix.height()) {
        return false;
      } // if

      for (int i = 0; i < this.height(); i++) {
        for (int j = 0; j < this.width(); j++) {
          if (!this.get(i, j).equals(otherMatrix.get(i, j))) {
            return false;
          } // if
        } // for
      } // for
      return true;
    } else {
      return false;
    } // if-else
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
