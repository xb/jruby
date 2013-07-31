package org.jruby.ast.truffle;

import com.oracle.truffle.api.frame.FrameSlot;

public class FrameSlotNode {
    FrameSlot slot;
    
    public FrameSlotNode(FrameSlot slot) {
        this.slot = slot;
    }
}
