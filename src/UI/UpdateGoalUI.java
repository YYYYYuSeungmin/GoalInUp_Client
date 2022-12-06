package UI;

import Entity.Goal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UpdateGoalUI extends JFrame {
    JLabel startDayLabel = new JLabel();
    JLabel endDayLabel = new JLabel();
    JTextField startDayText = new JTextField();
    JTextField endDayText = new JTextField();

    JLabel titleLabel = new JLabel();
    JTextField titleText = new JTextField();

    JButton updateButton = new JButton();
    JRadioButton goalSuccess[] = new JRadioButton[2];
    String successName[] = {"달성", "진행중"};
    ButtonGroup OXBox = new ButtonGroup();

    Goal goal;
    public UpdateGoalUI(Goal goal){
        this.goal = goal;

        this.setTitle("목표 수정하기 !");
        this.setSize(600, 300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

//        startDayLabel = new JLabel();
        startDayLabel.setText("시작 날짜");
        startDayLabel.setHorizontalAlignment(JLabel.CENTER);
        startDayLabel.setSize(200, 30);
        startDayLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        startDayLabel.setLocation(15, 15);
        this.add(startDayLabel);

//        startDayText = new JTextField();
        String start = goal.getStartDay().replace("-","");
        startDayText.setText(start);
        startDayText.setSize(200, 50);
        startDayText.setFont(new Font("SanSerif", Font.BOLD, 25));
        startDayText.setLocation(15, 50);
        startDayText.setHorizontalAlignment(JLabel.CENTER);
        this.add(startDayText);

        JLabel toDate = new JLabel("~");
        toDate.setHorizontalAlignment(JLabel.CENTER);
        toDate.setSize(50, 30);
        toDate.setFont(new Font("SanSerif", Font.BOLD, 25));
        toDate.setLocation(230, 50);
        this.add(toDate);

//        endDayLabel = new JLabel();
        endDayLabel.setText("끝 날짜");
        endDayLabel.setHorizontalAlignment(JLabel.CENTER);
        endDayLabel.setSize(200, 30);
        endDayLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        endDayLabel.setLocation(295, 15);
        this.add(endDayLabel);

//        endDayText = new JTextField();
        String end = goal.getEndDay().replace("-", "");
        endDayText.setText(end);
        endDayText.setSize(200,50);
        endDayText.setHorizontalAlignment(JLabel.CENTER);
        endDayText.setFont(new Font("SanSerif", Font.BOLD, 25));
        endDayText.setLocation(295, 50);
        this.add(endDayText);


//        titleLabel = new JLabel();
        titleLabel.setText("타이틀 :");
        titleLabel.setSize(100, 50);
        titleLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        titleLabel.setLocation(15, 130);
        this.add(titleLabel);

//        titleText = new JTextField();
        titleText.setText(goal.getTitle());
        titleText.setSize(400, 50);
        titleText.setFont(new Font("SanSerif", Font.BOLD, 25));
        titleText.setLocation(130, 130);
        this.add(titleText);

//        addButton = new JButton();
        updateButton.setText("수정완료");
        updateButton.setFont(new Font("SanSerif", Font.BOLD, 20));
        updateButton.setSize(130, 50);
        updateButton.setLocation(430, 200);
        this.add(updateButton);

        for (int i = 0; i < 2; i++){
            goalSuccess[i] = new JRadioButton(successName[i]);
            goalSuccess[i].setSize(110, 50);
            goalSuccess[i].setFont(new Font("SanSerif", Font.BOLD, 25));
            goalSuccess[i].setLocation(15 + (115 * i), 200);
            OXBox.add(goalSuccess[i]);
            this.add(goalSuccess[i]);
        }
        if (goal.isGoal()){
            goalSuccess[0].setSelected(true);
            goalSuccess[1].setSelected(false);
        } else{
            goalSuccess[0].setSelected(false);
            goalSuccess[1].setSelected(true);
        }

        this.revalidate();
        this.repaint();
    }
    public void updateGoal(){
        goal.setTitle(titleText.getText());
        goal.setStartDay(startDayText.getText());
        goal.setEndDay(endDayText.getText());
        goal.setGoal(goalSuccess[0].isSelected());
    }
    public Goal getGoal(){
        return goal;
    }
    public void setUpdateButtonListsner(ActionListener listener){
        updateButton.addActionListener(listener);
    }
}
