inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Adreno Graphics"

DEPENDS += "wayland glib-2.0 linux-kernel-qcom-headers property-vault qcom-libdmabufheap virtual/libgbm libdrm"

PROVIDES = "  virtual/libgles1 virtual/libgles2 virtual/egl"

QCM6490_SHA256SUM = "0ffd748398cf05256fd2fbb3f6bf66c29bcfc9bc847566d84b7bd9e1941e78d2"
QCS9100_SHA256SUM = "9abc0c4833b5efb12937f8d4a18bf58d84eaea054604198f6ef463bdfcba7d12"
QCS8300_SHA256SUM = "8d40f951b448dbe8078f6611e7a551e34bf595069b1341f68261ae5e9cb9725f"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

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

