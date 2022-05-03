import javax.swing.*;
import java.io.Serializable;

public class ChessButton extends JButton implements Serializable {
    ChessButton(int x , int y){
        this.coorX=x;
        this.coorY=y;
    }
    ChessButton(){}
    public int getFigure() {
        return figure;
    }
    public String getStringFigure() {
        String s = Integer.toString(figure);
        return s;
    }
    private static final long serialVersionUID = 1L;
    public void setFigure(int figure) {

        this.figure= figure;
    }

    public int getCoorX() {
        return coorX;
    }

    public void setCoorX(int coorX) {
        this.coorX = coorX;
    }

    public int getCoorY() {
        return coorY;
    }

    public void setCoorY(int coorY) {
        this.coorY = coorY;
    }

    private int coorX;
    private int coorY;
    private int figure;

}
