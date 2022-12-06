package Controller;

import DAO.UserDAO;
import Entity.User;
import UI.RegisterUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
    RegisterUI regUI;
    User user;
    UserDAO uDAO;

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
            uDAO = new UserDAO();

            user.setId(regUI.getInputID());
            user.setPw(regUI.getInputPW());
            user.setName(regUI.getName());
            user.setBirth(regUI.getInputBirth());
            user.setPhoneNum(regUI.getInputPhone());

            boolean check = uDAO.registerMember(user);
            System.out.println(check);
            if (check == true) {
                regUI.dispose();
                Start.createLogin();

            } else {
                regUI.resetLabel();
                //정보를 올바르게 입력해주세요. (대화형 옵션)
            }
        }
    }
}
