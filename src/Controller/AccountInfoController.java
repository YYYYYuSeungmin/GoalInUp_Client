package Controller;

import DAO.UserSocket;
import Entity.User;
import UI.AccountInfoUI;
import UI.MainUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class AccountInfoController {
    AccountInfoUI accountInfoUI;
    MainUI mainUI;
    User user;
    UserSocket userSocket;
    public AccountInfoController(User user, MainUI mainUI){
        this.user = user;
        this.mainUI = mainUI;

        accountInfoUI = new AccountInfoUI(user);
        accountInfoUI.setUpdateButtonListener(new UpdateButtonListener());
        accountInfoUI.setDeleteButtonListener(new DeleteButtonListener());
    }

    private void updateUser(){
        user.setId(accountInfoUI.getInputID());
        user.setName(accountInfoUI.getInputName());
        user.setPw(accountInfoUI.getInputPW());
        user.setBirth(accountInfoUI.getInputBirth());
        user.setPhoneNum(accountInfoUI.getInputPhone());
    }

    class UpdateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            updateUser();
            userSocket = new UserSocket();
            boolean check = userSocket.updateMember(user);

            if (check == true) {
                JOptionPane.showMessageDialog(accountInfoUI, "정보수정 성공", "회원정보 수정 결과", INFORMATION_MESSAGE);
                accountInfoUI.dispose();
                mainUI.dispose();
                mainUI = null;
                Start.createMain(user);
            } else {
                JOptionPane.showMessageDialog(accountInfoUI, "정보수정 실패", "회원정보 수정 결과", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class DeleteButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            userSocket = new UserSocket();

            boolean check = userSocket.deleteMember(user.getId());

            if (check == true) {
                JOptionPane.showMessageDialog(accountInfoUI, "탈퇴 성공", "회원 탈퇴 결과", INFORMATION_MESSAGE);
                accountInfoUI.dispose();
                mainUI.dispose();
                mainUI = null;
                Start.createLogin();
            } else {
                JOptionPane.showMessageDialog(accountInfoUI, "탈퇴 실패", "회원 탈퇴 결과", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
