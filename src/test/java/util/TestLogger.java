package util;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestLogger {
    private static PrintStream originalOut;
    private static PrintStream originalErr;
    private static PrintStream fileOut;
    private static String logFilePath = System.getProperty("user.dir") + "/ai-analysis/temp_logs.txt";
    
    /**
     * Redirect System.out and System.err to both console and log file
     */
    public static void startLogging() {
        try {
            // Clear the log file at the start of each execution
            File logFile = new File(logFilePath);
            if (logFile.exists()) {
                logFile.delete();
            }
            
            // Save original streams
            originalOut = System.out;
            originalErr = System.err;
            
            // Create file output stream
            FileOutputStream fos = new FileOutputStream(logFilePath, true);
            fileOut = new PrintStream(fos);
            
            // Create a custom PrintStream that writes to both console and file
            PrintStream dualStream = new PrintStream(fos) {
                @Override
                public void print(String s) {
                    originalOut.print(s);
                    super.print(s);
                }
                
                @Override
                public void println(String s) {
                    originalOut.println(s);
                    super.println(s);
                }
                
                @Override
                public void println() {
                    originalOut.println();
                    super.println();
                }
            };
            
            System.setOut(dualStream);
            System.setErr(dualStream);
            
            // Format timestamp to match Python embeddings.py format: "February 16, 2026 at 08:13:34 PM"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' HH:mm:ss");
            String formattedTimestamp = LocalDateTime.now().format(formatter);
            
            String separator = "================================================================================";
            System.out.println(separator);
            System.out.println("TEST EXECUTION LOG - " + formattedTimestamp);
            System.out.println(separator);
            
        } catch (IOException e) {
            System.err.println("Error setting up logging: " + e.getMessage());
        }
    }
    
    /**
     * Restore original System.out and System.err
     */
    public static void stopLogging() {
        try {
            if (fileOut != null) {
                fileOut.close();
            }
            if (originalOut != null) {
                System.setOut(originalOut);
            }
            if (originalErr != null) {
                System.setErr(originalErr);
            }
            System.out.println("Logging stopped. Logs saved to: " + logFilePath);
        } catch (Exception e) {
            System.err.println("Error stopping logging: " + e.getMessage());
        }
    }
    
    /**
     * Get the log file path
     */
    public static String getLogFilePath() {
        return logFilePath;
    }
}
