package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Server implements Runnable{

	private ArrayList<String> data;
	private ServerSocket server;
	private DataInputStream dataInput;
	private DataOutputStream dataOutput;
	private Thread thread;

	public Server() throws IOException {
		data = new ArrayList<>();
		server = new ServerSocket(3000);
		fillArray();
		Logger.getGlobal().info("Server run on 3000...");
		thread = new Thread(this);
		thread.start();
	}


	private void fillArray() {
		data.add("Jhon");
		data.add("Juan");
		data.add("Duvan");
		data.add("Sebasti�n");
		data.add("Nicol�s");
		data.add("Laura");
	}

	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			Socket connection;
			connection = server.accept();
			Logger.getGlobal().info("Nueva conexi�n");
			dataInput = new DataInputStream(connection.getInputStream());
			dataOutput = new DataOutputStream(connection.getOutputStream());
			int position = dataInput.readInt();
			if (position <= data.size() && position >= 0) {
				dataOutput.writeUTF(data.get(position));
			} else {
				dataOutput.writeUTF("La posici�n ingresada no es v�lida");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
