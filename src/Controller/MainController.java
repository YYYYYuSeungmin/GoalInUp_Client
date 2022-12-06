package Controller;

import DAO.GoalHandler;
import Entity.Goal;
import Entity.User;
import UI.AddGoalUI;
import UI.Calendar;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainController {
    ArrayList<Goal> goalList;
    GoalHandler gHandle;
    Calendar calendar;
    User user;
    MainUI mUI;

    AddGoalContorller addGoalContorller;
    public MainController(User user){
        this.user = user;
        calendar = new Calendar();

        goalList = new ArrayList<>();
        drawTodayGoal(user.getId());
    }
    private void setLisetner(){
        calendar.setdayBtnListener(new dayButtonListener());
        calendar.setprevMonthBtnListener(new prevMonthListener());
        calendar.setnextMonthBtnListener(new nextMonthListener());
    }
    private void drawTodayGoal(String userID){
        gHandle = new GoalHandler();
        goalList = gHandle.getGoalList(userID);
        mUI = new MainUI(goalList, userID, calendar);
        mUI.drawAll();

        mUI.setAddGoalBtnListener(new createGoalButtonListener());
        calendar.setdayBtnListener(new dayButtonListener());
        calendar.setprevMonthBtnListener(new prevMonthListener());
        calendar.setnextMonthBtnListener(new nextMonthListener());
    }

    public void createAddGoalController(){
        addGoalContorller = new AddGoalContorller(user.getId(), mUI);
    }
    class dayButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<JButton> dayBtnList = calendar.getDayBtnList();
            //현재 날짜를 오늘의 목표 라벨로 전달해야함.
            for (int i = 0; i < dayBtnList.size(); i++){
                if(e.getSource().equals(dayBtnList.get(i))){
                    MainUI.year = calendar.getYear();
                    MainUI.month = calendar.getMonth();
                    MainUI.day = Integer.parseInt(dayBtnList.get(i).getText());
                    mUI.updateSelectDay();
                    break;
                }
            }
            mUI.removeGoalPanel();
            mUI.drawTodayGoal();
            mUI.setAddGoalBtnListener(new createGoalButtonListener());
        }
    }

    class prevMonthListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (1 < calendar.month) calendar.month--;
            else {
                calendar.year--;
                calendar.month = 12;
            }
            calendar.removeComponents();
            calendar.drawing();
            setLisetner();
        }
    }
    class nextMonthListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            if (calendar.month < 12) calendar.month++;
            else {
                calendar.year++;
                calendar.month = 1;
            }
            calendar.removeComponents();
            calendar.drawing();
            setLisetner();
        }
    }

    class createGoalButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            createAddGoalController();
        }
    }
}
