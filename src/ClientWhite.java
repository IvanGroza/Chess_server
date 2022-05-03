import java.io.*;
import java.net.Socket;
import java.io.Serializable;
public class ClientWhite extends Client{
    public static Thread thread;
    private static Socket clientSocket; //сокет для общения
    private static InputStream in; // поток чтения из сокета
    private static OutputStream out;
    public static ObjectInputStream in_2;
    public static ObjectOutputStream out_2;
    public static ChessGUI white ;
    public static void  main(String[] args) {
        try {
            thread = new Thread();

           // try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", 8080); // этой строкой мы запрашиваем
                white=new ChessGUI(1,"ClientWhite");
            //  у сервера доступ на соединение
            thread.start();



           // } catch (ClassNotFoundException e) {
           //     e.printStackTrace();
//            } finally { // в любом случае необходимо закрыть сокет и потоки
//                System.out.println("Клиент был закрыт...");
//               // clientSocket.close();
//               // in.close();
//               // out.close();
//            }
        } catch (IOException e) {
            System.err.println(e);
        }
        //catch (ClassNotFoundException e) {
         //   e.printStackTrace();
       // }

    }

}
