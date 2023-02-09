
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
public class Run {

    public static void main(String[] args) throws IOException {

        String directory = "C:\\Users\\Dell\\Desktop\\compilation";
        String compileCommand = "gcc code.c -o code.exe";
        String executeCommand = "code.exe";

        // Change current working directory
        Runtime runtime = Runtime.getRuntime();
        Process process1 = runtime.exec("cmd /c cd " + directory);
        // Compile program
        Process process2 = runtime.exec("cmd /c "+compileCommand);

        try{
            int exitCode = process2.waitFor();
        }catch (Exception e){

        }
        Process process = runtime.exec("cmd /c "+executeCommand);
        BufferedReader stdOut = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8));

        String s;
        while ((s = stdOut.readLine()) != null) {
            System.out.println(s);
        }

        while ((s = stdError.readLine()) != null) {
            System.err.println(s);
        }

        BufferedReader stdOut2 = new BufferedReader(new InputStreamReader(process2.getInputStream(), StandardCharsets.UTF_8));
        BufferedReader stdError2 = new BufferedReader(new InputStreamReader(process2.getErrorStream(), StandardCharsets.UTF_8));

        String s2;
        while ((s2 = stdOut2.readLine()) != null) {
            System.out.println(s2);
        }

        while ((s2 = stdError2.readLine()) != null) {
            System.err.println(s2);
        }

    }
}
