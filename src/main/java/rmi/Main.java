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
        runShellCmd("cd src/main/java && javac -d classDir rmi/*.java");
        runShellCmd("cd src/main/java/classDir &&  rmiregistry ");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/university", "root", "123456");
            Statement statement = connection.createStatement();

        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);}
    }

    private static void runShellCmd(String cmd) throws InterruptedException {
        ProcessBuilder builder = new ProcessBuilder();
//        if (isWindows) {
//            builder.command("cmd.exe", "/c", "dir");
//        } else {
//            builder.command("sh", "-c", "ls");
//        }


        try {
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor(10, TimeUnit.SECONDS);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            )) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }
                if (process.exitValue() != 0) {
                }
                System.out.println(stringBuilder.toString());
            }
        } catch (IOException e) {
            // recover / etc
        }
    }


}
