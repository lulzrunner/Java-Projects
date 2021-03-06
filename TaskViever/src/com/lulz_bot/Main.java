package com.lulz_bot;

public class Main {
    public static void main(String[] args) {
        try {
            int used_ram = PerformanceMonitor.getUsedRam();
            int total_ram = PerformanceMonitor.getTotalRam();
            int available_ram = PerformanceMonitor.getAvailableRam();
            int progress = (used_ram / (total_ram / 100) / 10);
            int cpu_load = PerformanceMonitor.getCpuLoad();

            PerformanceMonitor.getProcesses();

            System.out.println("========================= ======== ================ =========== ============");
            System.out.println(total_ram + " MB total RAM.");
            System.out.println(used_ram + " MB of RAM used.");
            System.out.println(available_ram + " MB of RAM available.");

            PerformanceMonitor.drawProgressBar(progress);

            System.out.print(" (" + used_ram / ((total_ram) / 100) + "% RAM usage)\n");
            System.out.println("========================= ======== ================ =========== ============");
            
            System.out.println(cpu_load + "% (CPU usage)");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}