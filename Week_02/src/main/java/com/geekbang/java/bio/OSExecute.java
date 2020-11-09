package com.geekbang.java.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OSExecute {
    public static void command(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" .")).start();

            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String content;
            while ((content = results.readLine()) != null) {
                System.out.println(content);
                BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                while ((content = errors.readLine()) != null) {
                    System.err.println(content);
                    err = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (command.startsWith("CMD /C")) {
                command("CMD /C" + command);
            } else {
                throw new RuntimeException(e);
            }
        }
        if (err) {
            throw new RuntimeException("Errors executing" + command);
        }
    }

}
