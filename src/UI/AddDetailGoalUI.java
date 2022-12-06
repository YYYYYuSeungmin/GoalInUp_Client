package UI;

import Entity.Goal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddDetailGoalUI extends JFrame {
    Goal goal;
    JLabel goalTitle;
    JLabel duringGoalLabel;
    JLabel detailGoalTitleLabel;
    JTextField detailGoalTitleText = new JTextField();
    JLabel detailGoalContentsLabel;
    JTextArea detailGoalContentsText;

    JLabel startDayLabel = new JLabel();
    JLabel endDayLabel = new JLabel();
    JTextField startDayText = new JTextField();
    JTextField endDayText = new JTextField();

    JRadioButton goalSuccess[] = new JRadioButton[2];
    String successName[] = {"달성", "진행중"};
    ButtonGroup OXBox = new ButtonGroup();

    JButton addGoalBtn;
    public AddDetailGoalUI(Goal goal){
        this.goal = goal;

        this.setTitle("세부 목표 추가히기!");
        this.setSize(600, 600);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        goalTitle = new JLabel();
        goalTitle.setText(goal.getTitle());
        goalTitle.setFont(new Font("SanSerif", Font.BOLD, 25));
        goalTitle.setSize(400, 30);
        goalTitle.setLocation(15, 15);
        this.add(goalTitle);

        duringGoalLabel = new JLabel();
        duringGoalLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        duringGoalLabel.setSize(400, 30);
        duringGoalLabel.setLocation(15, 60);
        String goalDate = goal.getStartDay() + "     ~     " + goal.getEndDay();
        duringGoalLabel.setText(goalDate);
        this.add(duringGoalLabel);

        detailGoalTitleLabel = new JLabel();
        detailGoalTitleLabel.setText("세부 목표 : ");
        detailGoalTitleLabel.setSize(150, 30);
        detailGoalTitleLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        detailGoalTitleLabel.setLocation(15, 105);
        this.add(detailGoalTitleLabel);

        detailGoalTitleText = new JTextField("");
        detailGoalTitleText.setFont(new Font("SanSerif", Font.BOLD, 25));
        detailGoalTitleText.setLocation(165, 105);
        detailGoalTitleText.setSize(400, 30);
        this.add(detailGoalTitleText);

        detailGoalContentsLabel = new JLabel();
        detailGoalContentsLabel.setText("목표 내용 : ");
        detailGoalContentsLabel.setSize(150, 30);
        detailGoalContentsLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        detailGoalContentsLabel.setLocation(15, 150);
        this.add(detailGoalContentsLabel);

        detailGoalContentsText = new JTextArea(20, 10);
        detailGoalContentsText.setFont(new Font("SanSerif", Font.BOLD, 18));
        detailGoalContentsText.setLocation(165, 150);
        detailGoalContentsText.setLineWrap(true);
        detailGoalContentsText.setSize(400, 200);
        this.add(detailGoalContentsText);

        startDayLabel = new JLabel();
        startDayLabel.setText("시작 날짜");
        startDayLabel.setHorizontalAlignment(JLabel.CENTER);
        startDayLabel.setSize(200, 30);
        startDayLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        startDayLabel.setLocation(45, 380);
        this.add(startDayLabel);

        startDayText = new JTextField();
        String start = goal.getStartDay().replace("-", "");
        startDayText.setText(start);
        startDayText.setSize(200, 50);
        startDayText.setFont(new Font("SanSerif", Font.BOLD, 25));
        startDayText.setLocation(45, 415);
        startDayText.setHorizontalAlignment(JLabel.CENTER);
        this.add(startDayText);

        JLabel toDate = new JLabel("~");
        toDate.setHorizontalAlignment(JLabel.CENTER);
        toDate.setSize(50, 30);
        toDate.setFont(new Font("SanSerif", Font.BOLD, 25));
        toDate.setLocation(260, 420);
        this.add(toDate);

        endDayLabel = new JLabel();
        endDayLabel.setText("끝 날짜");
        endDayLabel.setHorizontalAlignment(JLabel.CENTER);
        endDayLabel.setSize(200, 30);
        endDayLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        endDayLabel.setLocation(325, 380);
        this.add(endDayLabel);

        endDayText = new JTextField();
        String end = goal.getStartDay().replace("-", "");
        endDayText.setText(end);
        endDayText.setSize(200,50);
        endDayText.setHorizontalAlignment(JLabel.CENTER);
        endDayText.setFont(new Font("SanSerif", Font.BOLD, 25));
        endDayText.setLocation(325, 415);
        this.add(endDayText);

        for (int i = 0; i < 2; i++){
            goalSuccess[i] = new JRadioButton(successName[i]);
            goalSuccess[i].setSize(110, 50);
            goalSuccess[i].setFont(new Font("SanSerif", Font.BOLD, 25));
            goalSuccess[i].setLocation(15 + (115 * i), 480);
            OXBox.add(goalSuccess[i]);
            this.add(goalSuccess[i]);
        }
        goalSuccess[0].setSelected(false);
        goalSuccess[1].setSelected(true);

        addGoalBtn = new JButton();
        addGoalBtn.setText("등록하기");
        addGoalBtn.setFont(new Font("SanSerif", Font.BOLD, 25));
        addGoalBtn.setSize(150, 50);
        addGoalBtn.setOpaque(true);
        addGoalBtn.setBackground(Color.lightGray);
        addGoalBtn.setLocation(415, 480);
        this.add(addGoalBtn);

        this.revalidate();
        this.repaint();
    }
    public String getStartDay(){
        return startDayText.getText();
    }
    public String getEndDay(){
        return endDayText.getText();
    }
    public String getTitle(){
        return detailGoalTitleText.getText();
    }
    public boolean isSuccess(){
        if (goalSuccess[0].isSelected() == true){
            return true;
        }
        else {
            return false;
        }
    }
    public String getcontents(){
        return detailGoalContentsText.getText();
    }
    public void setAddDetailGoalBtnListener(ActionListener listener){
        addGoalBtn.addActionListener(listener);
    }

}
