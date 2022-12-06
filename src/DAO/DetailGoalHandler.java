package DAO;

import Entity.DetailGoal;
import Entity.Goal;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class DetailGoalHandler {
    Socket socket = new Socket();
    InetSocketAddress sockAddr = null;
    String SERVER_IP = "192.168.13.1";
    int SERVER_PORT = 6420;

    InputStream is;
    OutputStream os;
    BufferedReader br = null;
    PrintWriter pw = null;

    ArrayList<DetailGoal> detailGoalsList;
    private DetailGoal detailGoal;

    public DetailGoalHandler(){
        try{ //소켓 및 기본적인 스트림 열기
            sockAddr = new InetSocketAddress(SERVER_IP, SERVER_PORT);
            socket.connect(sockAddr);

            os = socket.getOutputStream();
            is = socket.getInputStream();  //사용자로 부터 데이터를 입력받는 스트림

            pw = new PrintWriter(os);
            br = new BufferedReader(new InputStreamReader(is));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DetailGoal> getDetailGoals(int gID){
        detailGoalsList = new ArrayList<>();
        try{
            String msg = "C01"; //세부 목표 리스트 반환을 요청
            pw.println(msg);
            pw.flush();
            //완료되면

            pw.println(gID);
            pw.flush();
            //세부 목표를 찾고자 하는 큰 목표의 고유 id값을 줌.
            System.out.println(gID + "의 세부 목표 리스트 요청");

            int count = Integer.parseInt(br.readLine());
            //총 몇개의 세부 목표가 있는지 받는다.
            System.out.println("세부 목표 개수 : " + count);

            for (int i = 0; i < count; i++){
                DetailGoal dGoal = new DetailGoal();
                dGoal.setDetail_gID(Integer.parseInt(br.readLine()));
                dGoal.setgID(Integer.parseInt(br.readLine()));
                dGoal.setTitle(br.readLine());
                dGoal.setContents(br.readLine());
                dGoal.setStartDay(br.readLine());
                dGoal.setEndDay(br.readLine());
                dGoal.setGoal(Boolean.parseBoolean(br.readLine()));

                detailGoalsList.add(dGoal);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeSocket();
        }

        return detailGoalsList;
    }

    public boolean insertDetailGoal(DetailGoal detailGoal){
        this.detailGoal = detailGoal;
        boolean check;
        System.out.println("세부 목표 등록 요청");
        String msg = "C02"; //
        check = sendDetailGoal(detailGoal, msg);
        if (check == true) return true;
        else return false;
    }

    public boolean updateDetailGoal(DetailGoal detailGoal){
        this.detailGoal = detailGoal;
        boolean check;
        System.out.println("세부 목표 수정 요청");
        String msg = "C03";
        check = sendDetailGoal(detailGoal, msg);

        if (check == true) return true;
        else return false;
    }

    private boolean sendDetailGoal(DetailGoal detailGoal, String msg){
        boolean check;
        try{
            //msg = "C02"; // 세부 목표 등록 요청
            pw.println(msg);
            pw.flush();

            pw.println(detailGoal.getgID());
            pw.println(detailGoal.getDetail_gID());
            pw.println(detailGoal.getTitle());
            pw.println(detailGoal.getContents());
            pw.println(detailGoal.getStartDay());
            pw.println(detailGoal.getEndDay());
            pw.println(detailGoal.isGoal());
            pw.flush();
            System.out.println("정보 전송 완료");
            check = Boolean.parseBoolean(br.readLine());
            System.out.println("결과 : " + check);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeSocket();
        }

        return check;
    }
    public boolean deleteDetailGoal(int detail_gid){
        boolean check;
        System.out.println("세부 목표 삭제 요청");
        try{
            String msg = "C04"; //

            pw.println(msg);
            pw.flush();

            pw.println(detail_gid);
            pw.flush();
            check = Boolean.parseBoolean(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return check;
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
