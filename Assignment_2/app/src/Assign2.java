import java.util.Properties;

public class Assign2 {
    private static void cpuInfo() {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of CPU cores: " + cores);
    }
    private static void memoryInfo() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("Free Memory (bytes): " + freeMemory);
        System.out.println("Total Memory (bytes): " + totalMemory);
        System.out.println("Used Memory (bytes): " + usedMemory);
    }

    private static void directoryInfo() {
        String userDir = System.getProperty("user.dir");
        String userHome = System.getProperty("user.home");

        System.out.println("Current Working Directory: " + userDir);
        System.out.println("User Home Directory: " + userHome);
        
    }

    private static void osInfo() {
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        String osVersion = props.getProperty("os.version");

        System.out.println("Operating System Name: " + osName);
        System.out.println("Operating System Version: " + osVersion);
 
    }

    private static void javaInfo() {
        Properties props = System.getProperties();
        String javaVersion = props.getProperty("java.version");
        String javaVendor = props.getProperty("java.vendor");
        String javaRuntime = props.getProperty("java.runtime.name");
        String javaVmVersion = props.getProperty("java.vm.version");
        String javaVmName = props.getProperty("java.vm.name");

        System.out.println("Java Version: " + javaVersion);
        System.out.println("Java Vendor: " + javaVendor);
        System.out.println("Java Runtime: " + javaRuntime);
        System.out.println("Java VM Version: " + javaVmVersion);
        System.out.println("Java VM Name: " + javaVmName);
    }
    public static void main(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case "-cpu" -> cpuInfo();
                case "-mem" -> memoryInfo();
                case "-dirs" -> directoryInfo();
                case "-os" -> osInfo();
                case "-java" -> javaInfo();
                default -> {}
            }
        }
    }
}