package testauto.hw1;

public class Solution {

    // check whether an Integer is palindrome
    public boolean isPalindrome(int x) {
        String given = String.valueOf(x);
        StringBuilder reverse = new StringBuilder();
        for (int i = given.length() - 1; i >= 0; i--) {
            reverse.append(given.charAt(i));
        }
        return given.equals(reverse.toString());
    }

    // return indexes of two elements of array which add up to target integer
    public int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    indices[0] = i;
                    indices[1] = j;
                    break;
                }
            }

        }
        return indices;
    }

    // Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string
    public int lengthOfLastWord(String s) {
        int result = 1;
        char[] chars = s.trim().toCharArray();
        for (int i = chars.length - 2; i >= 0; i--) {
            if (chars[i] != ' ') {
                result++;
            } else break;
        }
        return result;
    }

    // Given a non-negative integer x, compute and return the square root of x.
    public int mySqrt(int x) {
        double num;
        double half = (double) x / 2;
        do {
            num = half;
            half = (num + (x / num)) / 2;
        }
        while ((num - half) != 0);
        return (int)half;
    }


    //Fizz-buzz: если число делится на 3 – fizz, на 5 - buzz, на 3 и 5 – fizzbuzz
    public String fizzBuzz(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        if(num%3 == 0) {
            stringBuilder.append("fizz");
        }
        if(num%5 == 0) {
            stringBuilder.append("buzz");
        }

        return stringBuilder.toString();
    }

    // Вычисление числа Фибоначчи n
    public long recursiveFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return recursiveFibonacci(n - 2) + recursiveFibonacci(n - 1);
    }


    //Реализовать операцию деления числа на число без использования (div, mod)*
    public int divisionBySubtraction(int x, int y) {
        int result = 0;
        while ((x -=y) >= 0) {
            result++;
        }
        return result;
    }
}
