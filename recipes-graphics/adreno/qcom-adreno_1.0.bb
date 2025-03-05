inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap virtual/libgbm libdrm"

PROVIDES = "  virtual/libgles1 virtual/libgles2 virtual/egl"

QCM6490_SHA256SUM = "a7305f302046ed80e1e6220dab23789e3f07e075bf73842962ef8a892ef69427"
QCS9100_SHA256SUM = "e2ee6fe73a8cff546de1b8bf0fd3bfe4c7414317065a64316c4a671a7ad63a73"
QCS8300_SHA256SUM = "1c7569c48d98bd8e45024916d4d064f62da69030b590ea94e9bb94ccc2b72c21"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} = "${includedir}/* \
               ${nonarch_base_libdir}/firmware/* \
               ${nonarch_libdir}/* \
               ${base_libdir}/firmware/* \
               ${libdir}/* \
               ${bindir}/* \
               /usr/share/vulkan/icd.d/* "
FILES:${PN}-dev = ""
FILES:${PN}-dbg = ""


INSANE_SKIP:${PN} = "dev-deps file-rdeps dev-so arch already-stripped"

