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

package com.wafflesoft.ents.filters;

import com.wafflesoft.ents.Entity;
import com.wafflesoft.ents.EntityFilter;
import com.wafflesoft.ents.EntityIterator;


/**
 * A filter that returns the opposite of what another filter would return.
 *
 * @author Philip Diffenderfer
 * @see EntityIterator
 */
public class NotFilter implements EntityFilter {

    protected EntityFilter filter;

    /**
     * Instantiates a NotFilter without two filters. The
     * {@link #set(EntityFilter)} method needs to be called, otherwise a
     * {@link NullPointerException} will be thrown.
     */
    public NotFilter() {
    }

    /**
     * Instantiates a NotFilter.
     *
     * @param filter The filter to return the negation (opposite) of.
     */
    public NotFilter(EntityFilter filter) {
        set(filter);
    }

    /**
     * Resets and returns this filter by specifying the filter to return the
     * negation (opposite) of.
     *
     * @param filter The filter to return the negation (opposite) of.
     * @return The reference to this filter.
     */
    public NotFilter set(EntityFilter filter) {
        this.filter = filter;

        return this;
    }

    @Override
    public boolean isValid(Entity e) {
        return !filter.isValid(e);
    }

}
