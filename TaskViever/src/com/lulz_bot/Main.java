package com.lulz_bot;
import com.sun.management.OperatingSystemMXBean;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) {
        try {
            String line;
            Process process = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(process.getInputStream(), "Cp866"));
            while ((line = input.readLine()) != null) {
                //System.out.print(p.info().user().get());
                System.out.println(line);
            }

            OperatingSystemMXBean mbean =
                    (com.sun.management.OperatingSystemMXBean)
                            ManagementFactory.getOperatingSystemMXBean();
            long available_ram = mbean.getFreePhysicalMemorySize();
            long total_ram = mbean.getTotalPhysicalMemorySize();
            long used_ram = (total_ram - available_ram);
            available_ram /= 1048576;
            total_ram /= 1048576;
            used_ram /= 1048576;
            System.out.println(used_ram + " MB of RAM used.");
            System.out.println(available_ram + " MB of RAM available.");

            long pb = (used_ram / (total_ram / 100) / 10);
            char[] progressBar = new char[12];

            for (int i = 0; i < progressBar.length; i++) {
                if (i == 0) progressBar[i] = '[';
                if (i == 11) progressBar[i] = ']';
                if (i != 0 && i != 11)
                {
                    if (i <= pb)
                        progressBar[i] = '|';
                    else
                        progressBar[i] = '-';
                }
                System.out.print(progressBar[i]);
            }
            System.out.print(" (" + used_ram / ((total_ram) / 100) + "% RAM usage)");
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}