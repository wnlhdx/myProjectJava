package com.myproject.springboot.APITest;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.myproject.springboot.tools.OS;
import com.myproject.springboot.tools.ssh;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class SSHTest {
    @Test
    public void testSSH() throws JSchException, IOException {
        ssh  SSHClient =new ssh();
        Session session=SSHClient.connect();
        String result=SSHClient.exec(session,"whoami");
        SSHClient.disconnect(session);
        if(new OS().osname.equals("windows")){
            String actual="u0_a311";
            Assertions.assertEquals(result,"u0_a311\n");
        }else{
            Assertions.assertEquals(result,"wnlhd\n");
        }
    }
}
