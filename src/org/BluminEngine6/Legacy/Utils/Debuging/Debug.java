package org.BluminEngine6.Legacy.Utils.Debuging;



import org.BluminEngine6.Application;
import org.BluminEngine6.Legacy.Utils.EventSystem.IAction;
import org.BluminEngine6.Legacy.Utils.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Debug{
    private static List<String> Log = new ArrayList<>();
    private static List<String> RawLog = new ArrayList<>();

    private static IAction InitAct= new IAction() {
        @Override
        public void Run() {
            OnExit();
        }
    };

    private static void OnExit() {
        var path = "Res/log.log";

        try {

            if(!Utils.FileExists(path)) {
                File myObj = new File(path);

                myObj.createNewFile();

                BufferedWriter bf = new BufferedWriter(new FileWriter(path));

                for (String data :Log) {
                    bf.write(data);
                    bf.newLine();
                }

                bf.flush();

            } else {
                File myObj = new File(path);
                myObj.delete();
                myObj.createNewFile();

                BufferedWriter bf = new BufferedWriter(new FileWriter(path));

                for (String data :Log) {
                    bf.write(data);
                    bf.newLine();
                }
                bf.flush();
            }
        } catch (IOException e) {

        }

    }
    public static void log(Object dat) {
        if(!Application.OnExit.IsListener(InitAct)) {
            Application.OnExit.addListener(InitAct);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:ms");
        LocalDateTime now = LocalDateTime.now();
        if(dat == null) {
            dat = "null";
        }

        String datString = "{System "+dtf.format(now)  + "}: "+ dat.toString();
        if(!RawLog.contains(dat.toString())) {
            Log.add(datString);
            RawLog.add(dat.toString());
        }
        System.out.println(datString);
    }

    public static void log(Object dat, String throwerName) {
        if(!Application.OnExit.IsListener(InitAct)) {
            Application.OnExit.addListener(InitAct);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:ms");
        LocalDateTime now = LocalDateTime.now();
        if(dat == null) {
            dat = "null";
        }
        String datString = "{" +throwerName + " " +dtf.format(now)  + "}: "+ dat.toString();
        if(!RawLog.contains(dat.toString())) {
            Log.add(datString);
            RawLog.add(dat.toString());
        }
        System.out.println(datString);
    }
    public static void logError(Object dat) {
        if(!Application.OnExit.IsListener(InitAct)) {
            Application.OnExit.addListener(InitAct);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:ms");
        LocalDateTime now = LocalDateTime.now();
        if(dat == null) {
            dat = "null";
        }
        String datString = "{Error: "+dtf.format(now)  + "}: "+ dat.toString();
        if(!RawLog.contains(dat.toString())) {
            Log.add(datString);
            RawLog.add(dat.toString());
        }
        System.err.println(datString);
    }

    public static void logException(Exception dat) {
        if(!Application.OnExit.IsListener(InitAct)) {
            Application.OnExit.addListener(InitAct);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:ms");
        LocalDateTime now = LocalDateTime.now();

        String datString = "{Error: "+dtf.format(now)  + "}: "+ dat.getMessage()+"\n" + Utils.GetStacktrace(dat);;

        if(!RawLog.contains(dat.toString())) {
            Log.add(datString);
            RawLog.add(dat.toString());
        }
        System.err.println(datString);
        Application.OnExit.Invoke();
    }

    public static void logException(Object data, Exception dat) {
        if(!Application.OnExit.IsListener(InitAct)) {
            Application.OnExit.addListener(InitAct);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:ms");
        LocalDateTime now = LocalDateTime.now();
        String datString = "{Error: "+dtf.format(now)  + "}: " + data.toString() +"\n"+ Utils.GetStacktrace(dat);
        if(!RawLog.contains(dat.toString())) {
            Log.add(datString);
            RawLog.add(dat.toString());
        }
        System.err.println(datString);
        Application.OnExit.Invoke();
    }

    public static void logException(String data, StackTraceElement[] dat) {
        if(!Application.OnExit.IsListener(InitAct)) {
            Application.OnExit.addListener(InitAct);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:ms");
        LocalDateTime now = LocalDateTime.now();
        String datString = "{Error: "+dtf.format(now)  + "}: " + data +"\n"+ Utils.GetStacktrace(dat);
        if(!RawLog.contains(dat.toString())) {
            Log.add(datString);
            RawLog.add(dat.toString());
        }
        System.err.println(datString);
        Application.OnExit.Invoke();
    }
}
