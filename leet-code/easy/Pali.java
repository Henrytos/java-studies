public class Pali {
    public static void main(String[] args) {

        Pali pali = new Pali();
        int number = 121;
        boolean result = pali.isPalindrome(number);
        System.out.println("Is " + number + " a palindrome? " + result);
        number = -121;
        result = pali.isPalindrome(number);
        System.out.println("Is " + number + " a palindrome? " + result);
        number = 10;
        result = pali.isPalindrome(number);
        System.out.println("Is " + number + " a palindrome? " + result);
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        StringBuilder pali = new StringBuilder().append(x);
        if (pali.reverse().toString().equals(String.valueOf(x))) {
            return true;
        }

        return false;
    }
}
