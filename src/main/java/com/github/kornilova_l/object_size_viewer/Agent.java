package com.github.kornilova_l.object_size_viewer;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String config, Instrumentation inst) {
        ObjectSizeEstimator.initInstance(inst);
        inst.addTransformer(new Transformer());
    }
}
