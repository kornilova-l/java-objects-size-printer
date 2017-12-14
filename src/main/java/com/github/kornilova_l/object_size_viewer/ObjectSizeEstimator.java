package com.github.kornilova_l.object_size_viewer;

import java.lang.instrument.Instrumentation;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ObjectSizeEstimator {
    private static ObjectSizeEstimator estimator = null;
    private final Instrumentation inst;
    private final Set<Class> printedClasses = new HashSet<>();

    private ObjectSizeEstimator(Instrumentation inst) {
        this.inst = inst;
    }

    static void initInstance(Instrumentation inst) {
        estimator = new ObjectSizeEstimator(inst);
    }

    /**
     * It is used in instrumented classes
     */
    @SuppressWarnings("unused")
    public static ObjectSizeEstimator getInstance() {
        if (estimator == null) {
            throw new IllegalStateException("ObjectSizeEstimator was not initialized. " +
                    "It should be done in premain method");
        }
        return estimator;
    }

    /**
     * It is used in instrumented classes
     */
    @SuppressWarnings("unused")
    public void printInstrumentationSize(final Object object) {
        if (!printedClasses.contains(object.getClass())) {
            String className = object.getClass().getSimpleName();
            if (className.length() < 15) {
                /* kinda ugly */
                className = className + String.join("", Collections.nCopies(15 - className.length(), " "));
            }
            System.out.println(className + " \t" +
                    inst.getObjectSize(object) + " bytes. Package: " + object.getClass().getPackage().getName());
            printedClasses.add(object.getClass());
        }
    }
}
