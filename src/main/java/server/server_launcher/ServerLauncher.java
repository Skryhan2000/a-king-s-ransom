package server.server_launcher;

public class ServerLauncher {
    public static void main(String[] args) {
        Server server = new Server(8000);
        server.consoleStart();
        while (true){
            if(server.applyConnection()==1)
                break;
        }

    }
}
