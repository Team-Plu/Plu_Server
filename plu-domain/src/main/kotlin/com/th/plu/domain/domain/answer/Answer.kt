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


@Table(
    name = "answers",
    uniqueConstraints = [
        UniqueConstraint(name = "idk01_answers", columnNames = ["member_id", "question_id"])
    ]
)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
class Answer(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private var _id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "member_id", nullable = false)
    private var member: Member,

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "question_id", nullable = false)
    var question: Question,

    @OneToMany(mappedBy = "answer", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    private var likes: List<Like> = mutableListOf(),

    content: String,
    isPublic: Boolean,
) : BaseEntity() {
    val id: Long
        get() = _id ?: throw PersistenceException("아직 save 되지 않은 entity 의 id 에대한 접근입니다.")

    @Column(name = "answer_content", nullable = false)
    var content: String = content
        private set

    @Column(name = "is_public", nullable = false)
    var isPublic: Boolean = isPublic
        private set

    val questionId: Long = question.id
}

fun newAnswerInstance(member: Member, question: Question, content: String, isPublic: Boolean) = Answer(
    _id = null,
    member = member,
    question = question,
    content = content,
    isPublic = isPublic,
)