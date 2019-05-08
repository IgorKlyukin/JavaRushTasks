package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LogParser implements IPQuery, UserQuery, DateQuery {
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

    private Set<Log> getFilteredEntries(Date after, Date before) {
        Set<Log> filteredRecords = new HashSet<>();
        for (Log log : logs) {
            if (isBetween(log.date, after, before)) {
                filteredRecords.add(log);
            }
        }
        return filteredRecords;
    }

    private boolean isBetween(Date date, Date after, Date before) {
        return (after == null || date.after(after) || date.equals(after)) &&
                (before == null || date.before(before) || date.equals(before));
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Log log : filteredRecords) {
            ips.add(log.ip);
        }

        return ips;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Log log : filteredRecords) {
            if (log.user.equals(user))
                ips.add(log.ip);
        }

        return ips;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Log log : filteredRecords) {
            if (log.event.equals(event))
                ips.add(log.ip);
        }

        return ips;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> ips = new HashSet<>();

        for (Log log : filteredRecords) {
            if (log.status.equals(status))
                ips.add(log.ip);
        }

        return ips;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> set = new HashSet<>();
        for (Log log :
                logs) {
            set.add(log.user);
        }
        return set;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);

        Set<String> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            set.add(log.user);
        }
        return set.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);

        Set<Event> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.user.equals(user))
                set.add(log.event);
        }
        return set.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.ip.equals(ip))
                set.add(log.user);
        }
        return set;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.event.equals(Event.LOGIN))
                set.add(log.user);
        }
        return set;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.event.equals(Event.DOWNLOAD_PLUGIN) && log.status.equals(Status.OK))
                set.add(log.user);
        }
        return set;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.event.equals(Event.WRITE_MESSAGE) && log.status.equals(Status.OK))
                set.add(log.user);
        }
        return set;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getSolvedTaskUsers(after, before, 0);
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.event.equals(Event.SOLVE_TASK) && (task == 0 || log.eventInt == task))
                set.add(log.user);
        }
        return set;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getDoneTaskUsers(after, before, 0);
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<String> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.event.equals(Event.DONE_TASK) && (task == 0 || log.eventInt == task))
                set.add(log.user);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<Date> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.event.equals(event) && log.user.equals(user))
                set.add(log.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<Date> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.status.equals(Status.FAILED))
                set.add(log.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<Date> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.status.equals(Status.ERROR))
                set.add(log.date);
        }
        return set;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<Date> set = new TreeSet<>();
        for (Log log :
                filteredRecords) {
            if (log.user.equals(user) && log.event.equals(Event.LOGIN))
                set.add(log.date);
        }
        return set.isEmpty() ? null : set.iterator().next();
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<Date> set = new TreeSet<>();
        for (Log log :
                filteredRecords) {
            if (log.user.equals(user) && log.eventInt == task && log.event.equals(Event.SOLVE_TASK))
                set.add(log.date);
        }
        return set.isEmpty() ? null : set.iterator().next();
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<Date> set = new TreeSet<>();
        for (Log log :
                filteredRecords) {
            if (log.user.equals(user) && log.eventInt == task && log.event.equals(Event.DONE_TASK))
                set.add(log.date);
        }
        return set.isEmpty() ? null : set.iterator().next();
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<Date> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.event.equals(Event.WRITE_MESSAGE) && log.user.equals(user))
                set.add(log.date);
        }
        return set;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Log> filteredRecords = getFilteredEntries(after, before);
        Set<Date> set = new HashSet<>();
        for (Log log :
                filteredRecords) {
            if (log.event.equals(Event.DOWNLOAD_PLUGIN) && log.user.equals(user))
                set.add(log.date);
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