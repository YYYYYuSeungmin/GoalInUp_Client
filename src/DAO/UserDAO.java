package DAO;

import Entity.User;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class UserDAO {
    Socket socket = new Socket();
    InetSocketAddress sockAddr = null;
    String SERVER_IP = "192.168.13.1";
    int SERVER_PORT = 6420;

    InputStream is;
    OutputStream os;
    BufferedReader br = null;
    PrintWriter pw = null;

    public UserDAO(){
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
    public boolean registerMember(User user){ //회원 가입 처리
        String msg = null;
        try {
            msg = "A01"; //회원가입 통신임을 알림
            pw.println(msg); //PrintWriter를 이용한 메세지 송신
            pw.flush();
            System.out.println("회원가입 요청 전송 완료");

            pw.println(user.getId());
            pw.println(user.getPw());
            pw.println(user.getName());
            pw.println(user.getBirth());
            pw.println(user.getPhoneNum());
            pw.flush();
            System.out.println("전송 완료");

            msg = br.readLine();
        } catch (UnknownHostException e){
            System.out.println("Unknown Host");
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            closeSocket();
        }
        if (msg.equals("0")){
            return true;
        }
        else return false;
    }

    public User loginMember(String ID, String PW){
        User user = null;
        String msg = null;
        try {
            msg ="A02";
            pw.println(msg); //PrintWriter를 이용한 메세지 송신
            pw.flush();
            System.out.println("로그인 요청 전송 완료");

            pw.println(ID);
            pw.println(PW);
            pw.flush();
            System.out.println("정보 전달 완료");

            String check = br.readLine(); //로그인 허가/불허 판별

            if(check.equals("0")){ //로그인 허가
                user = new User();
                user.setId(br.readLine());
                user.setPw(br.readLine());
                user.setName(br.readLine());
                user.setPhoneNum(br.readLine());
                user.setBirth(br.readLine());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeSocket();
        }

        return user;
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
