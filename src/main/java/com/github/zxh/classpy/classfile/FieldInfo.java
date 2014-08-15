package com.github.zxh.classpy.classfile;

import com.github.zxh.classpy.classfile.attr.AttributeInfo;
import java.util.Arrays;
import java.util.List;

/*
field_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class FieldInfo extends ClassComponent {

    private U2 accessFlags;
    private U2CpIndex nameIndex;
    private U2CpIndex descriptorIndex;
    private U2 attributesCount;
    private Table<AttributeInfo> attributes;
    
    @Override
    protected void readContent(ClassReader reader) {
        accessFlags = reader.readU2();
        nameIndex = reader.readU2CpIndex();
        descriptorIndex = reader.readU2CpIndex();
        attributesCount = reader.readU2();
        attributes = reader.readTable(AttributeInfo.class, attributesCount);
        setDesc(reader.getConstantPool().getUtf8String(nameIndex.getValue()));
    }

    @Override
    public List<ClassComponent> getSubComponents() {
        return Arrays.asList(accessFlags, nameIndex, descriptorIndex,
                attributesCount, attributes);
    }
    
}