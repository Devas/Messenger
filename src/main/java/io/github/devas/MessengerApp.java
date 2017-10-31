package io.github.devas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

class MessengerApp {

    private boolean isRunning = true;
    private Sender sender;
    private Receiver receiver;
    private Configuration conf = new Configuration(7777, "Anakin", "localhost", 8888);

    void startMessenger() throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Listening port: ");
        conf.setListeningPort(sc.nextInt());
        receiver = new Receiver(conf.getListeningPort());

        System.out.print("Target port: ");
        conf.setTargetPort(sc.nextInt());
        sender = new Sender(conf.getUserName(), conf.getTargetIp(), conf.getTargetPort());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Chat started. Type '\\exit' to quit.");

        while (isRunning) {
            String input = br.readLine();
            if (input.equals("\\exit")) {
                receiver.stop();
                sender.stop();
                isRunning = false;
            } else {
                sender.sendMessage(input);
            }
        }
    }
}
