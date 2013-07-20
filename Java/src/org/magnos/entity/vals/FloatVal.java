package org.magnos.entity.vals;

import org.magnos.entity.ComponentFactory;

/**
 * A mutable wrapper around a float that should replace the usage of the
 * {@link Float}. Using {@link Float} should be 
 * avoided, the "invisible" autoboxing and unboxing can cause a significant
 * performance hit in games when used inappropriately.  
 * 
 * @author Philip Diffenderfer
 *
 */
public class FloatVal implements ComponentFactory<FloatVal> 
{
	
	public float v;
	
	public FloatVal()
	{
	}
	
	public FloatVal(float v)
	{
		this.v = v;
	}
	
	public FloatVal(FloatVal iv)
	{
		this.v = iv.v;
	}

	@Override
	public FloatVal create() 
	{
		return new FloatVal( v );
	}

	@Override
	public FloatVal clone(FloatVal value) 
	{
		return new FloatVal( value );
	}

	@Override
	public FloatVal copy(FloatVal from, FloatVal to) 
	{
		to.v = from.v;
		
		return to;
	}

	@Override
	public int hashCode() 
	{
		return Float.floatToIntBits(v);
	}

	@Override
	public boolean equals(Object obj) 
	{
		return (obj instanceof FloatVal) && (Float.floatToIntBits(((FloatVal)obj).v) == Float.floatToIntBits(v));
	}

	@Override
	public String toString() 
	{
		return String.valueOf( v );
	}
	
}
