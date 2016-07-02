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

public class Vector3dTest {

    @Test
    public void testZero() throws Exception {
        Vector3d def = new Vector3d();
        Assert.assertEquals(0, def.getX(), 1e-6);
        Assert.assertEquals(0, def.getY(), 1e-6);
        Assert.assertEquals(0, def.getZ(), 1e-6);
    }

    @Test
    public void testFrom2DVector() throws Exception {
        Assert.assertEquals(new Vector3d(4, -5.3, 0), new Vector3d(new Vector2d(4, -5.3)));
    }

    @Test
    public void testAbsolute() throws Exception {
        Assert.assertEquals(new Vector3d(5, 22.5, 15.3), new Vector3d(-5, 22.5, -15.3).absolute());
    }

    @Test
    public void testNegate() throws Exception {
        Assert.assertEquals(new Vector3d(-1, 2.5, 3), new Vector3d(1, -2.5, -3).negate());
    }

    @Test
    public void testAdd() throws Exception {
        Assert.assertEquals(new Vector3d(15, 10, 11), new Vector3d(10, 15, 10.5).add(new Vector3d(5, -5, 0.5)));
    }

    @Test
    public void testAdd1() throws Exception {
        Assert.assertEquals(new Vector3d(15, 10, 11), new Vector3d(10, 15, 10.5).add(5, -5, 0.5));
    }

    @Test
    public void testSubtract() throws Exception {
        Assert.assertEquals(new Vector3d(5, 20, 10), new Vector3d(10, 15, 10.5).subtract(new Vector3d(5, -5, 0.5)));
    }

    @Test
    public void testSubtract1() throws Exception {
        Assert.assertEquals(new Vector3d(5, 20, 10), new Vector3d(10, 15, 10.5).subtract(5, -5, 0.5));
    }

    @Test
    public void testMultiply() throws Exception {
        Assert.assertEquals(new Vector3d(2, 4, 6), new Vector3d(1, 2, 3).multiply(2));
    }

    @Test
    public void testDivide() throws Exception {
        Assert.assertEquals(new Vector3d(1, 2, 3), new Vector3d(2, 4, 6).divide(2));
    }

    @Test
    public void testDot() throws Exception {
        Assert.assertEquals(7, new Vector3d(1, 5, 1).dot(new Vector3d(2, 2, -5)), 1e-6);
    }

    @Test
    public void testCross() throws Exception {
        Vector3d cross = new Vector3d(4, 5.6, 11).cross(new Vector3d(7, 20, -5));
        Assert.assertEquals(-248, cross.getX(), 1e-6);
        Assert.assertEquals(97, cross.getY(), 1e-6);
        Assert.assertEquals(40.8, cross.getZ(), 1e-6);
    }

    @Test
    public void testNormalize() throws Exception {
        Assert.assertEquals(new Vector3d(1, 0, 0), new Vector3d(10, 0, 0).normalize());
        Assert.assertEquals(new Vector3d(0.5773502691896258, 0.5773502691896258, 0.5773502691896258), new Vector3d(1, 1, 1).normalize());
    }

    @Test
    public void testLength() throws Exception {
        Assert.assertEquals(52.1225709266, new Vector3d(1.3, 50.1, 14.32).length(), 1e-6);
    }

    @Test
    public void testLengthSquared() throws Exception {
        Assert.assertEquals(2716.7624, new Vector3d(1.3, 50.1, 14.32).lengthSquared(), 1e-6);
    }

    @Test
    public void testDistance() throws Exception {
        Vector3d from = new Vector3d(10, 15.5, 14.24);
        Vector3d to = new Vector3d(18, -10, 22.515);
        Assert.assertTrue(from.distance(to) > 0);
        Assert.assertEquals(27.9772340484, from.distance(to), 1e-6);
    }

    @Test
    public void testDistanceSquared() throws Exception {
        Vector3d from = new Vector3d(10, 15.5, 14.24);
        Vector3d to = new Vector3d(18, -10, 22.515);
        Assert.assertTrue(from.distanceSquared(to) > 0);
        Assert.assertEquals(782.725625, from.distanceSquared(to), 1e-6);
    }

    @Test
    public void testGetX() throws Exception {
        Assert.assertEquals(1.3, new Vector3d(1.3, 50.1, 14.32).getX(), 1e-6);
    }

    @Test
    public void testGetY() throws Exception {
        Assert.assertEquals(50.1, new Vector3d(1.3, 50.1, 14.32).getY(), 1e-6);
    }

    @Test
    public void testGetZ() throws Exception {
        Assert.assertEquals(14.32, new Vector3d(1.3, 50, 14.32).getZ(), 1e-6);
    }
}