package org.jruby.ast.truffle;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.VirtualFrame;

public class LocalVarNode extends FrameSlotNode {
    public LocalVarNode(FrameSlot slot) {
        super(slot);
    }
    
    public Object doObject(VirtualFrame frame) {
        return frame.getValue(slot);
    }
}
