package othello.client;

import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class XYPointer {
	private int xPointer;
	private int yPointer;

	public XYPointer(int x,int y){
		if(x < 0)throw new IllegalArgumentException("x < 0");
		if(y < 0)throw new IllegalArgumentException("y < 0");
		if(x > 7)throw new IllegalArgumentException("x > 7");
		if(y > 7)throw new IllegalArgumentException("y > 7");

		this.xPointer = x;
		this.yPointer = y;
	}
	public XYPointer(MouseEvent e){
		this( (JButton)e.getComponent());
	}
	public XYPointer(JButton theButton){
		this(Integer.parseInt(theButton.getActionCommand())%8,Integer.parseInt(theButton.getActionCommand())/8);
	}
	public int getxPointer() {
		return xPointer;
	}
	public int getyPointer() {
		return yPointer;
	}


}
