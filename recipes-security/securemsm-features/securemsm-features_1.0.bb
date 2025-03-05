inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "securemsm-features with QseecomAPI user space library to interact with qseecom driver"

DEPENDS += "libxml2 libtinyxml2 linux-kernel-qcom-headers glib-2.0 glibc qcom-libdmabufheap securemsm-headers minkipc property-vault jsoncpp qmi-framework curl"

QCM6490_SHA256SUM = "91b2172248bc626a515c48e15fa67f4e827b863714bef93a5100731edb2f1286"
QCS9100_SHA256SUM = "95329f4b89b20996d55bd0b034481bcd850ef393cca8f8b8fe9aa547c32f7a7f"
QCS8300_SHA256SUM = "8776c17708082cc1f3ef46a6223477f850f8c933360b3c741ac5acbce005a725"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += "/usr/bin/*"
FILES:${PN} += "/usr/bin/"
FILES:${PN} += "${bindir}/*"
FILES:${PN} += "${libdir} ${includedir}"
FILES:${PN}-dev = "${libdir}/*.la"


INSANE_SKIP:${PN} = "dev-so"
INSANE_SKIP:${PN} += "dev-deps"
INSANE_SKIP:${PN} += "debug-files"
INSANE_SKIP:${PN} += "file-rdeps"

