package othello.client;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Othello extends JFrame implements MouseListener, MouseMotionListener {

	private static final String defaultServer = "localhost";
	private static final int defaultPort = 10000;

	// アイコンの設定
	private static final ImageIcon whiteIcon = new ImageIcon("White.jpg");
	private static final ImageIcon blackIcon = new ImageIcon("Black.jpg");
	private static final ImageIcon boardIcon = new ImageIcon("GreenFrame.jpg");

	private static final int BLACK = 0;
	private static final int WHITE = 1;


	private static final int UP 			= 0;
	private static final int UPRIGHT 	= 1;
	private static final int RIGHT 		= 2;
	private static final int DOWNRIGHT 	= 3;
	private static final int DOWN 		= 4;
	private static final int DOWNLEFT 	= 5;
	private static final int LEFT	 	= 6;
	private static final int UPLEFT	 	= 7;


	private Container c;
	private JButton[][] buttonArray;
	private int myColor;
	private boolean myTurn;

	private PrintWriter sout;

	public Othello(String name, String server, int port) {
		//ウィンドウを作成する
		createWindow(name);

		// ボタンの生成
		createButton();

		// サーバに接続する
		conectServer(server, port);

	}

	private void conectServer(String server, int port) {
		Socket socket = null;
		try {
			socket = new Socket(server, port);
			sout = new PrintWriter(socket.getOutputStream());
			MessageReciever omr = new MessageReciever(socket, this); // 受信用のスレッドを作成する
			omr.start(); // スレッドを動かす（Runが動く）
		} catch (UnknownHostException e) {
			System.err.println("ホストの IP アドレスが判定できません: " + e);
		} catch (IOException e) {
			 System.err.println("エラーが発生しました: " + e);
		}
	}

	private void createButton() {
		buttonArray = new JButton[8][8]; // ボタンの配列を５個作成する[0]から[4]まで使える
		for(int x=0;x<8;x++){
			for(int y=0;y<8;y++){
				buttonArray[x][y] = new JButton(boardIcon); // ボタンにアイコンを設定する
				c.add(buttonArray[x][y]); // ペインに貼り付ける
				buttonArray[x][y].setBounds(x*45,y*45,45,45); // ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
				buttonArray[x][y].addMouseListener(this); // ボタンをマウスでさわったときに反応するようにする
				buttonArray[x][y].addMouseMotionListener(this); // ボタンをマウスで動かそうとしたときに反応するようにする
				buttonArray[x][y].setActionCommand(Integer.toString(y*8+x)); // ボタンに配列の情報を付加する（ネットワークを介してオブジェクトを識別するため）
			}
		}
		clickButton(this.buttonArray[3][3],BLACK);
		clickButton(this.buttonArray[3][4],WHITE);
		clickButton(this.buttonArray[4][4],BLACK);
		clickButton(this.buttonArray[4][3],WHITE);
	}

	private void createWindow(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ウィンドウを閉じるときに，正しく閉じるように設定する
		setTitle("Othello: " + name); // ウィンドウのタイトルを設定する
		setSize(400,420); // ウィンドウのサイズを設定する
		c = getContentPane(); // フレームのペインを取得する
		c.setLayout(null); // 自動レイアウトの設定を行わない
	}

	public Othello(String name, String server) {
		this(name, server, defaultPort);
	}

	public Othello(String name, int port) {
		this(name, defaultServer, port);
	}

	public Othello(String name) {
		this(name, defaultServer, defaultPort);
	}

	public void setMyColor(int myNumber) {
		this.myColor = myNumber%2;
		this.myTurn = (this.myColor == BLACK);
	}

	public void clickButton(int x, int y, int color) {
		clickButton(this.buttonArray[x][y],color);
	}

	public void clickButton(JButton theButton, int color) {
		if (color == BLACK) { // アイコンがwhiteIconと同じなら
			theButton.setIcon(blackIcon); // blackIconに設定する
		} else {
			theButton.setIcon(whiteIcon); // whiteIconに設定する
		}
		this.myTurn = (color != this.myColor);
	}


	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if(myTurn){
			JButton theButton = (JButton)e.getComponent(); // クリックしたオブジェクトを得る．型が違うのでキャストする
			if(canIClicButton(theButton)){

				clickButton(theButton, this.myColor);

				sendClicInfomation(theButton);

				repaint(); //オブジェクトの再描画を行う
			}
		}
	}

	private boolean canIClicButton(JButton theButton){
		return isThisButtonGreen(theButton);
	}

	private boolean isThisButtonGreen(JButton theButton){
		return theButton.getIcon() == boardIcon;
	}



/*	private boolean checkLine(JButton theButton,int color,int direction){
		switch (direction) {
		case UP:


			break;

		default:
			break;
		}
	}
*/
	private void sendClicInfomation(JButton theButton) {
		XYPointer Pointer = new XYPointer(theButton);
		//送信情報を作成する（受信時には，この送った順番にデータを取り出す．スペースがデータの区切りとなる）
		String msg = "CLICK" + " " + Pointer.getxPointer() + " " + Pointer.getyPointer() + " " + this.myColor;

		//サーバに情報を送る
		sout.println(msg); //送信データをバッファに書き出す
		sout.flush(); //送信データをフラッシュ（ネットワーク上にはき出す）する
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public static void main(String[] args) {
		Othello othello;
		if (args.length == 3) {
			othello = new Othello(args[0], args[1], Integer.parseInt(args[2]));
		} else if (args.length == 2) {
			othello = new Othello(args[0], Integer.parseInt(args[1]));
		} else {
			othello = new Othello(args[0]);
		}
		othello.setVisible(true);
	}

}
