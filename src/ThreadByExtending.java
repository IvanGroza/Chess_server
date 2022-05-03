import javax.swing.*;

public class ThreadByExtending extends Thread{
    private Thread thread;
    private String threadName;

    public ThreadByExtending(String threadName) {
        this.threadName = threadName;
        System.out.println("Thread " + threadName + " created successfully.");
    }

    public void run(ChessGUI chessGUI){
        chessGUI.drawButton(chessGUI.getChessBoardSquares());
        chessGUI.getChessBoardSquares()[0][0].setIcon(new ImageIcon(chessGUI.chessPieceImages[0][0]));
        System.out.println("Thread " +  threadName + " is running...");

        try {

           // notifyAll();
           // join();
            ClientWhite.thread.run();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void start(){

        }
    }
