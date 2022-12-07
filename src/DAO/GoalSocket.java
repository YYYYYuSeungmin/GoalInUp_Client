package DAO;

import Entity.Goal;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class GoalSocket {
    Socket socket = new Socket();
    InetSocketAddress sockAddr = null;
    String SERVER_IP = "192.168.13.1";
    int SERVER_PORT = 9999;

    InputStream is;
    OutputStream os;
    BufferedReader br;
    PrintWriter pw;

    ArrayList<Goal> goalList;
    public GoalSocket(){
        try{ //소켓 및 기본적인 스트림 열기
            sockAddr = new InetSocketAddress(SERVER_IP, SERVER_PORT);
            socket.connect(sockAddr);

            os = socket.getOutputStream();
            is = socket.getInputStream();  //사용자로 부터 데이터를 입력받는 스트림
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            pw = new PrintWriter(new OutputStreamWriter(os, "utf-8"), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean insertGoal(Goal goal){
        String msg = null;
        try{
            msg = "B01"; //B01은 목표등록 신호
            pw.println(msg);
            pw.flush();

//            pw.println(goal.getgID());
            pw.println(goal.getUserID());
            pw.println(goal.getTitle());
            pw.println(goal.getStartDay());
            pw.println(goal.getEndDay());
            pw.println(goal.isGoal());
            pw.flush();
            System.out.println("목표등록 전송 완료");

            msg = br.readLine();

        } catch(IOException e){
            e.printStackTrace();
        } finally {
            closeSocket();
        }
        if (msg.equals("0")) return true;
        else return false;
    }
    public ArrayList<Goal> getGoalList(String userID){
        goalList = new ArrayList<>();
        try {
            String msg = "B02"; //B02는 유저의 목표리스트를 요청하는 신호
            pw.println(msg);
            pw.flush();

            pw.println(userID); // 해당 유저임을 알려줌
            pw.flush();
            System.out.println(userID + "유저의 목표 리스트 요청");

            int count = Integer.parseInt(br.readLine()); //목표 리스트의 개수를 먼저 받는다
            System.out.println(userID + "의 목표 개수 : " + count);
            //카운트 만큼 목표리스트에 골을 추가한다.
            for (int i = 0; i < count; i++){
                Goal goal = new Goal();
                goal.setgID(Integer.parseInt(br.readLine()));
                goal.setUserID(br.readLine());
                goal.setTitle(br.readLine());
                goal.setStartDay(br.readLine());
                goal.setEndDay(br.readLine());
                goal.setGoal(Boolean.parseBoolean(br.readLine()));

                goalList.add(goal);
            }
        } catch (IOException e) {
            System.out.println("목표 리스트 요청중 에러 발생");
            throw new RuntimeException(e);
        } finally {
            closeSocket();
        }
        return goalList;
    }

    public boolean removeGoal(Goal goal){
        String msg;
        try{
            msg = "B03"; //B03은 목표 삭제 신호
            pw.println(msg);
            pw.flush();

            pw.println(goal.getgID());
            pw.flush();

            msg = br.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeSocket();
        }
        if (msg.equals("0")) return true;
        else return false;
    }

    public boolean updateGoal(Goal goal){
        String msg = null;
        try{
            msg = "B04";
            pw.println(msg);
            pw.flush();

            pw.println(goal.getgID());
            pw.println(goal.getUserID());
            pw.println(goal.getTitle());
            pw.println(goal.getStartDay());
            pw.println(goal.getEndDay());
            pw.println(goal.isGoal());
            pw.flush();

            msg = br.readLine();

        } catch(IOException e){
            e.printStackTrace();
        } finally {
            closeSocket();
        }
        if (msg.equals("0")) return true;
        else return false;
    }

    public Goal getGoal(int gID){
        String msg = "B05"; //목표 요청 메세지
        Goal goal = new Goal();
        try{
            msg = "B05";
            pw.println(msg);
            pw.flush();

            pw.println(gID);
            pw.flush();

            goal.setgID(Integer.parseInt(br.readLine()));
            goal.setUserID(br.readLine());
            goal.setTitle(br.readLine());
            goal.setStartDay(br.readLine());
            goal.setEndDay(br.readLine());
            goal.setGoal(Boolean.parseBoolean(br.readLine()));

        } catch(IOException e){
            e.printStackTrace();
        } finally {
            closeSocket();
        }

        return goal;
    }
    private void closeSocket(){
        try {
            if (br != null) br.close();
            if (pw != null) pw.close();
            if (is != null) is.close();
            if (os != null) os.close();
            if (socket != null) socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
