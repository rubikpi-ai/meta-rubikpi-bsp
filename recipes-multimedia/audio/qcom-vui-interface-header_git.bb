inherit autotools pkgconfig

DESCRIPTION = "vui-interface header"

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM += "file://VoiceUIInterface.h;beginline=9;endline=11;md5=6e81219b9da09d37472d59042b06a623"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom/opensource/arpal-lx.git;protocol=https"
SRCBRANCH  = "audio-core.lnx.1.0.r1-rel"
SRCREV     = "136474d0fc15bdc8ce8c2c297d2ae2fef5bd91e2"

SRC_URI    =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/arpal-lx"

S = "${WORKDIR}/audio/opensource/arpal-lx/api/vui-interface"

do_compile[noexec] = "1"

ALLOW_EMPTY:${PN} = "1"
