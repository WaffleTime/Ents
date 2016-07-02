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
 * A filter that only returns entities that have a very specific template (and
 * not a custom one). The template comparison is done by reference and not by
 * the actual components, controllers, and view on the template and Entity.
 *
 * @author Philip Diffenderfer
 * @see EntityIterator
 */
public class TemplateExactFilter implements EntityFilter {

    protected Template template;

    /**
     * Instantiates a TemplateExactFilter without a template. The
     * {@link #set(Template)} method needs to be called, otherwise a
     * {@link NullPointerException} will be thrown.
     */
    public TemplateExactFilter() {
    }

    /**
     * Instantiates a TemplateExactFilter.
     *
     * @param template The template the Entities filtered must have.
     */
    public TemplateExactFilter(Template template) {
        set(template);
    }

    /**
     * Resets and returns this filter by specifying the template the Entities
     * must have.
     *
     * @param template The template the Entities filtered must have.
     * @return The reference to this filter.
     */
    public TemplateExactFilter set(Template template) {
        this.template = template;

        return this;
    }

    @Override
    public boolean isValid(Entity e) {
        return e.getTemplate() == template;
    }

}
