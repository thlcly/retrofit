package com.aaront.test;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * @author tonyhui
 * @since 17/6/7
 */
public interface PigeonApi {
    @Multipart
    @POST("/service/mail/send")
    Call<String> upload(@Part MultipartBody.Part file);

    @Multipart
    @POST("/service/mails/send")
    Call<String> upload(@Part List<MultipartBody.Part> files);

    @Multipart
    @POST("/service/mail/send")
    Call<String> upload(@Part List<MultipartBody.Part> attachments, @Part("cc") List<String> cc, @Part("to") List<String> to, @Part("subject") String subject, @Part("content") String content);
    @Multipart
    @POST("/service/mail/send")
    Call<String> upload(@Part List<MultipartBody.Part> attachments, @PartMap Map<String, RequestBody> map);

    @DELETE("/api/mr/template/{id}")
    Call<String> delete(@Path("id") Integer id, @QueryMap Map<String, Object> map);

    @POST("/service")
    Call<String> test(@Body Test test);
}
