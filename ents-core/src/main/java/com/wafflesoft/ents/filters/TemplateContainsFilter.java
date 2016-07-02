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

import com.wafflesoft.ents.Template;
import com.wafflesoft.ents.Entity;
import com.wafflesoft.ents.EntityFilter;
import com.wafflesoft.ents.EntityIterator;


/**
 * A filter that returns all Entities that at least have all components,
 * controllers, and view of a given template.
 *
 * @author Philip Diffenderfer
 * @see EntityIterator
 */
public class TemplateContainsFilter implements EntityFilter {

    protected Template template;

    /**
     * Instantiates a TemplateContainsFilter without a template. The
     * {@link #set(Template)} method needs to be called,
     * otherwise a {@link NullPointerException} will be thrown.
     */
    public TemplateContainsFilter() {

    }

    /**
     * Instantiates a TemplateContainsFilter.
     *
     * @param template The template to use which has the components, controllers, and
     *                 view the entities returned will have.
     */
    public TemplateContainsFilter(Template template) {
        set(template);
    }

    /**
     * Resets and returns this filter by specifying the template used to check
     * for containment.
     *
     * @param template The template to use which has the components, controllers, and
     *                 view the entities returned will have.
     * @return The reference to this filter.
     */
    public TemplateContainsFilter set(Template template) {
        this.template = template;

        return this;
    }

    @Override
    public boolean isValid(Entity e) {
        return e.getTemplate().contains(template);
    }

}
