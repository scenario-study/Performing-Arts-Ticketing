package com.hunhui.api.controller.exception

import com.hunhui.common.error.BusinessException

class SampleException : BusinessException(SampleErrorCode.SAMPLE_ERROR)
