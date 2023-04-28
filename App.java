package sg.edu.nus.iss;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws IOException {
        // take in arguments
        if (args.length == 3) {

            // String serverName = args[0];
            Integer port = Integer.parseInt(args[1]);
            String file = args[2];

            // open cookie file
            Cookie cookie = new Cookie();
            cookie.openFile(file);

            String msgReceived = "";

            // open server connection
            ServerSocket server = new ServerSocket(port);
            Socket socket = server.accept();

            try (InputStream is = socket.getInputStream()) {
                BufferedInputStream bis = new BufferedInputStream(is);
                DataInputStream dis = new DataInputStream(bis);

                try (OutputStream os = socket.getOutputStream()) {
                    BufferedOutputStream bos = new BufferedOutputStream(os);
                    DataOutputStream dos = new DataOutputStream(bos);

                    while (!msgReceived.equals("close")) {
                        msgReceived = dis.readUTF();

                        if (msgReceived.equals("get-cookie")) {
                            dos.writeUTF("cookie-text");
                            dos.writeUTF(cookie.getCookie());
                            dos.flush();
                        }

                    }

                    dos.close();
                    bos.close();
                    os.close();

                } catch (EOFException e) {
                }

                dis.close();
                bis.close();
                is.close();

            } catch (EOFException e) {
            }

            socket.close();
            server.close();
        }
    }
}
