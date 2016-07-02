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

public class Vector3fTest {

    @Test
    public void testZero() throws Exception {
        Vector3f def = new Vector3f();
        Assert.assertEquals(0, def.getX(), 1e-6);
        Assert.assertEquals(0, def.getY(), 1e-6);
        Assert.assertEquals(0, def.getZ(), 1e-6);
    }

    @Test
    public void testFrom2DVector() throws Exception {
        Assert.assertEquals(new Vector3f(4f, -5.3f, 0f), new Vector3f(new Vector2f(4f, -5.3f)));
    }

    @Test
    public void testAbsolute() throws Exception {
        Assert.assertEquals(new Vector3f(5f, 22.5f, 15.3f), new Vector3f(-5f, 22.5f, -15.3f).absolute());
    }

    @Test
    public void testNegate() throws Exception {
        Assert.assertEquals(new Vector3f(-1f, 2.5f, 3f), new Vector3f(1f, -2.5f, -3f).negate());
    }

    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals(new Vector3f(15f, 10f, 11f), new Vector3f(10f, 15f, 10.5f).add(new Vector3f(5f, -5f, 0.5f)));
    }

    @Test
    public void testAdd1() throws Exception {
        Assert.assertEquals(new Vector3f(15f, 10f, 11f), new Vector3f(10f, 15f, 10.5f).add(5f, -5f, 0.5f));
    }

    @Test
    public void testSubtract() throws Exception {
        Assert.assertEquals(new Vector3f(5f, 20f, 10f), new Vector3f(10f, 15f, 10.5f).subtract(new Vector3f(5f, -5f, 0.5f)));
    }

    @Test
    public void testSubtract1() throws Exception {
        Assert.assertEquals(new Vector3f(5f, 20f, 10f), new Vector3f(10f, 15f, 10.5f).subtract(5f, -5f, 0.5f));
    }

    @Test
    public void testMultiply() throws Exception {
        Assert.assertEquals(new Vector3f(2f, 4f, 6f), new Vector3f(1f, 2f, 3f).multiply(2));
    }

    @Test
    public void testDivide() throws Exception {
        Assert.assertEquals(new Vector3f(1f, 2f, 3f), new Vector3f(2f, 4f, 6f).divide(2));
    }

    @Test
    public void testDot() throws Exception {
        Assert.assertEquals(7f, new Vector3f(1f, 5f, 1f).dot(new Vector3f(2f, 2f, -5f)), 1e-6);
    }

    @Test
    public void testCross() throws Exception {
        Vector3f cross = new Vector3f(4f, 5.6f, 11f).cross(new Vector3f(7, 20, -5));
        Assert.assertEquals(-248, cross.getX(), 1e-6);
        Assert.assertEquals(97, cross.getY(), 1e-6);
        Assert.assertEquals(40.8, cross.getZ(), 1e-6);
    }

    @Test
    public void testNormalize() throws Exception {
        Assert.assertEquals(new Vector3f(1, 0, 0), new Vector3f(10, 0, 0).normalize());
        Assert.assertEquals(new Vector3f(0.5773502691896258f, 0.5773502691896258f, 0.5773502691896258f), new Vector3f(1, 1, 1).normalize());
    }

    @Test
    public void testLength() throws Exception {
        Assert.assertEquals(52.1225709266, new Vector3f(1.3f, 50.1f, 14.32f).length(), 1e-6);
    }

    @Test
    public void testLengthSquared() throws Exception {
        Assert.assertEquals(2716.7624, new Vector3f(1.3f, 50.1f, 14.32f).lengthSquared(), 1e-3);
    }

    @Test
    public void testDistance() throws Exception {
        Vector3f from = new Vector3f(10, 15.5f, 14.24f);
        Vector3f to = new Vector3f(18f, -10f, 22.515f);
        Assert.assertTrue(from.distance(to) > 0);
        Assert.assertEquals(27.9772340484, from.distance(to), 1e-6);
    }

    @Test
    public void testDistanceSquared() throws Exception {
        Vector3f from = new Vector3f(10, 15.5f, 14.24f);
        Vector3f to = new Vector3f(18, -10, 22.515f);
        Assert.assertTrue(from.distanceSquared(to) > 0);
        Assert.assertEquals(782.725625f, from.distanceSquared(to), 1e-4);
    }

    @Test
    public void testGetX() throws Exception {
        Assert.assertEquals(1.3, new Vector3f(1.3f, 50.1f, 14.32f).getX(), 1e-6);
    }

    @Test
    public void testGetY() throws Exception {
        Assert.assertEquals(50.1, new Vector3f(1.3f, 50.1f, 14.32f).getY(), 1e-4);
    }

    @Test
    public void testGetZ() throws Exception {
        Assert.assertEquals(14.32, new Vector3f(1.3f, 50f, 14.32f).getZ(), 1e-6);
    }
}