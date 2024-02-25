package support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
public class InteractionWithServer {
    private static Socket socket;
    public static void send(byte[] arr) throws IOException {
        int port = 6701;
        InetAddress host = InetAddress.getLocalHost();
        socket = new Socket(host, port);
        OutputStream out = socket.getOutputStream();
        out.write(arr);
    }
    public static String receive() throws IOException {
        byte[] arr = new byte[1000];
        int len = arr.length;
        InputStream in = socket.getInputStream();
        in.read(arr);
        String string = new String(arr, 0, len);
        String[] splittedStr = string.split("");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < splittedStr.length; i ++) {
            if (splittedStr[i].equals("\0")) {
                break;
            }
            builder.append(splittedStr[i]);
        }
        return builder.toString();
    }
}
