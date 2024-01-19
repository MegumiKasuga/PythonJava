package io_pipeline;

import var_type.var_roots.InstanceObject;

public interface IPipeLine {

    void output(InstanceObject... object);
    InstanceObject[] waitForInput(InstanceObject... parameters);
}
