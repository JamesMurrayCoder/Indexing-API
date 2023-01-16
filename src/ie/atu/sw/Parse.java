package ie.atu.sw;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * An interface to demonstrate the use of interfaces...
 */
public interface Parse {
    /**
     * Parses a file
     * @param file The file to be parsed.
     * @throws Exception .
     */
    //depends on implementation
    void parse(String file) throws Exception;


}
