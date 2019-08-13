package com.trans.kuro_core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
*@author TRS透明
*Created on 2019/8/13
*function :
*/
public interface RestService {
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> params);

    @Streaming //防止文件过大内存泄露
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);

    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);

}
