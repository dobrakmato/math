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
import eu.matejkormuth.math.quaternions.Quaterniond;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Immutable
public class Vector3d {

    public static final Vector3d ZERO = new Vector3d(0, 0, 0);
    public static final Vector3d ONE = new Vector3d(1, 1, 1);
    public static final Vector3d UNIT_X = new Vector3d(1, 0, 0);
    public static final Vector3d UNIT_Y = new Vector3d(0, 1, 0);
    public static final Vector3d UNIT_Z = new Vector3d(0, 0, 1);

    @Getter
    private final double x;
    @Getter
    private final double y;
    @Getter
    private final double z;

    public Vector3d() {
        this(0, 0, 0);
    }

    public Vector3d(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d(Vector2d vec) {
        this.x = vec.getX();
        this.y = vec.getY();
        this.z = 0.0f;
    }

    public static Vector3d getRandom() {
        return new Vector3d(Math.random(), Math.random(), Math.random());
    }

    public Vector3d absolute() {
        return new Vector3d(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Vector3d negate() {
        return new Vector3d(-x, -y, -z);
    }

    public Vector3d add(Vector3d vec) {
        return new Vector3d(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vector3d add(double x, double y, double z) {
        return new Vector3d(this.x + x, this.y + y, this.z + z);
    }

    public Vector3d subtract(Vector3d vec) {
        return new Vector3d(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public Vector3d subtract(double x, double y, double z) {
        return new Vector3d(this.x - x, this.y - y, this.z - z);
    }

    public Vector3d multiply(double n) {
        return new Vector3d(this.x * n, this.y * n, this.z * n);
    }

    public Vector3d divide(double n) {
        return new Vector3d(this.x / n, this.y / n, this.z / n);
    }

    public double dot(Vector3d vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public Vector3d cross(Vector3d vec) {
        return new Vector3d(y * vec.z - vec.y * z,
                z * vec.x - vec.z * x,
                x * vec.y - vec.x * y);
    }

    public Vector3d normalize() {
        double length = length();
        return new Vector3d(x / length, y / length, z / length);
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    public double distance(Vector3d vec) {
        return Math.sqrt(Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2) + Math.pow(vec.z - z, 2));
    }

    public double distanceSquared(Vector3d vec) {
        return (Math.pow(vec.x - x, 2) + Math.pow(vec.y - y, 2) + Math.pow(vec.z - z, 2));
    }

    @Override
    public String toString() {
        return "[" + Double.toString(x) + "; " + Double.toString(y) + "; " + Double.toString(z) + "]";
    }

    public Vector3d rotate(double degrees, Vector3d axis) {
        Quaterniond rotation = Quaterniond.fromAngle(degrees, axis);
        Quaterniond conjugate = rotation.conjugate();
        Quaterniond w = rotation.multiply(this).multiply(conjugate);
        return new Vector3d(w.getX(), w.getY(), w.getZ());
    }

    // https://keithmaggio.wordpress.com/2011/02/15/math-magician-lerp-slerp-and-nlerp/
    public static Vector3d lerp(Vector3d start, Vector3d end, double f) {
        return start.add(end.subtract(start).multiply(f));
    }

    public static Vector3d slerp(Vector3d start, Vector3d end, double f) {
        double dot = MathUtils.clamp(start.dot(end), -1.0, 1.0);
        double theta = Math.acos(dot) * f;
        Vector3d relative = end.subtract(start.multiply(dot)).normalize();
        return start.multiply(Math.cos(theta)).add(relative.multiply(Math.sin(theta)));
    }

    public static Vector3d nlerp(Vector3d start, Vector3d end, double f) {
        return start.add(end.subtract(start).multiply(f)).normalize();
    }
}
