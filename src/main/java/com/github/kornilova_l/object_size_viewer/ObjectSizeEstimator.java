package com.github.kornilova_l.object_size_viewer;

import java.lang.instrument.Instrumentation;

public class ObjectSizeEstimator {
    private static ObjectSizeEstimator estimator = null;
    private final Instrumentation inst;

    private ObjectSizeEstimator(Instrumentation inst) {
        this.inst = inst;
    }

    static void initInstance(Instrumentation inst) {
        if (estimator == null) {
            throw new IllegalStateException("ObjectSizeEstimator was not initialized. " +
                    "It should be done in premain method");
        }
        estimator = new ObjectSizeEstimator(inst);
    }

    public ObjectSizeEstimator getInstance() {
        return estimator;
    }

    public void printInstrumentationSize(final Object object) {
        System.out.println("Object of type '" + object.getClass() + "' has size of "
                + inst.getObjectSize(object) + " bytes.");
    }
}
