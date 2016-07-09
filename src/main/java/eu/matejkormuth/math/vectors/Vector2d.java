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

import eu.matejkormuth.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Immutable
public class Vector2d {

    public static final Vector2d ZERO = new Vector2d(0, 0);
    public static final Vector2d ONE = new Vector2d(1, 1);
    public static final Vector2d UNIT_X = new Vector2d(1, 0);
    public static final Vector2d UNIT_Y = new Vector2d(0, 1);

    @Getter
    private final double x;
    @Getter
    private final double y;

    public Vector2d() {
        this(0, 0);
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2d getRandom() {
        return new Vector2d(Math.random(), Math.random());
    }

    public Vector2d absolute() {
        return new Vector2d(Math.abs(x), Math.abs(y));
    }

    public Vector2d negate() {
        return new Vector2d(-x, -y);
    }

    public Vector2d add(Vector2d vec) {
        return new Vector2d(this.x + vec.x, this.y + vec.y);
    }

    public Vector2d add(double x, double y) {
        return new Vector2d(this.x + x, this.y + y);
    }

    public Vector2d subtract(Vector2d vec) {
        return new Vector2d(this.x - vec.x, this.y - vec.y);
    }

    public Vector2d subtract(double x, double y) {
        return new Vector2d(this.x - x, this.y - y);
    }

    public Vector2d multiply(double n) {
        return new Vector2d(this.x * n, this.y * n);
    }

    public Vector2d divide(double n) {
        return new Vector2d(this.x / n, this.y / n);
    }

    public double dot(Vector2d vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    // cross product undefined for 2d vectors

    public Vector2d normalize() {
        double invLength = 1.0D / length();
        return new Vector2d(x * invLength, y * invLength);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public double distance(Vector2d vec) {
        return Math.sqrt(Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2));
    }

    public double distanceSquared(Vector2d vec) {
        return (Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2));
    }

    @Override
    public String toString() {
        return "[" + Double.toString(x) + "; " + Double.toString(y) + "]";
    }

}
