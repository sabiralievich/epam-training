package testauto.hw1;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    private final Solution solution = new Solution();

    @Test
    public void isPalindromeTest() {

        Assert.assertTrue(solution.isPalindrome(12321));
        Assert.assertFalse(solution.isPalindrome(10));
    }

    @Test
    public void twoSumTest() {
        int[] testArray = new int[]{2, 7, 11, 15};
        int target = 9;

        Assert.assertArrayEquals(new int[]{0, 1}, solution.twoSum(testArray, target));
    }

    @Test
    public void twoSumTest2() {
        int[] testArray = new int[]{3, 2, 4};
        int target = 6;

        Assert.assertArrayEquals(new int[]{1, 2}, solution.twoSum(testArray, target));
    }

    @Test
    public void twoSumTest3() {
        int[] testArray = new int[]{3, 3};
        int target = 6;

        Assert.assertArrayEquals(new int[]{0, 1}, solution.twoSum(testArray, target));
    }

    @Test
    public void lengthOfLastWordTest() {
        Assert.assertEquals(5, solution.lengthOfLastWord("Hello World"));
    }


    @Test
    public void lengthOfLastWordTest2() {
        Assert.assertEquals(4, solution.lengthOfLastWord("   fly me   to   the moon  "));
    }

    @Test
    public void lengthOfLastWordTest3() {
        Assert.assertEquals(6, solution.lengthOfLastWord("luffy is still joyboy"));
    }

    @Test
    public void lengthOfLastWordTest4() {
        Assert.assertEquals(1, solution.lengthOfLastWord("a"));
    }


    @Test
    public void lengthOfLastWordTest5() {
        Assert.assertEquals(3, solution.lengthOfLastWord("    day"));
    }

    @Test
    public void mySqrt() {
        Assert.assertEquals(2, solution.mySqrt(4), 1);
    }

    @Test
    public void mySqrt2() {
        Assert.assertEquals(2, solution.mySqrt(8), 1);
    }

    @Test
    public void fizzBuzzTest1() {
        Assert.assertEquals("fizz", solution.fizzBuzz(9));
    }

    @Test
    public void fizzBuzzTest2() {
        Assert.assertEquals("buzz", solution.fizzBuzz(10));
    }

    @Test
    public void fizzBuzzTest3() {
        Assert.assertEquals("fizzbuzz", solution.fizzBuzz(15));
    }

    @Test
    public void fizzBuzzTest4() {
        Assert.assertEquals("", solution.fizzBuzz(16));
    }

    @Test
    public void recursiveFibonacciTest1() {
        Assert.assertEquals(6765, solution.recursiveFibonacci(20));
    }

    @Test
    public void recursiveFibonacciTest2() {
        Assert.assertEquals(1, solution.recursiveFibonacci(1));
    }

    @Test
    public void divisionBySubtractionTest1() {
        Assert.assertEquals(5, solution.divisionBySubtraction(20, 4));
    }

    @Test
    public void divisionBySubtractionTest2() {
        Assert.assertEquals(4, solution.divisionBySubtraction(17, 4));
    }
}
