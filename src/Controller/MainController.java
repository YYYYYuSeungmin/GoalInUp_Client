package Controller;

import DAO.DetailGoalSocket;
import DAO.GoalSocket;
import Entity.DetailGoal;
import Entity.Goal;
import Entity.User;
import UI.Calendar;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainController {
    ArrayList<Goal> goalList;
    ArrayList<DetailGoal> detailGoalList;
    DetailGoalSocket detailGoalSocket;
    GoalSocket goalSocket;
    Calendar calendar;
    User user;
    MainUI mUI;

    AddGoalContorller addGoalContorller;
    AccountInfoController accountInfoController;
    public MainController(User user){
        this.user = user;
        calendar = new Calendar();

        goalSocket = new GoalSocket();
        goalList = goalSocket.getGoalList(user.getId());

        drawTodayGoal(user.getName());
    }
    private void setLisetner(){
        calendar.setdayBtnListener(new dayButtonListener());
        calendar.setprevMonthBtnListener(new prevMonthListener());
        calendar.setnextMonthBtnListener(new nextMonthListener());
    }
    private void drawTodayGoal(String userName){
        mUI = new MainUI(goalList, userName, calendar);
        mUI.drawAll();

        mUI.setAddGoalBtnListener(new createGoalButtonListener());
        mUI.setInfoButtonListener(new InfoButtonListener());
        calendar.setdayBtnListener(new dayButtonListener());
        calendar.setprevMonthBtnListener(new prevMonthListener());
        calendar.setnextMonthBtnListener(new nextMonthListener());
    }

    private void createAddGoalController(){
        addGoalContorller = new AddGoalContorller(user.getId(), mUI);
    }

    private void createAccountInfoController(){
        accountInfoController = new AccountInfoController(user, mUI);
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
            mUI.setInfoButtonListener(new InfoButtonListener());
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

    class InfoButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            createAccountInfoController();
        }
    }
}
