package Controller;

import DAO.DetailGoalHandler;
import Entity.DetailGoal;
import Entity.Goal;
import UI.AddDetailGoalUI;
import UI.GoalUI;
import UI.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDetailGoalController {
    AddDetailGoalUI addDetailGoalUI;
    DetailGoalHandler detailGoalHandler;
    Goal goal;
    GoalUI goalUI;
    MainUI mainUI;
    DetailGoal detailGoal;

    public AddDetailGoalController(Goal goal, GoalUI goalUI, MainUI mainUI){
        this.goal = goal;
        this.goalUI = goalUI;
        this.mainUI = mainUI;

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
    }
    public void insertDetailGoal(){
        detailGoalHandler = new DetailGoalHandler();
        boolean check = detailGoalHandler.insertDetailGoal(detailGoal);
        if (check == true){
            System.out.println("세부 목표 등록 성공");
        }
        else{
            System.out.println("세부 목표 등록 실패");
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
            Start.createGoalController(goal, mainUI);
        }
    }
}
