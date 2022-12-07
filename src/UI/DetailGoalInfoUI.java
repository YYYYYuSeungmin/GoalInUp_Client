package UI;

import Entity.DetailGoal;
import Entity.Goal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DetailGoalInfoUI extends JFrame {
    JLabel goalTitleLabel = new JLabel();
    JLabel detailGoalTitleLabel = new JLabel();
    JTextField detailGoalTitleText = new JTextField();
    JLabel detailGoalContentsLabel = new JLabel();
    JTextArea detailGoalContentsText = new JTextArea();
    JLabel dateLabel = new JLabel(); //목표 기간
    JTextField startDayText = new JTextField();
    JTextField endDayText = new JTextField();

    JButton updateButton = new JButton(); // 목표 수정 버튼
    JButton deleteButton = new JButton(); // 목표 삭제 버튼

    JRadioButton goalSuccess[] = new JRadioButton[2];
    String successName[] = {"달성", "진행중"};
    ButtonGroup OXBox = new ButtonGroup();

    Goal goal;
    MainUI mainUI;
    DetailGoal detailGoal;
    public DetailGoalInfoUI(Goal goal, DetailGoal detailGoal){
        this.goal = goal;
        this.detailGoal = detailGoal;

        this.setTitle("세부 목표 정보");
        this.setSize(600, 550);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

//        goalTitleLabel = new JLabel();
        goalTitleLabel.setText("목표 : " + goal.getTitle());
        goalTitleLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        goalTitleLabel.setSize(400, 50);
        goalTitleLabel.setLocation(15, 15);
        this.add(goalTitleLabel);

//        detailGoalTitleLabel = new JLabel();
        detailGoalTitleLabel.setText("세부 목표 : ");
        detailGoalTitleLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        detailGoalTitleLabel.setSize(150, 50);
        detailGoalTitleLabel.setLocation(15, 70);
        this.add(detailGoalTitleLabel);

//        detailGoalTitleText = new JTextField();
        detailGoalTitleText.setText(detailGoal.getTitle());
        detailGoalTitleText.setFont(new Font("SanSerif", Font.BOLD, 25));
        detailGoalTitleText.setSize(350, 50);
        detailGoalTitleText.setLocation(165, 70);
        this.add(detailGoalTitleText);

//        detailGoalContentsLabel = new JLabel();
        detailGoalContentsLabel.setText("목표 내용 :");
        detailGoalContentsLabel.setSize(150, 50);
        detailGoalContentsLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        detailGoalContentsLabel.setLocation(15, 135);
        this.add(detailGoalContentsLabel);



//        detailGoalContentsText = new JTextArea(15, 6);
        detailGoalContentsText.setLineWrap(true);
        detailGoalContentsText.setText(detailGoal.getContents());
        detailGoalContentsText.setSize(400, 200);
        detailGoalContentsText.setFont(new Font("SanSerif", Font.BOLD, 25));
        detailGoalContentsText.setLocation(165, 140);
        this.add(detailGoalContentsText);

//        dateLabel = new JLabel();
        dateLabel.setText("목표 기간 : ");
        dateLabel.setSize(150, 30);
        dateLabel.setLocation(15, 355);
        dateLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(dateLabel);

        startDayText.setText(detailGoal.getStartDay());
        startDayText.setSize( 150, 50);
        startDayText.setFont(new Font("SanSerif", Font.BOLD, 25));
        startDayText.setLocation(165, 355);
        startDayText.setHorizontalAlignment(JLabel.CENTER);
        this.add(startDayText);

        JLabel toDate = new JLabel("~");
        toDate.setHorizontalAlignment(JLabel.CENTER);
        toDate.setSize(50, 30);
        toDate.setFont(new Font("SanSerif", Font.BOLD, 25));
        toDate.setLocation(340, 360);
        this.add(toDate);

        endDayText.setText(detailGoal.getEndDay());
        endDayText.setSize(150,50);
        endDayText.setHorizontalAlignment(JLabel.CENTER);
        endDayText.setFont(new Font("SanSerif", Font.BOLD, 25));
        endDayText.setLocation(420, 355);
        this.add(endDayText);

        for (int i = 0; i < 2; i++){
            goalSuccess[i] = new JRadioButton(successName[i]);
            goalSuccess[i].setSize(110, 50);
            goalSuccess[i].setFont(new Font("SanSerif", Font.BOLD, 25));
            goalSuccess[i].setLocation(15 + (115 * i), 420);
            OXBox.add(goalSuccess[i]);
            this.add(goalSuccess[i]);
        }
        if (detailGoal.isGoal()){
            goalSuccess[0].setSelected(true);
            goalSuccess[1].setSelected(false);
        } else{
            goalSuccess[0].setSelected(false);
            goalSuccess[1].setSelected(true);
        }

        updateButton.setText("수정완료");
        updateButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        updateButton.setSize(130, 50);
        updateButton.setLocation(290, 420);
        this.add(updateButton);

        deleteButton.setText("삭제");
        deleteButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        deleteButton.setSize(130, 50);
        deleteButton.setLocation(435, 420);
        this.add(deleteButton);


        this.revalidate();
        this.repaint();
    }
    public boolean getSuccess(){
        if (goalSuccess[0].isSelected() == true){
            return true;
        }
        else {
            return false;
        }
    }
    public int getDetailG_ID(){
        return detailGoal.getDetail_gID();
    }
    public String getDetailGoalTitle(){
        return detailGoalTitleText.getText();
    }
    public String getDetailGoalContents(){
        return detailGoalContentsText.getText();
    }
    public String getDetailGoalStartDay(){
        return startDayText.getText();
    }
    public String getDetailGOalEndDay(){
        return endDayText.getText();
    }
    public void setUpdateButtonListener(ActionListener listener){
        updateButton.addActionListener(listener);
    }
    public void setDeleteButtonListener(ActionListener listener){
        deleteButton.addActionListener(listener);
    }
}
