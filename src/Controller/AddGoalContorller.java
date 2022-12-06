package Controller;

import DAO.GoalHandler;
import Entity.Goal;
import UI.AddGoalUI;
import UI.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGoalContorller {
    AddGoalUI addgoalUI;
    GoalHandler goalHandle;
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
        goal.setGoal(addgoalUI.getGoal());


    }

    public void insertGoal(){
        goalHandle = new GoalHandler();
        boolean check = goalHandle.insertGoal(goal);

        if (check == true){
            System.out.println("목표 등록 성공");
        }
        else{
            System.out.println("목표 등록 실패");
        }
    }

    class addButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            createGoal();
            insertGoal();
            goalHandle = new GoalHandler();
            mUI.setGoalList(goalHandle.getGoalList(userID));

            mUI.removeGoalPanel();
            mUI.drawTodayGoal();

            addgoalUI.dispose();
        }
    }
}
