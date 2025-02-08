inherit qprebuilt pkgconfig

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Generates libfastcvopt"

DEPENDS += "qcom-libdmabufheap syslog-plumber property-vault glib-2.0 fastrpc"

RDEPENDS:${PN} += "${PN}-cpu ${PN}-dsp"


QCM6490_SHA256SUM = "e7e685e0408c591638d21b658a20b2680ad511fbeee507cc6483096a3d68e081"
QCS9100_SHA256SUM = "9cec92be81b96e7afe4f05ca27937b3d670b9e48065f49dfbc14d766d702d5f2"
QCS8300_SHA256SUM = "c64ccd07df2aee18eb6a6e994cd37da8824bf5655cb0f0cbceabb20d9eadb8bf"

SRC_URI[qcm6490.sha256sum] = "${QCM6490_SHA256SUM}"
SRC_URI[qcs9100.sha256sum] = "${QCS9100_SHA256SUM}"
SRC_URI[qcs8300.sha256sum] = "${QCS8300_SHA256SUM}"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz;name=${PBT_ARCH}"

FILES:${PN}-cpu = "${libdir}/libfastcvopt.so* ${libdir}/libfastcvdsp_stub.so*"
FILES:${PN}-cpu += "/usr/include/*"
FILES:${PN}-dsp += "${libdir}/dsp/cdsp/cv/*"


INSANE_SKIP:${PN} = "already-stripped"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN}-cpu += "dev-so"
INSANE_SKIP:${PN}-dsp = "arch"


SOLIBS = ".so"
FILES_SOLIBSDEV = ""

ALLOW_EMPTY:${PN} = "1"

PACKAGES =+ "${PN}-cpu ${PN}-dsp"
