package UI;

import Controller.MainController;
import Controller.Start;
import UI.Component.TitleLabel;

import Entity.Goal;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MainUI extends JFrame implements ListSelectionListener {
    static public String selectDay = "";
    static public int year;
    static public int month;
    static public int day;

    Goal goal = new Goal();
    Scanner sc = new Scanner(System.in);
    Calendar calendar;
    JLabel bgCal = new JLabel();
    JPanel todayGoalPanel;
    JLabel todayGoalTitle;
    JList todayGoalList;
    JScrollPane scroll;

    JProgressBar progressRate = new JProgressBar(0, 100);

    JLabel nameLabel = new JLabel();
    JButton infoButton = new JButton();
    JButton addGoalBtn;
    DefaultListModel model;
    ArrayList<Goal> goalList;
    ArrayList<Goal> selectGoalList;
    String userName;
    int count = 0; //완료도 표시할 때 사용 될 듯?

    public MainUI(ArrayList<Goal> goalList, String userName, Calendar calendar) {
        this.goalList = goalList;
        this.userName = userName;
        this.calendar = calendar;

        this.setTitle("Goal In Up !");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1810, 1024);
        new TitleLabel(this, 800, 100, 215, 0); //타이틀 라벨 생성
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        infoButton.setSize(150, 50);
        infoButton.setText("회원정보");
        infoButton.setFont(new Font("SanSerif", Font.BOLD, 25));
        infoButton.setOpaque(true);
        infoButton.setBackground(Color.lightGray);
        infoButton.setLocation(1605, 10);
        this.add(infoButton);
    }

    public void setGoalList(ArrayList<Goal> goalList){
        this.goalList = goalList;
    }

    //MainUI 전체를 그리는 메서드 (메서드 호출)
    public void drawAll() {
        nameLabel.setSize(300, 50);
        nameLabel.setText(userName + "님 환영합니다 !");
        nameLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        nameLabel.setLocation(1330, 10);
        this.add(nameLabel);

        drawCalendar();
        drawTodayGoal();

        this.repaint();
    }

    //캘린더에 관련된 UI를 그리는 메서드
    private void drawCalendar() {
        //현재 월 달력 UI 생성
        LocalDate now = LocalDate.now();
        MainUI.year = now.getYear();
        MainUI.month = now.getMonthValue();
        MainUI.day = now.getDayOfMonth();
        updateSelectDay();

        calendar.setInfo(bgCal, year, month);
        calendar.drawing();

        //달력이 들어갈 공간 생성
        bgCal.setOpaque(true);
        bgCal.setBackground(Color.lightGray);
        bgCal.setSize(1200, 850);
        bgCal.setLocation(30, 100);
        this.add(bgCal);
    }
    public void removeGoalPanel(){
        this.remove(todayGoalPanel);
    }
    public void updateSelectDay(){
        MainUI.selectDay = Integer.toString(MainUI.year) + "-";
        if (MainUI.month < 10){
            MainUI.selectDay += "0";
        }
        MainUI.selectDay += Integer.toString(MainUI.month) + "-" ;
        if (MainUI.day < 10) {
            MainUI.selectDay += "0";
        }
        MainUI.selectDay += Integer.toString(MainUI.day);
    }
    //오늘의 목표를 그리는 메서드
    public void drawTodayGoal() {
        DefaultListModel model = new DefaultListModel();

        todayGoalPanel = new JPanel();
        todayGoalTitle = new JLabel();
        //오늘의 목표 라벨 생성 해야함
        todayGoalPanel.setLayout(null);
        todayGoalPanel.setBackground(Color.lightGray);
        todayGoalPanel.setOpaque(true);
        todayGoalPanel.setBounds(1250, 105, 520, 390);

        todayGoalTitle.setSize(300, 50);
        todayGoalTitle.setLocation(30, 10);
        todayGoalTitle.setText( MainUI.selectDay + " 일 목표 ! ");
        todayGoalTitle.setFont(new Font("SanSerif", Font.BOLD, 25));
//        todayGoalTitle.setHorizontalAlignment(JLabel.CENTER);
        todayGoalPanel.add(todayGoalTitle);

        addGoalBtn = new JButton("목표 추가");
        addGoalBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
        addGoalBtn.setSize(130, 50);
        addGoalBtn.setLocation(380, 10);
        todayGoalPanel.add(addGoalBtn);


        model = createModel();
        todayGoalList = new JList(model);
        todayGoalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        todayGoalList.setFont(new Font("SanSerif", Font.BOLD, 25));
        todayGoalList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        todayGoalList.addListSelectionListener(this::valueChanged);
;

        scroll = new JScrollPane(todayGoalList);
        scroll.setBounds(0, 65, 520, 230);
        todayGoalPanel.add(scroll);


        JLabel progressRateLabel = new JLabel("성취율");
        progressRateLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        progressRateLabel.setSize(200, 30);
        progressRateLabel.setLocation(0, 320);
        todayGoalPanel.add(progressRateLabel);

        progressRate.setSize(520, 30);
        progressRate.setLocation(0, 355);
        progressRate.setForeground(Color.LIGHT_GRAY);
        progressRate.setValue(0);
        progressRate.setStringPainted(true);
        progressRate.setFont(new Font("SanSerif", Font.BOLD, 25));

        int count = goalList.size();
        int success = 0;
        for (int i = 0; i < count; i++){
            if (goalList.get(i).isGoal()) success += 1;
        }
        float rate = ((float) success / count) * 100;
        int achievementRate = (int) rate;
        progressRate.setValue(achievementRate);
        todayGoalPanel.add(progressRate);

        this.add(todayGoalPanel);
        this.revalidate();
        this.repaint();
    }
    public void setAddGoalBtnListener(ActionListener listener){
        addGoalBtn.addActionListener(listener);
    }
    public void setInfoButtonListener(ActionListener listener){
        infoButton.addActionListener(listener);
    }
    public DefaultListModel createModel() {
        model = new DefaultListModel();
        selectGoalList = new ArrayList<>();
        count = goalList.size();
        System.out.println("COUNT : " + count);
        for (int i = 0; i < count; i++) {
            boolean check = MainUI.compareDay(goalList.get(i).getStartDay(), goalList.get(i).getEndDay(), selectDay);

            if (check == false){
                continue;
            }
            String flag = "X";
            if (goalList.get(i).isGoal()){
                flag = "O";
            }
            String text = flag + " : " + goalList.get(i).getTitle();
            model.addElement(text);
            selectGoalList.add(goalList.get(i));
        }
        return model;
    }
    //선택한 날짜를 기준으로 목표가 표시 되어야 할 지 말아야할 지 결정하는 메서드
    public static boolean compareDay(String startDate, String endDate, String selectDay){
        Date start = createDate(startDate.replace("-", ""));
        Date end = createDate(endDate.replace("-", ""));
        Date selectDate = createDate(selectDay.replace("-", ""));

        int result = start.compareTo(selectDate); //목표 시작 날짜와 현재(사용자 선택) 날짜 비교
        if (result <= 0){
            //목표 시작 날짜 보다 선택 날짜가 뒤에 있음.
            result = end.compareTo(selectDate); // 목표 끝 날짜와 비교
            if (result >= 0) { //목표 끝보다 전 이라면
                return true;
            }
        }
        return false;
    }
    public static Date createDate(String dayText){
        int date = Integer.parseInt(dayText);
        int year = date / 10000;
        date -= year * 10000;
        int month = date / 100;
        date -= month * 100;
        int day = date;

        return new Date(year,month,day);
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int select = 0;
        if (!e.getValueIsAdjusting()){
            if (todayGoalList.getSelectedValue() != null) {
                select = todayGoalList.getSelectedIndex();
                Start.createGoalController(selectGoalList.get(select), this);
            }
        }
    }
}
