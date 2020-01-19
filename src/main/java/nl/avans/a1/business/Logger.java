package nl.avans.a1.business;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    private static Logger logger = null;

    private final String logFile = "messages_logfile.txt";
    private PrintWriter writer;

    public Logger() {
        try {
            FileWriter fw = new FileWriter(logFile);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) {}
    }

    public static synchronized Logger getInstance(){
        if(logger == null)
            logger = new Logger();
        return logger;
    }
    public void noteLogger (String logPerson, String logMessage, String logTitle) {
        writer.println("Person: " + logPerson + "\nMessage: " + logMessage + "\nTitle: " + logTitle + "\n");
    }
}