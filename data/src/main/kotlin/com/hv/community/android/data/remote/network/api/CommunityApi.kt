package com.hv.community.android.data.remote.network.api

import com.hv.community.android.data.remote.network.model.community.CreatePostReq
import com.hv.community.android.data.remote.network.model.community.CreatePostRes
import com.hv.community.android.data.remote.network.model.community.CreateReplyReq
import com.hv.community.android.data.remote.network.model.community.CreateReplyRes
import com.hv.community.android.data.remote.network.model.community.GetCommunityListRes
import com.hv.community.android.data.remote.network.model.community.GetPostDetailRes
import com.hv.community.android.data.remote.network.model.community.GetPostListRes
import com.hv.community.android.data.remote.network.model.community.GetReplyListRes
import com.hv.community.android.data.remote.network.model.community.UpdatePostReq
import com.hv.community.android.data.remote.network.model.community.UpdateReplyReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CommunityApi {
    @GET("/community/v1/list")
    suspend fun getCommunityList(): Response<GetCommunityListRes>

    @GET("/community/v1/{community_id}")
    suspend fun getPostList(
        @Path("community_id") communityId: Long,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = Int.MAX_VALUE
    ): Response<GetPostListRes>

    @GET("/community/v1/{community_id}/{post_id}")
    suspend fun getPostDetail(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long
    ): Response<GetPostDetailRes>

    @GET("/community/v1/{community_id}/{post_id}/reply")
    suspend fun getReplyList(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long,
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = Int.MAX_VALUE
    ): Response<GetReplyListRes>

    @POST("/community/v1/{community_id}/create")
    suspend fun createPost(
        @Path("community_id") communityId: Long,
        @Body body: CreatePostReq
    ): Response<CreatePostRes>

    @GET("/community/v1/{community_id}/{post_id}/check")
    suspend fun checkPostPassword(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long,
        @Query("password") password: String
    ): Response<Unit>

    @POST("/community/v1/{community_id}/{post_id}/update")
    suspend fun updatePost(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long,
        @Body body: UpdatePostReq
    ): Response<Unit>

    @DELETE("/community/v1/{community_id}/{post_id}/delete")
    suspend fun deletePost(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long,
        @Query("password") password: String
    ): Response<Unit>

    @POST("/community/v1/{community_id}/{post_id}/create")
    suspend fun createReply(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long,
        @Body body: CreateReplyReq
    ): Response<CreateReplyRes>

    @POST("/community/v1/{community_id}/{post_id}/{reply_id}/check")
    suspend fun checkReplyPassword(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long,
        @Path("reply_id") replyId: Long,
        @Query("password") password: String
    ): Response<Unit>

    @POST("/community/v1/{community_id}/{post_id}/{reply_id}/update")
    suspend fun updateReply(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long,
        @Path("reply_id") replyId: Long,
        @Body body: UpdateReplyReq
    ): Response<Unit>

    @DELETE("/community/v1/{community_id}/{post_id}/{reply_id}/delete")
    suspend fun deleteReply(
        @Path("community_id") communityId: Long,
        @Path("post_id") postId: Long,
        @Path("reply_id") replyId: Long,
        @Query("password") password: String
    ): Response<Unit>
}
