package othello.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MessageReciever extends Thread {

	private Socket socket;
	private Othello othello;
	private int myNumber;

	public MessageReciever(Socket socket, Othello othello) {
		this.socket = socket;
		this.othello = othello;
	}

	public void run() {
		Scanner sc = null;
		try {
			sc = new Scanner(socket.getInputStream());
			this.myNumber = sc.nextInt();
			System.out.println("myNumber: " + myNumber);
			othello.setMyColor(myNumber);
			while (sc.hasNextLine()) {
				String cmd = sc.next();
				if (cmd.equals("CLICK")) {
					// CLICKの時の処理(コマ置いたの処理)
					int x = sc.nextInt(); // ボタンのx座標
					int y = sc.nextInt(); // ボタンのy座標
					int color = sc.nextInt();
					othello.clickButton(x, y, color);
				} else {
					System.out.println(cmd + sc.nextLine()); // デバッグ用
				}
			}
		} catch (IOException e) {
			System.err.println("エラーが発生しました: " + e);
		} finally {
			if (sc != null) {
				sc.close();
			}
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("エラーが発生しました: " + e);
			}
		}
	}

}
