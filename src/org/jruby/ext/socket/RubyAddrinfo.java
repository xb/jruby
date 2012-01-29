/***** BEGIN LICENSE BLOCK *****
 * Version: CPL 1.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Common Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2012 Hiro Asari
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the CPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the CPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/

package org.jruby.ext.socket;

import org.jruby.CompatVersion;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.cext.RubyData;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

/**
 *
 * @author asari
 */
@JRubyClass(name = "Addrinfo", parent = "Data")
public class RubyAddrinfo extends RubyData {
    static void createAddrinfo(Ruby runtime) {
        RubyClass rb_cAddrinfo = runtime.defineClass("Addrinfo", runtime.getClass("Data"), ADDRINFO_ALLOCATOR);
        rb_cAddrinfo.defineAnnotatedMethods(RubyAddrinfo.class);
    }
    
    private static ObjectAllocator ADDRINFO_ALLOCATOR = new ObjectAllocator() {

        public IRubyObject allocate(Ruby runtime, RubyClass klazz) {
            return new RubyAddrinfo(runtime, klazz);
        }
    };

    public RubyAddrinfo(Ruby runtime, RubyClass klazz) {
        super(runtime, klazz);
    }
    
    @JRubyMethod(name = "initialize", required = 1, optional = 3, compat = CompatVersion.RUBY1_9)
    public IRubyObject initialize(ThreadContext context, IRubyObject[] args) {
                
        checkAddrinfo();
        throw context.getRuntime().newNotImplementedError("Not yet implemented");
    }
    
    private void checkAddrinfo() {
        // TODO: implement this
        // throw getRuntime().newTypeError("already initialized socket address");
    }
}
