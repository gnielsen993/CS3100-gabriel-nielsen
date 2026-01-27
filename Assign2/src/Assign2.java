import java.util.Properties;

/**
 * Assignment 2
 * @author Gabriel Nielsen
 */
public class Assign2 {

    /**
     * Displays CPU information.
     */
    private static void cpuInfo() {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of CPU cores: " + cores);
    }

    /**
     * Displays memory information.
     */
    private static void memoryInfo() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("Free Memory (bytes): " + freeMemory);
        System.out.println("Total Memory (bytes): " + totalMemory);
        System.out.println("Used Memory (bytes): " + usedMemory);
    }

    /**
     * Displays directory information.
     */
    private static void directoryInfo() {
        String userDir = System.getProperty("user.dir");
        String userHome = System.getProperty("user.home");

        System.out.println("Current Working Directory: " + userDir);
        System.out.println("User Home Directory: " + userHome);
        
    }

    /**
     * Displays operating system information.
     */
    private static void osInfo() {
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name");
        String osVersion = props.getProperty("os.version");

        System.out.println("Operating System Name: " + osName);
        System.out.println("Operating System Version: " + osVersion);
 
    }

    /**
     * Displays Java runtime information.
     */
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

    /**
     * Main method to process command-line arguments and display system information.
     * @param args Command-line arguments, including:
     *             - "-cpu" for CPU info
     *             - "-mem" for memory info
     *             - "-dirs" for directory info
     *             - "-os" for operating system info
     *             - "-java" for Java runtime info
     */
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-cpu":
                    cpuInfo();
                    break;
                case "-mem":
                    memoryInfo();
                    break;
                case "-dirs":
                    directoryInfo();
                    break;
                case "-os":
                    osInfo();
                    break;
                case "-java":
                    javaInfo();
                    break;
                default:
                    break;
            }
        }
    }
}