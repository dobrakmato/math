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
package eu.matejkormuth.math.quaternions;

import eu.matejkormuth.annotations.Immutable;
import eu.matejkormuth.math.MathUtils;
import eu.matejkormuth.math.vectors.Vector3d;
import eu.matejkormuth.math.vectors.Vector3f;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Immutable
public class Quaterniond {

    public static final Quaterniond IDENTITY = new Quaterniond(1, 0, 0, 0);

    @Getter
    private final double w;
    @Getter
    private final double x;
    @Getter
    private final double y;
    @Getter
    private final double z;

    public Quaterniond(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Quaterniond fromAngle(double degrees, Vector3d axis) {
        double radians = Math.toRadians(degrees);
        double sinHalf = Math.sin(radians / 2);
        double cosHalf = Math.cos(radians / 2);

        return new Quaterniond(cosHalf,
                axis.getX() * sinHalf,
                axis.getY() * sinHalf,
                axis.getZ() * sinHalf).normalize();
    }

    public Quaterniond add(Quaterniond rhs) {
        return new Quaterniond(w + rhs.w, x + rhs.x, y + rhs.y, z + rhs.z);
    }

    public Quaterniond subtract(Quaterniond rhs) {
        return new Quaterniond(w - rhs.w, x - rhs.x, y - rhs.y, z - rhs.z);
    }

    public Quaterniond multiply(double rhs) {
        return new Quaterniond(w * rhs, x * rhs, y * rhs, z * rhs);
    }

    public Quaterniond multiply(Quaterniond rhs) {
        return new Quaterniond(
                w * rhs.w - x * rhs.x - y * rhs.y - z * rhs.z,
                w * rhs.x + x * rhs.w + y * rhs.z - z * rhs.y,
                w * rhs.y + y * rhs.w + z * rhs.x - x * rhs.z,
                w * rhs.z + z * rhs.w + x * rhs.y - y * rhs.x
        );
    }

    public Quaterniond multiply(Vector3d rhs) {
        return new Quaterniond(
                w * rhs.getX() + y * rhs.getZ() - z * rhs.getY(),
                w * rhs.getY() + z * rhs.getX() - x * rhs.getZ(),
                w * rhs.getZ() + x * rhs.getY() - y * rhs.getX(),
                -x * rhs.getX() - y * rhs.getY() - z * rhs.getZ()
        );
    }

    public Quaterniond multiply(Vector3f rhs) {
        return new Quaterniond(
                w * rhs.getX() + y * rhs.getZ() - z * rhs.getY(),
                w * rhs.getY() + z * rhs.getX() - x * rhs.getZ(),
                w * rhs.getZ() + x * rhs.getY() - y * rhs.getX(),
                -x * rhs.getX() - y * rhs.getY() - z * rhs.getZ()
        );
    }

    public Quaterniond conjugate() {
        return new Quaterniond(w, -x, -y, -z);
    }

    public Quaterniond normalize() {
        double invLen = 1.0 / length();
        return new Quaterniond(w * invLen, x * invLen, y * invLen, z * invLen);
    }

    public double dot(Quaterniond rhs) {
        return w * rhs.w + x * rhs.x + y * rhs.y + z * rhs.z;
    }

    public double lengthSquared() {
        return w * w + x * x + y * y + z * z;
    }

    public double length() {
        return Math.sqrt(w * w + x * x + y * y + z * z);
    }

    // http://number-none.com/product/Understanding%20Slerp,%20Then%20Not%20Using%20It/
    public Quaterniond lerp(Quaterniond start, Quaterniond end, double f) {
        return start.add(end.subtract(start).multiply(f));
    }

    // !!! start and and should be normalized!
    public Quaterniond slerp(Quaterniond start, Quaterniond end, double f) {
        double dot = MathUtils.clamp(start.dot(end), 0.0, 1.0);
        if (dot > 0.9995) {
            return start.add(end.subtract(start).multiply(f)).normalize();
        }
        double theta_0 = Math.acos(dot);
        double theta = theta_0 * f;

        Quaterniond v2 = end.subtract(start.multiply(dot)).normalize();
        return start.multiply(Math.cos(theta)).add(v2.multiply(Math.sin(theta)));
    }

    public Quaterniond nlerp(Quaterniond start, Quaterniond end, double f) {
        return start.add(end.subtract(start).multiply(f)).normalize();
    }
}
