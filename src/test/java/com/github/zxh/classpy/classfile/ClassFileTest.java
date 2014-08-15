package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.TestClass;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClassFileTest {
    
    @Test
    public void classFile() throws Exception {
        byte[] classBytes = loadClass(TestClass.class);
        ClassFile cf = ClassParser.parse(classBytes);
        assertEquals(0, cf.getMinorVersion().getValue());
        assertEquals(52, cf.getMajorVersion().getValue());
        assertEquals(113, cf.getConstantPoolCount().getValue());
        assertEquals(2, cf.getInterfacesCount().getValue());
        assertEquals(9, cf.getFieldsCount().getValue());
        assertEquals(11, cf.getMethodsCount().getValue());
        assertEquals(4, cf.getAttributesCount().getValue());
    }
    
    @Test
    public void enclosingMethodAttribute() throws Exception {
        String classFileName = TestClass.class.getName().replace('.', '/') + "$1.class";
        byte[] classBytes = loadClass(classFileName);
        ClassParser.parse(classBytes);
    }
    
    @Test
    public void signatures() {
        // todo
    }
    
    private static byte[] loadClass(Class<?> cls) throws Exception {
        String classFileName = cls.getName().replace('.', '/') + ".class";
        return loadClass(classFileName);
    }
    
    private static byte[] loadClass(String classFileName) throws Exception {
        ClassLoader cl =TestClass.class.getClassLoader();
        Path classFilePath = Paths.get(cl.getResource(classFileName).toURI());
        byte[] classBytes = Files.readAllBytes(classFilePath);
        return classBytes;
    }
    
}