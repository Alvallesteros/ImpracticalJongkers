package app.components;

import retrofit2.Call;
import retrofit2.http.*;


public interface UserAppIF {

    @GET("/order/customerdetails")
    public Call<CustomerContactDto> getCustomerDetails(@Query("orderCode") String orderCode);


}
