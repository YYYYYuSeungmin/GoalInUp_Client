package Controller;

import DAO.DetailGoalHandler;
import Entity.DetailGoal;
import Entity.Goal;
import UI.DetailGoalInfoUI;
import UI.GoalUI;
import UI.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailGoalInfoController {
    GoalUI goalUI;
    Goal goal;
    MainUI mainUI;
    DetailGoal detailGoal;
    DetailGoalInfoUI detailGoalInfoUI;

    DetailGoalHandler detailGoalHandler;
    public DetailGoalInfoController(GoalUI goalUI, Goal goal, DetailGoal detailGoal, MainUI mainUI){
        this.goalUI = goalUI;
        this.detailGoal = detailGoal;
        this.goal = goal;
        this.mainUI = mainUI;

        System.out.println("선택된 세부 목표 리스트 : " + detailGoal.getTitle());
        System.out.println("선택된 목표 리스트 : " + goal.getTitle());

        detailGoalInfoUI = new DetailGoalInfoUI(goal, detailGoal);
        detailGoalInfoUI.setUpdateButtonListener(new detailGoalInfoUpdateBtnListener());
        detailGoalInfoUI.setDeleteButtonListener(new detailGoalInfoDeleteBtnListener());

    }
    public void updateDetailGoal(){
        detailGoal.setTitle(detailGoalInfoUI.getDetailGoalTitle());
        detailGoal.setContents(detailGoalInfoUI.getDetailGoalContents());
        detailGoal.setStartDay(detailGoalInfoUI.getDetailGoalStartDay());
        detailGoal.setEndDay(detailGoalInfoUI.getDetailGOalEndDay());
        detailGoal.setGoal(detailGoalInfoUI.getSuccess());

        detailGoalHandler = new DetailGoalHandler();
        detailGoalHandler.updateDetailGoal(detailGoal);
    }

    public void deleteDetailGoal(){

        detailGoalHandler = new DetailGoalHandler();
        detailGoalHandler.deleteDetailGoal(detailGoalInfoUI.getDetailG_ID());
    }
    class detailGoalInfoUpdateBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            updateDetailGoal();
            detailGoalInfoUI.dispose();
            goalUI.dispose();
            Start.createGoalController(goal, mainUI);
        }
    }
    class detailGoalInfoDeleteBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            deleteDetailGoal();
            detailGoalInfoUI.dispose();
            goalUI.dispose();
            Start.createGoalController(goal, mainUI);
        }
    }
}
