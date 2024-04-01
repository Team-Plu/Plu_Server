package com.th.plu.domain.domain.answer

import com.th.plu.domain.domain.common.BaseEntity
import com.th.plu.domain.domain.like.Like
import com.th.plu.domain.domain.member.Member
import com.th.plu.domain.domain.question.Question
import jakarta.persistence.*
import lombok.AccessLevel
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor


@Table(name = "answers")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
class Answer(

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "answer_id")
        var id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "member_id", nullable = false)
        var member: Member,

        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "question_id", nullable = false)
        var question: Question,

        @Column(name = "answer_content", nullable = false)
        var content: String,

        @Column(name = "is_public", nullable = false)
        var isPublic: Boolean,

        @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
        var likes: List<Like> = mutableListOf()

) : BaseEntity() {

    fun getQuestionId(): Long {
        return question.id!!
    }

    fun getLikeCount(): Int {
        return likes.size
    }
}
