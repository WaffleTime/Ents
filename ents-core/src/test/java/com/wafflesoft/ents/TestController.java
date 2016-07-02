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

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;
import org.magnos.entity.Component;
import org.magnos.entity.Control;
import org.magnos.entity.Controller;
import org.magnos.entity.Ents;
import org.magnos.entity.Template;
import com.wafflesoft.ents.helper.Vector;
import org.magnos.entity.util.ComponentSet;
import org.magnos.entity.util.ControllerSet;


public class TestController
{

   @AfterClass
   public static void afterTest()
   {
      Ents.clear();
   }

   public static Component<Vector> POSITION = Ents.newComponent( "position", new Vector() );
   public static Component<Vector> VELOCITY = Ents.newComponent( "velocity", new Vector() );
   public static Component<Vector> ACCELERATION = Ents.newComponent( "acceleration", new Vector() );

   public static Controller PHYSICS_SIMPLE = Ents.newController( "physics-simple", new Control() {

      public void update( Entity e, Object updateState )
      {
         float dt = (Float)updateState;
         e.get( POSITION ).addsi( e.get( VELOCITY ), dt );
      }
   } );

   public static Controller PHYSICS = Ents.newController( "physics", new Control() {

      public void update( Entity e, Object updateState )
      {
         float dt = (Float)updateState;
         e.get( POSITION ).addsi( e.get( VELOCITY ), dt );
         e.get( VELOCITY ).addsi( e.get( ACCELERATION ), dt );
      }
   } );

   public static Template SPRITE = Ents.newTemplate( "sprite", new ComponentSet( POSITION, VELOCITY ), new ControllerSet( PHYSICS_SIMPLE ), null );

   @Test
   public void testUpdate()
   {
      Entity e = new Entity( SPRITE );
      e.enable( PHYSICS_SIMPLE );

      Vector p = e.get( POSITION );
      Vector v = e.get( VELOCITY );
      p.x = 5.0f;
      p.y = 2.0f;
      v.x = 3.0f;
      v.y = -1.5f;

      final float dt = 0.5f;

      e.update( dt );

      assertEquals( 6.5f, p.x, 0.0001f );
      assertEquals( 1.25f, p.y, 0.0001f );

      e.disable( PHYSICS_SIMPLE );
      e.update( dt );

      assertEquals( 6.5f, p.x, 0.0001f );
      assertEquals( 1.25f, p.y, 0.0001f );

      e.enable( PHYSICS_SIMPLE );
      e.update( dt );

      assertEquals( 8.0f, p.x, 0.0001f );
      assertEquals( 0.5f, p.y, 0.0001f );
   }

}
