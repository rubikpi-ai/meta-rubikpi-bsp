inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Recipe to install video firmware files on rootfs"

PBT_ARCH = "armv8-2a"

ARMV8_SHA256SUM = "97d0d065062959bb5afa9d300bc6b56def11b11d906a0d12588133e28f276851"
SRC_URI[armv8-2a.sha256sum] = "${ARMV8_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "${nonarch_base_libdir}/firmware"


INSANE_SKIP:${PN} = "arch"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
