package com.myproject.springboot.tools;

import com.jcraft.jsch.*;

import java.io.IOException;
import java.io.InputStream;

public class ssh {
    private final String os=new OS().osname;
    public void connect(String command) throws JSchException, IOException {
        String host,user,password;

        if(os.equals("windows")){
            host = "192.168.1.3";
            user = "u0_a311";
            password = "w1995520";
        }else if(os.equals("termux")){
            host = "192.168.1.2";
            user = "wnlhd";
            password = "Lkxl,1995520";
        }else{
            return;
        }

        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, 22);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }

    public void disconnect(Session session) throws JSchException {
        session.disconnect();;
    }

    public String exec(Session session,String command) throws IOException, JSchException {
        // 打开通道，并执行命令
        Channel channel = session.openChannel("exec");
        InputStream in = channel.getInputStream();
        channel.connect();

        // 读取命令输出
        StringBuilder outputBuffer = new StringBuilder();
        byte[] tmp = new byte[1024];
        while (true) {
            while (in.available() > 0) {
                int i = in.read(tmp, 0, 1024);
                if (i < 0) break;
                outputBuffer.append(new String(tmp, 0, i));
            }
            if (channel.isClosed()) {
                if (in.available() > 0) continue;
                break;
            }
        }
        channel.disconnect();
        return outputBuffer.toString();
    }
}