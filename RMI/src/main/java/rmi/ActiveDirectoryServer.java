package rmi;


import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class ActiveDirectory implements IDirectory {
    public ActiveDirectory() throws RemoteException {
//        super();
//        try {
//            this.url = new URL("http://localhost");
//        } catch (MalformedURLException e) {
//            System.out.println("---------------Malformed URL----------------");
//            throw new RuntimeException(e);
//        }
        ;
    }

    //    @Override
    public int nbrStudent() throws RemoteException {
        return 100;
    }
    //
//    @Override
//    public ObservableList<Student> getAllStudents() throws RemoteException {
//        return null;
//    }
//
//    @Override
//    public ObservableList<Professor> getAllProfessors() throws RemoteException {
//        return null;
//    }
//    private  URL url ;
////    url = new URL("http://localhost");
//
//    public URL getUrl() {
//        return url;
//    }
//
//    BufferedReader bufferedReader;
//
//    {
//        try {
//            bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
//        } catch (IOException e) {
//            System.out.println("---------------Error in reading file--------------");
//            throw new RuntimeException(e);
//        }
//    }
    public static void main(String[] args) {
//        startRmiRegistry();
        try {
            ActiveDirectory rmi = new ActiveDirectory();
            IDirectory ref = (IDirectory) UnicastRemoteObject.exportObject((IDirectory)rmi, 0);
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("Members", ref);
            System.out.println("Server is ready...");
        } catch (RemoteException e) {
            System.out.println("RemoteException in Directory constructor ");
        }



    }
//    private static void startRmiRegistry() throws IOException, InterruptedException {
//        ProcessBuilder builder = new ProcessBuilder();
////        if (isWindows) {
////            builder.command("cmd.exe", "/c", "dir");
////        } else {
////            builder.command("sh", "-c", "ls");
////        }
////        String cmd = "cd src/main/java &&  java -classpath classDir -Djava.rmi.server.codebase=file:classDir/ rmi/Members.java & ";
//
//        try {
//            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
//            pb.redirectErrorStream(true);
//            Process process = pb.start();
//            process.waitFor(10, TimeUnit.SECONDS);
//            try (BufferedReader br = new BufferedReader(
//                    new InputStreamReader(process.getInputStream())
//            )) {
//                StringBuilder stringBuilder = new StringBuilder();
//                String line;
//                while ((line = br.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//                if (process.exitValue() != 0) {
//                }
//                System.out.println(stringBuilder.toString());
//            }
//        } catch (IOException e) {
//            // recover / etc
//        }
//    }
}
