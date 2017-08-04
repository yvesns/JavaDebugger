package javadebugger;

public class MethodData {
    private String name;
    private String signature;
    private String genericSignature;
    private int declaringClass;
    private int modifiers;
    private int maxLocals;
    private int argumentsSize;
    private boolean isNative;
    private boolean isSynthetic;
    private boolean isObsolete;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getGenericSignature() {
        return genericSignature;
    }

    public void setGenericSignature(String genericSignature) {
        this.genericSignature = genericSignature;
    }

    public int getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(int declaringClass) {
        this.declaringClass = declaringClass;
    }

    public int getModifiers() {
        return modifiers;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public int getArgumentsSize() {
        return argumentsSize;
    }

    public void setArgumentsSize(int argumentsSize) {
        this.argumentsSize = argumentsSize;
    }

    public boolean isIsNative() {
        return isNative;
    }

    public void setIsNative(boolean isNative) {
        this.isNative = isNative;
    }

    public boolean isIsSynthetic() {
        return isSynthetic;
    }

    public void setIsSynthetic(boolean isSynthetic) {
        this.isSynthetic = isSynthetic;
    }

    public boolean isIsObsolete() {
        return isObsolete;
    }

    public void setIsObsolete(boolean isObsolete) {
        this.isObsolete = isObsolete;
    }
}
