package com.github.kornilova_l.object_size_viewer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {
    private String packageName;

    Transformer(String packageName) {
        this.packageName = packageName;
    }

    private static boolean hasSystemCLInChain(ClassLoader loader) {
        ClassLoader chainLoader = loader;
        while (chainLoader != null) {
            if (chainLoader == ClassLoader.getSystemClassLoader()) {
                return true;
            }
            chainLoader = chainLoader.getParent();
        }
        return false;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        /* this agent does not like classes that were loaded by classloaders that
         * do not have system classloader in chain.
         * These classes cause troubles to agent so he decided not to instrument them.
         * (problem can be solved using reflection but I do not want to do it in this small project)*/
        if (className.startsWith(packageName) &&
                hasSystemCLInChain(loader)) {
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
            // For debugging uncomment next line and add cv to cr.accept (instead of cw)
//            TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));

            /* SKIP_FRAMES means that FRAMES will be excluded
             * they will be recomputed from scratch because COMPUTE_FRAMES flag is set. */
            cr.accept(new MyClassVisitor(cw), ClassReader.SKIP_FRAMES);
            return cw.toByteArray();
        }
        return classfileBuffer;
    }
}
