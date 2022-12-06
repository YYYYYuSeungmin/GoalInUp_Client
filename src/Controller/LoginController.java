package Controller;

import DAO.UserDAO;
import Entity.User;
import UI.LoginUI;
import UI.MainUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    LoginUI lUI;
    UserDAO uDAO;

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
            uDAO= new UserDAO();
            User user = new User();

            String id = lUI.getIDLabel();
            String pw = lUI.getPWLabel();

            user= uDAO.loginMember(id, pw);

            if (user != null){
                System.out.println("로그인 성공");
                lUI.dispose();

                Start.createMain(user);
            }
            else {
                System.out.println("로그인 실패");
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
