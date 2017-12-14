package com.github.kornilova_l.object_size_viewer;

import java.lang.instrument.Instrumentation;

public class Agent {
    public static void premain(String packageName, Instrumentation inst) {
        ObjectSizeEstimator.initInstance(inst);
        inst.addTransformer(new Transformer(packageName));
    }
}
