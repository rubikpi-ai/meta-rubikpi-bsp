inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Qualcomm Atheros common tools"

DEPENDS += "diag libnl glib-2.0 cld80211-lib libxml2 icu"

PV = "1.0"

QCM6490_SHA256SUM = "e70a6256c61b5e935dbb63f19d33a39e02be9bef2b38ad58844e07e3d7c54d37"
QCS9100_SHA256SUM = "38e903ad99a58281abfe22eff9704d817da5cfde3d68e9a363941a24dc18ce3d"
QCS8300_SHA256SUM = "2013273b728a3824bd234b7078f6916784f1d5b8534fb3aa3a1325e11a7ffdd5"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN} += " \
	/usr/bin/* \
	/usr/sbin/* \
	/usr/lib/* \
	"

