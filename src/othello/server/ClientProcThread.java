package othello.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientProcThread extends Thread {

	private int number;
	private Socket socket;
	private PrintWriter sout;
	private OthelloServer server;

	public ClientProcThread(int number, Socket socket, OthelloServer server) throws IOException {
		this.number = number;
		this.socket = socket;
		this.server = server;
		sout = new PrintWriter(socket.getOutputStream());
	}

	public void run() {
		Scanner sin = null;
		try {
			sin = new Scanner(socket.getInputStream());
			sout.println(number);
			sout.flush();
			while (sin.hasNextLine()) {
				String msg = sin.nextLine();
				System.out.println("Received from client No." + number + ", Messages:" + msg);
				server.sendAll(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sin != null) sin.close();
			sout.close();
			System.out.println("Connection removed client No." + number);
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("エラーが発生しました: " + e);
			}
			server.removeClient(this);
		}
	}

	public void sendMessage(String msg) {
		sout.println(msg);
		sout.flush();
		System.out.println("Send messages to client No." + number);
	}

}
