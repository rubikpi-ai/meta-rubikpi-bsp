inherit autotools pkgconfig

LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=3771d4920bd6cdb8cbdf1e8344489ee0"

DESCRIPTION = "mercury plugin"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/qcom-opensource/audio-utils.git;protocol=https"
SRCBRANCH  = "audio-utils.lnx.1.0.r1-rel"
SRCREV     = "c1e7b4e6c07d9ad26b38467a1adf56632a7380eb"

SRC_URI = "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=audio/opensource/audio-utils"

S = "${WORKDIR}/audio/opensource/audio-utils/audio-plugins/mercury_plugin"

DEPENDS = "qcom-audio-plugin-headers"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"
