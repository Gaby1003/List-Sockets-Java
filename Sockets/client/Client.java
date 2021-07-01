package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import java.util.Scanner;

public class Client {

	private Socket socket;
	private DataInputStream dataInput;
	private DataOutputStream dataOutput;
	private Scanner scanner;

	public Client() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 3000);
		Logger.getGlobal().info("Nueva conexión");
		dataInput = new DataInputStream(socket.getInputStream());
		dataOutput = new DataOutputStream(socket.getOutputStream());
		scanner = new Scanner(System.in);
		System.out.println("Ingrese la posición a consultar");
		dataOutput.writeInt(scanner.nextInt());
		System.out.println(dataInput.readUTF());
	}

	public static void main(String[] args) {
		try {
			new Client();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
