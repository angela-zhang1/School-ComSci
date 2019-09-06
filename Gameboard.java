import java.awt.*;
import javax.swing.*;
public class GameBoard extends JFrame{
	private static final int ROWS = 8, COLS = 8;
	//you'll need a 2d array
	private static Square[][] arr;
	//you'll need variables to keep track of the 1st and 2nd squares that were clicked
	private static Square squareone;
	private static Square squaretwo;
	private static boolean nextColor = false;
	public GameBoard(){
		super("CHESS");
		boolean x;
  
		this.setLayout(new GridLayout(ROWS,COLS));
		//be sure to instantiate the array
		arr = new Square[ROWS][COLS];
		//now you'll need to birth each element of the array AND add it to the Frame 
		for (int i = 0; i < ROWS; i++) {
			if (i%2 == 0)
				x = true;
			else
				x = false;
			for (int j = 0; j < COLS; j++) {
				if (x) {
					if (j%2 == 0) {
						arr[i][j] = new Square (i,j,this,false);
					}
					else {
						arr[i][j] = new Square (i,j,this,true);
					}
				}
				else {
					if (j%2 == 0) {
						arr[i][j] = new Square (i,j,this,true);
					}
					else {
						arr[i][j] = new Square (i,j,this,false);
					}
				}
			}
		}
  
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				this.add(arr[i][j]);
				if (i == 6) {
					ChessPiece cp = new Pawn("./whitepawn.png", false, arr[i][j]);
					arr[i][j].setPiece(cp);
				}
				if (i == 1) {
					ChessPiece cp = new Pawn("./blackpawn.png", true, arr[i][j]);
					arr[i][j].setPiece(cp);
				}
			}
		}
		//black
		arr[0][0].setPiece(new Rook("./blackrook.png", true, arr[0][0]));
		arr[0][7].setPiece(new Rook("./blackrook.png", true, arr[0][7]));
		arr[0][1].setPiece(new Knight("./blackknight.png", true, arr[0][1]));
		arr[0][6].setPiece(new Knight("./blackknight.png", true, arr[0][6]));
		arr[0][2].setPiece(new Bishop("./blackbishop.png", true, arr[0][2]));
		arr[0][5].setPiece(new Bishop("./blackbishop.png", true, arr[0][5]));
		arr[0][3].setPiece(new Queen("./blackqueen.png", true, arr[0][3]));
		arr[0][4].setPiece(new King("./blackking.png", true, arr[0][4]));
		
		//white
		arr[7][0].setPiece(new Rook("./whiterook.png", false, arr[7][0]));
		arr[7][7].setPiece(new Rook("./whiterook.png", false, arr[7][7]));
		arr[7][1].setPiece(new Knight("./whiteknight.png", false, arr[7][1]));
		arr[7][6].setPiece(new Knight("./whiteknight.png", false, arr[7][6]));
		arr[7][2].setPiece(new Bishop("./whitebishop.png", false, arr[7][2]));
		arr[7][5].setPiece(new Bishop("./whitebishop.png", false, arr[7][5]));
		arr[7][3].setPiece(new Queen("./whitequeen.png", false, arr[7][3]));
		arr[7][4].setPiece(new King("./whiteking.png", false, arr[7][4]));
		//some finishing touches
		this.setSize(750,750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
 
	//one of the squares will call this function to tell the board it was clicked
	public void clicked(Square whoGotClicked){
		/*if (squareone == null) {
			JOptionPane.showMessageDialog(this, "You clicked at "+((char)(whoGotClicked.getCol() + 97)) + (whoGotClicked.getRow()) + ".");
			//if (squareone.getPiece() != null) {
				squareone = whoGotClicked;
			//}
		}
		else if (squareone != null) {
			JOptionPane.showMessageDialog(this, "Moved from "+((char)(squareone.getCol() + 97)) + (squareone.getRow())  + " to " +((char)(whoGotClicked.getCol() + 97)) + (whoGotClicked.getRow()) + "." );
			squareone = null;
		} 
		*/
  
		if (squaretwo == null) {
			JOptionPane.showMessageDialog(this, "You clicked at "+((char)(whoGotClicked.getCol() + 97)) + (8-whoGotClicked.getRow()) + ".");
			if (whoGotClicked.getPiece()!=null && whoGotClicked.getPiece().getColor() == nextColor) {
				squaretwo = whoGotClicked;
				ChessPiece pie = whoGotClicked.getPiece();
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						if (pie.isMoveLegal(this.getSquare(i, j))) {
							this.getSquare(i,j).setHighlight(true);
						}
					}
				}
			}
		}
		else {
			if (squaretwo.getPiece().getColor() == nextColor) {
				JOptionPane.showMessageDialog(this, "Moved from "+((char)(squaretwo.getCol() + 97)) + (8-squaretwo.getRow())  + " to " +((char)(whoGotClicked.getCol() + 97)) + (8-whoGotClicked.getRow()) + "." );
				squaretwo.getPiece().move(whoGotClicked);
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						this.getSquare(i,j).setHighlight(false);
					}
				}
				nextColor = !(nextColor);
				squaretwo = null;
			}
			else {
				JOptionPane.showMessageDialog(this, "You clicked at "+((char)(whoGotClicked.getCol() + 97)) + (8-whoGotClicked.getRow()) + ".");
			}
		}
   
   
  
	} 
 
	public static Square getSquare (int row,int col) {
		return arr[row][col];
	}
	//lame main
	public static void main(String[] args) {
		new GameBoard();
	}
}