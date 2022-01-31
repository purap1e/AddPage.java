package untitled3.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try{
            System.out.println("Server started");
            System.out.println("waiting for connections...");
            ServerSocket server = new ServerSocket(2020);
            int id = 0;
            while(true){
                Socket socket = server.accept();
                id++;
                System.out.println("Client #"+id+" connected");

                ClientHandler ch = new ClientHandler(socket,id);
                ch.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

