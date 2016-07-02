package particles;

import com.wafflesoft.ents.ComponentValueFactory;

public interface Attribute<T extends Attribute<T>> extends ComponentValueFactory<T>
{
    public T scale(float scale);
    public T add(T a, float amount);
    public T set(T value);
    public boolean isZero();
}
