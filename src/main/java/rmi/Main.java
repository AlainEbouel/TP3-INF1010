package rmi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        runShellCmd("cd src/main/java && javac -d classDir rmi/*.java");
//        runShellCmd("cd src/main/java/classDir &&  rmiregistry ");
        String winCmd1 =  "cd src\\main\\java && javac -d classDir " +
                "rmi\\IDirectory.java rmi\\ActiveDirectoryServer.java rmi\\DatabaseClient.java"; ;
        String winCmd2 = "cd src\\main\\java\\classDir && start rmiregistry";
        String linuxCmd1 = "cd src/main/java && javac -d classDir rmi/*.java";
        String linuxCmd2 = "cd src/main/java/classDir &&  rmiregistry ";


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
//            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor(10, TimeUnit.SECONDS);

        } catch (IOException | InterruptedException e) {
            // recover / etc
        }
    }


}
