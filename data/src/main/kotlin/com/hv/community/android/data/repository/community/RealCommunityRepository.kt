package com.hv.community.android.data.repository.community

import com.hv.community.android.data.remote.network.api.CommunityApi
import com.hv.community.android.data.remote.network.model.community.CheckPostPasswordReq
import com.hv.community.android.data.remote.network.model.community.CheckReplyPasswordReq
import com.hv.community.android.data.remote.network.model.community.CreatePostReq
import com.hv.community.android.data.remote.network.model.community.CreateReplyReq
import com.hv.community.android.data.remote.network.model.community.DeletePostReq
import com.hv.community.android.data.remote.network.model.community.DeleteReplyReq
import com.hv.community.android.data.remote.network.model.community.UpdatePostReq
import com.hv.community.android.data.remote.network.model.community.UpdateReplyReq
import com.hv.community.android.data.remote.network.util.convertResponse
import com.hv.community.android.data.remote.network.util.convertResponseToDomain
import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.domain.model.community.Post
import com.hv.community.android.domain.model.community.PostDetail
import com.hv.community.android.domain.repository.CommunityRepository

class RealCommunityRepository(
    private val communityApi: CommunityApi
) : CommunityRepository {
    override suspend fun getCommunityList(): Result<List<Community>> {
        return communityApi.getCommunityList()
            .convertResponse()
            .map {
                it.communities.map { community ->
                    community.toDomain()
                }
            }
    }

    override suspend fun getPostList(
        communityId: Long
    ): Result<List<Post>> {
        return communityApi.getPostList(communityId)
            .convertResponse()
            .map {
                it.posts.map { post ->
                    post.toDomain()
                }
            }
    }

    override suspend fun getPostDetail(
        postId: Long
    ): Result<PostDetail> {
        return communityApi.getPostDetail(postId)
            .convertResponseToDomain()
    }

    override suspend fun createPost(
        communityId: Long,
        content: String,
        title: String,
        nickname: String,
        password: String
    ): Result<Unit> {
        return communityApi.createPost(
            CreatePostReq(
                communityId = communityId,
                content = content,
                title = title,
                nickname = nickname,
                password = password
            )
        ).convertResponse()
    }

    override suspend fun checkPostPassword(
        password: String,
        postId: Long
    ): Result<Unit> {
        return communityApi.checkPostPassword(
            CheckPostPasswordReq(
                password = password,
                postId = postId
            )
        ).convertResponse()
    }

    override suspend fun updatePost(
        content: String,
        password: String,
        postId: Long,
        title: String
    ): Result<Unit> {
        return communityApi.updatePost(
            UpdatePostReq(
                content = content,
                password = password,
                postId = postId,
                title = title
            )
        ).convertResponse()
    }

    override suspend fun deletePost(
        password: String,
        postId: Long
    ): Result<Unit> {
        return communityApi.deletePost(
            DeletePostReq(
                password = password,
                postId = postId
            )
        ).convertResponse()
    }

    override suspend fun createReply(
        nickname: String,
        password: String,
        postId: Long,
        reply: String
    ): Result<Unit> {
        return communityApi.createReply(
            CreateReplyReq(
                nickname = nickname,
                password = password,
                postId = postId,
                reply = reply
            )
        ).convertResponse()
    }

    override suspend fun checkReplyPassword(
        password: String,
        replyId: Long
    ): Result<Unit> {
        return communityApi.checkReplyPassword(
            CheckReplyPasswordReq(
                password = password,
                replyId = replyId
            )
        ).convertResponse()
    }

    override suspend fun updateReply(
        password: String,
        reply: String,
        replyId: Long
    ): Result<Unit> {
        return communityApi.updateReply(
            UpdateReplyReq(
                password = password,
                reply = reply,
                replyId = replyId
            )
        ).convertResponse()
    }

    override suspend fun deleteReply(
        password: String,
        replyId: Long
    ): Result<Unit> {
        return communityApi.deleteReply(
            DeleteReplyReq(
                password = password,
                replyId = replyId
            )
        ).convertResponse()
    }
}
