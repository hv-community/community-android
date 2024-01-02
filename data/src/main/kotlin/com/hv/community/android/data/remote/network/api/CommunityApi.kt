package com.hv.community.android.data.remote.network.api

import com.hv.community.android.data.remote.network.model.community.CheckPostPasswordReq
import com.hv.community.android.data.remote.network.model.community.CheckReplyPasswordReq
import com.hv.community.android.data.remote.network.model.community.CreatePostReq
import com.hv.community.android.data.remote.network.model.community.CreatePostRes
import com.hv.community.android.data.remote.network.model.community.CreateReplyReq
import com.hv.community.android.data.remote.network.model.community.CreateReplyRes
import com.hv.community.android.data.remote.network.model.community.DeletePostReq
import com.hv.community.android.data.remote.network.model.community.DeleteReplyReq
import com.hv.community.android.data.remote.network.model.community.GetCommunityListRes
import com.hv.community.android.data.remote.network.model.community.GetPostDetailRes
import com.hv.community.android.data.remote.network.model.community.GetPostListRes
import com.hv.community.android.data.remote.network.model.community.UpdatePostReq
import com.hv.community.android.data.remote.network.model.community.UpdateReplyReq
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CommunityApi {
    @GET("/community/get-community-list")
    suspend fun getCommunityList(): Response<GetCommunityListRes>

    @GET("/community/get-post-list/{community_id}")
    suspend fun getPostList(
        @Path("community_id") communityId: Long
    ): Response<GetPostListRes>

    @GET("/community/get-post-detail/{post-id}")
    suspend fun getPostDetail(
        @Path("post_id") postId: Long
    ): Response<GetPostDetailRes>

    @POST("/community/create-post")
    suspend fun createPost(
        @Body body: CreatePostReq,
    ): Response<CreatePostRes>

    @POST("/community/check-post-password")
    suspend fun checkPostPassword(
        @Body body: CheckPostPasswordReq,
    ): Response<Unit>

    @POST("/community/update-post")
    suspend fun updatePost(
        @Body body: UpdatePostReq,
    ): Response<Unit>

    @DELETE("/community/delete-post")
    suspend fun deletePost(
        @Body body: DeletePostReq,
    ): Response<Unit>

    @POST("/community/create-reply")
    suspend fun createReply(
        @Body body: CreateReplyReq,
    ): Response<CreateReplyRes>

    @POST("/community/check-reply-password")
    suspend fun checkReplyPassword(
        @Body body: CheckReplyPasswordReq,
    ): Response<Unit>

    @POST("/community/update-reply")
    suspend fun updateReply(
        @Body body: UpdateReplyReq,
    ): Response<Unit>

    @DELETE("/community/delete-reply")
    suspend fun deleteReply(
        @Body body: DeleteReplyReq,
    ): Response<Unit>
}
