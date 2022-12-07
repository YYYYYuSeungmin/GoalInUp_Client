package Controller;

import DAO.DetailGoalSocket;
import DAO.GoalSocket;
import Entity.DetailGoal;
import Entity.Goal;
import UI.AddDetailGoalUI;
import UI.GoalUI;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class AddDetailGoalController {
    AddDetailGoalUI addDetailGoalUI;
    DetailGoalSocket detailGoalSocket;
    GoalController goalController;
    Goal goal;
    GoalUI goalUI;
    MainUI mainUI;
    DetailGoal detailGoal;
    GoalSocket goalSocket;

    public AddDetailGoalController(Goal goal, GoalUI goalUI, MainUI mainUI, GoalController goalController){
        this.goal = goal;
        this.goalUI = goalUI;
        this.mainUI = mainUI;
        this.goalController = goalController;

        addDetailGoalUI = new AddDetailGoalUI(this.goal);
        addDetailGoalUI.setAddDetailGoalBtnListener(new addGoalButtonListener());
    }

    public void createDetailGoal(){
        detailGoal = new DetailGoal();
        detailGoal.setgID(goal.getgID());
        detailGoal.setTitle(addDetailGoalUI.getTitle());
        detailGoal.setContents(addDetailGoalUI.getcontents());
        detailGoal.setStartDay(addDetailGoalUI.getStartDay());
        detailGoal.setEndDay(addDetailGoalUI.getEndDay());
        detailGoal.setGoal(addDetailGoalUI.isSuccess());
    }
    public void insertDetailGoal(){
        detailGoalSocket = new DetailGoalSocket();
        boolean check = detailGoalSocket.insertDetailGoal(detailGoal);

        if (check == true) {
            JOptionPane.showMessageDialog(goalUI, "세부목표 등록 완료", "세부목표 등록 결과", INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(goalUI, "세부목표 등록 실패", "세부목표 등록 결과", JOptionPane.ERROR_MESSAGE);
        }
    }
    class addGoalButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
//            detailGoalHandler = new DetailGoalHandler();
            createDetailGoal();
            insertDetailGoal();

            addDetailGoalUI.dispose();
            goalUI.dispose();

            goalSocket = new GoalSocket();
            mainUI.setGoalList(goalSocket.getGoalList(goal.getUserID()));
            mainUI.removeGoalPanel();
            mainUI.drawTodayGoal();

            goalController.drawGoalUI(goal);
        }
    }
}
