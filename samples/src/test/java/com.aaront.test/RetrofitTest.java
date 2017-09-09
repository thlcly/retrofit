package com.aaront.test;

import com.google.common.base.Joiner;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tonyhui
 * @since 16/11/21
 */
public class RetrofitTest {
    private static String GITHUB_API = "https://api.github.com";
    private static String PHONE_API = "http://apis.baidu.com";
    private static String PHONE_API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";
    private static String pigeon = "http://localhost:3100";


    public static void main(String[] args) throws IOException {
        RetrofitTest retrofitTest = new RetrofitTest();
        retrofitTest.testForm();
        //retrofitTest.test();
    }

    public void testDelete() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(pigeon).addConverterFactory(GsonConverterFactory.create()).build();
        PigeonApi api = retrofit.create(PigeonApi.class);
        Map<String, Object> map = new HashMap<>();
        map.put("ownerId", 1);
        Response<String> execute = api.delete(1, map).execute();
        System.out.println(execute.isSuccessful());
    }

    /**
     * 测试github api
     */
    public void testGitApi() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 返回的是GitApi接口的代理对象
        GitApi git = retrofit.create(GitApi.class);
        // 调用接口中的方法会被代理拦截
        Response<GitModel> response = git.getFeed("basil2style").execute();
        if(response.isSuccessful()) {
            GitModel gitModel = response.body();
            System.out.println("Github Name :" + gitModel.getName());
            System.out.println("Company Name :" + gitModel.getCompany());
        }
    }

    public void testMultipartFile() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(pigeon).addConverterFactory(GsonConverterFactory.create()).build();
        PigeonApi api = retrofit.create(PigeonApi.class);
        File file = new File("/Users/tonyhui/Desktop/temp.txt");
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", "temp.txt", body);
        Response<String> response = api.upload(part).execute();
        System.out.println(response.isSuccessful());
    }

    public void testMultipartFiles() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(pigeon).addConverterFactory(GsonConverterFactory.create()).build();
        PigeonApi api = retrofit.create(PigeonApi.class);
        File tempfile = new File("/Users/tonyhui/Desktop/temp.txt");
        File imgfile = new File("/Users/tonyhui/Desktop/常量池的项目类型.png");
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), tempfile);
        RequestBody body2 = RequestBody.create(MediaType.parse("text/plain"), imgfile);
        MultipartBody.Part part = MultipartBody.Part.createFormData("files", "temp.txt", body);
        MultipartBody.Part part2 = MultipartBody.Part.createFormData("files", "常量池的项目类型.png", body);
        List<MultipartBody.Part> list = new ArrayList<>();
        list.add(part);
        list.add(part2);
        Response<String> response = api.upload(list).execute();
        System.out.println(response.isSuccessful());
    }

    public void testForm() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(pigeon).addConverterFactory(JacksonConverterFactory.create()).build();
        PigeonApi api = retrofit.create(PigeonApi.class);
        File tempfile = new File("/Users/tonyhui/Desktop/temp.txt");
        File imgfile = new File("/Users/tonyhui/Desktop/常量池的项目类型.png");
        File pdffile = new File("/Users/tonyhui/Desktop/spring-security.pdf");
        File excelfile = new File("/Users/tonyhui/Desktop/产品申请表.xlsx");
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), tempfile);
        RequestBody body2 = RequestBody.create(MediaType.parse("text/plain"), imgfile);
        RequestBody body3 = RequestBody.create(MediaType.parse("text/plain"), pdffile);
        RequestBody body4 = RequestBody.create(MediaType.parse("text/plain"), excelfile);
        MultipartBody.Part part = MultipartBody.Part.createFormData("attachments", "temp.txt", body);
        MultipartBody.Part part2 = MultipartBody.Part.createFormData("attachments", "常量池的项目类型.png", body2);
        MultipartBody.Part part3 = MultipartBody.Part.createFormData("attachments", "spring-security.pdf", body3);
        MultipartBody.Part part4 = MultipartBody.Part.createFormData("attachments", "产品申请表.xlsx", body4);
        List<MultipartBody.Part> list = new ArrayList<>();
        list.add(part);
        list.add(part2);
        list.add(part3);
        list.add(part4);
        List<String> cc = new ArrayList<>();
        cc.add("tonyhui0717@gmail.com");
        cc.add("954958168@qq.com");
        List<String> to = new ArrayList<>();
        to.add("thlcly@163.com");
        Map<String, RequestBody> map1 = new HashMap<>();
        map1.put("subject", RequestBody.create(MediaType.parse("text/plain"), "这是测试主题subject"));
        map1.put("content",RequestBody.create(MediaType.parse("text/plain"),  "这是测试内容content"));
        map1.put("cc", RequestBody.create(MediaType.parse("text/plain"), Joiner.on(",").join(cc)));
        map1.put("to", RequestBody.create(MediaType.parse("text/plain"), Joiner.on(",").join(to)));
        Response<String> response = api.upload(list, map1).execute();
        System.out.println(response.isSuccessful());
    }

    public void test() throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(pigeon).addConverterFactory(JacksonConverterFactory.create()).build();
        PigeonApi api = retrofit.create(PigeonApi.class);
        List<String> name = new ArrayList<>();
        name.add("a");
        Response<String> response = api.test(new Test(name)).execute();
        System.out.println(response.isSuccessful());
    }
}
