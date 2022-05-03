import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.io.Serializable;

public class Server {
    public static final int PORT = 8080;
    public static ServerSomthing chess;
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        try {
            while (true) {
                // Блокируется до возникновения нового соединения:
                Socket white = server.accept();
                //Socket black = server.accept();
                try {
                     ServerSomthing  chess =new ServerSomthing(white);
                    // добавить новое соединенние в список

                } catch (IOException e) {
                    // Если завершится неудачей, закрывается сокет,
                    // в противном случае, нить закроет его при завершении работы:
                    white.close();
                    //black.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            server.close();
        }
    }
}