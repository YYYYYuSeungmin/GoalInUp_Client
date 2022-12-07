package Controller;

import DAO.DetailGoalSocket;
import DAO.GoalSocket;
import Entity.DetailGoal;
import Entity.Goal;
import UI.DetailGoalInfoUI;
import UI.GoalUI;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class DetailGoalInfoController {
    GoalUI goalUI;
    Goal goal;
    MainUI mainUI;
    DetailGoal detailGoal;
    DetailGoalInfoUI detailGoalInfoUI;
    GoalSocket goalSocket;

    DetailGoalSocket detailGoalSocket;
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

        detailGoalSocket = new DetailGoalSocket();
        boolean check = detailGoalSocket.updateDetailGoal(detailGoal);
        
        if (check == true) {
            JOptionPane.showMessageDialog(goalUI, "세부목표 수정 완료", "세부목표 수정 결과", INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(goalUI, "세부목표 수정 실팰", "세부목표 수정 결과", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteDetailGoal(){

        detailGoalSocket = new DetailGoalSocket();
        boolean check = detailGoalSocket.deleteDetailGoal(detailGoalInfoUI.getDetailG_ID(), goal.getgID());

        if (check == true) {
            JOptionPane.showMessageDialog(goalUI, "세부목표 삭제 완료", "세부목표 삭제 결과", INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(goalUI, "세부목표 삭제 실팰", "세부목표 삭제 결과", JOptionPane.ERROR_MESSAGE);
        }
    }
    class detailGoalInfoUpdateBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            updateDetailGoal();
            detailGoalInfoUI.dispose();
            goalUI.dispose();

            goalSocket = new GoalSocket();
            mainUI.setGoalList(goalSocket.getGoalList(goal.getUserID()));
            mainUI.removeGoalPanel();
            mainUI.drawTodayGoal();

            goalSocket = new GoalSocket();
            goal = goalSocket.getGoal(goal.getgID());

            Start.createGoalController(goal, mainUI);
        }
    }
    class detailGoalInfoDeleteBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            deleteDetailGoal();
            detailGoalInfoUI.dispose();
            goalUI.dispose();

            goalSocket = new GoalSocket();
            mainUI.setGoalList(goalSocket.getGoalList(goal.getUserID()));
            mainUI.removeGoalPanel();
            mainUI.drawTodayGoal();

            goalSocket = new GoalSocket();
            goal = goalSocket.getGoal(goal.getgID());

            Start.createGoalController(goal, mainUI);
        }
    }
}
