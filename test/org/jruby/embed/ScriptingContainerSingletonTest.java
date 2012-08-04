/**
 * **** BEGIN LICENSE BLOCK *****
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
 * Copyright (C) 2009-2010 Yoko Harada <yokolet@gmail.com>
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
 * **** END LICENSE BLOCK *****
 */
package org.jruby.embed;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author donV
 */
public class ScriptingContainerSingletonTest {
    private ScriptingContainer container;

    @Before
    public void setUp() {
        container = new ScriptingContainer(LocalContextScope.SINGLETON);
    }

    @After
    public void tearDown() {
        container = null;
    }

    /**
     * Test of runScriptlet method, of class ScriptingContainer.
     */
    @Test
    public void testRunScriptletCheckConstant() {
        System.out.println();
        System.out.println("*******************************************************************************************************************");

        // Simulate a garbage collection of softreference to current context
        container.getProvider().getRuntime().getThreadService().setCurrentContext(null);

        container.put("MyConstant", 42);
        container.runScriptlet("a = 1");
        container.runScriptlet("b = 2");
        
        System.out.println("*******************************************************************************************************************");
    }

}
