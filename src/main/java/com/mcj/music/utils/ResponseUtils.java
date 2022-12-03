package com.mcj.music.utils;

import java.io.Serializable;

/**
 * @author mcj
 * @date 2022/10/30 15:31
 * @email 2037612492@qq.com
 * @description
 */
public class ResponseUtils<T> implements Serializable {

    private static final long serialVersionUID = 3728877563912075885L;

    private int code;
    private String msg;
    private T data;


    public ResponseUtils() {

    }

    public ResponseUtils(int code, String message, T data) {
        this.code = code;
        this.setMsg(message);
        this.data = data;
    }

    public ResponseUtils(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseUtils(int code, String message) {
        this.code = code;
        this.setMsg(message);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public T getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }


    /**
     * 成功时-返回data
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseUtils<T> success(T data) {
        return new ResponseUtils<T>(200, null, data);
    }

    /**
     * 成功-不返回data
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseUtils<T> success(String msg) {
        return new ResponseUtils<T>(200, msg);
    }

    /**
     * 成功-返回data+msg
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseUtils<T> success(String msg, T data) {
        return new ResponseUtils<T>(200, msg, data);
    }

    /**
     * 失败
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseUtils<T> fail(String msg) {
        return new ResponseUtils<T>(500, msg, null);
    }

    /**
     * 失败-code
     *
     * @param <T>
     * @return
     */
    public static <T> ResponseUtils<T> fail(int code, String msg) {
        return new ResponseUtils<T>(code, msg, null);
    }


    @Override
    public String toString() {
        return "RestResponse{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

}
