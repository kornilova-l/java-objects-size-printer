package com.github.kornilova_l.object_size_viewer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {
    private String packageName;

    Transformer(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (className.startsWith(packageName)) {
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
            // uncomment for debugging
//                TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
            // SKIP_FRAMES avoids visiting frames that will be ignored and recomputed from scratch in the class writer.
            cr.accept(new MyClassVisitor(cw), ClassReader.SKIP_FRAMES);
            return cw.toByteArray();
        }
        return classfileBuffer;
    }
}
