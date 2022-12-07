package UI;

import Entity.User;
import UI.Component.TextHint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AccountInfoUI extends JFrame {
    User user;

    JLabel idLabel = new JLabel("아이디");
    JTextField inputID = new JTextField();

    JLabel pwLabel = new JLabel("비밀번호");
    JPasswordField inputPW = new JPasswordField();

    JLabel nameLabel = new JLabel("이름");
    JTextField inputName = new JTextField();

    JLabel birthLabel = new JLabel("생년월일");
    JTextField inputBirth = new JTextField();

    JLabel phoneLabel = new JLabel("전화번호");
    JTextField inputPhone = new JTextField();

    JButton updateBtn = new JButton();

    JButton deleteBtn = new JButton();
    public AccountInfoUI(User user){
        this.user = user;
        this.setBackground(Color.lightGray);
        this.setTitle(user.getName() + "님의 정보");
        this.setLayout(null);
        this.setSize(1000, 800);
        this.setLocation(300, 150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //아이디 라벨
        //JLabel idLabel = new JLabel("아이디");
        idLabel.setLocation(100, 180);
        idLabel.setSize(200,50);
        idLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(idLabel);

        inputID.setText(user.getId());
        inputID.setLocation(350, 180);
        inputID.setSize(500, 50);
        inputID.setFont(new Font("SanSerif", Font.BOLD, 25));
        inputID.setEnabled(false);
        this.add(inputID);

        //비밀번호 라벨
        //JLabel pwLabel = new JLabel("비밀번호");
        pwLabel.setLocation(100, 250);
        pwLabel.setSize(200,50);
        pwLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(pwLabel);

        inputPW.setLocation(350, 250);
        inputPW.setSize(500, 50);
        inputPW.setFont(new Font("SanSerif", Font.BOLD, 25));
        inputPW.setText(user.getPw());
        this.add(inputPW);

        nameLabel.setLocation(100, 320);
        nameLabel.setSize(200,50);
        nameLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(nameLabel);

        inputName.setLocation(350, 320);
        inputName.setSize(500, 50);
        inputName.setFont(new Font("SanSerif", Font.BOLD, 25));
        inputName.setText(user.getName());
        this.add(inputName);

        birthLabel.setLocation(100, 390);
        birthLabel.setSize(200,50);
        birthLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(birthLabel);

        inputBirth.setText(user.getBirth());
        inputBirth.setLocation(350, 390);
        inputBirth.setSize(500, 50);
        inputBirth.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(inputBirth);

        phoneLabel.setLocation(100, 460);
        phoneLabel.setSize(200,50);
        phoneLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        this.add(phoneLabel);

        inputPhone.setLocation(350, 460);
        inputPhone.setSize(500, 50);
        inputPhone.setFont(new Font("SanSerif", Font.BOLD, 25));
        inputPhone.setText(user.getPhoneNum());
        this.add(inputPhone);

        updateBtn.setText("정보수정");
        updateBtn.setFont(new Font("SanSerif", Font.BOLD, 25));
        updateBtn.setSize(200, 200);
        updateBtn.setLocation(650, 550);
        this.add(updateBtn);

        deleteBtn.setText("회원탈퇴");
        deleteBtn.setFont(new Font("SanSerif", Font.BOLD, 25));
        deleteBtn.setSize(200, 200);
        deleteBtn.setLocation(430, 550);
        this.add(deleteBtn);


    }

    public String getInputID(){
        return inputID.getText();
    }

    //PW라벨 입력값을 반환
    public String getInputPW(){
        return new String(inputPW.getPassword());
    }

    //이름라벨 입력값을 반환
    public String getInputName(){
        return inputName.getText();
    }

    //생년월일라벨 입력값을 반환
    public String getInputBirth(){
        return inputBirth.getText();
    }

    //전화번호라벨 입력값을 반환
    public String getInputPhone(){
        return inputPhone.getText();
    }

    public void setUpdateButtonListener(ActionListener listener){
        updateBtn.addActionListener(listener);
    }
    public void setDeleteButtonListener(ActionListener listener){
        deleteBtn.addActionListener(listener);
    }
}
