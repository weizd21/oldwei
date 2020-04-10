package top.oldwei.common.base;

import lombok.Data;
import top.oldwei.common.constant.ResponseCode;

import java.io.Serializable;

/**
 * @Author:weizd
 * @Date:20-3-5
 */
@Data
public class R<T> implements Serializable {


    private R(){}

    private String msg;

    private Integer code;

    private T data;


    private R(Integer code , T data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static <T> R<T> success(){
        return getR(ResponseCode.SUCCESS,null,null);
    }

    public static <T> R<T> success(T data){
        return getR(ResponseCode.SUCCESS,data,null);
    }

    public static <T> R<T> success(T data, String msg){
        return getR(ResponseCode.SUCCESS,data,msg);
    }


    public static <T> R<T> fail(){
        return getR(ResponseCode.FAIL,null,null);
    }

    public static <T> R<T> fail(String msg){
        return getR(ResponseCode.FAIL,null,msg);
    }

    public static <T> R<T> fail(T data, String msg){
        return getR(ResponseCode.FAIL,data,msg);
    }





    private static <T> R<T> getR(Integer code , T data, String msg){
        R r = new R(code ,data,msg);
        return r;
    }



}
