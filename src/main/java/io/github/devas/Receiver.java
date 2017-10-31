package io.github.devas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class Receiver {

    private boolean isRunning = true;

    Receiver(final int listeningPort) throws IOException {

        Runnable receiverT = new Runnable() {
            public void run() {
                ServerSocket serverSocket;
                try {
                    serverSocket = new ServerSocket(listeningPort);
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    while (isRunning) {
                        try {
                            System.out.println(in.readLine());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        new Thread(receiverT).start();
    }

    void stop() {
        isRunning = false;
    }
}