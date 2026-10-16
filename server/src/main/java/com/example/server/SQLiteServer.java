package com.example.server;
import com.example.OBJECT.Health;
import com.example.OBJECT.ReportData;
import com.example.OBJECT.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLiteServer {
    Health[] health = new Health[100005];   int healthCount = 0;
    User[] user = new User[105];    int userCount = 0;
    String username;
    Statement stat;
    Connection conn;
    public Object solveSearchPersonInformation(String person, String startYear, String startMonth, String startDate, String endYear, String endMonth, String endDate) throws SQLException {
        //把年月日都转化为int
        System.out.println(person);
        int startYearInt = 0, startMonthInt = 0, startDateInt = 0, endYearInt = 0, endMonthInt = 0, endDateInt = 0;
        if (!startYear.isEmpty()) startYearInt = Integer.parseInt(startYear);
        if (!startMonth.isEmpty()) startMonthInt = Integer.parseInt(startMonth);
        if (!startDate.isEmpty()) startDateInt = Integer.parseInt(startDate);
        if (!endYear.isEmpty()) endYearInt = Integer.parseInt(endYear);
        if (!endMonth.isEmpty()) endMonthInt = Integer.parseInt(endMonth);
        if (!endDate.isEmpty()) endDateInt = Integer.parseInt(endDate);
        ArrayList<Health> result = new ArrayList<Health>();
        for (int i = 1; i <= healthCount; i++) {
            int year = Integer.parseInt(health[i].year);
            int month = Integer.parseInt(health[i].month);
            int date = Integer.parseInt(health[i].date);
            if (health[i].user.equals(person) && (year > startYearInt || (year == startYearInt && month > startMonthInt) || (year == startYearInt && month == startMonthInt && date >= startDateInt)) && (year < endYearInt || (year == endYearInt && month < endMonthInt) || (year == endYearInt && month == endMonthInt && date <= endDateInt))) {
                result.add(new Health(health[i].user, health[i].year, health[i].month, health[i].date, health[i].height, health[i].step, health[i].sleep, health[i].weight));
            }
            else if (health[i].user.equals(person) && startYearInt == 0 && startMonthInt == 0 && startDateInt == 0 && endYearInt == 0 && endMonthInt == 0 && endDateInt == 0) {
                result.add(new Health(health[i].user, health[i].year, health[i].month, health[i].date, health[i].height, health[i].step, health[i].sleep, health[i].weight));
            }
        }
        return result;
    }
    public Object solveReport(String year) throws SQLException {
        int dayNumber = 0;
        int MostMonth[] = new int[15];
        int HighestHeight = 0, LeastHeight = 1000, HighestWeight = 0, LeastWeight = 1000, MostStep = 0, MostStepMonth = 0, MostStepDay = 0, AverageSleepHours = 0;
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].user.equals(username) && health[i].year.equals(year)) {
                ++dayNumber;
                ++MostMonth[Integer.parseInt(health[i].month)];
                if (Integer.parseInt(health[i].height) > HighestHeight) HighestHeight = Integer.parseInt(health[i].height);
                if (Integer.parseInt(health[i].height) < LeastHeight) LeastHeight = Integer.parseInt(health[i].height);
                if (Integer.parseInt(health[i].weight) > HighestWeight) HighestWeight = Integer.parseInt(health[i].weight);
                if (Integer.parseInt(health[i].weight) < LeastWeight) LeastWeight = Integer.parseInt(health[i].weight);
                if (Integer.parseInt(health[i].step) > MostStep) {
                    MostStep = Integer.parseInt(health[i].step);
                    MostStepMonth = Integer.parseInt(health[i].month);
                    MostStepDay = Integer.parseInt(health[i].date);
                }
                AverageSleepHours += Integer.parseInt(health[i].sleep);
            }
        }
        AverageSleepHours /= dayNumber;
        int MostNumber = 0, MostNumberMonth = 0, LeastNumber = 1000, LeastNumberMonth = 0;
        for (int i = 1; i <= 12; ++i) {
            if (MostMonth[i] > MostNumber) {
                MostNumber = MostMonth[i];
                MostNumberMonth = i;
            }
            if (MostMonth[i] < LeastNumber) {
                LeastNumber = MostMonth[i];
                LeastNumberMonth = i;
            }
        }
        ReportData reportData = new ReportData(dayNumber, MostNumberMonth, MostNumber, LeastNumberMonth, LeastNumber, HighestHeight, LeastHeight, HighestWeight, LeastWeight, MostStepMonth, MostStepDay, MostStep, AverageSleepHours);
        System.out.println("OK");
        return reportData;
    }
    public String solveLogin(String username,String password) throws SQLException {
        for (int i = 1; i <= userCount; i++)
            if (user[i].username.equals(username) && user[i].password.equals(password)) {
                this.username = username;
                return "true";
            }
        return "false";
    }
    public Object solveSearch(String startYear, String startMonth, String startDate, String endYear, String endMonth, String endDate) throws SQLException {
        //把年月日都转化为int
        int startYearInt = 0, startMonthInt = 0, startDateInt = 0, endYearInt = 0, endMonthInt = 0, endDateInt = 0;
        if (!startYear.isEmpty()) startYearInt = Integer.parseInt(startYear);
        if (!startMonth.isEmpty()) startMonthInt = Integer.parseInt(startMonth);
        if (!startDate.isEmpty()) startDateInt = Integer.parseInt(startDate);
        if (!endYear.isEmpty()) endYearInt = Integer.parseInt(endYear);
        if (!endMonth.isEmpty()) endMonthInt = Integer.parseInt(endMonth);
        if (!endDate.isEmpty()) endDateInt = Integer.parseInt(endDate);
        ArrayList<Health> result = new ArrayList<Health>();
        for (int i = 1; i <= healthCount; i++) {
            int year = Integer.parseInt(health[i].year);
            int month = Integer.parseInt(health[i].month);
            int date = Integer.parseInt(health[i].date);
            if (health[i].user.equals(username) && (year > startYearInt || (year == startYearInt && month > startMonthInt) || (year == startYearInt && month == startMonthInt && date >= startDateInt)) && (year < endYearInt || (year == endYearInt && month < endMonthInt) || (year == endYearInt && month == endMonthInt && date <= endDateInt))) {
                result.add(new Health(health[i].user, health[i].year, health[i].month, health[i].date, health[i].height, health[i].step, health[i].sleep, health[i].weight));
            }
            else if (health[i].user.equals(username) && startYearInt == 0 && startMonthInt == 0 && startDateInt == 0 && endYearInt == 0 && endMonthInt == 0 && endDateInt == 0) {
                result.add(new Health(health[i].user, health[i].year, health[i].month, health[i].date, health[i].height, health[i].step, health[i].sleep, health[i].weight));
            }
        }
        return result;
    }
    public String solveRegister(String username,String password) throws SQLException {
        for (int i = 1; i <= userCount; i++)
            if (user[i].username.equals(username)) return "Register failed";
        String sql = "INSERT INTO user (username,password) VALUES ('" + username + "','" + password + "');";
        stat.executeUpdate(sql);
        conn.commit();
        user[++userCount] = new User(username, password);
        return "Register successful";
    }
    public String solveInsert(String year, String month, String date, String height, String step, String sleep, String weight) throws SQLException {
        int sleepTime = Integer.parseInt(sleep);
        if (sleepTime < 0 || sleepTime > 24) return "false";
        int y = Integer.parseInt(year), m = Integer.parseInt(month), d = Integer.parseInt(date);
        int[] monthDay = {0, 31, 28, 31, 30, 31, 30, 31, 31 ,30, 31, 30, 31};
        if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) monthDay[2] = 29;
        if (m < 1 || m > 12 || d < 1 || d > monthDay[m]) return "false";
        for (int i = 1; i <= healthCount; i++)
            if (health[i].year.equals(year) && health[i].month.equals(month) && health[i].date.equals(date) && health[i].user.equals(username)) return "false";
        String sql = "INSERT INTO health (user,year,month,date,height,step,sleep,weight) VALUES ('" + username + "','" + year + "','" + month + "','" + date + "','" + height + "','" + step + "','" + sleep + "','" + weight + "');";
        stat.executeUpdate(sql);
        conn.commit();
        health[++healthCount] = new Health(username, year, month, date, height, step, sleep, weight);
        return "true";
    }
    public String solveChange(String Date, String height, String step, String sleep, String weight) throws SQLException {
        int sleepTime = Integer.parseInt(sleep);
        if (sleepTime < 0 || sleepTime > 24) return "false";
        for (int i = 1; i <= healthCount; i++)
            if (health[i].Date.equals(Date) && health[i].user.equals(username)) {
                String sql = "UPDATE health SET height = '" + height + "', step = '" + step + "', sleep = '" + sleep + "', weight = '" + weight + "' WHERE year = '" + health[i].year + "' AND month = '" + health[i].month + "' AND user = '" + username + "' AND date = '" + health[i].date + "';";
                stat.executeUpdate(sql);
                conn.commit();
                health[i].height = height;
                health[i].step = step;
                health[i].sleep = sleep;
                health[i].weight = weight;
                System.out.println(health[i].date + " " + health[i].height + " " + health[i].step + " " + health[i].sleep + " " + health[i].weight);
                return "true";
            }
        return "false";
    }
    public Object solveHeightAverage(String year) throws SQLException {
        int[] result = new int[15];
        int[] count = new int[15];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && !health[i].height.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].month)] += Integer.parseInt(health[i].height);
                ++count[Integer.parseInt(health[i].month)];
            }
        }
        for (int i = 1; i <= 12; ++i)
            if (count[i] != 0) result[i] /= count[i];
        for (int i = 1; i <= 12; ++i) System.out.println(result[i]);
        return result;
    }
    public Object solveWeightAverage(String year) throws SQLException {
        int[] result = new int[15];
        int[] count = new int[15];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && !health[i].weight.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].month)] += Integer.parseInt(health[i].weight);
                ++count[Integer.parseInt(health[i].month)];
            }
        }
        for (int i = 1; i <= 12; ++i)
            if (count[i] != 0) result[i] /= count[i];
        return result;
    }
    public Object solveSleepAverage(String year) throws SQLException {
        int[] result = new int[15];
        int[] count = new int[15];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && !health[i].sleep.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].month)] += Integer.parseInt(health[i].sleep);
                ++count[Integer.parseInt(health[i].month)];
            }
        }
        for (int i = 1; i <= 12; ++i)
            if (count[i] != 0) result[i] /= count[i];
        return result;
    }
    public Object solveStepAverage(String year) throws SQLException {
        int[] result = new int[15];
        int[] count = new int[15];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && !health[i].step.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].month)] += Integer.parseInt(health[i].step);
                ++count[Integer.parseInt(health[i].month)];
            }
        }
        for (int i = 1; i <= 12; ++i)
            if (count[i] != 0) result[i] /= count[i];
        return result;
    }
    public Object solveBMIAverage(String year) throws SQLException {
        int[] result = new int[15];
        int[] count = new int[15];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && !health[i].height.equals("0") && !health[i].weight.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].month)] += Integer.parseInt(health[i].weight) * 10000 / (Integer.parseInt(health[i].height) * Integer.parseInt(health[i].height));
                ++count[Integer.parseInt(health[i].month)];
            }
        }
        for (int i = 1; i <= 12; ++i)
            if (count[i] != 0) result[i] /= count[i];
        return result;
    }
    public Object solveHeightMonth(String year, String month) throws SQLException {
        int[] result = new int[35];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && health[i].month.equals(month) && !health[i].height.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].date)] = Integer.parseInt(health[i].height);
            }
        }
        return result;
    }
    public Object solveWeightMonth(String year, String month) throws SQLException {
        int[] result = new int[35];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && health[i].month.equals(month) && !health[i].weight.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].date)] = Integer.parseInt(health[i].weight);
            }
        }
        return result;
    }
    public Object solveSleepMonth(String year, String month) throws SQLException {
        int[] result = new int[35];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && health[i].month.equals(month) && !health[i].sleep.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].date)] = Integer.parseInt(health[i].sleep);
            }
        }
        return result;
    }
    public Object solveStepMonth(String year, String month) throws SQLException {
        int[] result = new int[35];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && health[i].month.equals(month) && !health[i].step.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].date)] = Integer.parseInt(health[i].step);
            }
        }
        return result;
    }
    public Object solveBMIMonth(String year, String month) throws SQLException {
        int[] result = new int[35];
        for (int i = 1; i <= healthCount; i++) {
            if (health[i].year.equals(year) && health[i].month.equals(month) && !health[i].height.equals("0") && !health[i].weight.equals("0") && health[i].user.equals(username)) {
                result[Integer.parseInt(health[i].date)] = Integer.parseInt(health[i].weight) * 10000 / (Integer.parseInt(health[i].height) * Integer.parseInt(health[i].height));
            }
        }
        return result;
    }
    public Object solveSearchPerson() throws SQLException {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 1; i <= userCount; i++)
            if (!user[i].username.equals("admin")) result.add(user[i].username);
        return result;
    }
    public void solveDeletePerson(String person) throws SQLException {
        for (int i = 1; i <= userCount; i++)
            if (user[i].username.equals(person)) {
                String sql = "DELETE FROM user WHERE username = '" + person + "';";
                stat.executeUpdate(sql);
                conn.commit();
                user[i] = user[userCount];
                user[userCount--] = null;
            }
        for (int i = 1; i <= healthCount; i++)
            if (health[i].user.equals(person)) {
                String sql = "DELETE FROM health WHERE user = '" + person + "';";
                stat.executeUpdate(sql);
                conn.commit();
                health[i] = health[healthCount];
                health[healthCount--] = null;
            }
    }
    public void solveDelete(String Date) throws SQLException {
        for (int i = 1; i <= healthCount; i++) {
            System.out.println(health[i].date + " " + health[i].user);
            if (health[i].Date.equals(Date) && health[i].user.equals(username)) {
                String sql = "DELETE FROM health WHERE year = '" + health[i].year + "' AND user = '" + username + "' AND month = '" + health[i].month + "' AND user = '" + username + "' AND date = '" + health[i].date + "';";
                stat.executeUpdate(sql);
                conn.commit();
                health[i] = health[healthCount];
                health[healthCount--] = null;
                System.out.println("Delete successful");
                return;
            }
        }
    }
    public void run() {
        //导入数据库
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:data.db");
            conn.setAutoCommit(false);
            stat = conn.createStatement();
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS user (username TEXT PRIMARY KEY, password TEXT NOT NULL)");
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS health (user TEXT, year TEXT, month TEXT, date TEXT, height TEXT, step TEXT, sleep TEXT, weight TEXT)");
            conn.commit();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
        System.out.println("Opened database successfully");
    }
    public SQLiteServer() throws SQLException {
        run();
        init();
    }
    public void init() throws SQLException {
        for (int i = 1; i <= healthCount; ++i) health[i] = null;
        for (int i = 1; i <= userCount; ++i) user[i] = null;
        healthCount = userCount = 0;
        ResultSet rs = stat.executeQuery("SELECT * FROM user;");
        while (rs.next()) {
            String name = rs.getString("username");
            String pass = rs.getString("password");
            user[++userCount] = new User(name, pass);
        }
        rs = stat.executeQuery("SELECT * FROM health;");
        while (rs.next()) {
            String user = rs.getString("user");
            String year = rs.getString("year");
            String month = rs.getString("month");
            String date = rs.getString("date");
            String height = rs.getString("height");
            String step = rs.getString("step");
            String sleep = rs.getString("sleep");
            String weight = rs.getString("weight");
            health[++healthCount] = new Health(user, year, month, date, height, step, sleep, weight);
        }

    }
}
