package com.hv.community.android.domain.repository

import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.domain.model.community.Post
import com.hv.community.android.domain.model.community.PostDetail

interface CommunityRepository {
    suspend fun getCommunityList(): Result<List<Community>>

    suspend fun getPostList(
        communityId: Long
    ): Result<List<Post>>

    suspend fun getPostDetail(
        postId: Long
    ): Result<PostDetail>

    suspend fun createPost(
        communityId: Long,
        content: String,
        title: String,
        nickname: String,
        password: String,
    ): Result<Long>

    suspend fun checkPostPassword(
        password: String,
        postId: Long
    ): Result<Unit>

    suspend fun updatePost(
        content: String,
        password: String,
        postId: Long,
        title: String
    ): Result<Unit>

    suspend fun deletePost(
        password: String,
        postId: Long
    ): Result<Unit>

    suspend fun createReply(
        nickname: String,
        password: String,
        postId: Long,
        reply: String
    ): Result<Long>

    suspend fun checkReplyPassword(
        password: String,
        replyId: Long
    ): Result<Unit>

    suspend fun updateReply(
        password: String,
        reply: String,
        replyId: Long
    ): Result<Unit>

    suspend fun deleteReply(
        password: String,
        replyId: Long
    ): Result<Unit>
}
