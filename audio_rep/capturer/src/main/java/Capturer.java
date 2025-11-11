import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class Capturer {

    public static void main(String[] args)throws Exception {
        Communicator communicator = Util.initialize();

        ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Server", "ws -h 192.168.131.139 -p 9099");

        SubjectImpl impl = new SubjectImpl();

        Sender sender = new Sender(impl);
        sender.init();
        sender.start();

        adapter.add(impl, Util.stringToIdentity("Subject"));

        adapter.activate();

        communicator.waitForShutdown();

        
    }
    
}
