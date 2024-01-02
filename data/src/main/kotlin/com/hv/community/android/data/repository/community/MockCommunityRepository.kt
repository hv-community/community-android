package com.hv.community.android.data.repository.community

import com.hv.community.android.domain.model.community.Community
import com.hv.community.android.domain.model.community.Post
import com.hv.community.android.domain.model.community.PostDetail
import com.hv.community.android.domain.model.community.Reply
import com.hv.community.android.domain.repository.CommunityRepository
import kotlinx.coroutines.delay

class MockCommunityRepository : CommunityRepository {
    override suspend fun getCommunityList(): Result<List<Community>> {
        randomShortDelay()
        return Result.success(
            listOf(
                Community(
                    id = 1L,
                    title = "블루 아카이브 채널",
                    description = "블루 아카이브에 대한 이야기를 나누는 공간입니다.",
                    thumbnail = "https://play-lh.googleusercontent.com/xUQ8wVNuE-ZdubxWjs9K4MXTAFRDp1hpcpB8ozLUV5MK0HKzrWr12r4lJbLgiWDpDPo=w240-h480-rw"
                ),
                Community(
                    id = 2L,
                    title = "트릭컬 RE:VIVE 채널",
                    description = "트릭컬 RE:VIVE에 대한 이야기를 나누는 공간입니다.",
                    thumbnail = "https://cdn-www.bluestacks.com/bs-images/999ff719bc4ab24360e500d4564d5f2e.png"
                )
            )
        )
    }

    override suspend fun getPostList(
        communityId: Long
    ): Result<List<Post>> {
        randomShortDelay()
        return Result.success(
            listOf(
                Post(
                    id = 1L,
                    title = "공지사항",
                    member = "Admin",
                    nickname = "",
                    replyCount = 1
                ),
                Post(
                    id = 2L,
                    title = "서버 점검 안내",
                    member = "Admin",
                    nickname = "",
                    replyCount = 2
                ),
                Post(
                    id = 3L,
                    title = "정상 영업중",
                    member = "Admin",
                    nickname = "",
                    replyCount = 2
                ),
            )
        )
    }

    override suspend fun getPostDetail(
        postId: Long
    ): Result<PostDetail> {
        randomShortDelay()
        return when (postId) {
            1L -> Result.success(
                PostDetail(
                    id = 1L,
                    member = "Admin",
                    nickname = "",
                    replies = listOf(
                        Reply(
                            id = 1L,
                            member = "Admin",
                            nickname = "",
                            reply = "안녕하세요. 블루 아카이브 운영자입니다."
                        )
                    ),
                    replyCount = 1,
                    title = "공지사항"
                )
            )

            2L -> Result.success(
                PostDetail(
                    id = 2L,
                    member = "Admin",
                    nickname = "",
                    replies = listOf(
                        Reply(
                            id = 2L,
                            member = "Admin",
                            nickname = "",
                            reply = "안녕하세요. 블루 아카이브 운영자입니다."
                        ),
                        Reply(
                            id = 3L,
                            member = "Admin",
                            nickname = "",
                            reply = "안녕하세요. 블루 아카이브 운영자입니다."
                        )
                    ),
                    replyCount = 2,
                    title = "서버 점검 안내"
                )
            )

            else -> Result.success(
                PostDetail(
                    id = 3L,
                    member = "Admin",
                    nickname = "",
                    replies = listOf(
                        Reply(
                            id = 4L,
                            member = "Admin",
                            nickname = "",
                            reply = "안녕하세요. 블루 아카이브 운영자입니다."
                        ),
                        Reply(
                            id = 5L,
                            member = "Admin",
                            nickname = "",
                            reply = "안녕하세요. 블루 아카이브 운영자입니다."
                        )
                    ),
                    replyCount = 2,
                    title = "정상 영업중"
                )
            )
        }
    }

    override suspend fun createPost(
        communityId: Long,
        content: String,
        title: String,
        nickname: String,
        password: String
    ): Result<Long> {
        randomLongDelay()
        return Result.success(1L)
    }

    override suspend fun checkPostPassword(
        password: String,
        postId: Long
    ): Result<Unit> {
        randomShortDelay()
        return Result.success(Unit)
    }

    override suspend fun updatePost(
        content: String,
        password: String,
        postId: Long,
        title: String
    ): Result<Unit> {
        randomLongDelay()
        return Result.success(Unit)
    }

    override suspend fun deletePost(
        password: String,
        postId: Long
    ): Result<Unit> {
        randomLongDelay()
        return Result.success(Unit)
    }

    override suspend fun createReply(
        nickname: String,
        password: String,
        postId: Long,
        reply: String
    ): Result<Long> {
        randomShortDelay()
        return Result.success(1L)
    }

    override suspend fun checkReplyPassword(
        password: String,
        replyId: Long
    ): Result<Unit> {
        randomShortDelay()
        return Result.success(Unit)
    }

    override suspend fun updateReply(
        password: String,
        reply: String,
        replyId: Long
    ): Result<Unit> {
        randomShortDelay()
        return Result.success(Unit)
    }

    override suspend fun deleteReply(
        password: String,
        replyId: Long
    ): Result<Unit> {
        randomShortDelay()
        return Result.success(Unit)
    }

    private suspend fun randomShortDelay() {
        delay(LongRange(100, 5000).random())
    }

    private suspend fun randomLongDelay() {
        delay(LongRange(500, 2000).random())
    }
}
