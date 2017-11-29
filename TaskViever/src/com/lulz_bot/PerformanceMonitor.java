package com.lulz_bot;


import com.sun.management.OperatingSystemMXBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class PerformanceMonitor {

    static OperatingSystemMXBean mbean =
            (com.sun.management.OperatingSystemMXBean)
                    ManagementFactory.getOperatingSystemMXBean();

    public static void getProcesses()
    {
        try
        {
        String line;
        Process process = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
        BufferedReader input =
                new BufferedReader(new InputStreamReader(process.getInputStream(), "Cp866"));
        while ((line = input.readLine()) != null) {
            //System.out.print(p.info().user().get());
            System.out.println(line);
        }
            input.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static int getAvailableRam()
    {
        return (int)(mbean.getFreePhysicalMemorySize() / 1048576);
    }

    public static int getTotalRam()
    {
        return (int)(mbean.getTotalPhysicalMemorySize() / 1048576);
    }

    public static int getUsedRam()
    {
        return (int)((mbean.getTotalPhysicalMemorySize() - mbean.getFreePhysicalMemorySize()) / 1048576);
    }

    public static int getCpuLoad()
    {
        double load = 0;
        for(int i=0; i<10; i++) {
            load = mbean.getSystemCpuLoad();
            if ((load < 0.0 || load > 1.0) && load != -1.0) {
                throw new RuntimeException("getSystemCpuLoad() returns " + load
                        + " which is not in the [0.0,1.0] interval");
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (int)(load * 100);
    }

    public static void drawProgressBar(int progress)
    {
        char[] progressBar = new char[12];

        for (int i = 0; i < progressBar.length; i++) {
            if (i == 0) progressBar[i] = '[';
            if (i == 11) progressBar[i] = ']';
            if (i != 0 && i != 11)
            {
                if (i <= progress)
                    progressBar[i] = '|';
                else
                    progressBar[i] = '-';
            }
            System.out.print(progressBar[i]);
        }
    }
}
