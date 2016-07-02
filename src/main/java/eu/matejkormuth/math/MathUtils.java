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
package eu.matejkormuth.math;

import eu.matejkormuth.math.ranges.DoubleRange;
import eu.matejkormuth.math.ranges.FloatRange;
import eu.matejkormuth.math.ranges.IntRange;
import eu.matejkormuth.math.ranges.LongRange;
import eu.matejkormuth.math.vectors.Vector3d;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtils {

    // Clamp

    /**
     * Clamps specified float value between min and max values. Consider using {@link FloatRange}.
     *
     * @param val value to be clamped
     * @param min minimum value
     * @param max maximum value
     * @return value clamped between min and max
     */
    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    /**
     * Clamps specified double value between min and max values. Consider using {@link DoubleRange}.
     *
     * @param val value to be clamped
     * @param min minimum value
     * @param max maximum value
     * @return value clamped between min and max
     */
    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    /**
     * Clamps specified integer value between min and max values. Consider using {@link IntRange}.
     *
     * @param val value to be clamped
     * @param min minimum value
     * @param max maximum value
     * @return value clamped between min and max
     */
    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    /**
     * Clamps specified integer value between min and max values. Consider using {@link LongRange}.
     *
     * @param val value to be clamped
     * @param min minimum value
     * @param max maximum value
     * @return value clamped between min and max
     */
    public static long clamp(long val, long min, long max) {
        return Math.max(min, Math.min(max, val));
    }

    // Lerp
    public static float lerp(float min, float max, float f) {
        return min + f * (max - min);
    }

    public static double lerp(double min, double max, double f) {
        return min + f * (max - min);
    }

    public static Vector3d lerp(Vector3d min, Vector3d max, double f) {
        return min.add(max.subtract(min).multiply(f));
    }

    // Fast floor
    public static float floor(float n) {
        if (n > 0.0)
            return (int) n;
        return (int) n - 1;
    }

    public static double floor(double n) {
        if (n > 0.0)
            return (int) n;
        return (int) n - 1;
    }

    // Smoothstep (hermite interpolation)
    public static float smoothstep(float min, float max, float x) {
        x = clamp((x - min) / (max - min), 0.0f, 1.0f);
        return x * x * (3 - 2 * x);
    }

    public static double smoothstep(double min, double max, double x) {
        x = clamp((x - min) / (max - min), 0.0f, 1.0f);
        return x * x * (3 - 2 * x);
    }

    // Fade function
    public static double smootherstep(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);         // 6t^5 - 15t^4 + 10t^3
    }

    public static float smootherstep(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);         // 6t^5 - 15t^4 + 10t^3
    }

    // linear map
    public static double map(double value, double low1, double high1, double low2, double high2) {
        return low2 + (high2 - low2) * (value - low1) / (high1 - low1);
    }

    // functional remap
}
