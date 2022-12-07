package Controller;

import DAO.GoalSocket;
import Entity.Goal;
import UI.AddGoalUI;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGoalContorller {
    AddGoalUI addgoalUI;
    GoalSocket goalSocket;
    String userID;

    MainUI mUI;
    Goal goal;

    public AddGoalContorller(String userID, MainUI mUI){
        this.userID = userID;
        this.mUI = mUI;
        goal = new Goal();

        goal.setUserID(this.userID);
        addgoalUI = new AddGoalUI();

        addgoalUI.setAddButtonListener(new addButtonListener());
    }
    public void createGoal(){
        goal.setUserID(this.userID);
        goal.setStartDay(addgoalUI.getStartDay());
        goal.setEndDay(addgoalUI.getEndDay());
        goal.setTitle(addgoalUI.getTitle());
//        goal.setGoal(addgoalUI.getGoal());
    }

    public void insertGoal(){
        goalSocket = new GoalSocket();
        boolean check = goalSocket.insertGoal(goal);

        if (check == true){
            JOptionPane.showMessageDialog(mUI, "목표 등록 완료", "목표 등록", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(mUI, "목표 등록 실패", "목표 등록", JOptionPane.ERROR_MESSAGE);
        }
    }

    class addButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            createGoal();
            insertGoal();
            goalSocket = new GoalSocket();
            mUI.setGoalList(goalSocket.getGoalList(userID));

            mUI.removeGoalPanel();
            mUI.drawTodayGoal();

            addgoalUI.dispose();
        }
    }
}
