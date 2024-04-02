package com.th.plu.domain.domain.question

import com.th.plu.domain.domain.answer.Answer
import com.th.plu.domain.domain.common.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime


@Table(name = "questions")
@Entity
class Question(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    var _id: Long? = null,

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var answers: List<Answer>,

    elementType: ElementType,
    questionDate: LocalDateTime,
    title: String,
    content: String,
    exposedAt: LocalDateTime,
) : BaseEntity() {
    val id: Long
        get() = _id ?: throw PersistenceException("아직 save 되지 않은 entity 의 id 에대한 접근입니다.")

    @Column(name = "element_type", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    var elementType: ElementType = elementType
        private set

    @Column(name = "question_date", nullable = false, length = 30)
    var questionDate: LocalDateTime = questionDate
        private set

    @Column(name = "question_title", nullable = false, length = 100)
    var title: String = title
        private set

    @Column(name = "question_content", nullable = false, length = 300)
    var content: String = content
        private set

    @Column(name = "exposed_at", nullable = false, unique = true)
    var exposedAt: LocalDateTime = exposedAt
        private set
}
