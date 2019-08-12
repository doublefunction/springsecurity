package jit.wxs.demo.util;

public class CommonReturnType {
    //对应的返回处理结果，sucess/fail
    private  String status;

    private Object data;

    public static CommonReturnType create(String status,Object data){
        CommonReturnType commonReturnType =new CommonReturnType();
        commonReturnType.setStatus(status);
        commonReturnType.setData(data);
        return commonReturnType;
    }
    public static CommonReturnType create(Object data){
        CommonReturnType commonReturnType =new CommonReturnType();
        commonReturnType.setData(data);
        return commonReturnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
