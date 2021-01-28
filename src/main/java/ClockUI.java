import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class ClockUI implements Runnable {

    private final JPanel clockPanel = new JPanel();
    private final JLabel clockDisplayLabel = new JLabel();
    private boolean stop = false;
    private long recordedTime = 0L;

    public ClockUI () {
        clockDisplayLabel.setText("00:00:00");
        clockDisplayLabel.setFont(Configuration.UI_CLOCK_FONT);
        clockDisplayLabel.setForeground(Color.BLUE);
        clockPanel.add(clockDisplayLabel);
    }

    public void run() {

        long start = System.currentTimeMillis() / 1000;
        long elapsed = 0L;
        while (!stop) {
            try {
                long now = System.currentTimeMillis() / 1000;
                elapsed = recordedTime + (now - start);
                int hour = (int) (elapsed / 3600);
                int min = (int) ((elapsed - hour * 3600) / 60);
                int sec = (int) ((elapsed - (hour * 3600) - (min * 60)) % 60);
                clockDisplayLabel.setText(String.format("%02d:%02d:%02d", hour, min, sec));
                TimeUnit.MILLISECONDS.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        recordedTime = elapsed + 1;
    }

    public void start() {
        this.stop = false;
        Thread thread = new Thread(this);
        thread.start();

    }
    public void stop() { this.stop = true; }

    public JPanel panel() { return clockPanel; }

    @Override
    public String toString() {
        return clockDisplayLabel.getText();
    }
}
