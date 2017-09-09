package retrofit2.com.aaront.test;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

/**
 * @author tonyhui
 * @since 16/11/21
 */
public class RetrofitTest {
    private static String GITHUB_API = "https://api.github.com";
    private static String PHONE_API = "http://apis.baidu.com";
    private static String PHONE_API_KEY = "8e13586b86e4b7f3758ba3bd6c9c9135";


    public static void main(String[] args) throws IOException {
        RetrofitTest retrofitTest = new RetrofitTest();
        retrofitTest.testGitApi();
    }

    /**
     * 测试github api
     */
    public void testGitApi() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitApi git = retrofit.create(GitApi.class);
        Response<GitModel> response = git.getFeed("basil2style").execute();
        if(response.isSuccessful()) {
            GitModel gitModel = response.body();
            System.out.println("Github Name :" + gitModel.getName());
            System.out.println("Company Name :" + gitModel.getCompany());
        }
    }
}
