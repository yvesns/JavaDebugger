package javadebugger;

import java.util.ArrayList;

public class ClassData {
    private String jniSignature;
    private String generictSignature;
    private String sourceFileName;
    private int classStatus;
    private int modifiers;
    private int[] methodsIDs;
    private long id;
    private int[] implementedInterfaces;
    private int minorVersion;
    private int majorVersion;
    private boolean isInterface;
    private boolean isArray;
    private boolean isModifiable;
    
    public ClassData(long id){
        this.id = id;
    }

    public String getJniSignature() {
        return jniSignature;
    }

    public void setJniSignature(String jniSignature) {
        this.jniSignature = jniSignature;
    }

    public String getGenerictSignature() {
        return generictSignature;
    }

    public void setGenerictSignature(String generictSignature) {
        this.generictSignature = generictSignature;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public int getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(int classStatus) {
        this.classStatus = classStatus;
    }

    public int getModifiers() {
        return modifiers;
    }

    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

    public int[] getMethodsIDs() {
        return methodsIDs;
    }

    public void setMethodsIDs(int[] methodsIDs) {
        this.methodsIDs = methodsIDs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int[] getImplementedInterfaces() {
        return implementedInterfaces;
    }

    public void setImplementedInterfaces(int[] implementedInterfaces) {
        this.implementedInterfaces = implementedInterfaces;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setIsInterface(boolean isInterface) {
        this.isInterface = isInterface;
    }

    public boolean isArray() {
        return isArray;
    }

    public void setIsArray(boolean isArray) {
        this.isArray = isArray;
    }

    public boolean isModifiable() {
        return isModifiable;
    }

    public void setIsModifiable(boolean isModifiable) {
        this.isModifiable = isModifiable;
    }
}
