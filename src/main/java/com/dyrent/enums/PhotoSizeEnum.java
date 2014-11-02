package com.dyrent.enums;

/**
 * Created with IntelliJ IDEA.
 * Author: Jerry.hu
 * Create: Jerry.hu (14-5-9 14:21)
 * Description:
 * To change this template use File | Settings | File Templates.
 */
public enum PhotoSizeEnum {
	SMALL_60X80, SMALL_100X75, MIDDLE_150X200, MIDDLE_200X150, BIG_SIZE_500X375, BIG_SIZE_600X450, ORIGINAL;

    @Override
    public String toString() {
        switch (this){
            case SMALL_60X80:  return "_60x80";
            case SMALL_100X75: return "_100x75";
            case MIDDLE_150X200: return "_150x200";
            case MIDDLE_200X150: return "_200x150";
            case BIG_SIZE_500X375: return "_500x375";
            case BIG_SIZE_600X450: return "_600x450";
            default:
                return "";
        }

    }
}
