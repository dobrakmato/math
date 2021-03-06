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
import eu.matejkormuth.math.MathUtils;
import eu.matejkormuth.math.quaternions.Quaternionf;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Immutable
public class Vector3f {

    public static final Vector3f ZERO = new Vector3f(0, 0, 0);
    public static final Vector3f ONE = new Vector3f(1f, 1f, 1f);
    public static final Vector3f UNIT_X = new Vector3f(1f, 0, 0);
    public static final Vector3f UNIT_Y = new Vector3f(0, 1f, 0);
    public static final Vector3f UNIT_Z = new Vector3f(0, 0, 1f);

    @Getter
    private final float x;
    @Getter
    private final float y;
    @Getter
    private final float z;

    public Vector3f() {
        this(0f, 0f, 0f);
    }

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector2f vec) {
        this.x = vec.getX();
        this.y = vec.getY();
        this.z = 0.0f;
    }

    public static Vector3f getRandom() {
        return new Vector3f((float) Math.random(), (float) Math.random(), (float) Math.random());
    }

    public Vector3f absolute() {
        return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vector3f negate() {
        return new Vector3f(-x, -y, -z);
    }

    public Vector3f add(Vector3f vec) {
        return new Vector3f(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vector3f add(float x, float y, float z) {
        return new Vector3f(this.x + x, this.y + y, this.z + z);
    }

    public Vector3f subtract(Vector3f vec) {
        return new Vector3f(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public Vector3f subtract(float x, float y, float z) {
        return new Vector3f(this.x - x, this.y - y, this.z - z);
    }

    public Vector3f multiply(float n) {
        return new Vector3f(this.x * n, this.y * n, this.z * n);
    }

    public Vector3f divide(float n) {
        return new Vector3f(this.x / n, this.y / n, this.z / n);
    }

    public float dot(Vector3f vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public Vector3f cross(Vector3f vec) {
        return new Vector3f(y * vec.z - vec.y * z,
                z * vec.x - vec.z * x,
                x * vec.y - vec.x * y);
    }

    public Vector3f normalize() {
        float invLength = 1f / length();
        return new Vector3f(x * invLength, y * invLength, z * invLength);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    public float distance(Vector3f vec) {
        return (float) Math.sqrt(Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2) + Math.pow(vec.z - z, 2));
    }

    public float distanceSquared(Vector3f vec) {
        return (float) (Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2) + Math.pow(vec.z - z, 2));
    }

    @Override
    public String toString() {
        return "[" + Float.toString(x) + "; " + Float.toString(y) + "; " + Float.toString(z) + "]";
    }

    public Vector3f rotate(double degrees, Vector3f axis) {
        Quaternionf rotation = Quaternionf.fromAngle(degrees, axis);
        Quaternionf conjugate = rotation.conjugate();
        Quaternionf w = rotation.multiply(this).multiply(conjugate);
        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }

    // https://keithmaggio.wordpress.com/2011/02/15/math-magician-lerp-slerp-and-nlerp/
    public static Vector3f lerp(Vector3f start, Vector3f end, float f) {
        return start.add(end.subtract(start).multiply(f));
    }

    public static Vector3f slerp(Vector3f start, Vector3f end, float f) {
        double dot = MathUtils.clamp(start.dot(end), -1.0, 1.0);
        double theta = Math.acos(dot) * f;
        Vector3f relative = end.subtract(start.multiply((float) dot)).normalize();
        return start.multiply((float) Math.cos(theta)).add(relative.multiply((float) Math.sin(theta)));
    }

    public static Vector3f nlerp(Vector3f start, Vector3f end, float f) {
        return start.add(end.subtract(start).multiply(f)).normalize();
    }

}
