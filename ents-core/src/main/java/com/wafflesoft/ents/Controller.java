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

package com.wafflesoft.ents;

/**
 * A controller is something that updates the state of an {@link Entity} every
 * time {@link Entity#update(Object)} is called. An Entity can have many
 * Controllers at a time, and they are executed in the order that they are added
 * (Controllers added from a {@link Template} reside first in an Entity). <br/>
 * <br/>
 * <b>Controller Examples</b>
 * <ul>
 * <li>User Input</li>
 * <li>Physics / Collisions</li>
 * <li>Networking</li>
 * <li>AI (State Machine, Steering Behaviors)</li>
 * <li>Emit Particles</li>
 * <li>Record / Replay Entity</li>
 * </ul>
 *
 * @author Philip Diffenderfer
 */
public class Controller extends Id {

    /**
     * The Control implementation to invoke for this Controller.
     */
    public Control control;

    /**
     * Instantiates a new Controller.
     *
     * @param id      The id of the controller.
     * @param name    The name of the controller.
     * @param control The {@link Control} implementation.
     */
    public Controller(int id, String name, Control control) {
        super(id, name);

        this.control = control;
    }

}
