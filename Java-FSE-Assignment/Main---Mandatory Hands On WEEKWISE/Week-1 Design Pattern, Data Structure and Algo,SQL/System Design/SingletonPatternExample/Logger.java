public class Logger {
    
    // Step 1: Create a private static object
    private static Logger instance;
    
    // Step 2: Private constructor
    private Logger() {
        System.out.println("Logger Created");
    }
    
    // Step 3: Public method to access the object
    public static Logger getInstance() {
        
        if(instance == null) {
            instance = new Logger();
        }
        
        return instance;
    }
    
    public void log(String message) {
        System.out.println("LOG : " + message);
    }
}
