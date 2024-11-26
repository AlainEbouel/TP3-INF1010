package server;


import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class RmiServer {

    public static void main(String[] args)  {
        startRMI();
        try {
            ActiveDirectory AD = new ActiveDirectory();
            IDirectory reference = (IDirectory) UnicastRemoteObject.exportObject(AD, 0);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("ActiveDirectory", reference);
            System.out.println("Server is ready...");

        } catch (RemoteException e) {
            System.out.println("RemoteException in Directory constructor ");
            throw new RuntimeException(e);
        }
    }
    private static void startRMI(){
        String winCmd1 =  "cd src\\main\\java && javac -d bin " +
                "server\\IDirectory.java server\\ActiveDirectory.java server\\DatabaseClient.java"; ;
        String winCmd2 = "cd src\\main\\java\\bin && start rmiregistry";
        String linuxCmd1 = "cd src/main/java && javac -d bin server/*.java";
        String linuxCmd2 = "cd src/main/java/bin &&  rmiregistry ";

        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        if (isWindows) {
            runShellCmd(winCmd1);
            runShellCmd(winCmd2);
        } else {
            runShellCmd(linuxCmd1);
            runShellCmd(linuxCmd2);
        }
    }

    private static void runShellCmd(String cmd) {
        ProcessBuilder pb = new ProcessBuilder();
        boolean isWindows = System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
        if (isWindows) {
            pb.command("cmd.exe", "/c", cmd);
        } else {
            pb.command("sh", "-c", cmd);
        }
        try {
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor(1, TimeUnit.SECONDS);

        } catch (IOException | InterruptedException e) {
            // recover / etc
        }
    }

}