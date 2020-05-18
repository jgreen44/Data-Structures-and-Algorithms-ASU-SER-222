public class main {

    public static void main(String[] args) {
        System.out.println(recurse("SER 222"));

    }

    private static String recurse(String str){
        if(str.length() <= 1){
            return str;
        }
        return str.substring(str.length()-1)+recurse(str.substring(0, str.length()-1));
    }
}
