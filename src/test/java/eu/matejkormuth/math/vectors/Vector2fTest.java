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
package eu.matejkormuth.math.vectors;

import org.junit.Assert;
import org.junit.Test;

public class Vector2fTest {

    @Test
    public void testDefaultValue() throws Exception {
        Vector2f def = new Vector2f();
        Assert.assertEquals(0, def.getX(), 1e-6);
        Assert.assertEquals(0, def.getY(), 1e-6);
    }

    @Test
    public void testAbsolute() throws Exception {
        Assert.assertEquals(new Vector2f(8, 1), new Vector2f(-8, 1).absolute());
    }

    @Test
    public void testNegate() throws Exception {
        Assert.assertEquals(new Vector2f(8, -1), new Vector2f(-8, 1).negate());
    }

    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals(new Vector2f(5, 15), new Vector2f(2, 10).add(new Vector2f(3, 5)));
    }

    @Test
    public void testAdd1() throws Exception {
        Assert.assertEquals(new Vector2f(5, 15), new Vector2f(2, 10).add(3, 5));
    }

    @Test
    public void testSubtract() throws Exception {
        Assert.assertEquals(new Vector2f(-1, 5), new Vector2f(2, 10).subtract(new Vector2f(3, 5)));
    }

    @Test
    public void testSubtract1() throws Exception {
        Assert.assertEquals(new Vector2f(-1, 5), new Vector2f(2, 10).subtract(3, 5));
    }

    @Test
    public void testMultiply() throws Exception {
        Assert.assertEquals(new Vector2f(2, 4), new Vector2f(1, 2).multiply(2));
    }

    @Test
    public void testDivide() throws Exception {
        Assert.assertEquals(new Vector2f(1, 2), new Vector2f(2, 4).divide(2));
    }

    @Test
    public void testDot() throws Exception {
        Assert.assertEquals(-8, new Vector2f(2, -3).dot(new Vector2f(6.5f, 7)), 1e-6);
    }

    @Test
    public void testNormalize() throws Exception {
        Assert.assertEquals(new Vector2f(1, 0), new Vector2f(10, 0).normalize());
        Assert.assertEquals(new Vector2f(0.70710678118f, 0.70710678118f), new Vector2f(1, 1).normalize());
    }

    @Test
    public void testLength() throws Exception {
        Assert.assertEquals(6.40312423743, new Vector2f(-4, 5).length(), 1e-6);
    }

    @Test
    public void testLengthSquared() throws Exception {
        Assert.assertEquals(41, new Vector2f(-4, 5).lengthSquared(), 1e-6);
    }

    @Test
    public void testDistance() throws Exception {
        Assert.assertEquals(12.0830459736, new Vector2f(10, -3).distance(new Vector2f(5, 8)), 1e-6);
    }

    @Test
    public void testDistanceSquared() throws Exception {
        Assert.assertEquals(146, new Vector2f(10, -3).distanceSquared(new Vector2f(5, 8)), 1e-6);
    }

    @Test
    public void testGetX() throws Exception {
        Assert.assertEquals(5, new Vector2f(5, 4).getX(), 1e-6);
        Assert.assertEquals(1.2, new Vector2f(1.2f, 4.33f).getX(), 1e-6);
    }

    @Test
    public void testGetY() throws Exception {
        Assert.assertEquals(4, new Vector2f(0, 4).getY(), 1e-6);
        Assert.assertEquals(4.33, new Vector2f(3, 4.33f).getY(), 1e-6);
    }
}