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

package asteroids.ogl;

import static asteroids.Components.ANGLE;
import static asteroids.Components.COLOR;
import static asteroids.Components.POSITION;
import static asteroids.Components.RADIUS;
import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glDeleteLists;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Color;
import java.util.Random;

import com.wafflesoft.ents.Entity;
import com.wafflesoft.ents.Renderer;

import asteroids.Vector;


public class OpenGLAsteroidRenderer implements Renderer
{

	public float dentPercent;
	public int spokeCount;
	public Random random;
	public int callList;

	public OpenGLAsteroidRenderer( float dentPercent, int spokeCount, Random random )
	{
		this( dentPercent, spokeCount, random, -1 );
	}

	public OpenGLAsteroidRenderer( float dentPercent, int spokeCount, Random random, int callList )
	{
		this.dentPercent = dentPercent;
		this.spokeCount = spokeCount;
		this.random = random;
		this.callList = callList;
	}

	@Override
	public Renderer create( Entity e )
	{
		final float angleIncrease = (float)(Math.PI * 2 / spokeCount);
        
        int callList = glGenLists( 1 );
        glNewList( callList, GL_COMPILE );
        glPolygonMode( GL_FRONT, GL_FILL );
        glBegin( GL_TRIANGLE_FAN );
        
        float ps = nextSpoke();

        glVertex2f( 0, 0 );
        
        for (int i = 0; i <= spokeCount; i++)
        {
            float s = (i == spokeCount ? ps : nextSpoke());
            float ang = i * angleIncrease;
            float dx = (float)Math.cos( ang );
            float dy = (float)Math.sin( ang );
            
            glVertex2f( dx * s, dy * s );
        }
        
        glEnd();
        glEndList();
		
		return new OpenGLAsteroidRenderer( dentPercent, spokeCount, random, callList );
	}
    
    private float nextSpoke()
    {
        return 1.0f - (random.nextFloat() * dentPercent);
    }

	@Override
	public void begin( Entity e, Object drawState )
	{
        Vector pos = e.get( POSITION );
        float ang = e.get( ANGLE ).v;
        float rad = e.get( RADIUS ).v;
        Color clr = e.get( COLOR );
        
        glPushMatrix();
        glTranslatef( pos.x, pos.y, 0.0f );
        glRotatef( (float)Math.toDegrees( ang ), 0, 0, 1 );
        glScalef( rad, rad, 1.0f );
        
        glScalef( 1.1f, 1.1f, 1.0f );
        OpenGLAsteroids.bind( clr.darker() );
        glCallList( callList );
        
        glScalef( 0.9f, 0.9f, 1.0f );
        OpenGLAsteroids.bind( clr );
        glCallList( callList );
        
        glPopMatrix();
	}

	@Override
	public void end( Entity e, Object drawState )
	{

	}

	@Override
	public void destroy( Entity e )
	{
		glDeleteLists( callList, 1 );
	}
    
    @Override
    public void notify( Entity e, int message ) 
    {
        
    } 

}
