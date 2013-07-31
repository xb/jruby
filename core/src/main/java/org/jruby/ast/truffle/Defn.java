/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jruby.ast.truffle;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;
import org.jruby.runtime.ThreadContext;

public class Defn extends RootNode {
    private final String name;
    @Child private ArgsNode argsNode;
    @Child private ExpressionNode bodyNode;
    
    final FrameDescriptor descriptor;
    final FrameSlot contextSlot;
    
    public Defn(String name, ArgsNode argsNode, ExpressionNode bodyNode) {
        this.name = name;
        this.argsNode = argsNode;
        this.bodyNode = bodyNode;
        
        descriptor = new FrameDescriptor();
        contextSlot = descriptor.addFrameSlot("context");
    }
    
    @Override
    public String toString() {
        return "Method:" + name;
    }
    
    @Override
    public Object execute(VirtualFrame frame) {
        ThreadContext context = (ThreadContext)frame.getArguments(TruffleDynamicMethod.Arguments.class).context;
        return bodyNode.execute(context, frame);
    }
    
}
