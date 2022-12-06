package UI;

import Controller.Start;
import Entity.DetailGoal;
import Entity.Goal;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GoalUI extends JFrame implements ListSelectionListener{
//오늘의 목표 리스트에서, 목표를 선택하면 해당 목표에 대한 정보가 나오는 창
    Goal goal;
    MainUI mainUI;
    ArrayList<DetailGoal> detailGoalList;
    JLabel titleLabel = new JLabel(); // 목표 타이틀 라벨
    JButton updateBtn = new JButton(); // 목표 수정 버튼
    JButton deleteBtn = new JButton(); // 목표 삭제 버튼
    JButton addDetailGoalBtn = new JButton();
    JProgressBar progressRate = new JProgressBar(0, 100);
    JLabel dateLabel = new JLabel(); // 시작 ~ 끝
//    JList detailGoal; // 세부 목표 리스트
    JLabel successLabel = new JLabel();

    JList detailGoalsJList = new JList();
    JScrollPane scroll = new JScrollPane();
    public GoalUI(Goal goal, ArrayList<DetailGoal> detailGoalList, MainUI mainUI){
        this.goal = goal;
        this.detailGoalList = detailGoalList;
        this.mainUI = mainUI;

        this.setTitle(goal.getTitle());
        this.setVisible(true);
        this.setSize(600, 800);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }
    public void drawGoalAll(){
        drawGoalInfo();
        drawDetailGoals();
    }
    private void drawGoalInfo(){
//        titleLabel = new JLabel();
        titleLabel.setText("목표         :  " + goal.getTitle());
        titleLabel.setSize(600, 30);
        titleLabel.setLocation(0, 15);
        titleLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(titleLabel);

//        dateLabel = new JLabel();
        String start = goal.getStartDay();
        String end = goal.getEndDay();

        dateLabel.setText("목표 기간 :  " + start + "      ~      " + end);
        dateLabel.setSize(600, 30);
        dateLabel.setLocation(0, 75);
//        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(dateLabel);

//        updateBtn = new JButton("수정");
        updateBtn.setText("수정");
        updateBtn.setSize(80, 40);
        updateBtn.setLocation(400, 200);
        updateBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
        this.add(updateBtn);

        deleteBtn = new JButton("삭제");
        deleteBtn.setSize(80, 40);
        deleteBtn.setLocation(495, 200);
        deleteBtn.setFont(new Font("SanSerif", Font.BOLD, 20));
        this.add(deleteBtn);

        successLabel = new JLabel();
        String sign = "X";
        if (goal.isGoal()){
            sign = "O";
        }
        successLabel.setText("달성 여부 :  " + sign);
        successLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        successLabel.setSize(600, 30);
        successLabel.setLocation(0, 110);
        this.add(successLabel);

        JLabel progressRateLabel = new JLabel("성취율");
        progressRateLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        progressRateLabel.setSize(150, 30);
        progressRateLabel.setLocation(0, 160);
        this.add(progressRateLabel);

        progressRate.setSize(300, 30);
        progressRate.setLocation(0, 200);
        progressRate.setForeground(Color.LIGHT_GRAY);
        progressRate.setValue(0);
        progressRate.setStringPainted(true);
        progressRate.setFont(new Font("SanSerif", Font.BOLD, 25));

        int count = detailGoalList.size();
        if (count != 0){
            int success = 0;
            for (int i = 0; i < count; i++){
                if (detailGoalList.get(i).isGoal()) success += 1;
            }
            float rate = ((float) success / count) * 100;
            int achievementRate = (int) rate;
            progressRate.setValue(achievementRate);
            this.add(progressRate);
        }



        MyPanel panel = new MyPanel();
        panel.setLocation(0,260);
        panel.setSize(600,5);
        this.add(panel);

        addDetailGoalBtn = new JButton("세부 목표 추가");
        addDetailGoalBtn.setFont(new Font("SanSerif", Font.BOLD, 25));
        addDetailGoalBtn.setSize(220, 30);
        addDetailGoalBtn.setLocation(310, 700);
        addDetailGoalBtn.setOpaque(true);
        addDetailGoalBtn.setBackground(Color.lightGray);
        this.add(addDetailGoalBtn);
    }
    class MyPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawLine(0, 0, 600, 0);
        }
    }
    private void drawDetailGoals(){
        DefaultListModel model = createDetailGoalsModel();

        detailGoalsJList = new JList(model);
        detailGoalsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        detailGoalsJList.setFont(new Font("SanSerif", Font.BOLD, 25));
        detailGoalsJList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        detailGoalsJList.addListSelectionListener(this::valueChanged);

        scroll = new JScrollPane(detailGoalsJList);
        scroll.setBounds(35, 280, 500, 400);

        this.add(scroll);
    }
    public void setDeleteBtnListener(ActionListener listener){
        deleteBtn.addActionListener(listener);
    }

    public void setUpdateBtnListener(ActionListener listener){
        updateBtn.addActionListener(listener);
    }

    public void setAddDetailGoalBtnListener(ActionListener listener){
        addDetailGoalBtn.addActionListener(listener);
    }
    private DefaultListModel createDetailGoalsModel(){
        DefaultListModel model = new DefaultListModel();

        int count = this.detailGoalList.size();

        for (int i = 0; i < count; i++) {
            String flag = "X";
            if (detailGoalList.get(i).isGoal()){
                flag = "O";
            }
            String text = flag + " : " + detailGoalList.get(i).getTitle();
            model.addElement(text);
        }
        return model;
    }

    @Override
    public void valueChanged(ListSelectionEvent e){
        int select = 0;
        if (!e.getValueIsAdjusting()) {
            if(detailGoalsJList.getSelectedValue() != null){
                select = detailGoalsJList.getSelectedIndex();
                System.out.println("세부 목표 리스트에서 선택된 값 인덱스 : " + select);
                System.out.println(detailGoalList.get(0).getTitle());
                Start.createDetailGoalInfoController(this, goal, detailGoalList.get(select), mainUI);
                //세부 목표 수정 or 삭제 창 나와야 함. 아마 대화형 옵션?
//                System.out.println(detailGoalList.get(select).getContents());
            }
        }
    }
}
