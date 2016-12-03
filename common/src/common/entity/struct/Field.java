/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.entity.struct;

import java.io.Serializable;

public class Field  implements Serializable{
    private String name;
    private String caption;
    private boolean keyField;
    
    public Field(String aName, String aCaption) {
        name = aName;
        caption = aCaption;
        keyField = false;
    }
    
    public Field(String aName, String aCaption, boolean isKeyField) {
        name = aName;
        caption = aCaption;
        keyField = isKeyField;
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }
    
    public boolean isKeyField() {
        return keyField;
    }
    
    public String toString() {
        return name;
    }
}
