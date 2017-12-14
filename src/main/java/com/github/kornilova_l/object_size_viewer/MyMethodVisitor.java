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
//        mv.visitMethodInsn();
        /*
        GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    LDC "Hello"
    INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
         */
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(Ljava/lang/String;)V", false);
        System.out.println("Hello");
    }
}
