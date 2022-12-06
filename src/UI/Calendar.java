package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calendar {
    JButton prev;
    JButton next;
    static public int year;
    static public int month;
    static public int day;
    JLabel now;
    JLabel label;
    ArrayList<JButton> dayBtnList = new ArrayList<>();
    ArrayList<JLabel> dayOfWeekLabelList = new ArrayList<>();

    public void drawing() {
        drawNowLabel();
        drawDayButton();
        drawDayOfWeek();

        label.repaint();
    }
    //기존 컴포넌트 지우기
    public void removeComponents(){
        label.remove(now);
        label.remove(prev);
        label.remove(next);
        for (int i = 0; i < dayOfWeekLabelList.size(); i++){
            label.remove(dayOfWeekLabelList.get(i));
        }
        for (int i = 0; i < dayBtnList.size(); i++){
            label.remove(dayBtnList.get(i));
        }
    }
    //윤년 계산
    private boolean isLeapYear(int year) {
        boolean leap = false;

        if((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
            leap = true;
        }
        return leap;
    }
    public void setInfo(JLabel label, int year, int month){
        this.label = label;
        this.year = year;
        this.month = month;
    }
    private int getDate(int year, int month) {
        int tmp = 0;
        if(isLeapYear(year)) { //윤년일 때 날짜
            switch(month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    tmp = 31;
                    break;
                case 4: case 6: case 9: case 11:
                    tmp = 30;
                    break;
                case 2:
                    tmp = 29;
                    break;
            }
        }
        else { //윤년이 아닐 때
            if(month == 1 || month == 3 || month == 5 || month == 7 ||month == 8 || month == 10 || month == 12) {
                tmp = 31;
            } else if(month == 4 || month == 6 || month == 9 || month == 11) {
                tmp = 30;
            }else if (month == 2) {
                tmp = 28;
            }
        }
        return tmp;
    }

    //해당 월의 첫번쨰 요일 계산
    private int getDayOfWeek() {
        int dayOfWeek = 0;
        int sum = 0;

        // 1.1.1 ~ year-1.12.31
        for(int i = 1; i < year; i++) {
            for(int j = 1; j <= 12; j++) {
                sum += getDate(i, j);
            }
        }
        //year.1.1 ~ year.month-1.마지막(31,30.29.28)
        for(int k = 1; k < month; k++) {
            sum += getDate(year, k);
        }
        //year.month.1일
        sum += 1;
        //요일
        dayOfWeek = sum % 7;

        return dayOfWeek;
    }
    private void drawDayButton(){
        dayBtnList.clear();
        //시작 요일
        int start = getDayOfWeek();
        //마지막 날짜
        int last = getDate(this.year, this.month);

        for (int i = 1; i <= last; i++){ //1일부터 마지막날까지 반복
            JButton btn = new JButton(Integer.toString(i));
            int posX = ((i-1 + start) % 7); // 요일
            int posY = ((i-1 + start) / 7); // 주차
            btn.setSize(170, 120);
            btn.setLocation(posX * 170 + 5, posY * 120 + 111);
            btn.setOpaque(true);
            btn.setBackground(Color.lightGray);
            label.add(btn);
            dayBtnList.add(btn);
        }
    }

    private void drawDayOfWeek(){
        String[] dayOfWeek = {"일", "월", "화", "수", "목", "금", "토"};
        //요일 라벨 생성
        for (int i = 0; i < 7; i++){
            JLabel day = new JLabel(dayOfWeek[i]);
            day.setSize(170,40);
            day.setLocation(i * 171 + 1, 60);
            day.setOpaque(true);
            day.setBackground(Color.white);
            day.setFont(new Font("SanSerif", Font.BOLD, 30));
            day.setHorizontalAlignment(JLabel.CENTER);
            label.add(day);
            dayOfWeekLabelList.add(day);
        }
    }
    //최 상단 년도, 월 , 전달 다음달 버튼 UI생성
    private void drawNowLabel(){
        String syear = Integer.toString(year);
        String smonth = Integer.toString(month);

        now = new JLabel(syear + "." + smonth);
        now.setSize(200, 50);
        now.setFont(new Font("SanSerif", Font.BOLD, 30));
        now.setHorizontalAlignment(JLabel.CENTER);
        now.setLocation(500, 5);
//        now.setOpaque(true);
//        now.setBackground(Color.blue);
        label.add(now);

        prev = new JButton("◀");
        prev.setSize(50, 50);
        prev.setLocation(445, 5);
        label.add(prev);

        next = new JButton("▶");
        next.setSize(50, 50);
        next.setLocation(705, 5);
        label.add(next);
    }
    public int getYear(){
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public ArrayList<JButton> getDayBtnList(){
        return dayBtnList;
    }
    //액션리스너
    public void setdayBtnListener(ActionListener listener){
        for (int i = 0; i < dayBtnList.size(); i++){
            dayBtnList.get(i).addActionListener(listener);
        }
    }
    public void setprevMonthBtnListener(ActionListener listener){
        prev.addActionListener(listener);
    }
    public void setnextMonthBtnListener(ActionListener listener){
        next.addActionListener(listener);
    }
}
