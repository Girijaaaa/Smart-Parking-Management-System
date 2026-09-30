package gui;

import service.HistoryManager;

import javax.swing.*;
import java.awt.*;

public class HistoryFrame extends JFrame {

    private HistoryManager historyManager;
    private JTextArea historyArea;

    public HistoryFrame() {

        historyManager = new HistoryManager();

        setTitle("Parking History");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel(
                "Parking History",
                SwingConstants.CENTER
        );

        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        historyArea = new JTextArea();
        historyArea.setEditable(false);

        JButton refreshButton =
                new JButton("Refresh");

        JButton closeButton =
                new JButton("Close");

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        add(titleLabel, BorderLayout.NORTH);

        add(
                new JScrollPane(historyArea),
                BorderLayout.CENTER
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        refreshButton.addActionListener(
                e -> displayHistory()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        displayHistory();
    }

    private void displayHistory() {

        historyArea.setText("");

        if (historyManager.getHistory().isEmpty()) {

            historyArea.setText(
                    "No history records available."
            );

            return;
        }

        for (String record :
                historyManager.getHistory()) {

            historyArea.append(record);
            historyArea.append("\n");
            historyArea.append(
                    "-------------------------\n"
            );
        }
    }
}