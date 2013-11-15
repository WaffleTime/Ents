
package asteroids.ogl;

import static org.lwjgl.opengl.GL11.*;

import asteroids.Vector;


/**
 * A class for drawing text in OpenGL with lines.
 * 
 * GLYPH:
 * 
 * <pre>
 * 0---1---2
 * |   |   |
 * 3---4---5
 * |   |   |
 * 6---7---8
 * |   |   |
 * 9---A---B
 * |   |   |
 * C---D---E
 * </pre>
 * 
 * @author Philip Diffenderfer
 * 
 */
public class OpenGLText
{

	private static int[][] GLYPHS = new int[256][];

	private static Vector[] GLYPH_POINTS = {
		new Vector( 0.0f, 0.0f ), new Vector( 0.5f, 0.0f ), new Vector( 1.0f, 0.0f ),
		new Vector( 0.0f, 0.25f ), new Vector( 0.5f, 0.25f ), new Vector( 1.0f, 0.25f ),
		new Vector( 0.0f, 0.5f ), new Vector( 0.5f, 0.5f ), new Vector( 1.0f, 0.5f ),
		new Vector( 0.0f, 0.72f ), new Vector( 0.5f, 0.75f ), new Vector( 1.0f, 0.75f ),
		new Vector( 0.0f, 1.0f ), new Vector( 0.5f, 1.0f ), new Vector( 1.0f, 1.0f )
	};

	static
	{
		GLYPHS['A'] = new int[] { 0x02, 0x0C, 0x2E, 0x68 };
		GLYPHS['B'] = new int[] { 0x02, 0x25, 0x57, 0x7B, 0xBE, 0xCE, 0x0C, 0x67 };
		GLYPHS['C'] = new int[] { 0x02, 0x0C, 0xCE };
		GLYPHS['D'] = new int[] { 0x01, 0x15, 0x5B, 0xBD, 0xCD, 0x0C };
		GLYPHS['E'] = new int[] { 0x02, 0x0C, 0xCE, 0x67 };
		GLYPHS['F'] = new int[] { 0x02, 0x0C, 0x67 };
		GLYPHS['G'] = new int[] { 0x02, 0x0C, 0xCE, 0xE8, 0x78 };
		GLYPHS['H'] = new int[] { 0x0C, 0x2E, 0x68 };
		GLYPHS['I'] = new int[] { 0x02, 0xCE, 0x1D };
		GLYPHS['J'] = new int[] { 0x2E, 0xCE, 0x6C };
		GLYPHS['K'] = new int[] { 0x0C, 0x26, 0x6E };
		GLYPHS['L'] = new int[] { 0x0C, 0xCE };
		GLYPHS['M'] = new int[] { 0x0C, 0x07, 0x27, 0x2E };
		GLYPHS['N'] = new int[] { 0x0C, 0x0E, 0x2E };
		GLYPHS['O'] = new int[] { 0x0C, 0x02, 0x2E, 0xCE };
		GLYPHS['P'] = new int[] { 0x0C, 0x02, 0x28, 0x68 };
		GLYPHS['Q'] = new int[] { 0x0C, 0x02, 0x2E, 0xCE, 0xAE };
		GLYPHS['R'] = new int[] { 0x0C, 0x02, 0x28, 0x68, 0x6E };
		GLYPHS['S'] = new int[] { 0x02, 0x06, 0x68, 0x8E, 0xCE };
		GLYPHS['T'] = new int[] { 0x02, 0x1D };
		GLYPHS['U'] = new int[] { 0x0C, 0xCE, 0x2E };
		GLYPHS['V'] = new int[] { 0x0D, 0x2D };
		GLYPHS['W'] = new int[] { 0x0C, 0xC7, 0x7E, 0x2E };
		GLYPHS['X'] = new int[] { 0x0E, 0x2C };
		GLYPHS['Y'] = new int[] { 0x07, 0x27, 0x7D };
		GLYPHS['Z'] = new int[] { 0x02, 0x2C, 0xCE };

		GLYPHS['1'] = new int[] { 0x13, 0x1D, 0xCE };
		GLYPHS['2'] = new int[] { 0x13, 0x15, 0x58, 0x8C, 0xCE };
		GLYPHS['3'] = new int[] { 0x13, 0x15, 0x57, 0x7B, 0xBD, 0x9D };
		GLYPHS['4'] = new int[] { 0x26, 0x68, 0x2E };
		GLYPHS['5'] = new int[] { 0x02, 0x06, 0x67, 0x7B, 0xBD, 0xCD };
		GLYPHS['6'] = new int[] { 0x02, 0x0C, 0xCE, 0x8E, 0x68 };
		GLYPHS['7'] = new int[] { 0x02, 0x2C };
		GLYPHS['8'] = new int[] { 0x02, 0x2E, 0xCE, 0x0C, 0x68 };
		GLYPHS['9'] = new int[] { 0x02, 0x06, 0x68, 0x2E };
		GLYPHS['0'] = new int[] { 0x02, 0x2E, 0x0C, 0xCE };

		GLYPHS[':'] = new int[] { 0x44, 0xAA };
		GLYPHS['-'] = new int[] { 0x68 };
	}

	public void drawString( float x, float y, float w, float h, float kerning, float lineWidth, String format, Object... args )
	{
		drawString( x, y, w, h, kerning, lineWidth, String.format( format, args ) );
	}

	public void drawString( float x, float y, float w, float h, float kerning, float lineWidth, CharSequence chars )
	{
		glPushMatrix();
		glLineWidth( lineWidth );
		glBegin( GL_LINES );

		for (int i = 0; i < chars.length(); i++)
		{
			x += drawGlyph( chars.charAt( i ), x, y, w, h ) * kerning;
		}

		glEnd();
		glLineWidth( 1.0f );
		glPopMatrix();
	}

	public float drawGlyph( char c, float ox, float oy, float cw, float ch )
	{
		c = Character.toUpperCase( c );

		int[] lines = GLYPHS[c];

		if (lines != null)
		{
			for (int i = 0; i < lines.length; i++)
			{
				Vector a = GLYPH_POINTS[(lines[i] >> 4) & 15];
				Vector b = GLYPH_POINTS[(lines[i] >> 0) & 15];

				glVertex2f( a.x * cw + ox, a.y * ch + oy );
				glVertex2f( b.x * cw + ox, b.y * ch + oy );
			}

			return cw;
		}
		else if (Character.isWhitespace( c ))
		{
			return cw;
		}

		return 0.0f;
	}

}
