import java.io.*;
import java.net.*;

public class ChatClientt {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 54321;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            // Thread to receive messages
            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Send messages to the server
            String userInput;
            while ((userInput = consoleReader.readLine()) != null) {
                out.println(userInput);
                if (userInput.equalsIgnoreCase("/exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
