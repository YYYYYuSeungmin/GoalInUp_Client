package Controller;

import DAO.UserSocket;
import Entity.User;
import UI.RegisterUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class RegisterController {
    RegisterUI regUI;
    User user;
    UserSocket uDAO;

    public RegisterController(){
        user = new User();
        regUI = new RegisterUI();

        regUI.setRegisterBtnListener(new registerButtonListener());
        regUI.setCancleBtnListener(new cancleButtonListener());
    }

    class cancleButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            regUI.dispose();
            Start.createLogin();
        }
    }
    class registerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            uDAO = new UserSocket();

            user.setId(regUI.getInputID());
            user.setPw(regUI.getInputPW());
            user.setName(regUI.getInputName());
            user.setBirth(regUI.getInputBirth());
            user.setPhoneNum(regUI.getInputPhone());

            boolean check = uDAO.registerMember(user);

            if (check == true) {
                regUI.dispose();
                Start.createLogin();
                JOptionPane.showMessageDialog(regUI, "회원가입 성공", "회원가입 결과", INFORMATION_MESSAGE);
            } else {
                regUI.resetLabel();
                JOptionPane.showMessageDialog(regUI, "회원가입 실패", "회원가입 결과", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
