/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author hoang
 */
public class Function {
    private String id;
    private String functionName;
    private boolean STT;

    public Function() {
    }

    public Function(String id, String functionName, boolean STT) {
        this.id = id;
        this.functionName = functionName;
        this.STT = STT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public boolean isSTT() {
        return STT;
    }

    public void setSTT(boolean STT) {
        this.STT = STT;
    }
    
    
    
}
