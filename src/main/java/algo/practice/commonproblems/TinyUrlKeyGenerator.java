package algo.practice.commonproblems;

public class TinyUrlKeyGenerator {
    //base62 = 0-9,A-Z,a-z
    //base64 = base62, +, /
    private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = BASE62_CHARS.length();

    public static String toBase62(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            int remainder = (int) (value % BASE);
            sb.append(BASE62_CHARS.charAt(remainder));
            value /= BASE;
        }
        return sb.reverse().toString();
    }

    public static String generateCode(long counter) {
        String base62 = toBase62(counter);
        // Pad with '0' to ensure length is exactly 6
        return String.format("%6s", base62).replace(' ', '0');
    }

    public static void main(String[] args) {
        for (long i = 0; i < 5; i++) {
            System.out.println(generateCode(i));
        }
    }
}
