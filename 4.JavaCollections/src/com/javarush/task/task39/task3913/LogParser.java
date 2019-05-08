package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery {
    private Path logDir;
    private ArrayList<Log> logs = new ArrayList<>();

    public LogParser(Path logDir) {
        this.logDir = logDir;
        searchLogs();
    }

    private void searchLogs() {
        if (Files.isDirectory(logDir))
            for (File file : logDir.toFile().listFiles()) {
                if (file.toString().endsWith(".log"))
                    readlog(file);
            }
    }

    private void readlog(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            while (reader.ready()) {
                s = reader.readLine();
                parser(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parser(String string) {
        String[] s = string.split("\\t");
        Log log = new Log();

        if (s[0].matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$"))
            log.ip = s[0];
        else
            return;

        log.user = s[1];

        if (s[2].matches("^\\d{1,2}\\.\\d{1,2}\\.\\d+ \\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.M.yyyy HH:mm:ss");
            try {
                log.date = simpleDateFormat.parse(s[2]);
            } catch (ParseException e) {
                return;
            }
        }
        else
            return;

        if (s[3].contains(" ")){
            String[] temp = s[3].split(" ");
            log.event = Event.valueOf(temp[0]);
            log.eventInt = Integer.parseInt(temp[1]);
        }
        else
            log.event = Event.valueOf(s[3]);

        log.status = Status.valueOf(s[4]);

        logs.add(log);
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        if (after == null && before == null)
            for (Log log :
                    logs) {
                set.add(log.ip);
            }
        else if (after == null)
            for (Log log :
                    logs) {
                if (log.date.equals(before) || log.date.before(before))
                    set.add(log.ip);
            }
        else if (before == null)
            for (Log log :
                    logs) {
                if (log.date.equals(after) || log.date.after(after))
                    set.add(log.ip);
            }
        else
            for (Log log :
                    logs) {
                if (log.date.equals(after) || log.date.equals(before) || log.date.before(before) && log.date.after(after))
                    set.add(log.ip);
            }
            
        return set.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> set = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();
        
        if (after == null && before == null)
            for (Log log :
                    logs) {
                set.add(log.ip);
                list.add(log.ip);
            }
        else if (after == null)
            for (Log log :
                    logs) {
                if (log.date.equals(before) || log.date.before(before)) {
                    set.add(log.ip);
                    list.add(log.ip);
                }
            }
        else if (before == null)
            for (Log log :
                    logs) {
                if (log.date.equals(after) || log.date.after(after)) {
                    set.add(log.ip);
                    list.add(log.ip);
                }
            }
        else
            for (Log log :
                    logs) {
                if (log.date.equals(after) || log.date.equals(before) || log.date.before(before) && log.date.after(after)) {
                    set.add(log.ip);
                    list.add(log.ip);
                }
            }

//        for (String s :
//                new HashSet<>(set)) {
//            if (Collections.frequency(list, s) > 1) {
//                set.remove(s);
//            }
//        }
            
        return set;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> set = new HashSet<>();

        if (after == null && before == null)
            for (Log log :
                    logs) {
                if (log.user.equals(user))
                    set.add(log.ip);
            }
        else if (after == null)
            for (Log log :
                    logs) {
                if (log.user.equals(user))
                    if (log.date.equals(before) || log.date.before(before)) {
                        set.add(log.ip);
                    }
            }
        else if (before == null)
            for (Log log :
                    logs) {
                if (log.user.equals(user))
                    if (log.date.equals(after) || log.date.after(after)) {
                        set.add(log.ip);
                    }
            }
        else
            for (Log log :
                    logs) {
                if (log.user.equals(user))
                    if (log.date.equals(after) || log.date.equals(before) || log.date.before(before) && log.date.after(after)) {
                        set.add(log.ip);
                    }
            }

        return set;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> set = new HashSet<>();

        if (after == null && before == null)
            for (Log log :
                    logs) {
                if (log.event.equals(event))
                    set.add(log.ip);
            }
        else if (after == null)
            for (Log log :
                    logs) {
                if (log.event.equals(event))
                    if (log.date.equals(before) || log.date.before(before)) {
                        set.add(log.ip);
                    }
            }
        else if (before == null)
            for (Log log :
                    logs) {
                if (log.event.equals(event))
                    if (log.date.equals(after) || log.date.after(after)) {
                        set.add(log.ip);
                    }
            }
        else
            for (Log log :
                    logs) {
                if (log.event.equals(event))
                    if (log.date.equals(after) || log.date.equals(before) || log.date.before(before) && log.date.after(after)) {
                        set.add(log.ip);
                    }
            }

        return set;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> set = new HashSet<>();

        if (after == null && before == null)
            for (Log log :
                    logs) {
                if (log.status.equals(status))
                    set.add(log.ip);
            }
        else if (after == null)
            for (Log log :
                    logs) {
                if (log.status.equals(status))
                    if (log.date.equals(before) || log.date.before(before)) {
                        set.add(log.ip);
                    }
            }
        else if (before == null)
            for (Log log :
                    logs) {
                if (log.status.equals(status))
                    if (log.date.equals(after) || log.date.after(after)) {
                        set.add(log.ip);
                    }
            }
        else
            for (Log log :
                    logs) {
                if (log.status.equals(status))
                    if (log.date.equals(after) || log.date.equals(before) || log.date.before(before) && log.date.after(after)) {
                        set.add(log.ip);
                    }
            }

        return set;
    }

    private class Log {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int eventInt;
        private Status status;

        public int getEventInt() {
            return eventInt;
        }

        public void setEventInt(int eventInt) {
            this.eventInt = eventInt;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Event getEvent() {
            return event;
        }

        public void setEvent(Event event) {
            this.event = event;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Log{" +
                    "ip='" + ip + '\'' +
                    ", user='" + user + '\'' +
                    ", date=" + date +
                    ", event=" + event +
                    ", eventInt=" + eventInt +
                    ", status=" + status +
                    '}';
        }
    }
}