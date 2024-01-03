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
                    replyCount = 0
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
                    replyCount = 13
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
                            member = "",
                            nickname = "ㅇㅇ",
                            reply = "이미 하고 계신 일이 산더미인데도 불구하고 유저들을 위해 움직이시는 제작진분들께 감사함을 느낍니다.\n\n겨울철 몸 조심하시고 이미 찾아온 남반구 키보토스의 여름, 기대하고 있겠습니다. "
                        ),
                        Reply(
                            id = 2L,
                            member = "철새",
                            nickname = "",
                            reply = "유저와 소통 항상 감사드립니다 용하PD님 앞으로 계속 화이팅입니다!!"
                        ),
                        Reply(
                            id = 3L,
                            member = "황새",
                            nickname = "",
                            reply = "블아... 사랑합니다..."
                        )
                    ),
                    replyCount = 3,
                    title = "공지사항",
                    content = "안녕하세요.\n\n블루 아카이브 X PEER 팝업 공식 계정입니다.\n\n금일 팝업 스토어 첫 날을 운영하며, 방문해주신 선생님 그리고 방문 예정이신 선생님께 많은 심려를 끼쳐 드려 정말 죄송합니다. 선생님들께서 전달 주신 우려사항, 그리고 제보에 대해 팝업 운영사와 제품 제조사를 통해 빠르게 확인된 이슈에 대해 설명 드리겠습니다. 관련하여 안내가 매우 늦게 이뤄진 점 대단히 송구합니다.\n"
                )
            )

            2L -> Result.success(
                PostDetail(
                    id = 2L,
                    member = "Admin",
                    nickname = "",
                    replies = listOf(
                        Reply(
                            id = 4L,
                            member = "",
                            nickname = "ㅇㅇ",
                            reply = "이미 하고 계신 일이 산더미인데도 불구하고 유저들을 위해 움직이시는 제작진분들께 감사함을 느낍니다.\n\n겨울철 몸 조심하시고 이미 찾아온 남반구 키보토스의 여름, 기대하고 있겠습니다. "
                        ),
                        Reply(
                            id = 5L,
                            member = "철새",
                            nickname = "",
                            reply = "유저와 소통 항상 감사드립니다 용하PD님 앞으로 계속 화이팅입니다!!"
                        ),
                        Reply(
                            id = 6L,
                            member = "황새",
                            nickname = "",
                            reply = "블아... 사랑합니다..."
                        )
                    ),
                    replyCount = 3,
                    title = "서버 점검 안내",
                    content = "안녕하세요.\n\n블루 아카이브 X PEER 팝업 공식 계정입니다.\n\n금일 팝업 스토어 첫 날을 운영하며, 방문해주신 선생님 그리고 방문 예정이신 선생님께 많은 심려를 끼쳐 드려 정말 죄송합니다. 선생님들께서 전달 주신 우려사항, 그리고 제보에 대해 팝업 운영사와 제품 제조사를 통해 빠르게 확인된 이슈에 대해 설명 드리겠습니다. 관련하여 안내가 매우 늦게 이뤄진 점 대단히 송구합니다.\n"
                )
            )

            else -> Result.success(
                PostDetail(
                    id = 3L,
                    member = "",
                    nickname = "ㅇㅇ",
                    replies = listOf(
                        Reply(
                            id = 7L,
                            member = "",
                            nickname = "ㅇㅇ",
                            reply = "대체 뭔 버그냐 이건ㅋㅋㅋ"
                        ),
                        Reply(
                            id = 8L,
                            member = "철새",
                            nickname = "",
                            reply = "망겜 수준"
                        ),
                        Reply(
                            id = 9L,
                            member = "황새",
                            nickname = "",
                            reply = "뭐야 평소의 블아잖아"
                        )
                    ),
                    replyCount = 3,
                    title = "이겜 망했냐?",
                    content = "제곧내"
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
        delay(LongRange(100, 500).random())
    }

    private suspend fun randomLongDelay() {
        delay(LongRange(500, 2000).random())
    }
}
