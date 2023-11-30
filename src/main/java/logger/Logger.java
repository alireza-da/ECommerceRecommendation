package main.java.logger;

import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Logger {
    private static java.util.logging.Logger jLogger;
    private void load(){
        jLogger = java.util.logging.Logger.getLogger(Logger.class.getName());
    }

    public Logger() {
        load();
    }

    public void log(Level level, String msg){
        jLogger.log(level, msg);
    }
    public void fine(String msg){
        jLogger.fine(msg);
    }
    public void entering(String _class, String method){
        jLogger.entering(_class, method);
    }
    public void logp(Level level, String _class, String method, String msg){
        jLogger.logp(level, _class, method, msg);
    }
}
