package othello.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class OthelloServer {

	private static final int defaultPort = 10000;
	private int port;
	private List<ClientProcThread> incoming;

	public OthelloServer() {
		this(defaultPort);
	}

	public OthelloServer(int port) {
		this.port = port;
	}

	public void sendAll(String msg) {
		for (ClientProcThread client: incoming) {
			client.sendMessage(msg);
		}
	}

	public void removeClient(ClientProcThread client) {
		incoming.remove(client);
	}

	public void doService() {

		ServerSocket server = null;
		incoming = new ArrayList<ClientProcThread>();
		int n = 0;

		try {
			server = new ServerSocket(port);
			while (true) {
				try {
					Socket socket = server.accept();
					n++;
					System.out.println("Accept client No." + n);
					ClientProcThread thread = new ClientProcThread(n, socket, this);
					incoming.add(thread);
					thread.start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] args) {
		OthelloServer ms;
		if (args.length < 1) {
			ms = new OthelloServer();
		} else {
			ms = new OthelloServer(Integer.parseInt(args[0]));
		}
		ms.doService();
	}

}
