package cs158.com.company;
import java.io.*;
import java.net.*;
import java.nio.channels.*;
import java.util.Scanner;


//simulates client.
public class Client {
    private static HttpURLConnection con;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file you want to get: ");
        String requestedFile = scanner.nextLine(); // grab file request
        final int PORT = 80;
        File file = new File("local/" + requestedFile); // make file object
        try {
            URL url = new URL("http://localhost:" + PORT + "/" + requestedFile);
            System.out.println("Sending GET request to " + url);
            //InputStream res = myurl.openStream();
            //prepare file to save to
            file.getParentFile().mkdir(); // make local directory if it doesn't exist
            file.createNewFile(); // make
            //create writer
            System.out.println("Response Received:");
            // send request
            ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
            // setup file saver
            FileOutputStream fileOutputStream = new FileOutputStream(file.getPath());
            FileChannel fileChannel = fileOutputStream.getChannel();
            // transfer file info
            fileChannel.transferFrom(byteChannel, 0, Long.MAX_VALUE);
        } catch(Exception e){
            file.deleteOnExit();
            e.printStackTrace();
        }
    }
}
