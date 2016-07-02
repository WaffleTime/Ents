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
 * A filter that only returns Entities that are expired or alive.
 *
 * @author Philip Diffenderfer
 * @see EntityIterator
 */
public class ExpiredFilter implements EntityFilter {

    /**
     * A single instance to a ExpiredFilter that returns expired entities.
     */
    public static final ExpiredFilter TRUE = new ExpiredFilter(true);

    /**
     * A single instance to a ExpiredFilter that returns non-expired entities.
     */
    public static final ExpiredFilter FALSE = new ExpiredFilter(false);

    public final boolean expired;

    private ExpiredFilter(boolean expired) {
        this.expired = expired;
    }

    @Override
    public boolean isValid(Entity e) {
        return e.isExpired() == expired;
    }

}
