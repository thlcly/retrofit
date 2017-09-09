package retrofit2.com.aaront.test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author tonyhui
 * @since 16/6/7
 */
public interface GitApi {
    @GET("/users/{user}")      //here is the other url part.best way is to start using /
    Call<GitModel> getFeed(@Path("user") String user);     //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}
