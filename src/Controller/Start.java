package Controller;

import Entity.DetailGoal;
import Entity.Goal;
import Entity.User;
import UI.GoalUI;
import UI.MainUI;

public class Start {
    private static LoginController loginCon;
    private static RegisterController regCon;
    private static MainController mainCon;
    private static GoalController golCon;
    private static DetailGoalInfoController detailGoalInfoController;
    public static void createLogin(){
        loginCon = new LoginController();
    }

    public static void createMain(User user){
        mainCon = new MainController(user);
    }
    public static void createRegister(){
        regCon = new RegisterController();
    }

    public static void createDetailGoalInfoController(GoalUI goalUI, Goal goal, DetailGoal detailGoal, MainUI mainUI){
        detailGoalInfoController = new DetailGoalInfoController(goalUI, goal, detailGoal, mainUI);
    }
    public static void createGoalController(Goal goal, MainUI mainUI) {
        golCon = new GoalController(goal, mainUI);

    }
    public static void main(String[] args){
        Start.createLogin();
    }

}
