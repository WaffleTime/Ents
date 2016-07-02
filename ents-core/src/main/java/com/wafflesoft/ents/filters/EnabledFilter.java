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
 * A filter that only returns Entities that are enabled or disabled.
 *
 * @author Philip Diffenderfer
 * @see EntityIterator
 */
public class EnabledFilter implements EntityFilter {

    /**
     * A single instance to a EnabledFilter for enabled entities.
     */
    public static final EnabledFilter TRUE = new EnabledFilter(true);

    /**
     * A single instance to a EnabledFilter for disable entities.
     */
    public static final EnabledFilter FALSE = new EnabledFilter(false);

    public final boolean enabled;

    private EnabledFilter(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isValid(Entity e) {
        return e.isEnabled() == enabled;
    }

}
