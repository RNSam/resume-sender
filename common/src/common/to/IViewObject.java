package common.to;

import common.entity.struct.Field;

import java.io.Serializable;

import java.util.ArrayList;

public interface IViewObject extends Serializable{
    public ArrayList<Field> getFields();
}
