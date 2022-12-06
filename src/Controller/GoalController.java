package Controller;

import DAO.DetailGoalHandler;
import DAO.GoalHandler;
import Entity.DetailGoal;
import Entity.Goal;
import UI.AddDetailGoalUI;
import UI.GoalUI;
import UI.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GoalController {
    Goal goal = new Goal();
    MainUI mUI;
    DetailGoalHandler detailHandle;
    GoalHandler goalHandler;
    ArrayList<DetailGoal> detailGoalList;

    GoalUI gUI;

    public GoalController(Goal goal, MainUI mUI){
        this.goal = goal;
        this.mUI = mUI;

        drawGoalUI(goal);
    }

    public void drawGoalUI(Goal goal){
        detailHandle = new DetailGoalHandler();
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
            goalHandler = new GoalHandler();
            goalHandler.removeGoal(goal);

            goalHandler = new GoalHandler();
            mUI.setGoalList(goalHandler.getGoalList(goal.getUserID()));
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
