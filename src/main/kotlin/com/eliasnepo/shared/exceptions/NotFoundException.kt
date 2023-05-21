package com.eliasnepo.shared.exceptions

import java.lang.RuntimeException

data class NotFoundException(val msg: String) : RuntimeException(msg)
