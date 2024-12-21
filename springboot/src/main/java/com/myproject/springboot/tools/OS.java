package com.myproject.springboot.tools;

import java.io.*;

public class OS {
    public   String osname;

    public  OS() {
        String osArch = System.getProperty("os.arch");
        String osName = System.getProperty("os.name").toLowerCase();


        if (osName.contains("windows")) {
            if (osArch.contains("86")) {
                osname = "windows";
            } else {
                osname = "?";
            }
        } else if (osName.contains("linux")) {
            // Termux typically runs on Android, which is based on Linux
            if (osArch.contains("arm") || osArch.contains("aarch")) {
                osname = "termux";
            } else {
                osname = "wsl";
            }
        } else {
            osname = "?";
        }
    }

        public String runcommand(String command) throws IOException {
            StringBuilder output = new StringBuilder();
            String str;
            while ((str=Runtime.getRuntime().exec(command).inputReader().readLine())!=null){
                output.append(str);
            }
            return  output.toString();
        }
}
