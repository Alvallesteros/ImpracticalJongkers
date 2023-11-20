package app.components;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface OrderAppIF {

    @POST("/message/send")
    @FormUrlEncoded
    public Call<String> sendMessage(@Field("contactNo") String contactNo,
                                    @Field("message") String message);


}
