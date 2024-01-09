package com.hv.community.android.domain.repository

import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.domain.model.community.Post
import com.hv.community.android.domain.model.community.PostDetail
import com.hv.community.android.domain.model.community.Reply

interface CommunityRepository {
    suspend fun getCommunityList(): Result<List<Community>>

    suspend fun getPostList(
        communityId: Long
    ): Result<List<Post>>

    suspend fun getPostDetail(
        communityId: Long,
        postId: Long
    ): Result<PostDetail>

    suspend fun getReplyList(
        communityId: Long,
        postId: Long
    ): Result<List<Reply>>

    suspend fun createPost(
        communityId: Long,
        content: String,
        title: String,
        nickname: String,
        password: String,
    ): Result<Long>

    suspend fun checkPostPassword(
        communityId: Long,
        postId: Long,
        password: String
    ): Result<Unit>

    suspend fun updatePost(
        communityId: Long,
        postId: Long,
        title: String,
        content: String,
        password: String
    ): Result<Unit>

    suspend fun deletePost(
        communityId: Long,
        postId: Long,
        password: String
    ): Result<Unit>

    suspend fun createReply(
        communityId: Long,
        postId: Long,
        content: String,
        nickname: String,
        password: String
    ): Result<Long>

    suspend fun checkReplyPassword(
        communityId: Long,
        postId: Long,
        replyId: Long,
        password: String
    ): Result<Unit>

    suspend fun updateReply(
        communityId: Long,
        postId: Long,
        replyId: Long,
        content: String,
        password: String
    ): Result<Unit>

    suspend fun deleteReply(
        communityId: Long,
        postId: Long,
        replyId: Long,
        password: String
    ): Result<Unit>
}
