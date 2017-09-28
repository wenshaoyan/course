package wenshao.com.coursate.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;
import wenshao.com.coursate.bean.HttpApiResponse;

import static android.R.attr.data;

/**
 * Created by wenshao on 2017/9/23.
 * http返回数据解析
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {
    @Override
    public T convertResponse(Response response) throws Throwable {
        Type getType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) getType).getActualTypeArguments();

        // 获取泛型的所有类型
        Type type = params[0];

        if (!(type instanceof ParameterizedType)) throw new IllegalAccessException("没有填泛型参数");
        ParameterizedType paramsType = (ParameterizedType) type;
        Type rawType = paramsType.getRawType();
        Type typeArguments = paramsType.getActualTypeArguments()[0];

        ResponseBody body = response.body();
        if (body == null) return null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (rawType != HttpApiResponse.class) {
            T data = gson.fromJson(jsonReader, type);
            response.close();
            return data;
        }else{
            if (typeArguments==Void.class){
                throw new IllegalAccessException("泛型不能为void");
            }else{
                HttpApiResponse httpApiResponse = gson.fromJson(jsonReader, type);
                response.close();
                if (httpApiResponse.success){
                    return (T) httpApiResponse;
                }else{
                    throw new IllegalAccessException(httpApiResponse.message);

                }


            }
        }

    }
}
