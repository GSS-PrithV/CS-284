public class Test{
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = s1;
        String s3 = "abcd";
        if (s1 == s2) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }
        if (s1 == s3){
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }
    }
}