/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jruby.ast;

import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.lexer.yacc.ISourcePosition;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

import java.util.Arrays;

/**
 *
 * @author enebo
 */
public class Yield19Node extends YieldNode {
    public Yield19Node(ISourcePosition position, Node node) {
        super(ISourcePosition.INVALID_POSITION, node, false);
    }

    @Override
    public IRubyObject interpret(Ruby runtime, ThreadContext context, IRubyObject self, Block aBlock) {
        Node args = getArgsNode();
        IRubyObject argsResult = args.interpret(runtime, context, self, aBlock);
        IRubyObject[] javaArgs = IRubyObject.NULL_ARRAY;

        switch (args.getNodeType()) {
            case ARGSPUSHNODE:
            case ARGSCATNODE:
            case SPLATNODE:
            case ARRAYNODE:
                // Pass-thru
                javaArgs = ((RubyArray)argsResult).toJavaArray();
                break;
            default:
               assert false: "Invalid node found in yield";
        }

        return context.getCurrentFrame().getBlock().yield19(context, javaArgs, null, null);
    }
}
