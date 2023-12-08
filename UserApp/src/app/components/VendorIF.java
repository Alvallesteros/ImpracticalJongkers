package app.components;

import app.components.dto.CafeteriaDto;
import app.components.dto.ItemDto;
import app.components.dto.OrderReceivedDto;
import app.components.dto.VendorDto;
import app.components.dto.ParamsDto;
import retrofit2.Call;
import retrofit2.http.*;

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
    
    @GET("/vendor")
    public Call<List<VendorDto>> viewVendors();

    @GET("/order/view")
    public Call<OrderReceivedDto> viewOrderDetails(@Query("orderCode") String orderCode);
    
    @GET("/vendor/{vendorId}")
    public Call<List<ItemDto>> viewItems(@Path("vendorId") Long vendorId);
}
