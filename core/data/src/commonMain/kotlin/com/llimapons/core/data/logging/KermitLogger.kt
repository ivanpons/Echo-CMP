package com.llimapons.core.data.logging

import co.touchlab.kermit.Logger
import com.llimapons.core.domain.logging.EchoLogger

object KermitLogger: EchoLogger {

    override fun debug(message: String) {
        Logger.d(message)
    }

    override fun info(message: String) {
        Logger.i(message)
    }

    override fun warn(message: String) {
        Logger.w(message)
    }

    override fun error(message: String, throwable: Throwable?) {
        Logger.e(message, throwable)
    }
}