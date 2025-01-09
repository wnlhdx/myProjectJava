package com.myproject.springboot.tools;

import java.io.*;

public class OS {
    public   String osname;

    public  OS() {
        String osArch = System.getProperty("os.arch");
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows")) {
            if (osArch.contains("amd")) {
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

    public String runCommand(String command) throws IOException, InterruptedException {
        StringBuilder output = new StringBuilder();

        // 使用 ProcessBuilder 来启动进程
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        Process process = processBuilder.start();

        // 读取进程的标准输出流
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        }

        // 等待进程终止，并获取退出状态
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            // 可以选择处理非零退出状态
            // 例如，读取错误输出流
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String errorLine;
                while ((errorLine = errorReader.readLine()) != null) {
                    output.append(errorLine).append(System.lineSeparator());
                }
            }
        }

        return output.toString();
    }
}
