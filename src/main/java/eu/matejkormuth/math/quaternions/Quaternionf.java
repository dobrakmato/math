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
import eu.matejkormuth.math.vectors.Vector3d;
import eu.matejkormuth.math.vectors.Vector3f;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Immutable
public class Quaternionf {

    public static final Quaternionf IDENTITY = new Quaternionf(1, 0, 0, 0);

    @Getter
    private final float w;
    @Getter
    private final float x;
    @Getter
    private final float y;
    @Getter
    private final float z;

    public Quaternionf(float w, float x, float y, float z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Quaternionf fromAngle(double degrees, Vector3f axis) {
        double radians = Math.toRadians(degrees);
        float sinHalf = (float) Math.sin(radians / 2);
        float cosHalf = (float) Math.cos(radians / 2);

        return new Quaternionf(cosHalf,
                axis.getX() * sinHalf,
                axis.getY() * sinHalf,
                axis.getZ() * sinHalf).normalize();
    }

    public Quaternionf add(Quaternionf rhs) {
        return new Quaternionf(w + rhs.w, x + rhs.x, y + rhs.y, z + rhs.z);
    }

    public Quaternionf subtract(Quaternionf rhs) {
        return new Quaternionf(w - rhs.w, x - rhs.x, y - rhs.y, z - rhs.z);
    }

    public Quaternionf multiply(float rhs) {
        return new Quaternionf(w * rhs, x * rhs, y * rhs, z * rhs);
    }

    public Quaternionf multiply(Quaternionf rhs) {
        return new Quaternionf(
                w * rhs.w - x * rhs.x - y * rhs.y - z * rhs.z,
                w * rhs.x + x * rhs.w + y * rhs.z - z * rhs.y,
                w * rhs.y + y * rhs.w + z * rhs.x - x * rhs.z,
                w * rhs.z + z * rhs.w + x * rhs.y - y * rhs.x
        );
    }

    public Quaternionf multiply(Vector3d rhs) {
        return new Quaternionf(
                (float) (-x * rhs.getX() - y * rhs.getY() - z * rhs.getZ()),
                (float) (w * rhs.getX() + y * rhs.getZ() - z * rhs.getY()),
                (float) (w * rhs.getY() + z * rhs.getX() - x * rhs.getZ()),
                (float) (w * rhs.getZ() + x * rhs.getY() - y * rhs.getX())
        );
    }

    public Quaternionf multiply(Vector3f rhs) {
        return new Quaternionf(
                -x * rhs.getX() - y * rhs.getY() - z * rhs.getZ(),
                w * rhs.getX() + y * rhs.getZ() - z * rhs.getY(),
                w * rhs.getY() + z * rhs.getX() - x * rhs.getZ(),
                w * rhs.getZ() + x * rhs.getY() - y * rhs.getX()
        );
    }

    public Quaternionf conjugate() {
        return new Quaternionf(w, -x, -y, -z);
    }

    public Quaternionf normalize() {
        float invLen = (float) (1.0 / length());
        return new Quaternionf(w * invLen, x * invLen, y * invLen, z * invLen);
    }

    public float dot(Quaternionf rhs) {
        return w * rhs.w + x * rhs.x + y * rhs.y + z * rhs.z;
    }

    public float lengthSquared() {
        return w * w + x * x + y * y + z * z;
    }

    public float length() {
        return (float) Math.sqrt(w * w + x * x + y * y + z * z);
    }
}
