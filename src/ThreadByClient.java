import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class ThreadByClient extends Thread{
    private Thread thread;
    private String threadName;

    public ThreadByClient(String threadName) {
        this.threadName = threadName;
        System.out.println("Thread " + threadName + " created successfully.");
    }

    public void run(){
        try {
            out = clientSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ; // поток записи в сокет
        System.out.println("3");
        try {
            out_2 = new ObjectOutputStream(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("4");

        // читать соообщения с сервера
        try {
            out_2.writeObject(white.getChessBoardSquares());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //out.close();
        //out_2.close();
        System.out.println("5");
        // in_2=new ObjectInputStream(clientSocket.getInputStream());
        System.out.println("6");
        // white.chessBoardSquares=(ChessButton[][])in_2.readObject();
        System.out.println("7");
        // white= (ChessGUI) in_2.readObject();
        // white.drawButton(white.chessBoardSquares);

        System.out.println("8");
        Server.chess.run();
    }



    public void start(){

    }
}
