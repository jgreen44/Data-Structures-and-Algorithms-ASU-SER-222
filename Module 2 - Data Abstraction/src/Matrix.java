/**
 * A simple matrix ADT.
 * 
 * @author Ruben Acuna
 * @version 1.1
 */
public interface Matrix {
    
    /**
     * Returns the element at particular point in the matrix.
     * @param y y position
     * @param x x position
     * @return element
     */
    public int getElement(int y, int x);

    /**
     * Returns the number of rows in the matrix.
     * @return rows
     */
    public int getRows();
    
    /**
     * Returns the number of columns in the matrix.
     * @return columns
     */
    public int getColumns();
            
    /**
     * Returns this matrix scaled by a factor. That is, computes kA where k is a
     * constant and A is a matrix (this object).
     * 
     * @param scalar scalar
     * @return matrix
     */
    public Matrix scale(int scalar);
    
    /**
     * Returns this matrix added with another matrix. That is, computes A+B 
     * where A and B are matrices (this object, and another respectively).
     * @param other addend
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */
    public Matrix plus(Matrix other);
    
    /**
     * Returns this matrix subtracted by another matrix. That is, computes A-B 
     * where A and B are matrices (this object, and another respectively).
     * @param other subtrahend
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */
    public Matrix minus(Matrix other);
    
    /**
     * Returns this matrix multiplied by another matrix. That is, computes AB 
     * where A and B are matrices (this object, and another respectively).
     * @param other multiplicand
     * @return matrix
     * @throws RuntimeException if matrices do not have matching dimensions.
     */
    public Matrix multiply(Matrix other);
    
    
    /**
     * Returns true if this matrix matches another matrix.
     * @param other another matrix
     * @return equality
     */
    @Override
    public boolean equals(Object other);
    
    /**
     * Returns a string representation of this matrix. A new line character will
     * separate each row, while a space will separate each column.
     * @return string representation
     */
    @Override
    public String toString();
}