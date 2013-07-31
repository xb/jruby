package org.jruby.ast.truffle;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import org.jruby.RubyModule;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

public class TruffleDynamicMethod extends DynamicMethod {
    private final CallTarget callTarget;
    
    public TruffleDynamicMethod(Defn defnNode) {
        this.callTarget = Truffle.getRuntime().createCallTarget(defnNode, defnNode.descriptor);
    }
    
    public static class Arguments extends com.oracle.truffle.api.Arguments {
        public final ThreadContext context;
        public final Object self;
        
        public Arguments(ThreadContext context, Object self) {
            this.context = context;
            this.self = self;
        }
    }
    
    @Override
    public IRubyObject call(ThreadContext context, IRubyObject self, RubyModule clazz, String name, IRubyObject[] args, Block block) {
        System.out.println("calling via truffle");
        return (IRubyObject)callTarget.call(new Arguments(context, self));
    }

    @Override
    public DynamicMethod dup() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
