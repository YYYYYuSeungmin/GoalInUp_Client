package UI;

import java.awt.*;
import java.awt.event.ActionListener;

import UI.Component.TextHint;
import UI.Component.TitleLabel;
import Entity.User;

import javax.swing.*;

public class RegisterUI extends JFrame {
    User user = new User();

    JLabel infoBG = new JLabel();

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

    JButton registerBtn = new JButton("회원가입");

    JButton cancleBtn = new JButton("취소하기");

    public RegisterUI() {
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
        TextHint hint = new TextHint(inputID, "아이디를 입력해주세요.");
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

        //이름 라벨
        //JLabel nameLabel = new JLabel("이름");
        nameLabel.setLocation(100, 320);
        nameLabel.setSize(200,50);
        nameLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        infoBG.add(nameLabel);

        //이름을 입력받는 라벨
        //JPasswordField inputName = new JPasswordField();
        inputName.setLocation(350, 320);
        inputName.setSize(500, 50);
        inputName.setFont(new Font("SanSerif", Font.BOLD, 25));
        TextHint nameHint = new TextHint(inputName, "이름을 입력해주세요");
        infoBG.add(inputName);

        //생년월일 라벨
        //JLabel birthLabel = new JLabel("생년월일");
        birthLabel.setLocation(100, 390);
        birthLabel.setSize(200,50);
        birthLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        infoBG.add(birthLabel);

        //생년월일을 입력받는 라벨
        //JPasswordField inputBirth = new JPasswordField();
        inputBirth.setLocation(350, 390);
        inputBirth.setSize(500, 50);
        inputBirth.setFont(new Font("SanSerif", Font.BOLD, 25));
        TextHint birthHint = new TextHint(inputBirth, "ex.) 19990520");
        infoBG.add(inputBirth);

        //전화번호 라벨
        //JLabel phoneLabel = new JLabel("전화번호");
        phoneLabel.setLocation(100, 460);
        phoneLabel.setSize(200,50);
        phoneLabel.setFont(new Font("SanSerif", Font.BOLD, 25));
        infoBG.add(phoneLabel);

        //생년월일을 입력받는 라벨
        //JPasswordField inputPhone = new JPasswordField();
        inputPhone.setLocation(350, 460);
        inputPhone.setSize(500, 50);
        inputPhone.setFont(new Font("SanSerif", Font.BOLD, 25));
        TextHint phoneHint = new TextHint(inputPhone, "ex.) 01012345678");
        infoBG.add(inputPhone);

        //로그인 버튼 생성
        //JButton registerBtn = new JButton("회원가입");
        registerBtn.setFont(new Font("SanSerif", Font.BOLD, 25));
        registerBtn.setSize(200, 200);
        registerBtn.setLocation(650, 550);
        infoBG.add(registerBtn);

        //취소 버튼 생성
        //JButton cancleBtn = new JButton("취소하기");
        cancleBtn.setFont(new Font("SanSerif", Font.BOLD, 25));
        cancleBtn.setSize(200, 200);
        cancleBtn.setLocation(430, 550);
        infoBG.add(cancleBtn);
    }

    //ID라벨 입력값을 반환
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

    //회원가입 버튼 액션리스너 설정
    public void setRegisterBtnListener(ActionListener listener){
        registerBtn.addActionListener(listener);
    }

    //취소하기 버튼 액션리스너 설정
    public void setCancleBtnListener(ActionListener listener){
        cancleBtn.addActionListener(listener);
    }

    //라벨 모든값 초기화
    public void resetLabel(){
        inputBirth.setText("");
        inputID.setText("");
        inputPW.setText("");
        inputName.setText("");
        inputPhone.setText("");
    }
}