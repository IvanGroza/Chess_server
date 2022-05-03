import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.io.Serializable;
import java.net.URL;
import javax.imageio.ImageIO;

public class ChessGUI implements Serializable {


    public ChessButton[][] getChessBoardSquares() {
        return chessBoardSquares;
    }

    public void setChessBoardSquares(ChessButton[][] chessBoardSquares) {
        this.chessBoardSquares = chessBoardSquares;
    }

    public final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private  ChessButton[][] chessBoardSquares = new ChessButton[8][8];
    public  Image[][] chessPieceImages = new Image[2][6];
    public JPanel chessBoard;

    private final JLabel message = new JLabel(
            "Chess Champ is ready to play!");
    private static final String COLS = "ABCDEFGH";
    public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    public static final int[] STARTING_ROW = {
            ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    public static final int BLACK = -1, WHITE = 1;

    ChessGUI(int color,String s) {
        initializeGui(color);

        JFrame f = new JFrame(s);
        f.add(getGui());
        // Ensures JVM closes after frame(s) closed and
        // all non-daemon threads are finished
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // See https://stackoverflow.com/a/7143398/418556 for demo.
        f.setLocationByPlatform(true);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);

    }

    public final void initializeGui(int color) {
        // create the images for the chess pieces
        createImages();

        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
       gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New") {

            @Override
            public void actionPerformed(ActionEvent e) {
                drawButton(chessBoardSquares);
            }
        };
        class MyActionListener implements ActionListener {
            public static int count =0;
            public static int WAR =0;
            public static ChessButton but;
            public static ChessButton but2;
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    WAR=1;
                    but = new ChessButton();
                    but = (ChessButton) e.getSource();


                    if(color==1) {
                        if(but.getFigure()>0)
                            count=1;
                    }
                    else if(color==2) {
                        if(but.getFigure()<0)
                            count=1;
                    }
                    //if(but.getPressedIcon()==chessPieceImages[0][0])
                }
                else if (count == 1) {
                    but2 = new ChessButton();
                    but2 = (ChessButton) e.getSource();
                    //if(color==1) {
                        but2.setFigure(but.getFigure());
                        but.setFigure(0);
                        count=0;
                        WAR=1;
//                        for (int ii = 0; ii < 8; ii++){
//                            for (int jj = 0; jj < 8; jj++) {
//                                System.out.print(chessBoardSquares[ii][jj].getStringFigure()+"\t");
//                            }
//                            System.out.println("\n");
//                        }
//                        System.out.println("--------------------------------");
                        drawButton(chessBoardSquares);
                   // }


                }


            }
        };

        tools.add(newGameAction);
        tools.add(new JButton("Save")); // TODO - add functionality!
        tools.add(new JButton("Restore")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9)) {


            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
        ));
        // Set the BG to be ochre
        Color ochre = new Color(204,119,34);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(chessBoard);

        gui.add(boardConstrain);






        chessSquares();
        setupNewGame();
        //drawButton(chessBoardSquares);
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
             }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
               switch (jj) {
                   case 0:
                        chessBoard.add(new JLabel("" + (9-(ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        if(color==1)
                        chessBoard.add(chessBoardSquares[jj][ii]);
                        if(color==2)
                            chessBoard.add(chessBoardSquares[7-jj][7-ii]);
              }
            }
        }
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                int count = 0;
                ActionListener listener= new MyActionListener();
                chessBoardSquares[ii][jj].addActionListener(listener);
            }
        }
//        for (int ii = 7; ii < -1; ii--) {
//            for (int jj = 7; jj < -1; jj--) {
//                switch (jj) {
//                    case 0:
//                        chessBoard.add(new JLabel("" + (9-(ii + 1)),
//                                SwingConstants.CENTER));
//                    default:
//                        chessBoard.add(chessBoardSquares[jj][ii]);
//                }
//            }
//        }
    }
    public synchronized ChessButton[][] drawButton(ChessButton[][] chessButtons)
    {
        for (int ii = 0; ii < 8; ii++){
            for (int jj = 0; jj < 8; jj++) {
                if(chessBoardSquares[ii][jj].getFigure()>0)
                    chessBoardSquares[ii][jj].setIcon(new ImageIcon(chessPieceImages[WHITE][chessBoardSquares[ii][jj].getFigure()-1]));
                else if(chessBoardSquares[ii][jj].getFigure()<0)
                    chessBoardSquares[ii][jj].setIcon(new ImageIcon(chessPieceImages[BLACK+1][-1*(chessBoardSquares[ii][jj].getFigure())-1]));
                else
                    chessBoardSquares[ii][jj].setIcon(null);
            }

        }
        return chessButtons;
    }
    public  void chessSquares() {
        //Insets buttonMargin = new Insets(0, 0, 0, 0);

            for (int ii = 0; ii < chessBoardSquares.length; ii++) {
                for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                    chessBoardSquares[jj][ii]= new ChessButton(jj,ii);

                   // b.setMargin(buttonMargin);

                    // our chess pieces are 64x64 px in size, so we'll
                    // 'fill this in' using a transparent icon..
                    ImageIcon icon = new ImageIcon(
                            new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                    chessBoardSquares[jj][ii].setIcon(icon);
                    if ((jj % 2 == 1 && ii % 2 == 1)
                            //) {
                            || (jj % 2 == 0 && ii % 2 == 0)) {
                        chessBoardSquares[jj][ii].setBackground(Color.WHITE);
                    } else {
                        chessBoardSquares[jj][ii].setBackground(Color.BLACK);
                    }
                }
            }


    }
        public final JComponent getGui () {
            return gui;
        }

    public final void createImages() {
        try {
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImages[ii][jj] = bi.getSubimage(
                            jj * 64, ii * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }





    public final void setupNewGame() {


        message.setText("Make your move!");
        // set up the black pieces
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][0].setIcon(new ImageIcon(
                    chessPieceImages[BLACK+1][STARTING_ROW[ii]]));

            chessBoardSquares[ii][0].setFigure(BLACK*(STARTING_ROW[ii]+1));
        }
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][1].setIcon(new ImageIcon(
                    chessPieceImages[BLACK+1][PAWN]));
            chessBoardSquares[ii][1].setFigure(BLACK*(PAWN+1));
        }
        // set up the white pieces
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][6].setIcon(new ImageIcon(
                    chessPieceImages[WHITE][PAWN]));
            chessBoardSquares[ii][6].setFigure(WHITE*(PAWN+1));
        }
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][7].setIcon(new ImageIcon(
                    chessPieceImages[WHITE][STARTING_ROW[ii]]));
            chessBoardSquares[ii][7].setFigure(WHITE*(STARTING_ROW[ii]+1));
        }
        for (int ii = 0; ii < 8; ii++){
            for (int jj = 0; jj < 8; jj++) {
                System.out.print(chessBoardSquares[ii][jj].getStringFigure()+"\t");
            }
            System.out.println("\n");
        }
        }

}
