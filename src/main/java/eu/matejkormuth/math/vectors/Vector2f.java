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
public class Vector2f {

    public static final Vector2f ZERO = new Vector2f(0, 0);
    public static final Vector2f ONE = new Vector2f(1, 1);
    public static final Vector2f UNIT_X = new Vector2f(1, 0);
    public static final Vector2f UNIT_Y = new Vector2f(0, 1);

    @Getter
    private final float x;
    @Getter
    private final float y;

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static Vector2f getRandom() {
        return new Vector2f((float) Math.random(), (float) Math.random());
    }

    public Vector2f absolute() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    public Vector2f negate() {
        return new Vector2f(-x, -y);
    }

    public Vector2f add(Vector2f vec) {
        return new Vector2f(this.x + vec.x, this.y + vec.y);
    }

    public Vector2f add(float x, float y) {
        return new Vector2f(this.x + x, this.y + y);
    }

    public Vector2f subtract(Vector2f vec) {
        return new Vector2f(this.x - vec.x, this.y - vec.y);
    }

    public Vector2f subtract(float x, float y) {
        return new Vector2f(this.x - x, this.y - y);
    }

    public Vector2f multiply(float n) {
        return new Vector2f(this.x * n, this.y * n);
    }

    public Vector2f divide(float n) {
        return new Vector2f(this.x / n, this.y / n);
    }

    public float dot(Vector2f vec) {
        return this.x * vec.x + this.y * vec.y;
    }

    // cross product undefined for 2d vectors

    public Vector2f normalize() {
        float length = length();
        return new Vector2f(x / length, y / length);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float lengthSquared() {
        return x * x + y * y;
    }

    public float distance(Vector2f vec) {
        return (float) Math.sqrt(Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2));
    }

    public float distanceSquared(Vector2f vec) {
        return (float) (Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2));
    }

}
