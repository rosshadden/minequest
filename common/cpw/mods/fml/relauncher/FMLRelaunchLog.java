package cpw.mods.fml.relauncher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import cpw.mods.fml.relauncher.FMLLogFormatter;

public class FMLRelaunchLog
{

    private static class LoggingOutStream extends ByteArrayOutputStream
    {
        private Logger log;
        private StringBuilder currentMessage;

        public LoggingOutStream(Logger log)
        {
            this.log = log;
            this.currentMessage = new StringBuilder();
        }

        @Override
        public void flush() throws IOException
        {
            String record;
            synchronized(this)
            {
                super.flush();
                record = this.toString();
                super.reset();

                currentMessage.append(record);
                if (currentMessage.lastIndexOf(FMLLogFormatter.LINE_SEPARATOR)>=0)
                {
                    // Are we longer than just the line separator?
                    if (currentMessage.length()>FMLLogFormatter.LINE_SEPARATOR.length())
                    {
                        // Trim the line separator
                        currentMessage.setLength(currentMessage.length()-FMLLogFormatter.LINE_SEPARATOR.length());
                        log.log(Level.INFO, currentMessage.toString());
                    }
                    currentMessage.setLength(0);
                }
            }
        }
    }
    /**
     * Our special logger for logging issues to. We copy various assets from the
     * Minecraft logger to acheive a similar appearance.
     */
    public static FMLRelaunchLog log = new FMLRelaunchLog();

    static File minecraftHome;
    private static boolean configured;
    private Logger myLog;

    private FMLRelaunchLog()
    {
    }
    /**
     * Configure the FML logger
     */
    private static void configureLogging()
    {
        LogManager.getLogManager().reset();
        Logger globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        globalLogger.setLevel(Level.OFF);

        log.myLog = Logger.getLogger("ForgeModLoader");

        Logger stdOut = Logger.getLogger("STDOUT");
        stdOut.setParent(log.myLog);
        Logger stdErr = Logger.getLogger("STDERR");
        stdErr.setParent(log.myLog);
        FMLLogFormatter formatter = new FMLLogFormatter();

        // Console handler captures the normal stderr before it gets replaced
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.parse(System.getProperty("fml.log.level","INFO")));
        log.myLog.setUseParentHandlers(false);
        log.myLog.addHandler(ch);
        ch.setFormatter(formatter);
        log.myLog.setLevel(Level.ALL);
        try
        {
            File logPath = new File(minecraftHome, FMLRelauncher.logFileNamePattern);
            FileHandler fileHandler = new FileHandler(logPath.getPath(), 0, 3);
            fileHandler.setFormatter(formatter);
            fileHandler.setLevel(Level.ALL);
            log.myLog.addHandler(fileHandler);
        }
        catch (Exception e)
        {
        }

        // Set system out to a log stream
        System.setOut(new PrintStream(new LoggingOutStream(stdOut), true));
        System.setErr(new PrintStream(new LoggingOutStream(stdErr), true));

        // Reset global logging to shut up other logging sources (thanks guava!)
        configured = true;
    }

    public static void log(Level level, String format, Object... data)
    {
        if (!configured)
        {
            configureLogging();
        }
        log.myLog.log(level, String.format(format, data));
    }

    public static void log(Level level, Throwable ex, String format, Object... data)
    {
        if (!configured)
        {
            configureLogging();
        }
        log.myLog.log(level, String.format(format, data), ex);
    }

    public static void severe(String format, Object... data)
    {
        log(Level.SEVERE, format, data);
    }

    public static void warning(String format, Object... data)
    {
        log(Level.WARNING, format, data);
    }

    public static void info(String format, Object... data)
    {
        log(Level.INFO, format, data);
    }

    public static void fine(String format, Object... data)
    {
        log(Level.FINE, format, data);
    }

    public static void finer(String format, Object... data)
    {
        log(Level.FINER, format, data);
    }

    public static void finest(String format, Object... data)
    {
        log(Level.FINEST, format, data);
    }
    public Logger getLogger()
    {
        return myLog;
    }
}
