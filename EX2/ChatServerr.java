import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServerr {
    private static final int PORT = 54321; // Port number for the server
    private static Map<String, List<ClientHandler>> chatRooms = new HashMap<>(); // Map to store chat rooms and their clients

    public static void main(String[] args) {
        System.out.println("Chat server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;
        private String currentRoom;

        public ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                out.println("Enter your username:");
                username = in.readLine();
                out.println("Welcome " + username + "! Enter chat room ID to join or create:");
                currentRoom = in.readLine();
                
                synchronized (chatRooms) {
                    chatRooms.computeIfAbsent(currentRoom, k -> new ArrayList<>()).add(this);
                }
                out.println("Joined chat room: " + currentRoom);
                sendActiveUsers();

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("/exit")) {
                        out.println("Goodbye!");
                        break;
                    } else if (message.equalsIgnoreCase("/users")) {
                        sendActiveUsers();
                    } else {
                        broadcastMessage(currentRoom, username + ": " + message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        }

        private void broadcastMessage(String room, String message) {
            synchronized (chatRooms) {
                for (ClientHandler client : chatRooms.get(room)) {
                    client.out.println(message);
                }
            }
        }

        private void sendActiveUsers() {
            synchronized (chatRooms) {
                List<ClientHandler> clients = chatRooms.get(currentRoom);
                out.println("Active users in " + currentRoom + ": " + clients.size());
                for (ClientHandler client : clients) {
                    out.println(client.username);
                }
            }
        }

        private void closeConnection() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            synchronized (chatRooms) {
                chatRooms.get(currentRoom).remove(this);
                broadcastMessage(currentRoom, username + " has left the chat.");
            }
        }
    }
}
