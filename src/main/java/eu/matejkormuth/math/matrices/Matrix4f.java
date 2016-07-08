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
package eu.matejkormuth.math.matrices;

import eu.matejkormuth.annotations.Immutable;
import eu.matejkormuth.math.quaternions.Quaterniond;
import eu.matejkormuth.math.quaternions.Quaternionf;
import eu.matejkormuth.math.vectors.Vector3d;
import eu.matejkormuth.math.vectors.Vector3f;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Immutable
public class Matrix4f {

    public static final Matrix4f IDENTITY = new Matrix4f();
    public static final Matrix4f ZERO = new Matrix4f(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    @Getter
    private final float m00, m01, m02, m03;
    @Getter
    private final float m10, m11, m12, m13;
    @Getter
    private final float m20, m21, m22, m23;
    @Getter
    private final float m30, m31, m32, m33;

    public Matrix4f(float m00, float m01, float m02, float m03,
                    float m10, float m11, float m12, float m13,
                    float m20, float m21, float m22, float m23,
                    float m30, float m31, float m32, float m33) {
        this.m00 = m00;
        this.m01 = m01;
        this.m02 = m02;
        this.m03 = m03;
        this.m10 = m10;
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m20 = m20;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m30 = m30;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
    }

    public Matrix4f(float[] m) {
        if (m.length < 15) {
            throw new IllegalArgumentException("Array length must be at least 16 numbers");
        }

        this.m00 = m[0];
        this.m01 = m[1];
        this.m02 = m[2];
        this.m03 = m[3];
        this.m10 = m[4];
        this.m11 = m[5];
        this.m12 = m[6];
        this.m13 = m[7];
        this.m20 = m[8];
        this.m21 = m[9];
        this.m22 = m[10];
        this.m23 = m[11];
        this.m30 = m[12];
        this.m31 = m[13];
        this.m32 = m[14];
        this.m33 = m[15];
    }

    public Matrix4f(float[][] m) {
        if (m.length < 4) {
            throw new IllegalArgumentException("Array length must be at least 4 numbers");
        }

        this.m00 = m[0][0];
        this.m01 = m[0][1];
        this.m02 = m[0][2];
        this.m03 = m[0][3];

        this.m10 = m[1][0];
        this.m11 = m[1][1];
        this.m12 = m[1][2];
        this.m13 = m[1][3];

        this.m20 = m[2][0];
        this.m21 = m[2][1];
        this.m22 = m[2][2];
        this.m23 = m[2][3];

        this.m30 = m[3][0];
        this.m31 = m[3][1];
        this.m32 = m[3][2];
        this.m33 = m[3][3];
    }

    public Matrix4f() {
        this(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1);
    }

    public static Matrix4f createTranslation(Vector3f position) {
        return new Matrix4f(
                1, 0, 0, position.getX(),
                0, 1, 0, position.getY(),
                0, 0, 1, position.getZ(),
                0, 0, 0, 1
        );
    }

    public static Matrix4f createTranslation(Vector3d position) {
        return new Matrix4f(
                1, 0, 0, (float) position.getX(),
                0, 1, 0, (float) position.getY(),
                0, 0, 1, (float) position.getZ(),
                0, 0, 0, 1
        );
    }

    public static Matrix4f createScale(Vector3d scale) {
        return new Matrix4f(
                (float) scale.getX(), 0, 0, 0,
                0, (float) scale.getY(), 0, 0,
                0, 0, (float) scale.getZ(), 0,
                0, 0, 0, 1
        );
    }

    public static Matrix4f createScale(Vector3f scale) {
        return new Matrix4f(
                scale.getX(), 0, 0, 0,
                0, scale.getY(), 0, 0,
                0, 0, scale.getZ(), 0,
                0, 0, 0, 1
        );
    }

    public static Matrix4f createRotation(Quaterniond rot) {
        rot = rot.normalize();
        return new Matrix4f(
                (float) (1 - 2 * rot.getY() * rot.getY() - 2 * rot.getZ() * rot.getZ()),
                (float) (2 * rot.getX() * rot.getY() - 2 * rot.getW() * rot.getZ()),
                (float) (2 * rot.getX() * rot.getZ() + 2 * rot.getW() * rot.getY()), 0,
                (float) (2 * rot.getX() * rot.getY() + 2 * rot.getW() * rot.getZ()),
                (float) (1 - 2 * rot.getX() * rot.getX() - 2 * rot.getZ() * rot.getZ()),
                (float) (2 * rot.getY() * rot.getZ() - 2 * rot.getW() * rot.getX()), 0,
                (float) (2 * rot.getX() * rot.getZ() - 2 * rot.getW() * rot.getY()),
                (float) (2 * rot.getY() * rot.getZ() + 2 * rot.getX() * rot.getW()),
                (float) (1 - 2 * rot.getX() * rot.getX() - 2 * rot.getY() * rot.getY()), 0,
                0, 0, 0, 1);
    }

    public static Matrix4f createRotation(Quaternionf rot) {
        rot = rot.normalize();
        return new Matrix4f(
                1 - 2 * rot.getY() * rot.getY() - 2 * rot.getZ() * rot.getZ(),
                2 * rot.getX() * rot.getY() - 2 * rot.getW() * rot.getZ(),
                2 * rot.getX() * rot.getZ() + 2 * rot.getW() * rot.getY(), 0,
                2 * rot.getX() * rot.getY() + 2 * rot.getW() * rot.getZ(),
                1 - 2 * rot.getX() * rot.getX() - 2 * rot.getZ() * rot.getZ(),
                2 * rot.getY() * rot.getZ() - 2 * rot.getW() * rot.getX(), 0,
                2 * rot.getX() * rot.getZ() - 2 * rot.getW() * rot.getY(),
                2 * rot.getY() * rot.getZ() + 2 * rot.getX() * rot.getW(),
                1 - 2 * rot.getX() * rot.getX() - 2 * rot.getY() * rot.getY(), 0,
                0, 0, 0, 1);
    }

    public static Matrix4f createPerspective(float fov, float aspectRatio, float near, float far) {
        float tanHalfFov = (float) Math.tan(Math.toRadians(fov / 2));
        float range = near - far;

        return new Matrix4f(
                1 / tanHalfFov * aspectRatio, 0, 0, 0,
                0, 1 / tanHalfFov, 0, 0,
                0, 0, (-near - far) / range, 2 * far * near / range,
                0, 0, 1, 0
        );
    }

    public float get(int row, int col) {
        switch (row) {
            case 0:
                switch (col) {
                    case 0:
                        return m00;
                    case 1:
                        return m01;
                    case 2:
                        return m02;
                    case 3:
                        return m03;
                }
            case 1:
                switch (col) {
                    case 0:
                        return m10;
                    case 1:
                        return m11;
                    case 2:
                        return m12;
                    case 3:
                        return m13;
                }
            case 2:
                switch (col) {
                    case 0:
                        return m20;
                    case 1:
                        return m21;
                    case 2:
                        return m22;
                    case 3:
                        return m23;
                }
            case 3:
                switch (col) {
                    case 0:
                        return m30;
                    case 1:
                        return m31;
                    case 2:
                        return m32;
                    case 3:
                        return m33;
                }
        }
        throw new IllegalArgumentException("row and col must be greater or equal to zero and smaller or equal to three");
    }

    public Matrix4f add(Matrix4f m) {
        return new Matrix4f(
                m00 + m.m00, m01 + m.m01, m02 + m.m02, m03 + m.m03,
                m10 + m.m10, m11 + m.m11, m12 + m.m12, m13 + m.m13,
                m20 + m.m20, m21 + m.m21, m22 + m.m22, m23 + m.m23,
                m30 + m.m30, m31 + m.m31, m32 + m.m32, m33 + m.m33
        );
    }

    public Matrix4f subtract(Matrix4f m) {
        return new Matrix4f(
                m00 - m.m00, m01 - m.m01, m02 - m.m02, m03 - m.m03,
                m10 - m.m10, m11 - m.m11, m12 - m.m12, m13 - m.m13,
                m20 - m.m20, m21 - m.m21, m22 - m.m22, m23 - m.m23,
                m30 - m.m30, m31 - m.m31, m32 - m.m32, m33 - m.m33
        );
    }

    public Matrix4f multiply(float n) {
        return new Matrix4f(
                m00 * n, m01 * n, m02 * n, m03 * n,
                m10 * n, m11 * n, m12 * n, m13 * n,
                m20 * n, m21 * n, m22 * n, m23 * n,
                m30 * n, m31 * n, m32 * n, m33 * n
        );
    }

    // switch rows and cols
    public Matrix4f transpose() {
        return new Matrix4f(
                m00, m10, m20, m30,
                m01, m11, m21, m31,
                m02, m12, m22, m32,
                m03, m13, m23, m33
        );
    }

    public Matrix4f multiply(Matrix4f m) {
        return new Matrix4f(
                m00 * m.m00 + m01 * m.m10 + m02 * m.m20 + m03 * m.m30,
                m00 * m.m01 + m01 * m.m11 + m02 * m.m21 + m03 * m.m31,
                m00 * m.m02 + m01 * m.m12 + m02 * m.m22 + m03 * m.m32,
                m00 * m.m03 + m01 * m.m13 + m02 * m.m23 + m03 * m.m33,
                m10 * m.m00 + m11 * m.m10 + m12 * m.m20 + m13 * m.m30,
                m10 * m.m01 + m11 * m.m11 + m12 * m.m21 + m13 * m.m31,
                m10 * m.m02 + m11 * m.m12 + m12 * m.m22 + m13 * m.m32,
                m10 * m.m03 + m11 * m.m13 + m12 * m.m23 + m13 * m.m33,
                m20 * m.m00 + m21 * m.m10 + m22 * m.m20 + m23 * m.m30,
                m20 * m.m01 + m21 * m.m11 + m22 * m.m21 + m23 * m.m31,
                m20 * m.m02 + m21 * m.m12 + m22 * m.m22 + m23 * m.m32,
                m20 * m.m03 + m21 * m.m13 + m22 * m.m23 + m23 * m.m33,
                m30 * m.m00 + m31 * m.m10 + m32 * m.m20 + m33 * m.m30,
                m30 * m.m01 + m31 * m.m11 + m32 * m.m21 + m33 * m.m31,
                m30 * m.m02 + m31 * m.m12 + m32 * m.m22 + m33 * m.m32,
                m30 * m.m03 + m31 * m.m13 + m32 * m.m23 + m33 * m.m33);
    }


}
