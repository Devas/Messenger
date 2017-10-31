package io.github.devas;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Sender {

    private boolean isRunning = true;
    private volatile List<String> msgs;

    Sender(final String username, final String targetIP, final int targetPort) throws IOException {
        msgs = Collections.synchronizedList(new ArrayList<String>());

        Runnable senderT = new Runnable() {
            public void run() {
                try {
                    Socket socket = new Socket(targetIP, targetPort);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                    while (isRunning) {
                        synchronized (msgs) {
                            Iterator<String> it = msgs.iterator();

                            while (it.hasNext()) {
                                out.println(username + ": " + it.next());
                            }

                            // Clear messages to send
                            msgs.clear();
                        }
                    }

                    out.close();
                    socket.close();
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        };

        new Thread(senderT).start();
    }

    void stop() {
        isRunning = false;
    }

    void sendMessage(String msg) {
        synchronized (msgs) {
            msgs.add(msg);
        }
    }
}