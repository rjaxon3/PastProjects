
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;

/**
 * Your implementations of various string searching algorithms.
 *
 * @author Rhea Jaxon
 * @version 1.0
 * @userid rjaxon3
 * @GTID 903760234
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class PatternMatching {

    /**
     * Knuth-Morris-Pratt (KMP) algorithm relies on the failure table (also
     * called failure function). Works better with small alphabets.
     *
     * Make sure to implement the buildFailureTable() method before implementing
     * this method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> kmp(CharSequence pattern, CharSequence text,
                                    CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The pattern entered is null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator entered is null!");
        }
        if (text == null) {
            throw new IllegalArgumentException("The text entered is null!");
        }
        List<Integer> list = new LinkedList<>();
        if (pattern.length() > text.length()) {
            return list;
        }
        int[] table = buildFailureTable(pattern, comparator);
        int i = 0;
        int j = 0;
        while (i <= text.length() - pattern.length()) {
            while (j < pattern.length() && comparator.compare(text.charAt(i
                    + j), pattern.charAt(j)) == 0) {
                j++;
            }
            if (j == 0) {
                i++;
            } else {
                if (j == pattern.length()) {
                    list.add(i);
                }
                int shift = table[j - 1];
                i += j - shift;
                j = shift;
            }
        }
        return list;
    }

    /**
     * Builds failure table that will be used to run the Knuth-Morris-Pratt
     * (KMP) algorithm.
     *
     * The table built should be the length of the input pattern.
     *
     * Note that a given index i will contain the length of the largest prefix
     * of the pattern indices [0..i] that is also a suffix of the pattern
     * indices [1..i]. This means that index 0 of the returned table will always
     * be equal to 0
     *
     * Ex.
     * pattern:       a  b  a  b  a  c
     * failureTable: [0, 0, 1, 2, 3, 0]
     *
     * If the pattern is empty, return an empty array.
     *
     * @param pattern    a pattern you're building a failure table for
     * @param comparator you MUST use this to check if characters are equal
     * @return integer array holding your failure table
     * @throws java.lang.IllegalArgumentException if the pattern or comparator
     *                                            is null
     */
    public static int[] buildFailureTable(CharSequence pattern,
                                          CharacterComparator comparator) {
        if (pattern == null) {
            throw new IllegalArgumentException("The pattern entered is null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator entered is null!");
        }
        int[] table = new int[pattern.length()];
        if (pattern.length() == 0) {
            return table;
        }
        int i = 0;
        int j = 1;
        table[0] = 0;
        while (j < pattern.length()) {
            if (comparator.compare(pattern.charAt(i), pattern.charAt(j)) == 0) {
                i++;
                table[j] = i;
                j++;
            } else if (i == 0) {
                table[j] = 0;
                j++;
            } else {
                i = table[i - 1];
            }
        }
        return table;
    }

    /**
     * Boyer Moore algorithm that relies on last occurrence table. Works better
     * with large alphabets.
     *
     * Make sure to implement the buildLastTable() method before implementing
     * this method. Do NOT implement the Galil Rule in this method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class
     * useful.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMoore(CharSequence pattern,
                                           CharSequence text,
                                           CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The pattern entered is null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator entered is null!");
        }
        if (text == null) {
            throw new IllegalArgumentException("The text entered is null!");
        }
        List<Integer> list = new LinkedList<>();
        Map<Character, Integer> lastOcc = buildLastTable(pattern);
        int i = 0;
        int shift = 0;
        while (i <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;
            while (j >= 0 && comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0) {
                j--;
            }
            if (j == -1) {
                list.add(i);
                i++;
            } else {
                shift = lastOcc.getOrDefault(text.charAt(i + j), -1);
                if (shift < j) {
                    i += (j - shift);
                } else {
                    i++;
                }
            }
        }
        return list;
    }

    /**
     * Builds last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     *
     * @param pattern a pattern you are building last table for
     * @return a Map with keys of all of the characters in the pattern mapping
     * to their last occurrence in the pattern
     * @throws java.lang.IllegalArgumentException if the pattern is null
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("The pattern entered is null!");
        }
        HashMap<Character, Integer>
                table = new HashMap<Character, Integer>();
        for (int i = 0; i < pattern.length(); i++) {
            table.put(pattern.charAt(i), i);
        }
        return table;
    }

    /**
     * Prime base used for Rabin-Karp hashing.
     * DO NOT EDIT!
     */
    private static final int BASE = 113;

    /**
     * Runs the Rabin-Karp algorithm. This algorithms generates hashes for the
     * pattern and compares this hash to substrings of the text before doing
     * character by character comparisons.
     *
     * When the hashes are equal and you do character comparisons, compare
     * starting from the beginning of the pattern to the end, not from the end
     * to the beginning.
     *
     * You must use the Rabin-Karp Rolling Hash for this implementation. The
     * formula for it is:
     *
     * sum of: c * BASE ^ (pattern.length - 1 - i)
     *   c is the integer value of the current character, and
     *   i is the index of the character
     *
     * We recommend building the hash for the pattern and the first m characters
     * of the text by starting at index (m - 1) to efficiently exponentiate the
     * BASE. This allows you to avoid using Math.pow().
     *
     * Note that if you were dealing with very large numbers here, your hash
     * will likely overflow; you will not need to handle this case.
     * You may assume that all powers and calculations CAN be done without
     * overflow. However, be careful with how you carry out your calculations.
     * For example, if BASE^(m - 1) is a number that fits into an int, it's
     * possible for BASE^m will overflow. So, you would not want to do
     * BASE^m / BASE to calculate BASE^(m - 1).
     *
     * Ex. Hashing "bunn" as a substring of "bunny" with base 113
     * = (b * 113 ^ 3) + (u * 113 ^ 2) + (n * 113 ^ 1) + (n * 113 ^ 0)
     * = (98 * 113 ^ 3) + (117 * 113 ^ 2) + (110 * 113 ^ 1) + (110 * 113 ^ 0)
     * = 142910419
     *
     * Another key point of this algorithm is that updating the hash from
     * one substring to the next substring must be O(1). To update the hash,
     * subtract the oldChar times BASE raised to the length - 1, multiply by
     * BASE, and add the newChar as shown by this formula:
     * (oldHash - oldChar * BASE ^ (pattern.length - 1)) * BASE + newChar
     *
     * Ex. Shifting from "bunn" to "unny" in "bunny" with base 113
     * hash("unny") = (hash("bunn") - b * 113 ^ 3) * 113 + y
     *              = (142910419 - 98 * 113 ^ 3) * 113 + 121
     *              = 170236090
     *
     * Keep in mind that calculating exponents is not O(1) in general, so you'll
     * need to keep track of what BASE^(m - 1) is for updating the hash.
     *
     * Do NOT use Math.pow() in this method.
     * Do NOT implement your own version of Math.pow().
     *
     * @param pattern    a string you're searching for in a body of text
     * @param text       the body of text where you search for pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> rabinKarp(CharSequence pattern,
                                          CharSequence text,
                                          CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The pattern entered is null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator entered is null!");
        }
        if (text == null) {
            throw new IllegalArgumentException("The text entered is null!");
        }
        List<Integer> list = new LinkedList<>();
        if (pattern.length() > text.length()) {
            return list;
        }
        int pHash = 0;
        int tHash = 0;
        for (int i = 0; i < pattern.length(); i++) {
            pHash = pHash + pattern.charAt(i) * pow(BASE, pattern.length() - 1 - i);
            tHash = tHash + text.charAt(i) * pow(BASE, pattern.length() - 1 - i);
        }
        int k = 0;
        while (k <= text.length() - pattern.length()) {
            if (pHash == tHash) {
                int j = 0;
                while (j < pattern.length() && comparator.compare(text.charAt(k
                        + j), pattern.charAt(j)) == 0) {
                    j = j + 1;
                }
                if (j == pattern.length()) {
                    list.add(k);
                }
            }
            if (k + 1 <= text.length() - pattern.length()) {
                tHash = updateHash(tHash, pattern.length(),
                        text.charAt(k), text.charAt(k + pattern.length()));
            }
            k++;
        }
        return list;
    }

    /**
     * This method returns the math of a number to an exponent
     * @param base base number of the power method
     * @param exp the exponent the base is set to
     * @return the base to the power of exponent
     */
    private static int pow(int base, int exp) {
        if (exp == 0) {
            return 1;
        } else {
            return base * pow(base, exp - 1);
        }
    }

    /**
     * This method updates the textHash pattern for Rabin-Karp pattern matching
     * @param oldHash old hash calculated
     * @param length length of the pattern
     * @param oldChar old character to be deleted from textHash
     * @param newChar new character to be added to textHash
     * @return new textHash pattern
     */
    private static int updateHash(int oldHash, int length, char oldChar,
                                 char newChar) {
        if (length < 0) {
            throw new IllegalArgumentException("Length is negative");
        }
        return (oldHash - oldChar * pow(BASE, length - 1)) * BASE + newChar;
    }

    /**
     * This method is OPTIONAL and for extra credit only.
     *
     * The Galil Rule is an addition to Boyer Moore that optimizes how we shift the pattern
     * after a full match. Please read Extra Credit: Galil Rule section in the homework pdf for details.
     *
     * Make sure to implement the buildLastTable() method and buildFailureTable() method
     * before implementing this method.
     *
     * @param pattern    the pattern you are searching for in a body of text
     * @param text       the body of text where you search for the pattern
     * @param comparator you MUST use this to check if characters are equal
     * @return list containing the starting index for each match found
     * @throws java.lang.IllegalArgumentException if the pattern is null or has
     *                                            length 0
     * @throws java.lang.IllegalArgumentException if text or comparator is null
     */
    public static List<Integer> boyerMooreGalilRule(CharSequence pattern,
                                                    CharSequence text,
                                                    CharacterComparator comparator) {
        if (pattern == null || pattern.length() == 0) {
            throw new IllegalArgumentException("The pattern entered is null!");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("The comparator entered is null!");
        }
        if (text == null) {
            throw new IllegalArgumentException("The text entered is null!");
        }
        List<Integer> list = new LinkedList<>();
        if (pattern.length() > text.length()) {
            return list;
        }
        Map<Character, Integer> lastOcc = buildLastTable(pattern);
        int[] failureTable = buildFailureTable(pattern, comparator);
        int i = 0;
        int k = 0;
        int period = pattern.length() - failureTable[pattern.length() - 1];
        while (i <= text.length() - pattern.length()) {
            int j = pattern.length() - 1;
            while (j >= k && comparator.compare(text.charAt(i + j), pattern.charAt(j)) == 0) {
                j--;
            }
            if (j < k) {
                list.add(i);
                i = i + period;
                k = pattern.length() - period;
            } else {
                int shift = lastOcc.getOrDefault(text.charAt(i + j), -1);
                if (shift < j) {
                    i += (j - shift);
                } else {
                    i++;
                }
                k = 0;
            }
        }
        return list;
    }
}
