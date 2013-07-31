/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jruby.ast.truffle;

import org.jruby.ast.CallNode;
import org.jruby.ast.FixnumNode;
import org.jruby.ast.NewlineNode;
import org.jruby.ast.Node;
import org.jruby.ast.visitor.AbstractNodeVisitor;

/**
 *
 * @author headius
 */
public class TruffleNodeVisitor extends AbstractNodeVisitor {
    @Override
    public Object defaultVisit(Node node) {
        throw new RuntimeException("could not compile: " + node);
    }
    
    @Override
    public Object visitDefnNode(org.jruby.ast.DefnNode defnNode) {
        return new Defn(defnNode.getName(), null, (ExpressionNode)defnNode.getBodyNode().accept(this));
    }
    
    @Override
    public Object visitCallNode(CallNode callNode) {
        if (callNode.getName().equals("+")) {
            return new ExpressionNode.AddNode(
                    (ExpressionNode)callNode.getReceiverNode().accept(this),
                    (ExpressionNode)callNode.getArgsNode().childNodes().get(0).accept(this));
        }
        return super.visitCallNode(callNode);
    }
    
    @Override
    public Object visitFixnumNode(FixnumNode fixnumNode) {
        return new ExpressionNode.FixnumNode(fixnumNode.getValue());
    }
    
    @Override
    public Object visitNewlineNode(NewlineNode newlineNode) {
        return newlineNode.getNextNode().accept(this);
    }
}
