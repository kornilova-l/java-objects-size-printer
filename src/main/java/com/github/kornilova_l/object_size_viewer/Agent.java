package com.github.kornilova_l.object_size_viewer;

import java.lang.instrument.Instrumentation;

@SuppressWarnings("unused")
public class Agent {
    public static void premain(String packageName, Instrumentation inst) {
        if (packageName == null || packageName.equals("")) {
            System.out.println("Package name is not specified.\nSizes of all objects will be printed");
            packageName = "";
        }
        ObjectSizeEstimator.initInstance(inst);
        inst.addTransformer(new Transformer(packageName.replace('.', '/')));
    }
}
