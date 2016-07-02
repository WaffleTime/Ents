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
 *              Open Software License (OSL 3.0)
 */

package com.wafflesoft.ents;

import java.util.Arrays;


/**
 * A pool of component values. This is typically used for expensive and reusable
 * objects.
 *
 * @param <T> The component value type.
 * @author Philip Diffenderfer
 */
public class ComponentValuePool<T> implements ComponentValueFactory<T> {

    /**
     * The factory that creates the default value for an entity and creates new
     * values that will be pooled.
     */
    protected final ComponentValueFactory<T> factory;

    /**
     * The default value to copy from when cloning an Entity.
     */
    protected final T defaultValue;

    /**
     * An array of cached values to use when an entity needs a new one.
     */
    protected T[] pool;

    /**
     * The number of cached values.
     */
    protected int poolSize;

    /**
     * Instantiates a new ComponentValuePool.
     *
     * @param factory             The factory that creates the default value for an entity and
     *                            creates new values that will be pooled.
     * @param initialPoolCapacity The initial number of cached values this pool can store before it
     *                            needs to increase the underlying array by 50%.
     */
    @SuppressWarnings("unchecked")
    public ComponentValuePool(ComponentValueFactory<T> factory, int initialPoolCapacity) {
        this.factory = factory;
        this.defaultValue = factory.create();
        this.pool = (T[]) new Object[initialPoolCapacity];
        this.poolSize = 0;
    }

    /**
     * Implements {@link ComponentValueFactory#create()} by calling
     * {@link #pop()} to
     * ensure pooled values are used.
     */
    @Override
    public T create() {
        return pop();
    }

    /**
     * Implements {@link ComponentValueFactory#clone(Object)} by copying value
     * to
     * a {@link #pop()}'d value.
     */
    @Override
    public T clone(T value) {
        return factory.copy(value, pop());
    }

    /**
     * Implements {@link ComponentValueFactory#copy(Object, Object)} by calling
     * the
     * same method of the internal ComponentValueFactory.
     */
    @Override
    public T copy(T from, T to) {
        return factory.copy(from, to);
    }

    /**
     * Pushes the given value onto the pool.
     *
     * @param value The value to push into the pool.
     */
    protected void push(T value) {
        if (poolSize == pool.length) {
            pool = Arrays.copyOf(pool, poolSize + (poolSize >> 1));
        }

        pool[poolSize++] = value;
    }

    /**
     * Returns a new value from the pool. If the pool is empty
     * {@link ComponentValueFactory#create()} is called, otherwise the next
     * value on the pool is taken, set to the default value initially generated
     * by {@link ComponentValueFactory#create()}, and is returned.
     *
     * @return The reference to a value.
     */
    protected T pop() {
        return (poolSize == 0 ? factory.create() : factory.copy(defaultValue, pool[--poolSize]));
    }

    /**
     * @return The current number of cached values.
     */
    public int size() {
        return poolSize;
    }

}
