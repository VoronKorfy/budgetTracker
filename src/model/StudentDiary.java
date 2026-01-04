package model;
import java.time.LocalDate;

public class StudentDiary {
    private DailyRecord head;

    public StudentDiary() {
        this.head = null;
    }

    public DailyRecord getHead() {
        return head;
    }

    public void setHead(DailyRecord head) {
        this.head = head;
    }

    public void addDailyRecord() {
        int newID = head == null ? 0 : head.getDailyRecordID() + 1;

        LocalDate today = LocalDate.now();
        // YYYYMMDD
        int newDate = today.getYear() * 10000 + today.getMonthValue() * 100 + today.getDayOfMonth();

        DailyRecord newDailyRecord = new DailyRecord(newID, newDate);
        newDailyRecord.setNext(head);
        head = newDailyRecord;
    }
}
