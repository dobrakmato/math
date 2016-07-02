/**
 * basic math library - Basic math library for myself.
 * Copyright (c) 2016 - 2016, Matej Kormuth <http://www.github.com/dobrakmato>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package eu.matejkormuth.math;

import org.junit.Assert;
import org.junit.Test;

public class MathUtilsTest {

    @Test
    public void testClamp() throws Exception {
        Assert.assertEquals(0, MathUtils.clamp(-8, 0, 1));
        Assert.assertEquals(1, MathUtils.clamp(8, 0, 1));
    }

    @Test
    public void testClamp1() throws Exception {
        Assert.assertEquals(0L, MathUtils.clamp(-8L, 0L, 1L));
        Assert.assertEquals(1L, MathUtils.clamp(8L, 0L, 1L));
    }

    @Test
    public void testClamp2() throws Exception {
        Assert.assertEquals(0D, MathUtils.clamp(-8D, 0D, 1D), 1e-6);
        Assert.assertEquals(1D, MathUtils.clamp(8D, 0D, 1D), 1e-6);
    }

    @Test
    public void testClamp3() throws Exception {
        Assert.assertEquals(0f, MathUtils.clamp(-8f, 0f, 1f), 1e-6);
        Assert.assertEquals(1f, MathUtils.clamp(8f, 0f, 1f), 1e-6);
    }
}