package com.cd18.infra.persistence.model

import com.cd18.domain.metrics.enums.ActionType
import com.cd18.domain.metrics.enums.TargetType
import com.cd18.infra.persistence.config.model.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_action_log")
class UserActionLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    @Column(name = "user_id")
    var userId: Long? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type")
    var actionType: ActionType,
    @Column(name = "target_id")
    var targetId: Long? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "target_type")
    var targetType: TargetType,
    @Column(name = "action_detail")
    var actionDetail: String? = null,
) : BaseTimeEntity()
