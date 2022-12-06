package UI;

import UI.Component.TextHint;
import UI.Component.TitleLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame{
    private JLabel infoBG = new JLabel();
    private JLabel idLabel = new JLabel("아이디");
    private JTextField inputID = new JTextField();
    private JLabel pwLabel = new JLabel("비밀번호");
    private JPasswordField inputPW = new JPasswordField();
    private JButton loginBtn = new JButton("로그인");
    private JButton registerBtn = new JButton("회원가입");
    private JButton exitBtn = new JButton("종료하기");

    Calendar calendar = new Calendar();
    public LoginUI(){
        this.setTitle("Goal In Up !");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1600, 1024);
        new TitleLabel(this, 800, 100, 350, 0); //타이틀 라벨 생성
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //JLabel infoBG = new JLabel();
        infoBG.setOpaque(true);
        infoBG.setBackground(Color.lightGray);
        infoBG.setSize(1000, 800);
        infoBG.setLocation(300, 150);
        this.add(infoBG);

        //아이디 라벨
        //JLabel idLabel = new JLabel("아이디");
        idLabel.setLocation(100, 180);
        idLabel.setSize(200,50);
        idLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        infoBG.add(idLabel);

        //아이디 입력받는 라벨
        //JTextField inputID = new JTextField();
        inputID.setLocation(350, 180);
        inputID.setSize(500, 50);
        inputID.setFont(new Font("SanSerif", Font.BOLD, 25));
        TextHint idHint = new TextHint(inputID, "아이디를 입력해주세요");
        infoBG.add(inputID);

        //비밀번호 라벨
        //JLabel pwLabel = new JLabel("비밀번호");
        pwLabel.setLocation(100, 250);
        pwLabel.setSize(200,50);
        pwLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        infoBG.add(pwLabel);

        //비밀번호 입력받는 라벨
        //JPasswordField inputPW = new JPasswordField();
        inputPW.setLocation(350, 250);
        inputPW.setSize(500, 50);
        inputPW.setFont(new Font("SanSerif", Font.BOLD, 25));
        infoBG.add(inputPW);


        //로그인 버튼 생성
        //JButton loginBtn = new JButton("로그인");
        loginBtn.setFont(new Font("SanSerif", Font.BOLD, 25));
        loginBtn.setSize(200, 200);
        loginBtn.setLocation(650, 550);
        infoBG.add(loginBtn);

        //회원가입 버튼 생성
        //JButton registerBtn = new JButton("회원가입");
        registerBtn.setFont(new Font("SanSerif", Font.BOLD, 25));
        registerBtn.setSize(200, 200);
        registerBtn.setLocation(430, 550);
        infoBG.add(registerBtn);

        //종료 버튼 생성
        //JButton exitBtn = new JButton("종료하기");
        exitBtn.setFont(new Font("SanSerif", Font.BOLD, 14));
        exitBtn.setSize(100, 50);
        exitBtn.setLocation(1450, 900);
        this.add(exitBtn);
    }

    //id입력값 받기
    public String getIDLabel(){
        return inputID.getText();
    }

    //pw입력값 받기
    public String getPWLabel(){
        return new String(inputPW.getPassword());
    }
    //로그인 버튼 액션리스너 지정
    public void setLoginButtonListener(ActionListener listener){
        loginBtn.addActionListener(listener);
    }
    //회원가입 버튼 액션리스너 지정
    public void setRegisterButtonListener(ActionListener listener){
        registerBtn.addActionListener(listener);
    }
    //종료 버튼 액션리스너 지정
    public void setExitButtonListener(ActionListener listener){
        exitBtn.addActionListener(listener);
    }
}