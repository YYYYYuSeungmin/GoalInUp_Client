package Controller;

import DAO.GoalSocket;
import Entity.Goal;
import UI.GoalUI;
import UI.MainUI;
import UI.UpdateGoalUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateGoalController {
    UpdateGoalUI UGUI;
    GoalSocket goalSocket;
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
            goalSocket = new GoalSocket();
            boolean check = goalSocket.updateGoal(goal);

            if (check == true){
                JOptionPane.showMessageDialog(mUI, "목표 수정 완료", "목표 수정", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(mUI, "목표 수정 실패", "목표 수정", JOptionPane.ERROR_MESSAGE);
            }
            
            goalSocket = new GoalSocket();
            mUI.setGoalList(goalSocket.getGoalList(goal.getUserID()));
            mUI.removeGoalPanel();
            mUI.drawTodayGoal();
            goalUI.dispose();
            UGUI.dispose();

            Start.createGoalController(goal, mUI);
        }
    }
}
