package Controller;

import DAO.GoalHandler;
import Entity.Goal;
import UI.GoalUI;
import UI.MainUI;
import UI.UpdateGoalUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateGoalController {
    UpdateGoalUI UGUI;
    GoalHandler goalHandler;
    GoalUI goalUI;
    Goal goal;
    MainUI mUI;
    public UpdateGoalController(Goal goal, MainUI mUI, GoalUI goalUI) {
        this.goal = goal;
        this.mUI = mUI;
        this.goalUI = goalUI;

        UGUI = new UpdateGoalUI(goal);

        UGUI.setUpdateButtonListsner(new UpdateButtonActionListener());
    }
    public void setGoal(){
        UGUI.updateGoal();
        goal = UGUI.getGoal();
    }
    class UpdateButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            setGoal();
            goalHandler = new GoalHandler();
            goalHandler.updateGoal(goal);

            goalHandler = new GoalHandler();
            mUI.setGoalList(goalHandler.getGoalList(goal.getUserID()));
            mUI.removeGoalPanel();
            mUI.drawTodayGoal();
            goalUI.dispose();
            UGUI.dispose();

            Start.createGoalController(goal, mUI);
        }
    }
}
