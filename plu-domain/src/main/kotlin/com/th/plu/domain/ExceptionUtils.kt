package com.th.plu.domain

import org.hibernate.exception.ConstraintViolationException
import org.springframework.dao.DataIntegrityViolationException
import java.sql.SQLIntegrityConstraintViolationException

fun DataIntegrityViolationException.isUniqueError() =
    ((cause as? ConstraintViolationException)?.sqlException is SQLIntegrityConstraintViolationException)