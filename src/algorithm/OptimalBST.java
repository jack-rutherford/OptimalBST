package algorithm;

import java.util.Arrays;

public class OptimalBST {

    public static int optimalBST(int[] keys, int[] freq) {
        int n = keys.length;
        int[][] cost = new int[n + 1][n + 1];

        // Initialize cost for subproblems of size 1
        for (int i = 1; i <= n; i++) {
            cost[i][i] = freq[i-1];
        }

        // Solve subproblems of increasing size
        for (int L = 2; L <= n; L++) {
            for (int i = 1; i <= n - L + 1; i++) {
                int j = i + L - 1;
                cost[i][j] = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += freq[k-1];
                }
                // Try all possible roots
                for (int r = i; r <= j; r++) {
                    int c = sum + (r - 1 < i ? 0 : cost[i][r - 1]) + (r + 1 > j ? 0 : cost[r + 1][j]);
                    cost[i][j] = Math.min(cost[i][j], c);
                }
            }
        }
        return cost[1][n];
    }

    public static void main(String[] args) {
        int[] keys = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] freq = { 50, 25, 12, 18, 20, 22, 8, 10, 5, 15};
        System.out.println("Minimum cost of Optimal BST is : " + optimalBST(keys, freq));
    }
}