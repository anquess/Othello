package othello.client;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class XYCoordinate {
	private int xCoordinate;
	private int yCoordinate;

	public XYCoordinate(int x,int y){
		if(x < 0)throw new IllegalArgumentException("x < 0");
		if(y < 0)throw new IllegalArgumentException("y < 0");
		if(x > 7)throw new IllegalArgumentException("x > 7");
		if(y > 7)throw new IllegalArgumentException("y > 7");

		this.xCoordinate = x;
		this.yCoordinate = y;
	}
	public XYCoordinate(MouseEvent e){
		this( (JButton)e.getComponent());
	}
	public XYCoordinate(JButton theButton){
		this(Integer.parseInt(theButton.getActionCommand())%8,Integer.parseInt(theButton.getActionCommand())/8);
	}
	public int getxCoordinate() {
		return xCoordinate;
	}
	public int getyCoordinate() {
		return yCoordinate;
	}


}
