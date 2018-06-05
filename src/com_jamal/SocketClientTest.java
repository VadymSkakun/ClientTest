package com_jamal;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientTest {

    public static void main(String[] args) {
        int serverPort = 8017;
        String address = "127.0.0.1";
        System.out.println("Trying to connect socket with IP address " + address + " and port " + serverPort);

        try {
            InetAddress inetAddress = InetAddress.getByName(address);
            Socket socket = new Socket(inetAddress, serverPort);
            System.out.println("Connected to server!");

            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(socketInputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(socketOutputStream);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;

            System.out.println("Ready to send a message");

            while (true) {
                line = reader.readLine();
                System.out.println("Sending...");
                dataOutputStream.writeUTF(line);
                dataOutputStream.flush();

                line = dataInputStream.readUTF();
                System.out.println("[Server] " + line);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
