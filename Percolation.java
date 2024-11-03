import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* Percolation data type to solve the percolation problem */
public class Percolation {
    private boolean[][] site;
    private int n;
    private WeightedQuickUnionUF uf;
    private int virtualTop;
    private int virtualBottom;
    private int openSiteCount;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation (int n){
        if (n<=0){
            throw new IllegalArgumentException("n should be >= 0");
        }
        this.n = n;
        site = new boolean[n][n];   
        uf = new WeightedQuickUnionUF(n * n + 2);
        virtualTop = n * n;
        virtualBottom = n * n + 1;
        openSiteCount = 0;

        for (int i = 0; i < n; i++){
            for (int j=0; j < n; j++){
               site[i][j] = false; 
            }
        }
        
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        //Exception
        if (row < 1 || row > n || col < 1 || col > n){
            throw new IllegalArgumentException("Row or col out of bounds");
        }

        // Convert 1-based indexing to 0-based
        int rowIndex = row - 1;
        int colIndex = col - 1;

        if (!isOpen(row, col)){
            site[rowIndex][colIndex] = true;
            openSiteCount++;
            int index = rowIndex * n + colIndex;

            //Connect to the site above
            if (row > 1 && isOpen(row - 1, col)) {
                uf.union(index, (row-2) * n + (colIndex));
            }

            //Connect to site below
            if (row < n && isOpen(row + 1, col)) {
                uf.union(index, row * n + (colIndex));
            }

            //Connect to right site
            if (col < n && isOpen(row, col+1)) {
                uf.union(index, rowIndex * n + (col));
            }

            //Connect to the left site
            if (col > 1 && isOpen(row, col-1)) {
                uf.union(index, (rowIndex) * n + (colIndex - 1));
            }

            //Connect top and bottom rows to the virtual ones
            if (row == 1){
                uf.union(index, virtualTop);
            }
            if (row == n){
                uf.union(index, virtualBottom);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row < 1 || row > n || col < 1 || col > n){
            throw new IllegalArgumentException("Row or col out of bounds");
        }
        return site[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (row < 1 || row > n || col < 1 || col > n){
            throw new IllegalArgumentException("Row or col out of bounds");
        }
        int index = (row-1) * n + (col-1);
        return (uf.find(index) == uf.find(virtualTop));
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openSiteCount;
    }

    // does the system percolate?
    public boolean percolates(){
        return (uf.find(virtualTop) == uf.find(virtualBottom));
    }

    // test client (optional)
    public static void main(String[] args){

    }
    
}
