package org.magnos.entity.test.helper;

import org.magnos.entity.ComponentFactory;

public class Vector implements ComponentFactory<Vector> 
{
	public float x, y;
	
	public Vector()
	{
	}
	
	public Vector( Vector v )
	{
		set( v.x, v.y );
	}
	
	public Vector(float x, float y) 
	{
		set( x, y );
	}
	
	public void set(float vx, float vy)
	{
		x = vx;
		y = vy;
	}
	
	@Override
	public Vector create() 
	{
		return copy( this, new Vector() );
	}
	
	@Override
	public Vector clone( Vector value ) 
	{
		return copy( value, new Vector() );
	}

	@Override
	public Vector copy(Vector from, Vector to) 
	{
		to.x = from.x;
		to.y = from.y;
		
		return to;
	}
	
	public void addsi( Vector v, float s) 
	{
		x += v.x * s;
		y += v.y * s;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits( x );
		result = prime * result + Float.floatToIntBits( y );
		return result;
	}

	@Override
	public boolean equals( Object obj )
	{
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Vector other = (Vector) obj;
		if ( Float.floatToIntBits( x ) != Float.floatToIntBits( other.x ) )
			return false;
		if ( Float.floatToIntBits( y ) != Float.floatToIntBits( other.y ) )
			return false;
		return true;
	}
	
}