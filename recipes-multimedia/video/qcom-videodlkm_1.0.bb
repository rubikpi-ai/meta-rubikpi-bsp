inherit module

DESCRIPTION = "QCOM Video drivers"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/${LICENSE};md5=801f80980d171dd6425610833a22dbe6"

SRCPROJECT = "git://git.codelinaro.org/clo/le/platform/vendor/opensource/video-driver.git;protocol=https"
SRCBRANCH  = "video.qclinux.1.0.r1-rel"
SRCREV     = "218c41e57ca49533c954bfcd38759203544067de"

SRC_URI =  "${SRCPROJECT};branch=${SRCBRANCH};destsuffix=vendor/qcom/opensource/video-driver"
SRC_URI += "file://0001-PENDING-video-driver-fix-life-cycle-of-a-buffer-shared-acros.patch"

S = "${WORKDIR}/vendor/qcom/opensource/video-driver"

MAKE_TARGETS = "modules"
MODULES_INSTALL_TARGET = "modules_install"

