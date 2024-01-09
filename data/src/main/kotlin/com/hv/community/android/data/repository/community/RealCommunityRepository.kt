package com.hv.community.android.data.repository.community

import com.hv.community.android.data.remote.local.ErrorMessageMapper
import com.hv.community.android.data.remote.network.api.CommunityApi
import com.hv.community.android.data.remote.network.model.community.CreatePostReq
import com.hv.community.android.data.remote.network.model.community.CreateReplyReq
import com.hv.community.android.data.remote.network.model.community.UpdatePostReq
import com.hv.community.android.data.remote.network.model.community.UpdateReplyReq
import com.hv.community.android.data.remote.network.util.convertResponse
import com.hv.community.android.data.remote.network.util.convertResponseToDomain
import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.domain.model.community.Post
import com.hv.community.android.domain.model.community.PostDetail
import com.hv.community.android.domain.model.community.Reply
import com.hv.community.android.domain.repository.CommunityRepository

class RealCommunityRepository(
    private val communityApi: CommunityApi,
    private val errorMessageMapper: ErrorMessageMapper
) : CommunityRepository {
    override suspend fun getCommunityList(): Result<List<Community>> {
        return communityApi.getCommunityList()
            .convertResponse(errorMessageMapper::map)
            .map {
                it.items.map { community ->
                    community.toDomain()
                }
            }
    }

    override suspend fun getPostList(
        communityId: Long
    ): Result<List<Post>> {
        return communityApi.getPostList(communityId)
            .convertResponse(errorMessageMapper::map)
            .map {
                it.items.map { post ->
                    post.toDomain()
                }
            }
    }

    override suspend fun getPostDetail(
        communityId: Long,
        postId: Long
    ): Result<PostDetail> {
        return communityApi.getPostDetail(
            communityId = communityId,
            postId = postId
        ).convertResponseToDomain(errorMessageMapper::map)
    }

    override suspend fun getReplyList(
        communityId: Long,
        postId: Long
    ): Result<List<Reply>> {
        return communityApi.getReplyList(
            communityId = communityId,
            postId = postId
        ).convertResponse(errorMessageMapper::map).map {
            it.items.map { post ->
                post.toDomain()
            }
        }
    }

    override suspend fun createPost(
        communityId: Long,
        content: String,
        title: String,
        nickname: String,
        password: String
    ): Result<Long> {
        return communityApi.createPost(
            communityId = communityId,
            CreatePostReq(
                content = content,
                title = title,
                nickname = nickname,
                password = password
            )
        ).convertResponse(errorMessageMapper::map).map { it.id }
    }

    override suspend fun checkPostPassword(
        communityId: Long,
        postId: Long,
        password: String
    ): Result<Unit> {
        return communityApi.checkPostPassword(
            communityId = communityId,
            postId = postId,
            password = password
        ).convertResponse(errorMessageMapper::map)
    }

    override suspend fun updatePost(
        communityId: Long,
        postId: Long,
        title: String,
        content: String,
        password: String
    ): Result<Unit> {
        return communityApi.updatePost(
            communityId = communityId,
            postId = postId,
            UpdatePostReq(
                title = title,
                content = content,
                password = password
            )
        ).convertResponse(errorMessageMapper::map)
    }

    override suspend fun deletePost(
        communityId: Long,
        postId: Long,
        password: String
    ): Result<Unit> {
        return communityApi.deletePost(
            communityId = communityId,
            postId = postId,
            password = password
        ).convertResponse(errorMessageMapper::map)
    }

    override suspend fun createReply(
        communityId: Long,
        postId: Long,
        content: String,
        nickname: String,
        password: String
    ): Result<Long> {
        return communityApi.createReply(
            communityId = communityId,
            postId = postId,
            CreateReplyReq(
                content = content,
                nickname = nickname,
                password = password
            )
        ).convertResponse(errorMessageMapper::map).map { it.id }
    }

    override suspend fun checkReplyPassword(
        communityId: Long,
        postId: Long,
        replyId: Long,
        password: String
    ): Result<Unit> {
        return communityApi.checkReplyPassword(
            communityId = communityId,
            postId = postId,
            replyId = replyId,
            password = password
        ).convertResponse(errorMessageMapper::map)
    }

    override suspend fun updateReply(
        communityId: Long,
        postId: Long,
        replyId: Long,
        content: String,
        password: String
    ): Result<Unit> {
        return communityApi.updateReply(
            communityId = communityId,
            postId = postId,
            replyId = replyId,
            UpdateReplyReq(
                content = content,
                password = password
            )
        ).convertResponse(errorMessageMapper::map)
    }

    override suspend fun deleteReply(
        communityId: Long,
        postId: Long,
        replyId: Long,
        password: String
    ): Result<Unit> {
        return communityApi.deleteReply(
            communityId = communityId,
            postId = postId,
            replyId = replyId,
            password = password
        ).convertResponse(errorMessageMapper::map)
    }
}
