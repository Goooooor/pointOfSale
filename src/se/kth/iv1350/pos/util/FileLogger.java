package se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Logs exception to a file.
 */
public class FileLogger {

    private PrintWriter logStream;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private LocalDateTime now = LocalDateTime.now();

    /**
     * Creates an instance of the class.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException ioe) {
            System.out.println("CAN NOT LOG.");
            ioe.printStackTrace();
        }
    }

    /**
     * Logs the exception and the stacktrace.
     * 
     * @param e The exception.
     */
    public void log(Exception e) {
        logStream.println(e.getMessage() + " on date and time: " + dtf.format(now));
        e.printStackTrace(logStream);
        logStream.println();
        logStream.flush();
    }
}
