import volosatov.Phone;

import java.io.IOException;
import java.net.ServerSocket;
// 8  import java.net.Socket;
//import language-level

public class Server {
    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(8000))
        {
            System.out.println("Server started!");
// Многопоточность 11
            while (true) {

                Phone phone = new Phone(server);
// 14                try (Phone phone = new Phone(server)) {

                new Thread(() -> {
                    String request = phone.readLine();
                    System.out.println("Request: " + request);
                    String response = (int) (Math.random() * 30 - 10) + " ";
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                    }
                    phone.writeLine(response);
                    System.out.println("Response: " + response);
                    try {
                        phone.close();
                    } catch (IOException e) {
                    }
                }).start();
// 6                       Socket socket = server.accept();
                // 0           System.out.println("Client connected!");
// 6
                // 0       OutputStream stream = socket.getOutputStream(); // Поток
                // 0       OutputStreamWriter streamWriter = new OutputStreamWriter(stream);  // Поток над Потоком
// 6
// 6                       BufferedWriter writer =
// 6                           new BufferedWriter(
// 6                                   new OutputStreamWriter(
// 6                                           socket.getOutputStream()));
// 6
//  6                      BufferedReader reader =
//  6                          new BufferedReader(
//  6                              new InputStreamReader(
//  6                                  socket.getInputStream()
//  6                              )
//  6                          );
//  6              )
// 6               {
// 13                    String request = phone.readLine();
// 13                    System.out.println("Request: " + request);
                // 1               writer.write("HELLO FROM SERVER: " + request.length());
// 3                   String response = ("HELLO FROM SERVER: " + request.length());
// 13                    String response = (int)(Math.random() * 30 - 10) + " ";
// 13                    Thread.sleep(4000);
// 13                    phone.writeLine(response);
// 13                    System.out.println("Response: " + response);
// 7                   writer.write(response);
// 7                   writer.newLine();
// 7                   writer.flush();

                // 1               writer.close();
                // 1               socket.close();
// 15                } catch(NullPointerException e) {  // (IOException)
// 15                    e.printStackTrace();  // throw new RuntimeException(e);
// 15                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }



// 1       server.close();
    }
}
