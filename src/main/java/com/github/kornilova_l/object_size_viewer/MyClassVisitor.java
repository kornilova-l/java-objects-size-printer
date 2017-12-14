package com.github.kornilova_l.object_size_viewer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

class MyClassVisitor extends ClassVisitor {
    MyClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, methodName, desc, signature, exceptions);
        if (methodName.equals("<init>")) {
            return new MyMethodVisitor(access, methodName, desc, mv);
        }
        return mv;
    }
}
