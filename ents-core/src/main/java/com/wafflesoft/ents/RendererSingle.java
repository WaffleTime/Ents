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

/**
 * An abstract {@link Renderer} implementation for Renderer's which don't
 * store draw state information about each entity.
 *
 * @author Philip Diffenderfer
 */
public abstract class RendererSingle implements Renderer {

    @Override
    public Renderer create(Entity e) {
        return this;
    }

    @Override
    public void end(Entity e, Object drawState) {

    }

    @Override
    public void destroy(Entity e) {

    }

    @Override
    public void notify(Entity e, int message) {

    }

}
