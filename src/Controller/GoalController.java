package Controller;

import DAO.DetailGoalSocket;
import DAO.GoalSocket;
import Entity.DetailGoal;
import Entity.Goal;
import UI.GoalUI;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GoalController {
    Goal goal = new Goal();
    MainUI mUI;
    DetailGoalSocket detailHandle;
    GoalSocket goalSocket;
    ArrayList<DetailGoal> detailGoalList;

    GoalUI gUI;

    public GoalController(Goal goal, MainUI mUI){
        this.goal = goal;
        this.mUI = mUI;

        drawGoalUI(goal);
    }

    public void drawGoalUI(Goal goal){
        detailHandle = new DetailGoalSocket();
        detailGoalList = detailHandle.getDetailGoals(goal.getgID());

        gUI = new GoalUI(goal, detailGoalList, mUI);
        gUI.drawGoalAll();
        gUI.setDeleteBtnListener(new remmoveGoalButtonListener());
        gUI.setUpdateBtnListener(new updateGoalButtonListener());
        gUI.setAddDetailGoalBtnListener(new addDetailGoalButtonListener());

    }

    class remmoveGoalButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            goalSocket = new GoalSocket();
            boolean check = goalSocket.removeGoal(goal);

            if (check == true){
                JOptionPane.showMessageDialog(mUI, "목표 삭제 완료", "목표 삭제", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(mUI, "목표 삭제 실패", "목표 삭제", JOptionPane.ERROR_MESSAGE);
            }

            goalSocket = new GoalSocket();
            mUI.setGoalList(goalSocket.getGoalList(goal.getUserID()));
            mUI.removeGoalPanel();
            mUI.drawTodayGoal();
            gUI.dispose();
        }
    }

    class updateGoalButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
//            gUI.dispose();
            UpdateGoalController UGC = new UpdateGoalController(goal, mUI, gUI);
        }
    }

    class addDetailGoalButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("진입");
            AddDetailGoalController addDetailGoalController = new AddDetailGoalController(goal, gUI, mUI);
//            gUI.dispose();
        }
    }
}
