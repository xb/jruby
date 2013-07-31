package org.jruby.ast.truffle;

import com.oracle.truffle.api.frame.VirtualFrame;
import org.jruby.RubyFixnum;
import org.jruby.runtime.ThreadContext;

public abstract class ExpressionNode extends RubyNode {
    public abstract Object execute(ThreadContext context, VirtualFrame frame);
    
    public static class AddNode extends ExpressionNode {
        private final ExpressionNode left, right;
        
        public AddNode(ExpressionNode left, ExpressionNode right) {
            this.left = left;
            this.right = right;
        }
        
        public Object execute(ThreadContext context, VirtualFrame frame) {
            Object left = this.left.execute(context, frame);
            Object right = this.right.execute(context, frame);
            
            if (left instanceof RubyFixnum) {
                if (right instanceof RubyFixnum) {
                    return ((RubyFixnum)left).op_plus(context, (RubyFixnum)right);
                }
            }
            return context.nil;
        }
    }
    
    public static class FixnumNode extends ExpressionNode {
        private final long value;
        
        public FixnumNode(long value) {
            this.value = value;
        }
        
        public Object execute(ThreadContext context, VirtualFrame frame) {
            return context.runtime.newFixnum(this.value);
        }
    }
}
