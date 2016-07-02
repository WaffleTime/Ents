package particles;

import com.wafflesoft.ents.ComponentValueFactory;


public class NullComponentValueFactory<T> implements ComponentValueFactory<T>
{

    @Override
    public T create()
    {
        return null;
    }

    @Override
    public T clone( T value )
    {
        return value;
    }

    @Override
    public T copy( T from, T to )
    {
        return to;
    }

}
