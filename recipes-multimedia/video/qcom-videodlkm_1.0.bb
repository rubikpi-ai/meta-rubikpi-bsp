inherit module

DESCRIPTION = "QCOM Video drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/video-driver.git;protocol=https"
SRCBRANCH  = "video.qclinux.1.0.r1-rel"
SRCREV     = "19fdd38d51342cece2c5eb21b3e113a3a785d9da"

SRC_URI =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=vendor/qcom/opensource/video-driver"

S = "${WORKDIR}/vendor/qcom/opensource/video-driver"

MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"

