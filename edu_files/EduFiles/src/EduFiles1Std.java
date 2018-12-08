import java.io.*;

/*
in.txt
Andrew
*/

public class EduFiles1Std {

    public static void main(String[] args) throws IOException {
        System.out.print("Enter your name: ");
        byte[] line = new byte[1000];
        int count = System.in.read(line);
        String hello = "Hello, " + new String(line, 0, count) + "!";
        System.out.println("out:" + hello);
        System.err.println("err:" + hello);
    }
    
}
