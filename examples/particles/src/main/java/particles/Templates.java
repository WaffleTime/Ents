package particles;

import com.wafflesoft.ents.Ents;
import com.wafflesoft.ents.Template;
import com.wafflesoft.ents.util.ComponentSet;
import com.wafflesoft.ents.util.ControllerSet;

public class Templates
{
    public static Template PARTICLE_SYSTEM = Ents.newTemplate( "particle-system", new ComponentSet(), new ControllerSet(), Views.PARTICLE_SYSTEM );
}
