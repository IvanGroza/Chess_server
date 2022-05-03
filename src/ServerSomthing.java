import javax.swing.*;
import java.io.*;

import java.net.Socket;
import java.io.Serializable;
public class ServerSomthing extends Thread {

    private static Socket white;
    //private static Socket black;// сокет, через который сервер общается с клиентом,
    public ChessGUI server ;
    public static InputStream inWhite;
    public static OutputStream outWhite;
    public static ObjectOutputStream out_2White;
    public static ObjectInputStream in_2White ;
    public ThreadByExtending thread = new ThreadByExtending("first");
   // public static InputStream inBlack;
   // public static OutputStream outBlack;
   // public static ObjectOutputStream out_2Black;
   // public static ObjectInputStream in_2Black ;

    // кроме него - клиент и сервер никак не связаны


    public ServerSomthing(Socket white) throws IOException, InterruptedException {
        this.white = white;
        server=new ChessGUI(1,"Server");
        int a=0;
        start();

        //this.black = black;
          // поток чтения из сокета
        //outWhite=white.getOutputStream();; // поток записи в сокет
       // inBlack=black.getInputStream(); // поток чтения из сокета
       // outBlack=black.getOutputStream();

        // если потоку ввода/вывода приведут к генерированию исключения, оно проброситься дальше
       // вызываем run()

    }
    @Override
    public synchronized void run() {

        //ChessGUI chessGUI =new ChessGUI();
        //white.createImages();
        //chessGUI.initializeGui();
        try {

            //socket.
//                    / out_2 = new ObjectOutputStream(out);
//                      // out_2.writeObject(chessGUI);
//                       //out_2.close();
            int a = 0;
           // while (a == 0) {
                inWhite = white.getInputStream();
                System.out.println("2");
                in_2White = new ObjectInputStream(white.getInputStream());
                System.out.println("3");


                server.setChessBoardSquares((ChessButton[][]) in_2White.readObject());

                //inWhite.close();
                //in_2White.close();
                System.out.println("4");
                //out_2White = new ObjectOutputStream(white.getOutputStream());

                //out_2White.writeObject(server.chessBoardSquares);
                ;
                //  out_2Black = new ObjectOutputStream(black.getOutputStream());

                // out_2Black.writeObject(server.getChessBoardSquares());
                System.out.println("5");
                for (int ii = 0; ii < 8; ii++) {
                    for (int jj = 0; jj < 8; jj++) {
                        System.out.print(server.getChessBoardSquares()[ii][jj].getStringFigure() + "\t");
                    }
                    System.out.println("\n");
                }
                thread.start();
//                         }
                //ServerSomthing.this.join();
           // }
            }
        catch(IOException ex)
            {
                ex.printStackTrace();
            }
        catch(ClassNotFoundException e){
                e.printStackTrace();
            }
    }

}
