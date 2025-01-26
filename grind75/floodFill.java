/**!SECTION
 * https://leetcode.com/problems/flood-fill/
 */
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        return fill(image, sr, sc, image[sr][sc], color);
    }
    private int[][] fill(int[][] image, int sr, int sc, int stColor, int color) {
        if (sr >= image.length || sr < 0)
            return image;

        if (sc >= image[0].length || sc < 0)
            return image;

        if (image[sr][sc] == stColor && image[sr][sc] != color) {
            image[sr][sc] = color;
            fill(image, sr+1, sc, stColor, color);
            fill(image, sr, sc+1, stColor, color);
            fill(image, sr-1, sc, stColor, color);
            fill(image, sr, sc-1, stColor, color);
        }

        return image;
    }
}