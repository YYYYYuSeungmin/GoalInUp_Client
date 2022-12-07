package Controller;

import DAO.UserSocket;
import Entity.User;
import UI.LoginUI;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class LoginController {
    LoginUI lUI;
    UserSocket uDAO;

    public LoginController(){
        lUI = new LoginUI();

        lUI.setExitButtonListener(new exitButtonListener());
        lUI.setLoginButtonListener(new loginButtonListener());
        lUI.setRegisterButtonListener(new registerButtonListener());
    }

    //종료버튼 클릭시 시스템 종료
    class exitButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    class loginButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            uDAO= new UserSocket();
            User user = new User();

            String id = lUI.getIDLabel();
            String pw = lUI.getPWLabel();

            user= uDAO.loginMember(id, pw);

            if (user != null){
                System.out.println("로그인 성공");
                JOptionPane.showMessageDialog(lUI, "로그인 성공", "로그인 결과", INFORMATION_MESSAGE);
                lUI.dispose();

                Start.createMain(user);
            }
            else {
                System.out.println("로그인 실패");
                JOptionPane.showMessageDialog(lUI, "로그인 실패", "로그인 결과", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class registerButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            lUI.dispose();
            Start.createRegister();
        }
    }
}
