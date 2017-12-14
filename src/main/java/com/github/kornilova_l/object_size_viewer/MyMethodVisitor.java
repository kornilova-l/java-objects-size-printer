package com.github.kornilova_l.object_size_viewer;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

class MyMethodVisitor extends AdviceAdapter {
    MyMethodVisitor(int access, String methodName, String desc, MethodVisitor mv) {
        super(Opcodes.ASM5, mv, access, methodName, desc);
    }

    @Override
    protected void onMethodEnter() {
        /* get instance of ObjectSizeEstimator that contains Instrumentation */
        mv.visitMethodInsn(INVOKESTATIC, "com/github/kornilova_l/object_size_viewer/ObjectSizeEstimator",
                "getInstance", "()Lcom/github/kornilova_l/object_size_viewer/ObjectSizeEstimator;", false);
        /* load `this` to stack */
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/kornilova_l/object_size_viewer/ObjectSizeEstimator",
                "printInstrumentationSize", "(Ljava/lang/Object;)V", false);
    }
}
