inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "QCOM library image blending, color convertion and composition."

DEPENDS += "virtual/kernel virtual/egl virtual/libgles2"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "47148048278e94bbd4e33496804336e3080071e58323a357cd59cc4c754080b7"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

PACKAGECONFIG = "${@bb.utils.contains('PREFERRED_PROVIDER_virtual/libgbm', 'msm', '', 'gbm', d)} "
PACKAGECONFIG[gbm] = " , ,gbm,gbm"

INSANE_SKIP:${PN} = "dev-so"
FILES:${PN} += "${bindir}"
FILES:${PN} += "${libdir}"

SOLIBS = ".so*"
FILES_SOLIBSDEV = ""
