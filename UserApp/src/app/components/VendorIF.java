package app.components;

import app.rest.controllers.ParamsDto;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface VendorIF {

    @GET("/cafeteria/all")
    public Call<List<CafeteriaDto>> viewCafeterias();

    @POST("/order/new")
    public Call<OrderReceivedDto> order(@Body ParamsDto pa);
}
