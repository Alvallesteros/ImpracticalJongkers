package app.components;

import app.components.dto.CustomerContactDto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface UserAppIF {

    @GET("/order/customerdetails")
    public Call<CustomerContactDto> getCustomerDetails(@Query("orderCode") String orderCode);

    @FormUrlEncoded
    @POST("/order/edit")
    public Call<ResponseBody> editOrderStatus(@Field("orderCode") String orderCode,
                                              @Field("status") String status);
}
