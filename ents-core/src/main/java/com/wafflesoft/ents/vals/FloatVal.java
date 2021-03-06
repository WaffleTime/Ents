/* 
 * NOTICE OF LICENSE
 * 
 * This source file is subject to the Open Software License (OSL 3.0) that is 
 * bundled with this package in the file LICENSE.txt. It is also available 
 * through the world-wide-web at http://opensource.org/licenses/osl-3.0.php
 * If you did not receive a copy of the license and are unable to obtain it 
 * through the world-wide-web, please send an email to magnos.software@gmail.com 
 * so we can send you a copy immediately. If you use any of this software please
 * notify me via our website or email, your feedback is much appreciated. 
 * 
 * @copyright   Copyright (c) 2011 Magnos Software (http://www.magnos.org)
 * @license     http://opensource.org/licenses/osl-3.0.php
 * 				Open Software License (OSL 3.0)
 */

package com.wafflesoft.ents.vals;

import com.wafflesoft.ents.ComponentValueFactory;


/**
 * A mutable wrapper around a float that should replace the usage of the
 * {@link Float}. Using {@link Float} should be avoided, the "invisible"
 * autoboxing and unboxing can cause a significant performance hit in games when
 * used inappropriately.
 *
 * @author Philip Diffenderfer
 */
public class FloatVal implements ComponentValueFactory<FloatVal> {

    public float v;

    public FloatVal() {
    }

    public FloatVal(float v) {
        this.v = v;
    }

    public FloatVal(FloatVal iv) {
        this.v = iv.v;
    }

    @Override
    public FloatVal create() {
        return new FloatVal(v);
    }

    @Override
    public FloatVal clone(FloatVal value) {
        return new FloatVal(value);
    }

    @Override
    public FloatVal copy(FloatVal from, FloatVal to) {
        to.v = from.v;

        return to;
    }

    @Override
    public int hashCode() {
        return Float.floatToIntBits(v);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof FloatVal) && (Float.floatToIntBits(((FloatVal) obj).v) == Float.floatToIntBits(v));
    }

    @Override
    public String toString() {
        return String.valueOf(v);
    }

}
