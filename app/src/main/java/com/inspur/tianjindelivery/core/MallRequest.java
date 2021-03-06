package com.inspur.tianjindelivery.core;


import com.inspur.tianjindelivery.entiy.GroupListMainEntity;
import com.inspur.tianjindelivery.entiy.GroupListSecondEntity;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


/**
 * Created by Administrator on 2016/11/29.
 */
public interface MallRequest {

//    @FormUrlEncoded
//    @POST(AllUrl.mLoginUrl)
//    Observable<UserEntity> login(@Field("mobile") String userName, @Field("password") String pwd);

//    @FormUrlEncoded
//    @POST(AllUrl.mGetOrderList)
//    Observable<OrderListEntity> getOrderList(@Field("uid") String userName
//            , @Field("type") String type
//            , @Field("page") int page
//            , @Field("size") int size);
//
//
//    @FormUrlEncoded
//    @POST(AllUrl.mGetAdressList)
//    Observable<AdressEntity> getAdressList(@Field("id") String id);
//
//
//    @FormUrlEncoded
//    @POST(AllUrl.mGetTellList)
//    Observable<TellEntity> getTellList(@Field("page") String page
//            , @Field("size") String size
//            , @Field("uid") String uid
//            , @Field("type") String type
//            , @Field("truename") String truename);
//
//    @FormUrlEncoded
//    @POST(AllUrl.addOrder)
//    Observable<SaveOrderEntity> saveOrder(@Field("uid") String uid
//            , @Field("shipuser_address_id") String shipuser_address_id
//            , @Field("getuser_address_id") String getuser_address_id
//            , @Field("shipuser_truename") String shipuser_truename
//            , @Field("shipuser_mobile") String shipuser_mobile
//            , @Field("shipuser_address") String shipuser_address
//            , @Field("getuser_truename") String getuser_truename
//            , @Field("getuser_mobile") String getuser_mobile
//            , @Field("getuser_address") String getuser_address
//            , @Field("order_price") String order_price
//            , @Field("order_weight") String order_weight
//            , @Field("express_no") String manual
//            , @Field("shipuser_img") String shipuser_img
//            , @Field("getuser_img") String getuser_img
//    );
//
//    @FormUrlEncoded
//    @POST(AllUrl.getShowImgList)
//    Observable<ShowImgEntity> getShowImgList(@Field("id") String id);
//
//
//
//    @FormUrlEncoded
//    @POST(AllUrl.rijie)
//    Observable<SaveOrderEntity> rijie(@Field("uid") String uid);
//
//    /**
//     * 修改打状态
//     * @param uid
//     * @return
//     */
//    @FormUrlEncoded
//    @POST(AllUrl.changePrintStatus)
//    Observable<SaveOrderEntity> changePrintStatus(@Field("uid") String uid, @Field("order_no") String order_no);
//
//
//
//    //添加通讯录
//    //      uid
//    //    type   （shipuser|getuser）  寄件人|收件人
//    //    truename  真实姓名
//    //    mobile   手机号
//    //    address_id   获取三级联动的地址
//    @FormUrlEncoded
//    @POST(AllUrl.addTellList)
//    Observable<SaveOrderEntity> addTell(@Field("uid") String id
//            , @Field("type") String type
//            , @Field("truename") String truename
//            , @Field("mobile") String mobile
//            , @Field("address_id") String address_id
//            , @Field("address") String address
//            , @Field("base64") String base64
//    );
//
//
//    //    修改通讯录
//    //id  通讯录ID
//    //    truename  真实姓名
//    //    mobile   手机号
//    //    address_id  三级联动地址id 一户给你发获取地址的接口
//    //    address   详细地址
//    @FormUrlEncoded
//    @POST(AllUrl.mChangeTell)
//    Observable<SaveOrderEntity> changeTell(@Field("id") String id
//            , @Field("truename") String truename
//            , @Field("mobile") String mobile
//            , @Field("address_id") String address_id
//            , @Field("address") String address
//    );
//
//
//
//    @FormUrlEncoded
//    @POST(AllUrl.removeTell)
//    Observable<SaveOrderEntity> removeTell(@Field("id") String id);



    /**
     * 上传一张图片
     * @return
     */
    /*@Multipart
    @POST(AllUrl.uploadImageTrue)
    Call<StringBean> uploadImageTrue(@PartMap Map<String, RequestBody> params);



    @FormUrlEncoded
    @POST(AllUrl.uploadImage)
    Call<UpLoadResult> uploadImage(
            @Field("id") String id
            , @Field("photoString") String photoString);*/



    /*@Multipart
    @POST()
    Call<ResponseBody> uploadFiles(@Url String url, @PartMap() Map<String, RequestBody> maps);


    @Multipart
    @POST
    Call<ResponseBody> uploadFileWithPartMap(
            @Url() String url,
            @PartMap() Map<String, RequestBody> partMap,
            @Part("file") MultipartBody.Part file);



    @Multipart
    @POST(AllUrl.uploadImage)
    Call<String> updateImage(@Part MultipartBody.Part[] file, @QueryMap Map<String, String> maps);


    @Streaming //大文件时要加不然会OOM
    @FormUrlEncoded
    @POST
    Call<ResponseBody> downloadFile(@Url String fileUrl, @Field("jsonRequest") String jsonRequest);*/
    //站址的列表
    @FormUrlEncoded
    @POST(AllUrl.querySiteList)
    Observable<GroupListMainEntity> querySiteList(@Field("start") String start, @Field("length") String length, @Field("resName") String resName);

    //站址的二级列表
    @FormUrlEncoded
    @POST(AllUrl.queryBasicSiteInfo)
    Observable<GroupListSecondEntity> queryBasicSiteInfo(@Field("UID") String UID);

}
