package ie.atu.sw;
import java.util.EventListener;

/**
 * @author James Murray
 * @version 1.0
 * @since 19.0.1
 *
 * An interface used to allow communication between the MainFrame and the
 * TextPanel without direct association. Taken from Cave of Programming
 */

public interface FormListener extends EventListener {
    //Time complexity depends on the implementation
    void formEventOccurred(FormEvent e);
}
