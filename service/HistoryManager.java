package service;

import java.util.ArrayList;

public class HistoryManager {

    private static ArrayList<String> history =
            new ArrayList<>();

    public void addRecord(String record) {
        history.add(record);
    }

    public void displayHistory() {

        if (history.isEmpty()) {
            System.out.println("No history available.");
            return;
        }

        for (String record : history) {
            System.out.println(record);
        }
    }

    public int getHistoryCount() {
        return history.size();
    }

    public ArrayList<String> getHistory() {
        return history;
    }
}