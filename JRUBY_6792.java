import java.util.ArrayList;
import java.util.List;

import org.jruby.embed.ScriptingContainer;
import org.jruby.embed.internal.EmbedEvalUnitImpl;

class JRUBY_6792 {
    public static void main(String[] args) {
        ScriptingContainer container = new ScriptingContainer();

        // Simulate a garbage collection of softreference to current context
        container.getProvider().getRuntime().getThreadService().setCurrentContext(null);

        container.put("MyConstant", 42);
        container.runScriptlet("a = 1");
        container.runScriptlet("b = 2");
        
    }

    private static void oome() {
        try {
            List<byte[]> l = new ArrayList<byte[]>();
            while(true) {
                l.add(new byte[1024 * 1024]);
            }
        } catch (OutOfMemoryError oome) {
            System.out.println("Forced SoftReference collection: " + oome);
        }
    }

}
