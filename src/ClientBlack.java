import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.io.Serializable;


public class ClientBlack extends Client
{
    private static Socket clientSocket; //сокет для общения
    private static InputStream in; // поток чтения из сокета
    private static OutputStream out;
    public static ObjectInputStream in_2;
    public static ObjectOutputStream out_2;
    public static ChessGUI black ;
    public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    public static final int[] STARTING_ROW = {
            ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    public static final int BLACK = -1, WHITE = 1;

    public static void main(String[] args) {
        try {
             try {
            // адрес - локальный хост, порт - 4004, такой же как у сервера
            clientSocket = new Socket("localhost", 8080); // этой строкой мы запрашиваем
            //  у сервера доступ на соединение
            //InputStream in=clientSocket.getInputStream(); // поток чтения из сокета
            // out=clientSocket.getOutputStream();; // поток записи в сокет
            // in_2 = new ObjectInputStream(clientSocket.getInputStream());
            // читать соообщения с сервера

            black=new ChessGUI(2,"ClientBlack");
            int a=0;
                 while(!clientSocket.isOutputShutdown()){
                System.out.println("1");
                InputStream in=clientSocket.getInputStream();
                in_2=new ObjectInputStream(clientSocket.getInputStream());
                black.setChessBoardSquares((ChessButton[][])in_2.readObject());
                Thread.sleep(1000);
                //out = clientSocket.getOutputStream();
                ; // поток записи в сокет
                //out_2 = new ObjectOutputStream(clientSocket.getOutputStream());
                // читать соообщения с сервера
                //out_2.writeObject(black.getChessBoardSquares());
                black.drawButton(black.getChessBoardSquares());
                //black.getGui().
                //black.getGui().
                for (int ii = 0; ii < 8; ii++){
                    for (int jj = 0; jj < 8; jj++) {
                        System.out.println("8");
                        if(black.getChessBoardSquares()[ii][jj].getFigure()>0)
                            black.getChessBoardSquares()[ii][jj].setIcon(new ImageIcon(black.chessPieceImages[WHITE][black.getChessBoardSquares()[ii][jj].getFigure()-1]));
                        else if(black.getChessBoardSquares()[ii][jj].getFigure()<0)
                            black.getChessBoardSquares()[ii][jj].setIcon(new ImageIcon(black.chessPieceImages[BLACK+1][-1*(black.getChessBoardSquares()[ii][jj].getFigure())-1]));
                        else
                            black.getChessBoardSquares()[ii][jj].setIcon(null);
                    }
                    System.out.println("9");
                }
                for (int ii = 0; ii < 8; ii++){
                    for (int jj = 0; jj < 8; jj++) {
                        System.out.print(black.getChessBoardSquares()[ii][jj].getStringFigure()+"\t");
                    }
                    System.out.println("\n");
                }

                // white= (ChessGUI) in_2.readObject();

                // писать туда же
            }

            // white= (ChessGUI) in_2.readObject();

            // писать туда же



             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
            } catch (InterruptedException e) {
                 e.printStackTrace();
             } finally { // в любом случае необходимо закрыть сокет и потоки
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                //in.close();
                //out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        System.out.println("6");
black.drawButton(black.getChessBoardSquares());
        System.out.println("6");
    }
}
