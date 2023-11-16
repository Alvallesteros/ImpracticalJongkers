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

    @FormUrlEncoded
    @POST("/order/add")
    public Call<OrderReceivedDto> addToOrder(@Field("orderCode") String orderCode,
                                             @Field("itemId") long itemId,
                                             @Field("qty") int qty);
    
    @FormUrlEncoded
    @POST("/vendor/")
    public Call<List<VendorDto>> viewVendors(@Field("name") String name);
    
    @POST("/item/")
    @FormUrlEncoded
    public Call<List<ItemDto>> viewItems(@Field("vendorId") Long vendorId);
}
